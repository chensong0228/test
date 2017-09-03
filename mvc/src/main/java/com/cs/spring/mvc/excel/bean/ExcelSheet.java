package com.cs.spring.mvc.excel.bean;

import java.util.List;

public class ExcelSheet {
	
	private String sheetName;
	
	private String sheetStyle;
	
	private List<ExcelHeader> excelHeaders;

	public ExcelSheet(String sheetName, String sheetStyle, List<ExcelHeader> excelHeaders) {
		super();
		this.sheetName = sheetName;
		this.sheetStyle = sheetStyle;
		this.excelHeaders = excelHeaders;
	}
	
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	public String getSheetStyle() {
		return sheetStyle;
	}

	public void setSheetStyle(String sheetStyle) {
		this.sheetStyle = sheetStyle;
	}

	public List<ExcelHeader> getExcelHeaders() {
		return excelHeaders;
	}

	public void setExcelHeaders(List<ExcelHeader> excelHeaders) {
		this.excelHeaders = excelHeaders;
	}
	
}
