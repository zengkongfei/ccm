package com.ccm.mc.webapp.action.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.common.Textfile;
import com.ccm.api.service.common.TextfileManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.RequestUtil;

/**
 * 文本文件上传类
 */
public class TextfileUploadAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5601506465638547467L;

	private File file;
	private String fileFileName;
	private TextfileManager textfileManager;
	private String hotelId;
	/**
	 * 图片上传方法
	 */
	public String execute() {
		if(hotelId==null || hotelId.length()<1){
			return null;
		}
		try{
			InputStream stream = new FileInputStream(file);
			int maxSize = 512000;
			if (stream.available() > maxSize) {
				System.out.println("文件过大！");
				new Exception("over maxSize").printStackTrace();
			} else {
				Textfile textfile=textfileManager.createTextfile(fileFileName,hotelId);
				textfileManager.deleteTextfile(hotelId);
				String faPtha=textfile.getFileUrl();
				
				OutputStream bos = new FileOutputStream(faPtha);
				int bytesRead;
				byte[] buffer = new byte[8192];
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				bos.close();
				stream.close();
				textfile.setFileUrl(textfile.getUrl());
				textfile = textfileManager.addTextfile(textfile);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public TextfileManager getTextfileManager() {
		return textfileManager;
	}

	public void setTextfileManager(TextfileManager textfileManager) {
		this.textfileManager = textfileManager;
	}
	
}
