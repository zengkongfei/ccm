/**
 * 
 */
package com.ccm.api.dao.channel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.ChannelHotel;

/**
 * @author Jenny
 * 
 */
public interface ChannelHotelDao extends GenericDao<ChannelHotel, String> {

	/**
	 * 通过渠道ID,酒店ID逻辑删除渠道酒店表
	 * 
	 * @param ch
	 */
	void logicDeleteChannelHotel(ChannelHotel ch);

	/**
	 * 通过酒店ID查询渠道酒店表
	 * 
	 * @param hotelId
	 * @return
	 */
	public List<ChannelHotel> getChannelHotelCodeByHotelId(String hotelId);

	/**
	 * 通过渠道ID,酒店ID,时间查询渠道酒店表
	 * 
	 * @param ch
	 * @return
	 */
	public List<ChannelHotel> getChannelHotelByHotelIdChannelId(ChannelHotel ch);

	/**
	 * 通过渠道代码,酒店ID查询渠道酒店表
	 */
	public ChannelHotel getChannelHotelByHotelIdChannelCode(String channelCode, String hotelId);

	/**
	 * 通过渠道代码，渠道酒店代码查询酒店ID
	 * 
	 * @param channelCode
	 * @param channelHotelCode
	 * @return
	 */
	public String getHotelIdByChannelCodeChannelHotelCode(String channelCode, String channelHotelCode);

	void deleteCHByGroupIdAndHotelId(String groupId, String hotelId);

	void delete(String channelHotelId);

}