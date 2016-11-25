package com.ccm.api.service.ratePlan.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.common.ConstantDao;
import com.ccm.api.dao.common.PessimisticLockDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.PriceCalcDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.PessimisticLock;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.ratePlan.vo.ChannelRateplanVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("priceCalcManager")
public class PriceCalcManagerImpl extends GenericManagerImpl<PriceCalc, String> implements PriceCalcManager {
    
    private Log log = LogFactory.getLog(PriceCalcManagerImpl.class);
    
    @Resource
    private PriceCalcDao priceCalcDao;
    @Resource
    private RestrictionCalcDao restrictionCalcDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private RoomTypeManager roomTypeManager;
    @Resource
    private RatePlanManager ratePlanManager;
    @Resource
    private ChannelgoodsManager channelgoodsManager;
    @Resource
    private ChannelManager channelManager;
    @Resource
	private PessimisticLockDao pessimisticLockDao;
    @Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
    
    @Resource
	private ConstantDao constantDao;
    @Resource
	private ChainDao chainDao;
    @Resource
	private RateDetailVODaoMongo rateDetailVODaoMongo;
    
    @Autowired
    public PriceCalcManagerImpl(PriceCalcDao genericDao) {
        super(genericDao);
    }
    private Map<String,Channel> getChannelMap(){
    	Map<String,Channel> channelMap= new HashMap<String,Channel>();
    	List<Channel> channelList = channelManager.getAll();
    	for (Channel channel : channelList) {
    		channelMap.put(channel.getChannelCode(), channel);
		}
    	return channelMap;
    }
    @Override
    /**添加价格日，isSync 为true 为同步保存，false为子线程保存**/
    public void addPriceCalc(RatePlanVO ratePlanVO,boolean isSync) throws Exception {
    	long startMili=System.currentTimeMillis();
        Map<String,Channel> channelMap = getChannelMap();
        
        List<PriceCalc> pcAddList = new ArrayList<PriceCalc>();
        List<PriceCalc> pcSendList = new ArrayList<PriceCalc>();
        HotelVO hv = null;
        //如果当前用户已登陆
        if(SecurityHolder.getB2BUser()!=null){
        	hv = SecurityHolder.getB2BUser().getHotelvo();
        }else{
        	hv = hotelDao.getHotelChainByHotelId(ratePlanVO.getRp().getHotelId());
        }
        
        Rateplan rp = ratePlanVO.getRp();
        List<RateDetailVO> rateDetailVOList = ratePlanVO.getRateDetailVOList();
        for (RateDetailVO rateDetailVO : rateDetailVOList) {
        	
            List<RoomRateVO> roomRateList = rateDetailVO.getRoomRateList();
            for (RoomRateVO rv : roomRateList) {
            	if(rv == null){
            		continue;
            	}
                String roomTypeId = rv.getRoomTypeId();
                RoomTypeVO  roomTypeVODB = roomTypeManager.getRoomTypeById(roomTypeId);
                if(roomTypeVODB==null){
                	continue;
                }
                String roomTypeCode = roomTypeVODB.getRoomTypeCode();

                List<String> dayList = DateUtil.getWeekDay(rateDetailVO.getEffectiveDate(),rateDetailVO.getExpireDate(), getWeek(rateDetailVO));
                
                ChannelGoodsVO cgvo = new ChannelGoodsVO();
                cgvo.setHotelId(hv.getHotelId());
                cgvo.setRatePlanCode(ratePlanVO.getRp().getRatePlanCode());
                cgvo.setRoomTypeCode(roomTypeCode);
                List<ChannelGoodsVO> crpList = channelgoodsManager.getEnabledChannelGoods2(cgvo);//所有绑定状态
                if (CommonUtil.isEmpty(crpList)) {
                    crpList = new ArrayList<ChannelGoodsVO>();
                    log.debug("该房价没有对应的渠道");
                }
                for (ChannelGoodsVO channelGoodsVO : crpList) { // 渠道

                    for (String date : dayList) {
                        List<RateAmount> extList = new ArrayList<RateAmount>();
                        if(CommonUtil.isNotEmpty(rateDetailVO.getExtraBed())){
                            RateAmount rateAmount1 = new RateAmount();
                            rateAmount1.setNumberOfUnits(101);   //成人
                            rateAmount1.setBaseAmount(rateDetailVO.getExtraBed());
                            extList.add(rateAmount1);
                        }
                        if(CommonUtil.isNotEmpty(rateDetailVO.getExtraBedChild())){
                            RateAmount rateAmount2 = new RateAmount();
                            rateAmount2.setNumberOfUnits(102);   //小孩
                            rateAmount2.setBaseAmount(rateDetailVO.getExtraBedChild());
                            extList.add(rateAmount2);
                        }
                        List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
                        PriceCalc pc = null;
                        extList.addAll(0,rateAmountList);
                        for (RateAmount rateAmount : extList) { // 人数
                            pc = new PriceCalc();
                            pc.setChannelCode(channelGoodsVO.getChannelCode());
                            pc.setChainCode(hv.getChainCode());
                            pc.setHotelCode(hv.getHotelCode());
                            pc.setRoomTypeCode(roomTypeCode);
                            pc.setRatePlanCode(ratePlanVO.getRp().getRatePlanCode());
                            pc.setAmount(rateAmount.getBaseAmount());
                            pc.setNumberOfUnits(rateAmount.getNumberOfUnits());
                            pc.setDate(date);
                            pc.setStartDate(date);
                            pc.setEndDate(date);
                            pc.setRateDetailId(rateDetailVO.getRateDetailId());
                        	pc.setPriceCalcId(UUID.randomUUID().toString().replaceAll("-", ""));
                        	pc.setCurrencyCode(hv.getCurrencyCode());
                        	pcAddList.add(pc);
                        }
                        
                        //添加房价发送list
                        if(pc != null && ChannelGoodsStatus.publish.equals(channelGoodsVO.getStatus())){
                        	if(!DateUtil.convertStringToDate(pc.getStartDate()).before(DateUtil.cleanTimeDate(new Date()))){
	                            Channel channel = channelMap.get(channelGoodsVO.getChannelCode());
	                            if(channel!=null && channel.getPushRate() != null && channel.getPushRate() && channel.isPush(DateUtil.convertStringToDate(date))){
	                                pc.setRateAmountList(extList);
	                                pcSendList.add(pc);
	                            }
                        	}
                        }
                    }
                }
            }
        }
        long endMili=System.currentTimeMillis();
        System.out.println(rp.getRatePlanCode()+" 构造日历总耗时为："+ (endMili-startMili)/1000.0 +"秒");
        
        //保存房价
        if(isSync){
        	syncBatchSave(pcAddList, pcSendList, ratePlanVO);
        }else{
        	batchSave(pcAddList,pcSendList,ratePlanVO);
        }
    }
    @Override
    public List<PriceCalc> getPriceCalcListByDetail(List<RateDetailVO> rateDetailVOList,RatePlanVO ratePlanVO) throws Exception{
    	HotelVO hv = null;
        //如果当前用户已登陆
        if(SecurityHolder.getB2BUser()!=null){
        	hv = SecurityHolder.getB2BUser().getHotelvo();
        }else{
        	hv = hotelDao.getHotelChainByHotelId(ratePlanVO.getRp().getHotelId());
        }
        
        List<PriceCalc> pcList = new ArrayList<PriceCalc>();
        //存储有效的roomcode
        HashMap<String,String> roomCodeMap = new HashMap<String, String>();
        for (RateDetailVO rateDetailVO : rateDetailVOList) {
        	
            List<RoomRateVO> roomRateList = rateDetailVO.getRoomRateList();
            for (RoomRateVO rv : roomRateList) {
            	if(rv == null){
            		continue;
            	}
                String roomTypeId = rv.getRoomTypeId();
                
                String roomTypeCode = roomCodeMap.get(roomTypeId);
                if(CommonUtil.isEmpty(roomTypeCode)){
                	RoomType rt = roomTypeManager.getRoomTypeById(roomTypeId);
                	if(rt == null){
                		continue;
                	}
                	roomTypeCode = rt.getRoomTypeCode();
                	roomCodeMap.put(roomTypeId, roomTypeCode);
                }
                //获取符合星期选项的日期
                List<String> dayList = DateUtil.getWeekDay(rateDetailVO.getEffectiveDate(),rateDetailVO.getExpireDate(), getWeek(rateDetailVO));
                //循环日期范围内有效日期
                for (String date : dayList) {
                	Date date1 = DateUtil.convertStringToDate(date);
                	if(date1.before(DateUtil.getCleanDate(new Date()))){
                		//当天之前放弃
                		continue;
                	}
                    List<RateAmount> extList = new ArrayList<RateAmount>();
                    if(CommonUtil.isNotEmpty(rateDetailVO.getExtraBed())){
                        RateAmount rateAmount1 = new RateAmount();
                        rateAmount1.setNumberOfUnits(101);   //成人
                        rateAmount1.setBaseAmount(rateDetailVO.getExtraBed());
                        extList.add(rateAmount1);
                    }
                    if(CommonUtil.isNotEmpty(rateDetailVO.getExtraBedChild())){
                        RateAmount rateAmount2 = new RateAmount();
                        rateAmount2.setNumberOfUnits(102);   //小孩
                        rateAmount2.setBaseAmount(rateDetailVO.getExtraBedChild());
                        extList.add(rateAmount2);
                    }
                    List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
                    //先添加1-5人价格，加床和小孩
                    extList.addAll(0,rateAmountList);
                    PriceCalc pc  = new PriceCalc();
                    pc.setRateAmountList(extList);
                    pc.setChainCode(hv.getChainCode());
                    pc.setHotelCode(hv.getHotelCode());
                    pc.setRoomTypeCode(roomTypeCode);
                    pc.setRatePlanCode(ratePlanVO.getRp().getRatePlanCode());
                    pc.setDate(date);
                    pc.setStartDate(date);
                    pc.setEndDate(date);
                    pc.setRateDetailId(rateDetailVO.getRateDetailId());
                	pc.setPriceCalcId(UUID.randomUUID().toString().replaceAll("-", ""));
                	pc.setDelFlag(rateDetailVO.getDelFlag());
                	pc.setCurrencyCode(hv.getCurrencyCode());
                	pcList.add(pc);
                }
            }
        }
    	return CommonUtil.isEmpty(pcList) ? new ArrayList<PriceCalc>() : pcList;
    }
    
    public void addPriceCalc(RatePlanVO ratePlanVO,ChannelRateplanVO crv) throws Exception {
    	if(crv.getStartDate() == null){
    		throw new BizException("crv.getStartDate() is null");
    	}
    	Date temStartDate = DateUtil.addMonths(new Date(), -1);
    	if(crv.getStartDate().before(temStartDate)){
    		crv.setStartDate(temStartDate);
    	}
    	
//    	crv.setEndDate(DateUtil.convertStringToDate("2015-12-31"));
    	
        List<String> crpList = crv.getChannelCodeList();
        if (CommonUtil.isEmpty(crpList)) {
            crpList = new ArrayList<String>();
        }
        List<PriceCalc> pcAddList = new ArrayList<PriceCalc>();
        List<PriceCalc> pcSendList = new ArrayList<PriceCalc>();
        Map<String,Channel> channelMap = getChannelMap();
        
        List<RateDetailVO> rateDetailVOList = ratePlanVO.getRateDetailVOList();
        for (RateDetailVO rateDetailVO : rateDetailVOList) {
            List<RoomRateVO> roomRateList = rateDetailVO.getRoomRateList();
            for (RoomRateVO rv : roomRateList) {
            	if(rv == null){
            		continue;
            	}
                String roomTypeId = rv.getRoomTypeId();
                
                List<String> rtIdStrList = crv.getRoomTypeIdList();
                boolean isFindRoomTypeId = false;
                for (String rtId : rtIdStrList) {
                	if(rtId == null){
                		continue;
                	}
                    if(rtId.equals(roomTypeId)){
                    	isFindRoomTypeId = true;
                        break;
                    }
                }
                if(!isFindRoomTypeId){
                	continue;
                }
                
                String roomTypeCode = roomTypeManager.getRoomTypeById(roomTypeId).getRoomTypeCode();

                List<String> dayList = DateUtil.getWeekDay(rateDetailVO.getEffectiveDate(),rateDetailVO.getExpireDate(), getWeek(rateDetailVO));
                
                for (String channelCode : crpList) { // 渠道
                    for (String date : dayList) {
                        Date dayDate = DateUtil.convertStringToDate(date);
                        if(CommonUtil.isNotEmpty(crv.getStartDate())){
                            if(CommonUtil.isEmpty(crv.getEndDate())){
                                if(dayDate.before(crv.getStartDate())){
                                   continue; 
                                }
                            }else if(CommonUtil.isNotEmpty( crv.getEndDate()) 
                                    && !DateUtil.judge(crv.getStartDate(), crv.getEndDate(), dayDate)){
                                continue; 
                            }
                        }
                        List<RateAmount> extList = new ArrayList<RateAmount>();
                        if(CommonUtil.isNotEmpty(rateDetailVO.getExtraBed())){
                            RateAmount rateAmount1 = new RateAmount();
                            rateAmount1.setNumberOfUnits(101);   //成人
                            rateAmount1.setBaseAmount(rateDetailVO.getExtraBed());
                            extList.add(rateAmount1);
                        }
                        if(CommonUtil.isNotEmpty(rateDetailVO.getExtraBedChild())){
                            RateAmount rateAmount2 = new RateAmount();
                            rateAmount2.setNumberOfUnits(102);   //小孩
                            rateAmount2.setBaseAmount(rateDetailVO.getExtraBedChild());
                            extList.add(rateAmount2);
                        }
                        
                        List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
                        extList.addAll(0,rateAmountList);
                        PriceCalc pc = null;
                        for (RateAmount rateAmount : extList) { // 人数
                            pc = new PriceCalc();
                            pc.setChannelCode(channelCode);
                            pc.setChainCode(crv.getChainCode());
                            pc.setHotelCode(crv.getHotelCode());
                            pc.setRoomTypeCode(roomTypeCode);
                            pc.setRatePlanCode(crv.getRatePlanCode());
                            pc.setAmount(rateAmount.getBaseAmount());
                            pc.setNumberOfUnits(rateAmount.getNumberOfUnits());
                            pc.setDate(date);
                            pc.setStartDate(date);
                            pc.setEndDate(date);
                            pc.setRateDetailId(rateDetailVO.getRateDetailId());
                            pc.setPriceCalcId(UUID.randomUUID().toString().replaceAll("-", ""));
//                        	pc.setCurrencyCode(crv.getCurrencyCode());
							pcAddList.add(pc);
                        }
                      //添加房价发送list
                        if(pc != null && crv.getIsSendChannel()){
                        	if(!DateUtil.convertStringToDate(pc.getStartDate()).before(DateUtil.cleanTimeDate(new Date()))){
                        		Channel channel = channelMap.get(channelCode);
                                if(channel!=null && channel.getPushRate() != null && channel.getPushRate() && channel.isPush(DateUtil.convertStringToDate(date))){
                                    pc.setRateAmountList(extList);
                                    pcSendList.add(pc);
                                }
                			}
                        }
                    }
                }
            }
        }
        syncBatchSave(pcAddList,pcSendList,null);
    }
    
    private void sendPriceMsg(List<PriceCalc> pcSendList) throws ParseException {
    	List<RoomMsg> rmList = new ArrayList<RoomMsg>();
    	for (PriceCalc pc : pcSendList) {
    		RoomMsg rm = new RoomMsg();
    		rm.setAdsType(AdsMessage.ADSTYPE_RATE);
    		rm.setChainCode(pc.getChainCode());
    		rm.setChannelCode(pc.getChannelCode());
    		rm.setHotelCode(pc.getHotelCode());
    		rm.setRoomTypeCode(pc.getRoomTypeCode());
    		rm.setRateCode(pc.getRatePlanCode());
    		rm.setStartDate(pc.getDate());
    		rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
    		rm.setCreatedTime(new Date());
    		rm.setRateAmountList(pc.getRateAmountList());
    		rm.setCurrencyCode(pc.getCurrencyCode());
    		rmList.add(rm);
		}
    	roomMsgDaoMongo.batchSave(rmList);
	}
    
    /**子线程批量保存和发送消息*/
	private void batchSave(final List<PriceCalc> pcAddList,final List<PriceCalc> pcSendList,final RatePlanVO ratePlanVO) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				syncBatchSave(pcAddList,pcSendList,ratePlanVO);
			}
		});
		t.start();
	}
    /**同步批量保存和发送消息*/
	protected void syncBatchSave(List<PriceCalc> pcAddList,
			List<PriceCalc> pcSendList, RatePlanVO ratePlanVO) {
		long startMili2 = System.currentTimeMillis();
//		List<List> objList = CommonUtil.splitList(pcAddList, 500);
//		for (final List list : objList) {
//			priceCalcDao.batchSave(list);
//		}
//		long endMili2 = System.currentTimeMillis();
//		System.out.println(" priceCalc insert 耗时为："+ (endMili2 - startMili2) + "毫秒 pcAddList.size:"+pcAddList.size());
		//更新子价格
		if(CommonUtil.isNotEmpty(ratePlanVO)){
			List<Rateplan> rateplanList = ratePlanManager.getRefRateplan(ratePlanVO.getRp().getHotelId(),ratePlanVO.getRp().getRatePlanId());
	        for (Rateplan rateplan : rateplanList) {
//	        	deletePriceCalcByDetailVOList(ratePlanVO.getRateDetailVOList(),rateplan.getRatePlanCode(),h.getHotelCode());
	        	try {
					RatePlanVO rpv = new RatePlanVO();
					rateplan.setInheritRatePlanId(ratePlanVO.getRp().getRatePlanId());// 设置成父detail
					rpv.setRp(rateplan);
					rpv.setRateDetailVOList(ratePlanManager.getRateDetailVOList(rateplan,ratePlanVO.getRateDetailVOList()));
					addPriceCalc(rpv,true);
				} catch (Exception e) {
					log.error(CommonUtil.getExceptionMsg(e, new String[] { "mc","ccm" }));
					throw new BizException("syncBatchSave", CommonUtil.getExceptionMsg(e, new String[] { "mc","ccm" }));
				}
			}
		}
		
		long endMili2 = System.currentTimeMillis();
		//发送消息
		try {
			sendPriceMsg(pcSendList);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BizException("syncBatchSave", CommonUtil.getExceptionMsg(e, new String[] { "mc","ccm" }));
		}
		System.out.println(" sendPriceMsg 耗时为："+ (endMili2 - startMili2) + "毫秒 pcAddList.size:"+pcAddList.size());
	}
	@Override
    public int[] getWeek(RateDetailVO rateDetailVO) {
        List<Integer> list = new ArrayList<Integer>();
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToSunday())){
            list.add(0);
        }
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToMonday())){
            list.add(1);
        }
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToTuesday())){
            list.add(2);
        }
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToWednesday())){
            list.add(3);
        }
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToThursday())){
            list.add(4);
        }
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToFriday())){
            list.add(5);
        }
        if(BooleanUtils.isTrue(rateDetailVO.getIsApplyToSaturday())){
            list.add(6);
        }
        Iterator<Integer> iterator = list.iterator();
        int[] ret = new int[list.size()];
        int i=0;
        while(iterator.hasNext()){
          ret[i++] = iterator.next();
        }
        return ret;
    }

	@Override
    public List<PriceCalc> getRoomPrice(PriceCalc pc) {
        try {
            pc.setOnOff(1);
            log.info("PriceCalc: "+pc);
            List<PriceCalc> priceList = restrictionCalcDao.searchRestrictionCalcAndRate(pc,false);
            return getValidataPriceList(priceList, pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public List<PriceCalc> getRoomPriceOws(PriceCalc pc) {
        try {
            pc.setOnOff(1);
            log.info("PriceCalc: "+pc);
            List<PriceCalc> priceList = restrictionCalcDao.searchRestrictionCalcAndRateOws(pc,false);
            return getValidataPriceList(priceList, pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean validataSoldableCondition(SoldableCondition scn,PriceCalc pc,List<PriceCalc> priceList)throws Exception{
        Date checkInDate = DateUtil.convertStringToDate(pc.getStartDate());
        Date checkOutDate = DateUtil.convertStringToDate(pc.getEndDate());
        int evenDay = DateUtil.dateDiff(checkInDate, checkOutDate);
        Calendar c = Calendar.getInstance();
        int beforehandDays = DateUtil.dateDiff(DateUtil.convertStringToDate(DateUtil.convertDateTimeToString(c.getTime())), checkInDate);
        if(priceList != null){
            if (evenDay != priceList.size()) {
                log.info("房价天数不够!");
                return false;
            } 
        }
        
        if (scn.getMaxEvenDay() != null && evenDay > scn.getMaxEvenDay()) {
        	log.info("最大连住天数不符合");
            return false;
        }
        if (scn.getMaxBeforehandDay() != null && beforehandDays > scn.getMaxBeforehandDay()) {
        	log.info("最大提前天数不符合");
            return false;
        }
        if (scn.getMinEvenDay() != null && evenDay < scn.getMinEvenDay()) {
        	log.info("最小连住天数不符合");
            return false;
        }
        if (scn.getMinBeforehandDay() != null && beforehandDays < scn.getMinBeforehandDay()) {
        	log.info("最小提前天数不符合");
            return false;
        }
		if (StringUtils.hasText(scn.getStartDate()) && StringUtils.hasText(scn.getEndDate())) {
        	Date nowHHmm=DateUtil.convertDateToDate("HH:mm", new Date());
        	Date start=DateUtil.convertStringToDate("HH:mm", scn.getStartDate());
			Date end = DateUtil.convertStringToDate("HH:mm", scn.getEndDate());
			if (!DateUtil.judge(start, end, nowHHmm)) {
				return false;
			}
        }
        
        String btime = scn.getLastMinutesBeginTime();
        String etime = scn.getLastMinutesEndTime();
        
        if(CommonUtil.isNotEmpty(btime)&& CommonUtil.isNotEmpty(etime)){
        	Date beginTime = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", btime);
            Date endTime = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", etime);
            
        	if(priceList != null){
                for (PriceCalc priceCalc : priceList) {
                	if(DateUtil.judge(beginTime, endTime, DateUtil.convertStringToDate(priceCalc.getDate()))){
                		if(CommonUtil.isNotEmpty(scn.getLastMinutesAmount())){
                			priceCalc.setAmount(new BigDecimal(scn.getLastMinutesAmount()).setScale(2,BigDecimal.ROUND_HALF_UP));
                		}else if(CommonUtil.isNotEmpty(scn.getLastMinutesPercent())){
                			priceCalc.setAmount(new BigDecimal((priceCalc.getAmount().doubleValue() * (scn.getLastMinutesPercent() /100))).setScale(2,BigDecimal.ROUND_HALF_UP));
                		}
                	}
                }
            }
        }
        
        if(CommonUtil.isNotEmpty(scn.getLimitBuy())){
        	if(scn.getLimitBuy()-scn.getSoldNum() < pc.getNumberOfUnits() * evenDay){
                log.info("限量购买间数不符合");
                return false;
            }
        }
        
        if(CommonUtil.isNotEmpty(scn.getBookTime())){
            int bookTime = Integer.parseInt(scn.getBookTime());
            if(! (bookTime > c.get(c.HOUR_OF_DAY))){
                log.info("预定时间不符合");
                return false;
            }
        }
        return true;
    }
    @Override
    public void deletePriceCalcByRatePlanId(String ratePlanId) {
        if(CommonUtil.isEmpty(ratePlanId)){
            return;
        }
        Rateplan rp = ratePlanManager.get(ratePlanId);
        Hotel hv = hotelDao.getHotel(rp.getHotelId(), "hotelCode");
//        rp.setInheritRatePlanId(null);
        RatePlanVO ratePlanVO = new RatePlanVO();
        ratePlanVO.setRp(rp);
        List<RateDetailVO> rateDetailVOList =  ratePlanManager.getRateDetailVOList(rp);
        for (RateDetailVO rateDetailVO : rateDetailVOList) {
			priceCalcDao.deletePriceCalcByDetailIdAndRatePlanCode(rateDetailVO.getRateDetailId(), rp.getRatePlanCode(),hv.getHotelCode());
		}
    }

    public void deletePriceCalcByRateRateplan(Rateplan rp) {
        if(CommonUtil.isEmpty(rp)){
            return;
        }
        RatePlanVO ratePlanVO = new RatePlanVO();
        ratePlanVO.setRp(rp);
        ratePlanVO.setRateDetailVOList(ratePlanManager.getRateDetailVOList(rp));
        try {
            delInvalidPriceCalc(ratePlanVO,new HashMap<String, String>());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delInvalidPriceCalc(RatePlanVO ratePlanVO,
            HashMap<String, String> priceIdMap) throws ParseException {
    	HotelVO hv = null;
        //如果当前用户已登陆
        if(SecurityHolder.getB2BUser()!=null){
        	hv = SecurityHolder.getB2BUser().getHotelvo();
        }else{
        	hv = hotelDao.getHotelChainByHotelId(ratePlanVO.getRp().getHotelId());
        }
        List<RateDetailVO> rateDetailVOList = ratePlanVO.getRateDetailVOList();
        for (RateDetailVO rateDetailVO : rateDetailVOList) {
            List<RoomRateVO> roomRateList = rateDetailVO.getRoomRateList();
            for (RoomRateVO rv : roomRateList) {
                String roomTypeId = rv.getRoomTypeId();
                String roomTypeCode = roomTypeManager.getRoomTypeById(roomTypeId).getRoomTypeCode();
                
//                List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
//                for (RateAmount rateAmount : rateAmountList) {
                    //****************************************************
                ChannelGoodsVO cgvo = new ChannelGoodsVO();
                cgvo.setHotelId(hv.getHotelId());
                cgvo.setRatePlanCode(ratePlanVO.getRp().getRatePlanCode());
                cgvo.setRoomTypeCode(roomTypeCode);
                List<ChannelGoodsVO> crpList = channelgoodsManager.getChannelRatePlanByChannelGoods(cgvo);
                if (CommonUtil.isEmpty(crpList)) {
                    crpList = new ArrayList<ChannelGoodsVO>();
                    log.debug("该房价没有对应的渠道");
                }
                for (ChannelGoodsVO channelGoodsVO : crpList) { // 渠道
                    PriceCalc pc = new PriceCalc();
                    pc.setChannelCode(channelGoodsVO.getChannelCode());
                    pc.setChainCode(hv.getChainCode());
                    pc.setHotelCode(hv.getHotelCode());
                    pc.setRoomTypeCode(roomTypeCode);
                    pc.setRatePlanCode(ratePlanVO.getRp().getRatePlanCode());
//                    pc.setNumberOfUnits(rateAmount.getNumberOfUnits());
                    pc.setStartDate(DateUtil.convertDateToString(rateDetailVO.getEffectiveDate()));
                    pc.setEndDate(DateUtil.convertDateToString(rateDetailVO.getExpireDate()));
                    List<PriceCalc> priceList = priceCalcDao.searchPriceCalc(pc);
                    for (PriceCalc priceCalc : priceList) {
                        if (!priceIdMap.containsKey(priceCalc.getPriceCalcId())) {
                            //根据该房型起始时间、（未包括人数） 删除缺少的星期、空的房价
                            priceCalcDao.deletePriceCalc(priceCalc.getPriceCalcId());
                        }
                    }
//                }
                }
            }
        }
    }

    private boolean updatePriceCalcByChannelRateplanVO(ChannelRateplanVO crv) throws Exception{
        boolean isSuccess = false;
//        if(deletePriceCalcByChannelRateplanVO(crv)){
            log.info("add rateplan start...");
            RatePlanVO rpv = new RatePlanVO();
            Rateplan rp = ratePlanManager.getRateplanByRateplanCode(crv.getRatePlanCode(), crv.getHotelCode());
            rpv.setRp(rp);
            String chainCode = crv.getChainCode();
            String hotelCode = crv.getHotelCode();
            String ratePlanCode = crv.getRatePlanCode();
            List<RateDetailVO> rateDetailVOList = ratePlanManager.getRateDetailVOList(rp);
            rateDetailVODaoMongo.batchSaveMongo(rateDetailVOList,chainCode,hotelCode,ratePlanCode,null,RateDetailVO.RATETYPE_CCM);
            isSuccess= true;
//            rpv.setRateDetailVOList(ratePlanManager.getRateDetailVOList(rp));
//            addPriceCalc(rpv,crv);
            log.info("add rateplan end!");
//        }
        return isSuccess;
    }
    @Override
    public void updatePriceCalcByChannelRateplanVOList(final List<ChannelRateplanVO> channelRateplanVOList,final String hotelId,final String handleType) {
    	log.info(hotelId);
    	Thread t = new Thread(new Runnable() {
			public void run() {
				String isSuccess = ChannelGoodsStatus.fail;
				Boolean isSend = Boolean.FALSE;
				if(ChannelGoods.HANDLETYPE_BINDING.equals(handleType)){//渠道绑定
					isSend = Boolean.FALSE;
				}else if(ChannelGoods.HANDLETYPE_PUBLISH.equals(handleType)){//渠道发布
					isSend = Boolean.TRUE;
				}
				ChannelRateplanVO channelRateplanVO_tmp = new ChannelRateplanVO();
				
				String hotelCode = null;
				String chainCode = null;
				try {
					Hotel h = hotelDao.getHotel(hotelId);
					if (h != null) {
						hotelCode = h.getHotelCode();
					}
					Chain c = chainDao.get(h.getChainId());
					if (c != null) {
						chainCode = c.getChainCode();
					}
				} catch (Exception e) {
					log.info(e);
				}
				log.info(hotelCode + chainCode);
				
				int flag = 3;
		    	try {
					for (ChannelRateplanVO channelRateplanVO : channelRateplanVOList) {
						channelRateplanVO.setHotelCode(hotelCode);
						channelRateplanVO.setChainCode(chainCode);
						channelRateplanVO.setIsSendChannel(isSend);
						channelRateplanVO_tmp = channelRateplanVO;
						updatePriceCalcByChannelRateplanVO(channelRateplanVO);
					}
					isSuccess = ChannelGoodsStatus.publish;
					flag = 2;
				} catch (Exception e) {
					log.info("updatePriceCalcByChannelRateplanVOListException");
					e.printStackTrace();
					String err = CommonUtil.getExceptionMsg(e, new String[] {"ccm" ,"mc"});
					channelRateplanVO_tmp.setCreatedTime(new Date());
					channelRateplanVO_tmp.setErrMsg(err+CommonUtil.getExceptionMsg(e));
//					log.error(err);
					roomMsgDaoMongo.getMongoTemplate().save(channelRateplanVO_tmp);
				}finally{
					if(ChannelGoods.HANDLETYPE_PUBLISH.equals(handleType)){
						channelgoodsManager.updateChannelGoodsStatus(hotelId, isSuccess);
						PessimisticLock pl = new PessimisticLock();
						pl.setBizId(hotelId);
						pl.setBizType("publishChannelGoods");
						pessimisticLockDao.deletePessimisticLock(pl);
					}
					constantDao.updateConstantFlagById(hotelCode, flag);
				}
			}
		});
		t.start();
    	
    }
	@Override
	public void deletePriceCalcByDetailVOList(
			List<RateDetailVO> rateDetailVOList,String ratePlanCode,String hotelCode) {
		for (RateDetailVO rateDetailVO : rateDetailVOList) {
			String rateDetailId = rateDetailVO.getRateDetailId();
			PriceCalc pc = new PriceCalc();
			pc.setRateDetailId(rateDetailId);
			pc.setRatePlanCode(ratePlanCode);
			pc.setHotelCode(hotelCode);
			pc.setRoomTypeCode("all");
			List<PriceCalc> pcList = priceCalcDao.searchPriceCalc(pc);
			if(CommonUtil.isNotEmpty(pcList)){
				priceCalcDao.deletePriceCalc(pcList);
			}
			
//        	priceCalcDao.deletePriceCalcByDetailIdAndRatePlanCode(rateDetailId,ratePlanCode);
        }
	}
	/**
	 * 根据条件从RateDetail中获取价格
	 * prarm hotelId,channelId,startDate,endDate 必须，roomTypeCode,ratePlanCode,numberOfUnits 可选
	 * return ratePlanCode,roomTypeCode,date,numberOfUnits,amount,rateDetailId
	 * */
	public List<PriceCalc> getPriceFromRateDetail(PriceCalc pc){
		
		if(CommonUtil.isEmpty(pc.getRoomTypeIdsql())){
			StringBuffer roomTypeIds = new StringBuffer();
			if(CommonUtil.isNotEmpty(pc.getRoomTypeCode())){
				RoomTypeVO rtVo = new RoomTypeVO();
				rtVo.setHotelId(pc.getHotelId());
				rtVo.setRoomTypeCode(pc.getRoomTypeCode());
				RoomTypeVO resRt = roomTypeManager.getRoomTypeByCode(rtVo);
				roomTypeIds.append(" and roomTypeId = '").append(resRt.getRoomTypeId()).append("'");
			}else{//如果RoomTypeCode为空,则查询所有房型
				List<RoomType> rtList = roomTypeManager.getHotelRoomTypesByHotelId(pc.getHotelId());
				if(CommonUtil.isNotEmpty(rtList)){
					roomTypeIds.append(" and roomTypeId in (");
					for (RoomType roomType : rtList) {
						roomTypeIds.append("'").append(roomType.getRoomTypeId()).append("',");
					}
					roomTypeIds = new StringBuffer(roomTypeIds.toString().substring(0, roomTypeIds.length()-1)).append(")");
				}
			}
			pc.setRoomTypeIdsql(roomTypeIds.toString());
		}
		return priceCalcDao.getPriceFromRateDetail(pc);
	}
	
	/**
	 * 从rateDetail取出房型价格日期set
	 * roomTypeCode+ratePlanCode+date
	 */
	@Override
	public HashSet<String> getRateDetailRoomRateDate(PriceCalc pc){
		List<RoomType> rtList = roomTypeManager.getHotelRoomTypesByHotelId(pc.getHotelId());
		
		if(CommonUtil.isNotEmpty(rtList) && CommonUtil.isNotEmpty(pc.getRoomTypeCodeList())){
			StringBuffer roomTypeIds = new StringBuffer();
			roomTypeIds.append(" and roomTypeId in ( ");
			boolean flag = false;// roomTpeId in has roomTypeId
			for (String roomTypeCode : pc.getRoomTypeCodeList()) {
				for (RoomType roomType : rtList) {
					if(roomTypeCode.equals(roomType.getRoomTypeCode())){
						roomTypeIds.append("'").append(roomType.getRoomTypeId()).append("',");
						flag = true;
						break;
					}
				}
			}
			if(flag){
				roomTypeIds = new StringBuffer(roomTypeIds.toString().substring(0, roomTypeIds.length()-1)).append(")");
			}else{
				roomTypeIds = new StringBuffer("");
			}
			pc.setRoomTypeIdsql(roomTypeIds.toString());
		}
		
		
		if(CommonUtil.isNotEmpty(pc.getRatePlanCodeList())){
			StringBuffer ratePlanCodes = new StringBuffer();
			ratePlanCodes.append(" and ratePlanCode in (");
			for (String ratePlanCode : pc.getRatePlanCodeList()) {
				ratePlanCodes.append("'").append(ratePlanCode).append("',");
			}
			ratePlanCodes = new StringBuffer(ratePlanCodes.toString().substring(0, ratePlanCodes.length()-1)).append(")");
			pc.setRatePlanCodesql(ratePlanCodes.toString());
		}
		
		List<PriceCalc> pcList = priceCalcDao.getRateDetailRoomRateDate(pc);
		HashSet<String> set = new HashSet<String>();
		for (PriceCalc priceCalc : pcList) {
			set.add(priceCalc.getRoomTypeCode()+priceCalc.getRatePlanCode()+priceCalc.getDate());
		}
		
		return set;
	}
	
	private List<PriceCalc> getValidataPriceList(List<PriceCalc> priceList, PriceCalc pc) throws Exception {
		if (priceList != null && priceList.size() > 0) {
			String ratePlanCode = priceList.get(0).getRatePlanCode();
			String hotelCode = pc.getHotelCode();
			Rateplan rv = ratePlanManager.getRateplanByRateplanCode(ratePlanCode, hotelCode);
			List<SoldableCondition> scList = rv.getSCList();
			if (CommonUtil.isEmpty(scList)) {
				return priceList;
			} else {
				log.info("SoldableConditionList: " + scList);
				log.info("priceList: " + priceList);

				for (SoldableCondition soldableCondition : scList) {
					if (validataSoldableCondition(soldableCondition, pc, priceList)) {
						return priceList;
					}
				}
			}
		} else {
			log.warn("没有房价");
		}
		return null;
	}
	
}
