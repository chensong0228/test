package com.cs.spring.mvc.jms.rabbitmq.client;

import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQConsumer implements MessageListener {
	
    public void onMessage(Message message) {
        System.out.println("Rabbitmq消息消费者 = " + message.toString()+";时间："+new Date().toLocaleString());
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
