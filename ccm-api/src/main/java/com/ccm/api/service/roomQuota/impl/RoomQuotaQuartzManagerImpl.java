package com.ccm.api.service.roomQuota.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.model.roomQuota.RoomQuota;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.roomQuota.RoomQuotaManager;
import com.ccm.api.service.roomQuota.RoomQuotaQuartzManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomManager;

@Service("roomQuotaQuartzManager")
public class RoomQuotaQuartzManagerImpl extends GenericManagerImpl<RoomQuota, String> implements RoomQuotaQuartzManager {

	private Log log = LogFactory.getLog(RoomQuotaQuartzManagerImpl.class);
	@Autowired
	private RsvtypeDao rsvtypeDao;
	@Autowired
	private TaobaoRoomManager taobaoRoomManager;
	@Autowired
	private RoomQuotaManager roomQuotaManager;
	
	@Override
	public void roomQuotaQuartz() {
		log.info("批量发布房态开始...");
		List<Rsvtype> rList = rsvtypeDao.getDate90RsvtypeList(null,null,"1");
		if(!rList.isEmpty()){
			List<List<Rsvtype>> rListList = getSubListList(rList);
			for(List<Rsvtype> list:rListList){
				String hotelId = list.get(0).getHotelid();
				String roomTypeCode = list.get(0).getType();
				taobaoRoomManager.releaseRoomQuota(hotelId,roomTypeCode,"1");
			}
		}
		log.info("批量发布房态结束！");
	}
	
	/**
	 * 按照酒店ID和房型代码拆分房价日历表
	 * @param rList
	 */
	@Override
	public List<List<Rsvtype>> getSubListList(List<Rsvtype> rList){
		List<List<Rsvtype>> list = new ArrayList<List<Rsvtype>>();
		List<Rsvtype> subList = new ArrayList<Rsvtype>();
		int roomQuoAmount = 0;
		Rsvtype rsvtype= rList.get(0);
		for(Rsvtype r:rList){
			//设置房量
			roomQuoAmount = roomQuotaManager.getRoomQuoAmount(r);
			r.setAvailable(roomQuoAmount);
			
			if(!(r.getHotelid()+r.getType()).equals(rsvtype.getHotelid()+rsvtype.getType())){
				list.add(subList);
				subList = new ArrayList<Rsvtype>();
				rsvtype = r;
			}
			subList.add(r);
		}
		list.add(subList);//添加最后一个List
		return list;
	}
}
