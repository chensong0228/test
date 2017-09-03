package com.cs.spring.mvc.file.core;

public class ResFileTransferFactory {

	public static String IP;
	public static int PORT;
	public static String USERNAME;
	public static String PASSWORD;
	public static String TRANSFER_TYPE;
	
	public static void saveResFile(String localFilePath, String remoteFileName, String remotePath) throws Exception {
		switch (ResFileTransferFactory.TRANSFER_TYPE) {
		case "ftp":
			FTPUtils.uploadFile(remotePath, remoteFileName, localFilePath);
			break;
		case "sftp":
			SFTPUtils.uploadFile(remotePath, remoteFileName, localFilePath);
			break;
		default:
			break;
		}
	}
	
	public static void downloadFile(String remotePath,String remoteFileName, String localFilePath) throws Exception {
		switch (ResFileTransferFactory.TRANSFER_TYPE) {
		case "ftp":
			FTPUtils.downloadFile(remotePath, remoteFileName, localFilePath);
			break;
		case "sftp":
			SFTPUtils.downloadFile(remotePath, remoteFileName, localFilePath);
			break;
		default:
			break;
		}
	}
	
	public static void deleteFile(String remotePath,String remoteFileName) throws Exception{
		switch (ResFileTransferFactory.TRANSFER_TYPE) {
		case "ftp":
			FTPUtils.deleteFile(remotePath, remoteFileName);
			break;
		case "sftp":
			SFTPUtils.deleteFile(remotePath, remoteFileName);
			break;
		default:
			break;
		}
	}
}
