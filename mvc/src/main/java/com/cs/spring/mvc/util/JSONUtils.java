package com.cs.spring.mvc.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class JSONUtils {
    
	/**
	 * 将对象转换成JSONObject
	 * @param obj
	 * @return
	 */
    private static JSONObject getJsonObject(Object obj) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        return JSONObject.fromObject(obj, jsonConfig);
    }

    /**
	 * 将对象转换成JSONArray
	 * @param obj
	 * @return
	 */
    private static JSONArray getJsonArray(Object obj) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        return JSONArray.fromObject(obj, jsonConfig);
    }

    /**
     * 把对象转换成为Json字符串
     * @param obj
     * @return
     */
    public static String parseToJsonStr(Object obj) {
        if (obj == null) {
        	return null;
        }
        JSONObject jsonObject = JSONUtils.getJsonObject(obj);
        return jsonObject.toString();
    }

    /**
     * 把对象转换成为Json字符串
     * @param obj
     * @return
     */
    public static String parseToJsonArrayStr(Object obj) {
        if (obj == null) {
            return null;
        }
        JSONArray jsonArray = JSONUtils.getJsonArray(obj);
        return jsonArray.toString();
    }

    /**
     * 将JSON串转为一个对象
     */
    public static <T> T parseJsonToObj(String jsonStr, Class<T> objClass) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        return (T) JSONObject.toBean(jsonObject, objClass);
    }
    
    /**
     * 将JSON串转为一个对象的集合
     */
    public static <T> List<T> parseJsonToList(String jsonStr, Class<T> objClass) {
        if (jsonStr == null) {
            return null;
        }
        JSONArray jsonArray = JSONUtils.getJsonArray(jsonStr);
        return (List<T>) JSONArray.toCollection(jsonArray, objClass);
    }
    
    /**
     * 将json字符串转换成list
     */
	public static List<Map<String,String>> parseJsonToListMap(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        JSONArray jsonArray = JSONUtils.getJsonArray(jsonStr);
        return (List<Map<String, String>>) JSONArray.toCollection(jsonArray, HashMap.class);
    }
	
    /**
     * 将json字符串转换成map
     * @param jsonStr json字符串，格式如： {dictTable:"BM_XB",groupValue:"分组值"}
     * @return
     */
    public static Map<String, Object> parseJsonToMap(String jsonStr) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject jsonObj = JSONUtils.getJsonObject(jsonStr);
        for (Object key : jsonObj.keySet()) {
            result.put((String) key, jsonObj.get(key));
        }
        return result;
    }
}
