package com.cs.spring.mvc.websocket.interceptor;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.cs.spring.mvc.user.bean.UserContextHolder;

public class SystemHandshakeInterceptor implements HandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler handler,Map<String, Object> attr) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
        	String userId = UserContextHolder.getUserContext().getUser().getUserId();
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            session.setAttribute("userId", userId);
        }
        return true;
    }
    
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,WebSocketHandler handler, Exception e) {
    	String userName = UserContextHolder.getUserContext().getUser().getUserName();
    	System.out.println("用户"+userName+"握手成功");
    }
}
