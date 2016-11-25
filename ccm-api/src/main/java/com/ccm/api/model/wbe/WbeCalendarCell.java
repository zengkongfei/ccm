package com.ccm.api.model.wbe;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

public class WbeCalendarCell extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8554804413793180316L;

	private Date cellDate;// 单元格日期
	private Map<Integer, RateAmount> rateAmountMap = new HashMap<Integer, RateAmount>();// key:numberOfUnits(几人价)
																						// Value:RateAmount
	private Integer cellInventAvai;// 当前可用房量
	private BigDecimal cellPrice;// 单元格房价
	private Integer cellNumberOfUnits;// 几人价
	private CancelPolicyVO cancelPolicyVO;//取消规则
	private List<EffectiveGuarrntee> guarrnteeList;//担保
	public List<EffectiveGuarrntee> getGuarrnteeList() {
		return guarrnteeList;
	}

	public void setGuarrnteeList(List<EffectiveGuarrntee> guarrnteeList) {
		this.guarrnteeList = guarrnteeList;
	}

	public CancelPolicyVO getCancelPolicyVO() {
		return cancelPolicyVO;
	}

	public void setCancelPolicyVO(CancelPolicyVO cancelPolicyVO) {
		this.cancelPolicyVO = cancelPolicyVO;
	}

	public Date getCellDate() {
		return cellDate;
	}

	public void setCellDate(Date cellDate) {
		this.cellDate = cellDate;
	}

	public Map<Integer, RateAmount> getRateAmountMap() {
		return rateAmountMap;
	}

	public void setRateAmountMap(Map<Integer, RateAmount> rateAmountMap) {
		this.rateAmountMap = rateAmountMap;
	}

	public Integer getCellInventAvai() {
		return cellInventAvai;
	}

	public void setCellInventAvai(Integer cellInventAvai) {
		this.cellInventAvai = cellInventAvai;
	}

	public BigDecimal getCellPrice() {
		return cellPrice;
	}

	public void setCellPrice(BigDecimal cellPrice) {
		this.cellPrice = cellPrice;
	}

	public Integer getCellNumberOfUnits() {
		return cellNumberOfUnits;
	}

	public void setCellNumberOfUnits(Integer cellNumberOfUnits) {
		this.cellNumberOfUnits = cellNumberOfUnits;
	}

}
