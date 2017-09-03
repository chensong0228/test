package com.cs.spring.mvc.excel.core.jxl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.MapUtils;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportExcelUtils {

	/**
	 * 导出分页的excel
	 * @param titles 	标题{'name1':'标题1','name2':'标题2','name3':'标题3'}
	 * @param datas		数据集合[{'name1':'data1','name2':'data2','name3':'data3'},。。。]
	 * @param sheetNum	每个sheet多少条数据，超出则分页处理 
	 * @param fileName	导出的文件名
	 * @param response
	 */
	public static void exportPagingExcel(LinkedHashMap<String, String> titles, List<Map<String, Object>> datas, int sheetNum, String fileName, HttpServletResponse response) {
		try {
			response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8")+".xls");
			response.setContentType("application/msexcel"); 
			OutputStream os = ExportExcelUtils.getPagingExcel(titles, datas, sheetNum,response);
			os.flush();
			os.close();
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
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
		try {
			response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8")+".xls");
			response.setContentType("application/msexcel"); 
			OutputStream os = ExportExcelUtils.getMutiSheetExcel(sheets, datas,response);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static OutputStream getMutiSheetExcel(LinkedHashMap<String, LinkedHashMap<String, String>> sheets, 
			Map<String, List<Map<String, Object>>> datas,HttpServletResponse response) throws IOException, WriteException {
		OutputStream os = response.getOutputStream();
    	WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 获取表格样式
		WritableCellFormat dataCellStyle = getDataStyle(workbook);
		WritableCellFormat headerCellStyle = getHeaderCellStyle(workbook);
		int sheetCount = 0;	//sheet的序号
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
    		WritableSheet sheet = workbook.createSheet(sheetName, sheetCount++);
			SheetSettings sheetSetting = sheet.getSettings();
			sheetSetting.setDefaultColumnWidth(20);
			sheetSetting.setDefaultRowHeight(255);
			sheetSetting.setVerticalFreeze(1); // 冻结第一行 
			int row = 0;
            //插入表头
			for (int j = 0; j < titleNames.size(); j++) {// 设置标题
				sheet.addCell(new Label(j, row, titleNames.get(j), headerCellStyle));
			}
            //插入数据
            List<Map<String, Object>> sheetData = datas.get(sheetName);
            for (int k = 0; k < sheetData.size(); k++) {
            	Map<String,Object> rowData = sheetData.get(k);
            	++row;
            	for (int j = 0; j < titleKeys.size(); j++) {
                    sheet.addCell(new Label(j, row, MapUtils.getString(rowData, titleKeys.get(j), ""), dataCellStyle));
            	}
            }
        }
        workbook.write();	// 写入Exel工作表
		workbook.close();	// 关闭Excel工作薄对象
		return os;
	}

	private static OutputStream getPagingExcel(LinkedHashMap<String, String> titles, List<Map<String, Object>> datas, int sheetNum, HttpServletResponse response) throws IOException, WriteException {
    	OutputStream os = response.getOutputStream();
    	WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 获取表格样式
		WritableCellFormat dataCellStyle = getDataStyle(workbook);
		WritableCellFormat headerCellStyle = getHeaderCellStyle(workbook);
		// 获取分页信息
		if (sheetNum <= 0) {
			sheetNum = datas.size();
		}
		int sheetCount = datas.size() / sheetNum;// 页数
		sheetCount = datas.size() % sheetNum == 0 ? sheetCount : sheetCount + 1;
		// 获取表头信息
		ArrayList<String> titleKeys = new ArrayList<String>();
		ArrayList<String> titleNames = new ArrayList<String>();
		for (Entry<String, String> titleEntry : titles.entrySet()) {
			titleKeys.add(titleEntry.getKey());
			titleNames.add(titleEntry.getValue());
		}
		// 将数据进行分页导出
		for (int i = 0; i < sheetCount; i++) {
			String sheetName = "第" + (i + 1) + "页";
			WritableSheet sheet = workbook.createSheet(sheetName, i);
			SheetSettings sheetSetting = sheet.getSettings();
			sheetSetting.setDefaultColumnWidth(20);
			sheetSetting.setDefaultRowHeight(255);
			sheetSetting.setVerticalFreeze(1); // 冻结第一行 
			int row = 0;
			// 插入表头
			for (int j = 0; j < titleNames.size(); j++) {// 设置标题
				sheet.addCell(new Label(j, row, titleNames.get(j), headerCellStyle));
			}
			// 插入数据
			for (int k = i * sheetNum; k < (i + 1) * sheetNum && k < datas.size(); k++) {
				Map<String, Object> rowData = datas.get(k);
				++row;
				for (int j = 0; j < titleKeys.size(); j++) {
					sheet.addCell(new Label(j, row, MapUtils.getString(rowData, titleKeys.get(j), ""), dataCellStyle));
				}
			}
		}
		workbook.write();	// 写入Exel工作表
		workbook.close();	// 关闭Excel工作薄对象
		return os;
	}

	private static WritableCellFormat getHeaderCellStyle(WritableWorkbook workbook) throws WriteException {
    	//字体
    	WritableFont headerFontStyle = new WritableFont(WritableFont.ARIAL, 12,WritableFont.BOLD);
    	//样式
    	WritableCellFormat headerCellStyle = new WritableCellFormat(headerFontStyle);
    	headerCellStyle.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
    	headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
    	headerCellStyle.setAlignment(Alignment.CENTRE); // 文字水平对齐
    	headerCellStyle.setWrap(false); // 文字是否换行
		return headerCellStyle;
	}

	private static WritableCellFormat getDataStyle(WritableWorkbook workbook) throws WriteException {
		//字体
		WritableFont dataFontStyle = new WritableFont(WritableFont.ARIAL, 12);
		//样式
		WritableCellFormat dataCellStyle = new WritableCellFormat(dataFontStyle);
		dataCellStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTRE);
		dataCellStyle.setAlignment(Alignment.LEFT);
		dataCellStyle.setWrap(false);
		return dataCellStyle;
	}
}
