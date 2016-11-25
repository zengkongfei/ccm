package com.ccm.api.dao.log.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.HotelReceivepmsDao;
import com.ccm.api.model.log.HotelReceivepms;

@Repository("hotelReceivepmsDao")
public class HotelReceivepmsDaoIbatis extends GenericDaoiBatis<HotelReceivepms, String> implements HotelReceivepmsDao{

	public HotelReceivepmsDaoIbatis() {
		super(HotelReceivepms.class);
	}

	@Override
	public void deleteHotelReceivepmsByIds(List<String> hotelReceivepmsIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelReceivepmsIdList", hotelReceivepmsIds);
		getSqlMapClientTemplate().delete("deleteHotelReceivepmsByIds",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelReceivepms> getlive() {
		return getSqlMapClientTemplate().queryForList("getlive");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelReceivepms> getIsRemindHotelReceivepms() {
		return getSqlMapClientTemplate().queryForList("getIsRemindHotelReceivepms");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelReceivepms> getAllHotelReceivepms() {
		return getSqlMapClientTemplate().queryForList("getAllHotelReceivepms");
	}

	@Override
	public void updateHotelReceivepms(HotelReceivepms hotelReceivepms) {
		getSqlMapClientTemplate().update("updateHotelReceivepms",hotelReceivepms);
		
	}

	@Override
	public void deleteHotelReceivepmsByHotelIds(List<String> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelReceivepmsIdList", list);
		getSqlMapClientTemplate().delete("deleteHotelReceivepmsByHotelIds",map);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getHotelIdS(Integer number) {
		return getSqlMapClientTemplate().queryForList("getHotelIdS",number);
	}


}
