/**
 * 
 */
package com.ccm.api.model.channel.vo;

import java.util.List;

import com.ccm.api.model.channel.ChannelGoods;

/**
 * @author Jenny
 * 
 */
public class GoodsVO extends ChannelGoods {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472139203533614755L;

	private String babyName; // 宝贝名称
	private String produceName;
	private String channelGoodsCode;// 宝贝渠道ID
	private String roomTypeCode;
	private String roomTypeName;
	private String ratePlanName;
	private String hotelName;
	private String rpId; // 淘宝房价id
	private String status;
	private String ratePlanCode;
	private String description; // 房价描述
	private String hotelCode;
	private String channelRoomTypeCode;
	private String inventoryPrice;

	private List<String> hotelIdList;
	private String exception;// 异常错误
	private String type;
	private String interfaceId;
	private String language;   //语言编码

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getBabyName() {
		return babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public String getProduceName() {
		return produceName;
	}

	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}

	public String getChannelGoodsCode() {
		return channelGoodsCode;
	}

	public void setChannelGoodsCode(String channelGoodsCode) {
		this.channelGoodsCode = channelGoodsCode;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getRatePlanName() {
		return ratePlanName;
	}

	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRpId() {
		return rpId;
	}

	public void setRpId(String rpId) {
		this.rpId = rpId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChannelRoomTypeCode() {
		return channelRoomTypeCode;
	}

	public void setChannelRoomTypeCode(String channelRoomTypeCode) {
		this.channelRoomTypeCode = channelRoomTypeCode;
	}

	public String getInventoryPrice() {
		return inventoryPrice;
	}

	public void setInventoryPrice(String inventoryPrice) {
		this.inventoryPrice = inventoryPrice;
	}

	public List<String> getHotelIdList() {
		return hotelIdList;
	}

	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getHotelCode() {
        return hotelCode;
    }
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    @Override
    public String toString() {
        return "GoodsVO [babyName=" + babyName + ", produceName=" + produceName
                + ", channelGoodsCode=" + channelGoodsCode + ", roomTypeCode="
                + roomTypeCode + ", roomTypeName=" + roomTypeName
                + ", ratePlanName=" + ratePlanName + ", hotelName=" + hotelName
                + ", rpId=" + rpId + ", status=" + status + ", ratePlanCode="
                + ratePlanCode + ", description=" + description
                + ", hotelCode=" + hotelCode + ", channelRoomTypeCode="
                + channelRoomTypeCode + ", inventoryPrice=" + inventoryPrice
                + ", hotelIdList=" + hotelIdList + ", exception=" + exception
                + ", type=" + type + ", interfaceId=" + interfaceId + "]";
    }

}
