package com.cs.spring.mvc.excel.core.poi;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ImportExcelUtils {

	public int totalRows; //sheet中总行数  
    public static int totalCells; //每一行总单元格数  
    
	public List<ArrayList<String>> readExcel(MultipartFile file) throws IOException { 
		if(file == null){
			return null;
		}
		String originalFilename = file.getOriginalFilename().trim();
        if("".equals(originalFilename) || !originalFilename.contains(".")){  
            return null;  
        } 
        String postfix = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        if("xls".equals(postfix)){  
            return readXls(file);  
        }else if("xlsx".equals(postfix)){  
            return readXlsx(file);
        }else{                    
            return null;  
        }  
    }
	
	public List<ArrayList<String>> readXlsx(MultipartFile file){  
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        InputStream input = null;  
        XSSFWorkbook wb = null;  
        ArrayList<String> rowList = null;  
        try {  
            input = file.getInputStream();  
            wb = new XSSFWorkbook(input);  	// 创建文档                         
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){  //读取sheet(页)  
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);  
                if(xssfSheet == null){  
                    continue;  
                }  
                totalRows = xssfSheet.getLastRowNum();                
                for(int rowNum = 1;rowNum <= totalRows;rowNum++){  
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);  
                    if(xssfRow!=null){  
                        rowList = new ArrayList<String>();  
                        totalCells = xssfRow.getLastCellNum();  
                        for(int c=0;c<=totalCells+1;c++){  
                            XSSFCell cell = xssfRow.getCell(c);  
                            if(cell==null){  
                                rowList.add("");  
                                continue;  
                            }                             
                            rowList.add(getXValue(cell).trim());  
                        }                                                 
                    }  
                }  
            }  
            return list;  
        } catch (IOException e) {             
            e.printStackTrace();  
        } finally{  
        	if(wb != null){
            	try{
            		wb.close();
            		wb =null;
            	}catch(Exception e){
            		e.printStackTrace(); 
            	}
            }
            try {  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }  
        return null;  
    }  
	
	public static String getXValue(XSSFCell xssfCell){  
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {  
            return String.valueOf(xssfCell.getBooleanCellValue());  
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
            String cellValue = "";  
//            if(XSSFDateUtil.isCellDateFormatted(xssfCell)){  
//                Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());  
//                cellValue = sdf.format(date);  
//            }else{  
                DecimalFormat df = new DecimalFormat("#.##");  
                cellValue = df.format(xssfCell.getNumericCellValue());  
                String strArr = cellValue.substring(cellValue.lastIndexOf(".")+1,cellValue.length());  
                if(strArr.equals("00")){  
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf("."));  
                }    
//            }  
            return cellValue;  
        } else {  
           return String.valueOf(xssfCell.getStringCellValue());  
        }  
   }  
	
	
	public List<ArrayList<String>> readXls(MultipartFile file){   
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();  
        // IO流读取文件  
        InputStream input = null;  
        HSSFWorkbook wb = null;  
        ArrayList<String> rowList = null;  
        try {  
            input = file.getInputStream();  
            // 创建文档  
            wb = new HSSFWorkbook(input);                         
            //读取sheet(页)  
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){  
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);  
                if(hssfSheet == null){  
                    continue;  
                }  
                totalRows = hssfSheet.getLastRowNum();                
                //读取Row,从第二行开始  
                for(int rowNum = 1;rowNum <= totalRows;rowNum++){  
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
                    if(hssfRow!=null){  
                        rowList = new ArrayList<String>();  
                        totalCells = hssfRow.getLastCellNum();  
                        //读取列，从第一列开始  
                        for(short c=0;c<=totalCells+1;c++){  
                            HSSFCell cell = hssfRow.getCell(c);  
                            if(cell==null){  
                                rowList.add("");  
                                continue;  
                            }                             
                            rowList.add(getHValue(cell).trim());  
                        }          
                        list.add(rowList);  
                    }                     
                }  
            }  
            return list;  
        } catch (IOException e) {             
            e.printStackTrace();  
        } finally{  
            try {  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
	
	
	public static String getHValue(HSSFCell hssfCell){  
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {  
            return String.valueOf(hssfCell.getBooleanCellValue());  
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {  
            String cellValue = "";  
//            if(HSSFDateUtil.isCellDateFormatted(hssfCell)){                  
//                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());  
//                cellValue = sdf.format(date);  
//            }else{  
                DecimalFormat df = new DecimalFormat("#.##");  
                cellValue = df.format(hssfCell.getNumericCellValue());  
                String strArr = cellValue.substring(cellValue.lastIndexOf(".")+1,cellValue.length());  
                if(strArr.equals("00")){  
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf("."));  
                }    
//            }  
            return cellValue;  
        } else {  
           return String.valueOf(hssfCell.getStringCellValue());  
        }  
   }  
}
