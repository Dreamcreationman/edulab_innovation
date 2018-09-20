package com.edulab.controller.user;

import com.edulab.model.UserAuths;
import com.edulab.shiro.ShiroCredentialMatcher;
import com.edulab.shiro.ShiroRealm;
import com.edulab.utils.CryptoUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * CREATED BY Dream
 * DATE : 2018/8/27
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class UserService {

    public static final UserService me = new UserService();

    /**
     * 判断用户名是否唯一
     *
     * @param username 用户名
     * @return
     */
    public boolean isUniqueUsername(String username) {
        System.out.println(username);
        List<Record> users = Db.find("select * from edu_user_auths where identifier = ?", username);
        if (users.size() != 0) {
            return false;
        }
        return true;
    }

    /**
     * 将用户写入登录表和基本信息表
     *
     * @param registerIp 注册ip
     * @param auths      注册信息
     */
    public void getUserRegister(String registerIp, UserAuths auths) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dateFormat.format(new Date());
        Record user = new Record().set("updateTime", updateTime).set("registerTime", updateTime).set("status", 0).set("registerIp", registerIp);
        Db.save("edu_user", user);

        String salt = CryptoUtils.getSalt();
        String credential = CryptoUtils.getHash(auths.getCredential(), salt);
        Record userAuth = new Record().set("userId", user.get("id")).set("identityType", "username").set("insideLogin", 0)
                .set("identifier", auths.getIdentifier()).set("salt", salt).set("credential", credential);
        Db.save("edu_user_auths", userAuth);
    }


    /**
     * 执行登录
     *
     * @param userAuths
     * @return
     */
    public String checkLogin(UserAuths userAuths, String lastLoginIp) {
        String msg = "登录成功";
        ShiroRealm shiroRealm = new ShiroRealm();
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        shiroRealm.setCredentialsMatcher(new ShiroCredentialMatcher());
        defaultSecurityManager.setRealm(shiroRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userAuths.getIdentifier(), userAuths.getCredential());
        try {
            subject.login(token);
        } catch (ExpiredCredentialsException e) {
            msg = "账户token已过期，请重新授权";
        } catch (IncorrectCredentialsException e2) {
            msg = "密码错误，请检查密码";
        } catch (ExcessiveAttemptsException e3) {
            msg = "您短时间内尝试次数过多，请稍后重试或重置密码";
        } catch (LockedAccountException e4) {
            msg = "账户已被锁定，若有疑问，请向管理员申诉";
        } catch (UnknownAccountException e4) {
            msg = "未找到此用户，请检查您的用户名";
        }
        if (msg.equals("登录成功")) {
            updateLoginSuccess(userAuths.getIdentifier(), lastLoginIp);
        }
        System.out.println(subject.hasRole("root"));
        return msg;
    }

    /**
     * 更新最后登录的时间和IP，时间自己获取当前时间
     *
     * @param identifier
     * @param lastLoginIp 传入登录的IP
     */
    private void updateLoginSuccess(String identifier, String lastLoginIp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dateFormat.format(new Date());
        Record user = Db.findById("edu_user", UserAuths.dao.getIdByIdentifier(identifier)).set("lastLoginTime", updateTime).set("lastLoginIp", lastLoginIp);
        Db.update("edu_user", user);
    }

}
