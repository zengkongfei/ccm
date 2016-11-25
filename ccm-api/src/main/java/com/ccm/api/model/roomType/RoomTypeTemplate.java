package com.ccm.api.model.roomType;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型模板
 * 
 * @author carr
 * 
 */
public class RoomTypeTemplate extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4823715147780282367L;

	private String roomTypeTemplateId; //房型ID
	
	private String roomTypeTemplateCode;       //房型Code
	

	public String getRoomTypeTemplateId() {
		return roomTypeTemplateId;
	}

	public void setRoomTypeTemplateId(String roomTypeTemplateId) {
		this.roomTypeTemplateId = roomTypeTemplateId;
	}


	public String getRoomTypeTemplateCode() {
		return roomTypeTemplateCode;
	}

	public void setRoomTypeTemplateCode(String roomTypeTemplateCode) {
		this.roomTypeTemplateCode = roomTypeTemplateCode;
	}

	@Override
	public String toString() {
		return "RoomTypeTemplate [roomTypeTemplateId=" + roomTypeTemplateId + ", roomTypeTemplateCode=" + roomTypeTemplateCode + "]";
	}



}
