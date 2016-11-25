package com.ccm.api.model.rsvtype.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.rsvtype.RsvtypeChannel;

public class RoomStatusVO extends RsvtypeChannel{

	private static final long serialVersionUID = -3553453457649272362L;
	private String[] channelIds; //多个渠道
	private String[] roomTypeCodes;//多个房型代码
	private String roomTypeName;//房型名称
	private Date startDate;//开始日期
	private Date endDate;//截止日期
	private String[] rateCodes;//ratecode list
	private String blockCode;
	private Boolean isSendToPMS;
	private String isModifyRates;
	public String getIsModifyRates() {
		return isModifyRates;
	}
	public void setIsModifyRates(String isModifyRates) {
		this.isModifyRates = isModifyRates;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public Boolean getIsSendToPMS() {
		return isSendToPMS;
	}
	public void setIsSendToPMS(Boolean isSendToPMS) {
		this.isSendToPMS = isSendToPMS;
	}
	public String[] getRateCodes() {
		return rateCodes;
	}
	public void setRateCodes(String[] rateCodes) {
		this.rateCodes = rateCodes;
	}
	private List<String> rsvtypeIdList;
	
	public List<String> getRsvtypeIdList() {
		return rsvtypeIdList;
	}
	public void setRsvtypeIdList(List<String> rsvtypeIdList) {
		this.rsvtypeIdList = rsvtypeIdList;
	}
	public String[] getRoomTypeCodes() {
		return roomTypeCodes;
	}
	public void setRoomTypeCodes(String[] roomTypeCodes) {
		this.roomTypeCodes = roomTypeCodes;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
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
    public String[] getChannelIds() {
        return channelIds;
    }
    public void setChannelIds(String[] channelIds) {
        this.channelIds = channelIds;
    }
	
}
