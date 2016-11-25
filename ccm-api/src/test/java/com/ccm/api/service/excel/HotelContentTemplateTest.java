package com.ccm.api.service.excel;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.dataImport.ExcelDataImportManager;
import com.ccm.api.util.CommonUtil;



public class HotelContentTemplateTest extends BaseManagerTestCase{

	@Autowired
	private ExcelDataImportManager manager;
	

	// 项目配置属性
	protected Properties projectProperties;


	@Resource
	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}
	
	/**
	 * this test case has been refined.
	 * 
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void testExcelImport() throws Exception {

		System.out.println("---------------------------test begin-------------------------------------");
		
//		String filePath = "E:/test/Hotel Content Template_For Taobao 2.0.xls";
//		String[] sheetNames = {
////				HotelContentTemplateImportImpl.SHEET_HOTEL_NAME
////				HotelContentTemplateImportImpl.SHEET_ROOMTYPE_NAME
//				HotelContentTemplateImportImpl.SHEET_RATEPLAN_NAME
////				HotelContentTemplateImportImpl.SHEET_PRODUCT_NAME
//		};
//		
//		Map<String,Object> map = manager.getExcelData(filePath, sheetNames);
//		
//		System.out.println(map.get("result").toString());
//		
//		//如果执行失败
//		if(!"SUCCESS".equals(map.get("result").toString())){
//			System.out.println(map.get("errKey"));
//			System.out.println(map.get("errValue"));
//			System.out.println(map.get("errRow"));
//			System.out.println(map.get("pattern"));
//		}
//		if(ExcelDataImportManager.SUCCESS.equals(map.get("result"))){
//			manager.saveDataToDatabase(map);
//		}
		
//		System.out.println(projectProperties.getProperty("activemq.brokerURL"));
//		this.setPictureList("E:\\work\\ccm\\ccm-b2b\\src\\main\\webapp\\img");
//		if(pictureList.size()>0){
//			for (int i = 0; i < pictureList.size(); i++) {
//				System.out.println(pictureList.get(i).getName());
//			}
//		}
		
//		this.unzip("E:\\test\\test1.zip","E:\\test");
//		this.unZipFolder("E:\\test");
		
		
		
		
//		Object numberStr = 0.0;
//		
//		System.out.println(CommonUtil.isNotEmpty(numberStr));
//		
//		if(numberStr!=null&&StringUtils.isNotBlank(numberStr+"")){
//			System.out.println((int)Double.parseDouble(numberStr+""));
//			
//		}else{
//			System.out.println("null");
//		}
		
		this.validateGBK("a;df9742334我是中国人7wwjld$##@");
		
		System.out.println("---------------------------test end-------------------------------------");

	}
	
	private void executeJsonString(){
		String jsonString = "{'cancelPolicyType':3,'policyInfo':{'2013-11-25':10,'2013-11-28':20}}";
		
		//转成对象再处理
		JSONObject cencelPolicy =  JSONObject.parseObject(jsonString);
		Map<String,Object> cpMap = (Map<String, Object>) cencelPolicy;
		
		System.out.println(cpMap.get("cancelPolicyType"));
		
		JSONObject policyInfo = (JSONObject) cencelPolicy.get("policyInfo");
		Set<Entry<String,Object>>  policySet = policyInfo.entrySet();
		
		for (Entry<String, Object> entry : policySet) {
			
			
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
	
	
	List<File> pictureList = new ArrayList<File>();
	private void setPictureList(String formetFolderPath){
		File file = new File(formetFolderPath);
		File[] files = file.listFiles();
		
		if(files!=null&&files.length>0){
			for (int i = 0; i < files.length; i++) {
				
				  //如果是文件目录
				  if(files[i].isDirectory()){
					  this.setPictureList(files[i].getPath());
				  }else if(files[i].getName().startsWith("")){
					  pictureList.add(files[i]);
				  }
			}
		}
	}
	
	private void unZipFolder(String formetFolderPath) throws Exception{
		File file = new File(formetFolderPath);
		File[] files = file.listFiles();
		
		if(files!=null&&files.length>0){
			for (int i = 0; i < files.length; i++) {
				
				  //如果是文件目录
				  if(files[i].isDirectory()){
					  this.unZipFolder(files[i].getPath());
	
				  }else if(files[i].getName().endsWith(".zip")){
					  System.out.println("path:"+files[i].getPath());
					  System.out.println("parentPath:"+files[i].getParentFile().getPath());
					  this.unzip(files[i], files[i].getParentFile().getPath());
				  }
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void unzip(File zf, String unzipDirectory) throws Exception {
		
		  // 创建zip文件对象
		  ZipFile zipFile = new ZipFile(zf);
		  // 创建zip文件解压目录
		  File unzipFile = new File(unzipDirectory + "/"
				  + zf.getName().substring(0, zf.getName().lastIndexOf(".")));
		  
		  if (!unzipFile.exists()){
			  unzipFile.mkdir();
		  }
			
		  // 得到zip文件条目枚举对象
		  Enumeration zipEnum = zipFile.entries();
		  // 定义输入输出流对象
		  InputStream input = null;
		  OutputStream output = null;
		 
		  while (zipEnum.hasMoreElements()) {
			   // 得到当前条目
			   ZipEntry entry = (ZipEntry) zipEnum.nextElement();
			   String entryName = new String(entry.getName().getBytes("ISO8859_1"));
		
			   // 若当前条目为目录则创建
			   if (entry.isDirectory()){
				   new File(unzipDirectory + "/" + entryName).mkdir();
				   
			   }else { // 若当前条目为文件则解压到相应目录
				   	input = zipFile.getInputStream(entry);
				   	output = new FileOutputStream(new File(unzipFile.getPath() + "/" + entryName));
				   	byte[] buffer = new byte[1024 * 8];
				   	int readLen = 0;
				   	while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1){
					   	output.write(buffer, 0, readLen);
				   	}
				   	input.close();
			   		output.flush();
			   		output.close();
			  }
			   
			  
		 }
	}
	
	private void validateGBK(String str){
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c>+256){
				System.out.println(c + ":中文");
			}else{
				System.out.println(c + ":英文或者数字");
			}
		}
	}
}
