package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 建筑风格
 * @author carr
 *
 */
public class ArchitecturalStyle extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6043335722696253917L;

	private String architecturalStyle;//序号
	private String hotelId;//酒店序号       
	private Integer styleCode;//风格类型     
	private String description;//描述       
	private Integer existsCode;//存在代码    
	
	public String getArchitecturalStyle() {
		return architecturalStyle;
	}
	public void setArchitecturalStyle(String architecturalStyle) {
		this.architecturalStyle = architecturalStyle;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public Integer getStyleCode() {
		return styleCode;
	}
	public void setStyleCode(Integer styleCode) {
		this.styleCode = styleCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExistsCode() {
		return existsCode;
	}
	public void setExistsCode(Integer existsCode) {
		this.existsCode = existsCode;
	}
	
	@Override
	public String toString() {
		return "ArchitecturalStyle [architecturalStyle=" + architecturalStyle
				+ ", hotelId=" + hotelId + ", styleCode=" + styleCode
				+ ", description=" + description + ", existsCode=" + existsCode
				+ "]";
	}
}
