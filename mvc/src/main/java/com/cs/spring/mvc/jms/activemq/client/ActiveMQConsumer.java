package com.cs.spring.mvc.jms.activemq.client;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

import com.cs.spring.mvc.util.SerializationUtils;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

public class ActiveMQConsumer implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try {
			String messageType = message.getClass().getSimpleName();
			if ("ActiveMQTextMessage".equals(messageType)) {
				ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
				System.out.println("接收到文本消息：" + activeMQTextMessage.getText());
			} else if ("ActiveMQMapMessage".equals(messageType)) {
				ActiveMQMapMessage activeMQMapMessage = (ActiveMQMapMessage) message;
				Map<String, Object> contentMap = activeMQMapMessage.getContentMap();
				Set<Entry<String, Object>> entrySet = contentMap.entrySet();
				Iterator<Entry<String, Object>> iterator = entrySet.iterator();
				String mapStr = "";
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					mapStr += entry.getKey()+":"+entry.getValue()+"。";
				}
				System.out.println("接收到MAP消息：" + mapStr);
			} else if ("ActiveMQObjectMessage".equals(messageType)) {
				ActiveMQObjectMessage activeMQObjectMessage = (ActiveMQObjectMessage) message;
				Serializable serializable = activeMQObjectMessage.getObject();
				Object object = SerializationUtils.deserialize((byte[]) serializable);
				//根据对象类型进行判断，然后处理
				List<Map<String,Object>> list = (List<Map<String,Object>>)object;
				Map<String,Object> contentMap = list.get(0);
				Set<Entry<String, Object>> entrySet = contentMap.entrySet();
				Iterator<Entry<String, Object>> iterator = entrySet.iterator();
				String mapStr = "";
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					mapStr += entry.getKey()+":"+entry.getValue()+"。";
				}
				System.out.println("接收到Object消息：" + mapStr);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


}
