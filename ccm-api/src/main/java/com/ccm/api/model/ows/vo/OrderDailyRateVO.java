package com.ccm.api.model.ows.vo;

import java.util.Date;

public class OrderDailyRateVO {
	
	
	private Date priceDate;// 价格日期

	private Double price;// 每间房的价格

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	
	
}
