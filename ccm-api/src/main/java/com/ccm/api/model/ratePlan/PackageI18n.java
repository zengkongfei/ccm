package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 打包服务(多语言)
 * @author carr
 *
 */
public class PackageI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3796762761788810584L;
	
	private String packageMId;//序号        
	private String packageId;//打包服务序号
	private String language;//语言         
	private String packageName;//服务名称  
	private String description;//描述 
	private String message;//提醒 
	
	public String getPackageMId() {
		return packageMId;
	}
	public void setPackageMId(String packageMId) {
		this.packageMId = packageMId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "PackageI18n [packageMId=" + packageMId + ", packageId="
				+ packageId + ", language=" + language + ", packageName="
				+ packageName + ", description=" + description + ", message="
				+ message + "]";
	}
}
