package com.ccm.api.dao.pms.form;

public class InterfacesForm {
	//下面是给上海这边调接口用的,参数名称和方法里面的一样
	private String roomtype;
	private String fromDate;//原来是Date型的
	private String toDate;//原来是Date型的
	private Double price;
	private Double weekendPrice;
	private int roomSum;
	//---------------------
	public String getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getWeekendPrice() {
		return weekendPrice;
	}
	public void setWeekendPrice(Double weekendPrice) {
		this.weekendPrice = weekendPrice;
	}
	public int getRoomSum() {
		return roomSum;
	}
	public void setRoomSum(int roomSum) {
		this.roomSum = roomSum;
	}
}
