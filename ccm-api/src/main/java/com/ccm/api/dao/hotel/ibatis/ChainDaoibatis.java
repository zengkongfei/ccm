package com.ccm.api.dao.hotel.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.ChainI18n;
import com.ccm.api.model.hotel.vo.ChainCreteria;
import com.ccm.api.model.hotel.vo.ChainVO;

@Repository("chainDao")
public class ChainDaoibatis extends GenericDaoiBatis<Chain, String> implements ChainDao {

	public ChainDaoibatis() {
		super(Chain.class);
	}

	@Override
	public Chain addChain(Chain vo) {
		vo.setChainId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addChain", vo);
		return vo;
	}

	@Override
	public ChainI18n addChainI18n(ChainI18n vo) {
		vo.setChainMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addChainI18n", vo);
		return vo;
	}

	@Override
	public void updateChainI18n(ChainI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateChainI18n", vo);
	}

	@Override
	public void deleteChain(Chain vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteChain", vo);
	}

	@Override
	public void deleteChainI18n(ChainI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteChainI18n", vo);
	}

	/**
	 * 返回chainId
	 */
	public Chain getChainByCode(String chainCode) {
		return (Chain) getSqlMapClientTemplate().queryForObject("getChainByChainCode", chainCode);
	}

	@Override
	public ChainVO getChainByCode(String chainCode, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chainCode", chainCode);
		params.put("language", language);
		return (ChainVO) getSqlMapClientTemplate().queryForObject("getChainByCode", params);
	}

	@Override
	public ChainVO getChainById(String chainId) {
		return this.getChainById(chainId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public ChainVO getChainById(String chainId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chainId", chainId);
		params.put("language", language);
		return (ChainVO) getSqlMapClientTemplate().queryForObject("getChainById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChainVO> searchChain(ChainCreteria creteria) {
		// 如果没有知道语言种类
		if (creteria != null && StringUtils.isBlank(creteria.getLanguage())) {
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}

		return getSqlMapClientTemplate().queryForList("searchChain", creteria);
	}

	@Override
	public Integer searchChainCount(ChainCreteria creteria) {
		// 如果没有知道语言种类
		if (creteria != null && StringUtils.isBlank(creteria.getLanguage())) {
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchChainCount", creteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChainVO> getAllChainI18n(String language) {
		return getSqlMapClientTemplate().queryForList("getAllChainI18n", language);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chain> getAllChain() {
		return getSqlMapClientTemplate().queryForList("getAllChain");
	}

	@Override
	public void deleteChainI18nByChainId(String chainId) {
		getSqlMapClientTemplate().update("deleteChainI18nByChainId", chainId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChainI18n> getChainI18ns(String chainId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("chainId", chainId);
		return getSqlMapClientTemplate().queryForList("getChainI18ns", params);
	}
}
