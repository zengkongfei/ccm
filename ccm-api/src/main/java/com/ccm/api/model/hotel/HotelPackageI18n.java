package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 酒店打包
 * @author carr
 *
 */
public class HotelPackageI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7335206540842992884L;
	private String  hotelPackageMId ;//主键
	private String 	hotelPackageId	;//	序号
	private String  language;        //语言
	private String  packageName;     //包名
	private String  description;     //描述
	
	public String getHotelPackageId() {
		return hotelPackageId;
	}
	public void setHotelPackageId(String hotelPackageId) {
		this.hotelPackageId = hotelPackageId;
	}
	public String getHotelPackageMId() {
		return hotelPackageMId;
	}
	public void setHotelPackageMId(String hotelPackageMId) {
		this.hotelPackageMId = hotelPackageMId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "HotelPackageI18n [hotelPackageMId=" + hotelPackageMId
				+ ", hotelPackageId=" + hotelPackageId + ", language="
				+ language + ", packageName=" + packageName + ", description="
				+ description + "]";
	}
	
}
