package com.ccm.api.model.roomType.vo;

import java.util.List;
import com.ccm.api.model.roomType.RoomTypeTemplate;
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;

/**
 * 房型模板
 * 
 * @author carr
 * 
 */
public class RoomTypeTemplateVO extends RoomTypeTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3163585623266123009L;
	

	private String roomTypeTemplateMId; //多语言表ID
	
	private String roomTypeTemplateId; //模板ID
	
	private String language;//语言        
	
	private String roomTypeTemplateName;       //模板名称
	
	private String description;//描述     
	
	private List<RoomTypeTemplateI18n> roomTypeTempI18nList;
	

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
	

	public List<RoomTypeTemplateI18n> getRoomTypeTempI18nList() {
		return roomTypeTempI18nList;
	}

	public void setRoomTypeTempI18nList(
			List<RoomTypeTemplateI18n> roomTypeTempI18nList) {
		this.roomTypeTempI18nList = roomTypeTempI18nList;
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
