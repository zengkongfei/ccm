/**
 * 
 */
package com.ccm.api.model.log;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class CustomLog extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1175646623758773888L;

	private String customLogId;// 主键
	private String customId;
	private String hotelCode;
	private String name;
	private String type;
	private String corpIATANo;
	private String accessCode;
	private String business;
	private String mobile;
	private String fax;
	private String address;
	private String email;
	
	private String originalCreditLimit;
	private String totalRoomRev;
	
	private String income;  //由receivable累加
	private String receivable;
	private String minLimit;
	
	private String balance;//由其他字段计算获得
	
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCustomLogId() {
		return customLogId;
	}

	public void setCustomLogId(String customLogId) {
		this.customLogId = customLogId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getReceivable() {
		return receivable;
	}

	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}

	public String getBusiness() {
		return business;
	}

	public String getOriginalCreditLimit() {
		return originalCreditLimit;
	}

	public void setOriginalCreditLimit(String originalCreditLimit) {
		this.originalCreditLimit = originalCreditLimit;
	}

	public String getTotalRoomRev() {
		return totalRoomRev;
	}

	public void setTotalRoomRev(String totalRoomRev) {
		this.totalRoomRev = totalRoomRev;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	

	public String getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(String minLimit) {
		this.minLimit = minLimit;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCorpIATANo() {
		return corpIATANo;
	}

	public void setCorpIATANo(String corpIATANo) {
		this.corpIATANo = corpIATANo;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	@Override
	public String toString() {
		return "CustomLog [customLogId=" + customLogId + ", customId="
				+ customId + ", hotelCode=" + hotelCode + ", name=" + name
				+ ", type=" + type + ", corpIATANo=" + corpIATANo
				+ ", accessCode=" + accessCode + ", business=" + business
				+ ", mobile=" + mobile + ", fax=" + fax + ", address="
				+ address + ", email=" + email + ", originalCreditLimit="
				+ originalCreditLimit + ", totalRoomRev=" + totalRoomRev
				+ ", income=" + income + ", receivable=" + receivable
				+ ", minLimit=" + minLimit + ", balance=" + balance + "]";
	}

	
}
