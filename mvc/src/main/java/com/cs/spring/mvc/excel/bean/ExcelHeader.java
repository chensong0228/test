package com.cs.spring.mvc.excel.bean;

public class ExcelHeader {
	
	private String headerName;
	private String headerCode;
	private int level;
	private boolean isParentHeader;
	private int bottomChildrenNum;
	private int frontBottomChildrenNum;
	private int headerWidth;
	
	public ExcelHeader(String headerName, String headerCode, int level, boolean isParentHeader, int bottomChildrenNum, int frontBottomChildrenNum, int headerWidth) {
		super();
		this.headerName = headerName;
		this.headerCode = headerCode;
		this.level = level;
		this.isParentHeader = isParentHeader;
		this.bottomChildrenNum = bottomChildrenNum;
		this.frontBottomChildrenNum = frontBottomChildrenNum;
		this.headerWidth = headerWidth;
	}
	
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getHeaderCode() {
		return headerCode;
	}
	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isParentHeader() {
		return isParentHeader;
	}
	public void setParentHeader(boolean isParentHeader) {
		this.isParentHeader = isParentHeader;
	}
	public int getBottomChildrenNum() {
		return bottomChildrenNum;
	}
	public void setBottomChildrenNum(int bottomChildrenNum) {
		this.bottomChildrenNum = bottomChildrenNum;
	}
	public int getFrontBottomChildrenNum() {
		return frontBottomChildrenNum;
	}
	public void setFrontBottomChildrenNum(int frontBottomChildrenNum) {
		this.frontBottomChildrenNum = frontBottomChildrenNum;
	}
	public int getHeaderWidth() {
		return headerWidth;
	}
	public void setHeaderWidth(int headerWidth) {
		this.headerWidth = headerWidth;
	}
	
	
}
