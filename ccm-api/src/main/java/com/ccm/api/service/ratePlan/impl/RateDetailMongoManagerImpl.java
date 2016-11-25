package com.ccm.api.service.ratePlan.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVOCriteria;
import com.ccm.api.model.ratePlan.vo.RateDetailVOResult;
import com.ccm.api.service.ratePlan.RateDetailMongoManager;
import com.ccm.api.util.CommonUtil;

@Service("rateDetailMongoManager")
public class RateDetailMongoManagerImpl implements RateDetailMongoManager {
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private RateDetailVODaoMongo rateDetailVODaoMongo;
	
	@Override
	public boolean isExistsRateDetailMongo(PriceCalc pc){
		RateDetailVOCriteria criteria = new RateDetailVOCriteria();
		criteria.setChainCode(pc.getChainCode());
		criteria.setHotelCode(pc.getHotelCode());
//		criteria.setRatePlanCode(pc.getRatePlanCode());
		criteria.setStatus(new String[]{RateDetailVO.MONGO_STATUS_INIT,RateDetailVO.MONGO_STATUS_ERR});
		RateDetailVOResult result = rateDetailVODaoMongo.searchRateDetailVO(criteria);
		List<RateDetailVO> rateDetailVOList = result.getResultList();
		return CommonUtil.isNotEmpty(rateDetailVOList);
	}
}
