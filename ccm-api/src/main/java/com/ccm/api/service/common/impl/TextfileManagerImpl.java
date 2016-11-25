/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.common.TextfileDao;

import com.ccm.api.model.common.Textfile;

import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.TextfileManager;

@Service("textfileManager")
public class TextfileManagerImpl extends GenericManagerImpl<Textfile, String> implements TextfileManager {

	@SuppressWarnings("unused")
	private transient static Logger log = Logger.getLogger(Textfile.class);
	@Autowired
	private TextfileDao textfileDao;
	
	@Autowired
	public TextfileManagerImpl(TextfileDao textfileDao){
		super(textfileDao);
		this.textfileDao=textfileDao;
	}
	
	public TextfileDao getTextfileDao() {
		return textfileDao;
	}

	public void setTextfileDao(TextfileDao textfileDao) {
		this.textfileDao = textfileDao;
	}

	public Properties getProjectProperties() {
		return projectProperties;
	}

	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}

	@Override
	public Textfile addTextfile(Textfile textfile) {
		return textfileDao.addTextfile(textfile);
	}

	@Override
	public void deleteTextfile(String fileId) {
		textfileDao.deleteTextfile(fileId);
	}

	@Override
	public Textfile getTextfile(String fileId) {
		return textfileDao.getTextfile(fileId);
	}
	
	/**
	 * 构建上传文件信息（例如：路径，文件名称等）
	 */
	public Textfile createTextfile(String fileFileName,String hotelId) throws Exception {
		//酒店与样式文件一一对应，hotelId就是fileId
		String sysFileId = hotelId;
		// 获取扩展名
		int start = fileFileName.lastIndexOf("/");
		int end = fileFileName.lastIndexOf(".");
		String fileType;
		String textfileDir="/sharenfs/ccm_pic/resources/css/"; //服务器文件路径
		String dDir="/css/";	//数据库字段路径
		if (start < end && end < fileFileName.length()) {
			fileType = fileFileName.substring(end + 1);
		} else {
			fileType = "css";
		}
		String fileSysName = sysFileId + "." + fileType;
		//存储文件的路径
		String fileUrl =textfileDir+ fileSysName;
		//存储的路径
		String url=dDir+ fileSysName;
		// 获取系统上传图片的目录（先获取图片目录，再判断目录是否存在，若不存在则新建）
		File dirPath = new File(textfileDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		Textfile textfile = new Textfile();
		
		textfile.setFileId(sysFileId);
		textfile.setFileType(fileType);
		textfile.setFileSysName(fileSysName);
		textfile.setFileUrl(fileUrl);
		textfile.setFileName(fileFileName);
		textfile.setHotelId(hotelId);
		textfile.setUrl(url);
		
		return textfile;
	}

}
