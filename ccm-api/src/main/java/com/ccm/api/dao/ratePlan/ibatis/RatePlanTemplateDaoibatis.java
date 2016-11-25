package com.ccm.api.dao.ratePlan.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ccm.api.SecurityHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RatePlanTemplateDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.RatePlanTemplate;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateCreteria;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateVO;


@Repository("ratePlanTemplateDao")
public class RatePlanTemplateDaoibatis extends GenericDaoiBatis<RatePlanTemplate, String> implements RatePlanTemplateDao{

	public RatePlanTemplateDaoibatis() {
		super(RatePlanTemplate.class);
	}
	
	@Override
	public RatePlanTemplate addRatePlanTemplate(RatePlanTemplate ratePlanTemp) {
		ratePlanTemp.setRatePlanTemplateId(UUID.randomUUID().toString().replace("-", ""));
		ratePlanTemp.setCreatedBy(SecurityHolder.getUserId());
		ratePlanTemp.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addRatePlanTemplate", ratePlanTemp);
		return ratePlanTemp;
	}

	@Override
	public RatePlanTemplateI18n addRatePlanTemplateI18n(
			RatePlanTemplateI18n ratePlanTempI18n) {
		ratePlanTempI18n.setRatePlanTemplateMId(UUID.randomUUID().toString().replace("-", ""));
		ratePlanTempI18n.setCreatedBy(SecurityHolder.getUserId());
		ratePlanTempI18n.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addRatePlanTemplateI18n", ratePlanTempI18n);
		return ratePlanTempI18n;
	}
	
	@Override
	public void updateRatePlanTemplate(RatePlanTemplate ratePlanTemp) {
		ratePlanTemp.setUpdatedBy(SecurityHolder.getUserId());
		ratePlanTemp.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateRatePlanTemplate", ratePlanTemp);
	}

	@Override
	public void updateRatePlanTemplateI18n(RatePlanTemplateI18n ratePlanTempI18n) {
		ratePlanTempI18n.setUpdatedBy(SecurityHolder.getUserId());
		ratePlanTempI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateRatePlanTemplateI18n", ratePlanTempI18n);
	}

	@Override
	public void deleteRatePlanTemplate(RatePlanTemplate ratePlanTemp) {
		ratePlanTemp.setUpdatedBy(SecurityHolder.getUserId());
		ratePlanTemp.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteRatePlanTemplate", ratePlanTemp);
	}
	
	@Override
	public void deleteRatePlanTemplateI18n(RatePlanTemplateI18n ratePlanTempI18n) {
		ratePlanTempI18n.setUpdatedBy(SecurityHolder.getUserId());
		ratePlanTempI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteRatePlanTemplateI18n", ratePlanTempI18n);
	}
	
	@Override
	public void deleteRatePlanTemplateI18nByTempId(String ratePlanTempId){
		getSqlMapClientTemplate().update("deleteRatePlanTemplateI18nByTempId",ratePlanTempId);
	}

	@Override
	public RatePlanTemplateVO getRatePlanTemplateById(String ratePlanTemplateId) {
		return this.getRatePlanTemplateById(ratePlanTemplateId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RatePlanTemplateVO getRatePlanTemplateById(String ratePlanTemplateId,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("ratePlanTemplateId", ratePlanTemplateId);
		params.put("language", language);
		return (RatePlanTemplateVO) getSqlMapClientTemplate().queryForObject("getRatePlanTemplateById", params);
	}
	
	@Override
	public RatePlanTemplateVO getRatePlanTemplateByCode(String ratePlanTemplateCode) {
		return this.getRatePlanTemplateByCode(ratePlanTemplateCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RatePlanTemplateVO getRatePlanTemplateByCode(String ratePlanTemplateCode,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("ratePlanTemplateCode", ratePlanTemplateCode);
		params.put("language", language);
		return (RatePlanTemplateVO) getSqlMapClientTemplate().queryForObject("getRatePlanTemplateByCode", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanTemplateVO> searchRatePlanTemplate(
			RatePlanTemplateCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchRatePlanTemplate", creteria);
	}

	@Override
	public Integer searchRatePlanTemplateCount(RatePlanTemplateCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchRatePlanTemplateCount", creteria);
	}
	
	@Override
	public List<RatePlanTemplateVO> getAllRatePlanTemplate() {
		return this.getAllRatePlanTemplate(LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanTemplateVO> getAllRatePlanTemplate(String language) {
		return getSqlMapClientTemplate().queryForList("getAllRatePlanTemplate",language);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanTemplateI18n> getRatePlanTemplateI18ns(String ratePlanTemplateId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("ratePlanTemplateId", ratePlanTemplateId);
		return getSqlMapClientTemplate().queryForList("getRatePlanTemplateI18ns", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanTemplateI18n> getRatePlanTemplateI18ns2(
			String ratePlanTemplateCode) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("ratePlanTemplateCode", ratePlanTemplateCode);
		return getSqlMapClientTemplate().queryForList("getRatePlanTemplateI18ns2", params);
	}
	
	@Override
	public List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId){
		return this.getDontUseRatePlanTemplate(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId,String language){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getDontUseRatePlanTemplate", params);
	}
	
	
}
