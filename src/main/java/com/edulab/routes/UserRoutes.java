package com.edulab.routes;

import com.edulab.controller.user.UserController;
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
        setBaseViewPath("/view/user");
        add("/user", UserController.class,"/");
    }
}
