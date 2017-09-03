package com.cs.spring.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cs.spring.mvc.user.bean.UserContext;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("拦截器：方法执行前");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//请求后拦截
//		System.out.println("拦截器：方法执行后");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		System.out.println("拦截器：页面渲染后");
		//请求返回渲染页面结束后
		if(request.getQueryString().indexOf("method=login") >= 0){
			UserContext userContext = (UserContext) request.getSession().getAttribute("UserContext");
			if(userContext != null){
				System.out.println("记录登录日志：登录成功");
			}
		}else if(request.getQueryString().indexOf("method=logout") >= 0){
			System.out.println("记录登录日志：退出登录成功");
		}
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//异步请求执行
//		System.out.println("执行异步方法");
	}

}
