/**
 * 
 */
package com.ccm.api.service.hotel;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface HotelManager extends GenericManager<Hotel, String> {
	
	// 获取与PMS直连的所有酒店 
	List<HotelVO> getAllDirectPmsHotel(String languageCode, Boolean isDirectPms);
	
	/**
	 * 新建或更新酒店
	 * 
	 * @param h
	 * @return
	 */
	public HotelVO saveOrUpdateHotelInfo(HotelVO h) throws Exception;

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,cityName,
	 * telephone
	 * ,email,currencyCode,tbShopName,hotelName,languageCode,address,chainCode)
	 * 
	 * @param hotelId
	 * @return
	 */
	public HotelVO getHotelI18nChainByHotelId(String hotelId);

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,cityName,
	 * telephone
	 * ,email,currencyCode,tbShopName,hotelName,languageCode,address,chainCode)
	 * 
	 * @param hotelId
	 * @param languageCode
	 * @return
	 */
	public HotelVO getHotelI18nChainByHotelId(String hotelId, String languageCode);

	/**
	 * 根据用户ID和语言环境获取酒店
	 * @param userId
	 * @param language
	 * @return
	 */
	public List<HotelVO> getHotelInfoChainByUserId(String userId);
	/**
	 * 根据用户ID和语言环境获取酒店
	 * @param userId
	 * @param language
	 * @return
	 */
	public List<HotelVO> getHotelInfoChainByUserId(String userId,String language);

	/**
	 * 根据酒店ID获取酒店已存在的服务设施
	 * 
	 * @param hotelId
	 * @return key:codeNo,value:hotelAmenityId
	 */
	public Map<String, String> getHotelAmenity(String hotelId);

	/**
	 * 从渠道淘宝上初始化酒店信息到本系统中
	 * 
	 * @param tbHotel
	 * @return
	 */
	public Map<String, String> saveHotelFromTBByHid(com.taobao.api.domain.Hotel tbHotel);

	/**
	 * 修改酒店状态
	 * 
	 * @param hotelId
	 *            酒店ID
	 * @param status
	 *            渠道酒店状态
	 */
	public void updateHotelStatus(String hotelId, String status);

	/**
	 * 设置酒店为启动或停用
	 * 
	 * @param hotelId
	 * @param delFlag
	 */
	public void updateHotelDelFlag(String hotelId, String delFlag);

	public boolean cleanCCMData(String userId);

	/**
	 * 根据酒店ID查询酒店与集团的ID和代码及币种
	 * 
	 * @param hotelId
	 * @return
	 */
	HotelVO getHotelChainByHotelId(String hotelId);

	/**
	 * 发布酒店信息到淘宝
	 * 
	 * @param hotel
	 * @return
	 */
	public Map<String, String> publishHotelToTB(HotelVO hotel, String sessionKey);

	/**
	 * 根据酒店ID获取酒店VO对象
	 * 
	 * @param hotelId
	 * @return
	 */
	HotelVO getHotelVoByHotelId(String hotelId);

	/**
	 * 根据酒店ID获取酒店VO对象
	 * 
	 * @param hotelId
	 * @param languageCode
	 * @return
	 */
	HotelVO getHotelVoByHotelId(String hotelId, String languageCode);

	/**
	 * 根据hotelCode查询酒店
	 */
	List<Hotel> getHotelByHotelCode(String hotelCode);

	/**
	 * 查询集团下酒店并按代码升序排列
	 * 
	 * @param chainId
	 * @return
	 */
	List<Hotel> getHotelByChainId(String chainId);
	
	/**
	 * 查询集团下酒店并按代码升序排列
	 * 
	 * @param chainCode
	 * @return
	 */
	List<Hotel> getHotelByChainCode(String chainCode);

	/**
	 * 根据酒店code和集团ID获取酒店信息
	 * 
	 * @param hotelCode
	 * @param chainId
	 * @return
	 */
	HotelVO getHotelI18nByCode(String hotelCode, String chainId);

	/**
	 * 根据酒店CODE和集团ID以及语言获取酒店信息
	 * 
	 * @param hotelCode
	 * @param chainId
	 * @param languageCode
	 * @return
	 */
	HotelVO getHotelI18nByCode(String hotelCode, String chainId, String languageCode);

	/**
	 * 获取默认语言下的所有酒店记录
	 * 
	 * @return
	 */
	public List<HotelVO> getAllHotels();

	/**
	 * 获取指定语言下的所有酒店记录
	 * 
	 * @param language
	 * @return
	 */
	public List<HotelVO> getAllHotels(String languageCode);
	
	/**
	 * 通过hoteid 获取hotel
	 * @param hotelId
	 * @return
	 */
	public Hotel getHotel(String hotelId);

}
