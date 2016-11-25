package com.ccm.api.service.system.pushData;

public class RoomTypeSegment {
    private String roomTypeCode;
    private int roomsAvailable;
    public String getRoomTypeCode() {
        return roomTypeCode;
    }
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
    public int getRoomsAvailable() {
        return roomsAvailable;
    }
    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }
    
}
