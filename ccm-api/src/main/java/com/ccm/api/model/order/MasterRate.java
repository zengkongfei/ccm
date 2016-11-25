/**
 * 
 */
package com.ccm.api.model.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterRate extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6091219629053730099L;
	/**
	 * 
	 */
	private String masterId;// 订单号
	private String hotelId;// 酒店主健
	private Date date;//
	private BigDecimal rmrate;//优惠价格
	private BigDecimal setrateAfterTax;//税后房价
	private BigDecimal setrate;// 房价
	private BigDecimal packages;// 包价(1房)
	private Integer bkf;// 早餐数
	private String rtreason;// 理由
	private String manual;//
	private String cby;// 修改人
	private Date changed;// 最近修改

	private List<MasterPackage> mpList;// 动态与静态包价

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getPackages() {
		return packages;
	}

	public void setPackages(BigDecimal packages) {
		this.packages = packages;
	}

	public String getRtreason() {
		return rtreason;
	}

	public void setRtreason(String rtreason) {
		this.rtreason = rtreason;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public BigDecimal getRmrate() {
		if(rmrate==null){
			return new BigDecimal("0");
		}
		return rmrate;
	}

	public void setRmrate(BigDecimal rmrate) {
		this.rmrate = rmrate;
	}

	public BigDecimal getSetrate() {
		return setrate;
	}

	public void setSetrate(BigDecimal setrate) {
		this.setrate = setrate;
	}

	public Integer getBkf() {
		return bkf;
	}

	public void setBkf(Integer bkf) {
		this.bkf = bkf;
	}

	public String getCby() {
		return cby;
	}

	public void setCby(String cby) {
		this.cby = cby;
	}

	public Date getChanged() {
		return changed;
	}

	public void setChanged(Date changed) {
		this.changed = changed;
	}

	public List<MasterPackage> getMpList() {
		if (mpList == null) {
			mpList = new ArrayList<MasterPackage>();
		}
		return mpList;
	}

	public void setMpList(List<MasterPackage> mpList) {
		this.mpList = mpList;
	}

	public BigDecimal getSetrateAfterTax() {
		return setrateAfterTax;
	}

	public void setSetrateAfterTax(BigDecimal setrateAfterTax) {
		this.setrateAfterTax = setrateAfterTax;
	}


}
