package com.cs.spring.mvc.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WebServiceLogAspect {

	@Pointcut("execution(* com.cs..*WebService.*(..))")
	private void allMethod() {
	}

	// 针对指定的切入点表达式选择的切入点应用前置通知
	@Before("allMethod()")
	public void before(JoinPoint joinPoint) {
//		System.out.println("【注解-前置通知】:" + className + "类的" + methodName + "方法开始了");
	}

	// 访问命名切入点来应用后置通知
	@AfterReturning("allMethod()")
	public void afterReturn(JoinPoint joinPoint) {
//		System.out.println("【注解-后置通知】:方法正常结束了");
	}

	// 应用最终通知
	@After("allMethod()")
	public void after(JoinPoint joinPoint) {
		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			String classType = joinPoint.getArgs()[i].getClass().getName();
			if(StringUtils.equals(classType, "org.apache.catalina.connector.RequestFacade")){
				HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[i];
				Map<String, Object> paramMap = request.getParameterMap();
				String className = joinPoint.getTarget().getClass().getName();
				String methodName = joinPoint.getSignature().getName();
				System.out.println("WebService:class="+className+";methodName="+methodName+";paramMap="+paramMap.toString());
			}
		}
	}

	// 应用异常抛出后通知
	@AfterThrowing("allMethod()")
	public void afterThrowing(JoinPoint joinPoint) {
//		System.out.println("【注解-异常抛出后通知】:方法执行时出异常了");
	}
}
