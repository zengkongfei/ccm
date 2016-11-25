/**
 * 
 */
package com.ccm.api.dao.hotel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.CustomDao;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomReportRecord;
import com.ccm.api.model.hotel.vo.CustomResult;

/**
 * @author Jenny
 * 
 */
@Repository("customDao")
public class CustomDaoibatis extends GenericDaoiBatis<Custom, String> implements CustomDao {

	public CustomDaoibatis() {
		super(Custom.class);
	}

	/**
	 * 根据条件查询记录
	 */
	@SuppressWarnings("unchecked")
	public List<Custom> getCustomByObj(Custom c) {
		return getSqlMapClientTemplate().queryForList("getCustomByObj", c);
	}

	/**
	 * 根据酒店ID查询记录
	 */
	@SuppressWarnings("unchecked")
	public List<Custom> getCustomByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getCustomByHotelId", hotelId);
	}

	/**
	 * 查询列表并可分页显示
	 */
	@SuppressWarnings("unchecked")
	public CustomResult searchCustom(CustomCreteria creteria) {
		CustomResult searchResult = new CustomResult();
		int count = (Integer) getSqlMapClientTemplate().queryForObject("countCustom", creteria);
		searchResult.setTotalCount(count);

		List<Custom> list = getSqlMapClientTemplate().queryForList("searchCustom", creteria);
		searchResult.setResultList(list);

		return searchResult;
	}
	/*
	 * 根据customId查找
	 */
	@Override
	public Custom searchCustomById(String customId) {
		Custom searchResult = new Custom();
		searchResult=(Custom) getSqlMapClientTemplate().queryForObject("searchCustomById", customId);
		return searchResult;
	}
	/**
	 * 根据customId累加
	 * @param customId
	 */
	@Override
	public void updateCustomCumulative(Custom custom) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("customId", custom.getCustomId());
		map.put("income", custom.getIncome());
		getSqlMapClientTemplate().update("updateCustomCumulative", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomReportRecord> queryBookingDepositReport(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("queryBookingDepositReport", map);
	}

	@Override
	public Integer getBookingDepositReportCount(Map<String, Object> map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("getBookingDepositReportCount", map);
	}
	@Override
	public void updateTotalRoomRev(Custom custom) {
		getSqlMapClientTemplate().update("updateTotalRoomRev", custom);
	}

	@Override
	public Custom searchCustomByHotelIdAndAccessCode(String hotelId,String accessCode) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hotelId",hotelId);
		map.put("accessCode", accessCode);
		Custom custom=(Custom) getSqlMapClientTemplate().queryForObject("searchCustomByHotelIdAndAccessCode", map);
		return custom;
	}

	@Override
	public List<Custom> searchCustomByHotelIdAndChannelId(String hotelId,
			String channelId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hotelId",hotelId);
		map.put("channelId", channelId);
		return getSqlMapClientTemplate().queryForList("searchCustomByHotelIdAndChannelId",map);
	}
	/**
	 * 根据profileID 和 hotelId添加profileMessage
	 * @param custom
	 */
	@Override
	public void updateProfileMessage(Custom custom){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("customId", custom.getCustomId());
		map.put("profileMessage", custom.getProfileMessage());
		getSqlMapClientTemplate().update("updateProfileMessage", map);
	}
}
