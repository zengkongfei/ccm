package com.ccm.api.model.rsvtype;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;

public class RsvChangeInfo extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6606678921269203973L;
	@Id
	private String rsvChangeInfoId;// 主键
	private String newRoomTypeCode;// 新房型号码
	private String oldRoomTypeCode;// 老房型号码
	private String hotelCode;// 酒店号码
	private Date oldCheckInDate;// 老订单入住时间
	private Date newCheckInDate;// 新订单入住时间
	private Date oldCheckOutDate;// 老订单结帐时间
	private Date newCheckOutDate;// 老订单结帐时间
	private int oldRsvQty;// 老订单预订数量
	private int newRsvQty;// 新订单预订数量
	private String rateplanCode;//房价码
	private String oldRateplanCode;//老房价码
	private String channelId;//渠道id
	private Date orderCreatedDate;//订单创建日期
	private Boolean isSupplierRateCode=false;//是否是验证房价
	private Boolean sendOccupyFreeSellAvai=false;//有保留房，占用酒店自由房量
	
	public Boolean getSendOccupyFreeSellAvai() {
		return sendOccupyFreeSellAvai;
	}

	public void setSendOccupyFreeSellAvai(Boolean sendOccupyFreeSellAvai) {
		this.sendOccupyFreeSellAvai = sendOccupyFreeSellAvai;
	}

	public Boolean getIsSupplierRateCode() {
		return isSupplierRateCode;
	}

	public void setIsSupplierRateCode(Boolean isSupplierRateCode) {
		this.isSupplierRateCode = isSupplierRateCode;
	}

	public Date getOrderCreatedDate() {
		return orderCreatedDate;
	}

	public void setOrderCreatedDate(Date orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOldRateplanCode() {
		return oldRateplanCode;
	}

	public void setOldRateplanCode(String oldRateplanCode) {
		this.oldRateplanCode = oldRateplanCode;
	}

	private String channelCode;//渠道号码
	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getRateplanCode() {
		return rateplanCode;
	}

	public void setRateplanCode(String rateplanCode) {
		this.rateplanCode = rateplanCode;
	}

	public String getRsvChangeInfoId() {
		return rsvChangeInfoId;
	}

	public void setRsvChangeInfoId(String rsvChangeInfoId) {
		this.rsvChangeInfoId = rsvChangeInfoId;
	}

	public String getNewRoomTypeCode() {
		return newRoomTypeCode;
	}

	public void setNewRoomTypeCode(String newRoomTypeCode) {
		this.newRoomTypeCode = newRoomTypeCode;
	}

	public String getOldRoomTypeCode() {
		return oldRoomTypeCode;
	}

	public void setOldRoomTypeCode(String oldRoomTypeCode) {
		this.oldRoomTypeCode = oldRoomTypeCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Date getOldCheckInDate() {
		return oldCheckInDate;
	}

	public void setOldCheckInDate(Date oldCheckInDate) {
		this.oldCheckInDate = oldCheckInDate;
	}

	public Date getNewCheckInDate() {
		return newCheckInDate;
	}

	public void setNewCheckInDate(Date newCheckInDate) {
		this.newCheckInDate = newCheckInDate;
	}

	public Date getOldCheckOutDate() {
		return oldCheckOutDate;
	}

	public void setOldCheckOutDate(Date oldCheckOutDate) {
		this.oldCheckOutDate = oldCheckOutDate;
	}

	public Date getNewCheckOutDate() {
		return newCheckOutDate;
	}

	public void setNewCheckOutDate(Date newCheckOutDate) {
		this.newCheckOutDate = newCheckOutDate;
	}

	public int getOldRsvQty() {
		return oldRsvQty;
	}

	public void setOldRsvQty(int oldRsvQty) {
		this.oldRsvQty = oldRsvQty;
	}

	public int getNewRsvQty() {
		return newRsvQty;
	}

	public void setNewRsvQty(int newRsvQty) {
		this.newRsvQty = newRsvQty;
	}

}
