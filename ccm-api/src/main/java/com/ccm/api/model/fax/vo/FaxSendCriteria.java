package com.ccm.api.model.fax.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class FaxSendCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263735083602630308L;
	private String faxType; // 模板类型
	private String faxNumber; // 传真号码
	private String faxContent; // 内容
	private String verifyCode; // 语言代码
	private String bizId; // 订单id
	private String msgId; // 发送传真的消息id
	private String resultCode; // 结果状态代码
	private String resultMsg; // 结果信息
	private String sta;//订单状态
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private List<String> hotelIdList;//酒店id
	private boolean excelFlag = false;// 导出标志
	private String faxStatus;// fax 在接口查询的状态
	
	
	
	public String getFaxType() {
		return faxType;
	}
	public void setFaxType(String faxType) {
		this.faxType = faxType;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getFaxContent() {
		return faxContent;
	}
	public void setFaxContent(String faxContent) {
		this.faxContent = faxContent;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<String> getHotelIdList() {
		return hotelIdList;
	}
	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}
	public boolean isExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	public String getFaxStatus() {
		return faxStatus;
	}
	public void setFaxStatus(String faxStatus) {
		this.faxStatus = faxStatus;
	}
	@Override
	public String toString() {
		return "FaxSendCriteria [faxType=" + faxType + ", faxNumber=" + faxNumber + ", faxContent=" + faxContent + ", verifyCode=" + verifyCode + ", bizId=" + bizId + ", msgId=" + msgId + ", resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", sta=" + sta + ", beginDate=" + beginDate + ", endDate=" + endDate + ", hotelIdList=" + hotelIdList + ", excelFlag=" + excelFlag + ", faxStatus=" + faxStatus + "]";
	}
	
	

}
