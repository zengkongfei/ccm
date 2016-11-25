package com.ccm.api.model.ratePlan;

import java.math.BigDecimal;
import java.util.HashMap;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价价格
 * @author carr
 *
 */
public class RateAmount extends BaseObject implements Cloneable{

	@Override
	public RateAmount clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (RateAmount)super.clone();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8260270469213461796L;
	
	private String rateAmountId;//序号          
	private String rateDetailId;//房价明细序号  
	private Integer numberOfUnits;//人数         
	private BigDecimal baseAmount;//金额     
	private Integer ageQualifyingCode;//年龄分类
	private BigDecimal amountAfterTax;//税收价格

	public static HashMap<Integer, String> personMap = new HashMap<Integer, String>();
	static{
	    personMap.put(1,"OnePerson");
	    personMap.put(2,"TwoPerson");
	    personMap.put(3,"ThreePerson");
	    personMap.put(4,"FourPerson");
	    personMap.put(5,"FivePerson");
	    personMap.put(101,"ExtraAdult");//成人
	    personMap.put(102,"ExtraChild");//小孩
	}
	public BigDecimal getAmountAfterTax() {
		return amountAfterTax;
	}
	public void setAmountAfterTax(BigDecimal amountAfterTax) {
		this.amountAfterTax = amountAfterTax;
	}

	public static String getPerson(Integer key){
	    return personMap.get(key);
	}
	public String getRateAmountId() {
		return rateAmountId;
	}
	public void setRateAmountId(String rateAmountId) {
		this.rateAmountId = rateAmountId;
	}
	public String getRateDetailId() {
		return rateDetailId;
	}
	public void setRateDetailId(String rateDetailId) {
		this.rateDetailId = rateDetailId;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public BigDecimal getBaseAmount() {
		return baseAmount;
	}
	
	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}
	public Integer getAgeQualifyingCode() {
		return ageQualifyingCode;
	}
	public void setAgeQualifyingCode(Integer ageQualifyingCode) {
		this.ageQualifyingCode = ageQualifyingCode;
	}
	
	@Override
	public String toString() {
		return "RateAmount [rateAmountId=" + rateAmountId + ", rateDetailId="
				+ rateDetailId + ", numberOfUnits=" + numberOfUnits
				+ ", baseAmount=" + baseAmount + ", ageQualifyingCode="
				+ ageQualifyingCode + "]";
	}
}
