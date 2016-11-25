package com.ccm.api.service.rsvtype.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.dao.rsvtype.RoomQtyDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.dao.sell.OverbookingGroupDao;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.rsvtype.RsvChangeInfo;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.vo.RedisRsvtype;
import com.ccm.api.model.rsvtype.vo.RoomStatusJsonVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusSetVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.sell.OverbookingGroup;
import com.ccm.api.model.wbe.WbeCalendarRow;
import com.ccm.api.service.allotment.SendBlockManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.AsyncSendManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("rsvtypeChannelManager")
public class RsvtypeChannelManagerImpl extends GenericManagerImpl<RsvtypeChannel, String> implements RsvtypeChannelManager {

	private Log log = LogFactory.getLog("availabilityImpl");

	@Resource
	private RoomQtyDao roomQtyDao;
	@Resource
	private RsvtypeChannelDao rsvtypeChannelDao;
	@Resource
	private RsvchanBlockDao rsvchanBlockDao;
	@Resource
	private RsvtypeDao rsvtypeDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private OverbookingGroupDao overbookingGroupDao;
	@Resource
	private RoomJmsManager roomJmsManager;
	@Resource
	private AsyncSendManager asyncSendManager;
	@Resource
	private PriceCalcManager priceCalcManager;
	@Resource
	private RatePlanManager ratePlanManager;
	@Resource
	private RoomTypeManager roomTypeManager;	
	@Resource
	private ChainDao chainDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private SendBlockManager sendBlockManager;
	@Resource
    private RestrictionCalcDao restrictionCalcDao;
	@Resource
	public void setRsvtypeChannelDao(RsvtypeChannelDao rsvtypeChannelDao) {
		this.dao = rsvtypeChannelDao;
	}

	@Override
	public RsvtypeChannel setAllotmentAndFreeSell(RoomStatusVO vo, Boolean isPush) {
		List<RsvchanBlock> rsvchanBlockList=new ArrayList<RsvchanBlock>();
		List<RsvchanBlock> rsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		List<RsvchanBlock> removedRsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		Rsvtype rsvtype = rsvtypeDao.getRsvtype(vo);
		RsvtypeChannel rsvtypeChannel = null;
		String blockCode=vo.getBlockCode();
		String channelId = vo.getChannelId();
		boolean isBlockWithoutRate =false;
		if (CommonUtil.isEmpty(channelId)) {
			String channelCode = vo.getChannelCode();
			Channel cl = channelDao.getChannelByChannelCode(channelCode);
			if (cl != null) {
				channelId = cl.getChannelId();
			}
			if (CommonUtil.isEmpty(channelId)) {
				return null;
			}
		}
		try {
			if (null != rsvtype) {
				rsvtypeChannel = rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(rsvtype.getRsvtypeId(), channelId);
				vo.setRsvtypeId(rsvtype.getRsvtypeId());
			} else {
				vo.setPhysicalRooms(0);
				vo.setRoomTypeOverbook(0);
				vo.setOutoforder(0);
				vo.setAdultsInHouse(0);
				vo.setChildrenInHouse(0);
				vo.setArrivalRooms(0);
				vo.setDepartureRooms(0);
				vo.setBlockCount(0);
				vo.setResvCount(0);
				vo.setHouseOverbook(0);
				vo.setRsvtypeId(UUID.randomUUID().toString().replace("-", ""));
				rsvtypeDao.save(vo);
				rsvtype = rsvtypeDao.get(vo.getRsvtypeId());
			}
			if (rsvtypeChannel == null) {
				rsvtypeChannel = new RsvtypeChannel();
				rsvtypeChannel.setChannelId(channelId);
				rsvtypeChannel.setRsvtypeId(vo.getRsvtypeId());
				rsvtypeChannel.setHasBlock(0);
			}else{
				if(blockCode==null||blockCode.trim().length()==0||blockCode.equals("--")){
					rsvtypeChannel.setHasBlock(0);
				}else{
					rsvtypeChannel.setAllotment(null);
					rsvtypeChannel.setAllotmentSold(null);
					rsvtypeChannel.setHasBlock(1);
					rsvtypeChannel.setCutOffDays(null);
					rsvtypeChannel.setRatePlanCodes(null);
					RsvchanBlock rsvchanBlock=rsvchanBlockDao.getBlockListByRsvChanIdAndBlockCode(rsvtypeChannel.getRsvtypeChannelId(),blockCode.trim());
					
					if (vo.getAllotment() != null) {
						rsvchanBlock.setBlockNum(vo.getAllotment());
					}
					if(vo.getIsModifyRates()!=null){
						if(vo.getRateCodes()!=null){
							String ratePlanCodes="";
							for(int i=0;i<vo.getRateCodes().length;i++){
								if(i==vo.getRateCodes().length-1){
									ratePlanCodes+=vo.getRateCodes()[i];
								}else{
									ratePlanCodes+=vo.getRateCodes()[i]+",";
								}
							}
							if(ratePlanCodes.trim().length()>0){
								rsvchanBlock.setRatePlanCodes(ratePlanCodes);
							}else{
								if((rsvchanBlock.getRatePlanCodes()==null||rsvchanBlock.getRatePlanCodes().trim().length()==0)==false){
									/*search block data*/
					        		List<RsvchanBlock> existedRsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
					        		if(existedRsvchanBlockList.size()>0){
					        			for(RsvchanBlock rb:existedRsvchanBlockList){
					        				rb.setBlockNum(0);
				        					rb.setBlockSold(0);
				        					if(rb.getRatePlanCodes()==null||rb.getRatePlanCodes().trim().length()==0){
				            				}else{
				            					removedRsvchanBlockWithRatesList.add(rb);
					        				}
					        				if(blockCode.equalsIgnoreCase(rb.getBlockCode())==false){
					        					rsvchanBlockDao.removeRsvchanBlockByRsvchannelIdAndBlockCode(rb.getRsvtypeChannelId(), rb.getBlockCode());
					        				}
					        			}
					        		}
								}
								rsvchanBlock.setRatePlanCodes(null);
							}
						}else{
							rsvchanBlock.setRatePlanCodes(null);
							
						}
					}
					if(vo.getCutOffDays()!=null){
						Integer cutOffDays=vo.getCutOffDays();
						rsvchanBlock.setCutOffDays(cutOffDays);
						rsvchanBlock.setCutOffDate(DateUtil.addDays(vo.getDate(), -cutOffDays));
					}
					if(vo.getIsSendToPMS()!=null){
						rsvchanBlock.setIsSendToPMS(vo.getIsSendToPMS());
					}
					if(rsvchanBlock!=null){
						rsvchanBlockDao.updateRsvchanBlock(rsvchanBlock);
						if(rsvchanBlock.getRatePlanCodes()!=null&&vo.getFreeSell()==null){
							rsvchanBlockWithRatesList.add(rsvchanBlock);
						}
						rsvchanBlockList.add(rsvchanBlock);
					}
					if(rsvchanBlock.getRatePlanCodes()==null){
						isBlockWithoutRate=true;
					}
				}
			}
			
			if (vo.getFreeSell() != null) {
				rsvtypeChannel.setFreeSell(vo.getFreeSell());
				rsvtypeChannel.setNeedSendRtav(true);
			}
			if(getInt(rsvtypeChannel.getHasBlock())==0){
				if (vo.getAllotment() != null) {
					rsvtypeChannel.setAllotment(vo.getAllotment());
				}
				if(vo.getIsModifyRates()!=null){
					if(vo.getRateCodes()!=null){
						String ratePlanCodes="";
						for(int i=0;i<vo.getRateCodes().length;i++){
							if(i==vo.getRateCodes().length-1){
								ratePlanCodes+=vo.getRateCodes()[i];
							}else{
								ratePlanCodes+=vo.getRateCodes()[i]+",";
							}
						}
						if(ratePlanCodes.trim().length()>0){
							rsvtypeChannel.setRatePlanCodes(ratePlanCodes);
						}else{
							rsvtypeChannel.setRatePlanCodes(null);
						}
					}else{
						rsvtypeChannel.setRatePlanCodes(null);
					}
				}
				if(vo.getCutOffDays()!=null){
					Integer cutOffDays=vo.getCutOffDays();
					rsvtypeChannel.setCutOffDays(cutOffDays);
					rsvtypeChannel.setCutOffDate(DateUtil.addDays(vo.getDate(), -cutOffDays));
				}
				
				/*search block data*/
        		List<RsvchanBlock> existedRsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
        		if(existedRsvchanBlockList.size()>0){
        			for(RsvchanBlock rb:existedRsvchanBlockList){
        				rb.setBlockNum(0);
    					rb.setBlockSold(0);
        				if(rb.getRatePlanCodes()==null||rb.getRatePlanCodes().trim().length()==0){
        				}else{
        					removedRsvchanBlockWithRatesList.add(rb);
        				}
        			}
        		}
				/*remove block data*/
        		rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
        		 
			}
		
			rsvtypeChannel = rsvtypeChannelDao.save(rsvtypeChannel);
			log.info("push freesell or allotment msg start " + new Date().toLocaleString());
			if (isPush) {
				final List<RsvchanBlock> finalRsvchanBlockWithRatesList=rsvchanBlockWithRatesList;
				if(finalRsvchanBlockWithRatesList.size()>0){
		            Thread thread2 = new Thread(new Runnable() {
		        		public void run() {
		        			handleOTABlocks(null,"CHANGE",finalRsvchanBlockWithRatesList);
		        		}
		        	});
		            thread2.start();
	            }
				
				//send cancel ota block xml to switch
	            final List<RsvchanBlock> finalRemovedRsvchanBlockWithRatesList=removedRsvchanBlockWithRatesList;
	            if(finalRemovedRsvchanBlockWithRatesList.size()>0){
		            Thread thread3 = new Thread(new Runnable() {
		        		public void run() {
		        			handleOTABlocks(null,"DELETE",finalRemovedRsvchanBlockWithRatesList);
		        		}
		        	});
		            thread3.start();
	            }
				// 发送房量消息逻辑
				rsvtypeChannel.setOverBooking(rsvtype.getOverBooking());
				rsvtypeChannel.setHotelCode(vo.getHotelCode());
				rsvtypeChannel.setAvailable(rsvtype.getAvailable());
				rsvtypeChannel.setTotalOBSold(rsvtype.getTotalOBSold());
				rsvtypeChannel.setDate(rsvtype.getDate());
				rsvtypeChannel.setType(rsvtype.getType());
				rsvtypeChannel.setRsvchanBlockList(rsvchanBlockList);
				if(vo.getFreeSell()!=null||(vo.getAllotment()!=null&&rsvtypeChannel.getHasBlock()==1&&isBlockWithoutRate)||(vo.getRateCodes()==null&&rsvtypeChannel.getHasBlock()==1&&isBlockWithoutRate)){
				final RsvtypeChannel finalrsvtypeChannel=rsvtypeChannel;
		            Thread thread1 = new Thread(new Runnable() {
		        		public void run() {
		        			sendChangeSellDataMsg(finalrsvtypeChannel);
		        		}
		        	});
		            thread1.start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("push freesell or allotment msg end " + new Date().toLocaleString());
		return rsvtypeChannel;
	}

	@Override
	public void sendChangeSellDataMsg(RsvtypeChannel rsvtypeChannel) {
		// 发送房量消息逻辑
		if (rsvtypeChannel != null) {
			// 获取该天该ob量
			int obAvailable = getObAvailable(rsvtypeChannel);
			int totalOBSold = getInt(rsvtypeChannel.getTotalOBSold());
			int avai = 0;
			// freeSell为关
			if (rsvtypeChannel.getFreeSell() != null && !rsvtypeChannel.getFreeSell()) {
				avai = obAvailable - totalOBSold;
			} else {
				int roomTypePhysicalRoom = 0;
				if(null == rsvtypeChannel.getAvailable()){
					roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rsvtypeChannel.getType(), rsvtypeChannel.getHotelCode());
				}
				//获取redis中这个月的未同步到PMS的已收房量
		    	//parameter1 is room type,parameter2 is the map of room QTY
		    		String roomTypeCode=rsvtypeChannel.getType();
		    		String key=rsvtypeChannel.getHotelCode()+"|"+roomTypeCode+"|"+DateUtil.getMonthOfYear(rsvtypeChannel.getDate());
		    		Map<String,Long> redisRoomQtyMap=roomQtyDao.readLongValueFromMap(key);
		    		Long redisSoldQty= new Long(0);
		    		if(redisRoomQtyMap!=null){
		    			if(redisRoomQtyMap.get(DateUtil.getDate(rsvtypeChannel.getDate()))!=null){
		    				redisSoldQty=redisRoomQtyMap.get(DateUtil.getDate(rsvtypeChannel.getDate()));
		    			}
			    	}
				avai = roomTypePhysicalRoom + getInt(rsvtypeChannel.getAvailable()) + (obAvailable - totalOBSold)-redisSoldQty.intValue();
				log.info("physicalroom:"+roomTypePhysicalRoom+",avai:"+getInt(rsvtypeChannel.getAvailable())+",obAvai:"+(obAvailable - totalOBSold)+",redisSold:"+redisSoldQty.intValue());
			}
			Channel cl = channelDao.get(rsvtypeChannel.getChannelId());
			if (cl != null && CommonUtil.isNotEmpty(cl.getChannelCode())) {
				rsvtypeChannel.setChannel(cl.getChannelCode());
				rsvtypeChannel.setSendAvailable(avai);
				asyncSendManager.sendRoomStatusMsgToJms(rsvtypeChannel, false);
			}
		}
	}

	@Override
	public void addBatchRsvtypeChannel(List<RsvtypeChannel> rsvcList) {
		if (CommonUtil.isNotEmpty(rsvcList)) {
			long t1 = System.currentTimeMillis();
			rsvtypeChannelDao.addBatchRsvtypeChannel(rsvcList);
			System.out.println("addBatchRsvtypeChannel 这段代码运行了:" + (System.currentTimeMillis() - t1) / 1000.0 + "秒！" + "一共  " + rsvcList.size() + " 条");
		}
	}

	/**
	 * 获取发送到渠道的房量 OB配额 - TotalOBSold >0 关 OB配额 - TotalOBSold OB配额 - TotalOBSold
	 * >0 开 CCM可卖房+（OB配额 - TotalOBSold） OB配额 -TotalOBSold=0 X CCM可卖房
	 * */
	@Override
	public int calcSendChannelAvailable(RsvtypeChannel rsvtypeChannel) throws Exception {
		int avai = 0;
		if (rsvtypeChannel != null) {
			// 获取该天该ob量
			int obAvailable = getObAvailable(rsvtypeChannel);
			int totalOBSold = getInt(rsvtypeChannel.getTotalOBSold());
			int hotelAvailable = getInt(rsvtypeChannel.getAvailable());
			if (obAvailable - totalOBSold >= 0) {
				// freeSell为关
				if (rsvtypeChannel.getFreeSell() != null && !rsvtypeChannel.getFreeSell()) {
					avai = obAvailable - totalOBSold;
				} else {
					int roomTypePhysicalRoom = 0;
					if(null == rsvtypeChannel.getAvailable()){
						roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rsvtypeChannel.getType(), rsvtypeChannel.getHotelCode());
					}
					//获取redis中这个月的未同步到PMS的已收房量
			    	//parameter1 is room type,parameter2 is the map of room QTY
			    		String roomTypeCode=rsvtypeChannel.getType();
			    		String key=rsvtypeChannel.getHotelCode()+"|"+roomTypeCode+"|"+DateUtil.getMonthOfYear(rsvtypeChannel.getDate());
			    		Map<String,Long> redisRoomQtyMap=roomQtyDao.readLongValueFromMap(key);
			    		Long redisSoldQty= new Long(0);
			    		if(redisRoomQtyMap!=null){
			    			if(redisRoomQtyMap.get(DateUtil.getDate(rsvtypeChannel.getDate()))!=null){
			    				redisSoldQty=redisRoomQtyMap.get(DateUtil.getDate(rsvtypeChannel.getDate()));
			    			}
				    	}
					avai = roomTypePhysicalRoom + hotelAvailable + (obAvailable - totalOBSold)-redisSoldQty.intValue();
				}
			}
		}
		return avai;
	}

	@Override
	public Map<String, List<Rsvtype>> getRsvtypeByChannelIdsRoomTypeCodes(RoomStatusVO vo) {
		Map<String, List<Rsvtype>> rtMap = new HashMap<String, List<Rsvtype>>();
		log.info("getRsvtypeByChannelIdsRoomTypeCodes start");
		List<Rsvtype> volist = rsvtypeChannelDao.getRsvtypeByChannelIdsRoomTypeCodes(vo);
		log.info("getRsvtypeByChannelIdsRoomTypeCodes end");
		String[] roomTypeCodes = vo.getRoomTypeCodes();
		Map<String, String> channelMap = getChannelMap();

		//获取redis中这个月的未同步到PMS的已收房量
    	//parameter1 is room type,parameter2 is the map of room QTY
    	Map<String,Map<String,Long>> hotelRoomQtysMap=new HashMap<String,Map<String,Long>>();
    	String []roomTypeArrays=vo.getRoomTypeCodes();
    	for(int i=0;i<roomTypeArrays.length;i++){
    		String roomTypeCode=roomTypeArrays[i];
    		String key=vo.getHotelCode()+"|"+roomTypeCode+"|"+DateUtil.getMonthOfYear(vo.getStartDate());
    		hotelRoomQtysMap.put(roomTypeCode,roomQtyDao.readLongValueFromMap(key));
    	}
    	
		for (int i = 0; i < roomTypeCodes.length; i++) {
			List<Rsvtype> rsvList = new ArrayList<Rsvtype>();
			String roomTypeCode = roomTypeCodes[i];
			
			//set default Room QTY
			RoomType roomType = roomTypeManager.getRoomTypeByHotelCode(roomTypeCode, vo.getHotelCode());
			List<Rsvtype> availableList = new ArrayList<Rsvtype>();
			int daysOfMonth=DateUtil.getDay(vo.getStartDate().getYear(),vo.getStartDate().getMonth()+1);
			for(int day=1;day<=daysOfMonth;day++){
				Map <String,Long>redisRoomQtyMap=hotelRoomQtysMap.get(roomTypeCode);
				Long redisSoldQty=new Long(0);
	    		if(redisRoomQtyMap!=null){
	    			Date curDate=(Date) vo.getStartDate().clone();
	    			curDate.setDate(day);
	    			if(redisRoomQtyMap.get(DateUtil.getDate(curDate))!=null){
	    				redisSoldQty=redisRoomQtyMap.get(DateUtil.getDate(curDate));
	    			}
		    	}
				
				Rsvtype availRsv = new Rsvtype();
				if (roomType != null && roomType.getPhysicalRooms() != null) {
					availRsv.setPhysicalRooms(roomType.getPhysicalRooms()-redisSoldQty.intValue());
				} else {
					availRsv.setPhysicalRooms(0);
				}
				availableList.add(availRsv);
			}
				rtMap.put(roomTypeCode + "_available", availableList);
			
			//set  QTY of rsvtype
			for (Rsvtype r : volist) {
				if (roomTypeCode.equals(r.getType())) {
					Map <String,Long>redisRoomQtyMap=hotelRoomQtysMap.get(r.getType());
		    		if(redisRoomQtyMap!=null){
		    			Long redisSoldQty=redisRoomQtyMap.get(DateUtil.getDate(r.getDate()));
		    			if(redisSoldQty==null){
		    				redisSoldQty=new Long(0);
		    				}
			    		if(redisSoldQty!=null){
			    			if(r.getAvailable()!=null){
			    				r.setAvailable((r.getAvailable()-redisSoldQty.intValue())<=0?0:r.getAvailable()-redisSoldQty.intValue());
			    			}
			    			if(r.getUnavailable()!=null){
			    				r.setUnavailable((r.getUnavailable()+redisSoldQty.intValue())<0?0:(r.getUnavailable()+redisSoldQty.intValue()));
			    			}else{
			    				r.setUnavailable(redisSoldQty.intValue()<0?0:redisSoldQty.intValue());
			    			}
			    		}
			    	}
					rsvList.add(r);
					List<RsvtypeChannel> rcList = r.getRcList();
					if (rcList != null) {
						for (RsvtypeChannel rc : rcList) {
							if (rc != null) {
								rc.setHasBlock(getInt(rc.getHasBlock()));
								rc.setChannelCode(channelMap.get(rc.getChannelId()));
							}
						}
					}
				}
			}
			rtMap.put(roomTypeCode, rsvList);

			
		}
		return rtMap;
	}

	public Map<String, String> getChannelMap() {
		Map<String, String> channelMap = new HashMap<String, String>();
		List<Channel> clLIst = channelDao.getAll();
		for (Channel channel : clLIst) {
			if (!channelMap.containsKey(channel.getChannelId())) {
				channelMap.put(channel.getChannelId(), channel.getChannelCode());
			}
		}
		return channelMap;
	}

	/**
	 * RoomStatusVO转为RoomStatusJsonVO对象
	 */
	private RoomStatusJsonVO getRoomStatusJsonVO(Rsvtype vo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RoomStatusJsonVO jsonvo = new RoomStatusJsonVO();
		jsonvo.setDate(sdf.format(vo.getDate()));
		jsonvo.setUnavailable(vo.getUnavailable());
		jsonvo.setTotalOBSold(vo.getTotalOBSold());
		jsonvo.setAvailable(vo.getAvailable());
		jsonvo.setOverBooking(vo.getOverBooking());

		// jsonvo.setAllotmentSold(vo.getAllotmentSold());
		// jsonvo.setObSold(vo.getObSold());
		// jsonvo.setAllotment(vo.getAllotment());
		// jsonvo.setFreeSell(vo.getFreeSell());
		// jsonvo.setSold(vo.getSold()); //渠道已售房量
		return jsonvo;
	}

	/** 取到每一天的房量,checkoutDate 这一天不包含 */
	@Override
	public List<RsvtypeChannel> getRsvtypeChannelAvailable(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate, String channelCode) {
		// checkoutDate = DateUtil.addDays(checkoutDate, -1);
		checkinDate = DateUtil.getCleanDate(checkinDate);
		checkoutDate = DateUtil.getCleanDate(checkoutDate);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("channelCode", channelCode);
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("roomTypeCode", roomTypeCode);
		paramMap.put("startDate", checkinDate);
		paramMap.put("endDate", checkoutDate);
		List<RsvtypeChannel> rcList = rsvtypeChannelDao.getRsvtypeChannelAilable(paramMap);

		return rcList;
	}
	/** 取到每一天的房量,checkoutDate 这一天不包含 */
	@Override
	public Map<String,List<RsvchanBlock>> getBlockChannelAvailable(List<RsvtypeChannel> rcList) {
		// TODO Auto-generated method stub
		Map<String,List<RsvchanBlock>>  rsvchanBlocksMap = rsvchanBlockDao.getBlockChannelAilable(rcList);
		return rsvchanBlocksMap;
	}
	
	@Override
	public int getRsvtypeChannelAvailableByDate(String roomTypeCode, String hotelCode, Date date, String channelCode,String ratePlanCode) {
		return getRsvtypeChannelMinAvailable(roomTypeCode, hotelCode, date, DateUtil.addDays(date, 1), channelCode,ratePlanCode);
	}

	@Override
	public int getRsvtypeChannelMinAvailable(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate, String channelCode,String ratePlanCode) {
		List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
		Map<String,List<RsvchanBlock>> rsvchanBlocksMap =this.getBlockChannelAvailable(rcList);
		Date orderCreatedDate=DateUtil.currentDate();
		// 物理房量
		int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode);
		if (rcList == null || rcList.size() == 0) {
			if(isDaysProductionOpen(hotelCode,channelCode,checkinDate,checkoutDate,roomTypeCode,ratePlanCode)){
				return physicalRoom;
			}else{
				return 0;
			}
		}

		Integer validBlockResult=validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,ratePlanCode);
		if(validBlockResult==-1){
			return 0;
		}
		
		int dateDiff = DateUtil.dateDiff(checkinDate, checkoutDate);
		for (int i = 0; i < dateDiff; i++) {
			Date nowDate = DateUtil.addDays(checkinDate, i);
			boolean flag = false; // 标识是否已找到 rsvtypeChannel记录
			boolean productionOpen=isProductionOpen(hotelCode,channelCode,nowDate,roomTypeCode,ratePlanCode);
			for (RsvtypeChannel rc : rcList) {
				String dateStr = DateUtil.convertDateToString(rc.getDate());
				if (DateUtil.convertDateToString(nowDate).equals(dateStr)) {
					flag = true; // 已找到
					int available = 0;
					int avaiAllotmentSold = 0;
					if(getInt(rc.getHasBlock())==0){
						avaiAllotmentSold=getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
						boolean isAllotmentRateCode=true;
						String allotmentRatecodes=rc.getRatePlanCodes();
						if(allotmentRatecodes!=null){
							if(!allotmentRatecodes.contains(ratePlanCode)){
								avaiAllotmentSold=0;
								isAllotmentRateCode=false;
							}
						}
						if(isAllotmentRateCode){
								if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
									log.info(dateStr + "Allotment下单日期超出CutOffDate范围,allotment为0");
									avaiAllotmentSold=0;
							}
						}
						if(productionOpen==false)
							avaiAllotmentSold=0;
						log.info(dateStr + ":allotment可用房量:" + avaiAllotmentSold);
					}else{
						//block of pms
						List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
						if(blookList!=null){
							for(RsvchanBlock block:blookList){
								if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}else if(block.getRatePlanCodes().trim().contains(ratePlanCode)){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}
							}
						}
						if(validBlockResult==0)  avaiAllotmentSold=0;
						log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
					}
					if(avaiAllotmentSold > 0){
						available += avaiAllotmentSold;
					}
					if(productionOpen){
						log.info("freeSell:" + rc.getFreeSell());
						// freeSell为关，取ob房量
						if (rc.getFreeSell() != null && !rc.getFreeSell()) {
							// 获取该天该ob量
							rc.setChannelCode(channelCode);
							int obAvailable = getObAvailable(rc);
							int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
							if (avaiOb > 0) {
								available += avaiOb;
							}
							log.info(dateStr + ":ob可用房量:" + avaiOb);
						} else {
							log.info("日期:" + dateStr + ",rc.getAvailable:" + rc.getAvailable());
							//获得redis的已售出房量
							RedisRsvtype  findRedisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,rc.getDate(),new Integer(0));
							Map<String,Long>resultMap=roomQtyDao.readLongValueFromMap(findRedisRsvtype.getKey());
							Long redisSoldQty=resultMap.get(DateUtil.getDate(rc.getDate()))!=null?resultMap.get(DateUtil.getDate(rc.getDate())):0;
							// 如果可用房量为空,设置为物理放量
							if (rc.getAvailable() == null) {
								available += physicalRoom-redisSoldQty.intValue();
							} else {
								available += getInt(rc.getAvailable())-redisSoldQty.intValue();
							}
							log.info("available:" + available);

							// 获取该天该ob量
							rc.setChannelCode(channelCode);
							int obAvailable = getObAvailable(rc);

							log.info("日期:" + dateStr + ",obAvailable:" + obAvailable);
							int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
							if (avaiOb > 0) {
								available += avaiOb;
							}
						}
					}
					// 每日可用房量
					log.info("日期:" + dateStr + ":总可用房量:" + available);
					rc.setChannelAvailable(available);
				}
			}
			// 如果表中不存在rsvtypeChannel记录,则设置物理房量
			if (!flag) {
				RsvtypeChannel nullRc = new RsvtypeChannel();
				if(productionOpen){
					nullRc.setChannelAvailable(physicalRoom);
				}else{
					nullRc.setChannelAvailable(0);
				}
				rcList.add(nullRc);
				}
		}

		Collections.sort(rcList, new Comparator<RsvtypeChannel>() {
			@Override
			public int compare(RsvtypeChannel o1, RsvtypeChannel o2) {
				return o1.getChannelAvailable().compareTo(o2.getChannelAvailable());
			}
		});

		return rcList.get(0).getChannelAvailable();
	}

	@Override
	public int queryRsvtypeChannelAvailableByDate(String roomTypeCode, String hotelCode, Date date, String channelCode,String rateplanCode) {
		return queryRsvtypeChannelMinAvailable(roomTypeCode, hotelCode, date, DateUtil.addDays(date, 1), channelCode,rateplanCode);
	}

	@Override
	public int queryRsvtypeChannelMinAvailable(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate, String channelCode,String rateplanCode) {
		List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
		Map<String,List<RsvchanBlock>> rsvchanBlocksMap =this.getBlockChannelAvailable(rcList);
		Date orderCreatedDate=DateUtil.currentDate();
		// 物理房量
		int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode);
		if (rcList == null || rcList.size() == 0) {
			if(isDaysProductionOpen(hotelCode,channelCode,checkinDate,checkoutDate,roomTypeCode,rateplanCode)){
				return physicalRoom;
			}else{
				return 0;
			}
		}
		Integer validBlockResult=validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,rateplanCode);
		if(validBlockResult==-1){
			return 0;
		}
		int dateDiff = DateUtil.dateDiff(checkinDate, checkoutDate);
		for (int i = 0; i < dateDiff; i++) {
			Date nowDate = DateUtil.addDays(checkinDate, i);
			boolean flag = false; // 标识是否已找到 rsvtypeChannel记录
			boolean productionOpen=isProductionOpen(hotelCode,channelCode,nowDate,roomTypeCode,rateplanCode);
			for (RsvtypeChannel rc : rcList) {
				String dateStr = DateUtil.convertDateToString(rc.getDate());
				if (DateUtil.convertDateToString(nowDate).equals(dateStr)) {
					flag = true; // 已找到
					int available = 0;
					int avaiAllotmentSold =0;
					if(getInt(rc.getHasBlock())==0){
						avaiAllotmentSold = getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
	//					log.info(hotelCode + "freeSell:" + rc.getFreeSell());
						// freeSell为关，取ob房量
						boolean isAllotmentRateCode=true;
						String allotmentRatecodes=rc.getRatePlanCodes();
						if(allotmentRatecodes!=null){
							if(!allotmentRatecodes.contains(rateplanCode)){
								avaiAllotmentSold=0;
								isAllotmentRateCode=false;
							}
						}
						if(isAllotmentRateCode){
								if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
									log.info(dateStr + "Allotment下单日期超出CutOffDate范围,allotment为0");
									avaiAllotmentSold=0;
							}
						}
						if(productionOpen==false)
							avaiAllotmentSold=0;
						log.info(dateStr + ":allotment可用房量:" + avaiAllotmentSold);
					}else{
						//block of pms
						List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
						if(blookList!=null){
							for(RsvchanBlock block:blookList){
								if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}else if(block.getRatePlanCodes().trim().contains(rateplanCode)){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}
							}
						}
						if(validBlockResult==0) avaiAllotmentSold=0;
						log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
					}
					if(avaiAllotmentSold > 0){
						available+=avaiAllotmentSold;
					}					
				if(productionOpen){
					log.info("freeSell:" + rc.getFreeSell());						
					if (rc.getFreeSell() != null && !rc.getFreeSell()) {
						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = getObAvailable(rc);
						int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
						if (avaiOb > 0) {
							available += avaiOb;
						}
//						log.info(hotelCode + dateStr + ":ob可用房量:" + avaiOb);
					} else {
						// freeSell is opened
						//获得redis的已售出房量
						RedisRsvtype  findRedisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,rc.getDate(),new Integer(0));
						Map<String,Long>resultMap=roomQtyDao.readLongValueFromMap(findRedisRsvtype.getKey());
						Long redisSoldQty=resultMap.get(DateUtil.getDate(rc.getDate()))!=null?resultMap.get(DateUtil.getDate(rc.getDate())):0;
//						log.info(hotelCode + "日期:" + dateStr + ",rc.getAvailable:" + rc.getAvailable());
						// 如果可用房量为空,设置为物理放量
						if (rc.getAvailable() == null) {
							available += physicalRoom-redisSoldQty.intValue();
						} else {
							available += getInt(rc.getAvailable())-redisSoldQty.intValue();
						}
//						log.info(hotelCode + "available:" + available);

						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = getObAvailable(rc);

//						log.info(hotelCode + "日期:" + dateStr + ",obAvailable:" + obAvailable);
						int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
						if (avaiOb > 0) {
							available += avaiOb;
						}
					}
				}
					// 每日可用房量
					log.info(hotelCode + "日期:" + dateStr + ":总可用房量:" + available);
					rc.setChannelAvailable(available);
				}
			}

			// 如果表中不存在rsvtypeChannel记录,则设置物理房量
			if (!flag) {
				RsvtypeChannel nullRc = new RsvtypeChannel();
				if(productionOpen){
					nullRc.setChannelAvailable(physicalRoom);
				}else{
					nullRc.setChannelAvailable(0);
				}
				rcList.add(nullRc);
			}
		}

		Collections.sort(rcList, new Comparator<RsvtypeChannel>() {
			@Override
			public int compare(RsvtypeChannel o1, RsvtypeChannel o2) {
				return o1.getChannelAvailable().compareTo(o2.getChannelAvailable());
			}
		});

		return rcList.get(0).getChannelAvailable();
	}
	private boolean createNewRsvtypeChannel(RsvChangeInfo rsvChangeInfo){
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
		try {
			saveBatchRsvtypeChannel(roomStatusSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	//save the batch rsvtypechannel
	public List<RsvtypeChannel>  saveBatchRsvtypeChannel(RoomStatusSetVO setvo) throws Exception {
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
        System.out.println("addRsvtypeChannelList 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"一共  "+addRsvtypeChannelList.size()+" 条");
        return addRsvtypeChannelList;
	}
	//可以用来检查有修改订单的渠道房量信息
	private boolean checkRsvtypeChannelList(List<RsvtypeChannel> rcList,Map<String,List<RsvchanBlock>> rsvchanBlocksMap,RsvChangeInfo rsvChangeInfo) {
		//订单修改信息是否为null
		String hotelCode=rsvChangeInfo.getHotelCode();
		String roomTypeCode=rsvChangeInfo.getNewRoomTypeCode();
		Date checkinDate=rsvChangeInfo.getNewCheckInDate();
		Date checkoutDate=rsvChangeInfo.getNewCheckOutDate();
		String channelCode=rsvChangeInfo.getChannelCode();
		int roomNum=rsvChangeInfo.getNewRsvQty();
		String rateplanCode=rsvChangeInfo.getRateplanCode();
		Date orderCreatedDate=rsvChangeInfo.getOrderCreatedDate();
		// 物理房量
		int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode);
		// 如果没有记录
		if (rcList == null || rcList.size() == 0) {
			if(isDaysProductionOpen(hotelCode,channelCode,checkinDate,checkoutDate,roomTypeCode,rateplanCode)){
				if(true){
					if (physicalRoom >= roomNum) {
						return true;
					} else {
						return false;
					}
				}
			}else{
				return false;
			}
		}
		
		if (rsvChangeInfo.getOldRoomTypeCode()==null){
			//是，就调用老方法
			return oldcheckRsvtypeChannelList(rcList,rsvchanBlocksMap,rsvChangeInfo);
		}else{
			//检查修改前与修改后是否是一个房型
		if(rsvChangeInfo.getOldRoomTypeCode().equalsIgnoreCase(rsvChangeInfo.getNewRoomTypeCode())){
			//新日期范围在旧日期的范围内
			if(!rsvChangeInfo.getNewCheckInDate().before(rsvChangeInfo.getOldCheckInDate())&&!rsvChangeInfo.getNewCheckOutDate().after(rsvChangeInfo.getOldCheckOutDate())){
				if(rsvChangeInfo.getNewRsvQty()-rsvChangeInfo.getOldRsvQty()<=0){
					return true;
				}
			}
			
			Integer validBlockResult= validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,rateplanCode);
			if(validBlockResult==-1){
					return false;
			}	
		int dateDiff = DateUtil.dateDiff(checkinDate, checkoutDate);
		for (int i = 0; i < dateDiff; i++) {
			Date nowDate = DateUtil.addDays(checkinDate, i);
			boolean flag = false; // 标识是否已找到 rsvtypeChannel记录
			boolean productionOpen=isProductionOpen(hotelCode,channelCode,nowDate,roomTypeCode,rateplanCode);
			if(rsvChangeInfo.getIsSupplierRateCode())
				productionOpen=true;
			int checkRoomNum=roomNum;//设置检查的预订房量
			//如果当天日期大于等于入住日期&小于退房日期，就验证新房量与老房量的差值
			if(nowDate.compareTo(rsvChangeInfo.getOldCheckInDate())>=0&&nowDate.compareTo(rsvChangeInfo.getOldCheckOutDate())<0){
				checkRoomNum=roomNum-rsvChangeInfo.getOldRsvQty();
			}

			for (RsvtypeChannel rc : rcList) {
				String dateStr = DateUtil.convertDateToString(rc.getDate());
				if (DateUtil.convertDateToString(nowDate).equals(dateStr)) {
					flag = true; // 已找到
					int available = 0;
					int avaiAllotmentSold=0;
					if(getInt(rc.getHasBlock())==0){
						//allotment of ccm
						avaiAllotmentSold = getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
						boolean isAllotmentRateCode=true;
						
						String allotmentRatecodes=rc.getRatePlanCodes();
						if(allotmentRatecodes!=null){
							if(!allotmentRatecodes.contains(rateplanCode)){
								avaiAllotmentSold=0;
								isAllotmentRateCode=false;
							}
						}
						if(isAllotmentRateCode){
								if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
									log.info(dateStr + "allotment下单日期超出CutOffDate范围,allotment为0");
									avaiAllotmentSold=0;
								}
						}
						if(productionOpen==false)
							avaiAllotmentSold=0;
						log.info(dateStr + ":allotment可用房量:" + avaiAllotmentSold);
					}else{
						//block of pms
						List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
						if(blookList!=null){
							for(RsvchanBlock block:blookList){
								if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}else if(block.getRatePlanCodes().trim().contains(rateplanCode)){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}
							}
						}
						if(validBlockResult==0) avaiAllotmentSold=0;
						log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
					}				
					if (avaiAllotmentSold >= checkRoomNum) {
						// 取保留房
						available+= avaiAllotmentSold;
					} else if (avaiAllotmentSold < checkRoomNum) {
						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = getObAvailable(rc);
						int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
						log.info(dateStr + ":ob可用房量:" + avaiOb);

						if(avaiAllotmentSold>0){
							available+=avaiAllotmentSold;
						}
						if(productionOpen){
							// freeSell为关，取ob房量
							if (rc.getFreeSell() != null && !rc.getFreeSell()) {
								//保留房大于0
								if (avaiOb > 0) {
									available+= avaiOb;
								}
							} else {
								log.info("日期:" + dateStr + ",rc.getAvailable:" + rc.getAvailable());
								// 如果可用房量为空,取物理房量
								if (rc.getAvailable() == null) {
									available += physicalRoom;
								} else {
									available += rc.getAvailable();
								}
								RedisRsvtype  datefindRedisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,rc.getDate(),new Integer(0));
								Map<String,Long>dateresultMap=roomQtyDao.readLongValueFromMap(datefindRedisRsvtype.getKey());
								Long dateredisSoldQty=dateresultMap.get(DateUtil.getDate(rc.getDate()))!=null?dateresultMap.get(DateUtil.getDate(rc.getDate())):0;
								available-=dateredisSoldQty.intValue();
								log.info("available:" + available);
	
								if (avaiOb > 0) {
									available += avaiOb;
								}
							}
						}
					}
					// 每日可用房量
					if (available < checkRoomNum) {
						log.info(dateStr + ":总可用房量:" + available);
						return false;
					}
				}
			}
			// 如果表中不存在rsvtypeChannel记录,则判断物理房量
			if (!flag) {
				if (physicalRoom < checkRoomNum||productionOpen==false) {
					return false;
				}
			}
		}

		return true;
		}else{
			//新旧订单不是一个房型，就调用老方法
			return oldcheckRsvtypeChannelList(rcList,rsvchanBlocksMap,rsvChangeInfo);
			}
		}
	}
	private boolean oldcheckRsvtypeChannelList(List<RsvtypeChannel> rcList,Map<String,List<RsvchanBlock>> rsvchanBlocksMap,RsvChangeInfo rsvChangeInfo) {
		// 物理房量
		String hotelCode=rsvChangeInfo.getHotelCode();
		String roomTypeCode=rsvChangeInfo.getNewRoomTypeCode();
		Date checkinDate=rsvChangeInfo.getNewCheckInDate();
		Date checkoutDate=rsvChangeInfo.getNewCheckOutDate();
		String channelCode=rsvChangeInfo.getChannelCode();
		int roomNum=rsvChangeInfo.getNewRsvQty();
		String rateplanCode=rsvChangeInfo.getRateplanCode();
		Date orderCreatedDate=rsvChangeInfo.getOrderCreatedDate();
		
		int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode);
		Integer validBlockResult= validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,rateplanCode);
		if(validBlockResult==-1){
				return false;
		}	
		int dateDiff = DateUtil.dateDiff(checkinDate, checkoutDate);
		for (int i = 0; i < dateDiff; i++) {
			Date nowDate = DateUtil.addDays(checkinDate, i);
			boolean flag = false; // 标识是否已找到 rsvtypeChannel记录
			boolean productionOpen=isProductionOpen(hotelCode,channelCode,nowDate,roomTypeCode,rateplanCode);
			if(rsvChangeInfo.getIsSupplierRateCode())
				productionOpen=true;
			for (RsvtypeChannel rc : rcList) {
				String dateStr = DateUtil.convertDateToString(rc.getDate());
				if (DateUtil.convertDateToString(nowDate).equals(dateStr)) {
					flag = true; // 已找到
					int available = 0;
					int avaiAllotmentSold =0;
					
					if(getInt(rc.getHasBlock())==0){
						//allotment of ccm
						avaiAllotmentSold = getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
						boolean isAllotmentRateCode=true;
						String allotmentRatecodes=rc.getRatePlanCodes();
						if(allotmentRatecodes!=null){
							if(!allotmentRatecodes.contains(rateplanCode)){
								avaiAllotmentSold=0;
								isAllotmentRateCode=false;
							}
						}
						
						if(isAllotmentRateCode){
								if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
									log.info(dateStr + "Allotment下单日期超出CutOffDate范围,allotment为0");
									avaiAllotmentSold=0;
							}
						}
						if(productionOpen==false)
							avaiAllotmentSold=0;
						log.info(dateStr + ":allotment可用房量:" + avaiAllotmentSold);
					}else{
						//block of pms
						List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
						if(blookList!=null){
							for(RsvchanBlock block:blookList){
								if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}else if(block.getRatePlanCodes().trim().contains(rateplanCode)){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}
							}
						}
						if(validBlockResult==0)avaiAllotmentSold=0;
						log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
					}
					if (avaiAllotmentSold >= roomNum) {
						// 取保留房
						available = avaiAllotmentSold;
					} else if (avaiAllotmentSold < roomNum) {
						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = getObAvailable(rc);
						int avaiOb = obAvailable  - getInt(rc.getTotalOBSold());
						log.info(dateStr + ":ob可用房量:" + avaiOb);

						if(avaiAllotmentSold>0){
							available+=avaiAllotmentSold;
						}
						if(productionOpen){
							// freeSell为关，取ob房量
							if (rc.getFreeSell() != null && !rc.getFreeSell()) {
								// 如果ob量大于预订房间数
								if (avaiOb >= 0) {
									available += avaiOb;
								}
							} else {
								log.info("日期:" + dateStr + ",rc.getAvailable:" + rc.getAvailable());
								// 如果可用房量为空,取物理房量
								if (rc.getAvailable() == null) {
									available += physicalRoom;
								} else {
									available += rc.getAvailable();
								}
								RedisRsvtype  datefindRedisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,rc.getDate(),new Integer(0));
								Map<String,Long>dateresultMap=roomQtyDao.readLongValueFromMap(datefindRedisRsvtype.getKey());
								Long dateredisSoldQty=dateresultMap.get(DateUtil.getDate(rc.getDate()))!=null?dateresultMap.get(DateUtil.getDate(rc.getDate())):0;
								available-=dateredisSoldQty.intValue();
								log.info("available:" + available);
	
								if (avaiOb > 0) {
									available += avaiOb;
								}
							}
						}
					}
					// 每日可用房量
					if (available < roomNum) {
						log.info(dateStr + ":总可用房量:" + available);
						return false;
					}
				}
			}
			// 如果表中不存在rsvtypeChannel记录,则判断物理房量
			if (!flag) {
				if (physicalRoom < roomNum||productionOpen==false) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public boolean validataRsvtypeChannelAilable(RsvChangeInfo rsvChangeInfo) {
		String hotelCode=rsvChangeInfo.getHotelCode();
		String roomTypeCode=rsvChangeInfo.getNewRoomTypeCode();
		Date checkinDate= rsvChangeInfo.getNewCheckInDate();
		Date checkoutDate=rsvChangeInfo.getNewCheckOutDate();
		String channelCode=rsvChangeInfo.getChannelCode();
		List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
		Map<String,List<RsvchanBlock>> rsvchanBlocksMap = this.getBlockChannelAvailable(rcList);
		return this.checkRsvtypeChannelList(rcList,rsvchanBlocksMap,rsvChangeInfo);
	}

	/** 更新房价可卖条件中已卖房量 */
	@Override
	public synchronized boolean confirmSoldableCondition(String hotelCode, String rateplanCode, Date checkinDate, Date checkoutDate, int roomNum) throws Exception {
		PriceCalc pc = new PriceCalc();
		pc.setStartDate(DateUtil.convertDateToString(checkinDate));
		pc.setEndDate(DateUtil.convertDateToString(checkoutDate));
		pc.setNumberOfUnits(roomNum);
		Rateplan rv = ratePlanManager.getRateplanByRateplanCode(rateplanCode, hotelCode);
		List<SoldableCondition> scList = rv.getSCList();
		if (CommonUtil.isEmpty(scList)) {
			return true;
		} else {
			log.info("SoldableConditionList: " + scList);
			int evenDay = DateUtil.dateDiff(checkinDate, checkoutDate);
			for (SoldableCondition soldableCondition : scList) {
				if (priceCalcManager.validataSoldableCondition(soldableCondition, pc, null)) {
					int soldNum = soldableCondition.getSoldNum() == null ? 0 : soldableCondition.getSoldNum();
					soldableCondition.setSoldNum(soldNum + (evenDay * roomNum));
					ratePlanManager.updateRateSoldableCondition(rv.getRatePlanId(), JSON.toJSONString(scList));
					return true;
				}
			}
			log.warn("confirmSoldableCondition fail！");
			return false;
		}
	}

	@Override
	public boolean confirmRsvtypeChannelAilable(RsvChangeInfo rsvChangeInfo) throws Exception {
		/*push block collection*/
		List<RsvchanBlock> rsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		List<Rsvtype> sendAvaiRsvtypeList=new ArrayList<Rsvtype>();
		String roomTypeCode=rsvChangeInfo.getNewRoomTypeCode();
		String hotelCode=rsvChangeInfo.getHotelCode();
		Date checkinDate=rsvChangeInfo.getNewCheckInDate();
		Date checkoutDate=rsvChangeInfo.getNewCheckOutDate();
		String channelCode=rsvChangeInfo.getChannelCode();
		String rateplanCode=rsvChangeInfo.getRateplanCode();
		Date orderCreatedDate=rsvChangeInfo.getOrderCreatedDate();
		int roomNum= rsvChangeInfo.getNewRsvQty();
		List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
		Integer validBlockResult=validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,rateplanCode);
		if(validBlockResult==-1){
			return false;
		}
		Map<String,List<RsvchanBlock>> rsvchanBlocksMap = this.getBlockChannelAvailable(rcList);
		// 验证房量
		if (!this.checkRsvtypeChannelList(rcList,rsvchanBlocksMap,rsvChangeInfo)) {
			return false;
		}

		// 如果纪录都为空
		if (rcList == null || rcList.size() == 0) {
			return true;
		}

		String channelId = "";
		// 更新房量
		for (RsvtypeChannel rc : rcList) {
			boolean isUpdatedRsvtype=false;
			int txObSold=0;
			int txTotalOBSold=0;
			int txSold=0;
			int txAllotmentSold=0;
			Rsvtype rsvtype = new Rsvtype();// rsvtypeDao.getRsvtype(rc);
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);// 忽略空值
			BeanUtils.copyProperties(rsvtype, rc);
			RsvtypeChannel rsvtypeChannel = new RsvtypeChannel();// rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(rc.getRsvtypeId(),
																	// rc.getChannelId());
			RsvchanBlock rsvchanBlock=null;
			boolean isBlockRateEmpty=false;
			BeanUtils.copyProperties(rsvtypeChannel, rc);
			rsvtypeChannel.setFreeSell(rc.getFreeSell());

			if (CommonUtil.isEmpty(rsvtypeChannel.getRsvtypeChannelId())) {
				if (CommonUtil.isEmpty(rc.getChannelId())) {
					Channel channel = channelDao.getChannelByChannelCode(channelCode);
					channelId = channel.getChannelId();
				} else {
					channelId = rc.getChannelId();
				}
				rsvtypeChannel.setChannelId(channelId);
				rsvtypeChannel.setRsvtypeId(rsvtype.getRsvtypeId());
			}

			String dateStr = DateUtil.convertDateToString(rc.getDate());
			int allotmentMaximumAvai=0;
			int avaiAllotmentSold =0;
			if(getInt(rc.getHasBlock())==0){
				avaiAllotmentSold = getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
				boolean isAllotmentRateCode=true;
				String allotmentRatecodes=rc.getRatePlanCodes();
				if(allotmentRatecodes!=null){
					if(!allotmentRatecodes.contains(rateplanCode)){
						avaiAllotmentSold=0;
						allotmentMaximumAvai= getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
						isAllotmentRateCode=false;
					}
				}
					if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
						log.info(dateStr + "allotment下单日期超出CutOffDate范围,allotment为0");
						if(isAllotmentRateCode){
							avaiAllotmentSold=0;
						}
							allotmentMaximumAvai=0;
					}
				log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
			}else{
				//block of pms
				List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
				if(blookList!=null){
					for(RsvchanBlock block:blookList){
						if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
							if(orderCreatedDate.after(block.getCutOffDate())==false){
								avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
								rsvchanBlock=block;
								isBlockRateEmpty=true;
								break;
							}
						}
						else if(block.getRatePlanCodes().trim().contains(rateplanCode)){
							if(orderCreatedDate.after(block.getCutOffDate())==false){
								avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
								rsvchanBlock=block;
							}
						}else{
							if(orderCreatedDate.after(block.getCutOffDate())==false){
								if(allotmentMaximumAvai<(getInt(block.getBlockNum())-getInt(block.getBlockSold()))){
									allotmentMaximumAvai=getInt(block.getBlockNum())-getInt(block.getBlockSold());
								}
							}
						}
					}
				}
				if(validBlockResult==0)avaiAllotmentSold=0;
				log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
			}
			
			
			// 物理房量
			int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode);
			log.info("physicalRoom:" + physicalRoom);

			// freeSell为开，先取酒店房量，不够则取ob房量
			int hotelAvailable = 0;
			log.info("rc.getAvailable:" + rc.getAvailable());
			if (rc.getAvailable() != null) {
				hotelAvailable = getInt(rc.getAvailable());
			}else{
				hotelAvailable = physicalRoom;
			}
			
			// 获取该天该ob量
			int obAvailable = getObAvailable(rc);
			int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
			
			//获得redis的已售出房量
			RedisRsvtype  findRedisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,rc.getDate(),new Integer(0));
			Map<String,Long>resultMap=roomQtyDao.readLongValueFromMap(findRedisRsvtype.getKey());
			Long redisSoldQty=resultMap.get(DateUtil.getDate(rc.getDate()))!=null?resultMap.get(DateUtil.getDate(rc.getDate())):0;
			
			if (avaiAllotmentSold >= roomNum) {
				if(getInt(rc.getHasBlock())==0){
					// 已售保留房 allotment
					int allotmentSold = rsvtypeChannel.getAllotmentSold() + roomNum;
					rsvtypeChannel.setAllotmentSold(allotmentSold >= 0 ? allotmentSold : 0);
					txAllotmentSold+=roomNum;
				}else{
					// 已售保留房 block
					rsvchanBlock.setBlockSold(roomNum);
					rsvchanBlockDao.updateRsvchanBlockSold(rsvchanBlock);
					if(isBlockRateEmpty){
						//send ob + block avai
						if (rc.getFreeSell() != null && !rc.getFreeSell()) {
							/** OBSold值变化,发送消息 */
							int sendAvai = avaiOb;
							if (sendAvai < 0) sendAvai=0;
								rsvtype.setSendAvailable(sendAvai);
						}else{
							int sendAvai = (avaiOb + hotelAvailable) - redisSoldQty.intValue();
							if (sendAvai < 0) sendAvai=0;
							rsvtype.setSendAvailable(sendAvai);
						}
						sendAvaiRsvtypeList.add(rsvtype);
					}else{
						//send block to OTA
						rsvchanBlockWithRatesList.add(rsvchanBlock);
					}
				}
			} else if (avaiAllotmentSold < roomNum) {
				log.info("rc.getFreeSell:" + rc.getFreeSell());
				log.info("avaiOb:" + avaiOb + ",roomNum:" + roomNum);

				if(allotmentMaximumAvai >= roomNum){
					rsvChangeInfo.setSendOccupyFreeSellAvai(true);
				}
				// freeSell is closed，取ob房量
				if (rc.getFreeSell() != null && !rc.getFreeSell()) {
					int surplus=roomNum;
					if(avaiAllotmentSold>0){
						if(getInt(rc.getHasBlock())==0){
							// 已售保留房 allotment
							int allotmentSold = rsvtypeChannel.getAllotmentSold() + avaiAllotmentSold;
							rsvtypeChannel.setAllotmentSold(allotmentSold >= 0 ? allotmentSold : 0);
							txAllotmentSold+=avaiAllotmentSold;
						}else{
							// 已售保留房 block
							rsvchanBlock.setBlockSold(avaiAllotmentSold);
							rsvchanBlockDao.updateRsvchanBlockSold(rsvchanBlock);
							if(isBlockRateEmpty==false){
								//send block to OTA
								rsvchanBlockWithRatesList.add(rsvchanBlock);
							}
						}
						surplus=surplus-avaiAllotmentSold;
					}
					
					if (avaiOb >= surplus) {
						// 渠道已售超预订数
						int obSold = getInt(rsvtypeChannel.getObSold()) + surplus;
						rsvtypeChannel.setObSold(obSold >= 0 ? obSold : 0);
						txObSold+=surplus;
						// 酒店 已售超预订总数
						int totalObSold = getInt(rsvtype.getTotalOBSold()) + surplus;
						rsvtype.setTotalOBSold(totalObSold >= 0 ? totalObSold : 0);
						txTotalOBSold+=surplus;
						isUpdatedRsvtype=true;
//						rsvtypeDao.updateRsvtype(rsvtype);

						/** OBSold值变化,发送消息 */
						int sendAvai = obAvailable - rsvtype.getTotalOBSold();
						if (sendAvai < 0) sendAvai=0;
						rsvtype.setSendAvailable(sendAvai);
						sendAvaiRsvtypeList.add(rsvtype);
					}
					log.info(dateStr + ":ob可用房量:" + avaiOb);
				} 
				// freeSell is opened
				else {
					//judge inventory of allotment
					int surplus=roomNum;
					if(avaiAllotmentSold >0){
						if(getInt(rc.getHasBlock())==0){
						// 已售保留房 allotment
							int allotmentSold = rsvtypeChannel.getAllotmentSold() + avaiAllotmentSold;
							rsvtypeChannel.setAllotmentSold(allotmentSold >= 0 ? allotmentSold : 0);
							txAllotmentSold+=avaiAllotmentSold;
						}else{
							// 已售保留房 block
							rsvchanBlock.setBlockSold(avaiAllotmentSold);
							rsvchanBlockDao.updateRsvchanBlockSold(rsvchanBlock);
							if(isBlockRateEmpty==false){
								//send block to OTA
								rsvchanBlockWithRatesList.add(rsvchanBlock);
							}
						}
						surplus=roomNum-avaiAllotmentSold;
					}					
					hotelAvailable=hotelAvailable- redisSoldQty.intValue();
					
						// 如果CCM酒店物理房量大于要预定的房间数
						if (hotelAvailable >= surplus) {
							// send increased room qty to redis 
							modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),surplus);
							
							/** OBSold值变化,发送消息 */
							int sendAvai = (avaiOb + hotelAvailable) - surplus;
							if (sendAvai < 0) sendAvai=0;
							rsvtype.setSendAvailable(sendAvai);
							
							sendAvaiRsvtypeList.add(rsvtype);
						}

					// 如果 CCM物理房量或者PMS酒店物理房量都小于 房间预订数,但是加上ob后满足预订
					if (hotelAvailable < surplus && (avaiOb + hotelAvailable) >= surplus) {
						
						modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),hotelAvailable);
						
						// 渠道已售超预订数
						int obSold = getInt(rsvtypeChannel.getObSold()) + (surplus - hotelAvailable);
						rsvtypeChannel.setObSold(obSold >= 0 ? obSold : 0);
						txObSold+=surplus - hotelAvailable;
						// 酒店-已售超预订总数
						int totalObSold = getInt(rsvtype.getTotalOBSold()) + (surplus - hotelAvailable);
						rsvtype.setTotalOBSold(totalObSold >= 0 ? totalObSold : 0);
						txTotalOBSold+=surplus - hotelAvailable;
						isUpdatedRsvtype=true;
//						rsvtypeDao.updateRsvtype(rsvtype);

						/** OBSold值变化,发送消息 */
						int sendAvai = (avaiOb + hotelAvailable) - surplus;
						if (sendAvai < 0) sendAvai=0;
						rsvtype.setSendAvailable(sendAvai);
					
						sendAvaiRsvtypeList.add(rsvtype);
					}
				}
			}

			// 渠道已售总房数
			int sold = getInt(rsvtypeChannel.getSold()) + roomNum;
			rsvtypeChannel.setSold(sold >= 0 ? sold : 0);
			txSold+=roomNum;
			//copy and update it
			RsvtypeChannel rc2=buildUpdateRsvtypeChannel(rsvtypeChannel,txAllotmentSold,txSold,txObSold);
			
			if(CommonUtil.isEmpty(rsvtypeChannel.getRsvtypeChannelId())){
				try{
					rsvtypeChannelDao.save(rsvtypeChannel);
				}catch(Exception ex){
					if(ex.getMessage().contains("DuplicateKeyException")){
						System.out.println("run into exception");
					}else{
						throw ex;
					}
				}
			}else{
				rsvtypeChannelDao.updateRsvtypeChannelForDeduct(rc2);
			}
			if(isUpdatedRsvtype){
				rsvtype.setTotalOBSold(txTotalOBSold>0?txTotalOBSold:null);
				rsvtypeChannelDao.updateRsvtypeForDeduct(rsvtype);
				}
		}
		final List<RsvchanBlock> finalRsvchanBlockWithRatesList=rsvchanBlockWithRatesList;
		if(finalRsvchanBlockWithRatesList.size()>0){
            Thread thread2 = new Thread(new Runnable() {
        		public void run() {
        			roomJmsManager.sendDeductOTABlocks(null,"CHANGE",finalRsvchanBlockWithRatesList);
        		}
        	});
            thread2.start();
        }
		
		final List<Rsvtype>finalsendAvaiRsvtypeList =sendAvaiRsvtypeList;
		if(finalsendAvaiRsvtypeList.size()>0){
            Thread thread = new Thread(new Runnable() {
        		public void run() {
        			for(Rsvtype rt:finalsendAvaiRsvtypeList){
        				try {
							roomJmsManager.sendRoomStatusMsg2ToJms(rt, false);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        		}
        	});
            thread.start();
        }
		return true;
	}
	
	// 此方法作废，使用对象参数的方法
	@Override
	public boolean confirmRsvtypeChannelAilable(String roomTypeCode, String hotelCode, String rateplanCode, Date checkinDate, Date checkoutDate, String channelCode, int roomNum) throws Exception {
		return false;
	}
	public boolean updateRsvtypeChannelSold(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate, String channelId, String channelCode, int roomNum) throws Exception {
		List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
		if (rcList == null || rcList.isEmpty()) {
			List<Date> dList = DateUtil.getDayList(checkinDate, DateUtil.addDays(checkoutDate, -1));
			for (Date date : dList) {
				Rsvtype vo = new Rsvtype();
				vo.setPhysicalRooms(0);
				vo.setRoomTypeOverbook(0);
				vo.setOutoforder(0);
				vo.setAdultsInHouse(0);
				vo.setChildrenInHouse(0);
				vo.setArrivalRooms(0);
				vo.setDepartureRooms(0);
				vo.setBlockCount(0);
				vo.setResvCount(0);
				vo.setHouseOverbook(0);
				vo.setRsvtypeId(UUID.randomUUID().toString().replace("-", ""));
				vo.setType(roomTypeCode);
				vo.setHotelCode(hotelCode);
				vo.setDate(date);
				rsvtypeDao.save(vo);
				RsvtypeChannel rc = new RsvtypeChannel();
				rc.setRsvtypeId(vo.getRsvtypeId());
				rc.setChannelId(channelId);
				rc.setSold(roomNum);
				rsvtypeChannelDao.save(rc);
			}
		}
		// 更新渠道已售总房数
		for (RsvtypeChannel rc : rcList) {
			if (CommonUtil.isEmpty(rc.getRsvtypeChannelId())) {
				rc.setChannelId(channelId);
			}
			int sold = getInt(rc.getSold()) + roomNum;
			rc.setSold(sold >= 0 ? sold : 0);
			rsvtypeChannelDao.save(rc);
		}
		return true;
	}

	@Override
	public boolean cancelRsvtypeChannelAilable(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate, String channelCode, int roomNum,String orderRateCode,Date orderCreatedDate) {
		List<RsvchanBlock> rsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		List<RsvtypeChannel> sendAvaiRsvtypeChannelList=new ArrayList<RsvtypeChannel>();
		List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
		Map<String,List<RsvchanBlock>> rsvchanBlocksMap= this.getBlockChannelAvailable(rcList);
		Integer validBlockResult=validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,orderRateCode);
		if(validBlockResult==-1){
			return false;
		}
		
		// 验证房量
		for (RsvtypeChannel rc : rcList) {
			boolean needToSend=false;
			boolean isAllotmentRateCode=true;
			RsvchanBlock rsvchanBlock=null;
			boolean isBlockRateEmpty=false;
			if(getInt(rc.getHasBlock())==0){
				String allotmentRatecodes=rc.getRatePlanCodes();
				if(allotmentRatecodes!=null){
					if(!allotmentRatecodes.contains(orderRateCode)){
						isAllotmentRateCode=false;
					}
				}
				if(isAllotmentRateCode){
					if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
						isAllotmentRateCode=false;
					}
				}
			}else{
				//block of pms
				isAllotmentRateCode=false;
				List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
				if(blookList!=null){
					for(RsvchanBlock block:blookList){
						if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
							isAllotmentRateCode=true;
							isBlockRateEmpty=true;
							rsvchanBlock=block;
							break;
						}
						else if(block.getRatePlanCodes().trim().contains(orderRateCode)){
							if(orderCreatedDate.after(block.getCutOffDate())==false){
								isAllotmentRateCode=true;
								rsvchanBlock=block;
								break;
							}
						}
					}
				}
			}
			int obSold = getInt(rc.getObSold());
			int sold = getInt(rc.getSold());
			if (obSold >= roomNum) {
				rc.setObSold(obSold - roomNum);
				rc.setTotalOBSold(getInt(rc.getTotalOBSold()) - roomNum);
				needToSend=true;
			} else if (obSold < roomNum) {
				int allotmentSold=0;
				if(isAllotmentRateCode){
					if(getInt(rc.getHasBlock())==0){
						allotmentSold = getInt(rc.getAllotmentSold());
					}else{
						if(rsvchanBlock!=null){
							allotmentSold = getInt(rsvchanBlock.getBlockSold());
						}else{
							allotmentSold = 0;
						}
					}
				}
				rc.setObSold(0);
				rc.setTotalOBSold(getInt(rc.getTotalOBSold()) - obSold);
				int surplus=roomNum-obSold;
				
				if(isAllotmentRateCode){
					if (sold > allotmentSold) {
						// send  room qty to redis
						if(surplus<= sold-allotmentSold){
							modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),-surplus);
							needToSend=true;
						}else{
							surplus=surplus-(sold-allotmentSold);
							if(getInt(rc.getHasBlock())==0){
								modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),-(sold-allotmentSold));
								rc.setAllotmentSold(allotmentSold - surplus >= 0 ? allotmentSold - surplus : 0);
								needToSend=true;
							}else{
								if(validBlockResult==1){
									modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),-(sold-allotmentSold));
									rsvchanBlock.setBlockSold(-surplus);
									rsvchanBlockDao.updateRsvchanBlockSold(rsvchanBlock);
									rsvchanBlock.setBlockSold(allotmentSold - surplus >= 0 ? allotmentSold - surplus : 0);
									if(isBlockRateEmpty==false){
										rsvchanBlockWithRatesList.add(rsvchanBlock);
									}
									needToSend=true;
								}else if(validBlockResult==0){
									modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),-(surplus+(sold-allotmentSold)));
									needToSend=true;
								}
							}
						}
					} else if (sold == allotmentSold) {
						if(getInt(rc.getHasBlock())==0){
							rc.setAllotmentSold(allotmentSold - surplus >= 0 ? allotmentSold - surplus : 0);
						}else{
							if(validBlockResult==1){
								rsvchanBlock.setBlockSold(-surplus);
								rsvchanBlockDao.updateRsvchanBlockSold(rsvchanBlock);
								rsvchanBlock.setBlockSold(allotmentSold - surplus >= 0 ? allotmentSold - surplus : 0);
								//send block to OTA
								if(isBlockRateEmpty==false){
									rsvchanBlockWithRatesList.add(rsvchanBlock);
									}
							}else if(validBlockResult==0){
								modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),-surplus);
								needToSend=true;
							}
						}
					}
				}else{
					modifyRedisResvtypeToRedis(hotelCode,roomTypeCode,rc.getDate(),-surplus);
					needToSend=true;
				}
			}

			rc.setSold(sold - roomNum >= 0 ? sold - roomNum : 0);
			rsvtypeDao.updateRsvtype(rc);

			if (CommonUtil.isEmpty(rc.getChannelId())) {
				Channel channel = channelDao.getChannelByChannelCode(channelCode);
				rc.setChannelId(channel.getChannelId());
			}
			rsvtypeChannelDao.save(rc);
			if(needToSend){
				sendAvaiRsvtypeChannelList.add(rc);
			}
		}
		final List<RsvchanBlock> finalRsvchanBlockWithRatesList=rsvchanBlockWithRatesList;
		if(finalRsvchanBlockWithRatesList.size()>0){
            Thread thread2 = new Thread(new Runnable() {
        		public void run() {
        			handleOTABlocks(null,"CHANGE",finalRsvchanBlockWithRatesList);
        		}
        	});
            thread2.start();
        }
		
		final List<RsvtypeChannel>finalsendAvaiRsvtypeChannelList =sendAvaiRsvtypeChannelList;
		if(finalsendAvaiRsvtypeChannelList.size()>0){
            Thread thread = new Thread(new Runnable() {
        		public void run() {
        			for(RsvtypeChannel rc:finalsendAvaiRsvtypeChannelList){
        				sendAvai(rc.getHotelCode(), rc.getType(), rc);
        			}
        		}
        	});
            thread.start();
        }
		return true;
	}

	@Override
	public void sendAvai(String hotelCode,String roomTypeCode,RsvtypeChannel rc){
		sendChangeSellDataMsg(rc);
	}
	/** 获取该渠道ob房量 */
	@Override
	public int getObAvailable(RsvtypeChannel rc) {
		if (CommonUtil.isEmpty(rc.getChannelId())) {
			Channel c = channelDao.getChannelByChannelCode(rc.getChannelCode());
			if (c != null) {
				rc.setChannelId(c.getChannelId());
			} else {
				log.error("Channel Id/Code is null");
				return 0;
			}
		}
		if (CommonUtil.isEmpty(rc.getHotelCode())) {
			log.error(" HotelCode is null");
			return 0;
		}
		OverbookingGroup overbookingGroup = null;
		try {
			overbookingGroup = overbookingGroupDao.searchOverbookingChannelHotelGroup(rc.getChannelId(), rc.getHotelCode());
		} catch (Exception e) {
			log.error(rc.getChannelId() + "====" + rc.getHotelCode());
			e.printStackTrace();
		}
		if (overbookingGroup == null) {
			log.info("没有找到ob配额信息 ChannelId:" + rc.getChannelId());
			return 0;
		}
		return (int) (overbookingGroup.getPercent() / 100 * getInt(rc.getOverBooking()));
	}

	private int getInt(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}

	@Override
	public List<RsvtypeChannel> getRsvtypeChannelAvailable(String rsvtypeId, Set<String> channelIdSet) {
		return rsvtypeChannelDao.getRsvtypeChannelAvailable(rsvtypeId, channelIdSet);
	}

	@Override
	public void modifyRedisResvtypeToRedis(String hotelCode,
			String roomTypeCode, Date startDate, int roomQty) {
		// TODO Auto-generated method stub
		RedisRsvtype  redisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,startDate,roomQty);
		roomQtyDao.hincrebyForHash(redisRsvtype.getKey(),redisRsvtype.getDate(),redisRsvtype.getQty());
	}
	
	private void sendDeductOTABlocks(String oxiTrxId,String actionType,List <RsvchanBlock> rsvchanBlockList){

		String chainCode=null;
		if(rsvchanBlockList.size()==0){
			return ;
		}
		List <Hotel> hotelList=hotelDao.getHotelByHotelCode(rsvchanBlockList.get(0).getHotelCode());
		if(hotelList.size()>0){
			String chainId=hotelList.get(0).getChainId();
			Chain chain=chainDao.get(chainId);
			chainCode=chain.getChainCode();
			}else{
				return;
			}
		//key is channel code
		Map <String,List<Map<String, String>>> otaInvBlockListMap=new HashMap<String,List<Map<String, String>>>();
		for(RsvchanBlock rsvchanBlock:rsvchanBlockList){
			if(otaInvBlockListMap.get(rsvchanBlock.getChannelCode())==null){
				List<Map<String, String>> otaInvBlockList = new Vector<Map<String, String>>();
				otaInvBlockListMap.put(rsvchanBlock.getChannelCode(),otaInvBlockList);
			}
		Integer deduct=rsvchanBlock.getBlockSold()!=null?rsvchanBlock.getBlockSold():0;
		rsvchanBlock=rsvchanBlockDao.get(rsvchanBlock.getRsvchanblockId());
		if(CommonUtil.isEmpty(rsvchanBlock))
			continue;
		
		List<Map<String, String>> otaInvBlockList=otaInvBlockListMap.get(rsvchanBlock.getChannelCode());
		Map<String, String> otaBlockMap = new HashMap<String, String>();
		otaBlockMap.put("actionType", actionType);
		otaBlockMap.put("hotelCode",  rsvchanBlock.getHotelCode());
		otaBlockMap.put("chainCode", chainCode);
		otaBlockMap.put("invBlockCode", rsvchanBlock.getBlockCode());
		otaBlockMap.put("invBlockGroupingCode",
				rsvchanBlock.getBlockCode());
		otaBlockMap.put("startDate",
				DateUtil.convertDateToString(rsvchanBlock.getDate()));
		otaBlockMap.put("endDate",
				DateUtil.convertDateToString(rsvchanBlock.getDate()));
		if(rsvchanBlock.getCutOffDate()==null){
			otaBlockMap.put("cutOffDate",
					DateUtil.convertDateToString(rsvchanBlock.getDate()));
		}else{
			otaBlockMap.put("cutOffDate",
					DateUtil.convertDateToString(rsvchanBlock.getCutOffDate()));
		}
		otaBlockMap.put("roomTypeCode", rsvchanBlock.getType());
		otaBlockMap.put("numberOfUnits",
				String.valueOf(getInt(rsvchanBlock.getBlockNum())-getInt(rsvchanBlock.getBlockSold())-deduct));
		otaBlockMap.put("ratePlanCodes", rsvchanBlock.getRatePlanCodes());
	
		otaInvBlockList.add(otaBlockMap);
		}
		
		for(Map.Entry<String,List<Map<String, String>>> entry:otaInvBlockListMap.entrySet()){
			List<Map<String, String>> otaInvBlockList =entry.getValue();
			String channelCode=entry.getKey();
			Map<String,Object>blockParamMap=new HashMap<String,Object>();
			blockParamMap.put("oxiTrxId",oxiTrxId);
			blockParamMap.put("messageType",MessageType.ALLOTMENT);
			blockParamMap.put("action",actionType);
			blockParamMap.put("hotelCode",otaInvBlockList.get(0).get("hotelCode"));
			blockParamMap.put("chainCode",chainCode);
			blockParamMap.put("target",channelCode);
			blockParamMap.put("invBlockList",otaInvBlockList);
			sendBlockManager.done(blockParamMap);
		}
	}
	@Override
	public void handleOTABlocks(String oxiTrxId,String actionType,List <RsvchanBlock> rsvchanBlockList){
		String chainCode=null;
		if(rsvchanBlockList.size()==0){
			return ;
		}
		List <Hotel> hotelList=hotelDao.getHotelByHotelCode(rsvchanBlockList.get(0).getHotelCode());
		if(hotelList.size()>0){
			String chainId=hotelList.get(0).getChainId();
			Chain chain=chainDao.get(chainId);
			chainCode=chain.getChainCode();
			}else{
				return;
			}
		//key is channel code
		Map <String,List<Map<String, String>>> otaInvBlockListMap=new HashMap<String,List<Map<String, String>>>();
		for(RsvchanBlock rsvchanBlock:rsvchanBlockList){
			if(otaInvBlockListMap.get(rsvchanBlock.getChannelCode())==null){
				List<Map<String, String>> otaInvBlockList = new Vector<Map<String, String>>();
				otaInvBlockListMap.put(rsvchanBlock.getChannelCode(),otaInvBlockList);
			}
				
		List<Map<String, String>> otaInvBlockList=otaInvBlockListMap.get(rsvchanBlock.getChannelCode());
		Map<String, String> otaBlockMap = new HashMap<String, String>();
		otaBlockMap.put("actionType", actionType);
		otaBlockMap.put("hotelCode",  rsvchanBlock.getHotelCode());
		otaBlockMap.put("chainCode", chainCode);
		otaBlockMap.put("invBlockCode", rsvchanBlock.getBlockCode());
		otaBlockMap.put("invBlockGroupingCode",
				rsvchanBlock.getBlockCode());
		otaBlockMap.put("startDate",
				DateUtil.convertDateToString(rsvchanBlock.getDate()));
		otaBlockMap.put("endDate",
				DateUtil.convertDateToString(rsvchanBlock.getDate()));
		if(rsvchanBlock.getCutOffDate()==null){
			otaBlockMap.put("cutOffDate",
					DateUtil.convertDateToString(rsvchanBlock.getDate()));
		}else{
			otaBlockMap.put("cutOffDate",
					DateUtil.convertDateToString(rsvchanBlock.getCutOffDate()));
		}
		otaBlockMap.put("roomTypeCode", rsvchanBlock.getType());
		otaBlockMap.put("numberOfUnits",
				String.valueOf(getInt(rsvchanBlock.getBlockNum())-getInt(rsvchanBlock.getBlockSold())));
		otaBlockMap.put("ratePlanCodes", rsvchanBlock.getRatePlanCodes());
	
		otaInvBlockList.add(otaBlockMap);
		}
		
		for(Map.Entry<String,List<Map<String, String>>> entry:otaInvBlockListMap.entrySet()){
			List<Map<String, String>> otaInvBlockList =entry.getValue();
			String channelCode=entry.getKey();
			Map<String,Object>blockParamMap=new HashMap<String,Object>();
			blockParamMap.put("oxiTrxId",oxiTrxId);
			blockParamMap.put("messageType",MessageType.ALLOTMENT);
			blockParamMap.put("action",actionType);
			blockParamMap.put("hotelCode",otaInvBlockList.get(0).get("hotelCode"));
			blockParamMap.put("chainCode",chainCode);
			blockParamMap.put("target",channelCode);
			blockParamMap.put("invBlockList",otaInvBlockList);
			sendBlockManager.done(blockParamMap);
		}
	}
	@Override
	public RsvchanBlock saveNewRsvchanBlock(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate,Integer cutOffDays,Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,String rsvtypeId,String rsvtypeChannelId,Boolean isSendToPMS){
		RsvchanBlock rsvchanBlock=new RsvchanBlock();
		rsvchanBlock.setRsvchanblockId(CommonUtil.generatePrimaryKey());
		rsvchanBlock.setRsvtypeChannelId(rsvtypeChannelId);
		rsvchanBlock.setRsvtypeId(rsvtypeId);
		rsvchanBlock.setChannelCode(channelCode);
		rsvchanBlock.setChannelId(channelId);
		rsvchanBlock.setType(roomType);
		rsvchanBlock.setBlockCode(invBlockGroupingCode);
		rsvchanBlock.setDate(inventoryDate);
		rsvchanBlock.setCutOffDate(cutOffDate);
		rsvchanBlock.setCutOffDays(cutOffDays);
		rsvchanBlock.setCreatedTime(new Date());
		rsvchanBlock.setBlockNum(allotmentQty);
		rsvchanBlock.setBlockSold(allotmentSold);
		rsvchanBlock.setRatePlanCodes(ratePlanCodes);
		rsvchanBlock.setHotelCode(hotelCode);
		rsvchanBlock.setIsSendToPMS(isSendToPMS);
		rsvchanBlockDao.saveRsvchanBlock(rsvchanBlock);
		return rsvchanBlock;
	}
	@Override
	public Integer	updateExistedRsvchanBlock(RsvchanBlock rsvchanBlock,Integer allotmentQty,Integer allotmentSold,Date cutOffDate,Integer cutOffDays,String ratePlanCodes,Boolean isSendToPMS){
		rsvchanBlock.setBlockNum(allotmentQty);
		rsvchanBlock.setBlockSold(allotmentSold);
		rsvchanBlock.setLastModifyTime(new Date());
		rsvchanBlock.setCutOffDate(cutOffDate);
		rsvchanBlock.setCutOffDays(cutOffDays);
		rsvchanBlock.setRatePlanCodes(ratePlanCodes);
		rsvchanBlock.setIsSendToPMS(isSendToPMS);
		return rsvchanBlockDao.updateRsvchanBlock(rsvchanBlock);
	}
	@Override
	public boolean removeRsvchanBlockByBlockCode(String hotelCode,String channelCode,
			Date inventoryDate, String roomType, String invBlockGroupingCode) {
		if (rsvchanBlockDao.removeRsvchanBlock(hotelCode,channelCode,
				inventoryDate, roomType, invBlockGroupingCode) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param hotelCode
	 * @param roomTypeCode
	 * @param channelCode
	 * @param checkinDate
	 * @param checkoutDate
	 * @param ratecode
	 * @return
	 * -1: make a booking failed, the cause is more than one block code
	 * 0:use freesell,the cause is the order either occupys block or freesell
	 * 1:use block avai ,the cause is just one block code
	 */
	private Integer validOrderIsInOneBlock(String hotelCode,String roomTypeCode,String channelCode,Date checkinDate,Date checkoutDate,String ratecode){
		List<Date> dateList = DateUtil.getDayList(checkinDate, DateUtil.addDays(checkoutDate, -1));
		List<String>dateStrList=new ArrayList<String>();
		for(Date d:dateList){
			dateStrList.add(DateUtil.convertDateToString(d));
		}
		List<RsvchanBlock>  rsvchanBlockList = rsvchanBlockDao.getRsvchanBlocksByOrder(hotelCode,channelCode, dateStrList, roomTypeCode);
		Set<String> blockSet=new HashSet<String> ();
		for(Date d:dateList){
			String blockCode=null;
		inner:for(int i=0;i<rsvchanBlockList.size();i++){
				//使用block code
				if(rsvchanBlockList.get(i).getDate().equals(d)){
					if(rsvchanBlockList.get(i).getRatePlanCodes()==null){
						blockCode =  rsvchanBlockList.get(i).getBlockCode();
						break inner;
					}else{
						//使用block code
						if(rsvchanBlockList.get(i).getRatePlanCodes().contains(ratecode)){
							blockCode = rsvchanBlockList.get(i).getBlockCode();
							break inner;
						}
					}
				}
			}
			if(blockCode!=null){
				blockSet.add(blockCode);
			}
		}
		if(blockSet.size()==0){
			log.info("订单占用了0的block code");
			return 0;
		}else if(blockSet.size()==1){
			log.info("订单占用了1的block code");
			return 1;
		}else {
			log.info("订单占用了2及以上的block code,block设置错误");
			return -1;
		}
	}
	
	private Boolean isProductionOpen(String hotelCode,String channelCode,Date checkDate,String roomTypeCode,String ratePlanCode){
		RestrictionCalc rc = new RestrictionCalc();
		rc.setChannelCode(channelCode);
		rc.setHotelCode(hotelCode);
		rc.setRoomTypeCode(roomTypeCode);
		rc.setRatePlanCode(ratePlanCode);
		rc.setDate(checkDate);
		rc.setOnOff(0);
		RestrictionCalc  restrictionCalc= restrictionCalcDao.getRestrictionCalc(rc);
		if(restrictionCalc==null){
			return true;
		}else{
			if(restrictionCalc.getOnOff()==0){
				return false;
			}else{
				return true;
			}
		}
	}
	
	private Boolean isDaysProductionOpen(String hotelCode,String channelCode,Date checkInDate,Date checkOutDate,String roomTypeCode,String ratePlanCode){
		RestrictionCalc rc = new RestrictionCalc();
		rc.setChannelCode(channelCode);
		rc.setHotelCode(hotelCode);
		rc.setRoomTypeCode(roomTypeCode);
		rc.setRatePlanCode(ratePlanCode);
		rc.setStartDate(DateUtil.getDate(checkInDate));
		rc.setEndDate(DateUtil.getDate(DateUtil.addDays(checkOutDate,-1)));
		rc.setOnOff(0);
		List<RestrictionCalc> rcList = restrictionCalcDao.getRestrictionCalcByObj(rc);
		if (rcList == null || rcList.isEmpty()) {
			return true;
		}else{
			for(RestrictionCalc restrictionCalc:rcList){
				if(restrictionCalc.getOnOff()==0){
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	//
	public Map<String,Map<Date,Integer>> getRoomTypeAllInventForWBE(String inventoryType,String hotelCode,String channelCode,String roomTypeCode, Date checkinDate, Date checkoutDate,List<String>rateplanCodeList){
		//get allot qty
			//free-key:rateCode
			Map<String,Map<Date,Integer>> dateAvailWithRateCodeMap=new HashMap<String,Map<Date,Integer>>();
			//allot-key:rateCode
			List<RsvtypeChannel> rcList = this.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelCode);
			Map<String,List<RsvchanBlock>> rsvchanBlocksMap =this.getBlockChannelAvailable(rcList);
			for(String rateplanCode:rateplanCodeList){
				if(inventoryType.equalsIgnoreCase(WbeCalendarRow.INVENT_FREESELL)){
					//free
					Map<Date,Integer> dateFreeSellAvailMap =getRoomTypeFSAvailForWBE(hotelCode,channelCode,roomTypeCode,checkinDate,checkoutDate,rateplanCode,rcList);
					dateAvailWithRateCodeMap.put(rateplanCode, dateFreeSellAvailMap);
				}else if(inventoryType.equalsIgnoreCase(WbeCalendarRow.INVENT_ALLOT)){	
					//allot
					Map<Date,Integer> dateAllotAvailMap =getRoomTypeAllotAvailForWBE(hotelCode,channelCode,roomTypeCode,checkinDate,checkoutDate,rateplanCode,rcList,rsvchanBlocksMap);
					dateAvailWithRateCodeMap.put(rateplanCode, dateAllotAvailMap);
				}
			}
		return dateAvailWithRateCodeMap;
	}
	
	private Map<Date,Integer> getRoomTypeFSAvailForWBE(String hotelCode, String channelCode,
			String roomTypeCode, Date checkinDate, Date checkoutDate,String rateplanCode,List<RsvtypeChannel> rcList){
		Map<Date,Integer> availWithDateMap=new HashMap<Date,Integer>();
		// 物理房量
		int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode);
		
		int dateDiff = DateUtil.dateDiff(checkinDate, checkoutDate);
		
		if (rcList == null || rcList.size() == 0) {
				if(physicalRoom>0&&isDaysProductionOpen(hotelCode,channelCode,checkinDate,checkoutDate,roomTypeCode,rateplanCode)){
					for (int i = 0; i < dateDiff; i++) {
						Date curDate = DateUtil.addDays(checkinDate, i);
						availWithDateMap.put(curDate, physicalRoom);
					}
				}
				return availWithDateMap;
		}
		
		for (int i = 0; i < dateDiff; i++) {
			Date nowDate = DateUtil.addDays(checkinDate, i);
			boolean flag = false; // 标识是否已找到 rsvtypeChannel记录
			boolean productionOpen=isProductionOpen(hotelCode,channelCode,nowDate,roomTypeCode,rateplanCode);
			for (RsvtypeChannel rc : rcList) {
				String dateStr = DateUtil.convertDateToString(rc.getDate());
				if (DateUtil.convertDateToString(nowDate).equals(dateStr)) {
					flag = true; // 已找到
					int available = 0;
				if(productionOpen){
					log.info("freeSell:" + rc.getFreeSell());						
					if (rc.getFreeSell() != null && !rc.getFreeSell()) {
						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = getObAvailable(rc);
						int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
						if (avaiOb > 0) {
							available += avaiOb;
						}
//						log.info(hotelCode + dateStr + ":ob可用房量:" + avaiOb);
					} else {
						// freeSell is opened
						//获得redis的已售出房量
						RedisRsvtype  findRedisRsvtype=new RedisRsvtype(hotelCode,roomTypeCode,rc.getDate(),new Integer(0));
						Map<String,Long>resultMap=roomQtyDao.readLongValueFromMap(findRedisRsvtype.getKey());
						Long redisSoldQty=resultMap.get(DateUtil.getDate(rc.getDate()))!=null?resultMap.get(DateUtil.getDate(rc.getDate())):0;
//						log.info(hotelCode + "日期:" + dateStr + ",rc.getAvailable:" + rc.getAvailable());
						// 如果可用房量为空,设置为物理放量
						if (rc.getAvailable() == null) {
							available += physicalRoom-redisSoldQty.intValue();
						} else {
							available += getInt(rc.getAvailable())-redisSoldQty.intValue();
						}
//						log.info(hotelCode + "available:" + available);

						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = getObAvailable(rc);

//						log.info(hotelCode + "日期:" + dateStr + ",obAvailable:" + obAvailable);
						int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
						if (avaiOb > 0) {
							available += avaiOb;
						}
					}
				}
					// 每日可用房量
					log.info(hotelCode + "日期:" + dateStr + ":总可用房量:" + available);
					if(available>0)
						availWithDateMap.put(nowDate, available);
				}
			}

			// 如果表中不存在rsvtypeChannel记录,则设置物理房量
			if (!flag) {
				if(productionOpen){
					if(physicalRoom>0)
						availWithDateMap.put(nowDate,physicalRoom);
				}
			}
		}
		return availWithDateMap;
	}
	//Map<currentDate,qty>
	private Map<Date,Integer> getRoomTypeAllotAvailForWBE(String hotelCode, String channelCode,
			String roomTypeCode, Date checkinDate, Date checkoutDate,String rateplanCode,List<RsvtypeChannel> rcList,Map<String,List<RsvchanBlock>> rsvchanBlocksMap) {
		// TODO Auto-generated method stub
		Date orderCreatedDate=DateUtil.currentDate();
		Map<Date,Integer> availWithDateMap=new HashMap<Date,Integer>();
		if (rcList == null || rcList.size() == 0) {
			return availWithDateMap;
		}
		Integer validBlockResult=validOrderIsInOneBlock(hotelCode,roomTypeCode,channelCode,checkinDate,checkoutDate,rateplanCode);
		if(validBlockResult==-1){
			return availWithDateMap;
		}
		int dateDiff = DateUtil.dateDiff(checkinDate, checkoutDate);
		for (int i = 0; i < dateDiff; i++) {
			Date nowDate = DateUtil.addDays(checkinDate, i);
			boolean productionOpen=isProductionOpen(hotelCode,channelCode,nowDate,roomTypeCode,rateplanCode);
			for (RsvtypeChannel rc : rcList) {
				String dateStr = DateUtil.convertDateToString(rc.getDate());
				if (DateUtil.convertDateToString(nowDate).equals(dateStr)) {
					int available = 0;
					int avaiAllotmentSold =0;
					if(getInt(rc.getHasBlock())==0){
						avaiAllotmentSold = getInt(rc.getAllotment()) - getInt(rc.getAllotmentSold());
						boolean isAllotmentRateCode=true;
						String allotmentRatecodes=rc.getRatePlanCodes();
						if(allotmentRatecodes!=null){
							if(!allotmentRatecodes.contains(rateplanCode)){
								avaiAllotmentSold=0;
								isAllotmentRateCode=false;
							}
						}
						if(isAllotmentRateCode){
								if(orderCreatedDate.after(rc.getCutOffDate()==null?rc.getDate():rc.getCutOffDate())==true){
									log.info(dateStr + "Allotment下单日期超出CutOffDate范围,allotment为0");
									avaiAllotmentSold=0;
							}
						}
						if(productionOpen==false)
							avaiAllotmentSold=0;
						log.info(dateStr + ":allotment可用房量:" + avaiAllotmentSold);
					}else{
						//block of pms
						List<RsvchanBlock> blookList=rsvchanBlocksMap.get(rc.getRsvtypeChannelId());
						if(blookList!=null){
							for(RsvchanBlock block:blookList){
								if(block.getRatePlanCodes()==null||block.getRatePlanCodes().trim().length()==0){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}else if(block.getRatePlanCodes().trim().contains(rateplanCode)){
									if(orderCreatedDate.after(block.getCutOffDate())==false){
										avaiAllotmentSold=getInt(block.getBlockNum())-getInt(block.getBlockSold());
										break;
									}
								}
							}
						}
						if(validBlockResult==0) avaiAllotmentSold=0;
						log.info(dateStr + ":block可用房量:" + avaiAllotmentSold);
					}
					if(avaiAllotmentSold > 0){
						available+=avaiAllotmentSold;
					}					

					// 每日可用房量
					log.info(hotelCode + "日期:" + dateStr + ":总可用房量:" + available);
					if(available>0)
						availWithDateMap.put(nowDate, available);
				}
			}
		}
		return availWithDateMap;
	}
	
	@Override
	public int getSendAvai(RsvtypeChannel rc){
		// 物理房量
		int physicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
		log.info("physicalRoom:" + physicalRoom);
		
		//获得redis的已售出房量
		RedisRsvtype  findRedisRsvtype=new RedisRsvtype( rc.getHotelCode(),rc.getType(),rc.getDate(),new Integer(0));
		Map<String,Long>resultMap=roomQtyDao.readLongValueFromMap(findRedisRsvtype.getKey());
		Long redisSoldQty=resultMap.get(DateUtil.getDate(rc.getDate()))!=null?resultMap.get(DateUtil.getDate(rc.getDate())):0;
		
		int hotelAvailable = 0;
		if (rc.getFreeSell() == null || rc.getFreeSell()) {
			log.info("rc.getAvailable:" + rc.getAvailable());
			if (rc.getAvailable() != null) {
				hotelAvailable = getInt(rc.getAvailable());
			}else{
				hotelAvailable = physicalRoom;
			}
		}
		int obAvailable = getObAvailable(rc);
		int avaiOb = obAvailable - getInt(rc.getTotalOBSold());
			int sendAvai = (avaiOb + hotelAvailable)-redisSoldQty.intValue() ;
			if (sendAvai < 0) sendAvai=0;
				return sendAvai;
	}
	
	private RsvtypeChannel buildUpdateRsvtypeChannel(RsvtypeChannel rc,int txAllotmentSold,int txSold,int txObSold) throws CloneNotSupportedException{
		RsvtypeChannel rc2=rc.clone();
		rc2.setAllotmentSold(txAllotmentSold>0?txAllotmentSold:null);
		rc2.setSold(txSold>0?txSold:null);
		rc2.setObSold(txObSold>0?txObSold:null);
		return rc2;
	}
	
}
