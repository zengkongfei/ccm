package com.ccm.api.model.roomType.vo;


import com.ccm.api.model.base.criteria.SearchCriteria;

public class RoomTypeTemplateCreteria extends SearchCriteria {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863156737865153513L;
	
	private String roomTypeId;// 房型序号
	private String roomTypeTemplateId;
	private String roomTypeTemplateCode;// 房型模板代码
	private String roomTypeTemplateName;// 房型模板名称
	private String userId;// 模板创建者
	private String language;
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	
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
	public String getRoomTypeTemplateName() {
		return roomTypeTemplateName;
	}
	public void setRoomTypeTemplateName(String roomTypeTemplateName) {
		this.roomTypeTemplateName = roomTypeTemplateName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "RoomTypeTemplateCreteria [roomTypeId=" + roomTypeId
				+ ", roomTypeTemplateId=" + roomTypeTemplateId
				+ ", roomTypeTemplateCode=" + roomTypeTemplateCode
				+ ", roomTypeTemplateName=" + roomTypeTemplateName
				+ ", userId=" + userId + ", language=" + language + "]";
	}
	
	
	

	
}
