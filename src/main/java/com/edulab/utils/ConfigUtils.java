package com.edulab.utils;

import com.jfinal.kit.PropKit;

/**
 * CREATED BY Dream
 * DATE : 2018/11/2
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Help to load config files
 */
public class ConfigUtils {

    /**
     * Generate different config.properties file for product environment and develop environment
     * @param pro product environment config.properties file
     * @param dev develop environment config.properties file
     */
    public static void loadProp(String pro, String dev){
        try{
            PropKit.use(pro);
        }catch (Exception e){
            PropKit.use(dev);
        }
    }
}
