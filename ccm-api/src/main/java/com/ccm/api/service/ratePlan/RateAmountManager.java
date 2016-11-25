package com.ccm.api.service.ratePlan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.wbe.WbeCalendarCell;
import com.ccm.api.service.base.GenericManager;

public interface RateAmountManager extends GenericManager<RateAmount, String> {

	/**
	 * 保存房价价格
	 */
	RateAmount addRateAmount(RateAmount rateAmount);
	
	/**
	 * 根据房价明细ID删除房价价格
	 */
	void deleteRateAmountByrRateDetailId(String rateDetailId);
	
	/**
	 * 根据房价明细ID查找房价价格
	 */
	
	List<RoomMsg> setAfterAmountWithTaxForRoomMsg(List<RoomMsg> rmList);
	
	List<?> getAmountAfterTaxForCalendar(String ratePlanId, List<?> oList);
	
	void setAmountAfterTaxForCalendar(Object o,String ratePlanId);
	
	void setAmountAfterTaxForCalendar(PriceCalc priceCalc);

	void setAmountAfterTaxForOrder(PriceCalc priceCalc,
			Date svcDate,Boolean isUpdateAmount);
	
	/**
	 * 计算固定税费和税率
	 * @param priceCalc
	 * @param svcDate
	 * @param isUpdateAmount
	 */
	void setAmountAfterTaxForOrderOws(PriceCalc priceCalc,
			Date svcDate,Boolean isUpdateAmount);

	void getAmountAfterTaxForWEB(
			Map<Date, WbeCalendarCell> webCalendarCellMap, String ratePlanId,
			Date svcDate);

	void getAmountAfterTaxForJSON(JSONObject jsonCalendarCellMap,
			String ratePlanId, Date svcDate);

	void getAmountAfterTaxForJSON(String channelCode, String ratePlanCode,
			String hotelCode, JSONObject jsonCalendarCellMap, Date endDate);

	void getAmountAfterTaxWithDetailPackForJSON(Channel channel,String ratePlanCode, String hotelCode, String roomTypeCode,
			JSONObject jsonCalendarCellMap, Date endDate);

	void getAmountAfterTaxWithDetailPackForWEB(Channel channel,
			String ratePlanCode, String hotelCode, String roomTypeCode,
			Map<Date, WbeCalendarCell> webCalendarCellMap, String ratePlanId,
			Date svcDate);


}
