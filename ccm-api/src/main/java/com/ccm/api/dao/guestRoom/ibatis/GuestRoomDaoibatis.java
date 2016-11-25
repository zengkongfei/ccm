/**
 * 
 */
package com.ccm.api.dao.guestRoom.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.guestRoom.GuestRoomDao;
import com.ccm.api.model.guestroom.GuestRoom;
import com.ccm.api.model.guestroom.GuestroomI18n;
import com.ccm.api.model.ws.vo.availability.AvailabilityRQVO;
import com.ccm.api.model.ws.vo.availability.RoomTypeRSVO;

@Repository("guestRoomDao")
public class GuestRoomDaoibatis extends GenericDaoiBatis<GuestRoom, String> implements GuestRoomDao {

	public GuestRoomDaoibatis() {
		super(GuestRoom.class);
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, String>> getChannelGoodList(String hotelId) {
        return getSqlMapClientTemplate().queryForList("getChannelGoodList",hotelId);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, String>> getGuestRoomIdMap(String userId) {
        return getSqlMapClientTemplate().queryForList("getGuestRoomIdMap",userId);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<GuestroomI18n> checkExistsRoomTypeName(String hotelId, String roomtypename) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("hotelId", hotelId);
        map.put("roomTypeName", roomtypename);
        return getSqlMapClientTemplate().queryForList("checkGuestRoomName",map);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRoomTypeCodeByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList("getRoomTypeCodeByUserId",userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestRoom> getEditedGuestRoom(String userId) {
		return getSqlMapClientTemplate().queryForList("getEditedGuestRoom",userId);
	}

	@Override
	public GuestRoom getGuestRoomByGid(String gid) {
		return (GuestRoom) getSqlMapClientTemplate().queryForObject("getGuestRoomByGid",gid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestRoom> getGuestRoomByHotelIdAndGidIsNotNull(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getGuestRoomByHotelIdAndGidIsNotNull", hotelId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeRSVO> getGeneralRoomTypeRSVO(AvailabilityRQVO availability) {
		return getSqlMapClientTemplate().queryForList("getGeneralRoomTypeRSVO", availability);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeRSVO> getDetailRoomTypeRSVO(AvailabilityRQVO availability) {
		return getSqlMapClientTemplate().queryForList("getDetailRoomTypeRSVO", availability);
	}
}
