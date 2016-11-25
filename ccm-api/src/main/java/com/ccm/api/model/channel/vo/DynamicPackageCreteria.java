package com.ccm.api.model.channel.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class DynamicPackageCreteria extends SearchCriteria {

	private static final long serialVersionUID = -6634041611351538120L;

	private String hotelId;
	private List<String> channelIds;
	private List<String> packageIds;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public List<String> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<String> channelIds) {
		this.channelIds = channelIds;
	}

	public List<String> getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(List<String> packageIds) {
		this.packageIds = packageIds;
	}

	@Override
	public String toString() {
		return "DynamicPackageCreteria [hotelId=" + hotelId + ", channelIds=" + channelIds + ", packageIds=" + packageIds + "]";
	}

}
