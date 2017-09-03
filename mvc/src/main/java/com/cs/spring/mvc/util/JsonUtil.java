package com.cs.spring.mvc.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.logicalcobwebs.cglib.beans.BeanMap;

public class JsonUtil {

	/**
	 * 将Object对象转换为String类型
	 */
	public static String parseJSONText(Object obj) {
		String type = getObjectType(obj);
		JSONObject jsonObject = new JSONObject();
		if (type.equals("exception")) {
			Throwable ex = (Throwable) obj;
			StringBuffer sbuf = new StringBuffer();
			exceptionToStr(ex, sbuf);
			jsonObject.put("result", sbuf.toString());
		} else {
			jsonObject.put("result", objectJSONSerialze(obj));
		}
		return jsonObject.toString();
	}
	
	/**
	 * 将JSONArray对象转换为List，若JSONArray对象对象为null或长度为0，则范围空的List
	 */
	private static List<Object> parseJSONArray(JSONArray jsonArray){
        List<Object> list = new ArrayList<Object>();
        if (jsonArray == null || jsonArray.length() == 0) {
            return list;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            Object temp = jsonArray.get(i);
            if (temp instanceof JSONArray) {
                list.add(parseJSONArray((JSONArray) temp));
            } else if (temp instanceof JSONObject) {
                list.add(parseJSONObject((JSONObject) temp));
            } else {
                String value = (String) temp;
                list.add(value);
            }
        }
        return list;
    }

	/**
	 * 将JSONObject对象转换为Map，若JSONObject对象对象为null，则范围空的Map
	 */
    private static Map<String,Object> parseJSONObject(JSONObject jsonObject){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (jsonObject == null) {
            return resultMap;
        }
        for (Iterator<String> itor = jsonObject.keys(); itor.hasNext(); ) {
            String key = itor.next();
            Object temp = jsonObject.opt(key);
            if (temp instanceof JSONArray) {
                resultMap.put(key, parseJSONArray((JSONArray) temp));

            } else if (temp instanceof JSONObject) {
                resultMap.put(key, parseJSONObject((JSONObject) temp));
            } else {
                String value = (String) temp;
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }
	
	@SuppressWarnings("unchecked")
	private static Object objectJSONSerialze(Object obj) {
        String type = getObjectType(obj);
        if (type.equals("boolean") || type.equals("int") || type.equals("long") || type.equals("float") || type.equals("string") || type.equals("date")) {
            return obj.toString();
        } else if (type.equals("collection")) {
            return dealCollection((Collection<Object>) obj);
        } else if (type.equalsIgnoreCase("map")) {
            return dealMap((Map<Object,Object>) obj);
        } else if (type.equals("array")) {
            return dealArray((Object[]) obj);
        } else if (type.equals("object")) {
        	Map<Object,Object> map = BeanMap.create(obj);
            return dealM(map);
        } else {
            return "";
        }
    }
	
	private static Object dealM(Map<Object,Object> map) {
        Iterator<Object> ir = map.keySet().iterator();
        while (ir.hasNext()) {
            Object key = ir.next();
            Object obj = map.get(key);
            map.put(key, objectJSONSerialze(obj));
        }
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
	
	private static Object dealArray(Object[] obj) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < obj.length; i++) {
            list.add(objectJSONSerialze(obj[i]));
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray;
    }
	
	private static Object dealMap(Map<Object,Object> map) {
        Iterator<Object> ir = map.keySet().iterator();
        while (ir.hasNext()) {
            Object key = ir.next();
            Object obj = map.get(key);
            map.put(key, objectJSONSerialze(obj));
        }
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
	
	private static Object dealCollection(Collection<Object> collection) {
        List<Object> list = new ArrayList<Object>();
        Iterator<Object> it = collection.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            list.add(objectJSONSerialze(obj));
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray;
    }

	private static void exceptionToStr(Throwable ex, StringBuffer sbuf) {
        StackTraceElement[] em = ex.getStackTrace();
        sbuf.append("the cause:" + ex + "\n");
        for (int i = 0; i < em.length; i++) {
        	sbuf.append("\tat... " + em[i] + "\n");
        }
        sbuf.append("\t... more:" + em.length + "\n");
        Throwable nEx = ex.getCause();
        if (nEx != null) {
            exceptionToStr(nEx, sbuf);
        }
    }
	
	private static String getObjectType(Object obj) {
		if (obj == null) {
			return "null";
		} else if (obj instanceof Exception) {
			return "exception";
		} else if (obj instanceof Integer || obj instanceof BigInteger || obj instanceof BigDecimal) {
			return "int";
		} else if (obj instanceof Float || obj instanceof Double) {
			return "float";
		} else if (obj instanceof Long) {
			return "long";
		} else if (obj instanceof Boolean) {
			return "boolean";
		} else if (obj instanceof Date) {
			return "date";
		} else if (obj instanceof String) {
			return "string";
		} else if (obj instanceof Collection) {
			return "collection";
		} else if (obj instanceof Map) {
			return "map";
		} else if (obj.getClass().isArray()) {
			return "array";
		} else if (obj instanceof Element) {
			return "element";
		} else {
			return "object";
		}
	}

}
