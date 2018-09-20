package com.edulab.model_base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserAuths<M extends BaseUserAuths<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setUserId(java.lang.Long userId) {
		set("userId", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public M setIdentityType(java.lang.String identityType) {
		set("identityType", identityType);
		return (M)this;
	}
	
	public java.lang.String getIdentityType() {
		return getStr("identityType");
	}

	public M setInsideLogin(java.lang.Integer insideLogin) {
		set("insideLogin", insideLogin);
		return (M)this;
	}
	
	public java.lang.Integer getInsideLogin() {
		return getInt("insideLogin");
	}

	public M setIdentifier(java.lang.String identifier) {
		set("identifier", identifier);
		return (M)this;
	}
	
	public java.lang.String getIdentifier() {
		return getStr("identifier");
	}

	public M setSalt(java.lang.String salt) {
		set("salt", salt);
		return (M)this;
	}
	
	public java.lang.String getSalt() {
		return getStr("salt");
	}

	public M setCredential(java.lang.String credential) {
		set("credential", credential);
		return (M)this;
	}
	
	public java.lang.String getCredential() {
		return getStr("credential");
	}

}
