package com.ccm.api.dao.roomType;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.roomType.RoomTypeTemplate;
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateVO;


public interface RoomTypeTemplateDao extends GenericDao<RoomTypeTemplate, String>{

	RoomTypeTemplate addRoomTypeTemplate(RoomTypeTemplate roomTypeTemp);
	
	RoomTypeTemplateI18n addRoomTypeTemplateI18n(RoomTypeTemplateI18n roomTypeTempI18n);
	
	void deleteRoomTypeTemplate(RoomTypeTemplate roomTypeTemp);
	
	RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTempId);
	
	List<RoomTypeTemplateVO> searchRoomTypeTemplate(RoomTypeTemplateCreteria creteria);

	List<RoomTypeTemplateVO> getAllRoomTypeTemplate(String language);

	void updateRoomTypeTemplate(RoomTypeTemplate roomTypeTemp);

	void updateRoomTypeTemplateI18n(RoomTypeTemplateI18n roomTypeTempI18n);

	void deleteRoomTypeTemplateI18n(RoomTypeTemplateI18n roomTypeTempI18n);

	void deleteRoomTypeTemplateI18nByTempId(String roomTypeTempId);

	RoomTypeTemplateVO getRoomTypeTemplateByCode(String roomTypeTemplateCode);

	RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTemplateId,
			String language);

	RoomTypeTemplateVO getRoomTypeTemplateByCode(String roomTypeTemplateCode,
			String language);

	Integer searchRoomTypeTemplateCount(RoomTypeTemplateCreteria creteria);

	List<RoomTypeTemplateI18n> getRoomTypeTemplateI18ns(
			String roomTypeTemplateId);

	List<RoomTypeTemplateVO> getAllRoomTypeTemplate();

	List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId);

	List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId,
			String language);
	
}
