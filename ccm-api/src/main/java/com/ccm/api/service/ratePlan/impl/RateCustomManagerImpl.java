package com.ccm.api.service.ratePlan.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RateCustomManager;

@Service("rateCustomManager")
public class RateCustomManagerImpl extends
		GenericManagerImpl<RateCustomRelationship, String> implements
		RateCustomManager {

	@Resource
	private RateCustomRelationshipDao rateCustomRelationshipDao;

	/**
	 * 新增修改房价时保存房价客户关系
	 */
	public void saveOrUpdateRateCustomRelationship(String savedRatePlanId,
			RatePlanVO vo) {
		rateCustomRelationshipDao
				.deleteRateCustomRelationshipByRatePlanId(savedRatePlanId);
		if (vo.getCustomList() != null) {
			for (String customId : vo.getCustomList()) {
				RateCustomRelationship rcr = new RateCustomRelationship();
				rcr.setRatePlanId(savedRatePlanId);
				rcr.setCustomId(customId);
				rateCustomRelationshipDao.save(rcr);
			}
		}
	}

	/**
	 * 增加新的房价客户关系
	 */
	public void addCustomRelationship(String savedRatePlanId, RatePlanVO vo) {
		
		if (vo.getCustomList() != null) {
			for (String customId : vo.getCustomList()) {
				RateCustomRelationship customRelationship = rateCustomRelationshipDao
				.getRateCustomRelationshipByRatePlanIdAndCustomId(savedRatePlanId,
						customId);
				if(null==customRelationship){
					RateCustomRelationship rcr = new RateCustomRelationship();
					rcr.setRatePlanId(savedRatePlanId);
					rcr.setCustomId(customId);
					rateCustomRelationshipDao.save(rcr);
				}
			}
		}
	}

	/**
	 * 删除新的房价客户关系
	 */
	public void delCustomRelationship(String savedRatePlanId, RatePlanVO vo) {
		
		if (vo.getCustomList() != null) {
			for (String customId : vo.getCustomList()) {
				RateCustomRelationship customRelationship = 
					rateCustomRelationshipDao.getRateCustomRelationshipByRatePlanIdAndCustomId(savedRatePlanId,customId);
				if(null!=customRelationship){
					rateCustomRelationshipDao.remove(customRelationship.getRateCustomRelationshipId());
				}
			}
		}
	}
	
}
