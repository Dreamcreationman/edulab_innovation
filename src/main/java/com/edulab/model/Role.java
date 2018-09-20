package com.edulab.model;

import com.edulab.model_base.BaseRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Role extends BaseRole<Role> {

	public static final Role dao = new Role();

    /**
     * 根据传入的角色ID查询角色名
     * @param roleId
     * @return
     */
	public String getRoleNameById(int roleId){
        String sqlRole = "select roleName from edu_role where roleId = ?";
        List<Role> role = find(sqlRole,roleId);
        return role.get(0).getRoleName();
    }
}