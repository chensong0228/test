package mvc;

import org.apache.commons.collections4.KeyValue;
import org.junit.runner.Result;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

import com.google.common.primitives.Bytes;

public class Test2 {

	public static void main(String[] args) {

	       ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-beans-hbase.xml" });
	       BeanFactory factory = (BeanFactory) context;
	       HbaseTemplate htemplate = (HbaseTemplate) factory.getBean("htemplate");
	       String custom = "custom";
//	       htemplate.get("wcm", "10461", new RowMapper<HbaseTemplate>() {
//
//			@Override
//			public HbaseTemplate mapRow(org.apache.hadoop.hbase.client.Result result, int rowNum) throws Exception {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//	       htemplate.get("wcm", "10461", new RowMapper<String>(){
//
//			@Override
//			public String mapRow(Result result, int rowNum) throws Exception {
//				// TODO Auto-generated method stub
//				return null;
//			}
////	           public String mapRow(Result result, int rowNum) throws Exception {
////	                for(KeyValue kv :result.raw()){
////	                    String key = newString(kv.getQualifier());
////	                    String value = new String(kv.getValue());
////	                    System.out.println(key +"= "+Bytes.toString(value.getBytes()));
////	                }
////	                return null;
////	           }
//
////			@Override
////			public String mapRow(Result result, int rowNum) throws Exception {
////				// TODO Auto-generated method stub
////				return null;
////			}
//	       });

	}

}
