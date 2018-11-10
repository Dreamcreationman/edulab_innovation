package com.edulab.interceptor;

import com.edulab.shiro.ShiroUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * CREATED BY Yank
 * DATE : 2018/11/10
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : add admin interceptor
 */
public class AdminInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        if (ShiroUtils.getUser() == null){
            invocation.getController().redirect("/login");
        }
        invocation.invoke();
    }
}
