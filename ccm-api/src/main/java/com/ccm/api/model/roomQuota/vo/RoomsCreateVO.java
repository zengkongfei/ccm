package com.ccm.api.model.roomQuota.vo;

import java.util.Date;

/**
 * 房态创建
 * @author carr
 *
 */
public class RoomsCreateVO {
	
	/**淘宝酒店ID*/
	private String hid;
	/**淘宝房型ID*/
	private String rid;
	/**淘宝商品ID*/
	private String gid;
	/**酒店名称*/
	private String hname;
	/**房型名称*/
	private String rname;
	/**商品名称*/
	private String gname;
	/**渠道ID*/
	private String channelId;
	/**
	 * 商品ID
	 * 渠道商品表，渠道商品主键
	 */
	private String roomId;
	/**酒店ID*/
	private String hotelId;
	/**房型ID*/
	private String guestRoomId;
	/**开始时间*/
	private Date beginDate;
	/**结束时间*/
	private Date endDate;
	/**平日价*/
	private double generalPrice;
	/**周末价*/
	private double weekPrice;
	/**房量*/
	private int amount;
	
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
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
		return "RoomsCreateVO [hid=" + hid
				+ ", rid=" + rid + ", gid=" + gid + ", hname=" + hname
				+ ", rname=" + rname + ", gname=" + gname + ", channelId="
				+ channelId + ", roomId=" + roomId + ", hotelId=" + hotelId
				+ ", guestRoomId=" + guestRoomId + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", generalPrice=" + generalPrice
				+ ", weekPrice=" + weekPrice + ", amount=" + amount + "]";
	}
}
