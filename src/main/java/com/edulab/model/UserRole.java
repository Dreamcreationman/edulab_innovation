package com.edulab.model;

import com.edulab.model_base.BaseUserRole;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class UserRole extends BaseUserRole<UserRole> {

	public static final UserRole dao = new UserRole();

	/**
	 * 根据传入的用户ID查询用户的角色信息
	 * @param userId
	 * @return
	 */
	public Set<String> getRolesById(int userId){
        Set<String> roles = new HashSet();
        String sql = "select role_id from edu_user_role where user_id = ?";
        List<Record> roleAll = Db.find(sql,userId);
        for (int i =0; i <roleAll.size();i++){
        	String roleName = Role.dao.getRoleNameById(roleAll.get(i).getInt("role_id"));
        	if (roleName != null){
        		roles.add(roleName);
			}
        }
        return roles;
    }
}
