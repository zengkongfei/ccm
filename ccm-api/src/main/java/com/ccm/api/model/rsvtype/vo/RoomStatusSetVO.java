package com.ccm.api.model.rsvtype.vo;

import java.util.Date;
import java.util.Map;

/**
 * 房态管理-批量设置数据
 * @author carr
 *
 */
public class RoomStatusSetVO {

	private String hotelCode;//酒店代码
	private String roonTypeCodes;//多个房型
	private Date fromDate;//开始时间          
	private Date toDate;//结束时间            
	private String weeks;//星期
	private Date effectiveTime;//生效时间     
	private Date expireTime;//失效时间        
	private Integer allotment;//保留房           
	private Integer overBooking;//OverBooking数量
	private Boolean freeSell;//freesell开关    
	private String channelId;
	private String channelIds; //渠道Ids
	private Integer cutOffDays;//保留房截至天数
	private Map <String,String>rateCodesMap;
	private Boolean isSendToPMS;
	private String blockCode;
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
	public String getJsonRateCodesWithBlock() {
		return jsonRateCodesWithBlock;
	}
	public void setJsonRateCodesWithBlock(String jsonRateCodesWithBlock) {
		this.jsonRateCodesWithBlock = jsonRateCodesWithBlock;
	}
	private String jsonRateCodesWithBlock;
	public Map<String, String> getRateCodesMap() {
		return rateCodesMap;
	}
	public void setRateCodesMap(Map<String, String> rateCodesMap) {
		this.rateCodesMap = rateCodesMap;
	}
	public Integer getCutOffDays() {
		return cutOffDays;
	}
	public void setCutOffDays(Integer cutOffDays) {
		this.cutOffDays = cutOffDays;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRoonTypeCodes() {
		return roonTypeCodes;
	}
	public void setRoonTypeCodes(String roonTypeCodes) {
		this.roonTypeCodes = roonTypeCodes;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Integer getAllotment() {
		return allotment;
	}
	public void setAllotment(Integer allotment) {
		this.allotment = allotment;
	}
	
	public Integer getOverBooking() {
        return overBooking;
    }
    public void setOverBooking(Integer overBooking) {
        this.overBooking = overBooking;
    }
	
    public Boolean getFreeSell() {
        return freeSell;
    }
    public void setFreeSell(Boolean freeSell) {
        this.freeSell = freeSell;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    
    public String getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}
	@Override
    public String toString() {
        return "RoomStatusSetVO [hotelCode=" + hotelCode + ", roonTypeCodes="
                + roonTypeCodes + ", fromDate=" + fromDate + ", toDate="
                + toDate + ", weeks=" + weeks + ", effectiveTime="
                + effectiveTime + ", expireTime=" + expireTime + ", allotment="
                + allotment + ", overBooking=" + overBooking + ", freeSell="
                + freeSell + "]";
    }
	
}
