package com.ccm.api.dao.ratePlan.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RateCancelRelationshipDao;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.util.CommonUtil;

@Repository("rateCancelRelationshipDao")
public class RateCancelRelationshipDaoibatis extends GenericDaoiBatis<RateCancelRelationship, String> implements RateCancelRelationshipDao {

	public RateCancelRelationshipDaoibatis() {
		super(RateCancelRelationship.class);
	}

	@Override
	public RateCancelRelationship addRateCancelRelationship(RateCancelRelationship rateCancelRelationship) {
		return (RateCancelRelationship) this.getSqlMapClientTemplate().insert("addRateCancelRelationship", rateCancelRelationship);
	}

	@Override
	public void deleteRateCancelRelationshipByRatePlanId(String ratePlanId) {
		getSqlMapClientTemplate().delete("deleteRateCancelRelationshipByRatePlanId", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateCancelRelationship> getRateCancelRelationshipByRatePlanId(String ratePlanId) {
		List<RateCancelRelationship> rcrList = getSqlMapClientTemplate().queryForList("getRateCancelRelationshipByRatePlanId", ratePlanId);
		if (rcrList == null) {
			rcrList = new ArrayList<RateCancelRelationship>();
		}
		return rcrList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateCancelRelationship> getRateCancelPolicyByRatePlanId(String ratePlanId) {
		List<RateCancelRelationship> rcrList = getSqlMapClientTemplate().queryForList("getRateCancelPolicyByRatePlanId", ratePlanId);
		if (rcrList == null) {
			rcrList = new ArrayList<RateCancelRelationship>();
		}
		return rcrList;
	}
	@Override
	public RateCancelRelationship get180DayRateCancel(String hotelId,
			String ratePlanCode,String day) {
		HashMap<String,String> params = new HashMap<String,String>();
        params.put("hotelId", hotelId);
        params.put("ratePlanCode", ratePlanCode);
        params.put("day", day);
        List list =  getSqlMapClientTemplate().queryForList("get180DayRateCancel",params);
		return (CommonUtil.isNotEmpty(list) ? (RateCancelRelationship)list.get(0) : null);
	}
	@SuppressWarnings("unchecked")
	public List<RateCancelRelationship> getRateCancelPolicyI18nByRatePlanId(String hotelId, String ratePlanId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("ratePlanId", ratePlanId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRateCancelPolicyI18nByRatePlanId", params);
	}

}
