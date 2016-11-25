package com.ccm.api.dao.ratePlan.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.CancelPolicy;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyCriteria;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

@Repository("cancelPolicyDao")
public class CancelPolicyDaoibatis extends GenericDaoiBatis<CancelPolicy, String> implements CancelPolicyDao {

	public CancelPolicyDaoibatis() {
		super(CancelPolicy.class);
	}

	@Override
	public CancelPolicy addCancelPolicy(CancelPolicy vo) {
		vo.setCancelId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addCancelPolicy", vo);
		return vo;
	}

	@Override
	public CancelPolicyI18n addCancelPolicyI18n(CancelPolicyI18n vo) {
		vo.setCancelMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addCancelPolicyI18n", vo);
		return vo;
	}

	@Override
	public void updateCancelPolicy(CancelPolicy vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateCancelPolicy", vo);
	}

	@Override
	public void updateCancelPolicyI18n(CancelPolicyI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateCancelPolicyI18n", vo);
	}

	@Override
	public void deleteCancelPolicy(CancelPolicy vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteCancelPolicy", vo);
	}

	@Override
	public void deleteCancelPolicyI18n(CancelPolicyI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteCancelPolicyI18n", vo);
	}

	@Override
	public CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode) {
		return this.getCancelPolicyByCode(cancelPolicyCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cancelPolicyCode", cancelPolicyCode);
		params.put("language", language);
		return (CancelPolicyVO) getSqlMapClientTemplate().queryForObject("getCancelPolicyByCode", params);
	}

	@Override
	public CancelPolicyVO getCancelPolicyById(String cancelId) {
		return this.getCancelPolicyById(cancelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public CancelPolicyVO getCancelPolicyById(String cancelId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cancelId", cancelId);
		params.put("language", language);
		return (CancelPolicyVO) getSqlMapClientTemplate().queryForObject("getCancelPolicyById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CancelPolicyVO> searchCancelPolicy(CancelPolicyCriteria criteria) {// 如果没有知道语言种类
		if (criteria != null && StringUtils.isBlank(criteria.getLanguage())) {
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchCancelPolicy", criteria);
	}

	@Override
	public Integer searchCancelPolicyCount(CancelPolicyCriteria criteria) {
		// 如果没有知道语言种类
		if (criteria != null && StringUtils.isBlank(criteria.getLanguage())) {
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchCancelPolicyCount", criteria);
	}

	@Override
	public void deleteCancelPolicyI18nByCancelId(String cancelId) {
		getSqlMapClientTemplate().update("deleteCancelPolicyI18nByCancelId", cancelId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CancelPolicyI18n> getCancelPolicyI18ns(String cancelId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("cancelId", cancelId);
		return getSqlMapClientTemplate().queryForList("getCancelPolicyI18ns", params);
	}

	@Override
	public List<CancelPolicyVO> getAllCancelPolicys() {
		return this.getAllCancelPolicys(LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CancelPolicyVO> getAllCancelPolicys(String language) {
		return getSqlMapClientTemplate().queryForList("getAllCancelPolicys", language);
	}

	@SuppressWarnings("unchecked")
	public List<CancelPolicy> getRateCancelByRatePlanId(String ratePlanId) {
		return getSqlMapClientTemplate().queryForList("getRateCancelByRatePlanId", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	public List<CancelPolicyVO> getRateCancelI18nByRatePlanId(String ratePlanId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ratePlanId", ratePlanId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRateCancelI18nByRatePlanId", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CancelPolicyVO> getRateCancelI18nByCondition(Map<String,Object>params) {
		return getSqlMapClientTemplate().queryForList("getRateCancelI18nByRatePlanId", params);
	}

}
