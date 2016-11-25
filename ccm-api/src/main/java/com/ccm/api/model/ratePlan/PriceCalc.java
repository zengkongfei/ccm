package com.ccm.api.model.ratePlan;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.ratePlan.vo.PackageVO;

/**
 * 价格日历
 * 
 * @author carr
 * 
 */
public class PriceCalc extends BaseObject {

	private static final long serialVersionUID = 374042938224534297L;
	@Id
	private String priceCalcId;// 序号
	private String rateDetailId;// 序号
	private String channelCode;// 渠道代码
	private String chainCode;// 集团代码
	private String hotelCode;// 酒店代码
	private String roomTypeCode;// 房型代码
	private String ratePlanCode;// 房价代码
	private String date;// 日期
	private BigDecimal amount;// 价格
	private Integer numberOfUnits; // 人数
	private Integer onOff;// 房价开关

	private String startDate; // 供查询
	private String endDate;// 供查询
	private BigDecimal totalAmount;// 总价格
	private BigDecimal packageAmount;// 包价金额
	private List<PackageVO> packageVOList;// 包信息
	private List<RateAmount> rateAmountList;

	private List<String> roomTypeCodeList;// FetchCalendar,开关使用
	private List<String> ratePlanCodeList;// 开关使用
	private String sendStatus;
	private String hotelId;
	private String channelId;
	private String roomTypeIdsql;
	private String ratePlanCodesql;
	private BigDecimal amountAfterTax;//税后价格
	private String currencyCode; // 货币类型
	
	private Boolean useAmountAfterTax=false;//使用税前还是税后
	private BigDecimal taxFeeN = new BigDecimal("0");//固定税费 每间房每次入住
	private BigDecimal taxFeeS = new BigDecimal("0");//固定税费 每间房每次入住
	private BigDecimal taxFeePS= new BigDecimal("0");//固定税费 每人每次入住
	private BigDecimal taxFeePN= new BigDecimal("0");//固定税费 每人每晚
	
	private BigDecimal taxRateN = new BigDecimal("0");//税率 每间房每次入住
	private BigDecimal taxRateS = new BigDecimal("0");//税率 每间房每次入住
	private BigDecimal taxRatePS= new BigDecimal("0");//税率 每人每次入住
	private BigDecimal taxRatePN= new BigDecimal("0");//税率 每人每晚
	
	public BigDecimal getAmountAfterTax() {
		return amountAfterTax;
	}

	public void setAmountAfterTax(BigDecimal amountAfterTax) {
		this.amountAfterTax = amountAfterTax;
	}

	public String getRatePlanCodesql() {
		return ratePlanCodesql;
	}

	public void setRatePlanCodesql(String ratePlanCodesql) {
		this.ratePlanCodesql = ratePlanCodesql;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getPriceCalcId() {
		return priceCalcId;
	}

	public void setPriceCalcId(String priceCalcId) {
		this.priceCalcId = priceCalcId;
	}

	public String getRateDetailId() {
		return rateDetailId;
	}

	public void setRateDetailId(String rateDetailId) {
		this.rateDetailId = rateDetailId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getOnOff() {
		return onOff;
	}

	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}

	public List<PackageVO> getPackageVOList() {
		return packageVOList;
	}

	public void setPackageVOList(List<PackageVO> packageVOList) {
		this.packageVOList = packageVOList;
	}

	public BigDecimal getPackageAmount() {
		return packageAmount;
	}

	public void setPackageAmount(BigDecimal packageAmount) {
		this.packageAmount = packageAmount;
	}

	public List<RateAmount> getRateAmountList() {
		return rateAmountList;
	}

	public void setRateAmountList(List<RateAmount> rateAmountList) {
		this.rateAmountList = rateAmountList;
	}

	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}

	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomTypeIdsql() {
		return roomTypeIdsql;
	}

	public void setRoomTypeIdsql(String roomTypeIdsql) {
		this.roomTypeIdsql = roomTypeIdsql;
	}

	public List<String> getRatePlanCodeList() {
		return ratePlanCodeList;
	}

	public void setRatePlanCodeList(List<String> ratePlanCodeList) {
		this.ratePlanCodeList = ratePlanCodeList;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Boolean getUseAmountAfterTax() {
		return useAmountAfterTax;
	}

	public void setUseAmountAfterTax(Boolean useAmountAfterTax) {
		this.useAmountAfterTax = useAmountAfterTax;
	}

	public BigDecimal getTaxFeeS() {
		return taxFeeS;
	}

	public void setTaxFeeS(BigDecimal taxFeeS) {
		this.taxFeeS = taxFeeS;
	}

	public BigDecimal getTaxFeePS() {
		return taxFeePS;
	}

	public void setTaxFeePS(BigDecimal taxFeePS) {
		this.taxFeePS = taxFeePS;
	}

	public BigDecimal getTaxFeeN() {
		return taxFeeN;
	}

	public void setTaxFeeN(BigDecimal taxFeeN) {
		this.taxFeeN = taxFeeN;
	}

	public BigDecimal getTaxRateN() {
		return taxRateN;
	}

	public void setTaxRateN(BigDecimal taxRateN) {
		this.taxRateN = taxRateN;
	}

	public BigDecimal getTaxRateS() {
		return taxRateS;
	}

	public void setTaxRateS(BigDecimal taxRateS) {
		this.taxRateS = taxRateS;
	}

	public BigDecimal getTaxRatePS() {
		return taxRatePS;
	}

	public void setTaxRatePS(BigDecimal taxRatePS) {
		this.taxRatePS = taxRatePS;
	}

	public BigDecimal getTaxRatePN() {
		return taxRatePN;
	}

	public void setTaxRatePN(BigDecimal taxRatePN) {
		this.taxRatePN = taxRatePN;
	}

	public BigDecimal getTaxFeePN() {
		return taxFeePN;
	}

	public void setTaxFeePN(BigDecimal taxFeePN) {
		this.taxFeePN = taxFeePN;
	}

	@Override
	public String toString() {
		return "PriceCalc [priceCalcId=" + priceCalcId + ", rateDetailId="
				+ rateDetailId + ", channelCode=" + channelCode
				+ ", chainCode=" + chainCode + ", hotelCode=" + hotelCode
				+ ", roomTypeCode=" + roomTypeCode + ", ratePlanCode="
				+ ratePlanCode + ", date=" + date + ", amount=" + amount
				+ ", numberOfUnits=" + numberOfUnits + ", onOff=" + onOff
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", totalAmount=" + totalAmount + ", packageAmount="
				+ packageAmount + ", packageVOList=" + packageVOList
				+ ", rateAmountList=" + rateAmountList + ", roomTypeCodeList="
				+ roomTypeCodeList + ", ratePlanCodeList=" + ratePlanCodeList
				+ ", sendStatus=" + sendStatus + ", hotelId=" + hotelId
				+ ", channelId=" + channelId + ", roomTypeIdsql="
				+ roomTypeIdsql + ", ratePlanCodesql=" + ratePlanCodesql
				+ ", amountAfterTax=" + amountAfterTax + ", currencyCode="
				+ currencyCode + ", useAmountAfterTax=" + useAmountAfterTax
				+ ", taxFeeN=" + taxFeeN + ", taxFeeS=" + taxFeeS
				+ ", taxFeePS=" + taxFeePS + ", taxFeePN=" + taxFeePN
				+ ", taxRateN=" + taxRateN + ", taxRateS=" + taxRateS
				+ ", taxRatePS=" + taxRatePS + ", taxRatePN=" + taxRatePN + "]";
	}


	
}
