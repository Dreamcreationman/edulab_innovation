package com.edulab.controller.user;

import com.edulab.model.User;
import com.edulab.model.UserAuth;
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
 * CREATED BY Yank
 * DATE : 2018/8/27
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : User controller logic code implementation
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
        List<User> users = User.dao.find("select * from edu_user_auth where identifier = ?", username);
        if (users.size() != 0) {
            return false;
        }
        return true;
    }

    /**
     * 将用户写入登录表和基本信息表
     *
     * @param auth      注册安全信息
     * @param user      注册基本信息
     */
    public void getUserRegister(UserAuth auth, User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dateFormat.format(new Date());
        user.set("update_time", updateTime).set("register_time", updateTime).set("status", 0).save();

        String salt = CryptoUtils.getSalt();
        String credential = CryptoUtils.getHash(auth.getCredential(), salt);
        auth.set("user_id", user.getUserId()).set("identity_type", "username").set("inside_login", 0).set("salt", salt).set("credential", credential).save();
    }


    /**
     * 执行登录
     *
     * @param userAuth
     * @return
     */
    public String checkLogin(UserAuth userAuth, String lastLoginIp) {
        String msg = "登录成功";
        ShiroRealm shiroRealm = new ShiroRealm();
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        shiroRealm.setCredentialsMatcher(new ShiroCredentialMatcher());
        defaultSecurityManager.setRealm(shiroRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userAuth.getIdentifier(), userAuth.getCredential());
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
            updateLoginSuccess(userAuth.getIdentifier(), lastLoginIp);
        }
        return msg;
    }

    /**
     * 更新最后登录的时间和IP，时间获取当前时间
     *
     * @param identifier
     * @param lastLoginIp 传入登录的IP
     */
    private void updateLoginSuccess(String identifier, String lastLoginIp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dateFormat.format(new Date());
        User user = User.dao.findById(UserAuth.dao.getIdByIdentifier(identifier)).set("last_login_time", updateTime).set("last_login_ip", lastLoginIp);
        user.update();
    }

}
