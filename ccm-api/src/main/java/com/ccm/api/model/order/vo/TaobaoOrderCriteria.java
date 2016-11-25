package com.ccm.api.model.order.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class TaobaoOrderCriteria extends SearchCriteria {

    private static final long serialVersionUID = 2241610909495825852L;

    private String taobaoOrderId;
    private Long tradeId;
    private String status;
    private Date modified;
    private Integer sendStatus;
    private Long orderId;
    private String userId;
    private Integer orderType;
    private Date startDate;
    private Date endDate;
    public String getTaobaoOrderId() {
        return taobaoOrderId;
    }
    public void setTaobaoOrderId(String taobaoOrderId) {
        this.taobaoOrderId = taobaoOrderId;
    }
    public Long getTradeId() {
        return tradeId;
    }
    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
    public Integer getSendStatus() {
        return sendStatus;
    }
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getOrderType() {
        return orderType;
    }
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    

}
