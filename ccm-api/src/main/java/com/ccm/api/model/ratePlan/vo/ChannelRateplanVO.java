package com.ccm.api.model.ratePlan.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.BaseObject;

public class ChannelRateplanVO extends BaseObject{
	
	private static final long serialVersionUID = -8020499552116542203L;
	private List<String> channelCodeList;
    private String chainCode;
    private String hotelCode;
    private String ratePlanCode;
    private List<String> roomTypeIdList;
    private Date startDate;
    private Date endDate;
    private Boolean isSendChannel = Boolean.TRUE;//是否发送消息到渠道
    private String errMsg;
    
    public List<String> getChannelCodeList() {
        return channelCodeList;
    }
    public void setChannelCodeList(List<String> channelCodeList) {
        this.channelCodeList = channelCodeList;
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
    public String getRatePlanCode() {
        return ratePlanCode;
    }
    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }
    public List<String> getRoomTypeIdList() {
        return roomTypeIdList;
    }
    public void setRoomTypeIdList(List<String> roomTypeIdList) {
        this.roomTypeIdList = roomTypeIdList;
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
    
    public Boolean getIsSendChannel() {
		return isSendChannel;
	}
	public void setIsSendChannel(Boolean isSendChannel) {
		this.isSendChannel = isSendChannel;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "ChannelRateplanVO [channelCodeList=" + channelCodeList
				+ ", chainCode=" + chainCode + ", hotelCode=" + hotelCode
				+ ", ratePlanCode=" + ratePlanCode + ", roomTypeIdList="
				+ roomTypeIdList + ", startDate=" + startDate + ", endDate="
				+ endDate + ", isSendChannel=" + isSendChannel + ", errMsg="
				+ errMsg + "]";
	}
	
    
}
