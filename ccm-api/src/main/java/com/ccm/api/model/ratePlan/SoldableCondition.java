package com.ccm.api.model.ratePlan;

public class SoldableCondition {
	private Integer maxEvenDay;
	private Integer minEvenDay;
	private Integer maxBeforehandDay;
	private Integer minBeforehandDay;

	private String startDate;
	private String endDate;

	private Integer limitBuy; // 限量购买
	private String bookTime;// 预订时间
	private String lastMinutesBeginTime;
	private String lastMinutesEndTime;
	private Double lastMinutesPercent;
	private Double lastMinutesAmount;
	private Integer soldNum;// 已卖房量

	public Integer getMaxEvenDay() {
		return maxEvenDay;
	}

	public void setMaxEvenDay(Integer maxEvenDay) {
		this.maxEvenDay = maxEvenDay;
	}

	public Integer getMinEvenDay() {
		return minEvenDay;
	}

	public void setMinEvenDay(Integer minEvenDay) {
		this.minEvenDay = minEvenDay;
	}

	public Integer getMaxBeforehandDay() {
		return maxBeforehandDay;
	}

	public void setMaxBeforehandDay(Integer maxBeforehandDay) {
		this.maxBeforehandDay = maxBeforehandDay;
	}

	public Integer getMinBeforehandDay() {
		return minBeforehandDay;
	}

	public void setMinBeforehandDay(Integer minBeforehandDay) {
		this.minBeforehandDay = minBeforehandDay;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getLimitBuy() {
		return limitBuy;
	}

	public void setLimitBuy(Integer limitBuy) {
		this.limitBuy = limitBuy;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getLastMinutesBeginTime() {
		return lastMinutesBeginTime;
	}

	public void setLastMinutesBeginTime(String lastMinutesBeginTime) {
		this.lastMinutesBeginTime = lastMinutesBeginTime;
	}

	public String getLastMinutesEndTime() {
		return lastMinutesEndTime;
	}

	public void setLastMinutesEndTime(String lastMinutesEndTime) {
		this.lastMinutesEndTime = lastMinutesEndTime;
	}

	public Double getLastMinutesPercent() {
		return lastMinutesPercent;
	}

	public void setLastMinutesPercent(Double lastMinutesPercent) {
		this.lastMinutesPercent = lastMinutesPercent;
	}

	public Double getLastMinutesAmount() {
		return lastMinutesAmount;
	}

	public void setLastMinutesAmount(Double lastMinutesAmount) {
		this.lastMinutesAmount = lastMinutesAmount;
	}

	public Integer getSoldNum() {
		return soldNum == null ? 0 : soldNum;
	}

	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SoldableCondition [maxEvenDay=" + maxEvenDay + ", minEvenDay=" + minEvenDay + ", maxBeforehandDay=" + maxBeforehandDay + ", minBeforehandDay=" + minBeforehandDay + ", startDate=" + startDate + ", endDate=" + endDate + ", limitBuy=" + limitBuy + ", bookTime=" + bookTime + ", lastMinutesBeginTime=" + lastMinutesBeginTime + ", lastMinutesEndTime=" + lastMinutesEndTime + ", lastMinutesPercent=" + lastMinutesPercent + ", lastMinutesAmount=" + lastMinutesAmount + ", soldNum=" + soldNum + "]";
	}

}
