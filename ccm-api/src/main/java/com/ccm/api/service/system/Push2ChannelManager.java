/**
 * 
 */
package com.ccm.api.service.system;

import java.util.List;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;

/**
 * @author Jenny
 * 
 */
public interface Push2ChannelManager {
	void pushRateContent(String language, List<ChannelGoodsVO> rateChannelMap);
	/**
	 * 推送房价静态消息
	 * @param language
	 * @param rateChannelMap
	 */
	void pushRateStaticContent(String language, List<ChannelGoodsVO> rateChannelMap);
	
	void pushRateContentOfEditRate(String hotelId, String ratePlanId, String language);

	void pushRateOnOff(String language, ChannelGoodsVO cgvo);

	void pushHotelRoomContent(String language, String hotelId, List<String> channelIds, List<String> roomTypeIds);

	void pushReservation(Master order);
	
	/**
	 * 推送酒店静态信息
	 * @param hvo
	 * @param c
	 */
	void pushHotelInfo(HotelVO hvo, Channel c);
	/**
	 * 推送房型静态信息
	 * @param hvo
	 * @param c
	 * @param roomTypeId
	 */
	void pushRoomContent(HotelVO hvo, Channel c, String roomTypeId) ;
	/**
	 * 推送房价静态信息
	 * @param cgvo
	 * @param language
	 */
	void push2ChannelRate(ChannelGoodsVO cgvo, String language);
	
	/**
	 * 推送产品全量信息
	 * @param c
	 * @param hvo
	 * @param language
	 */
	public void push2ChannelProduct(Channel c,HotelVO hvo, String language,ChannelHotelConfig channelHotelConfig) ;
	
}
