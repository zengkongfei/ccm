package com.ccm.api.model.sell;

import com.ccm.api.model.base.BaseObject;

/**
 * 市场（多语言）
 * @author carr
 *
 */
public class MarketI18n extends BaseObject {

	private static final long serialVersionUID = 4487684271958454251L;

	private String	marketMId	;//	序号
	private String	marketId	;//	市场序号
	private String	language	;//	语言
	private String	description	;//	描述
	
	public String getMarketMId() {
		return marketMId;
	}
	public void setMarketMId(String marketMId) {
		this.marketMId = marketMId;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "MarketI18n [marketMId=" + marketMId + ", marketId=" + marketId
				+ ", language=" + language + ", description=" + description
				+ "]";
	}
}
