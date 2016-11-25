package com.ccm.api.dao.hotel;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.city.City;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.vo.HotelCreteria;
import com.ccm.api.model.hotel.vo.HotelVO;

public interface HotelMCDao extends GenericDao<Hotel, String> {

	Hotel addHotelMC(Hotel hotel);
	
	HotelI18n addHotelI18nMC(HotelI18n hotelI18n);
	
	void updateHotelMC(Hotel hotel);
	
	void updateHotelI18nMC(HotelI18n hotelI18n);
	
	void deleteHotelMC(Hotel hotel);
	
	void deleteHotelI18nMC(HotelI18n hotelI18n);
	
	HotelVO getHotelByCodeMC(HotelVO vo);
	
	HotelVO getHotelByIdMC(String hotelId);
	
	public HotelVO getHotelByIdMC(String hotelId,String languageCode);
	
	List<HotelVO> searchHotelMC(HotelCreteria creteria);
	
	Integer searchHotelCountMC(HotelCreteria creteria);
	
	List<HotelVO> getAllHotelMC();

	List<HotelVO> getAllHotelMC(String languageCode);

	HotelVO getHotelByCodeMC2(String hotelCode);

	HotelVO getHotelByCodeMC2(String hotelCode, String languageCode);
	
	Hotel getHotelByCodeMC3(String chainCode, String hotelCode);
	
	List<String> loadPrivinces();

	List<String> loadCitysByPrivince(String privinceCode);

	public List<HotelVO> loadHotelsByCity(String city,String language);

	List<Hotel> getUserHotelByChainAndUserId(Map<String, Object> params);
	
	List<Hotel> getUserHotelByChainCodeAndUserId(Map<String, Object> params);

	List<HotelVO> getHotelByChannelCode(String chainCode,String channel, String language);

	/**
	 * 获取有酒店的省
	 * @param parentId
	 * @return
	 */
	List<City> getPrivinces(String language);
	/**
	 * 获取有酒店的市
	 * @param privinceCode
	 * @return
	 */
	List<City> getCitysByPrivince(String privinceCode,String language);
	
	/**
	 * 获取酒店提醒email，efax内容
	 * @param hotelId
	 * @return
	 */
	public HotelVO gethotelRemindInfo(String hotelId);
	
	public void updateHotelPMSHeartBeat(Hotel hotel);
	
	public List<Hotel> getAllHoteleRemind();
}
