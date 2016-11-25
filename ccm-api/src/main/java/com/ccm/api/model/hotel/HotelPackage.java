package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 酒店打包
 * @author carr
 *
 */
public class HotelPackage extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -971331476776898859L;
	
	private String 	hotelPackageId	;//	序号
	private String 	hotelId	;//	酒店序号
	private String 	packageId	;//	打包服务序号
	private Integer sortNum;	//排序编号
	
	public String getHotelPackageId() {
		return hotelPackageId;
	}
	public void setHotelPackageId(String hotelPackageId) {
		this.hotelPackageId = hotelPackageId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	@Override
	public String toString() {
		return "HotelPackage [hotelPackageId=" + hotelPackageId + ", hotelId="
				+ hotelId + ", packageId=" + packageId + "]";
	}
}
