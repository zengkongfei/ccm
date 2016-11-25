package com.ccm.api.service.sell;

import java.util.List;

import com.ccm.api.model.sell.Market;
import com.ccm.api.model.sell.MarketI18n;
import com.ccm.api.model.sell.vo.MarketCreteria;
import com.ccm.api.model.sell.vo.MarketResult;
import com.ccm.api.model.sell.vo.MarketVO;
import com.ccm.api.service.base.GenericManager;

public interface MarketManager extends GenericManager<Market, String> {

	/**
	 * 新增市场
	 */
	void saveMarket(MarketVO vo);
	
	/**
	 * 修改市场
	 */
	void updateMarket(MarketVO vo);
	
	/**
	 * 删除市场
	 */
	void deleteMarket(MarketVO vo);
	
	/**
	 * 根据市场CODE取市场信息
	 */
	MarketVO getMarketByCode(String marketCode);
	
	/**
	 * 根据市场CODE和语言代码取市场信息
	 */
	MarketVO getMarketByCode(String marketCode,String language);
	
	/**
	 * 根据市场ID取市场信息
	 */
	MarketVO getMarketById(String marketId);
	
	/**
	 * 根据条件取市场信息
	 */
	MarketResult searchMarket(MarketCreteria creteria);

	void deleteMarketI18nByMarketId(String marketId);

	MarketVO getMarketById(String marketId, String language);

	List<MarketI18n> getMarketI18ns(String sourceId);

	MarketI18n getDefaultLanguageI18n(MarketVO vo);
}
