package com.ccm.api.service.roomType.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.roomType.RoomTypeTemplateDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.roomType.RoomTypeTemplate;
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateResult;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.roomType.RoomTypeTemplateManager;

@Service("roomTypeTemplateManager")
public class RoomTypeTemplateManagerImpl extends GenericManagerImpl<RoomTypeTemplate, String> implements RoomTypeTemplateManager {

	@Autowired
	private RoomTypeTemplateDao roomTypeTemplateDao;

	@Override
	public RoomTypeTemplateVO saveRoomTypeTemplate(RoomTypeTemplateVO vo){
		RoomTypeTemplate vo2 = new RoomTypeTemplate();
		try {
			PropertyUtils.copyProperties(vo2, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo2 = roomTypeTemplateDao.addRoomTypeTemplate(vo2);
		vo.setRoomTypeTemplateId(vo2.getRoomTypeTemplateId());
		
		//添加默认语言对象
		if(vo.getRoomTypeTempI18nList()==null||vo.getRoomTypeTempI18nList().size()==0){
			List<RoomTypeTemplateI18n> i18nList = new ArrayList<RoomTypeTemplateI18n>();
			i18nList.add(this.getDefaultLanguageI18n(vo));
			vo.setRoomTypeTempI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (RoomTypeTemplateI18n roomTypeTemplateI18n : vo.getRoomTypeTempI18nList()) {
			RoomTypeTemplateI18n i18n = new RoomTypeTemplateI18n();
			i18n.setRoomTypeTemplateId(vo.getRoomTypeTemplateId());	
			i18n.setLanguage(roomTypeTemplateI18n.getLanguage());
			i18n.setRoomTypeTemplateName(roomTypeTemplateI18n.getRoomTypeTemplateName());
			i18n.setDescription(roomTypeTemplateI18n.getDescription());
			roomTypeTemplateDao.addRoomTypeTemplateI18n(i18n);
		}
		
		return vo;
	}

	@Override
	public void updateRoomTypeTempldate(RoomTypeTemplateVO vo){
		RoomTypeTemplate vo2 = new RoomTypeTemplate();
		try {
			PropertyUtils.copyProperties(vo2, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roomTypeTemplateDao.updateRoomTypeTemplate(vo2);
		
		//CCM系统设置中调用的方法
		if(vo.getRoomTypeTempI18nList()!=null){
			roomTypeTemplateDao.deleteRoomTypeTemplateI18nByTempId(vo.getRoomTypeTemplateId());
			
			//循环添加多语言数据
			for (RoomTypeTemplateI18n roomTypeTemplateI18n : vo.getRoomTypeTempI18nList()) {
				RoomTypeTemplateI18n i18n = new RoomTypeTemplateI18n();
				i18n.setRoomTypeTemplateId(vo.getRoomTypeTemplateId());	
				i18n.setLanguage(roomTypeTemplateI18n.getLanguage());
				i18n.setRoomTypeTemplateName(roomTypeTemplateI18n.getRoomTypeTemplateName());
				i18n.setDescription(roomTypeTemplateI18n.getDescription());
				i18n.setUpdatedBy(SecurityHolder.getUserId());
				i18n.setLastModifyTime(new Date());
				roomTypeTemplateDao.addRoomTypeTemplateI18n(i18n);
			}
		}else{
			RoomTypeTemplateI18n i18n = new RoomTypeTemplateI18n();
			i18n.setRoomTypeTemplateMId(vo.getRoomTypeTemplateMId());
			i18n.setRoomTypeTemplateId(vo.getRoomTypeTemplateId());	
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setRoomTypeTemplateName(vo.getRoomTypeTemplateName());
			i18n.setDescription(vo.getDescription());
			roomTypeTemplateDao.updateRoomTypeTemplateI18n(i18n);
		}
	}

	public void saveOrUpdateRoomTypeTempldate(RoomTypeTemplateVO vo){
		if(StringUtils.isNotBlank(vo.getRoomTypeTemplateId())){
			this.updateRoomTypeTempldate(vo);
		}else{
			this.saveRoomTypeTemplate(vo);
		}
	}

	@Override
	public void deleteRoomTypeTempldate(RoomTypeTemplateVO vo){
		RoomTypeTemplate vo2 = new RoomTypeTemplate();
		try {
			PropertyUtils.copyProperties(vo2, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roomTypeTemplateDao.deleteRoomTypeTemplate(vo2);
		
		roomTypeTemplateDao.deleteRoomTypeTemplateI18nByTempId(vo.getRoomTypeTemplateId());
	}
	
	@Override
	public RoomTypeTemplateResult searchRoomTypeTemplate(RoomTypeTemplateCreteria creteria){
		RoomTypeTemplateResult result = new RoomTypeTemplateResult();
		List<RoomTypeTemplateVO> resultList = roomTypeTemplateDao.searchRoomTypeTemplate(creteria);
		
		Integer totalCount = roomTypeTemplateDao.searchRoomTypeTemplateCount(creteria);
		result.setResultList(resultList);
		result.setTotalCount(totalCount);
		return result;
	}
	
	@Override
	public RoomTypeTemplateI18n getDefaultLanguageI18n(RoomTypeTemplateVO vo) {
		//创建多语言对象,并且设置值
		RoomTypeTemplateI18n i18n = new RoomTypeTemplateI18n();
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setRoomTypeTemplateName(vo.getRoomTypeTemplateName());
		i18n.setDescription(vo.getDescription());
		return i18n;
	}

	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTempId) {
		return roomTypeTemplateDao.getRoomTypeTemplateById(roomTypeTempId);
	}

	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateByCode(
			String roomTypeTemplateCode) {
		return roomTypeTemplateDao.getRoomTypeTemplateByCode(roomTypeTemplateCode);
	}

	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateById(
			String roomTypeTemplateId, String language) {
		return roomTypeTemplateDao.getRoomTypeTemplateById(roomTypeTemplateId,language);
	}

	@Override
	public RoomTypeTemplateVO getRoomTypeTemplateByCode(
			String roomTypeTemplateCode, String language) {
		return roomTypeTemplateDao.getRoomTypeTemplateByCode(roomTypeTemplateCode,language);
	}
	
	@Override
	public List<RoomTypeTemplateI18n> getRoomTypeTemplateI18ns(String roomTypeTemplateId){
		return roomTypeTemplateDao.getRoomTypeTemplateI18ns(roomTypeTemplateId);
	}

	@Override
	public List<RoomTypeTemplateVO> getAllRoomTypeTemplate(String language) {
		return roomTypeTemplateDao.getAllRoomTypeTemplate(language);
	}

	@Override
	public List<RoomTypeTemplateVO> getAllRoomTypeTemplate() {
		return roomTypeTemplateDao.getAllRoomTypeTemplate();
	}

	@Override
	public List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId) {
		return roomTypeTemplateDao.getDontUseRoomTypeTemplate(hotelId);
	}

	@Override
	public List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId,
			String language) {
		return roomTypeTemplateDao.getDontUseRoomTypeTemplate(hotelId, language);
	}

}
