package com.ccm.api.model.sell.vo;

import java.util.List;

import com.ccm.api.model.sell.Market;
import com.ccm.api.model.sell.MarketI18n;

public class MarketVO extends Market{

	private static final long serialVersionUID = 3717055564375583301L;
	
	private String	marketMId	;//	序号
	private String	marketId	;//	市场序号
	private String	language	;//	语言
	private String	description	;//	描述
	private List<MarketI18n> marketI18nList; //市场多语言列表
	
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
	
	
	public List<MarketI18n> getMarketI18nList() {
		return marketI18nList;
	}
	public void setMarketI18nList(List<MarketI18n> marketI18nList) {
		this.marketI18nList = marketI18nList;
	}
	@Override
	public String toString() {
		return "MarketI18n [marketMId=" + marketMId + ", marketId=" + marketId
				+ ", language=" + language + ", description=" + description
				+ "]";
	}
}
