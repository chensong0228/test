package com.cs.spring.mvc.excel.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExportExcelDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getData() {
//		String sql = "select * from file";
//		String sql = "select s.name \"name\",s.staff_id \"id\",s.password \"password\",s.login_name \"loginName\" from staff s where rownum<200";
		String sql = "SELECT u.real_name \"name\",u.user_id \"id\",u. PASSWORD \"password\",u.user_name \"loginName\" FROM USER u";
		return jdbcTemplate.queryForList(sql);
	}

	public List getData2() {
		String sql = "SELECT u.real_name \"name\",u.user_id \"id\",u. PASSWORD \"password\",u.user_name \"loginName\" FROM USER u";
		return jdbcTemplate.queryForList(sql);
	}
}
