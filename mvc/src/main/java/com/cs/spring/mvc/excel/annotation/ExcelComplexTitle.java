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
public @interface ExcelComplexTitle {
	
	String name() default "表头";
	
	int startColumn() default -1;
	
	int columnLength() default 0;
	
	int startRow() default -1;
	
	int rowLength() default 0;
	
	boolean isExport() default true;
	
	boolean nullable() default true;
	
	String[] combobox() default {};
}
