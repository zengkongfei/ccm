/**
 * 
 */
package com.ccm.api.model.log.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * @author Jenny Liao
 * 
 */
public class SearchOrderLogCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3681085466414249493L;


	private String type;
	private String hotelCode;
	private String status;// 多个状态查询
	private String extId;// 外部订单号
	private String interfaceId;// 渠道代码
	private String chainCode;// 集团代码
	private String module;

	private Date createStart;
	private Date createEnd;


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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "SearchOrderLogCriteria [ type=" + type + ", hotelCode=" + hotelCode + ", status=" + status + ", extId=" + extId + ", interfaceId=" + interfaceId + ", chainCode=" + chainCode + ", module=" + module + ", createStart=" + createStart + ", createEnd=" + createEnd + "]";
	}

}
