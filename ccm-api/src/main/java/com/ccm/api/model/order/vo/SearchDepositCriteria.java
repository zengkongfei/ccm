package com.ccm.api.model.order.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class SearchDepositCriteria  extends SearchCriteria{

	private static final long serialVersionUID = -6895045452111725407L;
	
	private List<String> chainIdList;
	private List<String> hotelIdList;
	private List<String> accessCodeList;
	
	private String accessCode;
	private String profileName;
	private String corpIATANo;
	
	private boolean excelFlag = false;// 导出标志
	
	public List<String> getAccessCodeList() {
		return accessCodeList;
	}
	public void setAccessCodeList(List<String> accessCodeList) {
		this.accessCodeList = accessCodeList;
	}
	public List<String> getChainIdList() {
		return chainIdList;
	}
	public void setChainIdList(List<String> chainIdList) {
		this.chainIdList = chainIdList;
	}
	
	public boolean isExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	public List<String> getHotelIdList() {
		return hotelIdList;
	}
	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getCorpIATANo() {
		return corpIATANo;
	}
	public void setCorpIATANo(String corpIATANo) {
		this.corpIATANo = corpIATANo;
	}

}
