/**
 * 
 */
package com.ccm.api.model.channel;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class ChannelHotel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6660111217833956441L;

	private Integer channelStatus;
	private Long matchStatus;
	
	/***一下为新表字段*/
	private String channelHotelId;//序号           
	private String channelId;//渠道序号            
	private String hotelId;//酒店序号              
	private String channelHotelCode;//渠道酒店代码 
	private Date addTime;//上线时间              
	private Date delTime;//下线时间              
	private String outerId;
	private String channelCode;
	private Integer maxPushDay;// 最大推送天数
	private String groupId;
	
	public Integer getMaxPushDay() {
        return maxPushDay;
    }

    public void setMaxPushDay(Integer maxPushDay) {
        this.maxPushDay = maxPushDay;
    }

    public String getChannelHotelId() {
		return channelHotelId;
	}

	public void setChannelHotelId(String channelHotelId) {
		this.channelHotelId = channelHotelId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getChannelHotelCode() {
		return channelHotelCode;
	}

	public void setChannelHotelCode(String channelHotelCode) {
		this.channelHotelCode = channelHotelCode;
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

	public Integer getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}

    public Long getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Long matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
    public String toString() {
        return "ChannelHotel [channelStatus=" + channelStatus
                + ", matchStatus=" + matchStatus + ", channelHotelId="
                + channelHotelId + ", channelId=" + channelId + ", hotelId="
                + hotelId + ", channelHotelCode=" + channelHotelCode
                + ", addTime=" + addTime + ", delTime=" + delTime
                + ", outerId=" + outerId + "]";
    }
}