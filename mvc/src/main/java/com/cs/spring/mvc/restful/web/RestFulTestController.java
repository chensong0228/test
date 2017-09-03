package com.cs.spring.mvc.restful.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cs.spring.mvc.websocket.handler.SystemWebSocketHandler;

@Controller
@RequestMapping("restFul/demo.spr")
public class RestFulTestController extends BaseController{
	
	@Autowired
	private DemoCxfRestFulClient demoCxfRestFulClient;
	
	@Autowired
	private DemoHttpURLConnectionClient demoHttpURLConnectionClient;
	
	@RequestMapping(params = "method=testAll")
    public void testAll(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		demoCxfRestFulClient.testAll();
	}
	
	@RequestMapping(params = "method=testAll2")
    public void testAll2(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		demoHttpURLConnectionClient.testAll();
	}
	
}
