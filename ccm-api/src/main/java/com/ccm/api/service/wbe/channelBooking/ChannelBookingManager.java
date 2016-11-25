package com.ccm.api.service.wbe.channelBooking;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ccm.api.model.wbe.WbeCalendarRow;
import com.ccm.api.model.wbe.WbeOrderVO;
import com.ccm.api.model.wbe.WbeSearchCreteria;

public interface ChannelBookingManager {
	public Map<String, List<WbeCalendarRow>> getBookingCanlendar(
			WbeSearchCreteria creteria)throws ParseException;// key:roomTypeCode;value:包含1个allot和1个freesell的rows
	
	public WbeOrderVO getWbeOrderVO(String hotelId,String channelId,String name1,String masterId,String crsno);
}
