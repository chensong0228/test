package com.cs.spring.mvc.webservice.cxf.server.impl;

import com.cs.spring.mvc.webservice.cxf.server.DemoWebService;

public class DemoWebServiceImpl implements DemoWebService{

	@Override
	public String sendNull() {
		System.out.println("DemoWebServiceImpl:method=sendNull");
		return "sendNull";
	}

	@Override
	public String sendString(String name) {
		System.out.println("DemoWebServiceImpl:method=sendString;name="+name);
		return "sendString:"+name;
	}

	@Override
	public String sendDoubleString(String id, String name) {
		System.out.println("DemoWebServiceImpl:method=sendDoubleString;id="+id+";name="+name);
		return "sendDoubleString:"+id+";"+name;
	}

	@Override
	public String sendInteger(Integer age) {
		System.out.println("DemoWebServiceImpl:method=sendInteger;age="+age);
		return "sendInteger:"+age;
	}

	@Override
	public Integer returnInteger(Integer age) {
		System.out.println("DemoWebServiceImpl:method=returnInteger;age="+age);
		return age;
	}

	@Override
	public void returnNull(Integer age) {
		System.out.println("DemoWebServiceImpl:method=returnNull;age="+age);
	}


}
