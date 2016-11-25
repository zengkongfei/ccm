package com.ccm.api.model.roomType.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.RoomTypeI18n;

public class RoomTypeVO extends RoomType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4604318229898119260L;

	private String roomTypeMId;// 序号
	private String language;// 语言
	private String roomTypeName;// 房型名称
	private String description;// 房型描述,宝贝描述
	private String hotelCode;// 酒店代码
	private String hotelName;// 酒店名称
	private String bedTypeName;// 床型名称
	private Integer available;// 可用房量

	private String babyName;// 宝贝名称
	private String guide;// 购买须知
	private String receipt_other_type_desc;// 发票类型为其他时的发票描述,不能超过30个字
	private String receipt_info;// 发票说明，不能超过100个字
	private List<String> service;// 房型服务
	private List<RoomTypeI18n> roomTypeI18nList; //多语言数据列表
	private List<RatePlanVO> rateplanVoList;   //房价列表
	private List<Picture> pictureList;   //房型图片列表
	private List<PriceCalc> priceCalcList;   //房价日历列表
	private BigDecimal price;  //价格(均价)

	private String channelHotelCode;// 淘宝hid
	private String channelRoomTypeCode;// rid
	private String channelGoodsCode; // gid
	private String picId;
	private String roomPicUrl;
	private String serviceJson;
	private Long matchStatus;
	private String outerId;
	private String exception;// 异常错误

	public Long getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(Long matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getRoomTypeMId() {
		return roomTypeMId;
	}

	public void setRoomTypeMId(String roomTypeMId) {
		this.roomTypeMId = roomTypeMId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getBedTypeName() {
		return bedTypeName;
	}

	public void setBedTypeName(String bedTypeName) {
		this.bedTypeName = bedTypeName;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public List<String> getService() {
		return service;
	}

	public void setService(List<String> service) {
		this.service = service;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getBabyName() {
		return babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getReceipt_other_type_desc() {
		return receipt_other_type_desc;
	}

	public void setReceipt_other_type_desc(String receipt_other_type_desc) {
		this.receipt_other_type_desc = receipt_other_type_desc;
	}

	public String getReceipt_info() {
		return receipt_info;
	}

	public void setReceipt_info(String receipt_info) {
		this.receipt_info = receipt_info;
	}

	public String getChannelHotelCode() {
		return channelHotelCode;
	}

	public void setChannelHotelCode(String channelHotelCode) {
		this.channelHotelCode = channelHotelCode;
	}

	public String getServiceJson() {
		return serviceJson;
	}

	public void setServiceJson(String serviceJson) {
		this.serviceJson = serviceJson;
	}

	public String getChannelRoomTypeCode() {
		return channelRoomTypeCode;
	}

	public void setChannelRoomTypeCode(String channelRoomTypeCode) {
		this.channelRoomTypeCode = channelRoomTypeCode;
	}

	public String getChannelGoodsCode() {
		return channelGoodsCode;
	}

	public void setChannelGoodsCode(String channelGoodsCode) {
		this.channelGoodsCode = channelGoodsCode;
	}

	public String getRoomPicUrl() {
		return roomPicUrl;
	}

	public void setRoomPicUrl(String roomPicUrl) {
		this.roomPicUrl = roomPicUrl;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<RoomTypeI18n> getRoomTypeI18nList() {
		return roomTypeI18nList;
	}

	public void setRoomTypeI18nList(List<RoomTypeI18n> roomTypeI18nList) {
		this.roomTypeI18nList = roomTypeI18nList;
	}
	
	public List<RatePlanVO> getRateplanVoList() {
		if(rateplanVoList==null){
			rateplanVoList = new ArrayList<RatePlanVO>();
		}
		return rateplanVoList;
	}

	public void setRateplanVoList(List<RatePlanVO> rateplanVoList) {
		this.rateplanVoList = rateplanVoList;
	}

	
	public List<Picture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}
	
	public List<PriceCalc> getPriceCalcList() {
		if(priceCalcList==null){
			priceCalcList = new ArrayList<PriceCalc>();
		}
		return priceCalcList;
	}

	public void setPriceCalcList(List<PriceCalc> priceCalcList) {
		this.priceCalcList = priceCalcList;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "RoomTypeVO [roomTypeMId=" + roomTypeMId + ", language=" + language + ", roomTypeName=" + roomTypeName + ", description=" + description + ", hotelCode=" + hotelCode + ", hotelName=" + hotelName + ", bedTypeName=" + bedTypeName + ", available=" + available + ", babyName=" + babyName + ", guide=" + guide + ", receipt_other_type_desc=" + receipt_other_type_desc + ", receipt_info=" + receipt_info + ", service=" + service + ", roomTypeI18nList=" + roomTypeI18nList + ", rateplanVoList=" + rateplanVoList + ", pictureList=" + pictureList + ", priceCalcList=" + priceCalcList + ", price=" + price + ", channelHotelCode=" + channelHotelCode + ", channelRoomTypeCode=" + channelRoomTypeCode + ", channelGoodsCode=" + channelGoodsCode + ", picId=" + picId + ", roomPicUrl=" + roomPicUrl
				+ ", serviceJson=" + serviceJson + ", matchStatus=" + matchStatus + ", outerId=" + outerId + ", exception=" + exception + "]";
	}


}
