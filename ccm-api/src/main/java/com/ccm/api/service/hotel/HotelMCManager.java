package com.ccm.api.service.hotel;

import java.util.List;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.city.City;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.vo.HotelCreteria;
import com.ccm.api.model.hotel.vo.HotelResult;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.base.GenericManager;

public interface HotelMCManager extends GenericManager<Hotel, String> {

	/**
	 * 新增酒店
	 */
	HotelVO saveHotelMC(HotelVO vo);
	
	/**
	 * 修改酒店
	 */
	void updateHotelMC(HotelVO vo);
	
	/**
	 * 删除酒店
	 */
	void deleteHotelMC(HotelVO vo);
	
	/**
	 * 根据酒店代码取酒店信息
	 */
	HotelVO getHotelByCodeMC(HotelVO vo);
	
	/**
	 * 根据酒店ID取酒店信息(默认中文)
	 */
	HotelVO getHotelByIdMC(String hotelId);
	
	/**
	 * 根据酒店ID和languageCode取酒店信息
	 */
	HotelVO getHotelByIdMC(String hotelId,String languageCode);
	
	
	Hotel getHotelByCodeMC3(String chainCode, String hotelCode);
	
	/**
	 * 根据条件取酒店信息
	 */
	HotelResult searchHotelMC(HotelCreteria creteria);
	
	/**
	 * 查询所有酒店
	 */
	List<HotelVO> getAllHotelMC();

	HotelI18n getDefaultLanguageI18n(HotelVO hotelVo);
	
	/**
	 * 查询所有酒店
	 */
	List<HotelVO> getAllHotelMC(String languageCode);
	
	/**
	 * 加载酒店初始化数据
	 * @param chainCode
	 * @param hotelCode
	 * @param languageCode
	 * @return
	 */
	HotelVO initHotelVo(String chainCode,String hotelCode,String languageCode) throws BizException;
	
	List<String> loadPrivinces();

	List<String> loadCitysByPrivince(String privinceCode);
	
	List<HotelVO> loadHotelsByCity(String city,String language);
	
	List<Hotel> getUserHotelByChainAndUserId(String chainId,String userId);
	
	List<Hotel> getUserHotelByChainCodeAndUserId(String chainCode, String userId);
	
	List<Hotel> getUserHotelByChainAndUserId(String userId);

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
	
}
