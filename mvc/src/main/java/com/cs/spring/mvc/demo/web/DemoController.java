package com.cs.spring.mvc.demo.web;

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
import com.cs.spring.mvc.user.bean.UserContextHolder;
import com.cs.spring.mvc.websocket.handler.SystemWebSocketHandler;

@Controller
@RequestMapping("demo/demo.spr")
public class DemoController extends BaseController{
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private ActiveMQProducer testJmsService;
	
	@Autowired(required=false)
	@Qualifier("queueDestination")  
    private Destination queueDestination;
	
	@Autowired(required=false)
	@Qualifier("queueDestination2")  
    private Destination queueDestination2;
	
	@Autowired(required=false)
	@Qualifier("topicDestination") 
    private Destination topicDestination;
	
	@Autowired
	private RabbitMQProducer demoProducer;
	
//	@Value("${mq.queue}")
    private String queueId = "test_mq";
    
	@Value("${mq.queue}")
    private String queueId2;
	
	@RequestMapping(params = "method=toDemo")
    public String toMenu(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String userName = demoService.getUserNameById("11");
		System.out.println("userName="+userName);
		return "main/mainView/main";
	}
	
	@RequestMapping(params = "method=test")
    public void test(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		System.out.println("userName=sssssss");
//		System.out.println(UserContextHolder.getUserContext().getUser().getRealName());
		try {
			new SystemWebSocketHandler().sendMessageToAll(new TextMessage("hshhshs"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=test2")
    public void test2(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		System.out.println("======"+queueId);
		try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", "hello rabbitmq-1");
            demoProducer.sendDirectMessage("direct_queue", map);
            demoProducer.sendDirectMessage("direct_queue", map);
            map.put("data", "hello rabbitmq-2");
            demoProducer.sendTopicMessage("topic_queue", map);
            demoProducer.sendTopicMessage("topic_queue", map);
            map.put("data", "hello rabbitmq-3");
            demoProducer.sendFanoutMessage(map);
            demoProducer.sendFanoutMessage(map);
            map.put("data", "hello rabbitmq");
            demoProducer.sendMessage("direct_exchange","direct_queue", map);
            demoProducer.sendMessage("direct_exchange","direct_queue", map);
            demoProducer.sendMessage("direct_exchange","direct_queue", map);
            demoProducer.sendMessage("direct_exchange","direct_queue", map);
            demoProducer.sendMessage("direct_exchange","direct_queue", map);
            demoProducer.sendMessage("direct_exchange","direct_queue", map);
            map.put("data", "hello rabbitmq2");
            demoProducer.sendMessage("topic_exchange","topic_queue", map);
            demoProducer.sendMessage("topic_exchange","topic_queue", map);
            demoProducer.sendMessage("topic_exchange","topic_queue", map);
            demoProducer.sendMessage("topic_exchange","topic_queue", map);
            demoProducer.sendMessage("topic_exchange","topic_queue", map);
            demoProducer.sendMessage("topic_exchange","topic_queue", map);
            map.put("data", "hello rabbitmq3");
            demoProducer.sendMessage("fanout_exchange","", map);
            demoProducer.sendMessage("fanout_exchange","", map);
            demoProducer.sendMessage("fanout_exchange","", map);
            demoProducer.sendMessage("fanout_exchange","", map);
            demoProducer.sendMessage("fanout_exchange","", map);
            demoProducer.sendMessage("fanout_exchange","", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@RequestMapping(params = "method=test3")
    public void test3(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		System.out.println("======"+queueId2);
	}
}
