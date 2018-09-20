package com.edulab.controller.user;

import com.edulab.model.UserAuths;
import com.edulab.utils.ResultUtils;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * CREATED BY Dream
 * DATE : 2018/8/27
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class UserController extends Controller {

    UserService service = UserService.me;
    ResultUtils resultUtils;

    @ActionKey("/login")
    public void login() {
        render("login/index.html");
    }

    @ActionKey("/loginAuth")
    public void loginAuth() {
        UserAuths userAuths = getBean(UserAuths.class);
        String loginIp = getPara("loginIp");
        if (loginIp != null) {
            String msg = service.checkLogin(userAuths, loginIp);
            if (msg.equals("登录成功")) {
                resultUtils = new ResultUtils(true, msg, null);
            } else {
                resultUtils = new ResultUtils(false, msg, null);
            }
        } else {
            String msg = "未知错误";
            resultUtils = new ResultUtils(false, msg, null);
        }
        renderJson(resultUtils);
    }

    @ActionKey("/register")
    public void register() {
        render("register/index.html");
    }

    @ActionKey("/registerAuth")
    public void registerAuth() {
        UserAuths userAuths = getBean(UserAuths.class);
        if (service.isUniqueUsername(userAuths.getIdentifier())) {
            String registerIp = getPara("registerIp");
            service.getUserRegister(registerIp, userAuths);
            resultUtils = new ResultUtils(true, "注册成功", null);
        } else {
            resultUtils = new ResultUtils(false, "用户名已被使用", null);
        }
        renderJson(resultUtils);

    }

}
