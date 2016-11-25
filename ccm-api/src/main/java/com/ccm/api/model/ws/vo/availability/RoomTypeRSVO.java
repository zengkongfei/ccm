package com.ccm.api.model.ws.vo.availability;

public class RoomTypeRSVO {
	private String roomTypeCode;//房型代码
	private String roomTypeDescription;//房型描述
	
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getRoomTypeDescription() {
		return roomTypeDescription;
	}
	public void setRoomTypeDescription(String roomTypeDescription) {
		this.roomTypeDescription = roomTypeDescription;
	}
	
	@Override
	public String toString() {
		return "RoomTypeRSVO [roomTypeCode=" + roomTypeCode
				+ ", roomTypeDescription=" + roomTypeDescription + "]";
	}
}
