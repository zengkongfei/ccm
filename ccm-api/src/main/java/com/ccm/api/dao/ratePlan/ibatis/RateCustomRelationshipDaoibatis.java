package com.ccm.api.dao.ratePlan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.ratePlan.RateCustomRelationship;

@Repository("rateCustomRelationshipDao")
public class RateCustomRelationshipDaoibatis extends GenericDaoiBatis<RateCustomRelationship, String> implements RateCustomRelationshipDao {

	public RateCustomRelationshipDaoibatis() {
		super(RateCustomRelationship.class);
	}

	@Override
	public void deleteRateCustomRelationshipByRatePlanId(String ratePlanId) {
		getSqlMapClientTemplate().delete("deleteRateCustomRelationshipByRatePlanId", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCustomIdByRatePlanId(String ratePlanId) {
		return getSqlMapClientTemplate().queryForList("getCustomIdByRatePlanId", ratePlanId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Custom> getCustomByRatePlanId(String ratePlanId) {
		return getSqlMapClientTemplate().queryForList("getCustomByRatePlanId", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	public Custom getCustomIdByRateCustom(Custom c, String ratePlanId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ratePlanId", ratePlanId);
		param.put("hotelId", c.getHotelId());
		param.put("type", c.getType());
		param.put("accessCode", c.getAccessCode());
		List<Custom> rcrList = getSqlMapClientTemplate().queryForList("getCustomIdByRateCustom", param);
		if (rcrList != null && !rcrList.isEmpty()) {
			return rcrList.get(0);
		}
		return null;
	}

	@Override
	public RateCustomRelationship getCustomByRateCustom(Custom c, String ratePlanCode) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ratePlanCode", ratePlanCode);
		param.put("hotelId", c.getHotelId());
		param.put("type", c.getType());
		param.put("accessCode", c.getAccessCode());
		return (RateCustomRelationship) getSqlMapClientTemplate().queryForObject("getCustomByRateCustom", param);
	}
	/**
	 * 
	 */
	public RateCustomRelationship getRateCustomRelationshipByRatePlanIdAndCustomId(String ratePlanId, String customId)
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("ratePlanId", ratePlanId);
		param.put("customId", customId);
		return (RateCustomRelationship) getSqlMapClientTemplate().queryForObject("getRateCustomRelationshipByRatePlanIdAndCustomId", param);
	}
	
	@Override
	public List<String> getAccessRatePlanIdByChannelIds(String hotelId,List<Channel> channelList){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelId", hotelId);
		param.put("channelList", channelList);
		return getSqlMapClientTemplate().queryForList("getAccessRatePlanIdByChannelIds", param);
	}
}
