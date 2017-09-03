package com.cs.spring.mvc.user.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserContext implements Serializable {

	private static final long serialVersionUID = 663158648067942364L;

	private User user;
	
	private List<String> authorityList = new ArrayList<String>();

	public UserContext() {
		super();
	}
	
	public UserContext(User user, List<String> authorityList) {
		super();
		this.user = user;
		this.authorityList = authorityList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}
}
