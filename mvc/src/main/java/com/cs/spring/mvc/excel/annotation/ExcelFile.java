package com.cs.spring.mvc.excel.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented 
@Inherited
public @interface ExcelFile {
	
	String name() default "导出EXCEL文件名示例";
}
