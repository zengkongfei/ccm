package com.ccm.api.service.system;

import java.util.Date;

import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.channel.AdsGoods;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.ratePlan.PriceCalc;

public interface PushManage {
	/**
	 * 推送房量
	 */
	void pushAvailability(RoomMsg rm, Channel c);

	/**
	 * 推送开关房
	 */
	void pushAvailUpdateNotif(RoomMsg rm, Channel c);

	/**
	 * 推送房价
	 */
	void pushRateUpdateNotif(PriceCalc pc);

	void dailySend();

	boolean handSend(AdsLogMessageCriteria amc, HotelVO hotelvo, String language) throws Exception;

	/** 入住信息推送 */
	void stayHistoryNotification(Master order);

	void sendByDate(Date startDate, Date endDate, String channelCode);

	boolean sendByChannelGoods(AdsGoods adsGoods);
}
