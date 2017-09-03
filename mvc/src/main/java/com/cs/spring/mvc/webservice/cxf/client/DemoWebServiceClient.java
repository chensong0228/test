package com.cs.spring.mvc.webservice.cxf.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections4.MapUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class DemoWebServiceClient {

	private String endPoint;

	private String namespaceURI;
	
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getNamespaceURI() {
		return namespaceURI;
	}

	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	public Object getWebservice(String methodName, Map<String,String> param) {
		Object result = null;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTimeout(10000);
			call.setTargetEndpointAddress(new java.net.URL(endPoint));
			call.setOperationName(new QName(namespaceURI, methodName));
			Object[] object = null;
			if(param != null){
				Set<Entry<String, String>> paramSet = param.entrySet();
				Iterator<Entry<String, String>> iterator = paramSet.iterator();
				object = new Object[paramSet.size()];
				int num = 0;
				while(iterator.hasNext()){
					Entry<String, String> entry = iterator.next();
					call.addParameter(entry.getKey(), XMLType.XSD_STRING, ParameterMode.IN);
					object[num++] = entry.getValue();
				}
			}
			call.setReturnType(XMLType.XSD_STRING);
			result = call.invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
