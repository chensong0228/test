package com.cs.spring.mvc.excel.core.poi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExportExcelUtils {

	/**
	 * 导出分页的excel
	 * @param titles 	标题{'name1':'标题1','name2':'标题2','name3':'标题3'}
	 * @param datas		数据集合[{'name1':'data1','name2':'data2','name3':'data3'},。。。]
	 * @param sheetNum	每个sheet多少条数据，超出则分页处理 
	 * @param fileName	导出的文件名
	 * @param response
	 */
	public static void exportPagingExcel(LinkedHashMap<String, String> titles, List<Map<String, Object>> datas, 
			int sheetNum, String fileName, HttpServletResponse response) {
		HSSFWorkbook workbook = ExportExcelUtils.getPagingWorkbook(titles, datas, sheetNum);
		try {
			response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8")+".xls");
			response.setContentType("application/msexcel"); 
			OutputStream os = response.getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出分页的excel
	 * @param titles 	标题{'sheet1':{'name1':'标题1','name2':'标题2','name3':'标题3'},'sheet2':...}
	 * @param datas		数据集合{'sheet1':[{'name1':'data1','name2':'data2','name3':'data3'},。。。],'sheet2':[]}
	 * @param fileName	导出的文件名
	 * @param request
	 * @param response
	 */
	public static void exportMutiSheetExcel(LinkedHashMap<String,LinkedHashMap<String,String>> sheets, 
			Map<String,List<Map<String,Object>>> datas, String fileName, HttpServletResponse response) {
		HSSFWorkbook workbook = ExportExcelUtils.getMutiSheetWorkbook(sheets, datas);
		try {
			response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8")+".xls");
			response.setContentType("application/msexcel"); 
			OutputStream os = response.getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出分页的excel
	 * @param titles	标题{'name1':'标题1','name2':'标题2','name3':'标题3'}
	 * @param datas		数据集合[{'name1':'data1','name2':'data2','name3':'data3'},。。。]
	 * @param sheetNum	每个sheet多少条数据，超出则分页处理 
	 * @return
	 */
	private static HSSFWorkbook getPagingWorkbook(LinkedHashMap<String,String> titles, List<Map<String,Object>> datas, int sheetNum){
		HSSFWorkbook workbook = new HSSFWorkbook();
		//获取表格样式
		CellStyle dataCellStyle = getDataStyle(workbook);
        CellStyle headerCellStyle = getHeaderCellStyle(workbook);
        //获取分页信息
        if(sheetNum <= 0){
        	sheetNum = datas.size();
        }
        int sheetCount = datas.size() / sheetNum;//页数
        sheetCount = datas.size() % sheetNum == 0 ? sheetCount : sheetCount + 1;
        //获取表头信息
        ArrayList<String> titleKeys = new ArrayList<String>();
        ArrayList<String> titleNames = new ArrayList<String>();
        for (Entry<String, String> titleEntry : titles.entrySet()) {
        	titleKeys.add(titleEntry.getKey());
        	titleNames.add(titleEntry.getValue());
		}
        //将数据进行分页导出
        for (int i = 0; i < sheetCount; i++) {
        	//定义sheet页
            String sheetName = "第" + (i + 1) + "页";
            Sheet sheet = workbook.createSheet(sheetName);
            sheet.setDefaultColumnWidth(20);
            sheet.setDefaultRowHeightInPoints(20);
            sheet.createFreezePane(0,1,0,1); 	//冻结第一行
            int row = 0;
            //插入表头
            Row titleRow = sheet.createRow(row++);
            for (int j = 0; j < titleNames.size(); j++) {//设置标题
            	Cell cell = titleRow.createCell(j);
                cell.setCellValue(titleNames.get(j));
                cell.setCellStyle(headerCellStyle);
            }
            //插入数据
            for (int k = i * sheetNum; k < (i + 1) * sheetNum && k < datas.size(); k++) {
            	Map<String,Object> rowData = datas.get(k);
            	Row dataRow = sheet.createRow(row++);
            	for (int j = 0; j < titleKeys.size(); j++) {
            		Cell cell = dataRow.createCell(j);
                    cell.setCellValue(MapUtils.getString(rowData,titleKeys.get(j),""));
                    cell.setCellStyle(dataCellStyle);
            	}
            }
        }
		return workbook;
	}
	
	/**
	 * 导出不同的sheet页数据
	 * @param titles	{'sheet1':{'name1':'标题1','name2':'标题2','name3':'标题3'},'sheet2':{'column1':'列1','column2':'列2','column3':'列3'}，。。。}
	 * @param datas		['sheet1':[{'name1':'data1','name2':'data2','name3':'data3'},。。。],'sheet2':...]
	 * @return
	 */
	private static HSSFWorkbook getMutiSheetWorkbook(LinkedHashMap<String,LinkedHashMap<String,String>> sheets,Map<String, List<Map<String,Object>>> datas){
		HSSFWorkbook workbook = new HSSFWorkbook();
		//获取表格样式
		CellStyle dataCellStyle = getDataStyle(workbook);
        CellStyle headerCellStyle = getHeaderCellStyle(workbook);
        //分页导出
        for (Entry<String, LinkedHashMap<String,String>> sheetEntry : sheets.entrySet()) {
        	//获取sheet页名称
        	String sheetName = sheetEntry.getKey();
        	//获取sheet页表头
        	LinkedHashMap<String,String> sheetTitles = sheetEntry.getValue();
        	ArrayList<String> titleKeys = new ArrayList<String>();
	        ArrayList<String> titleNames = new ArrayList<String>();
			for (Entry<String, String> titleEntry : sheetTitles.entrySet()) {
				titleKeys.add(titleEntry.getKey());
				titleNames.add(titleEntry.getValue());
			}
        	//定义sheet页
            Sheet sheet = workbook.createSheet(sheetName);
            sheet.setDefaultColumnWidth(20);
            sheet.setDefaultRowHeightInPoints(20);
            sheet.createFreezePane(0,1,0,1);
            int row = 0;
            //插入表头
            Row titleRow = sheet.createRow(row++);
            for (int j = 0; j < titleNames.size(); j++) {//设置标题
            	Cell cell = titleRow.createCell(j);
                cell.setCellValue(titleNames.get(j));
                cell.setCellStyle(headerCellStyle);
            }
            //插入数据
            List<Map<String, Object>> sheetData = datas.get(sheetName);
            for (int k = 0; k < sheetData.size(); k++) {
            	Map<String,Object> rowData = sheetData.get(k);
            	Row dataRow = sheet.createRow(row++);
            	for (int j = 0; j < titleKeys.size(); j++) {
            		Cell cell = dataRow.createCell(j);
                    cell.setCellValue(MapUtils.getString(rowData,titleKeys.get(j),""));
                    cell.setCellStyle(dataCellStyle);
            	}
            }
        }
		return workbook;
	}
	
	/**
     * 获取表头标题单元格的样式
     * @param workbook
     * @return
     */
	private static HSSFCellStyle getHeaderCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        //样式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //字体
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(headerFont);
        return headerCellStyle;
    }
    
    /**
	 * 获取Excel表格数据样式
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle getDataStyle(HSSFWorkbook workbook) {
		HSSFCellStyle dataCellStyle = workbook.createCellStyle();
		//样式
		dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		dataCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		dataCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dataCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//字体
		Font dataFont = workbook.createFont();
		dataFont.setColor(HSSFColor.BLACK.index);
		dataFont.setFontHeightInPoints((short) 10);
		dataCellStyle.setFont(dataFont);
		return dataCellStyle;
	}
}
