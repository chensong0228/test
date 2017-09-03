package com.cs.spring.mvc.cache.memory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesCacheUtils {
	
	private static Properties propertiesCache = new Properties();

	public static void loadPropertiesFile(String propertiesURI) {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties(propertiesURI);
			propertiesCache.putAll(properties);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
    }
	
	public static void loadloadPropertiesConfig(List<Map<String,String>> configs){
		for (Map<String,String> config : configs) {
			String key = MapUtils.getString(config, "KEY","");
			String value = MapUtils.getString(config, "VALUE","");
			if(StringUtils.equals(key, "")){
				propertiesCache.put(key,value);
			}
		}
	}
	
	public static String getProperty(String key){
		return propertiesCache.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue){
		return propertiesCache.getProperty(key, defaultValue);
	}
	
	public static void setProperty(String key,String value){
		propertiesCache.put(key, value);
	}
	
	public boolean containsKey(String key){
		return propertiesCache.containsKey(key);
	}
	
	public void remove(String key){
		propertiesCache.remove(key);
	}
	
	public void removeAll(){
		propertiesCache.clear();
	}
	
	public void isEmpty(){
		propertiesCache.isEmpty();
	}

}
