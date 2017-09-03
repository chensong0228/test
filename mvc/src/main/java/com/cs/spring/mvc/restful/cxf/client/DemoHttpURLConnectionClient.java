package com.cs.spring.mvc.restful.cxf.client;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class DemoHttpURLConnectionClient {
	
	private String restFulURL = "http://127.0.0.1:8080/mvc/services/restFul/";
	
	public void testAll() {
		System.out.println("测试--testPutQueryParam");
		testPutQueryParam();
		System.out.println("测试--testPostQueryParam");
		testPostQueryParam();
		System.out.println("测试--testDeleteQueryParam");
		testDeleteQueryParam();
		System.out.println("测试--testPutQueryParam");
		testPutQueryParam();
		System.out.println("测试--testDeletePathParam");
		testDeletePathParam();
		System.out.println("测试--testPostPathyParam");
		testPostPathyParam();
		System.out.println("测试--testDeletePathParam");
		testDeletePathParam();
		System.out.println("测试--testPutPathParam");
		testPutPathParam();
	}

	public void testGetQueryParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"get/queryParam?id=123&name=www");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("GET");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(false);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public void testPostQueryParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"post/queryParam?id=123&name=www");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("POST");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        OutputStream stream = conn.getOutputStream();  
	        stream.write("post信息".getBytes());  
	        stream.flush();  
	        stream.close();  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public void testDeleteQueryParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"delete/queryParam?id=123&name=www");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("DELETE");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public void testPutQueryParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"put/queryParam?id=123");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("PUT");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        OutputStream stream = conn.getOutputStream();  
	        stream.write("put信息".getBytes());  
	        stream.flush();  
	        stream.close();  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	
	
	
	public void testGetPathParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"get/pathParam/11/22");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();
	        conn.setRequestMethod("GET");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(false);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public void testPostPathyParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"post/pathParam/123/www");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("POST");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        OutputStream stream = conn.getOutputStream();  
	        stream.write("post信息".getBytes());  
	        stream.flush();  
	        stream.close();  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public void testDeletePathParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"delete/pathParam/123/www");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("DELETE");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public void testPutPathParam(){
	    BufferedReader reader = null;  
	    StringBuffer buffer = new StringBuffer();  
	    try {  
	        URL sendUrl = new URL(restFulURL+"put/pathParam/123");  
	        HttpURLConnection conn = (HttpURLConnection) sendUrl.openConnection();  
	        conn.setRequestMethod("PUT");// 设定请求方式  
	        conn.setConnectTimeout(5000); // 设置连接超时为5秒  
	        conn.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true  
	        conn.setDoInput(true);// Read from the connection. Default is true.  
	        conn.setAllowUserInteraction(true);  
	        conn.setUseCaches(false); // Post 请求不能使用缓存  
	        conn.setRequestProperty("Content-Language", "GBK");  
	        conn.setRequestProperty("Content-Type", "text/xml");// 设置header信息,这个必不可少  
	        OutputStream stream = conn.getOutputStream();  
	        stream.write("put信息".getBytes());  
	        stream.flush();  
	        stream.close();  
	        InputStream inputStream = conn.getInputStream();  
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	        String strMessage = "";
	        while ((strMessage = reader.readLine()) != null) {  
	            buffer.append(strMessage);  
	        }  
	        String returnXml = buffer.toString();  
	        System.out.println("HttpURLConnection 的客户端接收返回值:" + returnXml);  
	        reader.close();  
	        conn.disconnect();  
	    } catch (MalformedURLException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
}
