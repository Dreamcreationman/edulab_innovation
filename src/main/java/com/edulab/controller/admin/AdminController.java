package com.edulab.controller.admin;

import com.edulab.model.Menu;
import com.edulab.model.MenuRole;
import com.edulab.model.UserRole;
import com.edulab.shiro.ShiroUtils;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import java.util.Set;

/**
 * CREATED BY Yank
 * DATE : 2018/11/10
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : User admin after login successfully
 */
public class AdminController extends Controller {

    @ActionKey("/admin")
    public void index(){
        if (ShiroUtils.getUserID() == -1){
            redirect("/login");
            return;
        }
        Set<Integer> roleIDs = UserRole.dao.getRoleIDByUserID(ShiroUtils.getUserID());
        Set<Integer> menuIDs = MenuRole.dao.getMenuIDByRoleIDs(roleIDs);
        Set<Menu> topMenus = Menu.dao.getMenusByMenuIds(menuIDs, 0);
        Set<Menu> oneMenus = Menu.dao.getMenusByMenuIds(menuIDs, 1);
        setAttr("topMenus", topMenus);
        setAttr("oneMenus", oneMenus);
        System.out.println(topMenus.size()+" "+oneMenus.size());
        render("menu/index.html");
    }
}
