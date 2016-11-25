package com.ccm.api.model.jmsMsg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.ratePlan.RateAmount;

public class RoomMsg implements Serializable{
    private static final long serialVersionUID = -9219451135430589059L;
    @Id
    private String roomMsgId;
    private String channelCode;
    private String chainCode;
    private String hotelCode;
    private String roomTypeCode;
    private String rateCode;
    private Integer amount;
    private BigDecimal price;
    private Integer numberOfUnits; // 人数
    private Boolean onOff;
    private String adsType;
    private Date date;// 日期
    private String startDate;
    private String sendStatus;
    private Date createdTime;
    private String errMsg;
    
    private String rsvtypeId;//对pms上传房量用
    private String channelId;//对pms上传房量用
    private Integer overBooking;//总超预定量
    private Integer totalOBSold;//已超卖总房量
    private List<RateAmount> rateAmountList;//房价人数价格
	private String currencyCode; // 货币类型

    public static final Boolean ROOM_OPEN=true;
    public static final Boolean ROOM_CLOSE=false;
    
    public String getHotelCode() {
        return hotelCode;
    }
    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
    public String getRoomTypeCode() {
        return roomTypeCode;
    }
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
    public String getRateCode() {
        return rateCode;
    }
    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Boolean getOnOff() {
        return onOff;
    }
    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getChainCode() {
        return chainCode;
    }
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }
    public String getChannelCode() {
        return channelCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public String getRoomMsgId() {
		return roomMsgId;
	}
	public void setRoomMsgId(String roomMsgId) {
		this.roomMsgId = roomMsgId;
	}
	public String getAdsType() {
		return adsType;
	}
	public void setAdsType(String adsType) {
		this.adsType = adsType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRsvtypeId() {
		return rsvtypeId;
	}
	public void setRsvtypeId(String rsvtypeId) {
		this.rsvtypeId = rsvtypeId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Integer getOverBooking() {
		return overBooking;
	}
	public void setOverBooking(Integer overBooking) {
		this.overBooking = overBooking;
	}
	public List<RateAmount> getRateAmountList() {
		return rateAmountList;
	}
	public void setRateAmountList(List<RateAmount> rateAmountList) {
		this.rateAmountList = rateAmountList;
	}
	public Integer getTotalOBSold() {
		return totalOBSold;
	}
	public void setTotalOBSold(Integer totalOBSold) {
		this.totalOBSold = totalOBSold;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Override
	public String toString() {
		return "RoomMsg [roomMsgId=" + roomMsgId + ", channelCode="
				+ channelCode + ", chainCode=" + chainCode + ", hotelCode="
				+ hotelCode + ", roomTypeCode=" + roomTypeCode + ", rateCode="
				+ rateCode + ", amount=" + amount + ", price=" + price
				+ ", numberOfUnits=" + numberOfUnits + ", onOff=" + onOff
				+ ", adsType=" + adsType + ", date=" + date + ", startDate="
				+ startDate + ", sendStatus=" + sendStatus + ", createdTime="
				+ createdTime + ", errMsg=" + errMsg + ", rsvtypeId="
				+ rsvtypeId + ", channelId=" + channelId + ", overBooking="
				+ overBooking + ", totalOBSold=" + totalOBSold
				+ ", rateAmountList=" + rateAmountList + "]";
	}
    
}
