package com.edulab.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * CREATED BY Yank
 * DATE : 2018/11/8
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : A global interceptor (it's of no use temporarily)
 */
public class GlobalInterceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
