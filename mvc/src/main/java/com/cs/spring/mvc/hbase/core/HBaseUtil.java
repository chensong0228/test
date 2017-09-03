package com.cs.spring.mvc.hbase.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseUtil {
	
	private static Configuration configuration;
	private static Connection connection = null;
	private static Admin admin = null;
	
	private static final int DEFAULT_MAX_VERSIONS = 3;
	
	private static Logger logger = LoggerFactory.getLogger(HBaseUtil.class);
	
	static {
	    configuration = HBaseConfiguration.create();
	    configuration.set("hbase.zookeeper.property.clientPort", "2181");
	    configuration.set("hbase.zookeeper.quorum", "poc-db-009");
	    configuration.set("hbase.master", "10.145.242.35:60000");
	    configuration.set("zookeeper.znode.parent", "/hbase");
	    //configuration.set("hbase.cluster.distributed", "true");
	}
	
	/**
	 * 获取HTable实例
	 * @param tableName
	 * @return
	 */
	private static HTable getHTable(String tableName) {  
        HTable hTable = null;  
        try {  
        	connection = ConnectionFactory.createConnection(configuration);
            hTable = (HTable) connection.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {  
            e.printStackTrace();
            logger.info("获取表："+tableName+"实例失败,发生错误："+e.getMessage());
            return null;  
        }  
        return hTable;  
    }
	
	/**
	 * 获取HBaseAdmin实例
	 * @return
	 * @throws IOException
	 */
	public static Admin getHBaseAdmin(){
		try {
			if (admin == null) {
				connection = ConnectionFactory.createConnection(configuration);
				admin = connection.getAdmin();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("获取HBaseAdmin实例失败,发生错误："+e.getMessage());
			return null;
		}
		return admin;
	}
	
	/**
	 * 关闭HTable实例
	 * @param hTable
	 */
	private static void closeHTable(HTable hTable) {
		try {
			if(hTable != null){
				hTable.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public List<Map<String, Object>> scanTable(String tableName) {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		try {
			HTable hTable = getHTable(tableName);
			Scan scan = new Scan();
			ResultScanner rs = hTable.getScanner(scan);
			for (Result r : rs) {
				Map<String, Object> map = resultTransformMap(r);
				resList.add(map);
			}
			hTable.close();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return resList;

	}
	
	private static Map<String, Object> resultTransformMap(Result result) {
		if (result == null) {
			return null;
		}
		List<String> rowkeyList = new ArrayList<String>();
		List<String> quilifierList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		List<Object> timeList = new ArrayList<Object>();
		Map<String, Object> resMap = new HashMap<String, Object>();
		for (Cell cell : result.rawCells()) {
			String Rowkey = Bytes.toString(result.getRow()); // 主键
			String Quilifier = Bytes.toString(CellUtil.cloneQualifier(cell)); // 名
			String Value = Bytes.toString(CellUtil.cloneValue(cell)); // 值
			Long Time = cell.getTimestamp(); // 时间戳
			rowkeyList.add(Rowkey);
			quilifierList.add(Quilifier);
			valueList.add(Value);
			timeList.add(Time);
		}
		for (int i = 0; i < rowkeyList.size(); i++) {
			resMap.put(quilifierList.get(i), valueList.get(i));
		}
		resMap.put("rowKey", rowkeyList.get(0));
		return resMap;
	}
}
