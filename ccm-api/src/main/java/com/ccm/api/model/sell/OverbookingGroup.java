package com.ccm.api.model.sell;

import java.util.List;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.channel.ChannelHotel;

/**
 * 超卖分组
 * @author carr
 *
 */
public class OverbookingGroup extends BaseObject {

	private static final long serialVersionUID = -3502277807521926710L;

	private String groupId;//序号      
	private String groupCode;//分组代码
	private Double percent;//OB占比    
	private String hotelId;
	private List<ChannelHotel> channelHotelList;	//渠道code,id
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public List<ChannelHotel> getChannelHotelList() {
		return channelHotelList;
	}
	public void setChannelHotelList(List<ChannelHotel> channelHotelList) {
		this.channelHotelList = channelHotelList;
	}
	@Override
	public String toString() {
		return "OverbookingGroup [groupId=" + groupId + ", groupCode="
				+ groupCode + ", percent=" + percent + ", hotelId=" + hotelId
				+ ", channelHotelList=" + channelHotelList + "]";
	}
	
}
