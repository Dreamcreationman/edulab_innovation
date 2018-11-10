package com.edulab.controller.user;

import com.edulab.model.User;
import com.edulab.model.UserAuth;
import com.edulab.shiro.ShiroUtils;
import com.edulab.utils.ResultUtils;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * CREATED BY Yank
 * DATE : 2018/8/27
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : base user action controller
 */
public class UserController extends Controller {

    UserService service = UserService.me;
    ResultUtils resultUtils;

    @ActionKey("/login")
    public void login() {
        //if user already login in, then, redirect to admin
        if (ShiroUtils.getUserID() != -1){
            redirect("/admin");
            return;
        }
        //return background image
        setAttr("bgImg", "/upload/bg.png");
        render("login/index.html");
    }

    public void loginAuth() {
        String msg = "";
        UserAuth userAuth = getBean(UserAuth.class);
        String loginIp = getPara("loginIp");
        boolean rememberMe = getParaToBoolean("rememberMe", false);
        if (userAuth.getIdentifier() == null || userAuth.getCredential() == null){
            msg = "用户信息不能为空";
            resultUtils = new ResultUtils(false, msg, null);
        }else if (loginIp == null){
            msg = "位置信息获取失败";
            resultUtils = new ResultUtils(false, msg, null);
        }else{
            msg = service.checkLogin(userAuth, loginIp, rememberMe);
            if (msg.equals("登录成功")) {
                resultUtils = new ResultUtils(true, msg, null);
            } else {
                resultUtils = new ResultUtils(false, msg, null);
            }
        }

        renderJson(resultUtils);
    }

    @ActionKey("/register")
    public void register() {
        if (ShiroUtils.getUserID() != -1L){
            redirect("/admin");
            return;
        }
        render("register/index.html");
    }

    @ActionKey("/register_auth")
    public void registerAuth() {
        User user = getBean(User.class);
        UserAuth userAuth = getBean(UserAuth.class);
        if (service.isUniqueUsername(userAuth.getIdentifier())) {
            service.getUserRegister(userAuth, user);
            resultUtils = new ResultUtils(true, "注册成功", null);
        } else {
            resultUtils = new ResultUtils(false, "用户名已被使用", null);
        }
        renderJson(resultUtils);

    }

}
