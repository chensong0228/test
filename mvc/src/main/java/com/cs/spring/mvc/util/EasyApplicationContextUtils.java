package com.cs.spring.mvc.util;

import org.springframework.context.ApplicationContext;

/**
 * 在Spring容器启动时，框架会将ApplicationContext的引用设置到EasyApplicationContextUtils中
 * <p/>
 * <p/>
 * 这样就可以直接使用该工具类从Spring中获取Bean了。
 *
 * @author <a href="mailto:javawen@gmail.com">文建国</a>
 * @version 1.0
 * @since 28-Feb-2011
 */
public class EasyApplicationContextUtils {
	
    private static ApplicationContext ctx;

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
    
    public static void setApplicationContext(ApplicationContext ctx) {
        EasyApplicationContextUtils.ctx = ctx;
    }
    
    public static Object getBeanByName(String beanName) {
    	return ctx.getBean(beanName);
    }

    public static <T> T getBeanByName(String beanName, Class<T> clazz) {
        return (T) getBeanByName(beanName);
    }

}
