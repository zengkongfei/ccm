package com.ccm.api.service.roomQuota.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.dao.guestRoom.GuestRoomDao;
import com.ccm.api.dao.rmsta.RmstaDao;
import com.ccm.api.dao.roomQuota.RoomQuotaDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.model.guestroom.GuestRoom;
import com.ccm.api.model.roomQuota.RoomQuota;
import com.ccm.api.model.roomQuota.vo.RoomService;
import com.ccm.api.model.roomQuota.vo.RoomsCreateVO;
import com.ccm.api.model.roomQuota.vo.RoomsInitVO;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.taobaoVO.RoomQuotaVO;
import com.ccm.api.model.taobaoVO.RoomVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.guestRoom.GuestRoomManager;
import com.ccm.api.service.roomQuota.RoomQuotaManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomTypeManager;
import com.ccm.api.util.DateUtil;
import com.taobao.api.domain.Room;

@Service("roomQuotaManager")
public class RoomQuotaManagerImpl extends GenericManagerImpl<RoomQuota, String> implements RoomQuotaManager {

	@Autowired
	private RoomQuotaDao roomQuotaDao;
	@Autowired
	private TaobaoRoomManager taobaoRoomManager;
	@Autowired
	private TaobaoRoomTypeManager taobaoRoomTypeManager;

	@Autowired
	private RsvtypeDao rsvtypeDao;
	@Autowired
	private GuestRoomDao guestRoomDao;
	@Autowired
	private RmstaDao rmstaDao;
	@Autowired
	private ChannelHotelDao channelHotelDao;
	@Autowired
	private GuestRoomManager guestRoomManager;

	@Override
	public boolean queryPriceCalendar(RoomsCreateVO vo) {
		int count = roomQuotaDao.queryPriceCalendar(vo);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<RoomsInitVO> getRoomsInitvos(String hid, String flag) {
		List<RoomsInitVO> list = roomQuotaDao.getRoomsInitvos(hid, flag);
		if (null != list) {
			for (RoomsInitVO vo : list) {
				if (flag.equals("1")) {// 未发布
					vo.setStatus("0");
				}
				// 已发布status参考淘宝发布状态
			}
		}
		return list;
	}

	/**
	 * 转换RoomQuota对象为List<RoomQuotaVO>
	 * 
	 * @param rq
	 * @return
	 */
	public List<RoomQuotaVO> roomQuotaConvert(RoomsCreateVO rq) {
		List<RoomQuotaVO> volist = new ArrayList<RoomQuotaVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = rq.getBeginDate();
		Date endDate = rq.getEndDate();
		// 如果结束时间距当前时间大于90天设置结束时间为当前时间+90天。
		if (DateUtil.dateDiff(beginDate, endDate) >= 90) {
			endDate = new Date(beginDate.getTime() + 89L * 24L * 60L * 60L * 1000L);
		}

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		while (begin.compareTo(end) <= 0) {
			RoomQuotaVO vo = new RoomQuotaVO();
			vo.setNum(rq.getAmount());
			vo.setDate(sdf.format(begin.getTime()));

			int w = begin.get(Calendar.DAY_OF_WEEK);

			if (w == Calendar.FRIDAY || w == Calendar.SATURDAY) {
				vo.setPrice((int) rq.getWeekPrice());
			} else {
				vo.setPrice((int) rq.getGeneralPrice());
			}
			volist.add(vo);
			// 循环，每次天数加1
			begin.set(Calendar.DATE, begin.get(Calendar.DATE) + 1);
		}
		return volist;
	}

	/**
	 * RoomsCreateVO对象转换为RoomQuota
	 * 
	 * @param vo
	 * @return
	 */
	public RoomQuota roomsCreateVOConvert(RoomsCreateVO vo) {
		RoomQuota rq = new RoomQuota();
		rq.setRoomQuotaId(UUID.randomUUID().toString().replace("-", ""));
		rq.setChannelId(vo.getChannelId());
		rq.setHotelId(vo.getHotelId());
		rq.setGuestRoomId(vo.getGuestRoomId());
		rq.setBeginDate(vo.getBeginDate());
		rq.setEndDate(vo.getEndDate());
		rq.setGeneralPrice(vo.getGeneralPrice());
		rq.setWeekPrice(vo.getWeekPrice());
		rq.setAmount(vo.getAmount());
		return rq;
	}

	@Override
	public RoomService getRoomsService(String roomTypeId) {
		RoomService rs = new RoomService();
		List<String> codeList = roomQuotaDao.getRoomsService(roomTypeId);
		if (null != codeList) {
			for (String str : codeList) {
				if (str.equals("bar")) {
					rs.setBar(true);
				}
				if (str.equals("catv")) {
					rs.setCatv(true);
				}
				if (str.equals("ddd")) {
					rs.setDdd(true);
				}
				if (str.equals("idd")) {
					rs.setIdd(true);
				}
				if (str.equals("pubtoilet")) {
					rs.setPubtoliet(true);
				}
				if (str.equals("toilet")) {
					rs.setToilet(true);
				}
			}
		}
		return rs;
	}

	@Override
	public List<RoomQuotaVO> getRoomQuotaVOList(String hotelId, String roomTypeCode) {
		List<RoomQuotaVO> voList = null;
		List<Rsvtype> rList = rsvtypeDao.getDate90RsvtypeList(hotelId, roomTypeCode, null);
		if (!rList.isEmpty()) {
			int roomQuoAmount = 0;
			for (Rsvtype r : rList) {
				// 设置房量
				roomQuoAmount = getRoomQuoAmount(r);
				r.setAvailable(roomQuoAmount);
			}

			voList = getRoomQuotaList(rList);
		}
		return voList;
	}

	@Override
	public List<RoomQuotaVO> getRoomQuotaList(List<Rsvtype> rList) {
		List<RoomQuotaVO> voList = new ArrayList<RoomQuotaVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RoomQuotaVO vo = null;
		// 如果只有一条记录，则增加一条记录，设置房量为0，房价为999999L
		if (rList.size() == 1) {
			Date date = rList.get(0).getDate();
			Rsvtype rsvtype = new Rsvtype();
			rsvtype.setAvailable(0);
			rsvtype.setDate(new Date(date.getTime() + 24L * 60L * 60L * 1000L));
			rsvtype.setRate(999999d);
			rList.add(rsvtype);
		}
		// 截取当前日期的时分秒
		Date currentDate = null;// 当前日期
		String dateStr = sdf.format(new Date());
		try {
			currentDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date lastDate = rList.get(rList.size() - 1).getDate();// 取出rList中最后一天的日期
		int length = DateUtil.dateDiff(currentDate, lastDate);// 计算自当天起到最后一天日期的天数
		for (int i = 0; i <= length; i++) {
			vo = new RoomQuotaVO();
			for (Rsvtype r : rList) {
				if (currentDate.equals(r.getDate())) {
					vo.setNum(r.getAvailable());
					vo.setDate(sdf.format(r.getDate()));
					vo.setPrice((int) (r.getRate() * 100));
					break;
				}
			}
			if (null == vo.getDate()) {
				vo.setNum(0);
				vo.setDate(sdf.format(currentDate));
				vo.setPrice((int) (999999L * 100));
			}
			voList.add(vo);
			currentDate = new Date(currentDate.getTime() + 24L * 60L * 60L * 1000L);
		}
		return voList;
	}

	/**
	 * Room转化为RoomsInitVO
	 */
	public RoomsInitVO roomConvert(Room room) {
		RoomsInitVO vo = new RoomsInitVO();
		vo.setGid(room.getGid().toString());
		vo.setGname(room.getTitle());
		vo.setHid(room.getHid().toString());
		vo.setHname(room.getHotel().getName());
		vo.setRid(room.getRid().toString());
		vo.setRname(room.getRoomType().getName());
		vo.setStatus(String.valueOf(room.getStatus()));
		vo.setIid(room.getIid().toString());
		return vo;
	}

	@Override
	public String getRoomTypeCode(String roomTypeId) {
		return roomQuotaDao.getRoomTypeCode(roomTypeId);
	}

	@Override
	public int getRoomQuoAmount(Rsvtype rsvtype) {
		Integer roomQuoAmount = 0;
		int count = rmstaDao.getCountRmsta(rsvtype.getHotelid(), rsvtype.getType());
		if (count == 0) {
			roomQuoAmount = roomQuotaDao.getRoomQuotaAmount(rsvtype.getHotelid(), rsvtype.getType());
		} else {
			roomQuoAmount = rsvtype.getAvailable();
		}
		if (null == roomQuoAmount) {
			roomQuoAmount = 0;
		}
		return roomQuoAmount;
	}

	@Override
	public String getInitMessage(String userId) {
		// 当前用户下有未发布的宝贝，请发布宝贝后再做后续操作！
		int gidCount = roomQuotaDao.getGidIsNullCount(userId);
		if (gidCount > 0) {
			return "1";
		}
		// 当前用户下的宝贝还未完成创建房间的操作，请创建好所有宝贝的房间！
		List<String> roomTypeCodeList = guestRoomDao.getRoomTypeCodeByUserId(userId);
		if (!roomTypeCodeList.isEmpty()) {
			for (String roomTypeCode : roomTypeCodeList) {
				int rmstaCount = rmstaDao.getIsNotRmstaCount(userId, roomTypeCode);
				if (rmstaCount == 0) {
					return "2";
				}
			}
		}
		return "";
	}

	public String releaseHotelRoomQuota(String hotelId) {
		StringBuffer masages = new StringBuffer();
		// 查询酒店下所有未删除并且有Gid的宝贝
		List<GuestRoom> grList = guestRoomDao.getGuestRoomByHotelIdAndGidIsNotNull(hotelId);
		if (!grList.isEmpty()) {
			for (GuestRoom gr : grList) {
				// 发布房态
				try {
					taobaoRoomManager.releaseRoomQuota(hotelId, gr.getRoomTypeCode(), "1");
				} catch (Exception e) {
					masages.append(e.getMessage()).append(".");
					continue;
				}
			}
		}
		return masages.toString();
	}

	/**
	 * 提取RoomsInitVO中的gid
	 * 
	 * @param localvos
	 * @return
	 */
	public List<Long> roomsInitVOConvertGid(List<RoomsInitVO> localvos) {
		List<Long> gids = new ArrayList<Long>();
		for (RoomsInitVO vo : localvos) {
			gids.add(Long.parseLong(vo.getGid()));
		}
		return gids;
	}

	/**
	 * 获取房量 如果房量表中没有创建则取价格日历中房量，并创建房号保存在房量表中
	 * 
	 * @param hotelId
	 * @param roomTypeCode
	 * @return
	 */
	public int getRoomNum(String hotelId, String roomTypeCode, String roomTypeName) {
		int roomNum = 0;
		List<String> roomNos = new ArrayList<String>();
		// 取房量表中房量
		roomNum = rmstaDao.getCountRmsta(hotelId, roomTypeCode);
		if (roomNum == 0) {
			List<Integer> availables = rsvtypeDao.getAvailableListOfRsvtype(hotelId, roomTypeCode);
			if (availables.isEmpty() || availables.contains(null)) {
				roomNum = 0;
			} else {
				roomNum = Collections.max(availables);
				if (roomNum == 0) {
					roomNum = 0;
				} else {
					for (int i = 0; i < roomNum; i++) {
						roomNos.add(roomTypeName + (i + 1));
					}
					rmstaDao.saveRmsta(hotelId, roomNos, roomTypeCode);
				}
			}
		}
		return roomNum;
	}

	@Override
	public List<RoomsInitVO> getRoomsInitvos(String hid, String flag,
			String language) {
		return roomQuotaDao.getRoomsInitvos(hid, flag, language);
	}

	@Override
	public RoomVO getRoomVO(String hotelId, String roomTypeId, String language) {
		return roomQuotaDao.getRoomVO(hotelId, roomTypeId, language);
	}
}
