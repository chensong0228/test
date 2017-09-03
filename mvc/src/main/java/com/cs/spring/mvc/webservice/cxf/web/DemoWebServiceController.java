package com.cs.spring.mvc.webservice.cxf.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthScrollBarUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.demo.service.DemoService;
import com.cs.spring.mvc.jms.activemq.server.ActiveMQProducer;
import com.cs.spring.mvc.jms.rabbitmq.server.RabbitMQProducer;
import com.cs.spring.mvc.restful.cxf.client.DemoCxfRestFulClient;
import com.cs.spring.mvc.restful.cxf.client.DemoHttpURLConnectionClient;
import com.cs.spring.mvc.user.bean.UserContextHolder;
import com.cs.spring.mvc.webservice.cxf.client.DemoWebServiceClient;
import com.cs.spring.mvc.websocket.handler.SystemWebSocketHandler;

@Controller
@RequestMapping("webservice/demo.spr")
public class DemoWebServiceController extends BaseController{
	
	@Autowired(required=false)
	private DemoWebServiceClient demoWebServiceClient;
	
	
	@RequestMapping(params = "method=test")
    public void test(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		System.out.println("--------sendNull--------");
		sendNull();
		System.out.println("--------sendString--------");
		sendString();
		System.out.println("--------sendDoubleString--------");
		sendDoubleString();
		System.out.println("--------sendInteger--------");
		sendInteger();
		System.out.println("--------returnInteger--------");
		returnInteger();
		System.out.println("--------returnNull--------");
		returnNull();
	}
	
	private void sendNull(){
		demoWebServiceClient.getWebservice("sendNull",null);
	}
	
	private void sendString(){
		Map<String,String> param = new HashMap<String,String>();
		param.put("name", "wwwwwww");
		demoWebServiceClient.getWebservice("sendString",param);
	}
	
	private void sendDoubleString(){
		Map<String,String> param = new HashMap<String,String>();
		param.put("id", "123");
		param.put("name", "wwwwwww");
		demoWebServiceClient.getWebservice("sendDoubleString",param);
	}
	
	private void sendInteger(){
		Map<String,String> param = new HashMap<String,String>();
		param.put("age", "123");
		demoWebServiceClient.getWebservice("sendInteger",param);
	}
	
	private void returnInteger(){
		Map<String,String> param = new HashMap<String,String>();
		param.put("age", "123");
		demoWebServiceClient.getWebservice("returnInteger",param);
	}
	
	private void returnNull(){
		Map<String,String> param = new HashMap<String,String>();
		param.put("age", "123");
		demoWebServiceClient.getWebservice("returnNull",param);
	}
}
