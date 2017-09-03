package com.cs.spring.mvc.excel.bean;

import com.cs.spring.mvc.excel.annotation.ExcelComplexTitle;
import com.cs.spring.mvc.excel.annotation.ExcelFile;

@ExcelFile(name="人员列表")
public class ImportDemoBean {
	
//	@ExcelComplexTitle(name="用户信息",startColumn=0,columnLength=5,startRow=0,rowLength=1)
	private String userInfo;
//	@ExcelComplexTitle(name="权限信息",startColumn=5,columnLength=1,startRow=0,rowLength=2)
	private String roleInfo;
//	@ExcelComplexTitle(name="工作信息",startColumn=0,columnLength=5,startRow=0,rowLength=1)
	private String workInfo;

	private String id;
	private String name;
	private String age;
	private String work;
	private String info;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
