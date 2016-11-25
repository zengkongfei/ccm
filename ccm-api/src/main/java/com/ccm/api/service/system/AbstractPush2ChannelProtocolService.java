/**
 * 
 */
package com.ccm.api.service.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
public abstract class AbstractPush2ChannelProtocolService {

	public abstract Object buildRateOnOff(String chainCode, String hotelCode, String ratePlanCode, String status);

	public abstract Object buildHotelMsg(HotelVO hvo) throws Exception;

	public abstract Object buildRoomMsg(String chainCode, RoomTypeVO rtvo, String channelId);

	public abstract Object buildRateMsg(RatePlanVO rpvo, String status, String language, String channelId) throws Exception;
	
	public abstract String buildHotelProductMsg(Map<String,Object> map);

	public int[] getWeek(RateCancelRelationship rateDetailVO) {
		List<Integer> list = new ArrayList<Integer>();
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToSunday())) {
			list.add(0);
		}
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToMonday())) {
			list.add(1);
		}
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToTuesday())) {
			list.add(2);
		}
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToWednesday())) {
			list.add(3);
		}
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToThursday())) {
			list.add(4);
		}
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToFriday())) {
			list.add(5);
		}
		if (BooleanUtils.isTrue(rateDetailVO.getIsApplyToSaturday())) {
			list.add(6);
		}
		Iterator<Integer> iterator = list.iterator();
		int[] ret = new int[list.size()];
		int i = 0;
		while (iterator.hasNext()) {
			ret[i++] = iterator.next();
		}
		return ret;
	}

	public Map<String, String> getDateBlock(List<String> daysList) {
		if (daysList == null) {
			return null;
		}
		List<String> dateBlock = new ArrayList<String>();
		for (int i = 0; i < daysList.size(); i++) {
			String cDay = daysList.get(i);
			if (i == 0) {
				if (1 == daysList.size()) {
					dateBlock.add(cDay);
					dateBlock.add(cDay);
				} else {
					String nDay = daysList.get(i + 1);
					long dayNum = DateUtil.dateDiff(cDay, nDay);
					if (dayNum == 1) {
						dateBlock.add(cDay);
					} else if (dayNum > 1) {
						dateBlock.add(cDay);
						dateBlock.add(cDay);
					}
				}
			}
			if (i > 0) {
				String pDay = daysList.get(i - 1);
				long dayNum = DateUtil.dateDiff(pDay, cDay);
				if (dayNum > 1) {
					if (i != 1) {
						dateBlock.add(pDay);
					}
					if (i != daysList.size() - 1) {
						dateBlock.add(cDay);
					}
				}
				if (i == daysList.size() - 1) {
					if (dayNum > 1) {
						dateBlock.add(cDay);
						dateBlock.add(cDay);
					} else if (dayNum == 1) {
						dateBlock.add(cDay);
					}
				}
			}
		}
		LinkedHashMap<String, String> dateBlockMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < dateBlock.size(); i += 2) {
			String start = dateBlock.get(i);
			String end = dateBlock.get(i + 1);
			dateBlockMap.put(start, end);
		}
		return dateBlockMap;
	}

}
