package com.edulab.model_base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> implements IBean {

	public M setRoleId(java.lang.Long roleId) {
		set("roleId", roleId);
		return (M)this;
	}
	
	public java.lang.Long getRoleId() {
		return getLong("roleId");
	}

	public M setRoleName(java.lang.String roleName) {
		set("roleName", roleName);
		return (M)this;
	}
	
	public java.lang.String getRoleName() {
		return getStr("roleName");
	}

	public M setGenTime(java.util.Date genTime) {
		set("genTime", genTime);
		return (M)this;
	}
	
	public java.util.Date getGenTime() {
		return get("genTime");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}
	
	public java.lang.String getDescription() {
		return getStr("description");
	}

}
