package com.edulab.routes;

import com.edulab.controller.admin.AdminController;
import com.edulab.controller.user.UserController;
import com.edulab.interceptor.AdminInterceptor;
import com.jfinal.config.Routes;

/**
 * CREATED BY Yank
 * DATE : 2018/11/7
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : User operation routes management
 */
public class UserRoutes extends Routes {

    @Override
    public void config() {
        setBaseViewPath("/WEB-INF/html");

        addInterceptor(new AdminInterceptor());
        add("/user", UserController.class);
        add("/admin", AdminController.class);
    }
}
