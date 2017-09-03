package com.cs.spring.mvc.excel.core.poi;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import com.cs.spring.mvc.excel.bean.ExcelFile;
import com.cs.spring.mvc.excel.bean.ExcelHeader;
import com.cs.spring.mvc.excel.bean.ExcelSheet;

public class ExportExcelUtil {
	
	/**
	 * 导出EXCEL表格
	 * @param response
	 * @param excelFile
	 * @param datas
	 * @throws Exception
	 */
	public static void getHSSFWorkbook(HttpServletResponse response,ExcelFile excelFile, Map<String,List<Map<String,Object>>> datas) throws Exception{
		//创建EXCEL工作簿
		HSSFWorkbook workbook = createHSSFWorkbook(excelFile,datas);
		//进行导出的response配置
		String fileName = new String(excelFile.getFileName() .getBytes("UTF-8"),"ISO-8859-1");
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;Filename=" +fileName + ".xls");
		//配置OutputStream
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		workbook.close();
	    out.flush();
	    out.close();
	}
	
	/**
	 * 创建一个EXCEL工作簿
	 * @param excelFile
	 * @param datas
	 * @return
	 */
	private static HSSFWorkbook createHSSFWorkbook(ExcelFile excelFile, Map<String, List<Map<String, Object>>> datas) {
		//创建EXCEL工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建Sheet页
		for (ExcelSheet excelSheet : excelFile.getExcelSheets()) {
			createExcelSheet(workbook,excelSheet,datas.get(excelSheet.getSheetName()));
		}
		return workbook;
	}
	
	/**
	 * 创建一个EXCEL的Sheet页
	 * @param workbook
	 * @param excelSheet
	 * @param datas
	 */
	private static void createExcelSheet(HSSFWorkbook workbook, ExcelSheet excelSheet, List<Map<String, Object>> datas) {
		//创建一个Sheet页
		HSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
		//获取Sheet页表头深度
//		int deep = getSheetHeaderDeep(excelSheet);
		//冻结表头
//		sheet.createFreezePane(0,deep,0,1);
		//获取sheet页样式
		String sheetStyle = excelSheet.getSheetStyle();
		Map<String, HSSFCellStyle> cellStyle = getCellStyle(workbook,sheetStyle);
		HSSFCellStyle headerCellStyle = cellStyle.get("headerCellStyle");
		HSSFCellStyle dataCellStyle = cellStyle.get("dataCellStyle");
		//创建sheet页表头
		createExcelSheetHeader(sheet,excelSheet,headerCellStyle);
		//创建sheet页数据
		createExcelSheetData(sheet,excelSheet,dataCellStyle,datas);
	}

	/**
	 * 创建一个Sheet页的数据
	 * @param sheet
	 * @param excelSheet
	 * @param dataCellStyle
	 * @param datas
	 */
	private static void createExcelSheetData(HSSFSheet sheet, ExcelSheet excelSheet, HSSFCellStyle dataCellStyle, List<Map<String, Object>> datas) {
		//获取Sheet页表头深度
		int deep = getSheetHeaderDeep(excelSheet);
		//遍历数据信息表
		for (int i = 0; i < datas.size(); i++) {
			Map<String, Object> data = datas.get(i);
			HSSFRow dataRow = sheet.createRow(deep+1+i);
			for (ExcelHeader excelHeader : excelSheet.getExcelHeaders()) {
				//如果不是叶子节点就跳过，所有的数据都是针对叶子节点的
				if(excelHeader.isParentHeader()){
					continue;
				}
				String headerCode = excelHeader.getHeaderCode();
				int frontBottomChildrenNum = excelHeader.getFrontBottomChildrenNum();
				String dataCellValue = MapUtils.getString(data, headerCode);
				HSSFCell dataCell = dataRow.createCell(frontBottomChildrenNum);
				dataCell.setCellValue(dataCellValue);
				dataCell.setCellStyle(dataCellStyle);
			}
		}
	}

	/**
	 * 创建一个Sheet的表头
	 * @param sheet
	 * @param excelSheet
	 * @param headerCellStyle
	 */
	private static void createExcelSheetHeader(HSSFSheet sheet, ExcelSheet excelSheet, HSSFCellStyle headerCellStyle) {
		//获取Sheet页表头深度
		int deep = getSheetHeaderDeep(excelSheet);
		//根据表头深度创建表头行
		List<HSSFRow> headerRows = new ArrayList<HSSFRow>();
		for (int i = 0; i <= deep; i++) {
			HSSFRow headerRow = sheet.createRow(i);
			headerRows.add(headerRow);
		}
		//在表头行创建表头单元格
		for (ExcelHeader excelHeader : excelSheet.getExcelHeaders()) {
			String headerName = excelHeader.getHeaderName();
			int level = excelHeader.getLevel();
			int frontBottomChildrenNum = excelHeader.getFrontBottomChildrenNum();
			int bottomChildrenNum = excelHeader.getBottomChildrenNum();
			HSSFCell headerCell = headerRows.get(level).createCell(frontBottomChildrenNum);
			headerCell.setCellValue(headerName);
			headerCell.setCellStyle(headerCellStyle);
			if(excelHeader.isParentHeader() && bottomChildrenNum > 1){	
				//非叶子节点 向右合并单元格 
		        sheet.addMergedRegion(new CellRangeAddress(level, level,frontBottomChildrenNum, frontBottomChildrenNum+bottomChildrenNum-1));
		    }
			if(!excelHeader.isParentHeader() && level < deep){	
				//叶子节点	向下合并单元格
				sheet.addMergedRegion(new CellRangeAddress(level, deep,frontBottomChildrenNum, frontBottomChildrenNum));
		    }  
		}
	}	
	
	/**
	 * 获取表头的深度
	 * @param excelSheet
	 * @return
	 */
	private static int getSheetHeaderDeep(ExcelSheet excelSheet){  
		int deep = 0;
		for (ExcelHeader excelHeader : excelSheet.getExcelHeaders()) {
			int level = excelHeader.getLevel();
			deep = deep < level ? level : deep;
		}
		return deep; 
    }
	
	/**
	 * 根据样式style获取sheet页的样式
	 * @param workbook
	 * @param style
	 * @return
	 */
	private static Map<String, HSSFCellStyle> getCellStyle(HSSFWorkbook workbook, String style){
		HSSFCellStyle headerCellStyle = null;
		HSSFCellStyle dataCellStyle = null;
		if("demo".equals(style)){
			headerCellStyle = getCellStyle(workbook,"center",HSSFColorPredefined.WHITE,true,HSSFColorPredefined.RED);
			HSSFFont headerCellFont = getHSSFFont(workbook,"宋体",Short.valueOf("16"),true);
			headerCellStyle.setFont(headerCellFont);
			
			dataCellStyle = getCellStyle(workbook,"center",HSSFColorPredefined.WHITE,true,HSSFColorPredefined.RED);
			HSSFFont dataCellFont = getHSSFFont(workbook,"宋体",Short.valueOf("16"),true);
			headerCellStyle.setFont(dataCellFont);
		}
		Map<String, HSSFCellStyle> cellStyle = new HashMap<String, HSSFCellStyle>();
		cellStyle.put("headerCellStyle", headerCellStyle);
		cellStyle.put("dataCellStyle", dataCellStyle);
		return cellStyle;
	}
	
	
	/**
	 * 获取单元格
	 * @param workbook			工作簿
	 * @param align				单元格布局 center,left,right,top,bottom
	 * @param backgroudColor	Color
	 * @param hasBorder			是否有边框
	 * @param borderColor		Color
	 * @return
	 */
	private static HSSFCellStyle getCellStyle(HSSFWorkbook workbook, String align, HSSFColorPredefined backgroudColor, boolean hasBorder, HSSFColorPredefined borderColor){
		//创建单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		//设置单元格布局
		if("center".equals(align)){
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		}else if("left".equals(align)){
			cellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		}else if("right".equals(align)){
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		}else if("top".equals(align)){
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		}else if("bottom".equals(align)){
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
		}
//		cellStyle.setShrinkToFit(true);
		//设置单元格背景颜色
		cellStyle.setFillForegroundColor(backgroudColor.getIndex());
		cellStyle.setFillForegroundColor(backgroudColor.getIndex());
		//设置边框
//		if(hasBorder){
//			cellStyle.setBorderBottom(BorderStyle.MEDIUM);
//			cellStyle.setBorderLeft(BorderStyle.MEDIUM);
//			cellStyle.setBorderRight(BorderStyle.MEDIUM);
//			cellStyle.setBorderTop(BorderStyle.MEDIUM);
//		}
		cellStyle.setBottomBorderColor(borderColor.getIndex());
		//将单元格样式返回
		return cellStyle;
	}
		
	/**
	 * 获取字体样式
	 * @param workbook  工作簿
	 * @param fontName	字体名
	 * @param height	字体大小
	 * @param isBold	是否加粗
	 * @return
	 */
	private static HSSFFont getHSSFFont(HSSFWorkbook workbook, String fontName, short height, boolean isBold){
		//创建字体样式
		HSSFFont cellFont = workbook.createFont();
		//设置字体类型
		cellFont.setFontName(fontName);
		//设置字体大小
		cellFont.setFontHeightInPoints(height);	
		cellFont.setBold(isBold);
		//将字体样式返回
		return cellFont;
	}
	
}
