/**
 * 
 */
package com.ccm.api.dao.hotel;

import java.util.List;
import java.util.Map;
import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelAmenity;
import com.ccm.api.model.hotel.vo.HotelVO;

/**
 * @author Jenny
 * 
 */
public interface HotelDao extends GenericDao<Hotel, String> {

	/**
	 * 保存酒店服务设施
	 * 
	 * @param ha
	 */
	public void saveHotelAmenity(HotelAmenity ha);

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,currencyCode,
	 * hotelName,chainCode,allotNotificationEmail)
	 * 
	 * @param hotelId
	 * @return
	 */
	HotelVO getHotelI18nChainByHotelId(String hotelId);

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,currencyCode,
	 * hotelName,chainCode,allotNotificationEmail)
	 * 
	 * @param hotelId
	 * @param languageCode
	 * @return
	 */
	HotelVO getHotelI18nChainByHotelId(String hotelId, String languageCode);

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,cityName,
	 * telephone,fax,email,currencyCode
	 * ,tbShopName,postCode,city,countryCode,hotelPushUrl
	 * ,hotelName,languageCode,
	 * address,chainCode,allotNotificationEmail,privinceName)
	 * 
	 * @param hotelId
	 * @param languageCode
	 * @return
	 */
	HotelVO getPushHotelInfoByHotelId(String hotelId, String languageCode);

	List<HotelVO> getAllDirectPmsHotel(String languageCode, Boolean isDirectPms);

	List<String> getHotelCodesByHotelIds(List<String> hotelIds);

	/**
	 * 根据用户ID和语言环境获取酒店
	 * 
	 * @param userId
	 * @return
	 */
	public List<HotelVO> getHotelInfoChainByUserId(String userId);

	/**
	 * 根据用户ID和语言环境获取酒店
	 * 
	 * @param userId
	 * @param language
	 * @return
	 */
	public List<HotelVO> getHotelInfoChainByUserId(String userId, String language);

	/**
	 * 根据酒店ID获取酒店已存在的服务设施
	 * 
	 * @param hotelId
	 * @return key:codeNo,value:hotelAmenityId
	 */
	public Map<String, String> getHotelAmenity(String hotelId);

	/**
	 * 根据酒店ID获取sessionKey
	 */
	public String getSessionKeyByHotelId(String hotelId);

	/**
	 * 根据hotelCode查询酒店
	 * 
	 * @param hotelCode
	 * @return
	 */
	List<Hotel> getHotelByHotelCode(String hotelCode);

	/**
	 * 只根据hotelCode查询
	 * 
	 * @param hotelCode
	 * @return
	 */
	List<Hotel> getHotelAllByHotelCode(String hotelCode);

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
	 * 根据hotelCode,ChainId查询酒店
	 * 
	 * @param hotelCode
	 * @param chainId
	 * @return
	 */
	List<HotelVO> getHotelI18nByCode(String hotelCode, String chainId);

	/**
	 * 根据hotelCode,ChainId,语言代码查询酒店
	 * 
	 * @param hotelCode
	 * @param chainId
	 * @return
	 */
	List<HotelVO> getHotelI18nByCode(String hotelCode, String chainId, String languageCode);

	/**
	 * 删除服务设施,此处是逻辑删除，即修改删除标志
	 * 
	 * @param hotelAmenityId
	 */
	public void deleteHotelAmenity(String hotelAmenityId);

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

	public boolean cleanCCMData(String hotelId);

	/**
	 * 根据酒店ID查询酒店与集团的ID和代码及币种
	 * 
	 * @param hotelId
	 * @return
	 */
	HotelVO getHotelChainByHotelId(String hotelId);

	/**
	 * 根据hotelid查询酒店
	 * 
	 * @param hotelId
	 * @return
	 */
	public Hotel getHotel(String hotelId);

	/**
	 * 根据hotelid查询酒店
	 * 
	 * @param hotelId
	 * @param selectFields
	 * @return
	 */
	public Hotel getHotel(String hotelId, String selectFields);

	/**
	 * 根据hid查询酒店
	 * 
	 * @param hid
	 * @return
	 */
	public Hotel getHotelOfHid(String hid);

	public Hotel getHotelContainDel(String hotelId);

	/**
	 * 根据酒店ID获取酒店VO对象
	 * 
	 * @param hotelId
	 * @return
	 */
	HotelVO getHotelVoByHotelId(String hotelId);

	HotelVO getHotelVoByHotelId(String hotelId, String languageCode);

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
	
	public List<Hotel> getBdpHotel();
}
