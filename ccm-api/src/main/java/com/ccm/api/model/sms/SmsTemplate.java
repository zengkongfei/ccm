package com.ccm.api.model.sms;

import com.ccm.api.model.base.BaseObject;

/**
 * 短信发送模板表
 */
public class SmsTemplate extends BaseObject {

	private static final long serialVersionUID = -3055216702676545541L;

	private String templateId;
	
	private String hotelCode;   //酒店代码
	
	private String language;    //语言编码

	private String smsType; // 模板类型

	private String templateDesc; // 模板描述

	private String templateContent; // 模板内容 velocity方式

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	
	

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	

	@Override
	public String toString() {
		return "SmsTemplate [templateId=" + templateId + ", hotelCode="
				+ hotelCode + ", language=" + language + ", smsType=" + smsType
				+ ", templateDesc=" + templateDesc + ", templateContent="
				+ templateContent + "]";
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
