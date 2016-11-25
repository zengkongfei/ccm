/**
 * 
 */
package com.ccm.api.dao.channel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.model.channel.ChannelHotel;

/**
 * @author Jenny
 * 
 */
@Repository("channelHotelDao")
public class ChannelHotelDaoibatis extends GenericDaoiBatis<ChannelHotel, String> implements ChannelHotelDao {

	public ChannelHotelDaoibatis() {
		super(ChannelHotel.class);
	}

	/**
	 * 通过渠道ID,酒店ID逻辑删除渠道酒店表
	 */
	public void logicDeleteChannelHotel(ChannelHotel ch) {
		getSqlMapClientTemplate().update("logicDeleteChannelHotel", ch);
	}

	/**
	 * 通过酒店ID查询渠道酒店表
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelHotel> getChannelHotelCodeByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getChannelHotelCodeByHotelId", hotelId);
	}

	/**
	 * 通过渠道ID,酒店ID,时间查询渠道酒店表
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelHotel> getChannelHotelByHotelIdChannelId(ChannelHotel ch) {
		return getSqlMapClientTemplate().queryForList("getChannelHotelByHotelIdChannelId", ch);
	}

	/**
	 * 通过渠道代码,酒店ID查询渠道酒店表
	 */
	public ChannelHotel getChannelHotelByHotelIdChannelCode(String channelCode, String hotelId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("channelCode", channelCode);
		param.put("hotelId", hotelId);
		return (ChannelHotel) getSqlMapClientTemplate().queryForObject("getChannelHotelByHotelIdChannelCode", param);
	}

	/**
	 * 通过渠道代码，渠道酒店代码查询酒店ID
	 */
	public String getHotelIdByChannelCodeChannelHotelCode(String channelCode, String channelHotelCode) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("channelCode", channelCode);
		param.put("channelHotelCode", channelHotelCode);
		return (String) getSqlMapClientTemplate().queryForObject("getHotelIdByChannelCodeChannelHotelCode", param);
	}

	@Override
	public void deleteCHByGroupIdAndHotelId(String groupId, String hotelId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("groupId", groupId);
		param.put("hotelId", hotelId);
		getSqlMapClientTemplate().delete("deleteCHByGroupIdAndHotelId",param);
	}

	@Override
	public void delete(String channelHotelId) {
		getSqlMapClientTemplate().delete("deleteChannelHotelById",channelHotelId);
	}

}