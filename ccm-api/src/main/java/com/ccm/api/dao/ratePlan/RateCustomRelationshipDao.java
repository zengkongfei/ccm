package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.ratePlan.RateCustomRelationship;

public interface RateCustomRelationshipDao extends GenericDao<RateCustomRelationship, String> {

	/**
	 * 根据房价ID删除
	 * 
	 * @param ratePlanId
	 */
	void deleteRateCustomRelationshipByRatePlanId(String ratePlanId);

	/**
	 * 根据房价ID查找
	 * 
	 * @param ratePlanId
	 * @return
	 */
	List<String> getCustomIdByRatePlanId(String ratePlanId);

	/**
	 * 根据房价ID,客户信息查询房价客户关系
	 * 
	 * @param c
	 * @param ratePlanId
	 * @return
	 */
	Custom getCustomIdByRateCustom(Custom c, String ratePlanId);

	/**
	 * 根据房价code,客户信息获取房价客户关系对象
	 * 
	 * @param c
	 * @param ratePlanCode
	 * @return
	 */
	RateCustomRelationship getCustomByRateCustom(Custom c, String ratePlanCode);
	
	/**
	 * 根据房价ID查找
	 * @param ratePlanId
	 * @return
	 */
	public List<Custom> getCustomByRatePlanId(String ratePlanId);
	
	/**
	 * 
	 * @param ratePlanId
	 * @param customId
	 * @return
	 */
	RateCustomRelationship getRateCustomRelationshipByRatePlanIdAndCustomId(String ratePlanId, String customId);

	List<String> getAccessRatePlanIdByChannelIds(String hotelId,List<Channel> channelList);
}
