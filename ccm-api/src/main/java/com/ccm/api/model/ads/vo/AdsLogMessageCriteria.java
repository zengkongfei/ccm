package com.ccm.api.model.ads.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;
import com.ccm.api.model.hotel.Hotel;

public class AdsLogMessageCriteria extends SearchCriteria {

    private static final long serialVersionUID = 2241610909495825852L;

    private Date startDate;
    private Date endDate;
    private String status;
    private String adsType;
    private String echoToken;
    private String chainCode;
    private String hotelCode;
    private List<Hotel> hotels;
    private String chainId;
    private String hotelId;
    private String targetGDS;
    private String roomTypeCode;
    private String ratePlanCode;
    private String tbStatus;
    private Date lastRequestDate;
    public String msgType; //消息类型，推送，接受
    private Date actionDate;
    
    public static final String EXEC_INIT_STATUS = "0";// 初始为 0 为执行
    public static final String EXEC_END_STATUS = "1";// 1 为 已执行
    public static final String EXEC_ERROR_STATUS = "9";// 9 执行错误

    private List<String> channelCodeList; //渠道代码
    private List<String> chainCodeList;   //集团代码
    private List<String> hotelCodeList;   //酒店代码
    private List<String> adsTypeList;   //消息类型
    private List<String> roomTypeCodeList;   //房型代码
    private List<String> roomTypeCodeListHidden;   //房型代码
    private List<String> statusList;   //推送状态
    
	public List<String> getRoomTypeCodeListHidden() {
		return roomTypeCodeListHidden;
	}

	public void setRoomTypeCodeListHidden(List<String> roomTypeCodeListHidden) {
		this.roomTypeCodeListHidden = roomTypeCodeListHidden;
	}

	public List<String> getChannelCodeList() {
		return channelCodeList;
	}

	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}

	public List<String> getChainCodeList() {
		return chainCodeList;
	}

	public void setChainCodeList(List<String> chainCodeList) {
		this.chainCodeList = chainCodeList;
	}

	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}

	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
	}

	public List<String> getAdsTypeList() {
		return adsTypeList;
	}

	public void setAdsTypeList(List<String> adsTypeList) {
		this.adsTypeList = adsTypeList;
	}

	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}

	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getTargetGDS() {
        return targetGDS;
    }

    public void setTargetGDS(String targetGDS) {
        this.targetGDS = targetGDS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getEchoToken() {
        return echoToken;
    }

    public void setEchoToken(String echoToken) {
        this.echoToken = echoToken;
    }

    public Date getLastRequestDate() {
        return lastRequestDate;
    }

    public void setLastRequestDate(Date lastRequestDate) {
        this.lastRequestDate = lastRequestDate;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public String getTbStatus() {
        return tbStatus;
    }

    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "AdsLogMessageCriteria [startDate=" + startDate + ", endDate="
				+ endDate + ", status=" + status + ", adsType=" + adsType
				+ ", echoToken=" + echoToken + ", chainCode=" + chainCode
				+ ", hotelCode=" + hotelCode + ", chainId=" + chainId
				+ ", hotelId=" + hotelId + ", targetGDS=" + targetGDS
				+ ", roomTypeCode=" + roomTypeCode + ", ratePlanCode="
				+ ratePlanCode + ", tbStatus=" + tbStatus
				+ ", lastRequestDate=" + lastRequestDate + ", msgType="
				+ msgType + ", actionDate=" + actionDate + "]";
	}

}
