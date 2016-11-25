package com.ccm.api.model.ratePlan;

import java.math.BigDecimal;
import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 打包服务
 * @author carr
 *
 */
public class Package extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -561265221947801494L;
	
	public static final int CALCULATION_EVENINGS = 1;//按每晚
	public static final int CALCULATION_CHECKIN = 2;//只算到达日
	public static final int CALCULATION_CHECKOUT = 3;//只算离店日
	public static final int CALCULATION_WEEKS = 4;// 在一周内的某（几）天
	public static final int CALCULATION_TIMES = 5;//在一段时间内（几月几日~几月几日）
	
	public static final int RULE_PRICE = 1;//固定价格
	public static final int RULE_ADULT = 2;//每成人
	public static final int RULE_CHILDREN = 3;//每小孩
	public static final int RULE_PEOPLE = 4;//每人
	public static final int RULE_ROOMS = 5;//每房间数
	
	public static final int BASICTYPE_PERCENT = 1;//按百分比(percent)
	public static final int BASICTYPE_ABSOLUTION = 2;//按具体值(absolution)

	private String packageId;//序号
	private String packageCode;//打包服务代码
	private Boolean isExtraCharge;//是否额外收费。true:在房价上另加；false:包含在房价内
	private Integer 	calculation	;//	计算方式
	private Boolean 	isApplyToMonday	;//	周一适用
	private Boolean 	isApplyToTuesday	;//	周二适用
	private Boolean 	isApplyToWednesday	;//	周三适用
	private Boolean 	isApplyToThursday	;//	周四适用
	private Boolean 	isApplyToFriday	;//	周五适用
	private Boolean 	isApplyToSaturday	;//	周六适用
	private Boolean 	isApplyToSunday	;//	周日适用
	private Date 	beginDate	;//	起始日期
	private Date 	endDate	;//	终止日期
	private Integer 	rule	;//	计算规则
	private Integer 	basicType	;//	计算类型
	private String 	percent	;//	相对百分比
	private String 	amount	;//	相对金额
	private Boolean issvcortax;
	public Boolean getIssvcortax() {
		return issvcortax;
	}
	public void setIssvcortax(Boolean issvcortax) {
		this.issvcortax = issvcortax;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public Boolean getIsExtraCharge() {
		return isExtraCharge;
	}
	public void setIsExtraCharge(Boolean isExtraCharge) {
		this.isExtraCharge = isExtraCharge;
	}
	public Integer getCalculation() {
		return calculation;
	}
	public void setCalculation(Integer calculation) {
		this.calculation = calculation;
	}
	public Boolean getIsApplyToMonday() {
		return isApplyToMonday;
	}
	public void setIsApplyToMonday(Boolean isApplyToMonday) {
		this.isApplyToMonday = isApplyToMonday;
	}
	public Boolean getIsApplyToTuesday() {
		return isApplyToTuesday;
	}
	public void setIsApplyToTuesday(Boolean isApplyToTuesday) {
		this.isApplyToTuesday = isApplyToTuesday;
	}
	public Boolean getIsApplyToWednesday() {
		return isApplyToWednesday;
	}
	public void setIsApplyToWednesday(Boolean isApplyToWednesday) {
		this.isApplyToWednesday = isApplyToWednesday;
	}
	public Boolean getIsApplyToThursday() {
		return isApplyToThursday;
	}
	public void setIsApplyToThursday(Boolean isApplyToThursday) {
		this.isApplyToThursday = isApplyToThursday;
	}
	public Boolean getIsApplyToFriday() {
		return isApplyToFriday;
	}
	public void setIsApplyToFriday(Boolean isApplyToFriday) {
		this.isApplyToFriday = isApplyToFriday;
	}
	public Boolean getIsApplyToSaturday() {
		return isApplyToSaturday;
	}
	public void setIsApplyToSaturday(Boolean isApplyToSaturday) {
		this.isApplyToSaturday = isApplyToSaturday;
	}
	public Boolean getIsApplyToSunday() {
		return isApplyToSunday;
	}
	public void setIsApplyToSunday(Boolean isApplyToSunday) {
		this.isApplyToSunday = isApplyToSunday;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getRule() {
		return rule;
	}
	public void setRule(Integer rule) {
		this.rule = rule;
	}
	public Integer getBasicType() {
		return basicType;
	}
	public void setBasicType(Integer basicType) {
		this.basicType = basicType;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getAmount() {
		return amount;
	}

	public BigDecimal getAmountBigDecimal() {
		if (amount != null) {
			return new BigDecimal(amount);
		}
		return BigDecimal.ZERO;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public boolean equalsPackageCode(Object obj){  
	        if(obj == null){  
	            return false;  
	        }else {           
	                if(this.getClass() == obj.getClass()){  
	                    Package p = (Package) obj;  
	                    if(this.getPackageCode().equals(p.getPackageCode())){  
	                        return true;  
	                    }else{  
	                        return false;  
	                    }  
	                  
	            }else{  
	                return false;  
	            }  
	      }             
	 }  
	@Override
	public String toString() {
		return "Package [packageId=" + packageId + ", packageCode="
				+ packageCode + ", isExtraCharge=" + isExtraCharge
				+ ", calculation=" + calculation + ", isApplyToMonday="
				+ isApplyToMonday + ", isApplyToTuesday=" + isApplyToTuesday
				+ ", isApplyToWednesday=" + isApplyToWednesday
				+ ", isApplyToThursday=" + isApplyToThursday
				+ ", isApplyToFriday=" + isApplyToFriday
				+ ", isApplyToSaturday=" + isApplyToSaturday
				+ ", isApplyToSunday=" + isApplyToSunday + ", beginDate="
				+ beginDate + ", endDate=" + endDate + ", rule=" + rule
				+ ", basicType=" + basicType + ", percent=" + percent
				+ ", amount=" + amount + "]";
	}
}
