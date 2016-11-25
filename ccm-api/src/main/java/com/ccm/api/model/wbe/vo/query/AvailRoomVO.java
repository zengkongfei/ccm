package com.ccm.api.model.wbe.vo.query;

import java.util.Date;

public class AvailRoomVO {

	private String hotelId;    //酒店ID
	private String hotelCode;  //酒店代码
	private String chainId;    //集团ID
	private String chainCode;  //集团代码
	private String channelCode;//渠道code;
	private	Integer numberOfRooms;//房间数
	private	Integer totalNumberOfGuests;//人数，每间房的入住成人数
	private Integer totalNumberOfChilds;//人数，每间房的入住小孩数
	private	Date startDate;//到店日期
	private	Date endDate;//离店日期
	private String customType; //客户类型
	private String customCode; //客户代码
	private String promoCode;  //促销代码
	private String language;  //语言编码
	private String queryType; //查询类型(查询房价下的房型:ratePlan,查询房型下的房价:roomType)
	private String displayMode; //酒店显示方式
	private String masterId;    //masterId
	private Boolean enableModifyRN = true; //房间数是否可修改(true表示可以修改,false表示不可以修改)
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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
	public Integer getTotalNumberOfChilds() {
		return totalNumberOfChilds;
	}
	public void setTotalNumberOfChilds(Integer totalNumberOfChilds) {
		this.totalNumberOfChilds = totalNumberOfChilds;
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
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	public String getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public Boolean getEnableModifyRN() {
		return enableModifyRN;
	}
	public void setEnableModifyRN(Boolean enableModifyRN) {
		this.enableModifyRN = enableModifyRN;
	}
	
	@Override
	public String toString() {
		return "AvailRoomVO [hotelId=" + hotelId + ", hotelCode=" + hotelCode
				+ ", chainId=" + chainId + ", chainCode=" + chainCode
				+ ", channelCode=" + channelCode + ", numberOfRooms="
				+ numberOfRooms + ", totalNumberOfGuests="
				+ totalNumberOfGuests + ", totalNumberOfChilds="
				+ totalNumberOfChilds + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", customType=" + customType
				+ ", customCode=" + customCode + ", promoCode=" + promoCode
				+ ", language=" + language + ", queryType=" + queryType + "]";
	}
	
	
	
}
