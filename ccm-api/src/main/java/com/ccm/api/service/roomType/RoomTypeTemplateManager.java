package com.ccm.api.service.roomType;

import java.util.List;

import com.ccm.api.model.roomType.RoomTypeTemplate;
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateResult;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateVO;
import com.ccm.api.service.base.GenericManager;

public interface RoomTypeTemplateManager extends GenericManager<RoomTypeTemplate, String> {

	RoomTypeTemplateVO saveRoomTypeTemplate(RoomTypeTemplateVO vo);

	void updateRoomTypeTempldate(RoomTypeTemplateVO vo);

	void deleteRoomTypeTempldate(RoomTypeTemplateVO vo);
	
	RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTempId);
	
	RoomTypeTemplateVO getRoomTypeTemplateByCode(String roomTypeTemplateCode);

	RoomTypeTemplateVO getRoomTypeTemplateById(String roomTypeTemplateId,
			String language);

	RoomTypeTemplateVO getRoomTypeTemplateByCode(String roomTypeTemplateCode,
			String language);

	List<RoomTypeTemplateI18n> getRoomTypeTemplateI18ns(
			String roomTypeTemplateId);

	List<RoomTypeTemplateVO> getAllRoomTypeTemplate(String language);
	
	List<RoomTypeTemplateVO> getAllRoomTypeTemplate();

	RoomTypeTemplateResult searchRoomTypeTemplate(
			RoomTypeTemplateCreteria creteria);

	RoomTypeTemplateI18n getDefaultLanguageI18n(RoomTypeTemplateVO vo);
	
	List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId);

	List<RoomTypeTemplateVO> getDontUseRoomTypeTemplate(String hotelId,
			String language);

}
