package com.cs.spring.mvc.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.cs.spring.mvc.cache.memory.PropertiesCacheUtils;
import com.cs.spring.mvc.file.core.ResFileTransferFactory;
import com.cs.spring.mvc.util.EasyApplicationContextUtils;

@Component
public class SysInitializer implements ApplicationContextAware {

	protected Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext ctx){
        this.ctx = ctx;
    }

    public void init() {
        EasyApplicationContextUtils.setApplicationContext(this.ctx);
        
      //加载properties配置文件
        PropertiesCacheUtils.loadPropertiesFile("conf/properties/ftp.properties");
        //加载数据库配置文件
//        SystemInit systemInit = (SystemInit) EasyApplicationContextUtils.getBeanByName("SystemInit");
//        List<Map<String, String>> configs = SystemInit.getAllSysConfigs();
//        PropertiesCacheUtils.loadloadPropertiesConfig(configs);
        initFTPConfig();
    }
    
    public void initFTPConfig(){
    	ResFileTransferFactory.IP = PropertiesCacheUtils.getProperty("ftp.ip");
    	ResFileTransferFactory.PORT = Integer.parseInt(PropertiesCacheUtils.getProperty("ftp.port"));
    	ResFileTransferFactory.USERNAME = PropertiesCacheUtils.getProperty("ftp.username");
    	ResFileTransferFactory.PASSWORD = PropertiesCacheUtils.getProperty("ftp.password");
    	ResFileTransferFactory.TRANSFER_TYPE = PropertiesCacheUtils.getProperty("ftp.transfer.type");
    }
}

