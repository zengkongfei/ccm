package com.ccm.api.model.taobaoVO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 超级收款 业务参数部分
 * @author tata.wang
 *
 */
public class PosInfo {
	// 不可为空
	private String service = "m_web_request";
	private String seller;
	private String sellerLogonId;
	private String amount;
	private String outTradeNo;
	private String srcFrom = "m_web";
	
	// 可为空
	private String sellerName;
	private String indOrderNo;
	private String tradeMemo;
	private String payMode;
	private boolean payModeChangeable = true;
	private String payAccount;
	private boolean payAccountChangeable = true;
	private String payBankcard;
	private boolean payBankcardChangeable = true;
	private String mposBankcardInfoName;
	private boolean mposBankcardInfoNameChangeable = true;
	private String mposBankcardInfoCertNo;
	private boolean mposBankcardInfoCertNoChangeable = true;
	private String mposBankcardInfoExpiredDateMonth;
	private boolean mposBankcardInfoExpiredDateMonthChangeable = true;
	private String mposBankcardInfoExpiredDateYear;
	private boolean mposBankcardInfoExpiredDateYearChangeable = true;
	private String mposBankcardInfoCvv2;
	private boolean mposBankcardInfoCvv2Changeable = true;
	private String mposBankcardInfoMobile;
	private boolean mposBankcardInfoMobileChangeable = true;
	private String merchantExtInfo;
	private String notifyUrl;
	private String royalty;
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getSellerLogonId() {
		return sellerLogonId;
	}
	public void setSellerLogonId(String sellerLogonId) {
		this.sellerLogonId = sellerLogonId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getSrcFrom() {
		return srcFrom;
	}
	public void setSrcFrom(String srcFrom) {
		this.srcFrom = srcFrom;
	}
	public String getIndOrderNo() {
		return indOrderNo;
	}
	public void setIndOrderNo(String indOrderNo) {
		this.indOrderNo = indOrderNo;
	}
	public String getTradeMemo() {
		return tradeMemo;
	}
	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public boolean isPayModeChangeable() {
		return payModeChangeable;
	}
	public void setPayModeChangeable(boolean payModeChangeable) {
		this.payModeChangeable = payModeChangeable;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public boolean isPayAccountChangeable() {
		return payAccountChangeable;
	}
	public void setPayAccountChangeable(boolean payAccountChangeable) {
		this.payAccountChangeable = payAccountChangeable;
	}
	public String getPayBankcard() {
		return payBankcard;
	}
	public void setPayBankcard(String payBankcard) {
		this.payBankcard = payBankcard;
	}
	public boolean isPayBankcardChangeable() {
		return payBankcardChangeable;
	}
	public void setPayBankcardChangeable(boolean payBankcardChangeable) {
		this.payBankcardChangeable = payBankcardChangeable;
	}
	public String getMposBankcardInfoName() {
		return mposBankcardInfoName;
	}
	public void setMposBankcardInfoName(String mposBankcardInfoName) {
		this.mposBankcardInfoName = mposBankcardInfoName;
	}
	public boolean isMposBankcardInfoNameChangeable() {
		return mposBankcardInfoNameChangeable;
	}
	public void setMposBankcardInfoNameChangeable(
			boolean mposBankcardInfoNameChangeable) {
		this.mposBankcardInfoNameChangeable = mposBankcardInfoNameChangeable;
	}
	public String getMposBankcardInfoCertNo() {
		return mposBankcardInfoCertNo;
	}
	public void setMposBankcardInfoCertNo(String mposBankcardInfoCertNo) {
		this.mposBankcardInfoCertNo = mposBankcardInfoCertNo;
	}
	public boolean isMposBankcardInfoCertNoChangeable() {
		return mposBankcardInfoCertNoChangeable;
	}
	public void setMposBankcardInfoCertNoChangeable(
			boolean mposBankcardInfoCertNoChangeable) {
		this.mposBankcardInfoCertNoChangeable = mposBankcardInfoCertNoChangeable;
	}
	public String getMposBankcardInfoExpiredDateMonth() {
		return mposBankcardInfoExpiredDateMonth;
	}
	public void setMposBankcardInfoExpiredDateMonth(
			String mposBankcardInfoExpiredDateMonth) {
		this.mposBankcardInfoExpiredDateMonth = mposBankcardInfoExpiredDateMonth;
	}
	public boolean isMposBankcardInfoExpiredDateMonthChangeable() {
		return mposBankcardInfoExpiredDateMonthChangeable;
	}
	public void setMposBankcardInfoExpiredDateMonthChangeable(
			boolean mposBankcardInfoExpiredDateMonthChangeable) {
		this.mposBankcardInfoExpiredDateMonthChangeable = mposBankcardInfoExpiredDateMonthChangeable;
	}
	public String getMposBankcardInfoExpiredDateYear() {
		return mposBankcardInfoExpiredDateYear;
	}
	public void setMposBankcardInfoExpiredDateYear(
			String mposBankcardInfoExpiredDateYear) {
		this.mposBankcardInfoExpiredDateYear = mposBankcardInfoExpiredDateYear;
	}
	public boolean isMposBankcardInfoExpiredDateYearChangeable() {
		return mposBankcardInfoExpiredDateYearChangeable;
	}
	public void setMposBankcardInfoExpiredDateYearChangeable(
			boolean mposBankcardInfoExpiredDateYearChangeable) {
		this.mposBankcardInfoExpiredDateYearChangeable = mposBankcardInfoExpiredDateYearChangeable;
	}
	public String getMposBankcardInfoCvv2() {
		return mposBankcardInfoCvv2;
	}
	public void setMposBankcardInfoCvv2(String mposBankcardInfoCvv2) {
		this.mposBankcardInfoCvv2 = mposBankcardInfoCvv2;
	}
	public boolean isMposBankcardInfoCvv2Changeable() {
		return mposBankcardInfoCvv2Changeable;
	}
	public void setMposBankcardInfoCvv2Changeable(
			boolean mposBankcardInfoCvv2Changeable) {
		this.mposBankcardInfoCvv2Changeable = mposBankcardInfoCvv2Changeable;
	}
	public String getMposBankcardInfoMobile() {
		return mposBankcardInfoMobile;
	}
	public void setMposBankcardInfoMobile(String mposBankcardInfoMobile) {
		this.mposBankcardInfoMobile = mposBankcardInfoMobile;
	}
	public boolean isMposBankcardInfoMobileChangeable() {
		return mposBankcardInfoMobileChangeable;
	}
	public void setMposBankcardInfoMobileChangeable(
			boolean mposBankcardInfoMobileChangeable) {
		this.mposBankcardInfoMobileChangeable = mposBankcardInfoMobileChangeable;
	}
	public String getMerchantExtInfo() {
		return merchantExtInfo;
	}
	public void setMerchantExtInfo(String merchantExtInfo) {
		this.merchantExtInfo = merchantExtInfo;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getRoyalty() {
		return royalty;
	}
	public void setRoyalty(String royalty) {
		this.royalty = royalty;
	}
	
}
