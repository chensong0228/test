package com.cs.spring.mvc.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs.spring.mvc.user.bean.User;
import com.cs.spring.mvc.user.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User getUserById(String userId){
		return userDao.getUserById(userId);
	}
	
	public User getUserByNameAndPwd(String userName,String password){
		return userDao.getUserByNameAndPwd(userName, password);
	} 
	
	public User getUserByName(String userName){
		return userDao.getUserByName(userName);
	} 
	
	public boolean isUserNameExist(String userName){
		User user = this.getUserByName(userName);
		return user == null ? false : true;
	}
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public List<User> getUsers(String orgId,String loginType){
		return userDao.getUsers(orgId, loginType);
	}
	
	public boolean addUser(User user){
		return userDao.addUser(user);
	}
	
	public boolean editUser(User user){
		return userDao.editUser(user);
	}
	
	public boolean deleteUser(String userId){
		return userDao.deleteUser(userId);
	}
}
