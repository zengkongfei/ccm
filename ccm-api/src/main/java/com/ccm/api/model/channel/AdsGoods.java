package com.ccm.api.model.channel;

public class AdsGoods {
	private String hotelId;
	private String hotelCode;
	private String roomTypeCode;
	private String channelCode;
	private String ratePlanCode;
	private Boolean forcePush=false;
	
	public Boolean getForcePush() {
		return forcePush;
	}
	public void setForcePush(Boolean forcePush) {
		this.forcePush = forcePush;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	@Override
	public String toString() {
		return "AdsGoods [hotelId=" + hotelId + ", hotelCode=" + hotelCode
				+ ", roomTypeCode=" + roomTypeCode + ", channelCode="
				+ channelCode + ", ratePlanCode=" + ratePlanCode
				+ ", forcePush=" + forcePush + "]";
	}
	
	
}
