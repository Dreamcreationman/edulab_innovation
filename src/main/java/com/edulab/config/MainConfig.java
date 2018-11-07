package com.edulab.config;

import com.edulab.controller.user.UserController;
import com.edulab.model._MappingKit;
import com.edulab.routes.UserRoutes;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * CREATED BY Yank
 * DATE : 2018/8/15
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Main config file
 */
public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {

        JFinal.start("src/main/webapp", 8080, "/");

    }

    @Override
    public void configConstant(Constants constants) {
        PropKit.use("project_config.txt");
        constants.setBaseUploadPath("/uploadFile");
        constants.setDevMode(PropKit.getBoolean("devMode"));
    }

    @Override
    public void configRoute(Routes routes) {
        routes.setBaseViewPath("/view");
        routes.add(new UserRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.use("db.txt").get("jdbcUrl"), PropKit.use("db.txt").get("username"), PropKit.use("db.txt").get("password"));
        plugins.add(druidPlugin);

        ActiveRecordPlugin recordPlugin = new ActiveRecordPlugin(druidPlugin);
        _MappingKit.mapping(recordPlugin);
        plugins.add(recordPlugin);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
    }
}
