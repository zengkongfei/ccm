/**
 * 
 */
package com.ccm.api.model.channel;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
public class Channel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1473386035113081599L;

	/*** 一下为新表字段 */
	private String channelId;// 序号
	private String groupId;// 分组序号
	private String channelCode;// 渠道代码
	private Date addTime;// 渠道上线时间
	private Date delTime;// 渠道下线时间
	private Boolean isEnabled = false;// 渠道是否可用

	private Integer validRoomPrice;// 订单下传到PMS时验证房价：1-校验房价，2-按渠道下发的房价发给PMS，3-不校验（按CCM的房价发给PMS）
	private String pushAddress;// 推送地址
	private Boolean pushRavl = false;// 推送房量
	private Boolean pushRate = false;// 推送房价
	private Boolean pushRtav = false;// 推送开关房
	private Boolean pushRstu = false;// 推送房态
	private String pushStayHistoryAddress;// 推送入住信息地址
	private Integer maxPushDay;// 最大推送天数
	private Date pushTime;// 推送时间（默认AM 2点 n+1） 时，分，秒

	private Boolean isChannelOrderId = false;// 生成的订单号是否使用渠道订单号加四位渠道代码
	private Boolean verifyChannelOrderId = true;// 按渠道订单号验证重复订单
	private Boolean isOverRide = false;// “是否OVER RIDE”勾选项
	private Boolean isRoomNumber = false;// “房量”勾选项
	private Boolean isGuarantee = false;// “担保规则”勾选项
	private String guaranteeId;// “默认担保规则”下拉框
	private Boolean isCancel = false;// “取消规则”勾选项
	private String pushRateUrl;// 推送房价地址
	private Boolean pushRateContent = false;// 推送房价代码内容
	private Boolean pushHotel = false;// 推送酒店代码内容
	private Boolean pushRoom = false;// 推送房型代码内容
	private Boolean isOTA = true;// 是否使用OTA协议，默认为true,false-使用旧的协议
	private Boolean isChannelPushUrl = true;// 是否使用渠道推送地址，默认为true,false-使用酒店推送地址

	private Boolean isPushClose;//渠道关闭时是否推送关
	
	private String receiveAddress;// 最大推送天数
	private Boolean receiveBlock=false;//接收Block
	private Boolean receiveRavl = false;// 接收房量
	private Boolean receiveRate = false;// 接收房价
	private Boolean receiveRtav = false;// 接收开关房
	private Boolean receiveRstu = false;// 接收房态
	
	private int  ordernNumberOfTimes;//每分钟允许订单数
	
	private Boolean useAmountAfterTax=false;//使用税前还是税后
	//排除订单监控时间区间
	private Date invalidTimeBegin;// 排除监控时间  开始时间
	private Date invalidTimeEnd;// 排除监控时间  结束时间

	private String allotNotificationEmail;
	
	private Boolean isJointwisdom;
			
	public Boolean getIsJointwisdom() {
		return isJointwisdom;
	}

	public void setIsJointwisdom(Boolean isJointwisdom) {
		this.isJointwisdom = isJointwisdom;
	}

	public String getAllotNotificationEmail() {
		return allotNotificationEmail;
	}

	public void setAllotNotificationEmail(String allotNotificationEmail) {
		this.allotNotificationEmail = allotNotificationEmail;
	}

	public Boolean getUseAmountAfterTax() {
		return useAmountAfterTax;
	}

	public void setUseAmountAfterTax(Boolean useAmountAfterTax) {
		this.useAmountAfterTax = useAmountAfterTax;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Boolean getReceiveBlock() {
		return receiveBlock;
	}

	public void setReceiveBlock(Boolean receiveBlock) {
		this.receiveBlock = receiveBlock;
	}
	
	public Boolean getReceiveRavl() {
		return receiveRavl;
	}

	public void setReceiveRavl(Boolean receiveRavl) {
		this.receiveRavl = receiveRavl;
	}

	public Boolean getReceiveRate() {
		return receiveRate;
	}

	public void setReceiveRate(Boolean receiveRate) {
		this.receiveRate = receiveRate;
	}

	public Boolean getReceiveRtav() {
		return receiveRtav;
	}

	public void setReceiveRtav(Boolean receiveRtav) {
		this.receiveRtav = receiveRtav;
	}

	public Boolean getReceiveRstu() {
		return receiveRstu;
	}

	public void setReceiveRstu(Boolean receiveRstu) {
		this.receiveRstu = receiveRstu;
	}
	
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getValidRoomPrice() {
		return validRoomPrice;
	}

	public void setValidRoomPrice(Integer validRoomPrice) {
		this.validRoomPrice = validRoomPrice;
	}

	public String getHttpPushAddress() {
		String httpAddress = pushAddress;
		if (CommonUtil.isNotEmpty(pushAddress) && pushAddress.indexOf("http") == -1) {
			httpAddress = "http://" + pushAddress.trim();
		}
		return httpAddress;
	}

	public String getHttpPushStayHistoryAddress() {
		String httpAddress = pushStayHistoryAddress;
		if (CommonUtil.isNotEmpty(pushStayHistoryAddress) && pushStayHistoryAddress.indexOf("http") == -1) {
			httpAddress = "http://" + pushStayHistoryAddress.trim();
		}
		return httpAddress;
	}

	public String getPushAddress() {
		return pushAddress;
	}

	public void setPushAddress(String pushAddress) {
		this.pushAddress = pushAddress;
	}

	public Boolean getPushRavl() {
		return pushRavl;
	}

	public void setPushRavl(Boolean pushRavl) {
		this.pushRavl = pushRavl;
	}

	public Boolean getPushRate() {
		return pushRate;
	}

	public void setPushRate(Boolean pushRate) {
		this.pushRate = pushRate;
	}

	public Boolean getPushRtav() {
		return pushRtav;
	}

	public void setPushRtav(Boolean pushRtav) {
		this.pushRtav = pushRtav;
	}

	public Integer getMaxPushDay() {
		return maxPushDay;
	}

	public void setMaxPushDay(Integer maxPushDay) {
		this.maxPushDay = maxPushDay;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Boolean getPushRstu() {
		return pushRstu;
	}

	public void setPushRstu(Boolean pushRstu) {
		this.pushRstu = pushRstu;
	}

	public String getPushStayHistoryAddress() {
		return pushStayHistoryAddress;
	}

	public void setPushStayHistoryAddress(String pushStayHistoryAddress) {
		this.pushStayHistoryAddress = pushStayHistoryAddress;
	}

	public Boolean getIsChannelOrderId() {
		return isChannelOrderId;
	}

	public void setIsChannelOrderId(Boolean isChannelOrderId) {
		this.isChannelOrderId = isChannelOrderId;
	}

	public Boolean getVerifyChannelOrderId() {
		return verifyChannelOrderId;
	}

	public void setVerifyChannelOrderId(Boolean verifyChannelOrderId) {
		this.verifyChannelOrderId = verifyChannelOrderId;
	}

	public Boolean getPushRateContent() {
		return pushRateContent;
	}

	public void setPushRateContent(Boolean pushRateContent) {
		this.pushRateContent = pushRateContent;
	}

	public String getPushRateUrl() {
		return pushRateUrl;
	}

	public void setPushRateUrl(String pushRateUrl) {
		this.pushRateUrl = pushRateUrl;
	}

	public Boolean getPushHotel() {
		return pushHotel;
	}

	public void setPushHotel(Boolean pushHotel) {
		this.pushHotel = pushHotel;
	}

	public Boolean getPushRoom() {
		return pushRoom;
	}

	public void setPushRoom(Boolean pushRoom) {
		this.pushRoom = pushRoom;
	}

	public Boolean getIsOverRide() {
		return isOverRide;
	}

	public void setIsOverRide(Boolean isOverRide) {
		this.isOverRide = isOverRide;
	}

	public Boolean getIsRoomNumber() {
		return isRoomNumber;
	}

	public void setIsRoomNumber(Boolean isRoomNumber) {
		this.isRoomNumber = isRoomNumber;
	}

	public Boolean getIsGuarantee() {
		return isGuarantee;
	}

	public void setIsGuarantee(Boolean isGuarantee) {
		this.isGuarantee = isGuarantee;
	}

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	public Boolean getIsOTA() {
		return isOTA;
	}

	public void setIsOTA(Boolean isOTA) {
		this.isOTA = isOTA;
	}

	public Boolean getIsChannelPushUrl() {
		return isChannelPushUrl;
	}

	public void setIsChannelPushUrl(Boolean isChannelPushUrl) {
		this.isChannelPushUrl = isChannelPushUrl;
	}

	public Boolean isPush(Date date) {
		if (null == getMaxPushDay()) {
			return false;
		}
		int day = Integer.MAX_VALUE;
		if (date != null) {
			day = DateUtil.dateDiff(DateUtil.currentDate(), date);
		}
		return day <= getMaxPushDay();
	}

	public Boolean getIsPushClose() {
		return isPushClose;
	}

	public void setIsPushClose(Boolean isPushClose) {
		this.isPushClose = isPushClose;
	}

	public int getOrdernNumberOfTimes() {
		return ordernNumberOfTimes;
	}

	public void setOrdernNumberOfTimes(int ordernNumberOfTimes) {
		this.ordernNumberOfTimes = ordernNumberOfTimes;
	}

	public Date getInvalidTimeBegin() {
		return invalidTimeBegin;
	}

	public void setInvalidTimeBegin(Date invalidTimeBegin) {
		this.invalidTimeBegin = invalidTimeBegin;
	}

	public Date getInvalidTimeEnd() {
		return invalidTimeEnd;
	}

	public void setInvalidTimeEnd(Date invalidTimeEnd) {
		this.invalidTimeEnd = invalidTimeEnd;
	}

	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", groupId=" + groupId + ", channelCode=" + channelCode
				+ ", addTime=" + addTime + ", delTime=" + delTime + ", isEnabled=" + isEnabled + ", validRoomPrice="
				+ validRoomPrice + ", pushAddress=" + pushAddress + ", pushRavl=" + pushRavl + ", pushRate=" + pushRate
				+ ", pushRtav=" + pushRtav + ", pushRstu=" + pushRstu + ", pushStayHistoryAddress="
				+ pushStayHistoryAddress + ", maxPushDay=" + maxPushDay + ", pushTime=" + pushTime
				+ ", isChannelOrderId=" + isChannelOrderId + ", verifyChannelOrderId=" + verifyChannelOrderId
				+ ", isOverRide=" + isOverRide + ", isRoomNumber=" + isRoomNumber + ", isGuarantee=" + isGuarantee
				+ ", guaranteeId=" + guaranteeId + ", isCancel=" + isCancel + ", pushRateUrl=" + pushRateUrl
				+ ", pushRateContent=" + pushRateContent + ", pushHotel=" + pushHotel + ", pushRoom=" + pushRoom
				+ ", isOTA=" + isOTA + ", isChannelPushUrl=" + isChannelPushUrl + ", isPushClose=" + isPushClose
				+ ", receiveAddress=" + receiveAddress + ", receiveBlock=" + receiveBlock + ", receiveRavl="
				+ receiveRavl + ", receiveRate=" + receiveRate + ", receiveRtav=" + receiveRtav + ", receiveRstu="
				+ receiveRstu + ", ordernNumberOfTimes=" + ordernNumberOfTimes + ", useAmountAfterTax="
				+ useAmountAfterTax + ", invalidTimeBegin=" + invalidTimeBegin + ", invalidTimeEnd=" + invalidTimeEnd
				+ ", allotNotificationEmail=" + allotNotificationEmail + ", isJointwisdom=" + isJointwisdom + "]";
	}

}
