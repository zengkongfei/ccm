package com.ccm.api.service.roomType.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.ChannelguestroomDAO;
import com.ccm.api.dao.common.DictCodeDao;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.channel.Channelguestroom;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.common.vo.InvokeResponse;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.UserInitStatus;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.model.enume.PictureType;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.ReceiveReqResLog;
import com.ccm.api.model.roomType.Amenity;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.RoomTypeI18n;
import com.ccm.api.model.roomType.vo.RoomTypeCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeResult;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.log.ReceiveReqResManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.taobao.api.domain.XRoom;
import com.taobao.api.domain.XRoomType;

@Service("roomTypeManager")
public class RoomTypeManagerImpl extends GenericManagerImpl<RoomType, String> implements RoomTypeManager {

	@Autowired
	private RoomTypeDao roomTypeDao;
	@Autowired
	private HotelMCDao hotelMCDao;
	@Autowired
	private DictCodeDao dictCodeDao;
	@Autowired
	private UserManager userManager;
	@Autowired
	private DictCodeManager dictCodeManager;

	@Resource
	private TaobaoApiManager taobaoApiManager;
	@Resource
	private PictureManager pictureManager;
	@Resource
	private ChannelguestroomDAO channelguestroomDAO;
	@Resource
	private ChannelgoodsDao channelgoodsDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private ChannelHotelDao channelHotelDao;
	@Resource
	private ReceiveReqResManager receiveReqResManager;
	
	 

	@Resource
	public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
		this.dao = roomTypeDao;
	}

	@Override
	public RoomTypeVO saveRoomType(RoomTypeVO vo) {
		RoomType vo1 = new RoomType();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = roomTypeDao.addRoomType(vo1);
		vo.setRoomTypeId(vo1.getRoomTypeId());

		// 如果为空,或者记录为空
		if (vo.getRoomTypeI18nList() == null || vo.getRoomTypeI18nList().size() == 0) {
			List<RoomTypeI18n> i18nList = new ArrayList<RoomTypeI18n>();
			i18nList.add(this.getDefaultLanguageI18n(vo));
			vo.setRoomTypeI18nList(i18nList);
		}

		// 循环添加多语言数据
		for (RoomTypeI18n roomTypeI18n : vo.getRoomTypeI18nList()) {
			RoomTypeI18n i18n = roomTypeVO2RoomTypeI18n(vo);
			i18n.setLanguage(roomTypeI18n.getLanguage());
			i18n.setRoomTypeName(roomTypeI18n.getRoomTypeName());
			i18n.setDescription(roomTypeI18n.getDescription());
			roomTypeDao.addRoomTypeI18n(i18n);
		}

		//如果上传了图片,则将房型记录和图片匹配上
		if(StringUtils.hasText(vo.getPicId())){
			pictureManager.updatePictureBizIdToPId(vo.getRoomTypeId(), vo.getPicId());
		}
		
		return vo;
	}

	@Override
	public void updateRoomType(RoomTypeVO vo) {
		RoomType vo1 = new RoomType();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roomTypeDao.updateRoomType(vo1);

		// 如果批量修改多语言(MC系统维护时)
		if (vo.getRoomTypeI18nList() != null) {
			// 批量删除多语言记录
			roomTypeDao.deleteRoomTypeI18nByRoomTypeId(vo.getRoomTypeId());

			// 循环添加多语言数据
			for (RoomTypeI18n roomTypeI18n : vo.getRoomTypeI18nList()) {
				RoomTypeI18n i18n = roomTypeVO2RoomTypeI18n(vo);
				i18n.setLanguage(roomTypeI18n.getLanguage());
				i18n.setRoomTypeName(roomTypeI18n.getRoomTypeName());
				i18n.setDescription(roomTypeI18n.getDescription());
				roomTypeDao.addRoomTypeI18n(i18n);
			}
			// 其他情况下修改默认的语言对象
		} else {
			RoomTypeI18n i18n = roomTypeVO2RoomTypeI18n(vo);
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setRoomTypeName(vo.getRoomTypeName());
			i18n.setDescription(vo.getDescription());
			roomTypeDao.updateRoomTypeI18n(i18n);
		}
	}

	public void saveOrUpdateRoomTypeI18n(RoomTypeVO roomTypeVO) {
		if (StringUtils.hasText(roomTypeVO.getRoomTypeId())) {
			updateRoomType(roomTypeVO);
		} else {
			saveRoomType(roomTypeVO);
			userManager.updateStatus(UserInitStatus.RoomTypeCreated);
		}

		// 保存房型服务
		saveRoomTypeAmenity(roomTypeVO.getRoomTypeId(), roomTypeVO.getService());
	}

	@Override
	public void deleteRoomType(RoomTypeVO vo) {
		RoomType vo1 = new RoomType();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roomTypeDao.deleteRoomType(vo1);

		// 批量删除多语言表中的数据
		roomTypeDao.deleteRoomTypeI18nByRoomTypeId(vo.getRoomTypeId());
	}

	@Override
	public RoomTypeVO getRoomTypeByCode(RoomTypeVO vo) {
		return roomTypeDao.getRoomTypeByCode(vo);
	}

	/**
	 * 根据酒店code和房型code获取roomTypeId,physicalRooms
	 */
	public RoomType getRoomTypeByHotelCode(String roomTypeCode, String hotelCode) {
		return roomTypeDao.getRoomTypeByHotelCode(roomTypeCode, hotelCode);
	}

	@Override
	public RoomTypeVO getRoomTypeById(String roomTypeId) {
		return roomTypeDao.getRoomTypeById(roomTypeId);
	}

	@Override
	public RoomTypeVO getRoomTypeById(String roomTypeId, String language) {
		return roomTypeDao.getRoomTypeById(roomTypeId, language);
	}

	@Override
	public List<RoomTypeI18n> getRoomTypeI18ns(String roomTypeId) {
		return roomTypeDao.getRoomTypeI18ns(roomTypeId);
	}

	@Override
	public RoomTypeResult searchRoomType(RoomTypeCreteria creteria) {
		RoomTypeResult result = new RoomTypeResult();
		List<RoomTypeVO> voList = roomTypeDao.searchRoomType(creteria);
		if (!voList.isEmpty()) {
			for (RoomTypeVO vo : voList) {
				if (null != vo.getHotelId() && !"".equals(vo.getHotelId())) {
					HotelVO hotel = hotelMCDao.getHotelByIdMC(vo.getHotelId());
					if (null != hotel) {
						vo.setHotelName(hotel.getHotelName());
					}
				}
				if (null != vo.getBedTypeCode() && !"".equals(vo.getBedTypeCode())) {
					DictCode code = dictCodeDao.searchByCodeNo(DictName.BEDTYPE, vo.getBedTypeCode().toString());
					if (null != code) {
						vo.setBedTypeName(code.getCodeLabel());
					}
				}
			}
		}
		Integer count = roomTypeDao.searchRoomTypeCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId,String language) {
		List<RoomTypeVO> roomTypeList = roomTypeDao.getAllRoomTypeByHotelId(hotelId, language);
		
		Collections.sort(roomTypeList, new Comparator<RoomTypeVO>() {
			@Override
			public int compare(RoomTypeVO o1, RoomTypeVO o2) {
				return o1.getRoomTypeCode().compareToIgnoreCase(o2.getRoomTypeCode());
			}
		});
		
		return roomTypeList;
		
	}
	@Override
	public List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId) {
		List<RoomTypeVO> roomTypeList = roomTypeDao.getAllRoomTypeByHotelId(hotelId);
		Collections.sort(roomTypeList, new Comparator<RoomTypeVO>() {
			@Override
			public int compare(RoomTypeVO o1, RoomTypeVO o2) {
				return o1.getRoomTypeCode().compareToIgnoreCase(o2.getRoomTypeCode());
			}
		});
		
		return roomTypeList;
		
	}

	@Override
	public List<RoomTypeVO> getRoomTypeByCodes(List<String> asList, String hotelId, String language) {
		List<RoomTypeVO> newList = new ArrayList<RoomTypeVO>();
		List<RoomTypeVO> list = roomTypeDao.getRoomTypeByCodes(asList, hotelId, language);
		if(!language.equalsIgnoreCase(LanguageCode.MAIN_LANGUAGE_CODE)){
			for(RoomTypeVO v:list){
				if(v.getRoomTypeName()!=null){
					newList.add(v);
				}
				if(v.getRoomTypeName()==null){
					RoomTypeVO vo = getRoomTypeById(v.getRoomTypeId(),LanguageCode.MAIN_LANGUAGE_CODE);
					newList.add(vo);
				}
			}
		}else{
			newList = list;
		}
		return newList;
	}

	/**
	 * 根据查询条件获取可用酒店下可用的房型
	 */
	public List<RoomTypeVO> getRoomTypeByCreteria(RoomTypeCreteria roomTypeCreteria) {
		return roomTypeDao.getRoomTypeByCreteria(roomTypeCreteria);
	}

	/**
	 * 根据房型ID取房型信息,不判断delFlag
	 */
	public RoomTypeVO getRoomTypeByRoomTypeId(String roomTypeId) {

		return roomTypeDao.getRoomTypeByRoomTypeId(roomTypeId);
	}

	public boolean checkRoomTypeName(String roomTypeName, String hotelId, String roomTypeId) {

		List<String> rtIds = roomTypeDao.checkRoomTypeName(roomTypeName, hotelId);

		if (rtIds != null && !rtIds.isEmpty()) {

			int size = rtIds.size();
			if (size > 1) {
				return true;
			}
			if (size == 1) {
				// 更新
				if (StringUtils.hasText(roomTypeId) && roomTypeId.equals(rtIds.get(0))) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 查询房型已有服务
	 */
	public Map<Integer, String> getAmenityByRoomTypeId(String roomTypeId) {
		return roomTypeDao.getAmenityByRoomTypeId(roomTypeId);
	}

	/**
	 * 保存房型服务设施F
	 * 
	 * @param roomTypeId
	 * @param services
	 */
	private void saveRoomTypeAmenity(String roomTypeId, List<String> services) {
		// 保存客房设施
		Map<Integer, String> amenityMap = getAmenityByRoomTypeId(roomTypeId);

		if (services != null && services.size() > 0) {
			for (String str : services) {
				Amenity a = new Amenity();
				a.setRoomTypeId(roomTypeId);
				a.setAmenityType(Integer.valueOf(str));
				a.setDelFlag(false);

				String codeNo = amenityMap.get(str);
				if (codeNo == null) {
					roomTypeDao.saveAmenity(a);
				}
				amenityMap.put(Integer.valueOf(str), null);
			}
		}

		if (amenityMap != null && amenityMap.keySet().size() > 0) {
			// 删除原来存在的服务现在则不存在的情况
			Iterator<Integer> it = amenityMap.keySet().iterator();
			while (it.hasNext()) {
				Integer key = it.next();
				String value = amenityMap.get(key);
				if (value != null) {
					Amenity a = new Amenity();
					a.setRoomTypeId(roomTypeId);
					a.setAmenityType(key);
					roomTypeDao.deleteAmenity(a);
				}
			}
		}
	}

	private void saveReceiveReqResLog(String roomTypeId, InvokeResponse res) {
		ReceiveReqResLog receiveReqResLog = new ReceiveReqResLog();
		receiveReqResLog.setInterfaceId(ChannelCodeEnum.TAOBAO.getName());
		receiveReqResLog.setType(MessageType.TB_ROOMTYPE);
		receiveReqResLog.setExtId(roomTypeId);
		receiveReqResLog.setRequest(res.getReqData());
		receiveReqResLog.setResponse(res.getResDate());
		receiveReqResLog.setException(res.getErrMsg());
		receiveReqResManager.saveOrUpdateReceiveReqRes(receiveReqResLog);
	}

	private String getRoomPicUrl(String picId) {
		Picture p = pictureManager.queryDefaultPicture(picId, PictureType.ROOM_TYPE.getName());
		String picUrl = "";
		if (p != null) {
			String faPtha = pictureManager.getUploadPath();
			String fileName = faPtha + p.getUrl();
			File f = new File(fileName);
			if (f.exists()) {
				picUrl = fileName;
			}
		}
		return picUrl;
	}

	private RoomTypeI18n roomTypeVO2RoomTypeI18n(RoomTypeVO vo) {
		RoomTypeI18n i18n = new RoomTypeI18n();
		i18n.setRoomTypeMId(vo.getRoomTypeMId());
		i18n.setRoomTypeId(vo.getRoomTypeId());
		// i18n.setLanguage(vo.getLanguage());
		// i18n.setRoomTypeName(vo.getRoomTypeName());
		// i18n.setDescription(vo.getDescription());
		i18n.setBabyName(vo.getBabyName());
		i18n.setGuide(vo.getGuide());
		i18n.setReceipt_info(vo.getReceipt_info());
		i18n.setReceipt_other_type_desc(vo.getReceipt_other_type_desc());
		return i18n;
	}

	// 获取默认语言对象
	@Override
	public RoomTypeI18n getDefaultLanguageI18n(RoomTypeVO roomTypeVo) {
		RoomTypeI18n roomTypeI18n = new RoomTypeI18n();
		roomTypeI18n.setRoomTypeId(roomTypeVo.getRoomTypeId());
		if(roomTypeVo.getLanguage()==null){
			roomTypeI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			roomTypeI18n.setLanguage(roomTypeVo.getLanguage());
		}
		roomTypeI18n.setRoomTypeName(roomTypeVo.getRoomTypeName());
		roomTypeI18n.setDescription(roomTypeVo.getDescription());
		return roomTypeI18n;
	}

	@Override
	public RoomTypeVO getRoomTypeByHotelCode(String roomTypeCode,
			String hotelCode, String language) {
		return roomTypeDao.getRoomTypeByHotelCode(roomTypeCode, hotelCode, language);
	}
	@Override
	public int getRoomTypePhysicalRoom(String roomTypeCode,String hotelCode){
		// 物理房量
		int physicalRoom = 0;
		RoomType roomType = getRoomTypeByHotelCode(roomTypeCode, hotelCode);
		if (roomType != null && roomType.getPhysicalRooms() != null) {
			physicalRoom = roomType.getPhysicalRooms();
		}
		return physicalRoom;
	}

	@Override
	public List<RoomType> getHotelRoomTypesByHotelId(String hotelId) {
		return roomTypeDao.getHotelRoomTypesByHotelId(hotelId);
	}
	
	public List<RoomType> getRoomTypeByHotelId(String hotelId) {
		return roomTypeDao.getRoomTypeByHotelId(hotelId);
	}

	@Override
	public List<RoomType> getRoomTypeByHotelIdList(List<String> hotelIdList) {
		
		return roomTypeDao.getRoomTypeByHotelIdList(hotelIdList);
	}
}
