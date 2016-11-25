package com.ccm.api.dao.roomQuota.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.roomQuota.RoomQuotaDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.roomQuota.RoomQuota;
import com.ccm.api.model.roomQuota.vo.RoomsCreateVO;
import com.ccm.api.model.roomQuota.vo.RoomsInitVO;
import com.ccm.api.model.taobaoVO.RoomVO;

@Repository("roomQuotaDao")
public class RoomQuotaDaoibatis extends GenericDaoiBatis<RoomQuota, String> implements RoomQuotaDao {

	public RoomQuotaDaoibatis() {
		super(RoomQuota.class);
	}

	@Override
	public RoomVO getRoomVO(String hotelId,String roomTypeId) {
		return this.getRoomVO(hotelId, roomTypeId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RoomVO getRoomVO(String hotelId,String roomTypeId,String language) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		map.put("roomTypeId", roomTypeId);
		map.put("language", language);
		return (RoomVO) getSqlMapClientTemplate().queryForObject("getRoomVO", map);
	}

	@Override
	public void saveRoomQuota(RoomQuota roomQuota) {
		getSqlMapClientTemplate().insert("saveRoomQuota", roomQuota);
	}

	@Override
	public int queryPriceCalendar(RoomsCreateVO vo) {
		return (Integer) getSqlMapClientTemplate().queryForObject("queryPriceCalendar", vo);
	}

	@Override
	public void updateChannelGoodsCode(String channelGoodsCode,String roomTypeId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("channelGoodsCode", channelGoodsCode);
		map.put("roomTypeId", roomTypeId);
		getSqlMapClientTemplate().update("updateChannelGoodsCode", map);
	}

	
	@Override
	public List<RoomsInitVO> getRoomsInitvos(String hid, String flag) {
		return this.getRoomsInitvos(hid, flag, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomsInitVO> getRoomsInitvos(String hid, String flag,String language) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hid", hid);
		map.put("flag", flag);
		map.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRoomsInitvos", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRoomsService(String roomTypeId) {
		return getSqlMapClientTemplate().queryForList("getRoomsService", roomTypeId);
	}

	@Override
	public String getRoomTypeCode(String roomTypeId) {
		return (String) getSqlMapClientTemplate().queryForObject("getRoomTypeCode", roomTypeId);
	}

	@Override
	public String getRoomTypeID(String roomTypeCode) {
		return(String) getSqlMapClientTemplate().queryForObject("getRoomTypeId", roomTypeCode);
	}

	@Override
	public Integer getRoomQuotaAmount(String hotelId, String roomTypeCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		return (Integer) getSqlMapClientTemplate().queryForObject("getRoomQuotaAmount", map);
	}

	@Override
	public Integer getGidIsNullCount(String userId) {
		return (Integer) getSqlMapClientTemplate().queryForObject("getGidIsNullCount", userId);
	}
	
	@Override
	public void deleteRoomquota(RoomQuota roomQuota){
		getSqlMapClientTemplate().delete("deleteRoomquota", roomQuota);
	}
}
