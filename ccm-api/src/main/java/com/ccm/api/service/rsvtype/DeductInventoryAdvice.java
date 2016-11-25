package com.ccm.api.service.rsvtype;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.dao.DuplicateKeyException;

import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.model.rsvtype.RsvChangeInfo;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.vo.RoomStatusSetVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class DeductInventoryAdvice implements MethodBeforeAdvice{

	@Resource
	private RsvtypeDao rsvtypeDao;
	@Resource
	private RsvtypeChannelDao rsvtypeChannelDao;
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Auto-generated method stub
        //访问目标方法的参数：
        if (args != null && args.length > 0 && args[0].getClass() == RsvChangeInfo.class) {
        	RsvChangeInfo rsvChangeInfo=(RsvChangeInfo)args[0];
        	try{
        		createNewRsvtypeChannel(rsvChangeInfo);
        	}catch(Exception ex){
        		if(ex.getMessage().contains("MySQLIntegrityConstraintViolationException")||ex.getMessage().contains("DuplicateKeyException")){
        			
        		}else{
        			throw ex;
        		}
        	}
        }
	}

	private boolean createNewRsvtypeChannel(RsvChangeInfo rsvChangeInfo) throws Exception{
		String hotelCode=rsvChangeInfo.getHotelCode();
		String roomTypeCode=rsvChangeInfo.getNewRoomTypeCode();
		Date checkinDate=rsvChangeInfo.getNewCheckInDate();
		Date checkoutDate=rsvChangeInfo.getNewCheckOutDate();
		String channelId=rsvChangeInfo.getChannelId();
		RoomStatusSetVO roomStatusSet=new RoomStatusSetVO();
		roomStatusSet.setFromDate(checkinDate);
		roomStatusSet.setToDate(checkoutDate);
		roomStatusSet.setChannelId(channelId);
		roomStatusSet.setAllotment(0);
		roomStatusSet.setHotelCode(hotelCode);
		roomStatusSet.setRoonTypeCodes(roomTypeCode);
		roomStatusSet.setOverBooking(0);
		saveBatchRsvtypeChannel(roomStatusSet);
		return true;
	}
	
	private List<RsvtypeChannel>  saveBatchRsvtypeChannel(RoomStatusSetVO setvo) throws Exception {
		String roonTypeCode = setvo.getRoonTypeCodes();
		String channelId = setvo.getChannelId();
		List<Rsvtype> addRsvtypeList = new ArrayList<Rsvtype>();
		List<RsvtypeChannel> addRsvtypeChannelList = new ArrayList<RsvtypeChannel>();
		int dateDiff = DateUtil.dateDiff(setvo.getFromDate(), setvo.getToDate());
			for (int i = 0; i < dateDiff; i++) {
				Date nowDate = DateUtil.addDays(setvo.getFromDate(), i);
				RoomStatusVO r = new RoomStatusVO();
				r.setHotelCode(setvo.getHotelCode());
				r.setType(roonTypeCode);
				r.setDate(nowDate);
				r.setFreeSell(setvo.getFreeSell());
				r.setAllotment(setvo.getAllotment());

				Rsvtype rsvtype = rsvtypeDao.getRsvtype(r);
				boolean flag = true;  //标识存在该房量记录
				//如果没有找到rsvtype对象
				if(rsvtype == null){
					rsvtype = new Rsvtype();
					rsvtype.setRsvtypeId(CommonUtil.generatePrimaryKey());
					rsvtype.setHotelCode(setvo.getHotelCode());
					rsvtype.setType(roonTypeCode);
					rsvtype.setDate(r.getDate());
					addRsvtypeList.add(rsvtype);
					
					flag = false; //标识未找到房量记录
				}
				
					RsvtypeChannel rsvtypeChannel = null;
					//如果存在该房量记录,需要查询房量渠道是否存在
					if(flag){
						rsvtypeChannel = rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(
								rsvtype.getRsvtypeId(), channelId);
					}
					
					//如果房量记录不存在或者未查到房量渠道记录
					if(!flag || rsvtypeChannel == null){
						rsvtypeChannel = new RsvtypeChannel();
						rsvtypeChannel.setRsvtypeChannelId(CommonUtil.generatePrimaryKey());
						rsvtypeChannel.setChannelId(channelId);
						rsvtypeChannel.setRsvtypeId(rsvtype.getRsvtypeId());
						rsvtypeChannel.setOverBooking(rsvtype.getOverBooking());
		        		rsvtypeChannel.setHotelCode(r.getHotelCode());
		        		rsvtypeChannel.setType(rsvtype.getType());
		        		rsvtypeChannel.setAvailable(rsvtype.getAvailable());
		        		rsvtypeChannel.setTotalOBSold(rsvtype.getTotalOBSold());
		        		rsvtypeChannel.setDate(rsvtype.getDate());
		        		rsvtypeChannel.setHasBlock(0);
						addRsvtypeChannelList.add(rsvtypeChannel);
					}
					
					//设置保留房和开关
					if (r.getAllotment() != null) {
						rsvtypeChannel.setAllotment(r.getAllotment());
					}
					if (r.getFreeSell() != null) {
						rsvtypeChannel.setFreeSell(r.getFreeSell());
					}
			}
			
			long t1=System.currentTimeMillis();
        	rsvtypeDao.addBatchRsvtypes(addRsvtypeList);
        	rsvtypeChannelDao.addBatchRsvtypeChannel(addRsvtypeChannelList);
        return addRsvtypeChannelList;
	}
}
