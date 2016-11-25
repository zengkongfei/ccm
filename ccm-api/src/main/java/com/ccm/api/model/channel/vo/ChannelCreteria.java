package com.ccm.api.model.channel.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class ChannelCreteria extends SearchCriteria {

	private static final long serialVersionUID = -6634041611351538120L;

	private String channelCode;// 代码
	private Date addTime;// 渠道上线时间
	private Date delTime;// 渠道下线时间

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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

	@Override
	public String toString() {
		return "ChannelCreteria [channelCode=" + channelCode + ", addTime=" + addTime + ", delTime=" + delTime + "]";
	}

}
