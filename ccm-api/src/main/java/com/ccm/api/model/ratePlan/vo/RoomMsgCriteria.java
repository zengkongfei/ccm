package com.ccm.api.model.ratePlan.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class RoomMsgCriteria extends SearchCriteria{
	private static final long serialVersionUID = 7944163432449991287L;
	private String channelCode;// 渠道代码
	private String chainCode;// 集团代码
	private String hotelCode;// 酒店代码
	private String roomTypeCode;// 房型代码
	private String rateCode;// 房价代码
	private String date;// 日期
	private Integer numberOfUnits; // 人数
	private String sendStatus;
	private String adsType;
	private Date startDate;
    private Date endDate;
    
    private List<String> channelCodeList; //渠道代码
    private List<String> chainCodeList;   //集团代码
    private List<String> hotelCodeList;   //酒店代码
    private List<String> adsTypeList;   //消息类型
    private List<String> roomTypeCodeList;   //房型代码
    private List<String> sendStatusList;   //推送状态
    
	public List<String> getChannelCodeList() {
		return channelCodeList;
	}
	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}
	public List<String> getChainCodeList() {
		return chainCodeList;
	}
	public void setChainCodeList(List<String> chainCodeList) {
		this.chainCodeList = chainCodeList;
	}
	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}
	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
	}
	public List<String> getAdsTypeList() {
		return adsTypeList;
	}
	public void setAdsTypeList(List<String> adsTypeList) {
		this.adsTypeList = adsTypeList;
	}
	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}
	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}
	
	public List<String> getSendStatusList() {
		return sendStatusList;
	}
	public void setSendStatusList(List<String> sendStatusList) {
		this.sendStatusList = sendStatusList;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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
	
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
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
	public String getAdsType() {
		return adsType;
	}
	public void setAdsType(String adsType) {
		this.adsType = adsType;
	}
}
