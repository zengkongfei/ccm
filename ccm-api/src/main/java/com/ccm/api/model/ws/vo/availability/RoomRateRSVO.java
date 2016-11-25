package com.ccm.api.model.ws.vo.availability;

import java.util.Date;

public class RoomRateRSVO {
	private String roomTypeCode;//房型代码
	private String currencyCode;//货币代码
	private String baseAmount;//首日房价
	private String totalAmount;//房价合计金额
	private Double totalRoomRateandpackages;//房价总价合计（含服务费），计算方法为：每日单价累加*房间数量 
	private Date postingDate;//房价对应日期
	private Double roomRateAndPackages;//单日总价合计
	private Double roomRateAndPackage;//单日房价
	private Integer available;//可用房量
	
	
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(String baseAmount) {
		this.baseAmount = baseAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getTotalRoomRateandpackages() {
		return totalRoomRateandpackages;
	}
	public void setTotalRoomRateandpackages(Double totalRoomRateandpackages) {
		this.totalRoomRateandpackages = totalRoomRateandpackages;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public Double getRoomRateAndPackages() {
		return roomRateAndPackages;
	}
	public void setRoomRateAndPackages(Double roomRateAndPackages) {
		this.roomRateAndPackages = roomRateAndPackages;
	}
	public Double getRoomRateAndPackage() {
		return roomRateAndPackage;
	}
	public void setRoomRateAndPackage(Double roomRateAndPackage) {
		this.roomRateAndPackage = roomRateAndPackage;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "RoomRateRSVO [roomTypeCode=" + roomTypeCode + ", currencyCode="
				+ currencyCode + ", baseAmount=" + baseAmount
				+ ", totalAmount=" + totalAmount
				+ ", totalRoomRateandpackages=" + totalRoomRateandpackages
				+ ", postingDate=" + postingDate + ", roomRateAndPackages="
				+ roomRateAndPackages + ", roomRateAndPackage="
				+ roomRateAndPackage + ", available=" + available + "]";
	}
}
