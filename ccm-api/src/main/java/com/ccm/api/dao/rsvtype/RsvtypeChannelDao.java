package com.ccm.api.dao.rsvtype;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.RsvtypeChannelCode;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.rsvtype.vo.RsvtypeChannelCriteria;
import com.ccm.api.model.rsvtype.vo.RsvtypeChannelResult;

public interface RsvtypeChannelDao extends GenericDao<RsvtypeChannel, String> {

    RsvtypeChannel getRsvtypeChannelByRsvIdAndChannelId(String rsvId, String channelId);

    List<RsvtypeChannel> getRsvtypeChannelAilable(HashMap<String, Object> paramMap);

    List<Rsvtype> getRsvtypeByChannelIdsRoomTypeCodes(RoomStatusVO vo);

	void addBatchRsvtypeChannel(List<RsvtypeChannel> rsvcList);

	void updateBatchRsvtypeChannel(List<RsvtypeChannel> list);

	List<RsvtypeChannel> getRsvtypeChannelAvailable(String rsvtypeId,
			Set<String> channelIdSet);

	RsvtypeChannelResult searchRsvtypeChannel(RsvtypeChannelCriteria amc);

	void updateRsvtypeChannelSendStatus(RsvtypeChannel rc);

	List<RsvtypeChannelCode> getRsvtypeChannelCode();

	void refreshRsvtypeChannelCode();
	
	Integer removeRsvtypeChannel(String channelId,String rsvtypeId);
	
	void addRsvtypeChannel(RsvtypeChannel rsvtypeChannel);
	
	List<RsvtypeChannel>  getRsvChannelListByHeaderDetail(String hotelCode,String channelId,String roomType,Date inventoryDate);
	
	Integer updateAllotmentToRsvtypeChannel(RsvtypeChannel rsvtypeChannel);
	
	Integer updateRsvtypeForDeduct(Rsvtype rsvtype);
	
	Integer updateRsvtypeChannelForDeduct(Rsvtype RsvtypeChannel);
}
