package com.cs.spring.mvc.websocket.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {
	
	private static Map<Long,WebSocketSession> userInfos = new HashMap<Long,WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//建立连接后，将用户信息保存在静态区
		Long userId = (Long) session.getAttributes().get("userId");
		if (userInfos.get(userId) == null) {
			userInfos.put(userId, session);
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("收到消息"+message.getPayload().toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//传输发生异常后，将静态区中的用户信息删除
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<Long, WebSocketSession>> userInfo = userInfos.entrySet().iterator();
		while (userInfo.hasNext()) {
			Entry<Long, WebSocketSession> entry = userInfo.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userInfos.remove(entry.getKey());
				break;
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		//连接关闭后，将静态区中的用户信息删除
		Iterator<Entry<Long, WebSocketSession>> it = userInfos.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Long, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userInfos.remove(entry.getKey());
				break;
			}
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	public void sendMessageToUser(Long userId, TextMessage message) throws IOException {
		WebSocketSession session = userInfos.get(userId);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}
	
	public void sendMessageToAll(TextMessage message) throws IOException {
		Iterator<Entry<Long, WebSocketSession>> userInfo = userInfos.entrySet().iterator();
		while (userInfo.hasNext()) {
			final Entry<Long, WebSocketSession> entry = userInfo.next();
			if (entry.getValue().isOpen()) {
				new Thread(new Runnable() {
					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}
    

}
