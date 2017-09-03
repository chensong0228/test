package com.cs.spring.mvc.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:javawen@gmail.com">文建国 </a>
 * @version 1.0 24-Feb-2011
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected void outputJSON(HttpServletResponse response,String responseContent) {
		outputJSON(response,responseContent,"UTF-8");
	}
	
	protected void outputJSON(HttpServletResponse response,String responseContent, String encoding) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript;charset=" + encoding);
			writer = response.getWriter();
			if (StringUtils.isEmpty(responseContent)) {
				writer.write("");
			} else {
				writer.write(responseContent);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	public void outputXML(HttpServletResponse response, String xml) {
		this.outputXML(response, xml, "UTF-8");
	}

	public void outputXML(HttpServletResponse response, String xml, String encoding) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/xml; charset=" + encoding);
			writer = response.getWriter();
			if (StringUtils.isEmpty(xml)) {
				writer.write("");
			} else {
				writer.write(xml);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	public void outputText(HttpServletResponse response, String text) {
		outputText(response,text,"UTF-8");
	}
	
	public void outputText(HttpServletResponse response, String text, String encoding) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/plain; charset=" + encoding);
			writer = response.getWriter();
			if (StringUtils.isEmpty(text)) {
				writer.write("");
			} else {
				writer.write(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
}
