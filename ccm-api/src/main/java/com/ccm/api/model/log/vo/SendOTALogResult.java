/**
 * 
 */
package com.ccm.api.model.log.vo;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchResult;
import com.ccm.api.model.log.SendOTALog;


/**
 * 查询返回结果类
 */
public class SendOTALogResult extends SearchResult<SendOTALog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2539793858013145299L;
	
	private List<SendOTALog> sendOTALogList = new ArrayList<SendOTALog>();

	public List<SendOTALog> getSendOTALogList() {
		return sendOTALogList;
	}

	public void setSendOTALogList(List<SendOTALog> sendOTALogList) {
		this.sendOTALogList = sendOTALogList;
	}
}
