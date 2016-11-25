/**
 * 
 */
package com.ccm.api.service.hotel;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomReportRecord;
import com.ccm.api.model.hotel.vo.CustomResult;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.vo.DepositSearchResult;
import com.ccm.api.model.order.vo.SearchDepositCriteria;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface CustomManager extends GenericManager<Custom, String> {

	void saveCustom(Custom c);

	/**
	 * 逻辑删除
	 * 
	 * @param customId
	 */
	void updateDelFlag(String customId);

	/**
	 * 根据逻辑判断是否重复记录
	 * 
	 * @param c
	 * @return
	 */
	boolean existCustom(Custom c);

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

	List<String> getCustomIdByRatePlanId(String ratePlanId);
	
	/**
	 * 根据房价ID查找
	 * @param ratePlanId
	 * @return
	 */
	public List<Custom> getCustomByRatePlanId(String ratePlanId);
	/*
	 * 根据customId查找
	 */
	public Custom searchCustomById(String customId);
	/**
	 * 根据customId累加
	 * @param customId
	 */
	public void updateCustomCumulative(Custom c);
	
	DepositSearchResult queryBookingDepositReport(SearchDepositCriteria sdc);

	/**
	 *  订单Credit Mgmt逻辑
	 * @param master
	 * @param custom
	 * @return
	 */
	public boolean creditMgmtManage(Master master,Custom custom);
	
	/**
	 * 增减totalRoomRev
	 * @param master
	 * @param custom
	 */
	public void updateTotalRoomRev(Master master,Custom custom);
	
	/**
	 *  根据hotelId,assccessCode查找
	 * @param hotelId
	 * @param accessCode
	 * @return
	 */
	public Custom searchCustomByHotelIdAndAccessCode(String hotelId,String accessCode);

	public boolean existProfileID(Custom c);
	/**
	 * 根据profileID 和 hotelId添加profileMessage
	 * @param custom
	 */
	void updateProfileMessage(Custom custom);

	List<String> getAccessRatePlanIdByChannelIds(String hotelId,List<Channel> channelList);
}
