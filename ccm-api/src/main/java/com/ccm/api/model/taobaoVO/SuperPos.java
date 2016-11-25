package com.ccm.api.model.taobaoVO;

/**
 * 超级收款结构
 * 
 * @author Tata.Wang
 * 
 */
public class SuperPos {
	private String service = "alipay.mobile.web.mpos.collect";
	private String partner;
	private String inputCharset = "utf-8";
	private String signType = "MD5";
	private String sign;
	private String posInfo;
	private String returnUrl;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPosInfo() {
		return posInfo;
	}

	public void setPosInfo(String posInfo) {
		this.posInfo = posInfo;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
}
