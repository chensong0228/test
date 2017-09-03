package com.cs.spring.mvc.file.service;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.spring.mvc.file.core.ResFileTransferFactory;
import com.cs.spring.mvc.file.dao.ResFileDao;
import com.cs.spring.mvc.util.NumberUtils;

@Service
public class ResFileService{
	
	@Autowired
	private ResFileDao resFileDao;
	
	public void saveResFile(Map<String, Object> resFile) throws Exception{
		//将文件的信息保存到数据库
		double fileSize = MapUtils.getDouble(resFile, "fileSize");
		String dealedFileSize = "";
		if(fileSize <= 1024){
    		dealedFileSize = fileSize+"B";
    	}else if(fileSize <= 1024*1024){
    		dealedFileSize = NumberUtils.parseString(fileSize/1024, "#.00")+"K";
    	}else if(fileSize <= 1024*1024*1024){
    		dealedFileSize = NumberUtils.parseString(fileSize/1024/1024, "#.00")+"M";
    	}else {
    		dealedFileSize = NumberUtils.parseString(fileSize/1024/1024/1024, "#.00")+"G";
    	}
		resFile.put("fileSize", dealedFileSize);
		resFileDao.saveResFile(resFile);
		//将文件保存到文件服务器
		String localFilePath = MapUtils.getString(resFile, "localFilePath");
		String remoteFileName = MapUtils.getString(resFile, "remoteFileName");
		String remotePath = MapUtils.getString(resFile, "remotePath");
		ResFileTransferFactory.saveResFile(localFilePath,remoteFileName, remotePath);
	}
	
	public void deleteResFile(Map<String, Object> param) throws Exception{
		String fileId = MapUtils.getString(param, "fileId");
		Map<String,Object> fileInfo = queryResFile(fileId);
		//删除数据库中的记录
		resFileDao.deleteResFile(param);
		//删除文件服务器上的记录
		String remotePath = MapUtils.getString(fileInfo, "REMOTE_PATH");
		String remoteFileName = MapUtils.getString(fileInfo, "REMOTE_FILE_NAME");
		ResFileTransferFactory.deleteFile(remotePath, remoteFileName);
	}
	
	public List<Map<String, Object>> queryResFiles(String remotePath){
		return resFileDao.queryResFiles(remotePath);
	}
	
	public Map<String, Object> queryResFile(String fileId){
		return resFileDao.queryResFile(fileId);
	}

	public void downloadFile(Map<String, Object> fileInfo) throws Exception {
		String localFilePath = MapUtils.getString(fileInfo, "localFilePath");
		String remoteFileName = MapUtils.getString(fileInfo, "REMOTE_FILE_NAME");
		String remotePath = MapUtils.getString(fileInfo, "REMOTE_PATH");
		ResFileTransferFactory.downloadFile(remotePath, remoteFileName, localFilePath);
	}	
}
