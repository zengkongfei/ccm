package com.ccm.api.model.roomQuota.vo;

import java.util.Date;

public class RoomAvailable{
    private String rsvtypeId;
    private String rsvtypeChannelId; 
    private Integer allotmentAvailable; //渠道可用保留房
    private Integer obAvailable; //可用ob房量
    private Integer hotelAvailable;//可用酒店物理房
    private Integer available;//总可用房
    private Date date;
    private String roomTypeCode;
    
    public Integer getAllotmentAvailable() {
        return allotmentAvailable;
    }
    public void setAllotmentAvailable(Integer allotment) {
        this.allotmentAvailable = allotment;
    }
    public Integer getObAvailable() {
        return obAvailable;
    }
    public void setObAvailable(Integer obAvailable) {
        this.obAvailable = obAvailable;
    }
    public Integer getHotelAvailable() {
        return hotelAvailable;
    }
    public void setHotelAvailable(Integer hotelAvailable) {
        this.hotelAvailable = hotelAvailable;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getAvailable() {
        return available;
    }
    public void setAvailable(Integer available) {
        this.available = available;
    }
    public String getRsvtypeId() {
        return rsvtypeId;
    }
    public void setRsvtypeId(String rsvtypeId) {
        this.rsvtypeId = rsvtypeId;
    }
    public String getRsvtypeChannelId() {
        return rsvtypeChannelId;
    }
    public void setRsvtypeChannelId(String rsvtypeChannelId) {
        this.rsvtypeChannelId = rsvtypeChannelId;
    }
    public String getRoomTypeCode() {
        return roomTypeCode;
    }
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
}