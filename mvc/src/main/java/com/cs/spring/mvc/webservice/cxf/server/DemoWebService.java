package com.cs.spring.mvc.webservice.cxf.server;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.cs.spring.mvc.webservice.cxf.server.DemoWebService")
public interface DemoWebService {

	public String sendNull();
	
	public String sendString(@WebParam(name = "name") String name);
	
	public String sendDoubleString(@WebParam(name = "id") String id, @WebParam(name = "name") String name);
	
	public String sendInteger(@WebParam(name = "age") Integer age);
	
	public Integer returnInteger(@WebParam(name = "age") Integer age);
	
	public void returnNull(@WebParam(name = "age") Integer age);

}
