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
import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyCriteria;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;

@Repository("guaranteePolicyDao")
public class GuaranteePolicyDaoibatis extends GenericDaoiBatis<GuaranteePolicy, String> implements GuaranteePolicyDao {

	public GuaranteePolicyDaoibatis() {
		super(GuaranteePolicy.class);
	}

	@Override
	public GuaranteePolicy addGuaranteePolicy(GuaranteePolicy vo) {
		vo.setGuaranteeId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addGuaranteePolicy", vo);
		return vo;
	}

	@Override
	public GuaranteePolicyI18n addGuaranteePolicyI18n(GuaranteePolicyI18n vo) {
		vo.setGuaranteeMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addGuaranteePolicyI18n", vo);
		return vo;
	}

	@Override
	public void updateGuaranteePolicy(GuaranteePolicy vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateGuaranteePolicy", vo);
	}

	@Override
	public void updateGuaranteePolicyI18n(GuaranteePolicyI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateGuaranteePolicyI18n", vo);
	}

	@Override
	public void deleteGuaranteePolicy(GuaranteePolicy vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteGuaranteePolicy", vo);
	}

	@Override
	public void deleteGuaranteePolicyI18n(GuaranteePolicyI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteGuaranteePolicyI18n", vo);
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode) {
		return this.getGuaranteePolicyByCode(guaranteeCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("guaranteeCode", guaranteeCode);
		params.put("language", language);
		return (GuaranteePolicyVO) getSqlMapClientTemplate().queryForObject("getGuaranteePolicyByCode", params);
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId) {
		return this.getGuaranteePolicyById(guaranteeId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("guaranteeId", guaranteeId);
		params.put("language", language);
		return (GuaranteePolicyVO) getSqlMapClientTemplate().queryForObject("getGuaranteePolicyById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteePolicyVO> searchGuaranteePolicy(GuaranteePolicyCriteria criteria) {
		// 如果没有知道语言种类
		if (criteria != null && StringUtils.isBlank(criteria.getLanguage())) {
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchGuaranteePolicy", criteria);
	}

	@Override
	public Integer searchGuaranteePolicyCount(GuaranteePolicyCriteria criteria) {
		// 如果没有知道语言种类
		if (criteria != null && StringUtils.isBlank(criteria.getLanguage())) {
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchGuaranteePolicyCount", criteria);
	}

	@Override
	public void deleteGuaranteePolicyI18nByGuaranteeId(String guaranteeId) {
		getSqlMapClientTemplate().update("deleteGuaranteePolicyI18nByGuaranteeId", guaranteeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteePolicyI18n> getGuaranteePolicyI18ns(String guaranteeId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("guaranteeId", guaranteeId);
		return getSqlMapClientTemplate().queryForList("getGuaranteePolicyI18ns", params);
	}

	public GuaranteePolicy getGuaranteeByCode(String guaranteeCode) {
		return (GuaranteePolicy) getSqlMapClientTemplate().queryForObject("getGuaranteeByCode", guaranteeCode);
	}

	/**
	 * 根据房价IDS查询担保规则表
	 */
	@SuppressWarnings("unchecked")
	public List<GuaranteePolicy> getGuaranteePolicyByRatePlanIds(List<String> ratePlanIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ratePlanIds", ratePlanIds);
		return getSqlMapClientTemplate().queryForList("getGuaranteePolicyByRatePlanIds", params);
	}

	@SuppressWarnings("unchecked")
	public List<GuaranteePolicy> getGuaranteePolicyByRatePlanId(String ratePlanId) {
		return getSqlMapClientTemplate().queryForList("getGuaranteePolicyByRatePlanId", ratePlanId);
	}

	@Override
	public List<GuaranteePolicyVO> getAllGuaranteePolicys() {
		return this.getAllGuaranteePolicys(LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteePolicyVO> getAllGuaranteePolicys(String language) {
		return getSqlMapClientTemplate().queryForList("getAllGuaranteePolicys", language);
	}

	@SuppressWarnings("unchecked")
	public List<GuaranteePolicyVO> getGuaranteePolicyI18nByRatePlanId(String ratePlanId, String language) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ratePlanId", ratePlanId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getGuaranteePolicyI18nByRatePlanId", params);
	}

}
