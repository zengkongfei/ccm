package com.ccm.api.service.roomType;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.RoomTypeI18n;
import com.ccm.api.model.roomType.vo.RoomTypeCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeResult;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.base.GenericManager;

public interface RoomTypeManager extends GenericManager<RoomType, String> {

	/**
	 * 新增房型
	 */
	RoomTypeVO saveRoomType(RoomTypeVO vo);

	/**
	 * 修改房型
	 */
	void updateRoomType(RoomTypeVO vo);

	void saveOrUpdateRoomTypeI18n(RoomTypeVO roomTypeVO);

	/**
	 * 删除房型
	 */
	void deleteRoomType(RoomTypeVO vo);

	/**
	 * 根据房型代码取房型信息
	 */
	RoomTypeVO getRoomTypeByCode(RoomTypeVO vo);

	/**
	 * 根据酒店code和房型code获取roomTypeId,physicalRooms
	 * 
	 * @param roomTypeCode
	 * @param hotelCode
	 * @return
	 */
	RoomType getRoomTypeByHotelCode(String roomTypeCode, String hotelCode);

	RoomTypeVO getRoomTypeByHotelCode(String roomTypeCode, String hotelCode,String language);

	/**
	 * 根据房型ID取房型信息
	 */
	RoomTypeVO getRoomTypeById(String roomTypeId);

	/**
	 * 根据条件取房型信息
	 */
	RoomTypeResult searchRoomType(RoomTypeCreteria creteria);

	/**
	 * 查询所有房型，根据酒店ID
	 * @param language 
	 */
	List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId);
	/**
	 * 查询所有房型，根据酒店ID
	 * @param language 
	 */
	List<RoomTypeVO> getAllRoomTypeByHotelId(String hotelId, String language);

	List<RoomTypeVO> getRoomTypeByCodes(List<String> asList, String hotelId, String language);

	/**
	 * 根据查询条件获取可用酒店下可用的房型
	 * 
	 * @param roomTypeCreteria
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

	/**
	 * 查询房型已有服务
	 * 
	 * @param roomTypeId
	 * @return
	 */
	Map<Integer, String> getAmenityByRoomTypeId(String roomTypeId);

	boolean checkRoomTypeName(String roomTypeName, String hotelId, String roomTypeId);

	List<RoomTypeI18n> getRoomTypeI18ns(String roomTypeId);

	RoomTypeVO getRoomTypeById(String roomTypeId, String language);

	RoomTypeI18n getDefaultLanguageI18n(RoomTypeVO roomTypeVo);

	/***
	 * 获取房型物理房量
	 * @param roomTypeCode
	 * @param hotelCode
	 * @return
	 */
	int getRoomTypePhysicalRoom(String roomTypeCode, String hotelCode);
	
	List<RoomType> getHotelRoomTypesByHotelId(String hotelId);
	
	List<RoomType> getRoomTypeByHotelId(String hotelId);
	
	List<RoomType> getRoomTypeByHotelIdList(List<String> hotelIdList);
}
