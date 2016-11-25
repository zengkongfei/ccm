package com.ccm.api.model.order;

import java.util.Date;
import java.util.HashMap;

import com.ccm.api.model.base.BaseObject;

public class TaobaoOrder extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2232539270982583233L;

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
    private String errMsg;
    private String sortBy;  //查询时使用其它请忽略
    private static final HashMap<String,String> taobaoStatusMap = new HashMap<String, String>(); 
    static {
        taobaoStatusMap.put("WAIT_BUYER_PAY", "未冻结/未付款 -> 等待买家付款");
        taobaoStatusMap.put("WAIT_SELLER_SEND_GOODS","已冻结/已付款 -> 等待卖家发货 -> 等待卖家确认");
        taobaoStatusMap.put("TRADE_CLOSED", "已退款 -> 交易关闭");
        taobaoStatusMap.put("TRADE_FINISHED", "已转交易 -> 交易成功");
        taobaoStatusMap.put("TRADE_NO_CREATE_PAY", "没有创建支付宝交易");
        taobaoStatusMap.put("TRADE_CLOSED_BY_TAOBAO", "交易被淘宝关闭->卖家关闭交易或卖家核实未入住");
        taobaoStatusMap.put("TRADE_SUCCESS", "预定成功");
    }
    public static String getTaobaoStatusMap(String key){
        return taobaoStatusMap.get(key);
    }
	/**以逗号连接的畅连订单确认号*/
	private String owsOrderNos;
	
	public static final Integer ORDERTYPE_TB_CCM = 0;
	public static final Integer ORDERTYPE_TB_ONLINE = 1;
	
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

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "TaobaoOrder [modify=" + modified + ", orderId=" + orderId
				+ ", sendStatus=" + sendStatus + ", status=" + status
				+ ", taobaoOrderId=" + taobaoOrderId + ", tradeId=" + tradeId
				+ ", userId=" + userId + ", orderType=" + orderType + ", owsOrderNos=" + owsOrderNos +"]";
	}

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOwsOrderNos() {
        return owsOrderNos;
    }

    public void setOwsOrderNos(String owsOrderNos) {
        this.owsOrderNos = owsOrderNos;
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

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
