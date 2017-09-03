package com.cs.spring.mvc.jms.activemq.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.jms.activemq.server.ActiveMQProducer;

@Controller
@RequestMapping("activemq.spr")
public class ActiveMQController extends BaseController{
	
	@Autowired
	private ActiveMQProducer activeMQProducer;
	
	@Autowired(required=false)
	@Qualifier("queueDestination")  
    private Destination queueDestination;
	
	@Autowired(required=false)
	@Qualifier("queueDestination2")  
    private Destination queueDestination2;
	
	@Autowired(required=false)
	@Qualifier("topicDestination") 
    private Destination topicDestination;
	
	@RequestMapping(params = "method=test")
    public void test(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		sendMessage(queueDestination);
		sendMessage(queueDestination2);
		sendMessage(topicDestination);
	}
	
	private void sendMessage(Destination destination){
		activeMQProducer.sendTextMessage(destination, destination.toString()+"-text1");
		activeMQProducer.sendTextMessage(destination, destination.toString()+"-text2");
		activeMQProducer.sendTextMessage(destination, destination.toString()+"-text3");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("queue", destination.toString());
		map.put("map", "map-1");
		activeMQProducer.sendMapMessage(destination, map);
		map.put("map", "map-2");
		activeMQProducer.sendMapMessage(destination, map);
		map.put("map", "map-3");
		activeMQProducer.sendMapMessage(destination, map);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		activeMQProducer.sendObjectMessage(destination, list);
		activeMQProducer.sendObjectMessage(destination, list);
		activeMQProducer.sendObjectMessage(destination, list);
	}
}
