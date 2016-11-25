package com.ccm.api.model.sell;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 市场
 * @author carr
 *
 */
public class Market extends BaseObject {

	private static final long serialVersionUID = -4265745459104422977L;

	private String	marketId	;//	序号
	private String	marketCode	;//	代码
	private Date	effectiveDate	;//	生效时间
	private Date	expireDate	;//	失效时间
	
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
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
	
	@Override
	public String toString() {
		return "Market [marketId=" + marketId + ", marketCode=" + marketCode
				+ ", effectiveDate=" + effectiveDate + ", expireDate="
				+ expireDate + "]";
	}
}
