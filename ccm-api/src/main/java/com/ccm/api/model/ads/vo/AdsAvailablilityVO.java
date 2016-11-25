package com.ccm.api.model.ads.vo;

import java.io.Serializable;

/**
 * 
 * 直连- ads 房量 VO
 * 
 */
public class AdsAvailablilityVO implements Serializable {

    private static final long serialVersionUID = -4127218598795515049L;

    private String startDate; // 开始日期

    private String endDate; // 结束日期

    private String roomTypeCode; // 房型代码

    private int availableAmount; // 房量

    // 以下房量更新消息使用
    private boolean allRateCode; // 所有房价

    private String rateCode; // 具体房价 如allrate为空，则有具体ratecode

    private String actionCode;// 更新动作

    // private int minLenStay;//

    private int duration;// 连住几天

    private int lengthOfStay;// 最少入住

    private String targetGDS;

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

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public boolean isAllRateCode() {
        return allRateCode;
    }

    public void setAllRateCode(boolean allRateCode) {
        this.allRateCode = allRateCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getTargetGDS() {
        return targetGDS;
    }

    public void setTargetGDS(String targetGDS) {
        this.targetGDS = targetGDS;
    }

}
