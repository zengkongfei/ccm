/**
 * 
 */
package com.ccm.api.model.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Jenny
 * 
 */
public class MasterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139046909679552605L;

	private String channel;// 渠道代码
	private String sta; // 预定状态，OrderStatus
	private String hotelCode;// 酒店编码

	private Integer numberOfOrders;// 订单总数
	private Integer numberOfUnits;// 房间数量
	private Long sumArrDays; // 间夜总数 总入住天数 ：统计用的
	private BigDecimal totalCharge;// 总收入
	private BigDecimal avgCharge;// 平均房价=总房价除以间夜数 保留小数点后四位
	private String avgArrDays; // 平均入住时长=间夜总数除以订单总数 保留小数点后两位

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the sta
	 */
	public String getSta() {
		return sta;
	}

	/**
	 * @param sta
	 *            the sta to set
	 */
	public void setSta(String sta) {
		this.sta = sta;
	}

	/**
	 * @return the hotelCode
	 */
	public String getHotelCode() {
		return hotelCode;
	}

	/**
	 * @param hotelCode
	 *            the hotelCode to set
	 */
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	/**
	 * @return the numberOfOrders
	 */
	public Integer getNumberOfOrders() {
		return numberOfOrders;
	}

	/**
	 * @param numberOfOrders
	 *            the numberOfOrders to set
	 */
	public void setNumberOfOrders(Integer numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	/**
	 * @return the numberOfUnits
	 */
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}

	/**
	 * @param numberOfUnits
	 *            the numberOfUnits to set
	 */
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	/**
	 * @return the sumArrDays
	 */
	public Long getSumArrDays() {
		return sumArrDays;
	}

	/**
	 * @param sumArrDays
	 *            the sumArrDays to set
	 */
	public void setSumArrDays(Long sumArrDays) {
		this.sumArrDays = sumArrDays;
	}

	/**
	 * @return the totalCharge
	 */
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	/**
	 * @param totalCharge
	 *            the totalCharge to set
	 */
	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	/**
	 * @return the avgArrDays
	 */
	public String getAvgArrDays() {
		if (sumArrDays != null && numberOfOrders != null) {
			float result = (float) sumArrDays / numberOfOrders;// 将整数其中一个强制转换成浮点数，再与另一个整数相除
			DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
			return df.format(result);// 返回的是String类型的
		}
		return avgArrDays;
	}

	/**
	 * @param avgArrDays
	 *            the avgArrDays to set
	 */
	public void setAvgArrDays(String avgArrDays) {
		this.avgArrDays = avgArrDays;
	}

	/**
	 * @return the avgCharge
	 */
	public BigDecimal getAvgCharge() {
		if (totalCharge != null && sumArrDays != null) {
			return totalCharge.divide(new BigDecimal(sumArrDays), 4);
		}
		return avgCharge;
	}

	/**
	 * @param avgCharge
	 *            the avgCharge to set
	 */
	public void setAvgCharge(BigDecimal avgCharge) {
		this.avgCharge = avgCharge;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MasterVO [channel=" + channel + ", sta=" + sta + ", hotelCode=" + hotelCode + ", numberOfOrders=" + numberOfOrders + ", numberOfUnits=" + numberOfUnits + ", sumArrDays=" + sumArrDays + ", totalCharge=" + totalCharge + ", avgArrDays=" + avgArrDays + ", avgCharge=" + avgCharge + "]";
	}

}
