package com.ccm.api.model.hotel.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class HotelCreteria extends SearchCriteria {

	private static final long serialVersionUID = -2623312015800158243L;

	private String chainId;// 集团
	private String brandId;// 品牌
	private String hotelCode;// 酒店代码
	private String hotelName;// 酒店名称
	private String languageCode; // 语言编码
	private String hotelCodeLike; // 酒店代码模糊查询
	private String email;

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getHotelCodeLike() {
		return hotelCodeLike;
	}

	public void setHotelCodeLike(String hotelCodeLike) {
		this.hotelCodeLike = hotelCodeLike;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "HotelCreteria [chainId=" + chainId + ", brandId=" + brandId + ", hotelCode=" + hotelCode + ", hotelName=" + hotelName + ", languageCode=" + languageCode + ", hotelCodeLike=" + hotelCodeLike + ", email=" + email + "]";
	}

}
