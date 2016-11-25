
package com.ccm.api.service.system;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.hotel.vo.HotelVO;

public interface Push2ChannelStaticMsgManager {
	/**
	 * 推送酒店静态信息<br/> 
	 * @param hotelId
	 * @param language
	 * @param bool
	 */
	public void pushStaticMsgHotelInfo(String hotelId, String language,Boolean bool);
	
	/**
	 * 推送房价静态信息<br/> 
	 * @param hotelId
	 * @param language
	 * @param bool
	 */
	public void pushStaticMsgRatePaln(String hotelId,String ratePlanId, String language,Boolean bool);
	
	/**
	 * 推送房型静态信息<br/> 
	 * @param hotelId
	 * @param language
	 * @param bool
	 */
	public void pushStaticMsgRoomType(String hotelId,String roomtypeId, String language,Boolean bool);
	
	/**
	 * 关闭渠道绑定关系
	 * 
	 * @param hotelId
	 * @param channelId
	 * @param language
	 */
	public void pushStaticMsgOff(ChannelGoodsVO cgvo, String language);
	
	/**
	 * 强制推送酒店所有产品静态消息
	 * @param hotelId
	 * @param channelId
	 * @param bool
	 */
	public void pushAllStaticMsg(String hotelId,String channelId,Boolean bool, String language);
	
	/**
	 * 组合推送
	 * @param hotelId
	 * @param hvo
	 */
	void pushHotelInfoContent(Channel c,HotelVO hvo,String language,ChannelHotelConfig channelHotelConfig);
	
}
