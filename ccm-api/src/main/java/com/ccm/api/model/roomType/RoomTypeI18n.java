package com.ccm.api.model.roomType;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型(多语言)
 * @author carr
 *
 */
public class RoomTypeI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8594368784102988653L;

	private String roomTypeMId;//序号     
	private String roomTypeId;//房型序号  
	private String language;//语言        
	private String roomTypeName;//房型名称
	private String description;//描述     
	private String babyName;// 宝贝名称
	private String guide;// 购买须知
	private String receipt_other_type_desc;// 发票类型为其他时的发票描述,不能超过30个字
	private String receipt_info;// 发票说明，不能超过100个字
	
	public String getRoomTypeMId() {
		return roomTypeMId;
	}
	public void setRoomTypeMId(String roomTypeMId) {
		this.roomTypeMId = roomTypeMId;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBabyName() {
		return babyName;
	}
	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public String getReceipt_other_type_desc() {
		return receipt_other_type_desc;
	}
	public void setReceipt_other_type_desc(String receipt_other_type_desc) {
		this.receipt_other_type_desc = receipt_other_type_desc;
	}
	public String getReceipt_info() {
		return receipt_info;
	}
	public void setReceipt_info(String receipt_info) {
		this.receipt_info = receipt_info;
	}
	
	@Override
	public String toString() {
		return "RoomTypeI18n [roomTypeMId=" + roomTypeMId + ", roomTypeId="
				+ roomTypeId + ", language=" + language + ", roomTypeName="
				+ roomTypeName + ", description=" + description + ", babyName="
				+ babyName + ", guide=" + guide + ", receipt_other_type_desc="
				+ receipt_other_type_desc + ", receipt_info=" + receipt_info
				+ "]";
	}
}
