package com.cs.spring.mvc.file.dao;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResFileDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void saveResFile(Map<String, Object> resFile) {
		String sql = "insert into res_file(res_id, res_type, application_name, remote_path, remote_file_name, file_name, file_size, create_time, create_user_id) "
				+ "values (?,?,?,?,?,?,?,sysdate(),?)";
		Object[] params = new Object[8];
		params[0] = MapUtils.getString(resFile, "resId", "");
		params[1] = MapUtils.getString(resFile, "resType", "");
		params[2] = MapUtils.getString(resFile, "applicationName", "");
		params[3] = MapUtils.getString(resFile, "remotePath", "");
		params[4] = MapUtils.getString(resFile, "remoteFileName", "");
		params[5] = MapUtils.getString(resFile, "fileName", "");
		params[6] = MapUtils.getString(resFile, "fileSize", "");
		params[7] = MapUtils.getString(resFile, "createUserId", "");
		jdbcTemplate.update(sql, params);
	}

	public void deleteResFile(Map<String, Object> param) {
		String sql = "update res_file set delete_state = '1',delete_time = sysdate(),delete_user_id = ? where file_id = ?";
		Object[] params = new Object[2];
		params[0] = MapUtils.getString(param, "createUserId", "");
		params[1] = MapUtils.getString(param, "fileId", "");
		jdbcTemplate.update(sql, params);
	}

	public List<Map<String, Object>> queryResFiles(String remotePath) {
		String sql = "select file_id,remote_path,remote_file_name,file_name,file_size,date_format(create_time,'%Y-%c-%e %k:%i:%s') as create_time,create_user_id from res_file where delete_state = '0' and remote_path = ?";
		return jdbcTemplate.queryForList(sql, remotePath);
	}
	
	public Map<String, Object> queryResFile(String fileId){
		String sql = "select file_id,remote_path,remote_file_name,file_name,file_size,date_format(create_time,'%Y-%c-%e %k:%i:%s') as create_time,create_user_id from res_file where delete_state = '0' and file_id = ?";
		return jdbcTemplate.queryForMap(sql, fileId);
	}

}
