package com.ccm.api.service.ratePlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;


public interface DealRateDetailManager {
	/**定时消费mc房价*/
	void dealRateDetail(String customerId);
	
	/**比较新旧房价，并产生消息到mongodb*/
	void compareRateDetailVO(RateDetailVO rateDetailVO, Rateplan rp);

	HashMap<String, Object> getOldPriceCalcByOldDetail(String uuidSign,
			RatePlanVO rpvo) throws Exception;

	Set<String> getBindChannelSet(
			HashMap<String, ArrayList<ChannelGoodsVO>> channelRateRoomMap,
			Map<String, Channel> channelMap, PriceCalc priceCalc)
			throws Exception;

	List<RoomMsg> getNewPriceAndClosePrice(HashMap<String, Object> oldMap,
			List<RateDetailVO> newPricelist,
			HashMap<String, ArrayList<ChannelGoodsVO>> channelRateRoomMap,
			RatePlanVO rpvo) throws Exception;
}
