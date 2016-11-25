/**
 * 
 */
package com.ccm.api.model.ads.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 直连- ads 房价VO
 *
 */
public class AdsRateUpdateVO implements Serializable {

	private static final long serialVersionUID = -4127218598795515049L;
	
	private String startDate;  // 开始日期
	
	private String endDate;   // 结束日期
	
	private  Map  weekFlag;   //星期标识
	
	private String roomTypeCode; //房型代码
	
	private String rateCode;    //房价代码	
	
	private String currencyCode; //货币代码	
	
	private String rateAmountType ;//房价类型，这里是指人数类型
	
	private float rateAmount;   //价格	
	
	private int  duration;//连住几天
   
	private String targetGDS;
	
	private Map rateAmountTypeMap;
	
	public void addWeekFlag(String week,Boolean flag){
		if(weekFlag==null){
			weekFlag = new HashMap();
		}
		weekFlag.put(week, flag);
		
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


	public String getRoomTypeCode() {
		return roomTypeCode;
	}


	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}


	public String getRateCode() {
		return rateCode;
	}


	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public String getRateAmountType() {
		return rateAmountType;
	}


	public void setRateAmountType(String rateAmountType) {
		this.rateAmountType = rateAmountType;
	}


	public float getRateAmount() {
		return rateAmount;
	}


	public void setRateAmount(float rateAmount) {
		this.rateAmount = rateAmount;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Map getWeekFlag() {
		return weekFlag;
	}

	public void setWeekFlag(Map weekFlag) {
		this.weekFlag = weekFlag;
	}

    public String getTargetGDS() {
        return targetGDS;
    }

    public void setTargetGDS(String targetGDS) {
        this.targetGDS = targetGDS;
    }

    public Map getRateAmountTypeMap() {
        return rateAmountTypeMap;
    }

    public void setRateAmountTypeMap(Map rateAmountTypeMap) {
        this.rateAmountTypeMap = rateAmountTypeMap;
    }


	
	
	
}
