package com.ccm.api.model.rsvtype.vo;

import com.ccm.api.model.base.criteria.SearchResult;
import com.ccm.api.model.rsvtype.Usage;

public class UsageResult extends SearchResult<Usage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4549369913422580115L;

	//记录总和
	private Integer availableSum;	//自由销售房量    = 酒店可售房
	private Integer allotmentSum;	//保留房销售房量  = 渠道保留房
	private Integer allotmentRemainSum; //保留房剩余房量  = 渠道保留房-渠道已售房
	public Integer getAvailableSum() {
		return availableSum;
	}
	public void setAvailableSum(Integer availableSum) {
		this.availableSum = availableSum;
	}
	public Integer getAllotmentSum() {
		return allotmentSum;
	}
	public void setAllotmentSum(Integer allotmentSum) {
		this.allotmentSum = allotmentSum;
	}
	public Integer getAllotmentRemainSum() {
		return allotmentRemainSum;
	}
	public void setAllotmentRemainSum(Integer allotmentRemainSum) {
		this.allotmentRemainSum = allotmentRemainSum;
	}
	
}
