/**
 * 
 */
package com.ccm.api.model.hotel;

import java.util.List;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class Custom extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175646623758773888L;

	private String customId;// 主键
	private String hotelId;//
	private String name;
	private String type;
	private String corpIATANo;
	private String accessCode;
	private String business;
	private String mobile;
	private String fax;
	private String address;
	private String email;

	private String originalCreditLimit;
	private String totalRoomRev;

	private String income; // 由receivable累加
	private String minLimit;

	private String balance;// Balance为保证金管理公式的余额，通过公式OriginalCreditLimit+Income-TotalRoomRev-MinLimit计算

	private Boolean bookingManagment;// 表示是否启用渠道保证金功能

	private String channelId;
	private String channelCode;
	private List<String> channelCodeList;
	private List<String> channelIdList;

	private String profileID;
	private String profileMessage;// profile消息内容

	private String defCancelId;// 客户默认酒店级取消规则ID
	private String defGuaranteeId;// 客户默认酒店级担保规则ID

	private Boolean autoDeposit;// 自动入帐押金
	private String transactionCode;// 入帐代码
	private String traceDept;// 订单下传节点ReservationTraces中的traceDepartment值

	public String getDefCancelId() {
		return defCancelId;
	}

	public void setDefCancelId(String defCancelId) {
		if (defCancelId != null) {
			if (defCancelId.length() == 0) {
				this.defCancelId = null;
			} else {
				this.defCancelId = defCancelId;
			}
		} else {
			this.defCancelId = defCancelId;
		}
	}

	public String getDefGuaranteeId() {
		return defGuaranteeId;
	}

	public void setDefGuaranteeId(String defGuaranteeId) {
		if (defGuaranteeId != null) {
			if (defGuaranteeId.length() == 0) {
				this.defGuaranteeId = null;
			} else {
				this.defGuaranteeId = defGuaranteeId;
			}
		} else {
			this.defGuaranteeId = defGuaranteeId;
		}
	}

	public String getProfileMessage() {
		return profileMessage;
	}

	public void setProfileMessage(String profileMessage) {
		this.profileMessage = profileMessage;
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public List<String> getChannelCodeList() {
		return channelCodeList;
	}

	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}

	public List<String> getChannelIdList() {
		return channelIdList;
	}

	public void setChannelIdList(List<String> channelIdList) {
		this.channelIdList = channelIdList;
	}

	public Boolean getBookingManagment() {
		if (bookingManagment == null) {
			return false;
		} else {
			return bookingManagment;
		}
	}

	public void setBookingManagment(Boolean bookingManagment) {
		this.bookingManagment = bookingManagment;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBusiness() {
		return business;
	}

	public String getOriginalCreditLimit() {
		return originalCreditLimit;
	}

	public void setOriginalCreditLimit(String originalCreditLimit) {
		this.originalCreditLimit = originalCreditLimit;
	}

	public String getTotalRoomRev() {
		return totalRoomRev;
	}

	public void setTotalRoomRev(String totalRoomRev) {
		this.totalRoomRev = totalRoomRev;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(String minLimit) {
		this.minLimit = minLimit;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCorpIATANo() {
		return corpIATANo;
	}

	public void setCorpIATANo(String corpIATANo) {
		this.corpIATANo = corpIATANo;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public Boolean getAutoDeposit() {
		return autoDeposit;
	}

	public void setAutoDeposit(Boolean autoDeposit) {
		this.autoDeposit = autoDeposit;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	/**
	 * @return the traceDept
	 */
	public String getTraceDept() {
		return traceDept;
	}

	/**
	 * @param traceDept
	 *            the traceDept to set
	 */
	public void setTraceDept(String traceDept) {
		this.traceDept = traceDept;
	}

}
