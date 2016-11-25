package com.ccm.api.model.hotel.vo;

import com.ccm.api.model.hotel.HotelSwitch;

/**
 * @author Water
 * 酒店开关
 */
public class HotelSwitchVO extends HotelSwitch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5952876556447719805L;
	
	private String hsId;   //HotelSwitch Id
	private String hotelId;// 酒店 Id
	private String chainId;
	private String hotelCode;
	private String chainCode;
	private Boolean isHardCancel;
	private Boolean isGenerates;
	private Boolean isUploadRateHeader;
	
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
	
}
