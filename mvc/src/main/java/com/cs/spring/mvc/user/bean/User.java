package com.cs.spring.mvc.user.bean;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

public class User implements Serializable{

	private static final long serialVersionUID = -3567278415123507500L;
	
	private String userId;
	private String userName;
	private String realName;
	private String password;
	private String loginType;
	private String title;
	private String orgId;
	private String orgName;
	
	public User() {
		super();
	}
	
	public User(String userId, String userName,String realName, String password, String loginType, String title, String orgId, String orgName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.loginType = loginType;
		this.title = title;
		this.orgId = orgId;
		this.orgName = orgName;
	}
	
	public User(Map<String, Object> userInfo){
		super();
		this.userId = MapUtils.getString(userInfo, "userId");
		this.userName = MapUtils.getString(userInfo, "userName");
		this.realName = MapUtils.getString(userInfo, "realName");
		this.password = MapUtils.getString(userInfo, "password");
		this.loginType = MapUtils.getString(userInfo, "loginType");
		this.title = MapUtils.getString(userInfo, "title");
		this.orgId = MapUtils.getString(userInfo, "orgId");
		this.orgName = MapUtils.getString(userInfo, "orgName");
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
