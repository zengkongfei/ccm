package com.ccm.api.model.roomType;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型设备(多语言)
 * @author carr
 *
 */
public class AmenityI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6193650969158843904L;

	private String amenityMId;//序号    
	private String amenityId;//设备序号 
	private String language;//语言      
	private String description;//描述   
	
	public String getAmenityMId() {
		return amenityMId;
	}
	public void setAmenityMId(String amenityMId) {
		this.amenityMId = amenityMId;
	}
	public String getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(String amenityId) {
		this.amenityId = amenityId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "AmenityI18n [amenityMId=" + amenityMId + ", amenityId="
				+ amenityId + ", language=" + language + ", description="
				+ description + "]";
	}
}
