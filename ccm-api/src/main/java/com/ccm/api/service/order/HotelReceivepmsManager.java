package com.ccm.api.service.order;

import java.util.List;

import com.ccm.api.model.log.HotelReceivepms;
import com.ccm.api.service.base.GenericManager;

public interface HotelReceivepmsManager extends GenericManager<HotelReceivepms, String>{
	/**
	 * 批量删除
	 */
	public void deleteHotelReceivepmsByIds();
	
	/**
	 * 新增或者修改
	 * @param hotelReceivepmsList
	 */
	public void addOrupdateHotelReceivepms(List<HotelReceivepms> hotelReceivepmsList);
	
	/**
	 * 得到需要提醒的HotelReceivepms
	 * @return
	 */
	public List<HotelReceivepms> getIsRemindHotelReceivepms();
	
	/**
	 * 发送提醒
	 */
	public void reminder();

	/**
	 * 1个月内，case open  ，接口断开的，发送sms
	 */
	public void smsRemind();
	
}
