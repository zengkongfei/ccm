package com.ccm.api.model.ws.vo.availability;

import java.util.Date;
import java.util.List;

public class AvailabilityRQVO {

	private List<String> ratePlanCodeList;// 房价编码
	private Integer numberOfRooms;// 房间数
	private Integer totalNumberOfGuests;// 人数，每间房的入住成人数
	private Date startDate;// 到店日期
	private Date endDate;// 离店日期
	private String channelCode;// 渠道编码
	private String channelId;// 用于查询房价日历
	private String chainCode;// 集团编码
	private String hotelCode;// 酒店编码
	private String hotelId;// 用于查询房价日历
	private List<String> roomTypeCodeList;// 房型编码
	private boolean summaryOnly;
	private String qualifyingIdType; // 客户类型
	private String qualifyingIdValue; // accessCode
	private String language; // 语言编码
	private String roomTypeIdsql; //房型idSQL

	public List<String> getRatePlanCodeList() {
		return ratePlanCodeList;
	}

	public void setRatePlanCodeList(List<String> ratePlanCodeList) {
		this.ratePlanCodeList = ratePlanCodeList;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getTotalNumberOfGuests() {
		return totalNumberOfGuests;
	}

	public void setTotalNumberOfGuests(Integer totalNumberOfGuests) {
		this.totalNumberOfGuests = totalNumberOfGuests;
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

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}

	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}

	public boolean isSummaryOnly() {
		return summaryOnly;
	}

	public void setSummaryOnly(boolean summaryOnly) {
		this.summaryOnly = summaryOnly;
	}

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

	public String getQualifyingIdType() {
		return qualifyingIdType;
	}

	public void setQualifyingIdType(String qualifyingIdType) {
		this.qualifyingIdType = qualifyingIdType;
	}

	public String getQualifyingIdValue() {
		return qualifyingIdValue;
	}

	public void setQualifyingIdValue(String qualifyingIdValue) {
		this.qualifyingIdValue = qualifyingIdValue;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoomTypeIdsql() {
		return roomTypeIdsql;
	}

	public void setRoomTypeIdsql(String roomTypeIdsql) {
		this.roomTypeIdsql = roomTypeIdsql;
	}

	@Override
	public String toString() {
		return "AvailabilityRQVO [ratePlanCodeList=" + ratePlanCodeList + ", numberOfRooms=" + numberOfRooms + ", totalNumberOfGuests=" + totalNumberOfGuests + ", startDate=" + startDate + ", endDate=" + endDate + ", channelCode=" + channelCode + ", channelId=" + channelId + ", chainCode=" + chainCode + ", hotelCode=" + hotelCode + ", hotelId=" + hotelId + ", roomTypeCodeList=" + roomTypeCodeList + ", summaryOnly=" + summaryOnly + ", qualifyingIdType=" + qualifyingIdType + ", qualifyingIdValue=" + qualifyingIdValue + ", language=" + language + "]";
	}

}
