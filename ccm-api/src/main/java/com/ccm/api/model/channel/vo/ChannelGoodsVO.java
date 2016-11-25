/**
 * 
 */
package com.ccm.api.model.channel.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.roomType.vo.RoomTypeVO;

/**
 * @author Jenny
 * 
 */
public class ChannelGoodsVO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472139203533614755L;

	private String isActiveImmediately;
	private String status;
	private String srcStatus;// 修改前的状态

	private List<String> roomTypeCodeList = new ArrayList<String>();
	private List<String> roomTypeIds;
	private List<String> channelIds;
	private List<String> ratePlanIds;

	private String channelgoodsId;// 序号
	private Date effectiveDate;// 生效时间
	private Date expireDate;// 失效时间
	private Date addTime;// 渠道上线时间
	private Date delTime;// 渠道下线时间

	private String channelId;
	private String channelCode;// 渠道代码

	private String hotelId;// 酒店Id

	private String ratePlanId;// 房价ID
	private String ratePlanCode;// 房价代码
	private String description;// 房价描述
	private String ratePlanName; // 房价名称

	private String roomTypeId;// 房型ID
	private String roomTypeCode;// 房型代码
	private String roomTypeName;// 房型描述
	private String pmsCode; // pms代码

	private Integer channelProfileId;// 渠道profileID,用于PMS接口使用
	private Integer validRoomPrice;// 渠道验证房价类型,用于PMS接口使用
	private String language; // 语言编码

	private Boolean isCancel = false;// “取消规则”勾选项:渠道取消订单，忽略房价取消规则，以确保取消订单生成

	private String pushRateUrl;// 推送房价地址
	private Boolean pushRateContent = false;// 推送房价内容
	private Boolean pushHotel = false;// 推送酒店代码内容
	private Boolean pushRoom = false;// 推送房型代码内容
	private Boolean isOTA = true;// 是否使用OTA协议，默认为true,false-使用旧的协议
	private Boolean isChannelPushUrl = true;// 是否使用渠道推送地址，默认为true,false-使用酒店推送地址

	private Boolean isBind;
	private List<String> statusList;// 用于查询未发布与已发布状态的渠道绑定关系
	private String[] roomTypeCodes;
	private String[] ratePlanCodes;
	private String[] channelCodes;
	private List<RoomTypeVO> roomTypeVoList;

	
	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public String getIsActiveImmediately() {
		return isActiveImmediately;
	}

	public void setIsActiveImmediately(String isActiveImmediately) {
		this.isActiveImmediately = isActiveImmediately;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}

	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public List<String> getRoomTypeIds() {
		if (roomTypeIds == null) {
			roomTypeIds = new ArrayList<String>();
		}
		return roomTypeIds;
	}

	public void setRoomTypeIds(List<String> roomTypeIds) {
		this.roomTypeIds = roomTypeIds;
	}

	public List<String> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<String> channelIds) {
		this.channelIds = channelIds;
	}

	public List<String> getRatePlanIds() {
		return ratePlanIds;
	}

	public void setRatePlanIds(List<String> ratePlanIds) {
		this.ratePlanIds = ratePlanIds;
	}

	public String getChannelgoodsId() {
		return channelgoodsId;
	}

	public void setChannelgoodsId(String channelgoodsId) {
		this.channelgoodsId = channelgoodsId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Integer getChannelProfileId() {
		return channelProfileId;
	}

	public void setChannelProfileId(Integer channelProfileId) {
		this.channelProfileId = channelProfileId;
	}

	public Integer getValidRoomPrice() {
		return validRoomPrice;
	}

	public void setValidRoomPrice(Integer validRoomPrice) {
		this.validRoomPrice = validRoomPrice;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSrcStatus() {
		return srcStatus;
	}

	public void setSrcStatus(String srcStatus) {
		this.srcStatus = srcStatus;
	}

	public String getRatePlanName() {
		return ratePlanName;
	}

	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
	}

	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
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

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String[] getRoomTypeCodes() {
		return roomTypeCodes;
	}

	public void setRoomTypeCodes(String[] roomTypeCodes) {
		this.roomTypeCodes = roomTypeCodes;
	}

	public String[] getRatePlanCodes() {
		return ratePlanCodes;
	}

	public void setRatePlanCodes(String[] ratePlanCodes) {
		this.ratePlanCodes = ratePlanCodes;
	}

	public String[] getChannelCodes() {
		return channelCodes;
	}

	public void setChannelCodes(String[] channelCodes) {
		this.channelCodes = channelCodes;
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

	public String getPmsCode() {
		return pmsCode;
	}

	public void setPmsCode(String pmsCode) {
		this.pmsCode = pmsCode;
	}

	public List<RoomTypeVO> getRoomTypeVoList() {
		return roomTypeVoList;
	}

	public void setRoomTypeVoList(List<RoomTypeVO> roomTypeVoList) {
		this.roomTypeVoList = roomTypeVoList;
	}

	@Override
	public String toString() {
		return "ChannelGoodsVO [isActiveImmediately=" + isActiveImmediately + ", status=" + status + ", srcStatus=" + srcStatus + ", roomTypeCodeList=" + roomTypeCodeList + ", roomTypeIds=" + roomTypeIds + ", channelIds=" + channelIds + ", ratePlanIds=" + ratePlanIds + ", channelgoodsId=" + channelgoodsId + ", effectiveDate=" + effectiveDate + ", expireDate=" + expireDate + ", addTime=" + addTime + ", delTime=" + delTime + ", channelId=" + channelId + ", channelCode=" + channelCode + ", hotelId=" + hotelId + ", ratePlanId=" + ratePlanId + ", ratePlanCode=" + ratePlanCode + ", description=" + description + ", ratePlanName=" + ratePlanName + ", roomTypeId=" + roomTypeId + ", roomTypeCode=" + roomTypeCode + ", roomTypeName=" + roomTypeName + ", pmsCode=" + pmsCode + ", channelProfileId="
				+ channelProfileId + ", validRoomPrice=" + validRoomPrice + ", language=" + language + ", isCancel=" + isCancel + ", pushRateUrl=" + pushRateUrl + ", pushRateContent=" + pushRateContent + ", pushHotel=" + pushHotel + ", pushRoom=" + pushRoom + ", isOTA=" + isOTA + ", isChannelPushUrl=" + isChannelPushUrl + ", statusList=" + statusList + ", roomTypeCodes=" + Arrays.toString(roomTypeCodes) + ", ratePlanCodes=" + Arrays.toString(ratePlanCodes) + ", channelCodes=" + Arrays.toString(channelCodes) + ", roomTypeVoList=" + roomTypeVoList + "]";
	}

}
