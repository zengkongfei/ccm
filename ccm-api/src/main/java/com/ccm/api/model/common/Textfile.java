package com.ccm.api.model.common;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;
/***
 * 文本文件类
 * @author Water
 */
public class Textfile extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4822874875632114178L;
	
	private String fileId;
	private String fileType;//文件类型
	private String fileName;//上传原始文件名
	private String fileSysName;//系统文件名
	private String fileUrl;//文件路径
	private String url;
	private Date createdTime;//上传时间
	private String hotelId;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSysName() {
		return fileSysName;
	}
	public void setFileSysName(String fileSysName) {
		this.fileSysName = fileSysName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
