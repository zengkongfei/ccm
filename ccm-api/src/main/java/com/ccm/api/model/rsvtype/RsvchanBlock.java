package com.ccm.api.model.rsvtype;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.ratePlan.RateDetail;

public class RsvchanBlock extends BaseObject implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7837691693202176801L;
	@Id
	private String rsvchanblockId;
	private Integer blockNum;
	private Integer blockSold;
	private String blockCode;
	
	private String rsvtypeId;
	private String rsvtypeChannelId;
	private String channelId;
	private String channelCode;
	private Date cutOffDate;
	private Integer cutOffDays;
	private String ratePlanCodes;
	private Date date;
	private String hotelCode;
	private String type;
	private Boolean isSendToPMS;
	
	@Override
	public RsvchanBlock clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (RsvchanBlock)super.clone();
	}

	public Boolean getIsSendToPMS() {
		return isSendToPMS;
	}

	public void setIsSendToPMS(Boolean isSendToPMS) {
		this.isSendToPMS = isSendToPMS;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRsvtypeId() {
		return rsvtypeId;
	}

	public void setRsvtypeId(String rsvtypeId) {
		this.rsvtypeId = rsvtypeId;
	}

	public String getRsvtypeChannelId() {
		return rsvtypeChannelId;
	}

	public void setRsvtypeChannelId(String rsvtypeChannelId) {
		this.rsvtypeChannelId = rsvtypeChannelId;
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

	public Date getCutOffDate() {
		return cutOffDate;
	}

	public void setCutOffDate(Date cutOffDate) {
		this.cutOffDate = cutOffDate;
	}

	public Integer getCutOffDays() {
		return cutOffDays;
	}

	public void setCutOffDays(Integer cutOffDays) {
		this.cutOffDays = cutOffDays;
	}

	public String getRatePlanCodes() {
		return ratePlanCodes;
	}

	public void setRatePlanCodes(String ratePlanCodes) {
		this.ratePlanCodes = ratePlanCodes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getRsvchanblockId() {
		return rsvchanblockId;
	}

	public void setRsvchanblockId(String rsvchanblockId) {
		this.rsvchanblockId = rsvchanblockId;
	}

	public Integer getBlockNum() {
		return blockNum;
	}

	public void setBlockNum(Integer blockNum) {
		this.blockNum = blockNum;
	}

	public Integer getBlockSold() {
		return blockSold;
	}

	public void setBlockSold(Integer blockSold) {
		this.blockSold = blockSold;
	}
}
