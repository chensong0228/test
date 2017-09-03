package com.cs.spring.mvc.file.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.file.core.ResFileTransferFactory;
import com.cs.spring.mvc.file.service.ResFileService;
import com.cs.spring.mvc.user.bean.User;
import com.cs.spring.mvc.user.bean.UserContext;
import com.cs.spring.mvc.user.bean.UserContextHolder;
import com.cs.spring.mvc.util.JSONUtils;

@Controller
@RequestMapping("file/file.spr")
public class FileController extends BaseController{
	
	@Autowired
	private ResFileService fileService;
	
	@RequestMapping(params = "method=toResList")
    public String toResList(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		model.addAttribute("resId", request.getParameter("resId"));
		model.addAttribute("resType", request.getParameter("resType"));
		model.addAttribute("applicationName", request.getParameter("applicationName"));
		return "file/resList";
	}
	
	@RequestMapping(params = "method=toUploadFile")
    public String toUploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		model.addAttribute("resId", request.getParameter("resId"));
		model.addAttribute("resType", request.getParameter("resType"));
		model.addAttribute("applicationName", request.getParameter("applicationName"));
		return "file/uploadFile";
	}
	
	@RequestMapping(params = "method=queryResFiles")
    public void queryResFileNames(HttpServletRequest request,HttpServletResponse response){
        try{
        	String condition = URLDecoder.decode(request.getParameter("condition"), "UTF-8");
    		Map<String, Object> paramMap = JSONUtils.parseJsonToMap(condition);
    		String applicationName = MapUtils.getString(paramMap, "applicationName");
    		String resType = MapUtils.getString(paramMap, "resType");
    		String resId = MapUtils.getString(paramMap, "resId");
    		String remotePath = "/"+applicationName+"/"+resType+"/"+resId;
    		List<Map<String,Object>> results = fileService.queryResFiles(remotePath);
            super.outputJSON(response, JSONUtils.parseToJsonArrayStr(results));
        } catch (Exception e){
            e.printStackTrace();
            super.outputText(response,e.getMessage());
        }
    }
	
	@RequestMapping(params = "method=saveResFile")
    public String saveResFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, ModelMap model){
        String resId = request.getParameter("resId");
        String resType = request.getParameter("resType");
        String applicationName = request.getParameter("applicationName");
        String remotePath = "/"+applicationName+"/"+resType+"/"+resId;
        String remoteFileName = new Date().getTime()+"."+Math.round(Math.random()*1000);
        try {  
        	String localFilePath = request.getSession().getServletContext().getResource("/").getPath()+"/upload";  
            File targetFile = new File(localFilePath, remoteFileName);
            if(!targetFile.exists()){  
                targetFile.mkdirs();
            } 
        	//将文件保存到应用服务器中
            file.transferTo(targetFile);
            //将应用服务中的文件保存到文件服务器中
            Map<String,Object> param  = new HashMap<String,Object>();
            param.put("applicationName", applicationName);
            param.put("resType", resType);
            param.put("resId", resId);
            param.put("localFilePath", localFilePath);
            param.put("remoteFileName", remoteFileName);
            param.put("fileSize", file.getSize());
            param.put("remotePath", remotePath);
            param.put("fileName", file.getOriginalFilename());
            param.put("createUserId", UserContextHolder.getUserContext().getUser().getUserId());
            fileService.saveResFile(param);
            //将文件从应用服务器中删除
            FileUtils.forceDelete(targetFile);
        } catch (Exception e) {  
            e.printStackTrace();  
            return "fail";
        }  
        model.addAttribute("resId", request.getParameter("resId"));
		model.addAttribute("resType", request.getParameter("resType"));
		model.addAttribute("applicationName", request.getParameter("applicationName"));
        return "file/uploadFile";
	}
	
	@RequestMapping(params = "method=deleteFile")
    public void deleteFile(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> param  = new HashMap<String,Object>();
        param.put("createUserId", UserContextHolder.getUserContext().getUser().getUserId());
        param.put("fileId", request.getParameter("fileId"));
        try {
			fileService.deleteResFile(param);
		} catch (Exception e) {
			e.printStackTrace();
			super.outputText(response,e.getMessage());
		}
        super.outputText(response, "success");
    }
	
	@RequestMapping(params = "method=downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String fileId = request.getParameter("fileId");
		Map<String,Object> fileInfo = fileService.queryResFile(fileId);
		File localFile = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			String localFilePath = request.getSession().getServletContext().getResource("/").getPath()+"/download";  
			fileInfo.put("localFilePath", localFilePath);
			String fileName = MapUtils.getString(fileInfo, "FILE_NAME");
			String remoteFileName = MapUtils.getString(fileInfo, "REMOTE_FILE_NAME");
			localFile = new File(localFilePath);
            if(!localFile.exists()){
                localFile.mkdir();
            }
            localFile = new File(localFilePath, remoteFileName);
            if(!localFile.exists()){
                localFile.createNewFile();
            }
            fileService.downloadFile(fileInfo);
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Content-Length", "" + localFile.length());
            response.setContentType("application/octet-stream");
            bis = new BufferedInputStream(new FileInputStream(localFile));
            byte[] buffer = new byte[bis.available()];
            bis.read(buffer);
            bis.close();
            os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            try{
                if(bis!=null){
                	bis.close();
                }
                if(localFile!=null){
                    FileUtils.forceDelete(localFile);
                }
                if(os!=null){
                    os.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
	
}
