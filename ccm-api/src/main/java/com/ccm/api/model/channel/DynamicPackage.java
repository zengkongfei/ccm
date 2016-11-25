/**
 * 
 */
package com.ccm.api.model.channel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class DynamicPackage extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1524458147560636398L;

	private String dynamicPackageId;

	private String hotelId;

	private String channelId;

	private String packageId;

	// 查询结果使用
	private String channelCode;// 代码
	private String packageCode;

	public String getDynamicPackageId() {
		return dynamicPackageId;
	}

	public void setDynamicPackageId(String dynamicPackageId) {
		this.dynamicPackageId = dynamicPackageId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	@Override
	public String toString() {
		return "DynamicPackage [dynamicPackageId=" + dynamicPackageId + ", hotelId=" + hotelId + ", channelId=" + channelId + ", packageId=" + packageId + ", channelCode=" + channelCode + ", packageCode=" + packageCode + "]";
	}

}
