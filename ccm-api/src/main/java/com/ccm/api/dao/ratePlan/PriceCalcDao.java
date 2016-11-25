package com.ccm.api.dao.ratePlan;

import java.util.HashSet;
import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.ChannelRateplanVO;

public interface PriceCalcDao extends GenericDao<PriceCalc, String> {
    /**根据房价条件查询，返回startDate到endDate区间的每日价格
     * 注：包含endDate的那天
     * */
    List<PriceCalc> searchPriceCalc(PriceCalc pc);

    void deletePriceCalc(String priceCalcId);
    
    int updateChannelCode(PriceCalc pc);

    void deletePriceCalcByChannelRateplanVO(ChannelRateplanVO crv);

	void deletePriceCalc(List<PriceCalc> pcList);

	void batchSave(List<PriceCalc> pcList);

	void deletePriceCalcByDetailIdAndRatePlanCode(String rateDetailId,String rateCode,String hotelCode);
	/**根据条件查询rateDetail返回价格，包含endDate那天*/
	List<PriceCalc> getPriceFromRateDetail(PriceCalc pc);

	List<PriceCalc> getRateDetailRoomRateDate(PriceCalc pc);
}
