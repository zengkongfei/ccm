package com.ccm.api.model.sell.vo;

import java.util.Arrays;
import java.util.Date;

public class ProductionVO {

	private String hotelId;
	private String chainCode;//集团代码
	private String hotelCode;//酒店代码
	private String[] channelCodeArray;//多个渠道代码
	private String[] ratePlanCodeArray;//多个房价代码
	private String[] roomTypeCodeArray;//多个房型代码
	private String[] dateArray;//多个日期
	private String channelCode;//渠道代码
	private String ratePlanCode;//房价代码
	private String roomTypeCode;//房型代码
	private String date;//日期
	private Date startDate;//开始日期
	private Date endDate;//截止日期   
	private String weeks;//星期
	private Integer onOff;//开关房
	
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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
	public String[] getChannelCodeArray() {
		return channelCodeArray;
	}
	public void setChannelCodeArray(String[] channelCodeArray) {
		this.channelCodeArray = channelCodeArray;
	}
	public String[] getRatePlanCodeArray() {
		return ratePlanCodeArray;
	}
	public void setRatePlanCodeArray(String[] ratePlanCodeArray) {
		this.ratePlanCodeArray = ratePlanCodeArray;
	}
	public String[] getRoomTypeCodeArray() {
		return roomTypeCodeArray;
	}
	public void setRoomTypeCodeArray(String[] roomTypeCodeArray) {
		this.roomTypeCodeArray = roomTypeCodeArray;
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
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public Integer getOnOff() {
		return onOff;
	}
	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}
	
	public String[] getDateArray() {
		return dateArray;
	}
	public void setDateArray(String[] dateArray) {
		this.dateArray = dateArray;
	}
	@Override
	public String toString() {
		return "ProductionVO [chainCode=" + chainCode + ", hotelCode="
				+ hotelCode + ", channelCodeArray="
				+ Arrays.toString(channelCodeArray) + ", ratePlanCodeArray="
				+ Arrays.toString(ratePlanCodeArray) + ", roomTypeCodeArray="
				+ Arrays.toString(roomTypeCodeArray) + ", channelCode="
				+ channelCode + ", ratePlanCode=" + ratePlanCode
				+ ", roomTypeCode=" + roomTypeCode + ", date=" + date
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", weeks=" + weeks + ", onOff=" + onOff + "]";
	}
}
