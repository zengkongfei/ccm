package com.ccm.api.model.ratePlan;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 担保规则
 * 
 * @author carr
 * 
 */
public class GuaranteePolicy extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257443073287849196L;

	public static final int RETRIBUTIONTYPE_RES_AUTOMATICALLY_CANCELLED = 1;// 自动取消
	public static final int RETRIBUTIONTYPE_RES_NO_LONGER_GUARANTEED = 2;// //不取消

	private String guaranteeId;// 序号
	private String hotelId;// 酒店序号
	private Integer retributionType;// 超时处理
	private String guaranteeCode;// 担保代码
	private Integer guaranteeType;// 担保类型,参照GuaranteeType
	private Date holdTime;// 保留时间
	private Date validTime;// 几点前有效

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getRetributionType() {
		return retributionType;
	}

	public void setRetributionType(Integer retributionType) {
		this.retributionType = retributionType;
	}

	public String getGuaranteeCode() {
		return guaranteeCode;
	}

	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
	}

	public Integer getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(Integer guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public Date getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(Date holdTime) {
		this.holdTime = holdTime;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	@Override
	public String toString() {
		return "GuaranteePolicy [guaranteeId=" + guaranteeId + ", hotelId=" + hotelId + ", retributionType=" + retributionType + ", guaranteeCode=" + guaranteeCode + ", guaranteeType=" + guaranteeType + ", holdTime=" + holdTime + ", validTime=" + validTime + "]";
	}
}
