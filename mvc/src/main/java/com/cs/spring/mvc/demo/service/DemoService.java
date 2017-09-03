package com.cs.spring.mvc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cs.spring.mvc.demo.dao.DemoDao;

@Service
public class DemoService {

	@Autowired
	private DemoDao demoDao;

	public String getUserNameById(String userId) {
//		demoDao.updateUserName("1","新名字1");
//		int s = 1/0;
		return demoDao.getUserNameById(userId);
	}
}
