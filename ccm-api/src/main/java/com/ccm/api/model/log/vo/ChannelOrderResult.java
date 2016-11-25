/**
 * 
 */
package com.ccm.api.model.log.vo;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchResult;
import com.ccm.api.model.log.ChannelOrder;

/**
 * 查询返回结果类
 */
public class ChannelOrderResult extends SearchResult<ChannelOrder> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5130243437142047243L;
	
	private List<ChannelOrder> channelOrderList = new ArrayList<ChannelOrder>();

	public List<ChannelOrder> getChannelOrderList() {
		return channelOrderList;
	}

	public void setChannelOrderList(List<ChannelOrder> channelOrderList) {
		this.channelOrderList = channelOrderList;
	}

}