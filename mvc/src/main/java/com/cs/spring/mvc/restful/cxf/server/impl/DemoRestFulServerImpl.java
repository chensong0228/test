package com.cs.spring.mvc.restful.cxf.server.impl;

import com.cs.spring.mvc.restful.cxf.server.DemoRestFulServer;

public class DemoRestFulServerImpl implements DemoRestFulServer{

	@Override
	public String testGetQueryParam(String id, String name) {
		System.out.println("testGetQueryParam:id="+id+";name="+name);
		return "testGetQueryParam:id="+id+";name="+name;
	}

	@Override
	public String testPostQueryParam(String id, String name, String postStr) {
		System.out.println("testPostQueryParam:id="+id+";name="+name+";postStr="+postStr);
		return "testPostQueryParam:id="+id+";name="+name+";postStr="+postStr;
	}

	@Override
	public void testDeleteQueryParam(String id, String name) {
		System.out.println("testDeleteQueryParam:id="+id+";name="+name);
	}

	@Override
	public String testPutQueryParam(String id, String info) {
		System.out.println("testPutQueryParam:id="+id+";info="+info);
		return "testPutQueryParam:id="+id+";info="+info;
	}

	@Override
	public String testGetPathParam(String id, String name) {
		System.out.println("testGetPathParam:id="+id+";name="+name);
		return "testGetPathParam:id="+id+";name="+name;
	}

	@Override
	public String testPostPathParam(String id, String name, String postStr) {
		System.out.println("testPostPathParam:id="+id+";name="+name+";postStr="+postStr);
		return "testPostPathParam:id="+id+";name="+name+";postStr="+postStr;
	}

	@Override
	public void testDeletePathParam(String id, String name) {
		System.out.println("testDeletePathParam:id="+id+";name="+name);
	}

	@Override
	public String testPutPathParam(String id, String info) {
		System.out.println("testPutPathParam:id="+id+";info="+info);
		return "testPutPathParam:id="+id+";info="+info;
	}
	
	
}
