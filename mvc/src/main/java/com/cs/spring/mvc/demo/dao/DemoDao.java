package com.cs.spring.mvc.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String getUserNameById(String userId) {
		String sql = "select s.name from staff s where s.staff_id= ?";
//		return jdbcTemplate.queryForObject(sql, new Object[]{userId}, String.class);
		return "11";
	}

	public void updateUserName(String string, String string2) {
		String sql = "update USER u set u.real_name = ? where u.user_id = ?";
		jdbcTemplate.update(sql, new Object[]{string2,string});
	}

}
