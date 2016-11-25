package com.ccm.api.dao.order;

import java.util.Date;
import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.channel.Channelguestroom;
import com.ccm.api.model.order.TaobaoOrder;
import com.ccm.api.model.order.vo.TaobaoOrderCriteria;

public interface TaobaoOrderDao extends GenericDao<TaobaoOrder, String>{
	
	/**
	 * 修改淘宝订单的处理状态
	 * @param tbOrder
	 */
	void updateTaobaoOrderSendStatus(TaobaoOrder tbOrder);
	
	/**
	 * 根据订单ID求出淘宝订单记录
	 * @param tbOrder
	 * @return
	 */
	TaobaoOrder getTaobaoOrderByTradeId(TaobaoOrder tbOrder);

	/**
	 * 求出最晚的那条订单日期，用于启动时查系统关闭时没能接收到的订单数据
	 * @return
	 */
	Date getLastDownloadTime();

	/**
	 * 查找订单记录
	 * @param order
	 * @return
	 */
	List<TaobaoOrder> searchOrder(TaobaoOrder order);
	
	/**
	 * 修改订单
	 * @param order
	 * @return
	 */
	TaobaoOrder update(TaobaoOrder order);

	/**
	 * 求出渠道酒店ID跟本地PMS酒店ID之间对应关系
	 * @param channelHotelCode
	 * @return
	 */
	List<ChannelHotel> searchPmsHotelId(String channelHotelCode);

	/**
	 * 求出渠道酒店房型ID跟本地PMS酒店房型ID之间对应关系
	 * @param channelId
	 * @param channelRoomTypeId
	 * @return
	 */
	List<Channelguestroom> searchPmsRoomTypeId(String channelId,
			String channelRoomTypeId);

    List<TaobaoOrder> searchTaobaoOrder(TaobaoOrderCriteria tbCriteria);

    Integer searchTaobaoOrderCount(TaobaoOrderCriteria tbCriteria);
}
