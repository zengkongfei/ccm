package com.ccm.api.dao.roomType.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.roomType.Amenity;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.RoomTypeI18n;
import com.ccm.api.model.roomType.vo.RoomTypeCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.util.CommonUtil;

@Repository("roomTypeDao")
public class RoomTypeDaoibatis extends GenericDaoiBatis<RoomType, String> implements RoomTypeDao {

	public RoomTypeDaoibatis() {
		super(RoomType.class);
	}

	@Override
	public RoomType addRoomType(RoomType vo) {
		vo.setRoomTypeId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addRoomType", vo);
		return vo;
	}

	@Override
	public RoomTypeI18n addRoomTypeI18n(RoomTypeI18n vo) {
		vo.setRoomTypeMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addRoomTypeI18n", vo);
		return vo;
	}

	@Override
	public void updateRoomType(RoomType vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateRoomType", vo);
	}

	@Override
	public void updateRoomTypeI18n(RoomTypeI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateRoomTypeI18n", vo);
	}

	@Override
	public void deleteRoomType(RoomType vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteRoomType", vo);
	}

	@Override
	public void deleteRoomTypeI18n(RoomTypeI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteRoomTypeI18n", vo);
	}

	@Override
	public void deleteRoomTypeI18nByRoomTypeId(String roomTypeId) {
		getSqlMapClientTemplate().update("deleteRoomTypeI18nByRoomTypeId", roomTypeId);
	}
	@Override
	public RoomType getRoomTypeByObj(RoomType r) {
		return (RoomType) getSqlMapClientTemplate().queryForObject("getRoomTypeByObj", r);
	}
	@Override
	public RoomTypeVO getRoomTypeVOByObj(RoomTypeVO r) {
		return (RoomTypeVO) getSqlMapClientTemplate().queryForObject("getRoomTypeVOByObj", r);
	}

	
	@Override
	public RoomTypeVO getRoomTypeByCode(RoomTypeVO vo) {
		if (vo != null && StringUtils.isBlank(vo.getLanguage())) {
			vo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (RoomTypeVO) getSqlMapClientTemplate().queryForObject("getRoomTypeByCode", vo);
	}

	/**
	 * 根据酒店code和房型code获取roomTypeId,physicalRooms
	 */
	public RoomType getRoomTypeByHotelCode(String roomTypeCode, String hotelCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomTypeCode", roomTypeCode);
		map.put("hotelCode", hotelCode);
		return (RoomType) getSqlMapClientTemplate().queryForObject("getRoomTypeByHotelCode", map);
	}

	@Override
	public RoomTypeVO getRoomTypeByHotelCode(String roomTypeCode, String hotelCode, String language) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("roomTypeCode", roomTypeCode);
		map.put("hotelCode", hotelCode);
		map.put("language", language);
		return (RoomTypeVO) getSqlMapClientTemplate().queryForObject("getRoomTypeI18nByHotelCode", map);
	}

	@Override
	public RoomTypeVO getRoomTypeById(String roomTypeId) {
		return this.getRoomTypeById(roomTypeId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public RoomTypeVO getRoomTypeById(String roomTypeId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("roomTypeId", roomTypeId);
		params.put("language", language); // 默认为中文语言
		return (RoomTypeVO) getSqlMapClientTemplate().queryForObject("getRoomTypeById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeVO> searchRoomType(RoomTypeCreteria creteria) {
		if (creteria != null && StringUtils.isBlank(creteria.getLanguage())) {
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchRoomType", creteria);
	}

	@Override
	public Integer searchRoomTypeCount(RoomTypeCreteria creteria) {
		if (creteria != null && StringUtils.isBlank(creteria.getLanguage())) {
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchRoomTypeCount", creteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeI18n> getRoomTypeI18ns(String roomTypeId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("roomTypeId", roomTypeId);
		return getSqlMapClientTemplate().queryForList("getRoomTypeI18ns", params);
	}

	@SuppressWarnings("unchecked")
	public List<RoomType> getRoomTypeByHotelId(String hotelId) {
		return (List<RoomType>) getSqlMapClientTemplate().queryForList("getRoomTypeByHotelId", hotelId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language); // 设置语言
		return getSqlMapClientTemplate().queryForList("getAllRoomTypeByHotelId", params);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		return getSqlMapClientTemplate().queryForList("getAllRoomTypeByHotelIdAll", params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<RoomTypeVO> getRoomTypeByCodes(List<String> asList, String hotelId, String language) {
		HashMap param = new HashMap();
		param.put("asList", asList);
		param.put("hotelId", hotelId);
		if (StringUtils.isBlank(language)) {
			param.put("language", LanguageCode.MAIN_LANGUAGE_CODE);
		} else {
			param.put("language", language);
		}
		return getSqlMapClientTemplate().queryForList("getRoomTypeByCodes", param);
	}

	/**
	 * 根据查询条件获取可用酒店下可用的房型
	 */
	@SuppressWarnings("unchecked")
	public List<RoomTypeVO> getRoomTypeByCreteria(RoomTypeCreteria creteria) {
		if (creteria != null && StringUtils.isBlank(creteria.getLanguage())) {
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getRoomTypeByCreteria", creteria);
	}

	/**
	 * 根据房型ID取房型信息,不判断delFlag
	 */
	public RoomTypeVO getRoomTypeByRoomTypeId(String roomTypeId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("roomTypeId", roomTypeId);
		params.put("language", LanguageCode.MAIN_LANGUAGE_CODE); // 默认为中文语言
		return (RoomTypeVO) getSqlMapClientTemplate().queryForObject("getRoomTypeByRoomTypeId", params);
	}

	@SuppressWarnings("unchecked")
	public List<String> checkRoomTypeName(String roomTypeName, String hotelId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomTypeName", roomTypeName);
		map.put("hotelId", hotelId);
		return (List<String>) getSqlMapClientTemplate().queryForObject("checkRoomTypeName", map);
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getAmenityByRoomTypeId(String roomTypeId) {
		return getSqlMapClientTemplate().queryForMap("getAmenityByRoomTypeId", roomTypeId, "amenityType", "amenityId");
	}

	public void saveAmenity(Amenity a) {
		a.setAmenityId(CommonUtil.generatePrimaryKey());
		getSqlMapClientTemplate().insert("addAmenity", a);
	}

	public void deleteAmenity(Amenity a) {
		getSqlMapClientTemplate().delete("deleteAmenity", a);
	}

	@SuppressWarnings("unchecked")
	public List<RoomTypeVO> getRoomTypeVOByRatePlanIdLang(String ratePlanId, String language) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ratePlanId", ratePlanId);
		map.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRoomTypeVOByRatePlanIdLang", map);
	}

	@SuppressWarnings("unchecked")
	public List<String> getRoomTypeCodesByIds(List<String> roomTypeIds) {
		return getSqlMapClientTemplate().queryForList("getRoomTypeCodesByIds", roomTypeIds);
	}

	@SuppressWarnings("unchecked")
	public List<RoomType> getHotelRoomTypesByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getHotelRoomTypesByHotelId", hotelId);
	}

	public List<RoomTypeVO> getRoomTypeInfosByIds(List<String> roomTypeIds) {
		return this.getRoomTypeInfosByIds(roomTypeIds, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	public List<RoomTypeVO> getRoomTypeInfosByIds(List<String> roomTypeIds, String language) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roomTypeIds", roomTypeIds);
		map.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRoomTypeInfosByIds", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> getRoomTypeByHotelIdList(List<String> hotelIdList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelIdList", hotelIdList);
		return getSqlMapClientTemplate().queryForList("getRoomTypeByHotelIdList", map);
	}
}
