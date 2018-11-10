package com.edulab.shiro;


import com.edulab.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY Dream
 * DATE : 2018/11/3
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : User define principal
 */
public class ShiroPrincipal implements Serializable {

    private static final long serialVersionUID = -8171213389613607984L;
    private User user;
    private List<String> roles = new ArrayList<String>();
    private List<String> authorities = new ArrayList<String>();

    public  ShiroPrincipal(User user){
        this.user = user;
    }
    /**
     * 通过当前认证的主体设置字段判定是否认证，减少查库
     */
    private boolean isAuthorized = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public int getUserId(){
        return this.user.getUserId();
    }

    @Override
    public String toString() {
        return this.user.get("realname");
    }
}
