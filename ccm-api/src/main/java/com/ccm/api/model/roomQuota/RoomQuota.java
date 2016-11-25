package com.ccm.api.model.roomQuota;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;


public class RoomQuota extends BaseObject {
	
	private static final long serialVersionUID = -3028424607788087087L;
	private String roomQuotaId;
	private String channelId;
	private String hotelId;
	private String guestRoomId;
	private Date beginDate;
	private Date endDate;
	private double generalPrice;
	private double weekPrice;
	private int amount;
	
	public String getRoomQuotaId() {
		return roomQuotaId;
	}
	public void setRoomQuotaId(String roomQuotaId) {
		this.roomQuotaId = roomQuotaId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getGuestRoomId() {
		return guestRoomId;
	}
	public void setGuestRoomId(String guestRoomId) {
		this.guestRoomId = guestRoomId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getGeneralPrice() {
		return generalPrice;
	}
	public void setGeneralPrice(double generalPrice) {
		this.generalPrice = generalPrice;
	}
	public double getWeekPrice() {
		return weekPrice;
	}
	public void setWeekPrice(double weekPrice) {
		this.weekPrice = weekPrice;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "RoomQuota [roomQuotaId=" + roomQuotaId + ", channelId="
				+ channelId + ", hotelId=" + hotelId + ", guestRoomId="
				+ guestRoomId + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", generalPrice=" + generalPrice + ", weekPrice="
				+ weekPrice + ", amount=" + amount + "]";
	}
}
