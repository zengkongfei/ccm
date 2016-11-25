package com.ccm.api.model.channel;

import com.ccm.api.model.base.BaseObject;

/**
 * 静态消息推送 md5
 * @author chay
 *
 */
public class ChannelHotelMD5Info extends BaseObject {
	public static final String TYPE_HOTEL="1";//酒店md5
	public static final String TYPE_RATEPLAN="2";//房价md5
	public static final String TYPE_ROONTYPE="3";//房型Md5
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String channelHotelMD5InfoId;
	private String channelId;//渠道id
	private String hotelId;//酒店id
	private String ratePlanId;//房价id
	private String roomTypeId;//房型id
	private String hotelMd5;//酒店md5
	private String ratePlanMd5;//房价md5
	private String roomTypeMd5;//房型md5
	private String type;//1:酒店md5 2:房价md5 3:房型Md5
	public String getChannelHotelMD5InfoId() {
		return channelHotelMD5InfoId;
	}
	public void setChannelHotelMD5InfoId(String channelHotelMD5InfoId) {
		this.channelHotelMD5InfoId = channelHotelMD5InfoId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getHotelMd5() {
		return hotelMd5;
	}
	public void setHotelMd5(String hotelMd5) {
		this.hotelMd5 = hotelMd5;
	}
	public String getRatePlanMd5() {
		return ratePlanMd5;
	}
	public void setRatePlanMd5(String ratePlanMd5) {
		this.ratePlanMd5 = ratePlanMd5;
	}
	public String getRoomTypeMd5() {
		return roomTypeMd5;
	}
	public void setRoomTypeMd5(String roomTypeMd5) {
		this.roomTypeMd5 = roomTypeMd5;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	@Override
	public String toString() {
		return "ChannelHotelMD5Info [channelHotelMD5InfoId="
				+ channelHotelMD5InfoId + ", channelId=" + channelId
				+ ", hotelId=" + hotelId + ", ratePlanId=" + ratePlanId
				+ ", roomTypeId=" + roomTypeId + ", hotelMd5=" + hotelMd5
				+ ", ratePlanMd5=" + ratePlanMd5 + ", roomTypeMd5="
				+ roomTypeMd5 + ", type=" + type + "]";
	}
	
}
