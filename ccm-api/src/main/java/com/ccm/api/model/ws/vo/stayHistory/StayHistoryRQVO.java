package com.ccm.api.model.ws.vo.stayHistory;

import java.util.Date;
import java.util.List;

public class StayHistoryRQVO {

	private String adsCode;  //渠道编码
	private String password; //COL分配给渠道的密码
	private	Date startDate;  //到店日期
	private	Date endDate;    //离店日期
	private String language; //语言编码
	private List<String> confirmationIDs;//订单确认号（PMS返回的订单确认号）
	
	public String getAdsCode() {
		return adsCode;
	}
	public void setAdsCode(String adsCode) {
		this.adsCode = adsCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<String> getConfirmationIDs() {
		return confirmationIDs;
	}
	public void setConfirmationIDs(List<String> confirmationIDs) {
		this.confirmationIDs = confirmationIDs;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
}
