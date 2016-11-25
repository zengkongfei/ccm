package com.ccm.api.model.hotel;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;

public class CreditLimit  extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7179572670626082708L;
	@Id
	private String creditLimitId;
	private String groupName;
	private String channelId;
	private String channelCode;
	private BigDecimal originalCreditLimit;
	private BigDecimal minLimit;
	private BigDecimal emailLimit;
	private BigDecimal minLimitPct;
	private BigDecimal emailLimitPct;
	private String hotelEmail;
	private String channelEmail;
	private BigDecimal totalRoomRev;
	private BigDecimal income;
	private BigDecimal variable=new BigDecimal("0");
	private Boolean hasSentEmailLimit;
	private Boolean hasSentMinLimit;
	public Boolean getHasSentEmailLimit() {
		return hasSentEmailLimit;
	}
	public void setHasSentEmailLimit(Boolean hasSentEmailLimit) {
		this.hasSentEmailLimit = hasSentEmailLimit;
	}
	public Boolean getHasSentMinLimit() {
		return hasSentMinLimit;
	}
	public void setHasSentMinLimit(Boolean hasSentMinLimit) {
		this.hasSentMinLimit = hasSentMinLimit;
	}
	public BigDecimal getMinLimitPct() {
		return minLimitPct;
	}
	public void setMinLimitPct(BigDecimal minLimitPct) {
		this.minLimitPct = minLimitPct;
	}
	public BigDecimal getEmailLimitPct() {
		return emailLimitPct;
	}
	public void setEmailLimitPct(BigDecimal emailLimitPct) {
		this.emailLimitPct = emailLimitPct;
	}
	public BigDecimal getVariable() {
		return variable;
	}
	public void setVariable(BigDecimal variable) {
		this.variable = variable;
	}
	public String getHotelEmail() {
		return hotelEmail;
	}
	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}
	public String getChannelEmail() {
		return channelEmail;
	}
	public void setChannelEmail(String channelEmail) {
		this.channelEmail = channelEmail;
	}
	public String getCreditLimitId() {
		return creditLimitId;
	}
	public void setCreditLimitId(String creditLimitId) {
		this.creditLimitId = creditLimitId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public BigDecimal getOriginalCreditLimit() {
		return originalCreditLimit;
	}
	public void setOriginalCreditLimit(BigDecimal originalCreditLimit) {
		this.originalCreditLimit = originalCreditLimit;
	}
	public BigDecimal getMinLimit() {
		return minLimit;
	}
	public void setMinLimit(BigDecimal minLimit) {
		this.minLimit = minLimit;
	}
	public BigDecimal getEmailLimit() {
		return emailLimit;
	}
	public void setEmailLimit(BigDecimal emailLimit) {
		this.emailLimit = emailLimit;
	}
	public BigDecimal getTotalRoomRev() {
		return totalRoomRev;
	}
	public void setTotalRoomRev(BigDecimal totalRoomRev) {
		this.totalRoomRev = totalRoomRev;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
}
