package com.ccm.api.model.hotel.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;


/**
 * @author Water
 * 酒店开关
 */
public class HotelSwitchCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5952876556447719805L;
	
	private String hsId;   //HotelSwitch Id
	
	private String hotelId;// 酒店 Id
	private String chainId;
	private String hotelCode;
	private String chainCode;
	
	private List<String> hoteCodes;
	private List<String> chainCodes;
	
	private Boolean isHardCancel;
	private Boolean isGenerates;
	private Boolean isUploadRateHeader;
	private Boolean isMonitorADSPending; //是否监控等待消息警示 Monitor ADS Pending
	private Boolean isMask;
	private Date createdTime;//创建日期
	private Date lastModifyTime;//更新日期
	
	public Boolean getIsMonitorADSPending() {
		return isMonitorADSPending;
	}
	public void setIsMonitorADSPending(Boolean isMonitorADSPending) {
		this.isMonitorADSPending = isMonitorADSPending;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	public List<String> getChainCodes() {
		return chainCodes;
	}
	public void setChainCodes(List<String> chainCodes) {
		this.chainCodes = chainCodes;
	}
	public List<String> getHoteCodes() {
		return hoteCodes;
	}
	public void setHoteCodes(List<String> hoteCodes) {
		this.hoteCodes = hoteCodes;
	}
	public String getHsId() {
		return hsId;
	}
	public void setHsId(String hsId) {
		this.hsId = hsId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public Boolean getIsHardCancel() {
		return isHardCancel;
	}
	public void setIsHardCancel(Boolean isHardCancel) {
		this.isHardCancel = isHardCancel;
	}
	public Boolean getIsGenerates() {
		return isGenerates;
	}
	public void setIsGenerates(Boolean isGenerates) {
		this.isGenerates = isGenerates;
	}
	public Boolean getIsUploadRateHeader() {
		return isUploadRateHeader;
	}
	public void setIsUploadRateHeader(Boolean isUploadRateHeader) {
		this.isUploadRateHeader = isUploadRateHeader;
	}
	public Boolean getIsMask() {
		return isMask;
	}
	public void setIsMask(Boolean isMask) {
		this.isMask = isMask;
	}
	
}
