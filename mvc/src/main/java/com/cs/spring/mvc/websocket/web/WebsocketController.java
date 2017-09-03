package com.cs.spring.mvc.websocket.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.websocket.handler.SystemWebSocketHandler;

@Controller
@RequestMapping("websocket.spr")
public class WebsocketController extends BaseController{
	
	@RequestMapping(params = "method=toTestWbsocket")
    public String toTestWbsocket(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		return "websocket/testWbsocket";
	}
	
	@RequestMapping(params = "method=test")
    public void test(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			new SystemWebSocketHandler().sendMessageToAll(new TextMessage("websocket测试消息"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
