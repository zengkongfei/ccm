package com.ccm.api.service.guestRoom;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.pms.form.Rmsta;
import com.ccm.api.model.channel.Channelguestroom;
import com.ccm.api.model.guestroom.GuestRoom;
import com.ccm.api.model.guestroom.GuestroomI18n;
import com.ccm.api.model.guestroom.vo.GuestRoomVo;
import com.ccm.api.service.base.GenericManager;
import com.taobao.api.domain.Hotel;

public interface GuestRoomManager extends GenericManager<GuestRoom, String> {

	GuestroomI18n getGuestroomI18nByGRID(String guestRoomId);

	String getGuestRoomNameByRoomTypeCode(String roomTypeCode);

	Channelguestroom getChannelguestroomByGRID(String guestRoomId);

	Map<String, String> getGuestRoomAmenity(String guestRoomId);

	List<Map<String, String>> getChannelGoodList(String hotelId);

	void saveChannelGuestRoom(Channelguestroom cgr);

	Channelguestroom getChannelGuestRoom(String guestRoomId);

	Map<String, String> getHotelRoomTypeNameMap(Hotel h);

	void deleteGuestRoom(String hotelId, String id);

	List<Rmsta> getRmsta(String hotelId, String string);

	boolean delRmstaByRoomNoAndHotelId(String hotelId, String roomNo);

	boolean checkExistsRoomTypeName(String hotelId, String roomtypename, String guestRoomId);

	boolean saveTBRoomQuotas(String hotelId, String roomTypeCode, String room_quotas);

	List<GuestRoomVo> getHotelTBRoom(String userId,String language);

	/**
	 * 根据用户ID获取已编辑过的GuestRoom
	 * 
	 * @param userId
	 * @return
	 */
	public List<GuestRoom> getEditedGuestRoom(String userId);
}
