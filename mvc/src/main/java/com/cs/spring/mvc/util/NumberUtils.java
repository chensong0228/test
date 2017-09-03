package com.cs.spring.mvc.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NumberUtils {
	
	public static String parseString(double d, String format){
		DecimalFormat df = new DecimalFormat(format);
        return df.format(d);
	}

}
