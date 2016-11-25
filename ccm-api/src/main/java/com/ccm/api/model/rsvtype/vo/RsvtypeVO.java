package com.ccm.api.model.rsvtype.vo;

/**
 * 房价日历表
 * 
 * @author carr
 * 
 */
public class RsvtypeVO {

	private static final long serialVersionUID = -4362818469551741076L;
	/** count(1) */
	private Integer count;

	/** 房量 */
	private Integer available;// 可用房量

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "RsvtypeVO [count=" + count + ", available=" + available + "]";
	}

}
