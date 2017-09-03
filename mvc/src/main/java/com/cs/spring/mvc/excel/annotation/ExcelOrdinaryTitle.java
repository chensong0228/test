package com.cs.spring.mvc.excel.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented 
@Inherited
public @interface ExcelOrdinaryTitle {
	
	String name() default "普通表头";
	
	int column() default -1;
	
	boolean isExport() default true;
	
	boolean nullable() default true;
	
	String[] combobox() default {};
}
