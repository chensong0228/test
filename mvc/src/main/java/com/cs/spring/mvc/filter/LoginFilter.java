package com.cs.spring.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cs.spring.mvc.user.bean.UserContext;

public class LoginFilter implements Filter {

	private static final String[] INHERENT_ESCAPE_URIS = { "/user/login.spr?method=toLogin","/user/login.spr?method=login" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		UserContext userContext = (UserContext) httpRequest.getSession().getAttribute("UserContext");
		if(userContext == null && !isEscapeURI(httpRequest.getRequestURI()+"?"+httpRequest.getQueryString())){
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/login.spr?method=toLogin");  
		}else{
			httpRequest.getSession().setAttribute("UserContext", userContext);
			chain.doFilter(request, response);
			return;
		}
	}

	private boolean isEscapeURI(String requestURI) {
		for (int i = 0; i < INHERENT_ESCAPE_URIS.length; i++) {
			if (requestURI != null && requestURI.indexOf(INHERENT_ESCAPE_URIS[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	@Override
	public void destroy() {

	}

}
