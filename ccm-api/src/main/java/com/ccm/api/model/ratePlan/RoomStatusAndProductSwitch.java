package com.ccm.api.model.ratePlan;

import java.io.Serializable;
import java.util.Date;

public class RoomStatusAndProductSwitch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7434667883909229111L;
	
	private String channelCode;  //渠道代码
	private String hotelCode;    //酒店代码
	private String specialist;   //酒店维护专员
	private String roomTypeCode; //房型代码
	private String ratePlanCode; //房价代码
	private String type;         //类型(1:房态,2:产品)
	private Date date;           //日期
	
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
