package com.ccm.api.dao.channel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.ChannelHotelMD5Info;

public interface ChannelHotelMD5InfoDao extends GenericDao<ChannelHotelMD5Info, String>{
	/**
	 * 通过渠道id 和酒店id 查找酒店所有的md5
	 * @param channelId
	 * @param hotelId
	 * @return
	 */
	public List<ChannelHotelMD5Info> getListByChannelId(String channelId,String hotelId);
	
	/**
	 * 查找
	 * type=1  id的值为 酒店id <br/> 
	 * type=2  id的值为 房价id <br/> 
	 * type=3  id的值为 房型id <br/> 
	 * 
	 * @param channelId
	 * @param type
	 * @param id
	 * @return
	 */
	public ChannelHotelMD5Info getChannelHotelMD5InfoByParam(String channelId,String type,String id);
	
}
