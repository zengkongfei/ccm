package com.ccm.api.model.channel;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 同步记录
 * @author carr
 *
 */
public class SynchronousLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7730815456624883246L;

	private String synchronousLogId;//序号      
	private String channelGoodsId;//渠道绑定序号
	private String request;//请求               
	private String response;//返回              
	private Integer status;//状态                
	private Date createdDate;//创建时间       
	private Date finishDate;//完成时间        
	
	public String getSynchronousLogId() {
		return synchronousLogId;
	}
	public void setSynchronousLogId(String synchronousLogId) {
		this.synchronousLogId = synchronousLogId;
	}
	public String getChannelGoodsId() {
		return channelGoodsId;
	}
	public void setChannelGoodsId(String channelGoodsId) {
		this.channelGoodsId = channelGoodsId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	@Override
	public String toString() {
		return "SynchronousLog [synchronousLogId=" + synchronousLogId
				+ ", channelGoodsId=" + channelGoodsId + ", request=" + request
				+ ", response=" + response + ", status=" + status
				+ ", createdDate=" + createdDate + ", finishDate=" + finishDate
				+ "]";
	}
}
