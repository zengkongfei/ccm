/**
 * 
 */
package com.ccm.api.service.hotel;

import java.util.List;

import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface Hoteli18nManager extends GenericManager<HotelI18n, String> {

	/**
	 * 根据酒店ID,语言查询酒店多语言表
	 * 
	 * @param hotelId
	 * @param language
	 * @return
	 */
	public HotelI18n getHoteli18nByHotelIdLanguage(String hotelId, String language);

	/**
	 * 保存或更新酒店多语言信息
	 * 
	 * @param hv
	 */
	public void saveOrUpdateHotelI18n(HotelVO hv);
	
	/**
	 * 根据酒店ID获取多语言列表
	 * @param hotelId
	 * @return
	 */
	public List<HotelI18n> getHotelI18nListByHotelId(String hotelId);
	
	/**
	 * 删除酒店下的多语言列表
	 * @param hotelId
	 */
	public void deleteHotelI18nByHotelId(String hotelId);

}
