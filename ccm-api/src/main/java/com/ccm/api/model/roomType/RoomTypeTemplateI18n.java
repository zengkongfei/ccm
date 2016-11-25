package com.ccm.api.model.roomType;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型模板
 * 
 * @author carr
 * 
 */
public class RoomTypeTemplateI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9083144094460506561L;

	private String roomTypeTemplateMId; //多语言表ID
	
	private String roomTypeTemplateId; //模板ID
	
	private String language;//语言        
	
	private String roomTypeTemplateName;       //模板名称
	
	private String description;//描述     
	

	public String getRoomTypeTemplateId() {
		return roomTypeTemplateId;
	}

	public void setRoomTypeTemplateId(String roomTypeTemplateId) {
		this.roomTypeTemplateId = roomTypeTemplateId;
	}
	

	public String getRoomTypeTemplateMId() {
		return roomTypeTemplateMId;
	}

	public void setRoomTypeTemplateMId(String roomTypeTemplateMId) {
		this.roomTypeTemplateMId = roomTypeTemplateMId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoomTypeTemplateName() {
		return roomTypeTemplateName;
	}

	public void setRoomTypeTemplateName(String roomTypeTemplateName) {
		this.roomTypeTemplateName = roomTypeTemplateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoomTypeTemplateI18n [roomTypeTemplateMId="
				+ roomTypeTemplateMId + ", roomTypeTemplateId="
				+ roomTypeTemplateId + ", language=" + language
				+ ", roomTypeTemplateName=" + roomTypeTemplateName
				+ ", description=" + description + "]";
	}

	
}
