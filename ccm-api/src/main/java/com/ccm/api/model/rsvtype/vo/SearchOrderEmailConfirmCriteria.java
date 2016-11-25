package com.ccm.api.model.rsvtype.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class SearchOrderEmailConfirmCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5405520639880427675L;

	private String masterId;
	private String emailAddress;
	private Date startDate;
	private Date endDate;
	private String emailType;
	private String source;
	private boolean excelFlag = false;// 导出标志

	private List<String> hotelCodes;
	private List<String> channelCodes;
	private List<String> statusList;

	
	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public List<String> getHotelCodes() {
		return hotelCodes;
	}

	public void setHotelCodes(List<String> hotelCodes) {
		this.hotelCodes = hotelCodes;
	}

	public List<String> getChannelCodes() {
		return channelCodes;
	}

	public void setChannelCodes(List<String> channelCodes) {
		this.channelCodes = channelCodes;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public boolean isExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SearchOrderEmailConfirmCriteria [masterId=" + masterId
				+ ", emailAddress=" + emailAddress + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", emailType=" + emailType
				+ ", source=" + source + ", excelFlag=" + excelFlag
				+ ", hotelCodes=" + hotelCodes + ", channelCodes="
				+ channelCodes + ", statusList=" + statusList + "]";
	}


}
