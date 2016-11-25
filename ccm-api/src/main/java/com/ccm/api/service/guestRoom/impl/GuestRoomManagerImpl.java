package com.ccm.api.service.guestRoom.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.ChannelguestroomDAO;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.guestRoom.GuestRoomDao;
import com.ccm.api.dao.guestRoom.GuestroomI18nDAO;
import com.ccm.api.dao.guestRoom.GuestroomamenityDAO;
import com.ccm.api.dao.pms.form.Rmsta;
import com.ccm.api.dao.rmsta.RmstaDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.dao.user.UserDao;
import com.ccm.api.model.channel.Channelguestroom;
import com.ccm.api.model.guestroom.GuestRoom;
import com.ccm.api.model.guestroom.GuestroomI18n;
import com.ccm.api.model.guestroom.vo.GuestRoomVo;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.guestRoom.GuestRoomManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomTypeManager;
import com.ccm.api.util.DateUtil;
import com.taobao.api.domain.Hotel;
import com.taobao.api.domain.RoomType;

@Service("guestRoomManager")
public class GuestRoomManagerImpl extends GenericManagerImpl<GuestRoom, String> implements GuestRoomManager {
	@Autowired
	private GuestRoomDao guestRoomDao;
	@Autowired
	private GuestroomI18nDAO guestroomI18nDAO;
	@Autowired
	private ChannelgoodsDao channelgoodsDao;
	@Autowired
	private ChannelguestroomDAO channelguestroomDAO;
	@Autowired
	private GuestroomamenityDAO guestroomamenityDAO;
	@Autowired
	private RateplanDao rateplanDao;
	@Autowired
	private TaobaoRoomTypeManager taobaoRoomTypeManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	@Autowired
	private ChannelManager channelManager;
	@Autowired
	private HotelManager hotelManager;
	@Autowired
	private PictureManager pictureManager;
	@Autowired
	private RmstaDao rmstaDao;
	@Autowired
	private RsvtypeDao rsvtypeDao;
	@Resource
	private UserDao userDao;

	@Autowired
	public void setGuestRoomDao(GuestRoomDao guestRoomDao) {
		this.dao = guestRoomDao;
		this.guestRoomDao = guestRoomDao;
	}

	@Override
	public GuestroomI18n getGuestroomI18nByGRID(String guestRoomId) {
		return guestroomI18nDAO.getGuestroomI18nByGRID(guestRoomId);
	}

	public String getGuestRoomNameByRoomTypeCode(String roomTypeCode) {
		return guestroomI18nDAO.getGuestRoomNameByRoomTypeCode(roomTypeCode);
	}

	@Override
	public Channelguestroom getChannelguestroomByGRID(String guestRoomId) {
		return channelguestroomDAO.getChannelguestroomByGRID(guestRoomId);
	}

	@Override
	public Map<String, String> getGuestRoomAmenity(String guestRoomId) {
		return guestroomamenityDAO.getGuestRoomAmenity(guestRoomId);
	}

	@Override
	public List<Map<String, String>> getChannelGoodList(String hotelId) {
		List<Map<String, String>> goodsMap = guestRoomDao.getChannelGoodList(hotelId);
		return goodsMap;
	}

	@Override
	public void saveChannelGuestRoom(Channelguestroom cgr) {
		channelguestroomDAO.save(cgr);
	}

	@Override
	public Channelguestroom getChannelGuestRoom(String guestRoomId) {
		return channelguestroomDAO.getChannelguestroomByGRID(guestRoomId);
	}

	@Override
	public Map<String, String> getHotelRoomTypeNameMap(Hotel h) {
		List<RoomType> roomTypes = h.getRoomTypes();
		HashMap<String, String> roomTypeNameMap = new HashMap<String, String>();
		if (roomTypes != null) {
			for (RoomType rt : roomTypes) {
				roomTypeNameMap.put(rt.getName(), rt.getRid().toString());
			}
		}
		return roomTypeNameMap;
	}

	public String getRoomTypeStatus(String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("0", "待审核");
		map.put("1", "完成");
		map.put("2", "审核未通过");
		map.put("3", "停售");
		return map.get(key);
	}

	@Override
	public void deleteGuestRoom(String hotelId, String id) {
		GuestRoom gr = guestRoomDao.get(id);
		rmstaDao.delRmstaByHotelIdAndRoomTypeCode(hotelId, gr.getRoomTypeCode());
		this.guestRoomDao.softDelete(id);
	}

	@Override
	public List<Rmsta> getRmsta(String hotelId, String roomTypeCode) {
		return rmstaDao.getRmsta(hotelId, roomTypeCode);
	}

	@Override
	public boolean delRmstaByRoomNoAndHotelId(String hotelId, String roomNo) {
		return rmstaDao.delRmstaByRoomNoAndHotelId(hotelId, roomNo);
	}

	@Override
	public boolean checkExistsRoomTypeName(String hotelId, String roomtypename, String guestRoomId) {
		List<GuestroomI18n> gI18nList = guestRoomDao.checkExistsRoomTypeName(hotelId, roomtypename);
		if (gI18nList != null && gI18nList.size() > 0) {
			if (!guestRoomId.equals(gI18nList.get(0).getGuestroomid())) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean saveTBRoomQuotas(String hotelId, String roomTypeCode, String room_quotas) {
		List<Map> list = JSON.parseArray(room_quotas, Map.class);
		if (list == null || list.isEmpty()) {
			log.warn("hotelId:" + hotelId + "roomTypeCode:" + roomTypeCode + "room_quotas:" + room_quotas);
			return true;
		}
		for (Map<String, String> map : list) {
			if (map == null) {
				continue;
			}
			String date = map.get("date");
			Rsvtype saveRt = new Rsvtype();
			try {
				saveRt.setDate(DateUtil.convertStringToDate(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			saveRt.setHotelid(hotelId);
			saveRt.setRate(Double.parseDouble(map.get("price")) / 100);
			saveRt.setType(roomTypeCode);
			saveRt.setChannel("TB");
			saveRt.setSta(1);
//			saveRt.setAvailable(Integer.parseInt(map.get("num")));
			Rsvtype rt = rsvtypeDao.getRsvtypeByHotelIdDateType(hotelId, roomTypeCode, date);
			if (rt == null) {
				rsvtypeDao.save(saveRt);
			} else {
				rsvtypeDao.updateRsvtype(saveRt);
			}
		}
		return true;
	}

	@Override
	public List<GuestRoom> getEditedGuestRoom(String userId) {
		return guestRoomDao.getEditedGuestRoom(userId);
	}

	@Override
	public List<GuestRoomVo> getHotelTBRoom(String userId,String language) {
		List<GuestRoomVo> guestRoomVoList = new ArrayList<GuestRoomVo>();
		List<HotelVO> hotelVoList = hotelManager.getHotelInfoChainByUserId(userId,language);
		for (HotelVO hotelVO : hotelVoList) {
			GuestRoomVo grVo = new GuestRoomVo();
			grVo.setRoomList(getChannelGoodList(hotelVO.getHotelId()));
			grVo.setHotelId(hotelVO.getHotelId());
			grVo.setHotelName(hotelVO.getHotelName());
			guestRoomVoList.add(grVo);
		}
		return guestRoomVoList;
	}
}
