package com.cs.spring.mvc.file.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;

public class ResFile implements Serializable {

	private static final long serialVersionUID = 5034972088232887580L;
	
	private String fileId;

	private String resId;
	
	private String resType;
	
	private String applicationName;

	private String fileName;
	
	private String fileSize;

	private String remotePath;

	private String remoteFileName;
	
	private long createUserId;
	
	private Date createTime;
	
	private String deleteState;
	
	private long deleteUserId;
	
	private Date deleteTime;
	
	public ResFile(){
		
	}
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public String getRemoteFileName() {
		return remoteFileName;
	}

	public void setRemoteFileName(String remoteFileName) {
		this.remoteFileName = remoteFileName;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(String deleteState) {
		this.deleteState = deleteState;
	}

	public long getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(long deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	
}
