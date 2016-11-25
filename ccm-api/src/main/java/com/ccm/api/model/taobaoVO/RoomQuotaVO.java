package com.ccm.api.model.taobaoVO;

import java.util.Date;

public class RoomQuotaVO {
	/**
	 * 日期
	 * yyyy-MM-dd
	 */
	private String date;
	/**
	 * 房价
	 */
	private Integer price;
	/**
	 * 房量
	 */
	private Integer num;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "RoomQuotaVO [date=" + date + ", price=" + price + ", num="
				+ num + "]";
	}
}
