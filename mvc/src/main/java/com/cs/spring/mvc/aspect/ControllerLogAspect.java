package com.cs.spring.mvc.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

public class ControllerLogAspect {
	
	public void before(JoinPoint joinPoint) {

	}

	public void afterReturn(JoinPoint joinPoint) {
		
	}

	public void after(JoinPoint joinPoint) {
		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			String classType = joinPoint.getArgs()[i].getClass().getName();
			if(StringUtils.equals(classType, "org.apache.catalina.connector.RequestFacade")){
				HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[i];
				Map<String, Object> paramMap = request.getParameterMap();
				String className = joinPoint.getTarget().getClass().getName();
				String methodName = joinPoint.getSignature().getName();
				System.out.println("Controller:class="+className+";methodName="+methodName+";paramMap="+paramMap.toString());
			}
		}
	}

	public void afterThrowing(JoinPoint joinPoint) {
		
	}
}
