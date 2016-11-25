package com.ccm.api.model.wbe;

import java.math.BigDecimal;
import java.util.Date;

import com.ccm.api.model.base.BaseObject;

public class WbeEffectiveData  extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -744123849376711841L;
	
	public Date getCurDate() {
		return curDate;
	}
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
	public BigDecimal getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}
	public Integer getAvai() {
		return avai;
	}
	public void setAvai(Integer avai) {
		this.avai = avai;
	}
	private Date curDate;
	private BigDecimal baseAmount;
	private Integer avai;
}
