package com.cs.spring.mvc.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.spring.mvc.user.bean.User;
import com.cs.spring.mvc.user.dao.LoginDao;
import com.cs.spring.mvc.user.dao.UserDao;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private UserDao userDao;

	public Map<String, Object> login(String userName, String password) {
		User user = userDao.getUserByNameAndPwd(userName, password);
		Map<String,Object> loginInfo = new HashMap<String,Object>();
		if(user == null){
			loginInfo.put("result", "fail");
			User user2 = userDao.getUserByName(userName);
			if(user2 == null){
				loginInfo.put("reason", "用户名不存在");
			}else{
				loginInfo.put("reason", "密码错误");
			}
		}else{
			loginInfo.put("result", "success");
			loginInfo.put("reason", "登录成功");
			loginInfo.put("userInfo", user);
		}
		return loginInfo;
	}

}
