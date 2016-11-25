/**
 * 
 */
package com.ccm.api.service.log.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.model.log.ChannelOrder;
import com.ccm.api.model.log.vo.ChannelOrderCriteria;
import com.ccm.api.model.log.vo.ChannelOrderResult;

import com.ccm.api.service.base.impl.GenericManagerImpl;

import com.ccm.api.service.log.ChannelOrderManager;


/**
 * @author Water
 * 
 */
@Service("channelOrderManager")
public class ChannelOrderManagerImpl extends GenericManagerImpl<ChannelOrder, String> implements ChannelOrderManager {
	@Autowired
	private MasterDao masterDao;
	
	/**
	 * 统计酒店的渠道订单
	 * @param changed
	 * @param hotelId
	 * @param languageCode
	 * @return
	 */
	@Override
	public ChannelOrderResult getChannelOrders(ChannelOrderCriteria channelOrderCriteria) {
		return masterDao.getChannelOrders(channelOrderCriteria);
	}
	
}
