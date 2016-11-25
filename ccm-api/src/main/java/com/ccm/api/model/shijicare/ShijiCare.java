package com.ccm.api.model.shijicare;

import java.util.HashMap;

import com.ccm.api.model.base.BaseObject;

/**
 * shiji care
 * @author chay
 *
 */
public class ShijiCare extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4463096984621343591L;
	private String careId;
	private String hotelCode;
	private String status;
	private String resultMsg;
	private String briefDescription;
	private String fullDescription;
	
	private String productId;//hotel 表中的pmsType
	private String problemType;
	private String assignTo;
	private String suite;
	
	private Boolean isclose;// 是否关闭
	private String closeCode;//case 关闭，返回code
	private String closeDesp;// case 关闭，描述
	private Boolean isSendSms;//这个case是否发送过sms 0：未发送，1发送
	
	public static final String PRODUCTID_CCM = "CCM";
	public static final String PRODUCTID_TBRIGDE = "TBRIGDE";
	public static final String PRODUCTID_SWITCH = "SWITCH";
	
	public static final String PROBLEMTYPE_PMSINTEFACE = "接口机";
	public static final String PROBLEMTYPE_PMS_ORDER = "pms订单";
	public static final String PROBLEMTYPE_ADS_PENDINGMSG= "adsPendingMsg";
	public static final String SUITE_COL="COL";
	public static final String CLOSE_REASONCODE="Admin Task Complete";
	public static final String SUITE_SOLUTION="接口已活跃";
	
	private static HashMap<String, String> problemAssignTo = new HashMap<String, String>();
	static{
		problemAssignTo.put(PROBLEMTYPE_PMSINTEFACE, "tsi");	//接口
		problemAssignTo.put(PROBLEMTYPE_PMS_ORDER, "tsr");		//预订
		problemAssignTo.put(PROBLEMTYPE_ADS_PENDINGMSG, "tsm");	//ccm系统
	}
	public String getProblemAssignTo(String probleType) {
		return problemAssignTo.get(probleType);
	}
	public String getCareId() {
		return careId;
	}
	public void setCareId(String careId) {
		this.careId = careId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBriefDescription() {
		return briefDescription;
	}
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Boolean getIsclose() {
		return isclose;
	}
	public void setIsclose(Boolean isclose) {
		this.isclose = isclose;
	}
	public String getCloseCode() {
		return closeCode;
	}
	public void setCloseCode(String closeCode) {
		this.closeCode = closeCode;
	}
	public String getCloseDesp() {
		return closeDesp;
	}
	public void setCloseDesp(String closeDesp) {
		this.closeDesp = closeDesp;
	}
	public Boolean getIsSendSms() {
		return isSendSms;
	}
	public void setIsSendSms(Boolean isSendSms) {
		this.isSendSms = isSendSms;
	}
	@Override
	public String toString() {
		return "ShijiCare [careId=" + careId + ", hotelCode=" + hotelCode
				+ ", status=" + status + ", resultMsg=" + resultMsg
				+ ", briefDescription=" + briefDescription
				+ ", fullDescription=" + fullDescription + ", productId="
				+ productId + ", problemType=" + problemType + ", assignTo="
				+ assignTo + ", suite=" + suite + ", isclose=" + isclose
				+ ", closeCode=" + closeCode + ", closeDesp=" + closeDesp
				+ ", isSendSms=" + isSendSms + "]";
	}
	
	
	
}
