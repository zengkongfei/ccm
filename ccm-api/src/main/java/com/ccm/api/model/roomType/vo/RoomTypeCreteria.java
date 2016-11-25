package com.ccm.api.model.roomType.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class RoomTypeCreteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8705212783994864647L;

	private String hotelId;// 酒店序号
	private String roomTypeCode;// 房型代码
	private String roomTypeName;// 房型名称
	private String channelCode;// 渠道code
	private String userId;// 酒店创建者，用于B2B
	private List<String> hotelIdList;
	private String type;
	private String interfaceId;
	private String language;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getHotelIdList() {
		return hotelIdList;
	}

	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}


	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "RoomTypeCreteria [hotelId=" + hotelId + ", roomTypeCode=" + roomTypeCode + ", roomTypeName=" + roomTypeName + ", channelCode=" + channelCode + ", userId=" + userId + ", hotelIdList=" + hotelIdList + ", type=" + type + ", interfaceId=" + interfaceId + "]";
	}

	
}
