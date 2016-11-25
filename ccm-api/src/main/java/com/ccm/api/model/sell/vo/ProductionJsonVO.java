package com.ccm.api.model.sell.vo;

public class ProductionJsonVO {

	private String channelCode;//渠道代码
	private String chainCode;//集团代码
	private String hotelCode;//酒店代码
	private String roomTypeCode;//房型代码
	private String ratePlanCode;//房价代码
	private Integer onOff;//开关房状态
	private String date;//日期
	
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public Integer getOnOff() {
		return onOff;
	}
	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "ProductionJsonVO [channelCode=" + channelCode + ", hotelCode="
				+ hotelCode + ", roomTypeCode=" + roomTypeCode
				+ ", ratePlanCode=" + ratePlanCode + ", onOff=" + onOff
				+ ", date=" + date + "]";
	}
}
