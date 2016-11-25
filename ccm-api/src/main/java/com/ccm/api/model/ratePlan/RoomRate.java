package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型房价
 * @author carr
 *
 */
public class RoomRate extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5474994601269901065L;
	
	private String roomRateId;//序号    
	private String roomTypeId;//房型序号
	private String rateDetailId;//房价明细序号
	
	public String getRoomRateId() {
		return roomRateId;
	}
	public void setRoomRateId(String roomRateId) {
		this.roomRateId = roomRateId;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRateDetailId() {
		return rateDetailId;
	}
	public void setRateDetailId(String rateDetailId) {
		this.rateDetailId = rateDetailId;
	}
	
	@Override
	public String toString() {
		return "RoomRate [roomRateId=" + roomRateId + ", roomTypeId="
				+ roomTypeId + ", rateDetailId=" + rateDetailId + "]";
	}
}
