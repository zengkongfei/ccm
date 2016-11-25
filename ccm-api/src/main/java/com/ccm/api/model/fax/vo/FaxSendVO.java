package com.ccm.api.model.fax.vo;

import com.ccm.api.model.fax.FaxSend;

public class FaxSendVO extends FaxSend{
	private String pmsId;//酒店预订号
	private String cancelId;//取消号
	private String sta;//消息类型
	private String hotelCode;
	public String getPmsId() {
		return pmsId;
	}
	public void setPmsId(String pmsId) {
		this.pmsId = pmsId;
	}
	public String getCancelId() {
		return cancelId;
	}
	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	
	
}
