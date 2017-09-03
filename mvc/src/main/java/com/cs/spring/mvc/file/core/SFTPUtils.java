package com.cs.spring.mvc.file.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SFTPUtils {

    private static ChannelSftp getSftpConnect() throws JSchException {  
        ChannelSftp sftp = null;  
        JSch  jsch = new JSch();  
        jsch.getSession(ResFileTransferFactory.USERNAME, ResFileTransferFactory.IP, ResFileTransferFactory.PORT);  
        Session sshSession = jsch.getSession(ResFileTransferFactory.USERNAME, ResFileTransferFactory.IP, ResFileTransferFactory.PORT);  
        sshSession.setPassword(ResFileTransferFactory.PASSWORD);  
        Properties sshConfig = new Properties();  
        sshConfig.put("StrictHostKeyChecking", "no");  
        sshSession.setConfig(sshConfig);  
        sshSession.connect();  
        Channel channel = sshSession.openChannel("sftp");  
        channel.connect();  
        sftp = (ChannelSftp) channel;
        return sftp;  
    }
  
    public static void downloadFile(String remotePath,String remoteFileName, String localFilePath) throws Exception {
    	ChannelSftp sftp = getSftpConnect();
    	FileOutputStream os = null;  
        File file = new File(localFilePath,remoteFileName);  
        try {  
            if (!file.exists()) {
                File parentFile = file.getParentFile();  
                if (!parentFile.exists()) {  
                    parentFile.mkdirs();  
                }  
                file.createNewFile();  
            }  
            os = new FileOutputStream(file);  
            sftp.get(remotePath+"//"+remoteFileName, os);
        } catch (Exception e) {  
        	sftp.exit();  
            throw e;  
        } finally {  
    		if(os != null){
    			try {
    				os.close();
    				os = null;
    			} catch (IOException e) {
                    throw e;
                }
    		}
        	if (sftp != null) {
            	sftp.disconnect();
            	sftp = null;
            }
        }
    }
 
  
    public static void deleteFile(String remotePath,String remoteFileName) throws Exception {
    	ChannelSftp sftp = getSftpConnect();
    	try {
    		sftp.rm(remotePath+"//"+remoteFileName); 
    	} catch (Exception e) {  
    		sftp.exit();  
            throw e;  
        } finally {
        	if (sftp != null) {
            	sftp.disconnect();
            	sftp = null;
            }
		}
    }   
    
	public static void changWorkingDirectory(String createpath, ChannelSftp sftp) throws Exception {
		try {
			if (isDirExist(createpath,sftp)) {
				sftp.cd(createpath);
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString(),sftp)) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}
			}
			sftp.cd(createpath);
		} catch (SftpException e) {
			throw new Exception("创建路径错误：" + createpath);
		}
	}

	/**
	 * 判断目录是否存在
	 */
	public static boolean isDirExist(String directory, ChannelSftp sftp) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}

	public static void uploadFile(String remotePath, String remoteFileName, String localFilePath) throws Exception{
    	ChannelSftp sftp = getSftpConnect();
    	InputStream in = null;
    	try {
    		changWorkingDirectory(remotePath, sftp);
    		File localFile = new File(localFilePath,remoteFileName);
            in = new FileInputStream(localFile);
    		sftp.put(in, remoteFileName);
    	} catch (Exception e) {  
    		e.printStackTrace();
    		sftp.exit();  
            throw e;  
        } finally {
        	if(in != null){
    			try {
    				in.close();
    				in = null;
    			} catch (IOException e) {
                    throw e;
                }
    		}
        	if (sftp != null) {
            	sftp.disconnect();
            	sftp = null;
            }
		}
    }  
    

}
