/**
 * 
 */
package com.ccm.api.model.log.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * @author Jenny Liao
 * 
 */
public class UpDownPmsLogCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3681085466414249493L;


	private String hotelCode;
	private Date createStart;
	private Date createEnd;
	private String ccmMsgId;// CCM消息编号
	private String extMsgId;// 外部消息编号
	private String messageType;// 消息模块
	private String action;// 消息类型
	private String status;// 消息状态 日志状态
	private String orderId;// 订单号
	private String upDown;// 下传到PMS或PMS上传

	private String tableName;
	
    private List<String> downPmsMsgStatusList;//消息状态 日志状态
	private List<String> hotelCodeList;
	private List<String> actions;//消息类型
	private List<String> messageTypes;//消息模块
	
	private String messageContentRoomType;//消息内容房型
	private Date messageContentDate;//消息内容日期
	
	public String getMessageContentRoomType() {
		return messageContentRoomType;
	}

	public void setMessageContentRoomType(String messageContentRoomType) {
		this.messageContentRoomType = messageContentRoomType;
	}

	public Date getMessageContentDate() {
		return messageContentDate;
	}

	public void setMessageContentDate(Date messageContentDate) {
		this.messageContentDate = messageContentDate;
	}

	public List<String> getMessageTypes() {
		return messageTypes;
	}

	public void setMessageTypes(List<String> messageTypes) {
		this.messageTypes = messageTypes;
	}

	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}

	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
	}


	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Date getCreateStart() {
		return createStart;
	}

	public void setCreateStart(Date createStart) {
		this.createStart = createStart;
	}

	public Date getCreateEnd() {
		return createEnd;
	}

	public void setCreateEnd(Date createEnd) {
		this.createEnd = createEnd;
	}

	public String getCcmMsgId() {
		return ccmMsgId;
	}

	public void setCcmMsgId(String ccmMsgId) {
		this.ccmMsgId = ccmMsgId;
	}

	public String getExtMsgId() {
		return extMsgId;
	}

	public void setExtMsgId(String extMsgId) {
		this.extMsgId = extMsgId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUpDown() {
		return upDown;
	}

	public void setUpDown(String upDown) {
		this.upDown = upDown;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
     
	public List<String> getDownPmsMsgStatusList() {
		return downPmsMsgStatusList;
	}

	public void setDownPmsMsgStatusList(List<String> downPmsMsgStatusList) {
		this.downPmsMsgStatusList = downPmsMsgStatusList;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		return "UpDownPmsLogCriteria [  hotelCode=" + hotelCode + ", createStart=" + createStart + ", createEnd=" + createEnd + ", ccmMsgId=" + ccmMsgId + ", extMsgId=" + extMsgId + ", messageType=" + messageType + ", action=" + action + ", status=" + status + ", orderId=" + orderId + ", upDown=" + upDown + ", tableName=" + tableName + "]";
	}

}
