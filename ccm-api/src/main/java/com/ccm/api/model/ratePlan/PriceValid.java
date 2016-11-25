package com.ccm.api.model.ratePlan;

import java.io.Serializable;
import java.util.Date;

public class PriceValid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6765029442165368210L;

	
	private String specialist;  //酒店维护专员
	private String hotelCode;   //酒店代码
	private String ratePlanCode;// 房价代码
	private String ratePlanName;// 房价名称
	private String roomTypeCode;// 房型代码
	private String roomTypeName;//房型名称
	private Date effectiveDate;     //sh日期
	private Date expireDate;       //结束日期
	private String weeks;          //星期
	
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getRatePlanName() {
		return ratePlanName;
	}
	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	@Override
	public String toString() {
		return "PriceValid [specialist=" + specialist + ", hotelCode="
				+ hotelCode + ", ratePlanCode=" + ratePlanCode
				+ ", ratePlanName=" + ratePlanName + ", roomTypeCode="
				+ roomTypeCode + ", roomTypeName=" + roomTypeName
				+ ", effectiveDate=" + effectiveDate + ", expireDate="
				+ expireDate + ", weeks=" + weeks + "]";
	}

}
