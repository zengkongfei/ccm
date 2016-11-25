/**
 * 
 */
package com.ccm.api.model.log;

import com.ccm.api.common.Column;
import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class ReceiveReqResLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4040166497815621385L;

	private String receivereqresId;

	@Column(title = "渠道代码")
	private String interfaceId;// 渠道代码ChannelCodeEnum

	@Column(title = "集团代码")
	private String chainCode;// 集团代码

	@Column(title = "酒店代码")
	private String hotelCode;// 酒店代码

	@Column(title = "消息类型")
	private String type;// 消息类型,参考MessageType

	@Column(title = "酒店ID/房型代码/房价代码")
	private String extId;// 业务Id号

	@Column(title = "接收状态")
	private String status;// 状态

	@Column(title = "处理状态")
	private String processStatus;

	@Column(title = "请求消息")
	private String request;// 请求消息

	@Column(title = "返回消息")
	private String response;// 返回消息内容

	@Column(title = "异常错误")
	private String exception;// 异常错误

	private String module;// 模块类型，订单-RESERVATION

	private String encode;

	private boolean updateFlag;

	public String getReceivereqresId() {
		return receivereqresId;
	}

	public void setReceivereqresId(String receivereqresId) {
		this.receivereqresId = receivereqresId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	@Override
	public String toString() {
		return "ReceiveReqResLog [receivereqresId=" + receivereqresId + ", interfaceId=" + interfaceId + ", chainCode=" + chainCode + ", hotelCode=" + hotelCode + ", type=" + type + ", extId=" + extId + ", status=" + status + ", processStatus=" + processStatus + ", request=" + request + ", response=" + response + ", exception=" + exception + ", module=" + module + ", encode=" + encode + ", updateFlag=" + updateFlag + "]";
	}

}
