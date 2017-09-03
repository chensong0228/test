package com.cs.spring.mvc.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.user.service.UserService;

@Controller
@RequestMapping("user/user.spr")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
}
