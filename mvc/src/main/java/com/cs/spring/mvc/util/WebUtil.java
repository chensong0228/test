package com.cs.spring.mvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cs.spring.mvc.user.bean.UserContext;

public class WebUtil {

	public static UserContext getLoginedUserContext(HttpServletRequest request) {
		return (UserContext) request.getSession().getAttribute("UserContext");
	}
}
