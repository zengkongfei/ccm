/**
 * 
 */
package com.ccm.api.model.log.vo;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchResult;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.SendMsgLog;

/**
 * order 查询返回结果类
 */
public class UpDownPmsLogResult extends SearchResult<SendMsgLog> {
	private static final long serialVersionUID = -1977615185344318930L;

	private List<ReceiveMsgLog> upMsgLogList = new ArrayList<ReceiveMsgLog>();

	public List<ReceiveMsgLog> getUpMsgLogList() {
		return upMsgLogList;
	}

	public void setUpMsgLogList(List<ReceiveMsgLog> upMsgLogList) {
		this.upMsgLogList = upMsgLogList;
	}

}
