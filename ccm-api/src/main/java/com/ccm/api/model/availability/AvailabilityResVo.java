package com.ccm.api.model.availability;

import java.util.List;

import com.ccm.api.model.availability.res.RoomRate;

public class AvailabilityResVo {
    
    private String resultStatusFlag;
    private List<RoomRate> roomRateList;
    
    public String getResultStatusFlag() {
        return resultStatusFlag;
    }
    public void setResultStatusFlag(String resultStatusFlag) {
        this.resultStatusFlag = resultStatusFlag;
    }
    public List<RoomRate> getRoomRateList() {
        return roomRateList;
    }
    public void setRoomRateList(List<RoomRate> roomRateList) {
        this.roomRateList = roomRateList;
    }
}
