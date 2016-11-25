package com.ccm.api.dao.rmsta.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.pms.form.Rmsta;
import com.ccm.api.dao.rmsta.RmstaDao;
import com.ccm.api.model.rsvtype.Rsvtype;

@Repository("rmstaDao")
public class RmstaDaoibatis extends GenericDaoiBatis<Rsvtype, String> implements RmstaDao {

	public RmstaDaoibatis() {
		super(Rsvtype.class);
	}

	@Override
	public int getCountRmsta(String hotelId, String roomTypeCode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		return (Integer) getSqlMapClientTemplate().queryForObject("getCountRmsta",map);
	}

    @Override
    public List<Rmsta> getRmsta(String hotelId, String roomTypeCode) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("hotelId", hotelId);
        map.put("roomTypeCode", roomTypeCode);
        return getSqlMapClientTemplate().queryForList("getRmsta",map);
    }

    @Override
    public boolean delRmstaByRoomNoAndHotelId(String hotelId, String roomNo) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("hotelId", hotelId);
        map.put("roomNo", roomNo);
        return getSqlMapClientTemplate().delete("delRmstaByRoomNoAndHotelId", map) > 0;
    }

    @Override
    public void delRmstaByHotelIdAndRoomTypeCode(String hotelId,
            String roomTypeCode) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("hotelId", hotelId);
        map.put("roomTypeCode", roomTypeCode);
        getSqlMapClientTemplate().delete("delRmstaByHotelIdAndRoomTypeCode", map);
    }

	@Override
	public int getIsNotRmstaCount(String userId, String roomTypeCode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("roomTypeCode", roomTypeCode);
		return (Integer) getSqlMapClientTemplate().queryForObject("getIsNotRmstaCount",map);
	}

	@Override
	public int getValidRoomno(String roomNo, String roomTypeCode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("roomNo", roomNo);
		map.put("roomTypeCode", roomTypeCode);
		return (Integer) getSqlMapClientTemplate().queryForObject("getValidRoomno",map);
	}

	@Override
	public void saveRmsta(String hotelId, List<String> roomNos, String roomTypeCode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		for (int i = 0; i < roomNos.size(); i++) {
		    String roomNo = roomNos.get(i);
		    if(StringUtils.hasText(roomNo)){
		        map.put("roomNo", roomNos.get(i).trim());
	            try {
	                getSqlMapClientTemplate().insert("saveRmsta", map);
	            } catch (DataAccessException e) {
	                throw new BizException("", "房间号不能重复！");
	            }
		    }
		}
	}
}
