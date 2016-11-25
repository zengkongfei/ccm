package com.ccm.api.service.rmsta.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.guestRoom.GuestRoomDao;
import com.ccm.api.dao.pms.form.Rmsta;
import com.ccm.api.dao.rmsta.RmstaDao;
import com.ccm.api.model.guestroom.GuestRoom;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.rmsta.RmstaManager;
import com.ccm.api.service.system.PushManage;
import com.ccm.api.service.taobaoAPI.TaobaoRoomManager;

@Service("rmstaManager")
public class RmstaManagerImpl extends GenericManagerImpl<Object, String> implements RmstaManager {

	@Autowired
	private RmstaDao rmstaDao;
	@Autowired
	private GuestRoomDao guestRoomDao;
	@Autowired
	private TaobaoRoomManager taobaoRoomManager;
//	@Resource
//	private JmsManager jmsManager;
	
	@Resource
    private PushManage pushManage;
	@Override
	public String queryRoomNo(String hotelId, String roomTypeCode,String gid) {
		if(roomTypeCode.equals("")){
			GuestRoom guestRoom = guestRoomDao.getGuestRoomByGid(gid);
			if(null!=guestRoom){
				roomTypeCode = guestRoom.getRoomTypeCode();
			}
		}
		String roomNos = "";
		List<Rmsta> rmstas = rmstaDao.getRmsta(hotelId, roomTypeCode);
		if(!rmstas.isEmpty()){
			for(Rmsta rmsta:rmstas){
				roomNos += rmsta.getRoomno()+",";
			}
			roomNos = roomNos.substring(0,roomNos.lastIndexOf(","));
		}
		return roomNos;
	}

	@Override
	public String deleteRmstaRoomNo(String hotelId,String roomNo, String roomTypeCode,String gid) {
		String message = "";
		if(roomTypeCode.equals("")){
			GuestRoom guestRoom = guestRoomDao.getGuestRoomByGid(gid);
			if(null!=guestRoom){
				roomTypeCode = guestRoom.getRoomTypeCode();
			}
		}
		int count = rmstaDao.getValidRoomno(roomNo, roomTypeCode);
		if(count>0){
			message = "房号已经被占用，请确认订单已处理完成后再做操作！";
		}else{
			message = "true";
		}
		return message;
	}
	@Override
	public void saveRmstaRoomNo(String hotelId, String roomNos,String roomTypeCode, String gid) {
		if(roomTypeCode.equals("")){
			GuestRoom guestRoom = guestRoomDao.getGuestRoomByGid(gid);
			if(null!=guestRoom){
				roomTypeCode = guestRoom.getRoomTypeCode();
			}
		}
		List<String> roomNo = Arrays.asList(roomNos.split(","));
	
		rmstaDao.delRmstaByHotelIdAndRoomTypeCode(hotelId,roomTypeCode);
		if(StringUtils.hasText(roomNos)){
		    rmstaDao.saveRmsta(hotelId, roomNo, roomTypeCode);
		}
		
		if(StringUtils.hasText(gid)){
			taobaoRoomManager.releaseRoomQuota(hotelId, roomTypeCode,"1");
		}
		
		try {
		    //发送房量消息  
//		    jmsManager.sendAvailabilityMsg(hotelId, roomTypeCode);
		    
//		    pushManage.pushAvailability(hotelId, roomTypeCode);
//		    log.info("send Availability msg!!!!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
