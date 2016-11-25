package com.ccm.api.dao.roomType.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.roomType.RoomTypeTemplateDao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.roomType.RoomTypeTemplate;
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateVO;

@Repository("roomTypeTemplateDao")
public class RoomTypeTemplateDaoibatis extends GenericDaoiBatis<RoomTypeTemplate, String> implements RoomTypeTemplateDao{

	public RoomTypeTemplateDaoibatis() {
		super(RoomTypeTemplate.class);
	}
	
	@Override
	public RoomTypeTemplate addRoomTypeTemplate(RoomTypeTemplate roomTypeTemp) {
		roomTypeTemp.setRoomTypeTemplateId(UUID.randomUUID().toString().replace("-", ""));
		roomTypeTemp.setCreatedBy(SecurityHolder.getUserId());
		roomTypeTemp.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addRoomTypeTemplate", roomTypeTemp);
		return roomTypeTemp;
	}

	@Override
	public RoomTypeTemplateI18n addRoomTypeTemplateI18n(
			RoomTypeTemplateI18n roomTypeTempI18n) {
		roomTypeTempI18n.setRoomTypeTemplateMId(UUID.randomUUID().toString().replace("-", ""));
		roomTypeTempI18n.setCreatedBy(SecurityHolder.getUserId());
		roomTypeTempI18n.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addRoomTypeTemplateI18n", roomTypeTempI18n);
		return roomTypeTempI18n;
	}
	
	@Override
	public void updateRoomTypeTemplate(RoomTypeTemplate roomTypeTemp) {
		roomTypeTemp.setUpdatedBy(SecurityHolder.getUserId());
		roomTypeTemp.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateRoomTypeTemplate", roomTypeTemp);
	}

	@Override
	public void updateRoomTypeTemplateI18n(RoomTypeTemplateI18n roomTypeTempI18n) {
		roomTypeTempI18n.setUpdatedBy(SecurityHolder.getUserId());
		roomTypeTempI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateRoomTypeTemplateI18n", roomTypeTempI18n);
	}

	@Override
	public void deleteRoomTypeTemplate(RoomTypeTemplate roomTypeTemp) {
		roomTypeTemp.setUpdatedBy(SecurityHolder.getUserId());
		roomTypeTemp.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteRoomTypeTemplate", roomTypeTemp);
	}
	
	@Override
	public void deleteRoomTypeTemplateI18n(RoomTypeTemplateI18n roomTypeTempI18n) {
		roomTypeTempI18n.setUpdatedBy(SecurityHolder.getUserId());
		roomTypeTempI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteRoomTypeTemplateI18n", roomTypeTempI18n);
	}
	
	@Override
	public void deleteRoomTypeTemplateI18nByTempId(String roomTypeTempId){
		getSqlMapClientTemplate().update("deleteRoomTypeTemplateI18nByTempId",roomTypeTempId);
	}

	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTemplateId) {
		return this.getRoomTypeTemplateById(roomTypeTemplateId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTemplateId,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("roomTypeTemplateId", roomTypeTemplateId);
		params.put("language", language);
		return (RoomTypeTemplateVO) getSqlMapClientTemplate().queryForObject("getRoomTypeTemplateById", params);
	}
	
	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateByCode(String roomTypeTemplateCode) {
		return this.getRoomTypeTemplateByCode(roomTypeTemplateCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateByCode(String roomTypeTemplateCode,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("roomTypeTemplateCode", roomTypeTemplateCode);
		params.put("language", language);
		return (RoomTypeTemplateVO) getSqlMapClientTemplate().queryForObject("getRoomTypeTemplateByCode", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeTemplateVO> searchRoomTypeTemplate(
			RoomTypeTemplateCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchRoomTypeTemplate", creteria);
	}

	@Override
	public Integer searchRoomTypeTemplateCount(RoomTypeTemplateCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchRoomTypeTemplateCount", creteria);
	}
	
	@Override
	public List<RoomTypeTemplateVO> getAllRoomTypeTemplate() {
		return this.getAllRoomTypeTemplate(LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeTemplateVO> getAllRoomTypeTemplate(String language) {
		return getSqlMapClientTemplate().queryForList("getAllRoomTypeTemplate",language);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeTemplateI18n> getRoomTypeTemplateI18ns(String roomTypeTemplateId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("roomTypeTemplateId", roomTypeTemplateId);
		return getSqlMapClientTemplate().queryForList("getRoomTypeTemplateI18ns", params);
	}
	
	@Override
	public List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId){
		return this.getDontUseRoomTypeTemplate(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId,
			String language){
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getDontUseRoomTypeTemplate",params);
	}
	
	
	
	
}
