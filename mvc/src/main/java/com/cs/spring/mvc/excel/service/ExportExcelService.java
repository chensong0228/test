package com.cs.spring.mvc.excel.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs.spring.mvc.excel.dao.ExportExcelDao;

@Service
public class ExportExcelService{

	@Autowired
	private ExportExcelDao exportExcelDao;
	
	public List<Map<String, Object>> getData() {
		return exportExcelDao.getData();
	}

	public List<Map<String, String>> getData2() {
		return exportExcelDao.getData2();
	}
	
	
}
