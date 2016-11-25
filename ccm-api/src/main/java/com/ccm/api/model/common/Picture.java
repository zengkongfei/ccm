package com.ccm.api.model.common;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 图片表
 */
public class Picture extends BaseObject {

	private static final long serialVersionUID = 3564016896542136139L;

	private String picId;

	private String cateId;// 参见PictureCategory.java

	private String fileFileName;// 上传原始文件名

	private String picSysName;// 图片系统名称,uuid

	private String specName;// 参看PictureSpec.java,切图规格，11种？针对酒店和房型,webcut_215x215

	private String url;// 相对于Context的路径

	private String picName;// 图片名称，上传时输入的名称

	private String bizId;// 业务对象

	private String bizType;// 参见PictureType.java, 业务对象类型：房型，酒店，商家，客户

	private Date createdTime; // 如果是srcFlag等于1 则为上传时间, 等于2 则为 切图时间

	private String srcFlag;// 是否原图

	private String cutFlag;// 是否做过切图标志,用在图片处理

	private String defaultFlag; // 参见YesNo.java,是否默认图片，如果是默认图片，在对多图房型和酒店，查询列表中默认显示的图片,详情页的第一张图片

	private Integer width; // 长度

	private Integer height; // 宽度

	private String channelCode;// 从哪个渠道过来的图片

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getPicSysName() {
		return picSysName;
	}

	public void setPicSysName(String picSysName) {
		this.picSysName = picSysName;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getSrcFlag() {
		return srcFlag;
	}

	public void setSrcFlag(String srcFlag) {
		this.srcFlag = srcFlag;
	}

	public String getCutFlag() {
		return cutFlag;
	}

	public void setCutFlag(String cutFlag) {
		this.cutFlag = cutFlag;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 * @return the picId
	 */
	public String getPicId() {
		return picId;
	}

	/**
	 * @param picId
	 *            the picId to set
	 */
	public void setPicId(String picId) {
		this.picId = picId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the bizId
	 */
	public String getBizId() {
		return bizId;
	}

	/**
	 * @param bizId
	 *            the bizId to set
	 */
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	/**
	 * @return the bizType
	 */
	public String getBizType() {
		return bizType;
	}

	/**
	 * @param bizType
	 *            the bizType to set
	 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return picId;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
