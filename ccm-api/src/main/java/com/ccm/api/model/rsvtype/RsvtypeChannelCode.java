package com.ccm.api.model.rsvtype;

import org.springframework.data.annotation.Id;


public class RsvtypeChannelCode{
	private static final long serialVersionUID = -4882057364247451953L;
	@Id
	private String rsvtypeChannelCodeId;
	private String chainCode;
	private String channelCode;
	private String hotelCode;
	private String type;
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRsvtypeChannelCodeId() {
		return rsvtypeChannelCodeId;
	}

	public void setRsvtypeChannelCodeId(String rsvtypeChannelCodeId) {
		this.rsvtypeChannelCodeId = rsvtypeChannelCodeId;
	}

	@Override
	public String toString() {
		return "RsvtypeChannelCode [rsvtypeChannelCodeId="
				+ rsvtypeChannelCodeId + ", chainCode=" + chainCode
				+ ", channelCode=" + channelCode + ", hotelCode=" + hotelCode
				+ ", type=" + type + ", count=" + count + "]";
	}
	
}
