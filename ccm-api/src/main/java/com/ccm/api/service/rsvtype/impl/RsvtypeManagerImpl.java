package com.ccm.api.service.rsvtype.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.rsvtype.Rent;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.model.rsvtype.vo.RentSearchResult;
import com.ccm.api.model.rsvtype.vo.RoomStatusSetVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.rsvtype.vo.SearchRentCriteria;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.rsvtype.AsyncSendManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.service.rsvtype.RsvtypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("rsvtypeManager")
public class RsvtypeManagerImpl extends GenericManagerImpl<Rsvtype, String> implements RsvtypeManager {

	@Autowired
	private RsvtypeDao rsvtypeDao;
	@Autowired
    private RsvtypeChannelDao rsvtypeChannelDao;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private ChannelgoodsManager channelgoodsManager;
    @Resource
    private RoomJmsManager roomJmsManager;
    @Resource
	private AsyncSendManager asyncSendManager;
    @Resource
    private ChannelDao channelDao;
    @Resource
	private ChannelManager channelManager;
    @Resource
    private RsvchanBlockDao rsvchanBlockDao;
    
	@Resource
    public void setRsvtypeDao(RsvtypeDao rsvtypeDao) {
        this.dao = rsvtypeDao;
        this.rsvtypeDao = rsvtypeDao;
    }
	
	@Override
	public List<Rsvtype> roomNumCompare(List<String> hotelIds) {
		List<Rsvtype> list = new ArrayList<Rsvtype>();
		for (String hotelId : hotelIds) {
			List<Rsvtype> changeRoomNums = rsvtypeDao.getDate90RsvtypeListOfChange(hotelId, null, null);
			list.addAll(changeRoomNums);
		}
		return list;
	}

	public List<Rsvtype> getDate90RsvtypeList(List<String> hotelIds, String roomTypeCode, String sta) {
		List<Rsvtype> list = new ArrayList<Rsvtype>();
		List<Rsvtype> subList = new ArrayList<Rsvtype>();
		for (String hotelId : hotelIds) {
			subList = rsvtypeDao.getDate90RsvtypeList(hotelId, roomTypeCode, sta);
			list.addAll(subList);
		}
		return list;
	}

	@Override
	public void saveRsvtype(Rsvtype rt) {
		rsvtypeDao.saveRsvtype(rt);
	}
	
	@Override
	public void updateRsvtype(Rsvtype rt) {
		Rsvtype rsvtype = rsvtypeDao.getRsvtype(rt);
		if(rsvtype != null){
			rt.setRsvtypeId(rsvtype.getRsvtypeId());
			rsvtypeDao.updateRsvtype(rt);
		}
	}

	@Override
	public Rsvtype getRsvtype(Rsvtype rt) {
		return rsvtypeDao.getRsvtype(rt);
	}

	@Override
	public void saveSell(RoomStatusSetVO setvo,Boolean isPush) {
		Calendar cal = new GregorianCalendar();
		String roonTypeCodes = setvo.getRoonTypeCodes();
		String[] roonTypeCodeArray = roonTypeCodes.split(",");
		for (String roonTypeCode : roonTypeCodeArray) {
			String weeks = setvo.getWeeks();
			Date fromDate = setvo.getFromDate();
			Date toDate = setvo.getToDate();
			int dateLength = DateUtil.dateDiff(fromDate, toDate);

			for (int i = 0; i <= dateLength; i++) {
				cal.setTime(fromDate);
				cal.add(Calendar.DATE, i);
				// 判断当前日期是否有效，无效不保存。
				int week = cal.get(Calendar.DAY_OF_WEEK);
				if (weeks.indexOf(String.valueOf(week)) < 0) {
					continue;
				}
				
				RoomStatusVO r = new RoomStatusVO();
                r.setHotelCode(setvo.getHotelCode());
                r.setType(roonTypeCode);
                r.setDate(cal.getTime());
                r.setOverBooking(setvo.getOverBooking());
                r.setChannelId(setvo.getChannelId());
                r.setFreeSell(setvo.getFreeSell());
                r.setAllotment(setvo.getAllotment());
				rsvtypeChannelManager.setAllotmentAndFreeSell(r,isPush);
			}
		}
	}

    @Override
    public List<Rsvtype> searchResvType(Rsvtype rsv) {
        return rsvtypeDao.searchResvType(rsv);
    }

    @Override
    public void setOverBooking(RoomStatusVO vo, Boolean isPush) {
        Rsvtype rsvtype = rsvtypeDao.getRsvtype(vo);
        if(null!=rsvtype){
            rsvtype.setOverBooking(vo.getOverBooking());
            vo.setRsvtypeId(rsvtype.getRsvtypeId());
            rsvtypeDao.updateRsvtype(rsvtype);
        }else{
        	rsvtype = new Rsvtype();
        	rsvtype.setHotelCode(vo.getHotelCode());
        	rsvtype.setDate(vo.getDate());
        	rsvtype.setType(vo.getType());
        	rsvtype.setOverBooking(vo.getOverBooking());
        	rsvtype.setPhysicalRooms(0);
        	rsvtype.setRoomTypeOverbook(0);
        	rsvtype.setOutoforder(0);
        	rsvtype.setAdultsInHouse(0);
        	rsvtype.setChildrenInHouse(0);
        	rsvtype.setArrivalRooms(0);
        	rsvtype.setDepartureRooms(0);
        	rsvtype.setBlockCount(0);
        	rsvtype.setResvCount(0);
        	rsvtype.setHouseOverbook(0);
            rsvtype.setRsvtypeId(UUID.randomUUID().toString().replace("-", ""));
            rsvtypeDao.save(rsvtype);
        }
        
        try {
        	if(isPush){
                final Rsvtype tmpRsvtype = new Rsvtype();
            
                BeanUtils.copyProperties(rsvtype, tmpRsvtype);
            	Thread t = new Thread(new Runnable() {
        			public void run() {
        				sendChannelObAvailable(tmpRsvtype);
        			}
        		});
        		t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sendChannelObAvailable(Rsvtype rsvtype){
    	Hotel h = null;
        if(CommonUtil.isNotEmpty(rsvtype.getHotelid())){
            h = hotelDao.getHotel(rsvtype.getHotelid());
        }else{
        	List<Hotel> hlist = hotelDao.getHotelByHotelCode(rsvtype.getHotelCode());
        	if(CommonUtil.isNotEmpty(hlist)){
        		h = hlist.get(0);
        	}else{
        		log.warn("hotel is null");
        	}
        }
        ChannelGoodsVO cgvo = new ChannelGoodsVO();
        cgvo.setRoomTypeCode(rsvtype.getType());
        cgvo.setHotelId(h.getHotelId());
System.out.println("start getEnabledChannelGoods "+new Date().toLocaleString());        
        List<ChannelGoodsVO> crpList  = channelgoodsManager.getEnabledChannelGoods(cgvo);
System.out.println("end getEnabledChannelGoods "+new Date().toLocaleString());        
        if (CommonUtil.isEmpty(crpList)) {
            crpList = new ArrayList<ChannelGoodsVO>();
            log.error("该房型没有对应的渠道");
        }
        
        HashMap<String, Object> saveChannelMap = new HashMap<String, Object>();
        for (ChannelGoodsVO channelGoodsVO : crpList) { // 渠道
            String roomAvaiKey = channelGoodsVO.getChannelCode()+h.getHotelId()+rsvtype.getType();
            
            if(saveChannelMap.containsKey(roomAvaiKey)){ continue;}
            RsvtypeChannel rsvtypeChannel = rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(rsvtype.getRsvtypeId(), channelGoodsVO.getChannelId());
            if(rsvtypeChannel==null){
            	rsvtypeChannel = new RsvtypeChannel();
            }
          //OverBooking数量修改 发送房量消息逻辑
            try {
                rsvtypeChannel.setChannelId(channelGoodsVO.getChannelId());
                rsvtypeChannel.setTotalOBSold(rsvtype.getTotalOBSold());
                rsvtypeChannel.setAvailable(rsvtype.getAvailable());
                rsvtypeChannel.setHotelCode(h.getHotelCode());
                rsvtypeChannel.setOverBooking(rsvtype.getOverBooking());
                rsvtypeChannel.setType(rsvtype.getType());
                int avai = rsvtypeChannelManager.calcSendChannelAvailable(rsvtypeChannel);
                rsvtype.setChannel(channelGoodsVO.getChannelCode());
                rsvtype.setSendAvailable(avai);
                rsvtype.setInvSnapInvoke(true);	//无需再次查找渠道
				roomJmsManager.sendRoomStatusMsgToJms(rsvtype,false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveChannelMap.put(roomAvaiKey, null);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void setBatchOverBooking(List<String> daysList,RoomStatusSetVO setvo,Boolean isPush) throws Exception {
        String roonTypeCodes = setvo.getRoonTypeCodes();
        String[] roonTypeCodeArray = roonTypeCodes.split(",");
        
        List<Rsvtype> addList = new ArrayList<Rsvtype>();
        List<Rsvtype> updateList = new ArrayList<Rsvtype>();
        for (String roonTypeCode : roonTypeCodeArray) {
            for (int i = 0; i < daysList.size(); i++) {
                RoomStatusVO r = new RoomStatusVO();
                r.setHotelCode(setvo.getHotelCode());
                r.setType(roonTypeCode);
                r.setDate(DateUtil.convertStringToDate(daysList.get(i)));
                r.setOverBooking(setvo.getOverBooking());
                
                Rsvtype rsvtype = rsvtypeDao.getRsvtype(r);
                if(null!=rsvtype){
                    rsvtype.setOverBooking(r.getOverBooking());
                    updateList.add(rsvtype);
                }else{
                	r.setRsvtypeId(UUID.randomUUID().toString().replace("-", ""));
                	addList.add(r);
                }
            }
        }
        List<List> addLists = CommonUtil.splitList(addList, 800);
        for (List list : addLists) {
        	rsvtypeDao.addBatchRsvtypes(list);
		}
        List<List> updateLists = CommonUtil.splitList(updateList, 800);
        for (List list : updateLists) {
        	rsvtypeDao.updateBatchRsvtypes(list);
		}
        
        if(isPush){
        	//推送逻辑
        	addList.addAll(updateList);
        	final List<Rsvtype> list = new ArrayList<Rsvtype>();
        	list.addAll(addList);
        	Thread t = new Thread(new Runnable() {
    			public void run() {
    				for (Rsvtype rsvtype : list) {
    					sendChannelObAvailable(rsvtype);
					}
    			}
    		});
    		t.start();
        }
    }
    
    @Override
    public void setBatchOverBooking(RoomStatusSetVO setvo,Boolean isPush) {
        Calendar cal = new GregorianCalendar();
        String roonTypeCodes = setvo.getRoonTypeCodes();
        String[] roonTypeCodeArray = roonTypeCodes.split(",");
        for (String roonTypeCode : roonTypeCodeArray) {
            String weeks = setvo.getWeeks();
            Date fromDate = setvo.getFromDate();
            Date toDate = setvo.getToDate();
            int dateLength = DateUtil.dateDiff(fromDate, toDate);

            for (int i = 0; i <= dateLength; i++) {
                cal.setTime(fromDate);
                cal.add(Calendar.DATE, i);
                // 判断当前日期是否有效，无效不保存。
                int week = cal.get(Calendar.DAY_OF_WEEK);
                if (weeks.indexOf(String.valueOf(week)) < 0) {
                    continue;
                }
                RoomStatusVO r = new RoomStatusVO();
                r.setHotelCode(setvo.getHotelCode());
                r.setType(roonTypeCode);
                r.setDate(cal.getTime());
                r.setOverBooking(setvo.getOverBooking());
                setOverBooking(r,isPush);
            }
        }
    }

	@Override
	public void addBatchRsvtypes(List<Rsvtype> rsvList) {
		rsvtypeDao.addBatchRsvtypes(rsvList);
	}

	@Override
	public void deleteBatchRsvtypes(List<Rsvtype> rsvList) {
		rsvtypeDao.deleteBatchRsvtypes(rsvList);
	}
	
	@Override
	public void updateBatchRsvtypes(List<Rsvtype> rsvList) {
		rsvtypeDao.updateBatchRsvtypes(rsvList);
	}

	@Override
	public List<Rsvtype> getRsvtypeByDateSpan(String hotelCode, String[] arrayOfRoom, Date dateStart,Date dateEnd) {
		return rsvtypeDao.getRsvtypeByDateSpan(hotelCode, arrayOfRoom, dateStart, dateEnd);
	}
	
	@Override
	public void removeBatchSell(List<String> dayList, RoomStatusSetVO setvo,
			boolean isPush) throws Exception{
		List<RsvtypeChannel> updateRsvtypeChannelList = new ArrayList<RsvtypeChannel>();
		String roonTypeCodes = setvo.getRoonTypeCodes();
		String[] roonTypeCodeArray = roonTypeCodes.split(",");
		String channelIds = setvo.getChannelIds();
		String[] channelIdArray = channelIds.split(",");
		List<RsvchanBlock> rsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		for (String roomTypeCode : roonTypeCodeArray) {
			for (int i = 0; i < dayList.size(); i++) {
				RoomStatusVO r = new RoomStatusVO();
				r.setHotelCode(setvo.getHotelCode());
				r.setType(roomTypeCode);
				r.setDate(DateUtil.convertStringToDate(dayList.get(i)));
				Rsvtype rsvtype = rsvtypeDao.getRsvtype(r);
				if(rsvtype!=null){
					for(String channelId:channelIdArray){
						RsvtypeChannel rsvtypeChannel = rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(
								rsvtype.getRsvtypeId(), channelId);
						if(rsvtypeChannel!=null){
							rsvtypeChannel.setOverBooking(rsvtype.getOverBooking());
			        		rsvtypeChannel.setHotelCode(r.getHotelCode());
			        		rsvtypeChannel.setType(rsvtype.getType());
			        		rsvtypeChannel.setAvailable(rsvtype.getAvailable());
			        		rsvtypeChannel.setTotalOBSold(rsvtype.getTotalOBSold());
			        		rsvtypeChannel.setDate(rsvtype.getDate());
							rsvtypeChannel.setAllotment(null);
							rsvtypeChannel.setAllotmentSold(null);
							rsvtypeChannel.setCutOffDays(null);
							rsvtypeChannel.setRatePlanCodes(null);
							List<RsvchanBlock> rsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
							if(setvo.getBlockCode().trim().length()>0){
								RsvchanBlock rsvchanBlock=rsvchanBlockDao.getBlockListByRsvChanIdAndBlockCode(rsvtypeChannel.getRsvtypeChannelId(),setvo.getBlockCode().trim());
								if(rsvchanBlock!=null){
									if(rsvchanBlock.getRatePlanCodes()!=null){
										rsvchanBlock.setBlockNum(0);
										rsvchanBlock.setBlockSold(0);
										rsvchanBlockWithRatesList.add(rsvchanBlock);
									}
								}
									rsvchanBlockDao.removeRsvchanBlockByRsvchannelIdAndBlockCode(rsvtypeChannel.getRsvtypeChannelId(),setvo.getBlockCode().trim());
									if(rsvchanBlockList.size()>1){
										rsvtypeChannel.setHasBlock(1);
									}else{
										rsvtypeChannel.setHasBlock(0);
									}
							}else{//removeAll
									for( RsvchanBlock rsvchanBlock:rsvchanBlockList){
										if(rsvchanBlock.getRatePlanCodes()!=null){
											rsvchanBlock.setBlockNum(0);
											rsvchanBlock.setBlockSold(0);
											rsvchanBlockWithRatesList.add(rsvchanBlock);
										}
									}
									rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
									rsvtypeChannel.setHasBlock(0);
								}
							updateRsvtypeChannelList.add(rsvtypeChannel);
							}
						}
					}
				}
			}
			int num=800;
		 	long t2=System.currentTimeMillis();
	        List<List> updateRsvtypeChannelLists = CommonUtil.splitList(updateRsvtypeChannelList, num);
	        for (List list : updateRsvtypeChannelLists) {
	        	rsvtypeChannelDao.updateBatchRsvtypeChannel(list);
			}
	        System.out.println("updateRsvtypeChannelList 这段代码运行了:"+ (System.currentTimeMillis()-t2)/1000.0 + "秒！"+"一共  "+updateRsvtypeChannelList.size()+" 条");
	
	        if(isPush){
		        final Map<String, Channel> channelMap = new HashMap<String, Channel>();
	        	for (String channelId : channelIdArray) {
	        		Channel channel = channelManager.get(channelId);
	        		channelMap.put(channelId, channel);
				}
	        	
		        final List<RsvtypeChannel> list = new ArrayList<RsvtypeChannel>();
	            list.addAll(updateRsvtypeChannelList);
	            Thread thread1 = new Thread(new Runnable() {
	        		public void run() {
	        			for (RsvtypeChannel rsvtypeChannel : list) {
	        				Channel channel = channelMap.get(rsvtypeChannel.getChannelId());
	        				if(channel.isPush(rsvtypeChannel.getDate())){
	        						rsvtypeChannelManager.sendChangeSellDataMsg(rsvtypeChannel);
	        	            }
	    				}
	        		}
	        	});
	            thread1.start();
	            
	          //send ota block xml to switch
	            final List<RsvchanBlock> finalRsvchanBlockWithRatesList=rsvchanBlockWithRatesList;
	            if(finalRsvchanBlockWithRatesList.size()>0){
		            Thread thread2 = new Thread(new Runnable() {
		        		public void run() {
		        			rsvtypeChannelManager.handleOTABlocks(null,"DELETE",finalRsvchanBlockWithRatesList);
		        		}
		        	});
		            thread2.start();
	            }
            }
	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setBatchSaveSell(List<String> dayList, RoomStatusSetVO setvo,
			boolean isPush) throws Exception {
		String roonTypeCodes = setvo.getRoonTypeCodes();
		String[] roonTypeCodeArray = roonTypeCodes.split(",");
		String channelIds = setvo.getChannelIds();
		String[] channelIdArray = channelIds.split(",");
		List<Rsvtype> addRsvtypeList = new ArrayList<Rsvtype>();
		List<RsvtypeChannel> addRsvtypeChannelList = new ArrayList<RsvtypeChannel>();
		List<RsvtypeChannel> updateRsvtypeChannelList = new ArrayList<RsvtypeChannel>();
		List<RsvchanBlock> addRsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		List<RsvchanBlock> removedRsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
		Integer cutOffDays=setvo.getCutOffDays();
		if(cutOffDays==null){
			cutOffDays=0;
			}
		for (String roomTypeCode : roonTypeCodeArray) {
			for (int i = 0; i < dayList.size(); i++) {
				RoomStatusVO r = new RoomStatusVO();
				r.setHotelCode(setvo.getHotelCode());
				r.setType(roomTypeCode);
				r.setDate(DateUtil.convertStringToDate(dayList.get(i)));
				//r.setChannelId(setvo.getChannelId());
				r.setFreeSell(setvo.getFreeSell());
				r.setAllotment(setvo.getAllotment());
				Rsvtype rsvtype = rsvtypeDao.getRsvtype(r);
				boolean flag = true;  //标识存在该房量记录
				//如果没有找到rsvtype对象
				if(rsvtype == null){
					rsvtype = new Rsvtype();
					rsvtype.setRsvtypeId(CommonUtil.generatePrimaryKey());
					rsvtype.setHotelCode(setvo.getHotelCode());
					rsvtype.setType(roomTypeCode);
					rsvtype.setDate(r.getDate());
					addRsvtypeList.add(rsvtype);
					
					flag = false; //标识未找到房量记录
				}
				
				for (String channelId : channelIdArray) {
					RsvtypeChannel rsvtypeChannel = null;
					//如果存在该房量记录,需要查询房量渠道是否存在
					if(flag){
						rsvtypeChannel = rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(
								rsvtype.getRsvtypeId(), channelId);
					}
					
					//如果房量记录不存在或者未查到房量渠道记录
					if(!flag || rsvtypeChannel == null){
						rsvtypeChannel = new RsvtypeChannel();
						rsvtypeChannel.setHasBlock(0);
						rsvtypeChannel.setRsvtypeChannelId(CommonUtil.generatePrimaryKey());
						rsvtypeChannel.setChannelId(channelId);
						rsvtypeChannel.setRsvtypeId(rsvtype.getRsvtypeId());
						addRsvtypeChannelList.add(rsvtypeChannel);
					}else{
						updateRsvtypeChannelList.add(rsvtypeChannel);
					}
					rsvtypeChannel.setOverBooking(rsvtype.getOverBooking());
	        		rsvtypeChannel.setHotelCode(r.getHotelCode());
	        		rsvtypeChannel.setType(rsvtype.getType());
	        		rsvtypeChannel.setAvailable(rsvtype.getAvailable());
	        		rsvtypeChannel.setTotalOBSold(rsvtype.getTotalOBSold());
	        		rsvtypeChannel.setDate(rsvtype.getDate());
	        		
	        		if (r.getFreeSell() != null) {
						rsvtypeChannel.setFreeSell(r.getFreeSell());
					}
	        		
	        		if(setvo.getBlockCode()==null||setvo.getBlockCode().trim().length()==0){
						//allotment	part
					//设置保留房和开关
					if (r.getAllotment() != null) {
						rsvtypeChannel.setHasBlock(0);
						rsvtypeChannel.setAllotment(r.getAllotment());
						Date cutOffDate=DateUtil.addDays(rsvtype.getDate(), -cutOffDays);
		        		rsvtypeChannel.setCutOffDate(cutOffDate);
		        		rsvtypeChannel.setCutOffDays(cutOffDays);
		        		rsvtypeChannel.setRatePlanCodes(setvo.getRateCodesMap().get(rsvtype.getType()));
				
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
					}else{
						if (r.getAllotment() != null) {
						//block part
						//如果房量记录不存在或者未查到房量渠道记录
						Channel channel = channelDao.get(channelId);
						Date cutOffDate=DateUtil.addDays(rsvtype.getDate(), -cutOffDays);
						String ratePlanCodes=setvo.getRateCodesMap().get(rsvtype.getType());
						
						rsvtypeChannel.setAllotment(null);
						rsvtypeChannel.setAllotmentSold(null);
						rsvtypeChannel.setHasBlock(1);
						rsvtypeChannel.setCutOffDays(null);
						rsvtypeChannel.setRatePlanCodes(null);
						
						RsvchanBlock rsvchanBlock=rsvchanBlockDao.getBlockListByRsvChanIdAndBlockCode(rsvtypeChannel.getRsvtypeChannelId(), setvo.getBlockCode());
						if(rsvchanBlock!=null){
							//last has rate,now has not rate
							if((rsvchanBlock.getRatePlanCodes()==null||rsvchanBlock.getRatePlanCodes().trim().length()==0)==false){
								if(ratePlanCodes==null||ratePlanCodes.trim().length()==0){
									RsvchanBlock removeRB=rsvchanBlock.clone();
									removeRB.setBlockNum(0);
									removeRB.setBlockSold(0);
									removedRsvchanBlockWithRatesList.add(removeRB);
								}
							}
							rsvtypeChannelManager.updateExistedRsvchanBlock(rsvchanBlock,setvo.getAllotment(),rsvchanBlock.getBlockSold(),cutOffDate,cutOffDays,ratePlanCodes,setvo.getIsSendToPMS());
						}else{
							if(ratePlanCodes==null||ratePlanCodes.trim().length()==0){
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
							}else if(rsvchanBlockDao.getBlockCountWithoutRates(rsvtypeChannel.getRsvtypeChannelId())>0){
								rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
							}
						rsvchanBlock=rsvtypeChannelManager.saveNewRsvchanBlock(channel.getChannelId(),channel.getChannelCode(),setvo.getBlockCode().trim(),setvo.getHotelCode(),roomTypeCode,rsvtype.getDate(),cutOffDays,
								setvo.getAllotment(),0,cutOffDate, ratePlanCodes,rsvtype.getRsvtypeId(), rsvtypeChannel.getRsvtypeChannelId(),setvo.getIsSendToPMS());
						}
						if(!(ratePlanCodes==null||ratePlanCodes.trim().length()==0)){
							addRsvchanBlockWithRatesList.add(rsvchanBlock);
						}
					}
					}
				}
			}
		}
		
		int num=800;
		List<List> addLists = CommonUtil.splitList(addRsvtypeList, num);
        for (List list : addLists) {
        	rsvtypeDao.addBatchRsvtypes(list);
		}
        
        List<List> addRsvtypeChannelLists = CommonUtil.splitList(addRsvtypeChannelList, num);
        long t1=System.currentTimeMillis();
        for (List list : addRsvtypeChannelLists) {
        	rsvtypeChannelDao.addBatchRsvtypeChannel(list);
		}
        System.out.println("addRsvtypeChannelList 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"一共  "+addRsvtypeChannelList.size()+" 条");
        
        long t2=System.currentTimeMillis();
        List<List> updateRsvtypeChannelLists = CommonUtil.splitList(updateRsvtypeChannelList, num);
        for (List list : updateRsvtypeChannelLists) {
        	rsvtypeChannelDao.updateBatchRsvtypeChannel(list);
		}
        System.out.println("updateRsvtypeChannelList 这段代码运行了:"+ (System.currentTimeMillis()-t2)/1000.0 + "秒！"+"一共  "+updateRsvtypeChannelList.size()+" 条");
        
        if(isPush){
        	final Map<String, Channel> channelMap = new HashMap<String, Channel>();
        	for (String channelId : channelIdArray) {
        		Channel channel = channelManager.get(channelId);
        		channelMap.put(channelId, channel);
			}
        	
        	addRsvtypeChannelList.addAll(updateRsvtypeChannelList);
            final List<RsvtypeChannel> list = new ArrayList<RsvtypeChannel>();
            list.addAll(addRsvtypeChannelList);
            Thread thread1 = new Thread(new Runnable() {
        		public void run() {
        			for (RsvtypeChannel rsvtypeChannel : list) {
        				Channel channel = channelMap.get(rsvtypeChannel.getChannelId());
        				if(channel.isPush(rsvtypeChannel.getDate())){
        						rsvtypeChannelManager.sendChangeSellDataMsg(rsvtypeChannel);
        	            }
    				}
        		}
        	});
            thread1.start();
        	//send new ota block xml to switch
            final List<RsvchanBlock> finalAddRsvchanBlockWithRatesList=addRsvchanBlockWithRatesList;
            if(finalAddRsvchanBlockWithRatesList.size()>0){
	            Thread thread2 = new Thread(new Runnable() {
	        		public void run() {
	        			rsvtypeChannelManager.handleOTABlocks(null,"NEW",finalAddRsvchanBlockWithRatesList);
	        		}
	        	});
	            thread2.start();
            }
            
          //send cancel ota block xml to switch
            final List<RsvchanBlock> finalRemovedRsvchanBlockWithRatesList=removedRsvchanBlockWithRatesList;
            if(finalRemovedRsvchanBlockWithRatesList.size()>0){
	            Thread thread3 = new Thread(new Runnable() {
	        		public void run() {
	        			rsvtypeChannelManager.handleOTABlocks(null,"DELETE",finalRemovedRsvchanBlockWithRatesList);
	        		}
	        	});
	            thread3.start();
            }
        }
	}

	@Override
	public RentSearchResult searchHotelRoomRentRates(SearchRentCriteria criteria) {
		RentSearchResult result = rsvtypeDao.searchHotelRoomRentRates(criteria);
		if(result.getResultList() != null && result.getResultList().size() > 0){
			for (Rent rent : result.getResultList()) {
				if(rent.getRentRate() == null){
					rent.setRentRate(new BigDecimal(0.00));
				}else{
					rent.setRentRate(rent.getRentRate().setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		return result;
		
	}

	@Override
	public List<RateCodeWithRoomVO> getRateCodeFromRoomType(String hotelCode,
			String startDate, String endDate, List<String> roomCodeList,String weekRange) {
		// TODO Auto-generated method stub
		//json={"roomTypeCode1":["rateCode1","rateCode2"]};
		
		return rsvtypeDao.getRateCodeFromRoomType(hotelCode, startDate, endDate, roomCodeList,weekRange);
	}
	
	private int getInt(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}
}
