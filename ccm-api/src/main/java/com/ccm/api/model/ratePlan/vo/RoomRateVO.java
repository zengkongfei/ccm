package com.ccm.api.model.ratePlan.vo;

import java.util.List;

import com.ccm.api.model.ratePlan.RoomPackage;
import com.ccm.api.model.ratePlan.RoomRate;

public class RoomRateVO extends RoomRate implements Cloneable{

    private static final long serialVersionUID = -3608722755411918477L;

    private String roomTypeName;
    private List<RoomPackage> roomPackageList;
    private String roomTypeCode;
    
    @Override
	public RoomRateVO clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (RoomRateVO)super.clone();
	}
	public String getRoomTypeName() {
        return roomTypeName;
    }
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
    public List<RoomPackage> getRoomPackageList() {
        return roomPackageList;
    }
    public void setRoomPackageList(List<RoomPackage> roomPackageList) {
        this.roomPackageList = roomPackageList;
    }
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
}
