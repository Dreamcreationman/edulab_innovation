package com.edulab.shiro;

import com.edulab.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * CREATED BY Yank
 * DATE : 2018/11/3
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Provide Some basic operation for shiro authority or authentication
 */
public class ShiroUtils {

    /**
     * Get current authorized subject
     * @return current authorized subject
     */
    public static ShiroPrincipal getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        return (ShiroPrincipal)(subject.getPrincipal());
    }

    /**
     * Get current authorized user
     * @return current authorized user
     */
    public static User getUser(){
        ShiroPrincipal shiroPrincipal = getPrincipal();
        if (shiroPrincipal != null){
            return shiroPrincipal.getUser();
        }
        return null;
    }

    /**
     * judge id user is login by userID
     * @return
     */
    public static int getUserID(){
        ShiroPrincipal principal = getPrincipal();
        if (principal != null){
            return principal.getUserId();
        }
        return -1;
    }
}
