package com.ccm.api.model.channel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.util.CommonUtil;

public class Rateplan extends BaseObject {

	private static final long serialVersionUID = 136860314490114579L;

	private String ratePlanId;// 序号

	/*** 一下为新表字段 */

	private String hotelId;// 酒店序号
	private Date effectiveDate;// 生效时间
	private Date expireDate;// 失效时间
	private Integer ratePlanType;// 房价类型
	private String ratePlanCode;// 房价代码
	private String marketCode;// 市场代码
	private Integer availabilityStatus = 1;// 可用性
	private Boolean isViewabled;// 是否可见
	private Integer qualificationType;// 获取资格
	private Boolean isTaxIncluded;// 是否含税
	private Boolean isServiceFeeIncluded;// 是否含服务费
	private Boolean isBreakfastIncluded;// 是否含早餐
	private Boolean isLunchIncluded;// 是否含午餐
	private Boolean isDinnerIncluded;// 是否含晚餐
	private String inheritRatePlanId;// 基础房价代码
	private Integer basicType;// 计算类型
	private BigDecimal percent;// 相对百分比
	private BigDecimal amount;// 相对金额
	private String canBeSoldFunction;// 可卖条件公式
	private String baseRateCode; // 基础房价
	private String sourceCode;// 来源
	private Boolean isNegotiated;// 是否协议价
	private Boolean isExtraControl;// 是否外部控制
	private String commissionCode;// 佣金代码
	private BigDecimal commisionPercent;// 佣金比例

	private Integer orderNum;
	private String accessCode;// custom表中字段
	private String payment;// 用于订单下传PMS备注

	// b2b暂时使用字段---start
	// 应对应担保规则
	private Integer paymentType;// 支付类型
	// 应对应包价
	private Integer breakfastCount;// 早餐类型
	private Integer fee_breakfast_count;// 额外服务-早餐-数量（0-99）
	private Integer fee_breakfast_amount;// 额外服务-早餐-金额（1-9999）
	private Integer fee_gov_tax_amount;// 额外服务-政府税-金额（0-9999）
	private Integer fee_gov_tax_percent;// 额外服务-政府税-百分比（0%-99%）
	private Integer fee_service_amount;// 额外服务-服务费-金额（0-9999）
	private Integer fee_service_percent;// 额外服务-服务费-百分比（0%-99%）
	// 应对应可卖条件
	private Integer min_days;// 最小入住天数（1-90）。默认1
	private Integer max_days;// 最大入住天数（1-90）。默认90
	private Integer min_amount;// 首日入住房间数（1-99）。默认1
	private Integer min_adv_hours;// 最小提前预订小时按入住时间的23:59:59(一般认为24点)来计算
	private Integer max_adv_hours;// 最大提前预订小时按入住时间的23:59:59(一般认为24点)来计算
	// 应对应房价担保
	private Date startTime;// 每日生效时间,00:00
	private Date endTime;// 每日结束时间,00:00
	// b2b暂时使用字段---end
	private String outerId;
	private String hotelCode;// 用查询结果使用
	private Long rpId;
	private String includeRoomType;
	private String hotelName;

	private String soldableCondition;// 可卖条件
	private String rateDetailId;

	private String transactionId;
	private Boolean isNew; // pms上传时判断是否是新的房价
	private Boolean isSuper = false;// 是否是超级房价

	private Boolean priceValidate = true;// 是否验证价格

	private Boolean isPmsUpload = true;// 是否pms上传房价

	public Boolean getIsPmsUpload() {
		return isPmsUpload;
	}

	public void setIsPmsUpload(Boolean isPmsUpload) {
		this.isPmsUpload = isPmsUpload;
	}

	public Boolean getPriceValidate() {
		return priceValidate;
	}

	public void setPriceValidate(Boolean priceValidate) {
		this.priceValidate = priceValidate;
	}

	public Boolean getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(Boolean isSuper) {
		this.isSuper = isSuper;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getRateDetailId() {
		return rateDetailId;
	}

	public void setRateDetailId(String rateDetailId) {
		this.rateDetailId = rateDetailId;
	}

	public Long getRpId() {
		return rpId;
	}

	public void setRpId(Long rpId) {
		this.rpId = rpId;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getRatePlanType() {
		return ratePlanType;
	}

	public void setRatePlanType(Integer ratePlanType) {
		this.ratePlanType = ratePlanType;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		if (ratePlanCode != null) {
			ratePlanCode = ratePlanCode.trim();
		}
		this.ratePlanCode = ratePlanCode;
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public Integer getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(Integer availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public Boolean getIsViewabled() {
		return isViewabled;
	}

	public void setIsViewabled(Boolean isViewabled) {
		this.isViewabled = isViewabled;
	}

	public Integer getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(Integer qualificationType) {
		this.qualificationType = qualificationType;
	}

	public Boolean getIsTaxIncluded() {
		return isTaxIncluded;
	}

	public void setIsTaxIncluded(Boolean isTaxIncluded) {
		this.isTaxIncluded = isTaxIncluded;
	}

	public Boolean getIsServiceFeeIncluded() {
		return isServiceFeeIncluded;
	}

	public void setIsServiceFeeIncluded(Boolean isServiceFeeIncluded) {
		this.isServiceFeeIncluded = isServiceFeeIncluded;
	}

	public Boolean getIsBreakfastIncluded() {
		return isBreakfastIncluded;
	}

	public void setIsBreakfastIncluded(Boolean isBreakfastIncluded) {
		this.isBreakfastIncluded = isBreakfastIncluded;
	}

	public Boolean getIsLunchIncluded() {
		return isLunchIncluded;
	}

	public void setIsLunchIncluded(Boolean isLunchIncluded) {
		this.isLunchIncluded = isLunchIncluded;
	}

	public Boolean getIsDinnerIncluded() {
		return isDinnerIncluded;
	}

	public void setIsDinnerIncluded(Boolean isDinnerIncluded) {
		this.isDinnerIncluded = isDinnerIncluded;
	}

	public String getInheritRatePlanId() {
		return inheritRatePlanId;
	}

	public void setInheritRatePlanId(String inheritRatePlanId) {
		this.inheritRatePlanId = inheritRatePlanId;
	}

	public Integer getBasicType() {
		return basicType;
	}

	public void setBasicType(Integer basicType) {
		this.basicType = basicType;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCanBeSoldFunction() {
		return canBeSoldFunction;
	}

	public void setCanBeSoldFunction(String canBeSoldFunction) {
		this.canBeSoldFunction = canBeSoldFunction;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Boolean getIsNegotiated() {
		return isNegotiated;
	}

	public void setIsNegotiated(Boolean isNegotiated) {
		this.isNegotiated = isNegotiated;
	}

	public Boolean getIsExtraControl() {
		return isExtraControl;
	}

	public void setIsExtraControl(Boolean isExtraControl) {
		this.isExtraControl = isExtraControl;
	}

	public String getCommissionCode() {
		return commissionCode;
	}

	public void setCommissionCode(String commissionCode) {
		this.commissionCode = commissionCode;
	}

	public BigDecimal getCommisionPercent() {
		return commisionPercent;
	}

	public void setCommisionPercent(BigDecimal commisionPercent) {
		this.commisionPercent = commisionPercent;
	}

	public String getBaseRateCode() {
		return baseRateCode;
	}

	public void setBaseRateCode(String baseRateCode) {
		this.baseRateCode = baseRateCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getBreakfastCount() {
		return breakfastCount;
	}

	public void setBreakfastCount(Integer breakfastCount) {
		this.breakfastCount = breakfastCount;
	}

	public Integer getFee_breakfast_count() {
		return fee_breakfast_count;
	}

	public void setFee_breakfast_count(Integer fee_breakfast_count) {
		this.fee_breakfast_count = fee_breakfast_count;
	}

	public Integer getFee_breakfast_amount() {
		return fee_breakfast_amount;
	}

	public void setFee_breakfast_amount(Integer fee_breakfast_amount) {
		this.fee_breakfast_amount = fee_breakfast_amount;
	}

	public Integer getFee_gov_tax_amount() {
		return fee_gov_tax_amount;
	}

	public void setFee_gov_tax_amount(Integer fee_gov_tax_amount) {
		this.fee_gov_tax_amount = fee_gov_tax_amount;
	}

	public Integer getFee_gov_tax_percent() {
		return fee_gov_tax_percent;
	}

	public void setFee_gov_tax_percent(Integer fee_gov_tax_percent) {
		this.fee_gov_tax_percent = fee_gov_tax_percent;
	}

	public Integer getFee_service_amount() {
		return fee_service_amount;
	}

	public void setFee_service_amount(Integer fee_service_amount) {
		this.fee_service_amount = fee_service_amount;
	}

	public Integer getFee_service_percent() {
		return fee_service_percent;
	}

	public void setFee_service_percent(Integer fee_service_percent) {
		this.fee_service_percent = fee_service_percent;
	}

	public Integer getMin_days() {
		return min_days;
	}

	public void setMin_days(Integer min_days) {
		this.min_days = min_days;
	}

	public Integer getMax_days() {
		return max_days;
	}

	public void setMax_days(Integer max_days) {
		this.max_days = max_days;
	}

	public Integer getMin_amount() {
		return min_amount;
	}

	public void setMin_amount(Integer min_amount) {
		this.min_amount = min_amount;
	}

	public Integer getMin_adv_hours() {
		return min_adv_hours;
	}

	public void setMin_adv_hours(Integer min_adv_hours) {
		this.min_adv_hours = min_adv_hours;
	}

	public Integer getMax_adv_hours() {
		return max_adv_hours;
	}

	public void setMax_adv_hours(Integer max_adv_hours) {
		this.max_adv_hours = max_adv_hours;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getIncludeRoomType() {
		return includeRoomType;
	}

	public void setIncludeRoomType(String includeRoomType) {
		this.includeRoomType = includeRoomType;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getSoldableCondition() {
		return soldableCondition;
	}

	public void setSoldableCondition(String soldableCondition) {
		this.soldableCondition = soldableCondition;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<SoldableCondition> getSCList() {
		if (CommonUtil.isNotEmpty(this.soldableCondition)) {
			return JSONArray.parseArray(soldableCondition, SoldableCondition.class);
		}
		return new ArrayList<SoldableCondition>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rateplan [ratePlanId=" + ratePlanId + ", hotelId=" + hotelId + ", effectiveDate=" + effectiveDate + ", expireDate=" + expireDate + ", ratePlanType=" + ratePlanType + ", ratePlanCode=" + ratePlanCode + ", marketCode=" + marketCode + ", availabilityStatus=" + availabilityStatus + ", isViewabled=" + isViewabled + ", qualificationType=" + qualificationType + ", isTaxIncluded=" + isTaxIncluded + ", isServiceFeeIncluded=" + isServiceFeeIncluded + ", isBreakfastIncluded=" + isBreakfastIncluded + ", isLunchIncluded=" + isLunchIncluded + ", isDinnerIncluded=" + isDinnerIncluded + ", inheritRatePlanId=" + inheritRatePlanId + ", basicType=" + basicType + ", percent=" + percent + ", amount=" + amount + ", canBeSoldFunction=" + canBeSoldFunction + ", baseRateCode=" + baseRateCode
				+ ", sourceCode=" + sourceCode + ", isNegotiated=" + isNegotiated + ", isExtraControl=" + isExtraControl + ", commissionCode=" + commissionCode + ", commisionPercent=" + commisionPercent + ", orderNum=" + orderNum + ", accessCode=" + accessCode + ", payment=" + payment + ", paymentType=" + paymentType + ", breakfastCount=" + breakfastCount + ", fee_breakfast_count=" + fee_breakfast_count + ", fee_breakfast_amount=" + fee_breakfast_amount + ", fee_gov_tax_amount=" + fee_gov_tax_amount + ", fee_gov_tax_percent=" + fee_gov_tax_percent + ", fee_service_amount=" + fee_service_amount + ", fee_service_percent=" + fee_service_percent + ", min_days=" + min_days + ", max_days=" + max_days + ", min_amount=" + min_amount + ", min_adv_hours=" + min_adv_hours + ", max_adv_hours="
				+ max_adv_hours + ", startTime=" + startTime + ", endTime=" + endTime + ", outerId=" + outerId + ", hotelCode=" + hotelCode + ", rpId=" + rpId + ", includeRoomType=" + includeRoomType + ", hotelName=" + hotelName + ", soldableCondition=" + soldableCondition + ", rateDetailId=" + rateDetailId + ", transactionId=" + transactionId + ", isNew=" + isNew + ", isSuper=" + isSuper + ", priceValidate=" + priceValidate + ", isPmsUpload=" + isPmsUpload + "]";
	}

}