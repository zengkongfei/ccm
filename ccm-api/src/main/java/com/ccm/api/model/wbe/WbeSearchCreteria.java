package com.ccm.api.model.wbe;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.base.BaseObject;

public class WbeSearchCreteria extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3591589173299123183L;
	private String hotelId;
	private String hotelCode;
	private String accessCode;
	private String channelCode;
	private String channelId;
	private String arrDate;
	private String depDate;
	private Integer numberOfUnits;
	private List<String>roomTypeIds;
	private String language;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getArrDate() {
		return arrDate;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public List<String> getRoomTypeIds() {
		return roomTypeIds;
	}
	public void setRoomTypeIds(List<String> roomTypeIds) {
		this.roomTypeIds = roomTypeIds;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	@Override
	public String toString() {
		return "WbeSearchCreteria [hotelId=" + hotelId + ", hotelCode="
				+ hotelCode + ", accessCode=" + accessCode + ", channelCode="
				+ channelCode + ", channelId=" + channelId + ", arrDate="
				+ arrDate + ", depDate=" + depDate + ", numberOfUnits="
				+ numberOfUnits + ", roomTypeIds=" + roomTypeIds + "]";
	}
	
}
