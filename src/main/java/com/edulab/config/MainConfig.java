package com.edulab.config;

import com.edulab.interceptor.GlobalInterceptor;
import com.edulab.model._MappingKit;
import com.edulab.routes.UserRoutes;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import static com.edulab.utils.ConfigUtils.loadProp;
import static com.edulab.utils.DBHelper.createDruidPlugin;

/**
 * CREATED BY Yank
 * DATE : 2018/8/15
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Main config file
 */
public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {

        JFinal.start("src/main/webapp", 8888, "/");

    }

    @Override
    public void configConstant(Constants constants) {
        loadProp("pro_db_config.txt", "dev_db_config.txt");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        constants.setBaseUploadPath("/uploadFile");
        constants.setEncoding("utf-8");
        constants.setDevMode(PropKit.getBoolean("devMode", true));
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
        interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("BASE_PATH"));
    }
}
