package com.edulab.utils;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

import static com.edulab.utils.ConfigUtils.loadProp;


/**
 * CREATED BY Dream
 * DATE : 2018/11/2
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Help to load Database
 */
public class DBHelper {

    /**
     * Get an instance of Database connection pool
     * @return an instance of database connection pool
     */
    public static DruidPlugin createDruidPlugin(){
        return new DruidPlugin(PropKit.get("jdbcURL"), PropKit.get("username").trim(), PropKit.get("password").trim());
    }

    /**
     * Get base datasourse by our database
     * @return a connection data pool
     */
    public static DataSource getDataSource(){
        loadProp("pro_db_config.txt", "dev_db_config.txt");
        DruidPlugin druidPlugin = createDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

}
