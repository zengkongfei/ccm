package com.ccm.api.model.hotel.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class FeedBackCriteria extends SearchCriteria {

	private static final long serialVersionUID = -2623312015800158243L;

	private String hotelId;
	private List<String> feedBackIds;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public List<String> getFeedBackIds() {
		return feedBackIds;
	}

	public void setFeedBackIds(List<String> feedBackIds) {
		this.feedBackIds = feedBackIds;
	}

	@Override
	public String toString() {
		return "FeedBackCriteria [hotelId=" + hotelId + "]";
	}

}
