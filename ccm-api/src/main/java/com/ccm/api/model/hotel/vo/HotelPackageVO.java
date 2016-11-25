package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.model.hotel.HotelPackage;
import com.ccm.api.model.hotel.HotelPackageI18n;

/**
 * 酒店打包VO
 * @author edwin
 *
 */
public class HotelPackageVO extends HotelPackage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2657141660783587122L;
	
	private String  hotelPackageMId ;//主键
	private String  language;
	private String  packageName;     //包名
	private String  description;     //描述
	private String  hotelCode; //酒店代码
	private String  packageIds; //打包服务Ids
	private String  packageCode; //打包服务Code
	private String  packageCodes; //打包服务CODEs
	private List<String> packageIdList; 
	private List<HotelPackageI18n> hotelPackageI18nList; //多语言列表
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	
	public String getPackageIds() {
		return packageIds;
	}
	public void setPackageIds(String packageIds) {
		this.packageIds = packageIds;
	}
	
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	
	public String getPackageCodes() {
		return packageCodes;
	}
	public void setPackageCodes(String packageCodes) {
		this.packageCodes = packageCodes;
	}
	
	public List<String> getPackageIdList() {
		if(this.packageIdList!=null&&this.packageIdList.size()>0)
			return this.packageIdList;
		
		if(StringUtils.isNotBlank(this.packageIds)){
			List<String> packageIdList = new ArrayList<String>();
			String[] packageIdArry = this.packageIds.split(",");
			for (String packageId : packageIdArry) {
				packageIdList.add(packageId);
			}
			return packageIdList;
		}
		return packageIdList;
	}
	public void setPackageIdList(List<String> packageIdList) {
		this.packageIdList = packageIdList;
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
	public List<HotelPackageI18n> getHotelPackageI18nList() {
		return hotelPackageI18nList;
	}
	public void setHotelPackageI18nList(List<HotelPackageI18n> hotelPackageI18nList) {
		this.hotelPackageI18nList = hotelPackageI18nList;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	
}
