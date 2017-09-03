package com.cs.spring.mvc.user.web;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.user.bean.User;
import com.cs.spring.mvc.user.bean.UserContext;
import com.cs.spring.mvc.user.service.LoginService;
import com.cs.spring.mvc.util.JsonUtil;

@Controller
@RequestMapping("user/login.spr")
public class LoginController extends BaseController{
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(params = "method=toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		return "user/login/login";
	}
	
	@RequestMapping(params = "method=login")
    public void login(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		Map<String,Object> loginInfo = loginService.login(userName,password);
		String result = MapUtils.getString(loginInfo, "result");
		if(StringUtils.equals(result, "success")){
			UserContext userContext = new UserContext((User)loginInfo.get("userInfo"),new ArrayList<String>());
			request.getSession().setAttribute("UserContext", userContext);
		}
//		int s = 1/0;
		super.outputJSON(response, JsonUtil.parseJSONText(loginInfo));
	}
	
	@RequestMapping(params = "method=logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		request.getSession().invalidate();
		super.outputText(response, request.getContextPath()+"/user/login.spr?method=toLogin");
	}
}
