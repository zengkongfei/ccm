package com.ccm.api.service.ratePlan;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.ratePlan.vo.ChannelRateplanVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.service.base.GenericManager;

public interface PriceCalcManager extends GenericManager<PriceCalc, String> {

    void addPriceCalc(RatePlanVO ratePlanVO,boolean isSync) throws Exception;

    List<PriceCalc> getRoomPriceOws(PriceCalc pc);
    List<PriceCalc> getRoomPrice(PriceCalc pc);

    void deletePriceCalcByRatePlanId(String ratePlanId);

    void delInvalidPriceCalc(RatePlanVO ratePlanVO,
    HashMap<String, String> priceIdMap) throws ParseException;
    
    int[] getWeek(RateDetailVO rateDetailVO);

    void deletePriceCalcByRateRateplan(Rateplan rp);

	void deletePriceCalcByDetailVOList(List<RateDetailVO> rateDetailVOList,String ratePlanCode,String hotelCode);

	void updatePriceCalcByChannelRateplanVOList(
			List<ChannelRateplanVO> channelRateplanVOList,String hotelId,String handleType);

    boolean validataSoldableCondition(SoldableCondition scn, PriceCalc pc,
            List<PriceCalc> priceList) throws Exception;
    
    /**根据条件从 RateDetail中生成价格*/
	List<PriceCalc> getPriceFromRateDetail(PriceCalc pc);

	HashSet<String> getRateDetailRoomRateDate(PriceCalc pc);

	List<PriceCalc> getPriceCalcListByDetail(
			List<RateDetailVO> rateDetailVOList, RatePlanVO ratePlanVO)
			throws Exception;
}
