/**
 * 
 */
package com.ccm.api.dao.hotel;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomReportRecord;
import com.ccm.api.model.hotel.vo.CustomResult;

/**
 * @author Jenny
 * 
 */
public interface CustomDao extends GenericDao<Custom, String> {

	/**
	 * 根据条件查询记录
	 * 
	 * @param c
	 * @return
	 */
	List<Custom> getCustomByObj(Custom c);

	/**
	 * 根据酒店ID查询记录
	 * 
	 * @param hotelId
	 * @return
	 */
	List<Custom> getCustomByHotelId(String hotelId);

	/**
	 * 查询列表并可分页显示
	 * 
	 * @param creteria
	 * @return
	 */
	CustomResult searchCustom(CustomCreteria creteria);

	/*
	 * 根据customId查找
	 */
	Custom searchCustomById(String customId);
	/**
	 * 根据customId累加
	 * @param customId
	 */
	void updateCustomCumulative(Custom custom);
	
	List<CustomReportRecord> queryBookingDepositReport(Map<String,Object>map);
	
	Integer getBookingDepositReportCount(Map<String,Object>map);
	
	
	/**
	 * 增减totalRoomRev,只更新totalRoomRev
	 * @param master
	 * @param custom
	 */
	public void updateTotalRoomRev(Custom custom);

	/**
	 *  根据hotelId,assccessCode查找
	 * @param hotelId
	 * @param accessCode
	 * @return
	 */
	Custom searchCustomByHotelIdAndAccessCode(String hotelId,String accessCode);
	
	List<Custom> searchCustomByHotelIdAndChannelId(String hotelId,String channelId);
	/**
	 * 根据profileID 和 hotelId添加profileMessage
	 * @param custom
	 */
	void updateProfileMessage(Custom custom);
}
