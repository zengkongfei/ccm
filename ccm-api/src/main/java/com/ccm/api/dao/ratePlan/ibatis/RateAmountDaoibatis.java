package com.ccm.api.dao.ratePlan.ibatis;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.ratePlan.RateAmountDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.util.CommonUtil;

@Repository("rateAmountDaoiBatis")
public class RateAmountDaoibatis extends GenericDaoiBatis<RateAmount, String> implements RateAmountDao {
    
    @Resource
    private RateplanDao rateplanDao;
    
	public RateAmountDaoibatis() {
		super(RateAmount.class);
	}

    @Override
    public RateAmount addRateAmount(RateAmount rateAmount) {
        return (RateAmount) getSqlMapClientTemplate().insert("addRateAmount",rateAmount);
    }

    @Override
    public void deleteRateAmountByrRateDetailId(String rateDetailId) {
        getSqlMapClientTemplate().delete("deleteRateAmountByRateDetailId",rateDetailId);
    }

    @Override
    public List<RateAmount> getRateAmountByRateDetailId(String rateDetailId) {
        List<RateAmount> list = getSqlMapClientTemplate().queryForList("getRateAmountByRateDetailId",rateDetailId);
        if(list == null){
            list = new ArrayList<RateAmount>();
        }
        return list;
    }

    @Override
    public List<RateAmount> getRateAmountByRateDetailId(String rateDetailId,Rateplan rp) {
        List<RateAmount> list = getRateAmountByRateDetailId(rateDetailId);
        for (RateAmount rateAmount : list) {
            BigDecimal baseAmount=null;
            if(CommonUtil.isNotEmpty(rp.getPercent())){
                baseAmount = rateAmount.getBaseAmount().multiply(rp.getPercent()).divide(new BigDecimal(100));
            }else if(CommonUtil.isNotEmpty(rp.getAmount())){
                baseAmount = rateAmount.getBaseAmount().add(rp.getAmount());
            }else{
                baseAmount = rateAmount.getBaseAmount();
            }
            if(baseAmount != null){
//            	baseAmount = baseAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
            	baseAmount = baseAmount.add(new BigDecimal(0.000001));
            	DecimalFormat df = new DecimalFormat("#.##"); 
            	baseAmount = new BigDecimal(df.format(baseAmount));
            }
            rateAmount.setBaseAmount(baseAmount);
        }
        return list;
    }

	@Override
	public void addBatchRateAmounts(List<RateAmount> rateAmountList) {
		if(CommonUtil.isNotEmpty(rateAmountList)){
			getSqlMapClientTemplate().insert("addBatchRateAmounts", rateAmountList);
		}
	}
	
	/**
	 * 删除不存在detail中的房价,返回删除操作影响的记录条数
	 * @return
	 */
	@Override
	public int deleteRateAmountNotExistsInDetail() {
		return getSqlMapClientTemplate().delete("deleteRateAmountNotExistsInDetail");
	}
}
