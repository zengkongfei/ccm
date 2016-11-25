/**
 * 
 */
package com.ccm.api.service.channel;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface ChannelHotelManager extends GenericManager<ChannelHotel, String> {

	/**
	 * 根据渠道ID，酒店ID删除渠道酒店关系
	 * 
	 * @param hotelId
	 * @param chanelId
	 * @param addTime
	 * @param delTime
	 */
	void logicDeleteChannelHotel(String hotelId, String chanelId, Date addTime, Date delTime);

	/**
	 * 通过酒店ID查询渠道酒店表
	 * 
	 * @param hotelId
	 * @return
	 */
	List<ChannelHotel> getChannelHotelCodeByHotelId(String hotelId);

	/**
	 * 根据渠道ID，酒店ID保存或更新渠道酒店关系
	 * 
	 * @param ch
	 */
	void saveOrUpdateChannelHotel(ChannelHotel ch);

	/**
	 * 通过渠道代码，渠道酒店代码查询酒店ID
	 * 
	 * @param channelCode
	 * @param channelHotelCode
	 * @return
	 */
	public String getHotelIdByChannelCodeChannelHotelCode(String channelCode, String channelHotelCode);

	/**
	 * 通过渠道代码,酒店ID查询渠道酒店表
	 */
	ChannelHotel getChannelHotelByHotelIdChannelCode(String channelCode, String hotelId);

	void delete(String channelHotelId);
	
	/**
	 * 通过酒店ID得到所有绑定的渠道列表
	 * @param hotelId
	 * @return
	 */
	List<Channel> getChannelsByHotelId(String hotelId);

}
