package com.ccm.api.dao.ratePlan.ibatis;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.compass.core.util.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RateDetailDao;
import com.ccm.api.model.channel.vo.RoomRateDetailComparableVO;
import com.ccm.api.model.ratePlan.PriceValid;
import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.vo.PriceValidSearchResult;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.SearchPriceValidCriteria;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Repository("rateDetailDao")
public class RateDetailDaoibatis extends GenericDaoiBatis<RateDetail, String> implements RateDetailDao {
	public RateDetailDaoibatis() {
		super(RateDetail.class);
	}

    @Override
    public RateDetail addRateDetail(RateDetail detail) {
        if(StringUtils.hasText(detail.getRateDetailId())){
            return updateRateDetail(detail);
        }else{
            detail.setRateDetailId(UUID.randomUUID().toString().replace("-", ""));
            getSqlMapClientTemplate().insert("addRateDetail",detail);
            return detail;
        }
    }

    @Override
    public RateDetail updateRateDetail(RateDetail detail) {
        getSqlMapClientTemplate().update("updateRateDetail",detail);
        return detail;
    }

    @Override
    public List<RateDetail> getRateDetailByRatePlanId(String ratePlanId) {
        return getSqlMapClientTemplate().queryForList("getRateDetailByRatePlanId",ratePlanId);
    }

    @Override
    public List<RateDetailVO> getRateDetailVOByRatePlanId(String ratePlanId) {
    	HashMap<String,String> map = new HashMap<String, String>();
		map.put("ratePlanId", ratePlanId);
		List<RateDetailVO> rateDetailList = getSqlMapClientTemplate().queryForList("getRateDetailVOByRatePlanId",map);
        if(rateDetailList != null && rateDetailList.size()> 0){
            return rateDetailList;
        }
        return new ArrayList<RateDetailVO>();
    }
    
    @Override
    public void deleteRateDetailByRatePlanId(String ratePlanId){
    	getSqlMapClientTemplate().delete("deleteRateDetailByRatePlanId", ratePlanId);
    	
    }

    @Override
    public void deleteRateDetailById(String rateDetailId){
    	getSqlMapClientTemplate().update("deleteRateDetail", rateDetailId);
    	
    }
    
	@Override
	public RateDetailVO getRateDetailVO(String detailId) {
		return (RateDetailVO) getSqlMapClientTemplate().queryForObject("getRateDetailVO", detailId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PriceValidSearchResult searchPriceValidTimes(SearchPriceValidCriteria criteria){
		PriceValidSearchResult searchResult = new PriceValidSearchResult();
		
		//导出excel标识
		if(criteria.isExcelFlag()){
			List<PriceValid> resultList = getSqlMapClientTemplate().queryForList("searchPriceValidTimes", criteria);
			searchResult.setResultList(resultList);
			
		}else{
			//查询一页数据	
			List<PriceValid> resultList = getSqlMapClientTemplate().queryForList("searchPriceValidTimes", criteria);
			searchResult.setResultList(resultList);
			
			//得到总条数
			Integer totalCount = 
				(Integer) getSqlMapClientTemplate().queryForObject("searchPriceValidTimesCount", criteria);
			searchResult.setTotalCount(totalCount);
		}
		return searchResult;
	}

	@Override
	public void addBatchRateDetails(List<RateDetailVO> rateDetailList) {
		if(CommonUtil.isNotEmpty(rateDetailList)){
			getSqlMapClientTemplate().insert("addBatchRateDetails", rateDetailList);
		}
	}

	@Override
	public List<RateDetailVO> getRateDetailVOByRatePlanId(String rpId,
			String rateDetailId) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("ratePlanId", rpId);
		if(CommonUtil.isNotEmpty(rateDetailId)){
			map.put("rateDetailId", rateDetailId);
		}
		List<RateDetailVO> rateDetailList = getSqlMapClientTemplate().queryForList("getRateDetailVOByRatePlanId",map);
        if(rateDetailList != null && rateDetailList.size()> 0){
            return rateDetailList;
        }
        return new ArrayList<RateDetailVO>();
	}

	@Override
	public List<RoomRateDetailComparableVO> getCompareableRateDetailVOByRatePlanId(Map<String,Object>map){
		List<RoomRateDetailComparableVO> rateDetailList = getSqlMapClientTemplate().queryForList("getRoomRateDetailComparableVO",map);
        if(rateDetailList != null && rateDetailList.size()> 0){
            return rateDetailList;
        }
        return new ArrayList<RoomRateDetailComparableVO>();
	}

	@Override
	public List<RateCodeWithRoomVO> getRateCodeFromRoomTypeId(String hotelId,
			String startDate, String endDate, String roomTypeId
			,List<String> ratePlanCodeList,String accessCode,String channelId) {
		// TODO Auto-generated method stub
		Map paramMap=new HashMap();
		paramMap.put("hotelId", hotelId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("roomTypeId", roomTypeId);
		paramMap.put("ratePlanCodeList", ratePlanCodeList);
		paramMap.put("accessCode", accessCode);
		paramMap.put("channelId", channelId);
		return getSqlMapClientTemplate().queryForList("getRateCodeFromRoomTypeId", paramMap);
	}
	
	@Override
	public Integer getValidCountOfRateDetail(String ratePlanId,Date endDate){
		Map paramMap=new HashMap();
		paramMap.put("ratePlanId", ratePlanId);
		paramMap.put("endDate", DateUtil.getDate(endDate));
		return (Integer)getSqlMapClientTemplate().queryForObject("getValidCountOfRateDetail",paramMap);
	}
}
