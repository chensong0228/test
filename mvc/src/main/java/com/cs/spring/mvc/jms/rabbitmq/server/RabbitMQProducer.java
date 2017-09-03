package com.cs.spring.mvc.jms.rabbitmq.server;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
	
	@Autowired(required=false)
	@Qualifier("directTemplate") 
    private AmqpTemplate directTemplate;
	
	@Autowired(required=false)
	@Qualifier("topicTemplate") 
    private AmqpTemplate topicTemplate;
	
	@Autowired(required=false)
	@Qualifier("fanoutTemplate") 
    private AmqpTemplate fanoutTemplate;
	
	@Autowired(required=false)
	@Qualifier("rabbitMQTemplate") 
    private AmqpTemplate rabbitMQTemplate;
    
	public void sendDirectMessage(String queue_key, Object object) {
		directTemplate.convertAndSend(queue_key, object);
    }
	
    public void sendTopicMessage(String queue_key, Object object) {
    	topicTemplate.convertAndSend(queue_key, object);
    }
    
    public void sendFanoutMessage(Object object) {
    	fanoutTemplate.convertAndSend(object);
    }
    
    public void sendMessage(String exchange_key, String queue_key, Object object) {
    	rabbitMQTemplate.convertAndSend(exchange_key, queue_key, object);
    }
}
