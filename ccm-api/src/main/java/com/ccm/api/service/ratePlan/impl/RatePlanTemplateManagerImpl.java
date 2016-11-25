package com.ccm.api.service.ratePlan.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.ratePlan.RatePlanTemplateDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.RatePlanTemplate;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateCreteria;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateResult;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RatePlanTemplateManager;

@Service("ratePlanTemplateManager")
public class RatePlanTemplateManagerImpl extends GenericManagerImpl<RatePlanTemplate, String> implements RatePlanTemplateManager {

	@Autowired
	private RatePlanTemplateDao ratePlanTemplateDao;

	@Override
	public RatePlanTemplateVO saveRatePlanTemplate(RatePlanTemplateVO vo){
		RatePlanTemplate vo2 = new RatePlanTemplate();
		try {
			PropertyUtils.copyProperties(vo2, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo2 = ratePlanTemplateDao.addRatePlanTemplate(vo2);
		vo.setRatePlanTemplateId(vo2.getRatePlanTemplateId());
		
		//添加默认语言对象
		if(vo.getRatePlanTempI18nList()==null||vo.getRatePlanTempI18nList().size()==0){
			List<RatePlanTemplateI18n> i18nList = new ArrayList<RatePlanTemplateI18n>();
			i18nList.add(this.getDefaultLanguageI18n(vo));
			vo.setRatePlanTempI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (RatePlanTemplateI18n RatePlanTemplateI18n : vo.getRatePlanTempI18nList()) {
			RatePlanTemplateI18n i18n = new RatePlanTemplateI18n();
			i18n.setRatePlanTemplateId(vo.getRatePlanTemplateId());	
			i18n.setLanguage(RatePlanTemplateI18n.getLanguage());
			i18n.setRatePlanTemplateName(RatePlanTemplateI18n.getRatePlanTemplateName());
			i18n.setDescription(RatePlanTemplateI18n.getDescription());
			ratePlanTemplateDao.addRatePlanTemplateI18n(i18n);
		}
		
		return vo;
	}

	@Override
	public void updateRoomTypeTempldate(RatePlanTemplateVO vo){
		RatePlanTemplate vo2 = new RatePlanTemplate();
		try {
			PropertyUtils.copyProperties(vo2, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ratePlanTemplateDao.updateRatePlanTemplate(vo2);
		
		//CCM系统设置中调用的方法
		if(vo.getRatePlanTempI18nList()!=null){
			ratePlanTemplateDao.deleteRatePlanTemplateI18nByTempId(vo.getRatePlanTemplateId());
			
			//循环添加多语言数据
			for (RatePlanTemplateI18n RatePlanTemplateI18n : vo.getRatePlanTempI18nList()) {
				RatePlanTemplateI18n i18n = new RatePlanTemplateI18n();
				i18n.setRatePlanTemplateId(vo.getRatePlanTemplateId());	
				i18n.setLanguage(RatePlanTemplateI18n.getLanguage());
				i18n.setRatePlanTemplateName(RatePlanTemplateI18n.getRatePlanTemplateName());
				i18n.setDescription(RatePlanTemplateI18n.getDescription());
				i18n.setUpdatedBy(SecurityHolder.getUserId());
				i18n.setLastModifyTime(new Date());
				ratePlanTemplateDao.addRatePlanTemplateI18n(i18n);
			}
		}else{
			RatePlanTemplateI18n i18n = new RatePlanTemplateI18n();
			i18n.setRatePlanTemplateMId(vo.getRatePlanTemplateMId());
			i18n.setRatePlanTemplateId(vo.getRatePlanTemplateId());	
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setRatePlanTemplateName(vo.getRatePlanTemplateName());
			i18n.setDescription(vo.getDescription());
			ratePlanTemplateDao.updateRatePlanTemplateI18n(i18n);
		}
	}

	public void saveOrUpdateRoomTypeTempldate(RatePlanTemplateVO vo){
		if(StringUtils.isNotBlank(vo.getRatePlanTemplateId())){
			this.updateRoomTypeTempldate(vo);
		}else{
			this.saveRatePlanTemplate(vo);
		}
	}

	@Override
	public void deleteRoomTypeTempldate(RatePlanTemplateVO vo){
		RatePlanTemplate vo2 = new RatePlanTemplate();
		try {
			PropertyUtils.copyProperties(vo2, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ratePlanTemplateDao.deleteRatePlanTemplate(vo2);
		
		ratePlanTemplateDao.deleteRatePlanTemplateI18nByTempId(vo.getRatePlanTemplateId());
	}
	
	@Override
	public RatePlanTemplateResult searchRatePlanTemplate(RatePlanTemplateCreteria creteria){
		RatePlanTemplateResult result = new RatePlanTemplateResult();
		List<RatePlanTemplateVO> resultList = ratePlanTemplateDao.searchRatePlanTemplate(creteria);
		
		Integer totalCount = ratePlanTemplateDao.searchRatePlanTemplateCount(creteria);
		result.setResultList(resultList);
		result.setTotalCount(totalCount);
		return result;
	}
	
	@Override
	public RatePlanTemplateI18n getDefaultLanguageI18n(RatePlanTemplateVO vo) {
		//创建多语言对象,并且设置值
		RatePlanTemplateI18n i18n = new RatePlanTemplateI18n();
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setRatePlanTemplateName(vo.getRatePlanTemplateName());
		i18n.setDescription(vo.getDescription());
		return i18n;
	}

	@Override
	public RatePlanTemplateVO getRatePlanTemplateById(String roomTypeTempId) {
		return ratePlanTemplateDao.getRatePlanTemplateById(roomTypeTempId);
	}

	@Override
	public RatePlanTemplateVO getRatePlanTemplateByCode(
			String RatePlanTemplateCode) {
		return ratePlanTemplateDao.getRatePlanTemplateByCode(RatePlanTemplateCode);
	}

	@Override
	public RatePlanTemplateVO getRatePlanTemplateById(
			String RatePlanTemplateId, String language) {
		return ratePlanTemplateDao.getRatePlanTemplateById(RatePlanTemplateId,language);
	}

	@Override
	public RatePlanTemplateVO getRatePlanTemplateByCode(
			String RatePlanTemplateCode, String language) {
		return ratePlanTemplateDao.getRatePlanTemplateByCode(RatePlanTemplateCode,language);
	}
	
	@Override
	public List<RatePlanTemplateI18n> getRatePlanTemplateI18ns(String RatePlanTemplateId){
		return ratePlanTemplateDao.getRatePlanTemplateI18ns(RatePlanTemplateId);
	}
	
	@Override
	public List<RatePlanTemplateI18n> getRatePlanTemplateI18ns2(
			String ratePlanTemplateCode) {
		return ratePlanTemplateDao.getRatePlanTemplateI18ns2(ratePlanTemplateCode);
	}

	@Override
	public List<RatePlanTemplateVO> getAllRatePlanTemplate(String language) {
		return ratePlanTemplateDao.getAllRatePlanTemplate(language);
	}

	@Override
	public List<RatePlanTemplateVO> getAllRatePlanTemplate() {
		return ratePlanTemplateDao.getAllRatePlanTemplate();
	}

	@Override
	public List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId) {
		return ratePlanTemplateDao.getDontUseRatePlanTemplate(hotelId);
	}

	@Override
	public List<RatePlanTemplateVO> getDontUseRatePlanTemplate(
			String hotelId, String language) {
		return ratePlanTemplateDao.getDontUseRatePlanTemplate(hotelId, language);
	}

}
