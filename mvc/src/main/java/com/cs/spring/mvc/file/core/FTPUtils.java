package com.cs.spring.mvc.file.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPUtils {

    public static void uploadFile(String remotePath, String remoteFileName, String localFilePath) throws Exception {
        InputStream in = null;
        FTPClient ftpClient = new FTPClient();
        try {
        	File localFile = new File(localFilePath,remoteFileName);
            in = new FileInputStream(localFile);
            ftpClient.connect(ResFileTransferFactory.IP, ResFileTransferFactory.PORT);
            if (ftpClient.login(ResFileTransferFactory.USERNAME, ResFileTransferFactory.PASSWORD)) {
            	ftpClient.enterLocalPassiveMode();
                // 改变工作目录
                changWorkingDirectory(remotePath, ftpClient, true);
                // 设置为二进制传输模式
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.setControlEncoding("GBK");
                boolean flag = ftpClient.storeFile(remoteFileName,in);
                if (!flag) {
                    throw new Exception("上传文件失败");
                }
            } else {
                throw new Exception("ftp连接不上，请检查ftp服务器状态或连接ftp配置");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
        	e.printStackTrace();
            throw e;
        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    throw e;
                }
            }
            if (ftpClient != null) {
                try {
                    ftpClient.disconnect();
                    ftpClient = null;
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 改变ftp的工作目录
     * @param remotePath
     * @param ftpClient
     * @param isMakeDir
     * @throws IOException
     */
    private static void changWorkingDirectory(String remotePath, FTPClient ftpClient, boolean isMakeDir) throws IOException {
        String rps[] = remotePath.split("/");
        for (int i = 0; i < rps.length; i++) {
            if (!"".equals(rps[i]) && !ftpClient.changeWorkingDirectory(rps[i])) {
                if (isMakeDir) {
                    ftpClient.makeDirectory(rps[i]);
                    ftpClient.changeWorkingDirectory(rps[i]);
                } else {
                    throw new RuntimeException("找不到FTP服务器目录:" + rps[i]);
                }
            }
        }
    }

    /**
     * 从ftp下载文件到本地
     * @param ip             IP
     * @param port           端口
     * @param userName       用户名
     * @param password       密码
     * @param remotePath     远程文件目录
     * @param remoteFileName 远程文件
     * @param localFilePath  本地文件目录
     * @throws Exception
     */
    public static void downloadFile(String remotePath,String remoteFileName, String localFilePath) throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
        	ftpClient.connect(ResFileTransferFactory.IP, ResFileTransferFactory.PORT);
            // 登陆
            if (ftpClient.login(ResFileTransferFactory.USERNAME, ResFileTransferFactory.PASSWORD)) {
            	ftpClient.enterLocalPassiveMode();
                // 改变工作目录
                changWorkingDirectory(remotePath, ftpClient, true);
                ftpClient.setControlEncoding("GBK");
                // 获取FTP登陆目录下的所有文件
                FTPFile[] files = ftpClient.listFiles();
                for (FTPFile file : files) {
                    if (remoteFileName.equalsIgnoreCase(file.getName())) {
                        BufferedOutputStream out = null;
                        try {
                            // IO流下载文件到本地
                            out = new BufferedOutputStream(new FileOutputStream(new File(localFilePath, remoteFileName)));
                            ftpClient.retrieveFile(remoteFileName, out);
                        } finally {
                            try {
                                if (out != null) {
                                    out.close();
                                }
                            } catch (Exception e) {
                                throw e;
                            }
                        }
                    }
                }
            } else {
                throw new Exception("ftp连接不上，请检查ftp服务器状态或连接ftp配置");
            }
        } finally {
            if (ftpClient != null) {
                try {
                    ftpClient.disconnect();
                    ftpClient = null;
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 从ftp上删除文件
     *
     * @param ip             IP
     * @param port           端口
     * @param userName       用户名
     * @param password       密码
     * @param remotePath     远程文件目录
     * @param remoteFileName 远程文件
     * @throws Exception
     */
    public static void deleteFile(String remotePath,String remoteFileName) throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
        	ftpClient.connect(ResFileTransferFactory.IP, ResFileTransferFactory.PORT);
            // 登陆
            if (ftpClient.login(ResFileTransferFactory.USERNAME, ResFileTransferFactory.PASSWORD)) {
                ftpClient.enterLocalPassiveMode();
                // 改变工作目录
                changWorkingDirectory(remotePath, ftpClient, true);
                ftpClient.setControlEncoding("GBK");
                // 删除ftp上文件
                ftpClient.deleteFile(remoteFileName);
            } else {
                throw new Exception("ftp连接不上，请检查ftp服务器状态或连接ftp配置");
            }
        } finally {
            if (ftpClient != null) {
                try {
                    ftpClient.disconnect();
                    ftpClient = null;
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
}
