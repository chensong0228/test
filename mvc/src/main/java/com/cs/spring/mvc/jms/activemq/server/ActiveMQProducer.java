package com.cs.spring.mvc.jms.activemq.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import com.cs.spring.mvc.util.SerializationUtils;

@Component
public class ActiveMQProducer {

	@Autowired(required = false)
	@Qualifier("activeMQTemplate")
	private JmsTemplate activeMQTemplate;

	public void sendTextMessage(Destination destination, String message) {
		activeMQTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	public void sendMapMessage(Destination destination, Map<String, Object> message) {
		activeMQTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				Set<Entry<String, Object>> entrySet = message.entrySet();
				Iterator<Entry<String, Object>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					mapMessage.setObject(entry.getKey(), entry.getValue());
				}
				return mapMessage;
			}
		});
	}

	public void sendObjectMessage(Destination destination, Object message) {
		activeMQTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage();
				objectMessage.setObject(SerializationUtils.serialize(message));
				return objectMessage;
			}
		});
	}

}
