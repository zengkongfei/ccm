/**
 * 
 */
package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class HotelI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237662257145622120L;

	/*** 一下为新表字段 */
	private String hotelMId;// 序号
	private String hotelId;// 酒店序号
	private String languageCode;// 语言
	private String hotelName;// 酒店名称
	private String hotelShortName;// 酒店简称
	private String description;

	private String hotelUsedName;// 酒店曾用名
	private String address;
	private String business;// 商业区（圈）长度不超过20字
	private String salutatory;  //欢迎词
	
	private String checkInTimeDesc; //入住时间描述
	private String checkOutTimeDesc; //退房时间描述
	private String cancelPolicyDesc; //取消政策描述
	private String payRemind;  		//支付提醒
	private String pickUpService; //接机服务提醒

	public String getHotelMId() {
		return hotelMId;
	}

	public void setHotelMId(String hotelMId) {
		this.hotelMId = hotelMId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelShortName() {
		return hotelShortName;
	}

	public void setHotelShortName(String hotelShortName) {
		this.hotelShortName = hotelShortName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getHotelUsedName() {
		return hotelUsedName;
	}

	public void setHotelUsedName(String hotelUsedName) {
		this.hotelUsedName = hotelUsedName;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	
	public String getSalutatory() {
		return salutatory;
	}

	public void setSalutatory(String salutatory) {
		this.salutatory = salutatory;
	}

	
	public String getCheckInTimeDesc() {
		return checkInTimeDesc;
	}

	public void setCheckInTimeDesc(String checkInTimeDesc) {
		this.checkInTimeDesc = checkInTimeDesc;
	}

	public String getCheckOutTimeDesc() {
		return checkOutTimeDesc;
	}

	public void setCheckOutTimeDesc(String checkOutTimeDesc) {
		this.checkOutTimeDesc = checkOutTimeDesc;
	}

	public String getCancelPolicyDesc() {
		return cancelPolicyDesc;
	}

	public void setCancelPolicyDesc(String cancelPolicyDesc) {
		this.cancelPolicyDesc = cancelPolicyDesc;
	}

	public String getPayRemind() {
		return payRemind;
	}

	public void setPayRemind(String payRemind) {
		this.payRemind = payRemind;
	}

	public String getPickUpService() {
		return pickUpService;
	}

	public void setPickUpService(String pickUpService) {
		this.pickUpService = pickUpService;
	}

	@Override
	public String toString() {
		return "Hoteli18n [hotelMId=" + hotelMId + ", hotelId=" + hotelId + ", languageCode=" + languageCode + ", hotelName=" + hotelName + ", hotelShortName=" + hotelShortName + ", description=" + description + ", hotelUsedName=" + hotelUsedName + ", address=" + address + ", business=" + business + "]";
	}

}
