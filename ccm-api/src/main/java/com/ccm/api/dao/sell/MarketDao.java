package com.ccm.api.dao.sell;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.sell.Market;
import com.ccm.api.model.sell.MarketI18n;
import com.ccm.api.model.sell.vo.MarketCreteria;
import com.ccm.api.model.sell.vo.MarketVO;

public interface MarketDao extends GenericDao<Market, String> {

	Market addMarket(Market market);
	
	MarketI18n addMarketI18n(MarketI18n marketI18n);
	
	void updateMarket(Market market);
	
	void updateMarketI18n(MarketI18n marketI18n);
	
	void deleteMarket(Market market);
	
	void deleteMarketI18n(MarketI18n marketI18n);
	
	MarketVO getMarketById(String marketId);
	
	MarketVO getMarketByCode(String marketCode);
	
	List<MarketVO> searchMarket(MarketCreteria creteria);
	
	Integer searchMarketCount(MarketCreteria creteria);

	MarketVO getMarketById(String marketId, String language);

	void deleteMarketI18nByMarketId(String marketId);

	List<MarketI18n> getMarketI18ns(String marketId);

	MarketVO getMarketByCode(String marketCode, String language);
}
