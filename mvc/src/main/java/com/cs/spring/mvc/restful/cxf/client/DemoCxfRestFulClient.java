package com.cs.spring.mvc.restful.cxf.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Component
public class DemoCxfRestFulClient {

	@Autowired(required=false)
	@Qualifier("restFulClient") 
	private WebClient restFulClient;
	
	public void testAll() {
		System.out.println("测试--testPutQueryParam");
		testPutQueryParam();
		System.out.println("测试--testPostQueryParam");
		testPostQueryParam();
		System.out.println("测试--testDeleteQueryParam");
		testDeleteQueryParam();
		System.out.println("测试--testPutQueryParam");
		testPutQueryParam();
		System.out.println("测试--testDeletePathParam");
		testDeletePathParam();
		System.out.println("测试--testPostPathyParam");
		testPostPathyParam();
		System.out.println("测试--testDeletePathParam");
		testDeletePathParam();
		System.out.println("测试--testPutPathParam");
		testPutPathParam();
    }
	
	public void testGetQueryParam() {
    	String result = restFulClient.reset().path("get/queryParam").replaceQueryParam("id","id信息").replaceQueryParam("name","name信息").accept(MediaType.APPLICATION_JSON).get(String.class);
    	System.out.println(result);
    }
	
	public void testPostQueryParam() {
    	String result = restFulClient.reset().path("post/queryParam").replaceQueryParam("id","id信息").replaceQueryParam("name","name信息").accept(MediaType.APPLICATION_JSON).post("post信息", String.class);
    	System.out.println(result);
    }
	
	public void testDeleteQueryParam() {
    	restFulClient.reset().path("delete/queryParam").replaceQueryParam("id","id信息").replaceQueryParam("name","name信息").accept(MediaType.APPLICATION_JSON).delete();
    }
	
	public void testPutQueryParam() {
    	String result = restFulClient.reset().path("put/queryParam").replaceQueryParam("id","id信息").accept(MediaType.APPLICATION_JSON).put("put信息", String.class);
    	System.out.println(result);
    }
	
	
	public void testGetPathParam() {
    	String result = restFulClient.reset().path("get/pathParam/{id}/{name}","id信息","name信息").accept(MediaType.APPLICATION_JSON).get(String.class);
    	System.out.println(result);
    }
	
	public void testPostPathyParam() {
    	String result = restFulClient.reset().path("post/pathParam/{id}/{name}","id信息","name信息").accept(MediaType.APPLICATION_JSON).post("post信息", String.class);
    	System.out.println(result);
    }
	
	public void testDeletePathParam() {
    	restFulClient.reset().path("/delete/pathParam/{id}/{name}","id信息","name信息").accept(MediaType.APPLICATION_JSON).delete();
    }
	
	public void testPutPathParam() {
    	String result = restFulClient.reset().path("/put/pathParam/{id}","id信息").accept(MediaType.APPLICATION_JSON).put("put信息", String.class);
    	System.out.println(result);
    }

}
