package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchCriteria;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchResult;
import com.ccm.api.model.sell.vo.ProductionJsonVO;
import com.ccm.api.model.sell.vo.ProductionVO;
import com.ccm.api.service.base.GenericManager;

public interface RestrictionCalcManager extends GenericManager<RestrictionCalc, String> {

    /**
     * 查询startDate到endDate区间的每日开关房状态
     */
	List<List<List<List<ProductionJsonVO>>>> getProductionCalendars(ProductionVO vo);
    
    /**
     * 修改开关房状态
     */
    void updateRestrictionCalcOnOff(RestrictionCalc rc);
    
    /**
     * 批量修改开关房状态
     */
    void batchUpdateRestrictionCalcOnOff(ProductionVO vo);

    List<RestrictionCalc> searchRestrictionCalc(RestrictionCalc rcalc);
    
    /**
     * 房态和产品开关报表
     * @param criteria
     * @return
     */
	RoomStatusAndProductSwitchResult searchRoomStatusAndProductSwitchs(
			RoomStatusAndProductSwitchCriteria criteria);
}
