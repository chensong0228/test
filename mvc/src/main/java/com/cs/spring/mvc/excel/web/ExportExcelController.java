package com.cs.spring.mvc.excel.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cs.spring.mvc.base.BaseController;
import com.cs.spring.mvc.excel.bean.ExcelFile;
import com.cs.spring.mvc.excel.bean.ExcelHeader;
import com.cs.spring.mvc.excel.bean.ExcelSheet;
import com.cs.spring.mvc.excel.bean.ExcelHeaderStyle;
import com.cs.spring.mvc.excel.core.poi.ExportExcelUtil;
import com.cs.spring.mvc.excel.service.ExportExcelService;

@Controller
@RequestMapping("/common/exportExcel.spr")
public class ExportExcelController extends BaseController{
	
	@Autowired
	private ExportExcelService exportExcelService;
	
	@RequestMapping(params = "method=test")
    public void test(HttpServletRequest request, HttpServletResponse response){
		ExcelHeader excelHeader1 = new ExcelHeader("地市名称","name", 0, false, 0, 0, 70);
		ExcelHeader excelHeader2 = new ExcelHeader("基站","", 0, true, 4, 1, 70);
		ExcelHeader excelHeader3 = new ExcelHeader("3G基站","", 1, true, 2, 1, 70);
		ExcelHeader excelHeader4 = new ExcelHeader("LTE基站","", 1, true, 2, 3, 70);
		ExcelHeader excelHeader5 = new ExcelHeader("基站电路","", 0,true, 1, 5, 70);
		ExcelHeader excelHeader6 = new ExcelHeader("总数（个）","id", 2, false, 0, 1, 70);
		ExcelHeader excelHeader7 = new ExcelHeader("已接入分组传送网的数量（个）","password", 2, false, 0, 2, 70);
		ExcelHeader excelHeader8 = new ExcelHeader("总数（个）","loginName", 2,false, 0, 3, 70);
		ExcelHeader excelHeader9 = new ExcelHeader("已接入分组传送网的数量（个）","name", 2, false, 0, 4, 70);
		ExcelHeader excelHeader10 = new ExcelHeader("通过MSTP或分组传送网已开通21M及以上带宽的基站电路数量（条）","password", 1, false, 0, 5, 70);
		List<ExcelHeader> excelHeaders = new ArrayList<ExcelHeader>();
		excelHeaders.add(excelHeader1);
		excelHeaders.add(excelHeader2);
		excelHeaders.add(excelHeader3);
		excelHeaders.add(excelHeader4);
		excelHeaders.add(excelHeader5);
		excelHeaders.add(excelHeader6);
		excelHeaders.add(excelHeader7);
		excelHeaders.add(excelHeader8);
		excelHeaders.add(excelHeader9);
		excelHeaders.add(excelHeader10);
		ExcelSheet sheet1 = new ExcelSheet("sheet1","demo", excelHeaders);
		ExcelSheet sheet2 = new ExcelSheet("sheet2","demo", excelHeaders);
		List<ExcelSheet> excelSheets = new ArrayList<ExcelSheet>();
		excelSheets.add(sheet1);
		excelSheets.add(sheet2);
		ExcelFile excelFile = new ExcelFile("测试导出", excelSheets);
		try {
			Map<String,List<Map<String,Object>>> exportData = new HashMap<String,List<Map<String,Object>>>();
			List<Map<String,Object>> datas = exportExcelService.getData();
			exportData.put("sheet1", datas);
			exportData.put("sheet2", datas);
			ExportExcelUtil.getHSSFWorkbook(response, excelFile, exportData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=testJxlExportPagingExcel")
    public void testJxlExportPagingExcel(HttpServletRequest request, HttpServletResponse response){
		LinkedHashMap<String,String> titles = new LinkedHashMap<String,String>();
		titles.put("id", "员工ID_____");
		titles.put("name", "员工姓名————————————");
		titles.put("password", "员工密码++++++");
		titles.put("loginName", "员工登录名--------");
		List<Map<String,Object>> datas = exportExcelService.getData();
		com.cs.spring.mvc.excel.core.jxl.ExportExcelUtils.exportPagingExcel(titles, datas, 200000, "测试哦", response);
	}
	
	@RequestMapping(params = "method=testJxlExportMutiSheetExcel")
    public void testJxlExportMutiSheetExcel(HttpServletRequest request, HttpServletResponse response){
		LinkedHashMap<String,LinkedHashMap<String,String>> sheets = new LinkedHashMap<String,LinkedHashMap<String,String>>();
		LinkedHashMap<String,String> title1 = new LinkedHashMap<String,String>();
		title1.put("id", "员工ID");
		title1.put("loginName", "员工登录名");
		title1.put("name", "员工姓名");
		title1.put("password", "员工密码");
		LinkedHashMap<String,String> title2 = new LinkedHashMap<String,String>();
		title2.put("password", "员工密码");
		title2.put("loginName", "员工登录名");
		title2.put("id", "员工ID");
		title2.put("name", "员工姓名");
		LinkedHashMap<String,String> title3 = new LinkedHashMap<String,String>();
		title3.put("name", "员工姓名");
		title3.put("password", "员工密码");
		title3.put("loginName", "员工登录名");
		title3.put("id", "员工ID");
		sheets.put("标签1", title1);
		sheets.put("标签2", title2);
		sheets.put("标签3", title3);
		Map<String,List<Map<String,Object>>> datas = new HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> data1 = exportExcelService.getData();
		datas.put("标签1", data1);
		datas.put("标签2", data1);
		datas.put("标签3", data1);
		com.cs.spring.mvc.excel.core.jxl.ExportExcelUtils.exportMutiSheetExcel(sheets, datas, "测试哦", response);
	}
	
	@RequestMapping(params = "method=testPoiExportPagingExcel")
    public void testPoiExportPagingExcel(HttpServletRequest request, HttpServletResponse response){
		LinkedHashMap<String,String> titles = new LinkedHashMap<String,String>();
		titles.put("id", "员工ID_____");
		titles.put("name", "员工姓名————————————");
		titles.put("password", "员工密码++++++");
		titles.put("loginName", "员工登录名--------");
		List<Map<String,Object>> datas = exportExcelService.getData();
		com.cs.spring.mvc.excel.core.poi.ExportExcelUtils.exportPagingExcel(titles, datas, 200000, "测试哦", response);
	}
	
	@RequestMapping(params = "method=testPoiExportMutiSheetExcel")
    public void testPoiExportMutiSheetExcel(HttpServletRequest request, HttpServletResponse response){
		LinkedHashMap<String,LinkedHashMap<String,String>> sheets = new LinkedHashMap<String,LinkedHashMap<String,String>>();
		LinkedHashMap<String,String> title1 = new LinkedHashMap<String,String>();
		title1.put("id", "员工ID");
		title1.put("loginName", "员工登录名");
		title1.put("name", "员工姓名");
		title1.put("password", "员工密码");
		LinkedHashMap<String,String> title2 = new LinkedHashMap<String,String>();
		title2.put("password", "员工密码");
		title2.put("loginName", "员工登录名");
		title2.put("id", "员工ID");
		title2.put("name", "员工姓名");
		LinkedHashMap<String,String> title3 = new LinkedHashMap<String,String>();
		title3.put("name", "员工姓名");
		title3.put("password", "员工密码");
		title3.put("loginName", "员工登录名");
		title3.put("id", "员工ID");
		sheets.put("标签1", title1);
		sheets.put("标签2", title2);
		sheets.put("标签3", title3);
		Map<String,List<Map<String,Object>>> datas = new HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> data1 = exportExcelService.getData();
		datas.put("标签1", data1);
		datas.put("标签2", data1);
		datas.put("标签3", data1);
		com.cs.spring.mvc.excel.core.poi.ExportExcelUtils.exportMutiSheetExcel(sheets, datas, "测试哦", response);
	}
	
	
}
