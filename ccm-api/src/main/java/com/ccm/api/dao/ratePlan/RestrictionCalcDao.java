package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchCriteria;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchResult;
import com.ccm.api.model.sell.vo.ProductionVO;

public interface RestrictionCalcDao extends GenericDao<RestrictionCalc, String> {

    /**
     * 查询startDate到endDate区间的每日开关房状态
     */
    List<RestrictionCalc> searchRestrictionCalcOnOff(ProductionVO vo);
    
    /**
     * 查询startDate到endDate区间的每日开关房状态,忽略房价日历
     */
    List<RestrictionCalc> searchRestrictionCalcOnOff2(ProductionVO vo);
    
    /**
     * 查询某天开关房状态
     */
    RestrictionCalc getRestrictionCalc(RestrictionCalc rc);
    
    /**
     * 查询每日开关房状态和房价,返回 endDate-1的天数
     */
    List<PriceCalc> searchRestrictionCalcAndRate(PriceCalc pc,Boolean isUpdateAmount);
    /**
     * 查询每日开关房状态和房价,返回 endDate-1的天数
     */
    List<PriceCalc> searchRestrictionCalcAndRateOws(PriceCalc pc,Boolean isUpdateAmount);
    
    /**
     * 修改开关房状态
     */
    void updateRestrictionCalcOnOff(RestrictionCalc rc);


	List<RestrictionCalc> searchRestrictionCalc(RestrictionCalc rcalc);

	List<RestrictionCalc> getRestrictionCalcByObj(RestrictionCalc rc);

	/**
	 * 房态和产品开关报表
	 * 
	 * @param criteria
	 * @return
	 */
	RoomStatusAndProductSwitchResult searchRoomStatusAndProductSwitchs(RoomStatusAndProductSwitchCriteria criteria);

	List<RestrictionCalc> searchRestrictionCalcOnOffForPush(ProductionVO product);

	List<PriceCalc> searchRestrictionCalcAndRateForWBE(PriceCalc pc);

	Integer getRestrictionCalcCountByObj(RestrictionCalc rc);
}
