package com.ccm.api.model.rsvtype.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class UsageCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2096737217286451864L;

	private Date resvDate;	//预定日期
	private Date resvDateBegin;	
	private Date resvDateEnd;

	private List<String> hotelIdList;	//酒店
	private List<String> channelIdList;	//渠道代码
	private List<String> roomTypeList;	//房型代码
	
	public Date getResvDateBegin() {
		return resvDateBegin;
	}
	public void setResvDateBegin(Date resvDateBegin) {
		this.resvDateBegin = resvDateBegin;
	}
	public Date getResvDateEnd() {
		return resvDateEnd;
	}
	public void setResvDateEnd(Date resvDateEnd) {
		this.resvDateEnd = resvDateEnd;
	}
	public Date getResvDate() {
		return resvDate;
	}
	public void setResvDate(Date resvDate) {
		this.resvDate = resvDate;
	}
	public List<String> getHotelIdList() {
		return hotelIdList;
	}
	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}
	public List<String> getChannelIdList() {
		return channelIdList;
	}
	public void setChannelIdList(List<String> channelIdList) {
		this.channelIdList = channelIdList;
	}
	public List<String> getRoomTypeList() {
		return roomTypeList;
	}
	public void setRoomTypeList(List<String> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}
	
}
