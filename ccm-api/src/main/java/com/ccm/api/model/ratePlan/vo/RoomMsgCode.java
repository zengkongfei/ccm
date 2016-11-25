package com.ccm.api.model.ratePlan.vo;

import org.springframework.data.annotation.Id;


public class RoomMsgCode{
	@Id
	private String roomMsgCodeId;
	private String channelCode;
    private String chainCode;
    private String hotelCode;
    private String roomTypeCode;
	private Integer count;
	
	public String getRoomMsgCodeId() {
		return roomMsgCodeId;
	}
	public void setRoomMsgCodeId(String roomMsgCodeId) {
		this.roomMsgCodeId = roomMsgCodeId;
	}
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "RoomMsgCode [roomMsgCodeId=" + roomMsgCodeId + ", channelCode="
				+ channelCode + ", chainCode=" + chainCode + ", hotelCode="
				+ hotelCode + ", roomTypeCode=" + roomTypeCode + ", count="
				+ count + "] \n";
	}
}
