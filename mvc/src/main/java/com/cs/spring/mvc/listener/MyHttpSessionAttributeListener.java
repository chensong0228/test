package com.cs.spring.mvc.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.springframework.stereotype.Component;

import com.cs.spring.mvc.user.bean.UserContext;
import com.cs.spring.mvc.user.bean.UserContextHolder;

@Component
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if ("UserContext".equals(event.getName())) {
			UserContextHolder.setUserContext((UserContext) event.getValue());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if ("UserContext".equals(event.getName())) {
			UserContextHolder.cleanUserContext();
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		if ("UserContext".equals(event.getName())) {
			UserContextHolder.setUserContext((UserContext) event.getValue());
		}
	}
}
