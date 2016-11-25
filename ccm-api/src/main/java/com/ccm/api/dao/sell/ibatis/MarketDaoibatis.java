package com.ccm.api.dao.sell.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.sell.MarketDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.sell.Market;
import com.ccm.api.model.sell.MarketI18n;
import com.ccm.api.model.sell.vo.MarketCreteria;
import com.ccm.api.model.sell.vo.MarketVO;

@Repository("marketDao")
public class MarketDaoibatis extends GenericDaoiBatis<Market, String> implements MarketDao {

	public MarketDaoibatis() {
		super(Market.class);
	}

	@Override
	public Market addMarket(Market vo) {
		vo.setMarketId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addMarket",vo);
		return vo;
	}

	@Override
	public MarketI18n addMarketI18n(MarketI18n vo) {
		vo.setMarketMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addMarketI18n",vo);
		return vo;
	}

	@Override
	public void updateMarket(Market vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateMarket",vo);
	}
	
	@Override
	public void updateMarketI18n(MarketI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateMarketI18n",vo);
	}

	@Override
	public void deleteMarket(Market vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteMarket",vo);
	}

	@Override
	public void deleteMarketI18n(MarketI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteMarketI18n",vo);
	}

	@Override
	public MarketVO getMarketByCode(String marketCode) {
		return this.getMarketByCode(marketCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public MarketVO getMarketByCode(String marketCode,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("marketCode", marketCode);
		params.put("language", language);
		return (MarketVO) getSqlMapClientTemplate().queryForObject("getMarketByCode",params);
	}
	
	@Override
	public MarketVO getMarketById(String marketId) {
		return this.getMarketById(marketId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public MarketVO getMarketById(String marketId,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("marketId", marketId);
		params.put("language", language);
		return (MarketVO) getSqlMapClientTemplate().queryForObject("getMarketById",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketVO> searchMarket(MarketCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchMarket",creteria);
	}

	@Override
	public Integer searchMarketCount(MarketCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchMarketCount",creteria);
	}
	
	@Override
	public void deleteMarketI18nByMarketId(String marketId){
		getSqlMapClientTemplate().update("deleteMarketI18nByMarketId",marketId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketI18n> getMarketI18ns(String marketId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("marketId", marketId);
		return getSqlMapClientTemplate().queryForList("getMarketI18ns",params);
	}
}
