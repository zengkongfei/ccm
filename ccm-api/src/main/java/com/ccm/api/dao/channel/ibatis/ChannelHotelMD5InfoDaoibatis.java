package com.ccm.api.dao.channel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.ChannelHotelMD5InfoDao;
import com.ccm.api.model.channel.ChannelHotelMD5Info;

@Repository("channelHotelMD5InfoDao")
public class ChannelHotelMD5InfoDaoibatis extends GenericDaoiBatis<ChannelHotelMD5Info, String> implements ChannelHotelMD5InfoDao {
	public ChannelHotelMD5InfoDaoibatis() {
		super(ChannelHotelMD5Info.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelHotelMD5Info> getListByChannelId(String channelId,String hotelId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("channelId", channelId);
		param.put("hotelId", hotelId);
		List<ChannelHotelMD5Info> list = getSqlMapClientTemplate().queryForList("getListByChannelId",param);
		return list;
	}

	@Override
	public ChannelHotelMD5Info getChannelHotelMD5InfoByParam(String channelId,String type, String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("channelId", channelId);
		param.put("id", id);
		param.put("type", type);
		ChannelHotelMD5Info channelHotelMD5Info = (ChannelHotelMD5Info) getSqlMapClientTemplate().queryForObject("getChannelHotelMD5InfoByParam",param);
		return channelHotelMD5Info;
	}


}
