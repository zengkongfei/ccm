package com.ccm.api.model.ws.vo.fetchCalendar;

import java.util.Date;

public class FetchCalendarRQVO {
	private String channelCode;
	private String channelId;// 用于查询房价日历
	private String chainCode;// 集团编码
	private String hotelCode;// 酒店代码
	private String roomTypeCode;// 房型代码
	private String ratePlanCode;// 房价代码
	private Date startDate;// 开始日期
	private Date endDate;// 结束日期
	private String ageQualifyingCode;// 客人代码
	private Integer numberOfRooms;// 房间数
	private Integer count;// 客人总数

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public String getAgeQualifyingCode() {
		return ageQualifyingCode;
	}

	public void setAgeQualifyingCode(String ageQualifyingCode) {
		this.ageQualifyingCode = ageQualifyingCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	@Override
	public String toString() {
		return "FetchCalendarRQVO [channelCode=" + channelCode + ", channelId=" + channelId + ", chainCode=" + chainCode + ", hotelCode=" + hotelCode + ", roomTypeCode=" + roomTypeCode + ", ratePlanCode=" + ratePlanCode + ", startDate=" + startDate + ", endDate=" + endDate + ", ageQualifyingCode=" + ageQualifyingCode + ", numberOfRooms=" + numberOfRooms + ", count=" + count + "]";
	}

}
