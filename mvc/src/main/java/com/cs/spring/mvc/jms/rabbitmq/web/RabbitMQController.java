package com.cs.spring.mvc.jms.rabbitmq.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.jms.rabbitmq.server.RabbitMQProducer;

@Controller
@RequestMapping("rabbitmq.spr")
public class RabbitMQController extends BaseController {

	@Autowired
	private RabbitMQProducer rabbitMQProducer;

	@RequestMapping(params = "method=test2")
	public void test2(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		//使用各自的交换机发送
		map.put("data", "hello rabbitmq-1");
		rabbitMQProducer.sendDirectMessage("direct_queue", map);
		rabbitMQProducer.sendDirectMessage("direct_queue", map);
		map.put("data", "hello rabbitmq-2");
		rabbitMQProducer.sendTopicMessage("topic_queue", map);
		rabbitMQProducer.sendTopicMessage("topic_queue", map);
		map.put("data", "hello rabbitmq-3");
		rabbitMQProducer.sendFanoutMessage(map);
		rabbitMQProducer.sendFanoutMessage(map);
		//使用公共的发送方法发送
		map.put("data", "hello rabbitmq");
		rabbitMQProducer.sendMessage("direct_exchange", "direct_queue", map);
		rabbitMQProducer.sendMessage("direct_exchange", "direct_queue", map);
		rabbitMQProducer.sendMessage("direct_exchange", "direct_queue", map);
		map.put("data", "hello rabbitmq2");
		rabbitMQProducer.sendMessage("topic_exchange", "topic_queue", map);
		rabbitMQProducer.sendMessage("topic_exchange", "topic_queue", map);
		rabbitMQProducer.sendMessage("topic_exchange", "topic_queue", map);
		map.put("data", "hello rabbitmq3");
		rabbitMQProducer.sendMessage("fanout_exchange", "", map);
		rabbitMQProducer.sendMessage("fanout_exchange", "", map);
		rabbitMQProducer.sendMessage("fanout_exchange", "", map);
	}

}
