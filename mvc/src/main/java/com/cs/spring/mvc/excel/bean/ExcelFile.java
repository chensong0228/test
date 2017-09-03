package com.cs.spring.mvc.excel.bean;

import java.util.List;

public class ExcelFile {

	private String fileName;
	
	private List<ExcelSheet> excelSheets;
	
	public ExcelFile(String fileName, List<ExcelSheet> excelSheets) {
		super();
		this.fileName = fileName;
		this.excelSheets = excelSheets;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<ExcelSheet> getExcelSheets() {
		return excelSheets;
	}

	public void setExcelSheets(List<ExcelSheet> excelSheets) {
		this.excelSheets = excelSheets;
	}
	
}
