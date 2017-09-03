package com.cs.spring.mvc.user.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cs.spring.mvc.user.bean.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String mapperNameSpace = this.getClass().getName();
	
	public User getUserById(String userId){
		List<Map<String, Object>> userInfos = sqlSessionTemplate.selectList(mapperNameSpace+".getUserById", userId);
		return userInfos.size() == 0 ? null : new User(userInfos.get(0));
	}
	
	public User getUserByNameAndPwd(String userName,String password){
		Map<String,String> userParam = new HashMap<String,String>();
		userParam.put("userName", userName);
		userParam.put("password", password);
		List<Map<String, Object>> userInfos = sqlSessionTemplate.selectList(mapperNameSpace+".getUserByNameAndPwd", userParam);
		return userInfos.size() == 0 ? null : new User(userInfos.get(0));
	} 
	
	public User getUserByName(String userName){
		List<Map<String, Object>> userInfos = sqlSessionTemplate.selectList(mapperNameSpace+".getUserByName", userName);
		return userInfos.size() == 0 ? null : new User(userInfos.get(0));
	} 
	
	public List<User> getAllUsers(){
		List<Map<String, Object>> userInfos = sqlSessionTemplate.selectList(mapperNameSpace+".getAllUsers");
		List<User> users = new ArrayList<User>();
		for (Map<String, Object> userInfo : userInfos) {
			users.add(new User(userInfo));
		}
		return users;
	}
	
	public List<User> getUsers(String orgId,String loginType){
		List<Map<String, Object>> userInfos = sqlSessionTemplate.selectList(mapperNameSpace+".getUsers",new Object[]{orgId,loginType});
		List<User> users = new ArrayList<User>();
		for (Map<String, Object> userInfo : userInfos) {
			users.add(new User(userInfo));
		}
		return users;
	}
	
	public boolean addUser(User user){
		Map<String,String> userInfo = new HashMap<String,String>();
		userInfo.put("userName", user.getUserName());
		userInfo.put("realName", user.getRealName());
		userInfo.put("password", user.getPassword());
		userInfo.put("title", user.getTitle());
		userInfo.put("orgId", user.getOrgId());
		userInfo.put("orgName", user.getOrgName());
		int num = sqlSessionTemplate.insert(mapperNameSpace+".addUser", userInfo);
		return num == 1 ? true : false;
	}
	
	public boolean editUser(User user){
		Map<String,String> userInfo = new HashMap<String,String>();
		userInfo.put("userId", user.getUserId());
		userInfo.put("userName", user.getUserName());
		userInfo.put("realName", user.getRealName());
		userInfo.put("password", user.getPassword());
		userInfo.put("title", user.getTitle());
		userInfo.put("orgId", user.getOrgId());
		userInfo.put("orgName", user.getOrgName());
		int num = sqlSessionTemplate.update(mapperNameSpace+".editUser", userInfo);
		return num == 1 ? true : false;
	}
	
	public boolean deleteUser(String userId){
		int num = sqlSessionTemplate.delete(mapperNameSpace+".deleteUser", userId);
		return num == 1 ? true : false;
	}

}
