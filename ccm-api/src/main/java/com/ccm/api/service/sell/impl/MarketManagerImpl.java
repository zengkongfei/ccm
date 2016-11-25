package com.ccm.api.service.sell.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.sell.MarketDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.sell.Market;
import com.ccm.api.model.sell.MarketI18n;
import com.ccm.api.model.sell.vo.MarketCreteria;
import com.ccm.api.model.sell.vo.MarketResult;
import com.ccm.api.model.sell.vo.MarketVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.sell.MarketManager;

@Service("marketManager")
public class MarketManagerImpl extends GenericManagerImpl<Market, String> implements MarketManager {
	
	@Autowired
	private MarketDao marketDao;

	@Override
	public void saveMarket(MarketVO vo) {
		Market vo1 = new Market();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = marketDao.addMarket(vo1);
		vo.setMarketId(vo1.getMarketId());
		
		if(vo.getMarketI18nList()==null||vo.getMarketI18nList().size()==0){
			List<MarketI18n> i18nList = new ArrayList<MarketI18n>();
			MarketI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setMarketI18nList(i18nList);
		}
			
		//循环添加房型多语言数据
		for (MarketI18n marketI18n : vo.getMarketI18nList()) {
			MarketI18n i18n = new MarketI18n();
			i18n.setMarketId(vo.getMarketId());
			i18n.setLanguage(marketI18n.getLanguage());
			i18n.setDescription(marketI18n.getDescription());
			marketDao.addMarketI18n(i18n);
		}
	}

	@Override
	public void updateMarket(MarketVO vo) {
		Market vo1 = new Market();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		marketDao.updateMarket(vo1);
		
		if(vo.getMarketI18nList()!=null){
			
			//批量删除房型下的多语言记录
			marketDao.deleteMarketI18nByMarketId(vo.getMarketId());
			//循环添加房型多语言数据
			for (MarketI18n marketI18n : vo.getMarketI18nList()) {
				MarketI18n i18n = new MarketI18n();
				i18n.setMarketId(vo.getMarketId());
				i18n.setLanguage(marketI18n.getLanguage());
				i18n.setDescription(marketI18n.getDescription());
				marketDao.addMarketI18n(i18n);
			}
		}else{
			MarketI18n i18n = new MarketI18n();
			i18n.setMarketMId(vo.getMarketMId());
			i18n.setMarketId(vo.getMarketId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setDescription(vo.getDescription());
			marketDao.updateMarketI18n(i18n);
		}
	}

	@Override
	public void deleteMarket(MarketVO vo) {
		Market vo1 = new Market();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		marketDao.deleteMarket(vo1);
		
		marketDao.deleteMarketI18nByMarketId(vo.getMarketId());
	}

	@Override
	public MarketVO getMarketById(String marketId) {
		return marketDao.getMarketById(marketId);
	}
	
	@Override
	public MarketVO getMarketByCode(String marketCode) {
		return marketDao.getMarketByCode(marketCode);
	}

	@Override
	public MarketResult searchMarket(MarketCreteria creteria) {
		MarketResult result = new MarketResult();
		List<MarketVO> voList = marketDao.searchMarket(creteria);
		Integer count = marketDao.searchMarketCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}
	
	@Override
	public MarketI18n getDefaultLanguageI18n(MarketVO vo) {
		MarketI18n marketI18n = new MarketI18n();
		if(vo.getLanguage()==null){
			marketI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			marketI18n.setLanguage(vo.getLanguage());
		}
		marketI18n.setDescription(vo.getDescription());
		
		return marketI18n;
	}
	
	@Override
	public void deleteMarketI18nByMarketId(String marketId) {
		marketDao.deleteMarketI18nByMarketId(marketId);
	}

	@Override
	public MarketVO getMarketById(String marketId, String language) {
		return marketDao.getMarketById(marketId, language);
	}

	@Override
	public List<MarketI18n> getMarketI18ns(String marketId) {
		return marketDao.getMarketI18ns(marketId);
	}

	@Override
	public MarketVO getMarketByCode(String marketCode, String language) {
		return marketDao.getMarketByCode(marketCode,language);
	}
}
