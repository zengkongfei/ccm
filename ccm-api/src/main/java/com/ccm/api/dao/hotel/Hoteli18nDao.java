/**
 * 
 */
package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.HotelI18n;

/**
 * @author Jenny
 * 
 */
public interface Hoteli18nDao extends GenericDao<HotelI18n, String> {

	/**
	 * 根据酒店ID,语言（语言不为空时）查询酒店多语言表
	 * 
	 * @param hi
	 * @return
	 */
	public List<HotelI18n> getHoteli18nByObj(HotelI18n hi);
	
	/**
	 * 删除酒店下的多语言记录
	 * @param hotelId
	 */
	public void deleteHotelI18nByHotelId(String hotelId);

}
