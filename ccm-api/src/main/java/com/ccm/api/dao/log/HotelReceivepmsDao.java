package com.ccm.api.dao.log;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.HotelReceivepms;

public interface HotelReceivepmsDao extends GenericDao<HotelReceivepms, String>{
	
	public void updateHotelReceivepms(HotelReceivepms hotelReceivepms);
	
	/**
	 * 批量删除
	 * @param hotelReceivepmsIds
	 */
	public void deleteHotelReceivepmsByIds(List<String> hotelReceivepmsIds);
	
	/**
	 * 得到接口从断开到已连接的酒店id
	 * @return
	 */
	public List<HotelReceivepms> getlive();
	
	/**
	 * 得到需要提醒的HotelReceivepms
	 * @return
	 */
	public List<HotelReceivepms> getIsRemindHotelReceivepms();
	
	/**
	 * 得到所有的HotelReceivepms
	 * @return
	 */
	public List<HotelReceivepms> getAllHotelReceivepms();

	public void deleteHotelReceivepmsByHotelIds(List<String> list);
	
	/**
	 * 轮询次数大于等于number的hotelid
	 * @return
	 */
	public List<String> getHotelIdS(Integer number);
	
	
}
