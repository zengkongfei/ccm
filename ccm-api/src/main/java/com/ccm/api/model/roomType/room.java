package com.ccm.api.model.roomType;

import com.ccm.api.model.base.BaseObject;

/**
 * 房间
 * @author carr
 *
 */
public class room extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7737619041559991933L;

	private String roomId;//序号                    
	private String roomTypeId;//房型序号            
	private String roomNo;//房间号                  
	private Integer floor;//所在楼层                 
	private Integer roomLocationCode;//位置          
	private Integer roomViewCode;//景观              
	private Boolean isNonSmoking;//是否无烟房        
	private Double sizeMeasurement;//面积           
	private Integer roomArchitectureCode;//房间结构  
	private Integer roomGender;//性别限定            
	private Boolean isShared;//是否可分享         
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getRoomLocationCode() {
		return roomLocationCode;
	}
	public void setRoomLocationCode(Integer roomLocationCode) {
		this.roomLocationCode = roomLocationCode;
	}
	public Integer getRoomViewCode() {
		return roomViewCode;
	}
	public void setRoomViewCode(Integer roomViewCode) {
		this.roomViewCode = roomViewCode;
	}
	public Boolean getIsNonSmoking() {
		return isNonSmoking;
	}
	public void setIsNonSmoking(Boolean isNonSmoking) {
		this.isNonSmoking = isNonSmoking;
	}
	public Double getSizeMeasurement() {
		return sizeMeasurement;
	}
	public void setSizeMeasurement(Double sizeMeasurement) {
		this.sizeMeasurement = sizeMeasurement;
	}
	public Integer getRoomArchitectureCode() {
		return roomArchitectureCode;
	}
	public void setRoomArchitectureCode(Integer roomArchitectureCode) {
		this.roomArchitectureCode = roomArchitectureCode;
	}
	public Integer getRoomGender() {
		return roomGender;
	}
	public void setRoomGender(Integer roomGender) {
		this.roomGender = roomGender;
	}
	public Boolean getIsShared() {
		return isShared;
	}
	public void setIsShared(Boolean isShared) {
		this.isShared = isShared;
	}
	
	@Override
	public String toString() {
		return "room [roomId=" + roomId + ", roomTypeId=" + roomTypeId
				+ ", roomNo=" + roomNo + ", floor=" + floor
				+ ", roomLocationCode=" + roomLocationCode + ", roomViewCode="
				+ roomViewCode + ", isNonSmoking=" + isNonSmoking
				+ ", sizeMeasurement=" + sizeMeasurement
				+ ", roomArchitectureCode=" + roomArchitectureCode
				+ ", roomGender=" + roomGender + ", isShared=" + isShared + "]";
	}
}
