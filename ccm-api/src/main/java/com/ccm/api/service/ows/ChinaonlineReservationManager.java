package com.ccm.api.service.ows;

import com.ccm.api.model.availability.AvailabilityReqVo;
import com.ccm.api.model.availability.AvailabilityResVo;
import com.ccm.api.model.ows.vo.OrderReservationRetVO;
import com.ccm.api.model.ows.vo.OrderReservationVO;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 * 提供对chinaonline订单预订相关接口
 *
 */
public interface ChinaonlineReservationManager extends GenericManager<String, String>  {
	
	  /**
	   * 创建订单 
	   * @param orderResVo
	   * @return
	   */
	   public OrderReservationRetVO createBooking(OrderReservationVO orderResVo);
	    
	   /**
	    * 修改预订,修改时候提供订单全量信息 和 生成预订时候返回的唯一确认ID
	    * @param orderResVo
	    * @return
	    */
	   public OrderReservationRetVO modifyBooking(OrderReservationVO orderResVo);
      
	   /**
	    * 取消订单
	    * @return
	    */
	   public OrderReservationRetVO cacelBooking(OrderReservationVO orderResVo);
	   
	   /***
	    * 获取可用房
	    * @param avaiVo
	    * @return
	    */
	   AvailabilityResVo generalAvailability(AvailabilityReqVo avaiVo);
	    
}