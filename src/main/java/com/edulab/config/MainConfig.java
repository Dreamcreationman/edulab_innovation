package com.edulab.config;

import com.edulab.controller.user.UserController;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * CREATED BY Dream
 * DATE : 2018/8/15
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {

        JFinal.start("src/main/webapp", 8080, "/");

    }

    public void configConstant(Constants constants) {
        PropKit.use("project_config.txt");
        constants.setBaseUploadPath("/uploadFile");
        constants.setDevMode(PropKit.getBoolean("devMode"));
    }

    public void configRoute(Routes routes) {
        routes.setBaseViewPath("/view");
        routes.add("/user", UserController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.use("db.txt").get("jdbcUrl"), PropKit.use("db.txt").get("username"), PropKit.use("db.txt").get("password"));
        plugins.add(druidPlugin);

        ActiveRecordPlugin recordPlugin = new ActiveRecordPlugin(druidPlugin);
        plugins.add(recordPlugin);
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {
    }
}
