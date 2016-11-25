package com.ccm.api.dao.order.ibatis;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.order.TaobaoOrderDao;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.channel.Channelguestroom;
import com.ccm.api.model.order.TaobaoOrder;
import com.ccm.api.model.order.vo.TaobaoOrderCriteria;

@Repository("taobaoOrderDao")
public class TaobaoOrderDaoiBatis extends GenericDaoiBatis<TaobaoOrder, String> implements TaobaoOrderDao {

	public TaobaoOrderDaoiBatis() {
		super(TaobaoOrder.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TaobaoOrder getTaobaoOrderByTradeId(TaobaoOrder tbOrder) {
		
		List<TaobaoOrder> list = getSqlMapClientTemplate().queryForList("getTaobaoOrderByTradeId", tbOrder);

		if( list != null && list.size()>0 ){
			
			return list.get(0);
			
		}
		
		return null;
	}

	@Override
	public void updateTaobaoOrderSendStatus(TaobaoOrder tbOrder) {

		getSqlMapClientTemplate().update("updateTaobaoOrderSendStatus", tbOrder);
		
	}
	
	@Override
	public Date getLastDownloadTime(){
		
		return (Date)getSqlMapClientTemplate().queryForObject("getMaxModifiedTime");
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaobaoOrder> searchOrder(TaobaoOrder order){
		
		return getSqlMapClientTemplate().queryForList("searchOrder", order);		
		
	}

	@Override
	public TaobaoOrder update(TaobaoOrder order) {
		
		getSqlMapClientTemplate().update("updateTaobaoOrder", order);
		
		return order;
	}

	@Override
	public List<ChannelHotel> searchPmsHotelId(String channelHotelCode){
		ChannelHotel ch = new ChannelHotel();
		ch.setChannelHotelCode(channelHotelCode);
		return getSqlMapClientTemplate().queryForList("searchChannelHid", ch);	
	}
	
	@Override
	public List<Channelguestroom> searchPmsRoomTypeId(String channelId, String channelRoomTypeId){
		Channelguestroom guestroom = new Channelguestroom();
		guestroom.setChannelid(channelId);
		guestroom.setChannelroomtypecode(channelRoomTypeId);
		return getSqlMapClientTemplate().queryForList("searchChannelRoomid", guestroom);	
	}

    @Override
    public List<TaobaoOrder> searchTaobaoOrder(TaobaoOrderCriteria tbCriteria) {
        return getSqlMapClientTemplate().queryForList("searchOrder", tbCriteria);
    }

    @Override
    public Integer searchTaobaoOrderCount(TaobaoOrderCriteria tbCriteria) {
        return (Integer) getSqlMapClientTemplate().queryForObject("searchOrderCount",tbCriteria);
    }
}
