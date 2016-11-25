package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class HotelPackageCreteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4038109493126907712L;
	
	private String hotelId; //酒店ID
	private String packageIds;
	private String packageCodes; //打包服务codes
	private String language;  //语言
	private Boolean issvcortax;
	private List<String> packageIdList;
	
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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
	
	
	public String getPackageIds() {
		return packageIds;
	}
	
	public void setPackageIds(String packageIds) {
		this.packageIds = packageIds;
	}
	public String getPackageCodes() {
		return packageCodes;
	}
	public void setPackageCodes(String packageCodes) {
		this.packageCodes = packageCodes;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Boolean getIssvcortax() {
		return issvcortax;
	}
	public void setIssvcortax(Boolean issvcortax) {
		this.issvcortax = issvcortax;
	}
	
	
}
