package com.cs.spring.mvc.user.bean;

import java.io.Serializable;

public class UserContextHolder implements Serializable {

	private static final long serialVersionUID = -8222859118216476499L;

	private static ThreadLocal<UserContext> threadLocal = new ThreadLocal<UserContext>();

	public UserContextHolder() {
		super();
	}

	public static void setUserContext(UserContext userContext) {
		threadLocal.set(userContext);
	}

	public static UserContext getUserContext() {
		return threadLocal.get();
	}

	public static void cleanUserContext() {
		threadLocal.set(null);
	}
}
