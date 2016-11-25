package com.ccm.api.service.roomType;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.RoomTypeI18n;
import com.ccm.api.service.base.BaseManagerTestCase;

public class RoomTypeServiceTest extends BaseManagerTestCase{

	@Autowired
	private RoomTypeDao roomTypeDao;
	
	@Test
	@Rollback(false)
	public void saveRoomType(){
		RoomType roomType = new RoomType();
		String hotelId="af1567e1248a49cc8f87500dfa249a15";
		String roomTypeCode = "xxxkkk";
		roomType.setHotelId(hotelId);
		roomType.setRoomTypeCode(roomTypeCode);
		roomTypeDao.addRoomType(roomType);
		
		RoomTypeI18n roomTypeI18n = new RoomTypeI18n();
		roomTypeI18n.setRoomTypeId(roomType.getRoomTypeId());
		roomTypeI18n.setLanguage(LanguageCode.ZH_CN);
		roomTypeI18n.setRoomTypeName(roomType.getRoomTypeCode());
		roomTypeDao.addRoomTypeI18n(roomTypeI18n);
	}
}
