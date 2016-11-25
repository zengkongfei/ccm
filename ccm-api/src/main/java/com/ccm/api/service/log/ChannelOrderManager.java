/**
 * 
 */
package com.ccm.api.service.log;

import java.util.List;

import com.ccm.api.model.log.ChannelOrder;
import com.ccm.api.model.log.vo.ChannelOrderCriteria;
import com.ccm.api.model.log.vo.ChannelOrderResult;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Water
 * 
 */
public interface ChannelOrderManager extends GenericManager<ChannelOrder, String> {

	/**
	 * 统计酒店的渠道订单
	 * @return
	 */
	public ChannelOrderResult getChannelOrders(ChannelOrderCriteria channelOrderCriteria);

}
