package com.ccm.api.model.rsvtype;

import java.util.Date;

public class RsvtypeOnline {
    
    private static final long serialVersionUID = -4510354323976569510L;
    private String rsvtypeOnlineId;
    private String chainCode;
    private String hotelCode;
    private String roomTypeCode;
    private String rateCode;
    private Float rate;
    private Integer available;
    private Date date;
    private String channelGoodsCode;
    private String actionCode;
    
    private Date checkInDate;
    private Date checkOutDate;
    
    
    public String getActionCode() {
        return actionCode;
    }
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
    public String getRsvtypeOnlineId() {
        return rsvtypeOnlineId;
    }
    public void setRsvtypeOnlineId(String rsvtypeOnlineId) {
        this.rsvtypeOnlineId = rsvtypeOnlineId;
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
    
    public Float getRate() {
        return rate;
    }
    public void setRate(Float rate) {
        this.rate = rate;
    }
    public Integer getAvailable() {
        return available;
    }
    public void setAvailable(Integer available) {
        this.available = available;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getRateCode() {
        return rateCode;
    }
    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }
    public String getChannelGoodsCode() {
        return channelGoodsCode;
    }
    public void setChannelGoodsCode(String channelGoodsCode) {
        this.channelGoodsCode = channelGoodsCode;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    @Override
    public String toString() {
        return "RsvtypeOnline [rsvtypeOnlineId=" + rsvtypeOnlineId
                + ", chainCode=" + chainCode + ", hotelCode=" + hotelCode
                + ", roomTypeCode=" + roomTypeCode + ", rateCode=" + rateCode
                + ", rate=" + rate + ", available=" + available + ", date="
                + date.toLocaleString() + ", channelGoodsCode=" + channelGoodsCode
                + ", actionCode=" + actionCode + ", checkInDate=" + checkInDate.toLocaleString()
                + ", checkOutDate=" + checkOutDate.toLocaleString() + "]";
    }
}
