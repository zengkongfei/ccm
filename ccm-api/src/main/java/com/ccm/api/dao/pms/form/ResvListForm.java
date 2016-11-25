package com.ccm.api.dao.pms.form;

public class ResvListForm {
	private String fromDate="";
	private String toDate="";
	private String selectType="";//好把..这其实是预订状态
	private String roomType="";//这才是选择的房型
	private String isInit="false";
	
	private String roomno;
	private String accnt;
	private String autoInfo = "";
	private String isToday = "";
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getIsInit() {
		return isInit;
	}
	public void setIsInit(String isInit) {
		this.isInit = isInit;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public String getAutoInfo() {
		return autoInfo;
	}
	public void setAutoInfo(String autoInfo) {
		this.autoInfo = autoInfo;
	}
	public String getIsToday() {
		return isToday;
	}
	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}
}
