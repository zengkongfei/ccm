package com.ccm.api.service.wbe.channelBooking;

import com.ccm.api.model.ows.vo.OrderReservationRetVO;
import com.ccm.api.model.wbe.WbeOrderVO;
import com.ccm.api.service.base.GenericManager;

public interface ChannelBookIngReservationManager extends GenericManager<String, String>{
	/**
	   * 创建订单 
	   * @param orderResVo
	   * @return
	   */
	   public OrderReservationRetVO createBooking(WbeOrderVO wbeOrderVO) throws Exception;
	    
    
	   /**
	    * 取消订单
	    * @return
	    */
	   public OrderReservationRetVO cacelBooking(WbeOrderVO wbeOrderVO) throws Exception;
}
