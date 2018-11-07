package com.edulab.model_base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public M setUserId(java.lang.Long userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public M setAvatar(java.lang.String avatar) {
		set("avatar", avatar);
		return (M)this;
	}
	
	public java.lang.String getAvatar() {
		return getStr("avatar");
	}

	public M setRealname(java.lang.String realname) {
		set("realname", realname);
		return (M)this;
	}
	
	public java.lang.String getRealname() {
		return getStr("realname");
	}

	public M setMotto(java.lang.String motto) {
		set("motto", motto);
		return (M)this;
	}
	
	public java.lang.String getMotto() {
		return getStr("motto");
	}

	public M setBirth(java.util.Date birth) {
		set("birth", birth);
		return (M)this;
	}
	
	public java.util.Date getBirth() {
		return get("birth");
	}

	public M setGender(java.lang.Integer gender) {
		set("gender", gender);
		return (M)this;
	}
	
	public java.lang.Integer getGender() {
		return getInt("gender");
	}

	public M setCountry(java.lang.String country) {
		set("country", country);
		return (M)this;
	}
	
	public java.lang.String getCountry() {
		return getStr("country");
	}

	public M setProvince(java.lang.String province) {
		set("province", province);
		return (M)this;
	}
	
	public java.lang.String getProvince() {
		return getStr("province");
	}

	public M setCity(java.lang.String city) {
		set("city", city);
		return (M)this;
	}
	
	public java.lang.String getCity() {
		return getStr("city");
	}

	public M setAcademy(java.lang.String academy) {
		set("academy", academy);
		return (M)this;
	}
	
	public java.lang.String getAcademy() {
		return getStr("academy");
	}

	public M setClasses(java.lang.String classes) {
		set("classes", classes);
		return (M)this;
	}
	
	public java.lang.String getClasses() {
		return getStr("classes");
	}

	public M setBankcard(java.lang.String bankcard) {
		set("bankcard", bankcard);
		return (M)this;
	}
	
	public java.lang.String getBankcard() {
		return getStr("bankcard");
	}

	public M setRegisterIp(java.lang.String registerIp) {
		set("register_ip", registerIp);
		return (M)this;
	}
	
	public java.lang.String getRegisterIp() {
		return getStr("register_ip");
	}

	public M setRegisterTime(java.util.Date registerTime) {
		set("register_time", registerTime);
		return (M)this;
	}
	
	public java.util.Date getRegisterTime() {
		return get("register_time");
	}

	public M setLastLoginIp(java.lang.String lastLoginIp) {
		set("last_login_ip", lastLoginIp);
		return (M)this;
	}
	
	public java.lang.String getLastLoginIp() {
		return getStr("last_login_ip");
	}

	public M setLastLoginTime(java.util.Date lastLoginTime) {
		set("last_login_time", lastLoginTime);
		return (M)this;
	}
	
	public java.util.Date getLastLoginTime() {
		return get("last_login_time");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

}
