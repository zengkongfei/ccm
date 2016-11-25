package com.ccm.api.model.channel;

import java.io.Serializable;
import java.util.Date;

import com.ccm.api.model.base.BaseObject;

public class ChannelGoods extends BaseObject implements Serializable {

	private static final long serialVersionUID = 136860410321544217L;

	private String channelGoodsId;// 序号
	private String channelId;// 渠道序号
	private String roomTypeId;// 房型序号
	private String rateplanid;// 房价序号
	private Boolean isActiveImmediately;// 是否立即激活
	private Date effectiveDate;// 生效时间
	private Date expireDate;// 失效时间
	private String produceName;
	private Long addBed; // 是否可以加床，1：不可以，2：可以
	private Long addBedPrice;// 额外服务-加床价格
	private Long currencyCode;// 币种（仅支持CNY）
	private Long shijiaTag;// 实价有房标签（RP支付类型为全额支付）
	
	private String channelGoodsCode;

	/*
	 * 2为要发布
	 */
	private String status = "0";
	private String gidAndRpid; // 淘宝 酒店商品id-酒店rpID
	private Integer tbSyncStatus; // 淘宝同步状态 1：同步，2：停止同步
	private String hotelId;
	private String ratePlanCode;
	private Boolean isBind=true;//是否匹配
	
	public static final Integer TBSYNCSTATUS_SYNC = 1; // 淘宝同步状态 1：同步，2：停止同步
	public static final Integer TBSYNCSTATUS_STOP_SYNC = 2;
	public static final String HANDLETYPE_BINDING="binding";//绑定渠道
	public static final String HANDLETYPE_PUBLISH="publish";//发布渠道
	public String getChannelGoodsId() {
		return channelGoodsId;
	}

	public void setChannelGoodsId(String channelGoodsId) {
		this.channelGoodsId = channelGoodsId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRateplanid() {
		return rateplanid;
	}

	public void setRateplanid(String rateplanid) {
		this.rateplanid = rateplanid;
	}

	public Boolean getIsActiveImmediately() {
		return isActiveImmediately;
	}

	public void setIsActiveImmediately(Boolean isActiveImmediately) {
		this.isActiveImmediately = isActiveImmediately;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getProduceName() {
		return produceName;
	}

	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}

	public Long getAddBed() {
		return addBed;
	}

	public void setAddBed(Long addBed) {
		this.addBed = addBed;
	}

	public Long getAddBedPrice() {
		return addBedPrice;
	}

	public void setAddBedPrice(Long addBedPrice) {
		this.addBedPrice = addBedPrice;
	}

	public Long getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(Long currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Long getShijiaTag() {
		return shijiaTag;
	}

	public void setShijiaTag(Long shijiaTag) {
		this.shijiaTag = shijiaTag;
	}

	public String getChannelGoodsCode() {
		return channelGoodsCode;
	}

	public void setChannelGoodsCode(String channelGoodsCode) {
		this.channelGoodsCode = channelGoodsCode;
	}

	public String getGidAndRpid() {
		return gidAndRpid;
	}

	public void setGidAndRpid(String gidAndRpid) {
		this.gidAndRpid = gidAndRpid;
	}

	public Integer getTbSyncStatus() {
		return tbSyncStatus;
	}

	public void setTbSyncStatus(Integer tbSyncStatus) {
		this.tbSyncStatus = tbSyncStatus;
	}

	public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }
    
    public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	@Override
    public String toString() {
        return "ChannelGoods [channelGoodsId=" + channelGoodsId
                + ", channelId=" + channelId + ", roomTypeId=" + roomTypeId
                + ", rateplanid=" + rateplanid + ", isActiveImmediately="
                + isActiveImmediately + ", effectiveDate=" + effectiveDate
                + ", expireDate=" + expireDate + ", produceName=" + produceName
                + ", addBed=" + addBed + ", addBedPrice=" + addBedPrice
                + ", currencyCode=" + currencyCode + ", shijiaTag=" + shijiaTag
                + ", channelGoodsCode=" + channelGoodsCode + ", status="
                + status + ", gidAndRpid=" + gidAndRpid + ", tbSyncStatus="
                + tbSyncStatus + ", hotelId=" + hotelId + ", ratePlanCode="
                + ratePlanCode +",isBind="+isBind+"]";
    }

   

   

}
