package com.ccm.api.dao.ratePlan.ibatis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.PriceCalcDao;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.ChannelRateplanVO;

@Repository("priceCalcDao")
public class PriceCalcDaoibatis extends GenericDaoiBatis<PriceCalc, String> implements PriceCalcDao {

	public PriceCalcDaoibatis() {
		super(PriceCalc.class);
	}

    @SuppressWarnings("unchecked")
	@Override
    public List<PriceCalc> searchPriceCalc(PriceCalc pc) {
        return getSqlMapClientTemplate().queryForList("searchPriceCalc", pc);
    }

    @Override
    public void deletePriceCalc(String priceCalcId) {
        getSqlMapClientTemplate().delete("deletePriceCalc",priceCalcId);
    }

    @Override
    public int updateChannelCode(PriceCalc pc) {
        return getSqlMapClientTemplate().update("updateChannelCode",pc);
    }

    @Override
    public void deletePriceCalcByChannelRateplanVO(ChannelRateplanVO crv) {
        HashMap<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("channelCodeList", crv.getChannelCodeList());
        paramMap.put("chainCode", crv.getChainCode());
        paramMap.put("hotelCode", crv.getHotelCode());
        paramMap.put("ratePlanCode", crv.getRatePlanCode());
        paramMap.put("startDate", crv.getStartDate());
        paramMap.put("endDate", crv.getEndDate());
        getSqlMapClientTemplate().delete("deletePriceCalcByChannelRateplanVO",paramMap);
    }

	@Override
	public void deletePriceCalc(List<PriceCalc> pcList) {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("priceCalcList", pcList);
		getSqlMapClientTemplate().delete("deletePriceCalcByPriceList",paramMap);
	}

	@Override
	public void batchSave(List<PriceCalc> pcList) {
		getSqlMapClientTemplate().insert("batchAddPriceCalc",pcList);
	}

	@Override
	public void deletePriceCalcByDetailIdAndRatePlanCode(String rateDetailId,String rateCode,String hotelCode) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("rateDetailId", rateDetailId);
		paramMap.put("ratePlanCode", rateCode);
		paramMap.put("hotelCode", hotelCode);
		getSqlMapClientTemplate().delete("deletePriceCalcByDetailIdAndRatePlanCode",paramMap);
	}

	@Override
	public List<PriceCalc> getPriceFromRateDetail(PriceCalc pc) {
		return getSqlMapClientTemplate().queryForList("getPriceFromRateDetail", pc);
	}

	@Override
	public List<PriceCalc> getRateDetailRoomRateDate(PriceCalc pc) {
		return (List<PriceCalc>)getSqlMapClientTemplate().queryForList("getRateDetailRoomRateDate", pc);
	}
}
