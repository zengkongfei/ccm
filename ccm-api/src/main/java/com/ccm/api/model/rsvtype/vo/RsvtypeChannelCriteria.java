package com.ccm.api.model.rsvtype.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class RsvtypeChannelCriteria extends SearchCriteria{
	private static final long serialVersionUID = 8584880487562148501L;
	/**酒店ID*/
	private String hotelid;
	private String chainCode;
	/**日期*/
	private Date date;
	/**房型ID*/
	private String type;
	private String sendStatus;
	private String channelCode;
	private String hotelCode;
	private Date startDate;
    private Date endDate;
	
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
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
	@Override
	public String toString() {
		return "RsvtypeChannelCriteria [hotelid=" + hotelid + ", chainCode="
				+ chainCode + ", date=" + date + ", type=" + type
				+ ", sendStatus=" + sendStatus + ", channelCode=" + channelCode
				+ ", hotelCode=" + hotelCode + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
}
