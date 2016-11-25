package com.ccm.api.model.guestroom.vo;

import java.util.ArrayList;
import java.util.List;

public class GuestRoomVo {
    
    private String hotelId;
    private String hotelName;
    private List roomList;
    private String channelHotelCode;
    
    public String getHotelId() {
        return hotelId;
    }
    public String getHotelName() {
        return hotelName;
    }
    public List getRoomList() {
        return roomList;
    }
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public void setRoomList(List roomList) {
        this.roomList = roomList;
    }
    public String getChannelHotelCode() {
        return channelHotelCode;
    }
    public void setChannelHotelCode(String channelHotelCode) {
        this.channelHotelCode = channelHotelCode;
    }
    
}
