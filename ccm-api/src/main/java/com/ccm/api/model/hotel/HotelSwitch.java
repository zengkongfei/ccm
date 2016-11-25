package com.ccm.api.model.hotel;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Water
 * 酒店开关
 */
public class HotelSwitch extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5952876556447719805L;
	
	private String hsId;   //HotelSwitch Id
	private String hotelId;// 酒店 Id
	private String chainId;
	private String hotelCode;
	private String chainCode;
	//默认为false
	private Boolean isHardCancel=false;
	private Boolean isGenerates=false;
	private Boolean isUploadRateHeader=false;
	private Boolean isAcceptRavr=false;
	private Boolean isAcceptRavl=false;
	private Boolean isMask=false;//订单详情显示，信用卡是否掩码
	//默认为true
	private Boolean isMonitorADSPending=true; //是否监控等待消息警示 Monitor ADS Pending
	
	private Boolean isDiscount;//订单是否优惠
	
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
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
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
	
	
	public Boolean getIsAcceptRavr() {
		return isAcceptRavr;
	}
	public void setIsAcceptRavr(Boolean isAcceptRavr) {
		this.isAcceptRavr = isAcceptRavr;
	}
	public Boolean getIsAcceptRavl() {
		return isAcceptRavl;
	}
	public void setIsAcceptRavl(Boolean isAcceptRavl) {
		this.isAcceptRavl = isAcceptRavl;
	}
	public Boolean getIsMask() {
		return isMask;
	}
	public void setIsMask(Boolean isMask) {
		this.isMask = isMask;
	}
	public Boolean getIsDiscount() {
		if(isDiscount==null){
			return false;
		}
		return isDiscount;
	}
	public void setIsDiscount(Boolean isDiscount) {
		this.isDiscount = isDiscount;
	}
	@Override
	public String toString() {
		return "HotelSwitch [hsId=" + hsId + ", hotelId=" + hotelId + ", chainId=" + chainId + ", hotelCode=" + hotelCode + ", chainCode=" + chainCode + ", isHardCancel=" + isHardCancel + ", isGenerates=" + isGenerates + ", isUploadRateHeader=" + isUploadRateHeader + ", isAcceptRavr=" + isAcceptRavr + ", isAcceptRavl=" + isAcceptRavl + ", isMask=" + isMask + ", isMonitorADSPending=" + isMonitorADSPending + ", isDiscount=" + isDiscount + ", createdTime=" + createdTime + ", lastModifyTime=" + lastModifyTime + "]";
	}
	
	
}
