package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.ratePlan.RateAmount;

public interface RateAmountDao extends GenericDao<RateAmount, String> {

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
	List<RateAmount> getRateAmountByRateDetailId(String rateDetailId);

    List<RateAmount> getRateAmountByRateDetailId(String rateDetailId,
            Rateplan rp);
    
    void addBatchRateAmounts(List<RateAmount> rateAmountList);
    
	/**
	 * 删除不存在detail中的房价,返回删除操作影响的记录条数
	 * @return
	 */
	int deleteRateAmountNotExistsInDetail();
    
}
