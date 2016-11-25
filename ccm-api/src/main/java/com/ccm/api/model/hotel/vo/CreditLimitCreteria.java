package com.ccm.api.model.hotel.vo;

import java.math.BigDecimal;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class CreditLimitCreteria extends SearchCriteria{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4562992908790446572L;
	private String creditLimitId;
	private String groupName;
	private String channelId;
	private String channelCode;
	private BigDecimal originalCreditLimit;
	private BigDecimal minLimit;
	private BigDecimal emailLimit;
	private String hotelEmail;
	private String channelEmail;
	private BigDecimal totalRoomRev;
	private BigDecimal income;
	private BigDecimal variable=new BigDecimal("0");
	private String hotelId;
	private List<String> hoteIds;
	private boolean excelFlag = false;// 导出标志
	
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
	public BigDecimal getVariable() {
		return variable;
	}
	public void setVariable(BigDecimal variable) {
		this.variable = variable;
	}
	public List<String> getHoteIds() {
		return hoteIds;
	}
	public void setHoteIds(List<String> hoteIds) {
		this.hoteIds = hoteIds;
	}
	public boolean isExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	@Override
	public String toString() {
		return "CreditLimitCreteria [creditLimitId=" + creditLimitId + ", groupName=" + groupName + ", channelId=" + channelId + ", channelCode=" + channelCode + ", originalCreditLimit=" + originalCreditLimit + ", minLimit=" + minLimit + ", emailLimit=" + emailLimit + ", hotelEmail=" + hotelEmail + ", channelEmail=" + channelEmail + ", totalRoomRev=" + totalRoomRev + ", income=" + income + ", variable=" + variable + ", hotelId=" + hotelId + ", hoteIds=" + hoteIds + ", excelFlag=" + excelFlag + "]";
	}
	
}
