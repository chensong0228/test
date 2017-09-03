package com.cs.spring.mvc.excel.core.poi;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ExcelUtil {
	
	public static void main(String[] args) {
		test();
	}

	public static void test(){
		try {
//			BeanUtils.setProperty(Test.class, "id", "123");
			Test t = new Test();
			BeanUtils.setProperty(t, "id", "123");
			System.out.println(t.getId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
