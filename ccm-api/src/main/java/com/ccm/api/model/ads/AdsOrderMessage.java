package com.ccm.api.model.ads;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 接收ads订单推送消息类
 */
public class AdsOrderMessage extends BaseObject{

	private static final long serialVersionUID = 4352228269529241166L;
	
    private String adsOrderId;
    
    private String chainCode;
    
    private String hotelCode;
    
    private String channelResId;
    
    private String confirmationNo;
    
    private String pmsResId;
    
    private String status;
    
    private String guestFirstName;
    
    private String guestLastName;
    
    private Date arrivalTime;
    
    private Date departureTime;
    
    private String roomType;
    
    private String rateCode;
    
    private String roomNo;
    
    private String channelCode;
    
    private Date resDate;
    
    private String remark;

	public String getAdsOrderId() {
		return adsOrderId;
	}

	public void setAdsOrderId(String adsOrderId) {
		this.adsOrderId = adsOrderId;
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

	public String getChannelResId() {
		return channelResId;
	}

	public void setChannelResId(String channelResId) {
		this.channelResId = channelResId;
	}

	public String getConfirmationNo() {
		return confirmationNo;
	}

	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

	public String getPmsResId() {
		return pmsResId;
	}

	public void setPmsResId(String pmsResId) {
		this.pmsResId = pmsResId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGuestFirstName() {
		return guestFirstName;
	}

	public void setGuestFirstName(String guestFirstName) {
		this.guestFirstName = guestFirstName;
	}

	public String getGuestLastName() {
		return guestLastName;
	}

	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
