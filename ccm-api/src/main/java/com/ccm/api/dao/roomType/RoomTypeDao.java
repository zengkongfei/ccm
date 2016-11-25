package com.ccm.api.dao.roomType;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.roomType.Amenity;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.RoomTypeI18n;
import com.ccm.api.model.roomType.vo.RoomTypeCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeVO;

public interface RoomTypeDao extends GenericDao<RoomType, String> {

	RoomType addRoomType(RoomType roomType);

	RoomTypeI18n addRoomTypeI18n(RoomTypeI18n roomTypeI18n);

	void updateRoomType(RoomType roomType);

	void updateRoomTypeI18n(RoomTypeI18n roomTypeI18n);

	void deleteRoomType(RoomType roomType);

	void deleteRoomTypeI18n(RoomTypeI18n roomTypeI18n);

	RoomType getRoomTypeByObj(RoomType r);
	RoomTypeVO getRoomTypeVOByObj(RoomTypeVO r);
	

	RoomTypeVO getRoomTypeByCode(RoomTypeVO vo);

	RoomTypeVO getRoomTypeByHotelCode(String roomTypeCode, String hotelCode, String language);

	/**
	 * 根据酒店code和房型code获取roomTypeId,physicalRooms
	 * @param roomTypeCode
	 * @param hotelCode
	 * @return
	 */
	RoomType getRoomTypeByHotelCode(String roomTypeCode, String hotelCode);

	RoomTypeVO getRoomTypeById(String roomTypeId);

	RoomTypeVO getRoomTypeById(String roomTypeId, String language);

	List<RoomTypeVO> searchRoomType(RoomTypeCreteria creteria);

	Integer searchRoomTypeCount(RoomTypeCreteria creteria);

	List<RoomType> getRoomTypeByHotelId(String hotelId);

	List<RoomType> getRoomTypeByHotelIdList(List<String> hotelIdList);
	
	List<RoomTypeVO> getRoomTypeByCodes(List<String> asList, String hotelId, String language);

	/**
	 * 根据查询条件获取可用酒店下可用的房型
	 * 
	 * @param userId
	 * @return
	 */
	List<RoomTypeVO> getRoomTypeByCreteria(RoomTypeCreteria roomTypeCreteria);

	/**
	 * 根据房型ID取房型信息,不判断delFlag
	 * 
	 * @param roomTypeId
	 * @return
	 */
	RoomTypeVO getRoomTypeByRoomTypeId(String roomTypeId);

	List<String> checkRoomTypeName(String roomTypeName, String hotelId);

	/**
	 * 根据房型ID查询服务
	 * 
	 * @param roomTypeId
	 * @return
	 */
	Map<Integer, String> getAmenityByRoomTypeId(String roomTypeId);

	void saveAmenity(Amenity a);

	void deleteAmenity(Amenity a);

	void deleteRoomTypeI18nByRoomTypeId(String roomTypeId);

	List<RoomTypeI18n> getRoomTypeI18ns(String roomTypeId);

	List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId, String language);
	List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId);

	List<RoomTypeVO> getRoomTypeVOByRatePlanIdLang(String ratePlanId, String language);

	List<String> getRoomTypeCodesByIds(List<String> roomTypeIds);

	List<RoomType> getHotelRoomTypesByHotelId(String hotelId);
	
	List<RoomTypeVO> getRoomTypeInfosByIds(List<String> roomTypeIds);
	
	List<RoomTypeVO> getRoomTypeInfosByIds(List<String> roomTypeIds, String language);
	
}
