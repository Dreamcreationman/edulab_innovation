package com.edulab.config;

import com.edulab.model._MappingKit;
import com.edulab.routes.UserRoutes;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import static com.edulab.utils.DBHelper.createDruidPlugin;
import static com.yank.utils.ConfigUtils.loadProp;

/**
 * CREATED BY Yank
 * DATE : 2018/8/15
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Main config file
 */
public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {

        JFinal.start("src/main/webapp", 8088, "/");

    }

    @Override
    public void configConstant(Constants constants) {
        loadProp("pro_db_config.txt", "dev_db_config.txt");
        constants.setBaseUploadPath("/uploadFile");
        constants.setDevMode(PropKit.getBoolean("devMode"));
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new UserRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = createDruidPlugin();
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
