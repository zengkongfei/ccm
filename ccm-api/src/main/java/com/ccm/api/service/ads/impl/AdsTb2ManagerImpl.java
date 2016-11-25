package com.ccm.api.service.ads.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.pms.utils.DateUtils;
import com.ccm.api.dao.ratePlan.PriceCalcDao;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.dao.rsvtype.AdsToTBLogDao;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsAvailablilityVO;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.ads.vo.AdsMessageResult;
import com.ccm.api.model.ads.vo.AdsRateUpdateVO;
import com.ccm.api.model.ads.vo.AdsVO;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.common.vo.InvokeResponse;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.ratePlan.vo.RoomMsgCriteria;
import com.ccm.api.model.ratePlan.vo.RoomMsgResult;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.rsvtype.AdsToTBLog;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.service.ads.AdsManager;
import com.ccm.api.service.ads.OnlineUserManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.ratePlan.RestrictionCalcManager;
import com.ccm.api.service.rsvtype.RsvtypeManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomManager;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
@Repository("adsTb2Manager")
public class AdsTb2ManagerImpl extends GenericManagerImpl<AdsMessage, String> implements AdsManager{
    
    protected AdsMessageDao adsMessageDao;
    
    protected Log onlineLog = LogFactory.getLog("online");
    @Resource
    private AdsToTBLogDao adsToTBLogDao;

    @Resource
    private TaobaoRoomManager taobaoRoomManager;
    
    @Resource
    private OnlineUserManager onlineUserManager;
    
    @Resource
    private RsvtypeManager rsvtypeManager;
    @Autowired
    private ChannelManager channelManager;
    @Resource
    private RestrictionCalcManager restrictionCalcManager;
    @Resource
    private PriceCalcDao priceCalcDao;
    @Resource
    private ChainDao chainDao;
    @Resource
    private RoomTypeDao roomTypeDao;
    @Resource
    private RateplanDao rateplanDao;
    @Resource
    private ChannelgoodsDao channelgoodsDao;
    @Resource
    private ChannelgoodsManager channelgoodsManager;
    @Resource
    private TaobaoApiManager taobaoApiManager;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private UserManager userManager;
    @Resource
    private RoomMsgDaoMongo roomMsgDaoMongo;
    @Resource
    private AdsMessageDao adsMessageErrDao;
    @Resource
    public void setAdsMessageDao(AdsMessageDao adsMessageDao) {
        this.adsMessageDao = adsMessageDao;
        dao = adsMessageDao;
    }

    @Override
    public AdsMessage createAdsMessage(AdsMessage adsMsg) {
        AdsMessage saveAdsMsg = adsMessageDao.save(adsMsg);
        return saveAdsMsg;
    }

    @Override
    public List<HashMap<String,String>> saveAvailablilityStatus(List<AdsVO> availList) {
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(1);
        
        for (AdsVO adsVO : availList) {
            Rsvtype rsv = new Rsvtype();
            rsv.setHotelCode(adsVO.getHotelCode());
//            channelManager.getChannelByChannelCode(ChannelCodeEnum.TAOBAO.getName()).getChannelId()
            rsv.setChannel("TB");
            
            Set<AdsAvailablilityVO> availSet = adsVO.getAvailVOSet();
            
            for (AdsAvailablilityVO adsAvailVO : availSet) {
                HashMap<String,String> returnMap = new HashMap<String, String>();
                rsv.setType(adsAvailVO.getRoomTypeCode());
                rsv.setPhysicalRooms(adsAvailVO.getAvailableAmount());
                try {
                    rsv.setDate(DateUtil.convertStringToDate(adsAvailVO.getStartDate()));
                    returnMap.put("chainCode", adsVO.getChainCode());
                    returnMap.put("date", DateUtil.convertDateToString(rsv.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                returnMap.put("roomTypeCode",rsv.getType());
                returnMap.put("hotelCode",rsv.getHotelCode());
                returnMap.put("targetGDS",adsAvailVO.getTargetGDS());
                
                //根据 HotelCode、RoomTypeCode、Date 查询是否存在，存在则更新。
                
                List<Rsvtype> rsvList = rsvtypeManager.searchResvType(rsv);
                if(!rsvList.isEmpty()){
                    String gids ="";
                    for (Rsvtype rsvtype : rsvList) {
                        rsvtype.setPhysicalRooms(rsv.getPhysicalRooms());
                        rsvtype.setMcInvoke(false);
                        rsvtypeManager.updateRsvtype(rsvtype);
                    }
                }else{
                    rsv.setUnavailable(0);
                    rsv.setOutoforder(0);
                    rsv.setDefinite(0);
                    rsv.setHouseOverbook(0);
                    rsv.setRoomTypeOverbook(0);
                    rsv.setAdultsInHouse(0);
                    rsv.setChildrenInHouse(0);
                    rsv.setArrivalRooms(0);
                    rsv.setDepartureRooms(0);
                    rsv.setBlockCount(0);
                    rsv.setResvCount(0);
                    rsv.setOverbookAmount(0);
                    rsvtypeManager.save(rsv);
                }
                list.add(returnMap);
            }
        }
        return list;
    }
    
    
    @Override
    public List<HashMap<String,String>> updateAvailablilityStatus(List<AdsVO> availList) {
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(2);
        for (AdsVO adsVO : availList) {
            RestrictionCalc rcalc = new RestrictionCalc();
            String hotelCode = adsVO.getHotelCode();
            rcalc.setHotelCode(hotelCode);
            rcalc.setChainCode(adsVO.getChainCode());
            rcalc.setChannelCode("TB");
            
            Set<AdsAvailablilityVO> availSet = adsVO.getAvailVOSet();
            for (AdsAvailablilityVO adsAvailVO : availSet) {
                
                String roomTypeCode = adsAvailVO.getRoomTypeCode();
                String rateCode = adsAvailVO.getRateCode();
                String actionCode = adsAvailVO.getActionCode();
                String start = adsAvailVO.getStartDate();
                String end = adsAvailVO.getEndDate();
                
                rcalc.setRoomTypeCode(roomTypeCode);
                
                Date startDate = DateUtils.parse(start);
                Date endDate = DateUtils.parse(end);
                
                
                String[] rateCode2 = new String[]{rateCode,rateCode+"P2X"};
                for (String rate : rateCode2) {
                    HashMap<String,String> returnMap = new HashMap<String, String>();
                    returnMap.put("chainCode", rcalc.getChainCode());
                    returnMap.put("date",end);
                    returnMap.put("targetGDS",adsAvailVO.getTargetGDS());
                    returnMap.put("hotelCode",rcalc.getHotelCode());
                    returnMap.put("roomTypeCode",roomTypeCode);
                    returnMap.put("ratePlanCode",rate);
                    
                    if("Open".equals(actionCode) || "Close".equals(actionCode)){
                        while (!startDate.after(endDate)) {
                            rcalc.setDate(startDate);
                            boolean allRateCode = adsAvailVO.isAllRateCode();
                            if(!allRateCode){
                                rcalc.setRatePlanCode(rate);
                                List<RestrictionCalc> rcList = restrictionCalcManager.searchRestrictionCalc(rcalc);
                                if(!rcList.isEmpty()){
                                    for (RestrictionCalc rc : rcList) {
                                        rc.setOnOff("Open".equals(actionCode) ? 1 : 0);
                                        restrictionCalcManager.updateRestrictionCalcOnOff(rc);
                                    }
                                }else{
                                    rcalc.setRestrictionCalcId(null);
                                    rcalc.setOnOff("Open".equals(actionCode) ? 1 : 0);
                                    restrictionCalcManager.save(rcalc);
                                }
                            }
                            startDate = DateUtils.add(startDate, DateUtils.DAY, 1);
                        }
                     }else{continue;}
                    list.add(returnMap);
                }
            }
       }
        return list;
    }
    @Override
    public List<HashMap<String,String>> saveRateUpdate(List<AdsVO> rateUpdateList) {
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(1);
        for (AdsVO adsVO : rateUpdateList) {
            PriceCalc pc = new PriceCalc();
            pc.setHotelCode(adsVO.getHotelCode());
            pc.setChainCode(adsVO.getChainCode());
            pc.setChannelCode("TB");
            
            Set<AdsRateUpdateVO> rateSet = adsVO.getRateUpdateVOSet();
            for (AdsRateUpdateVO adsRateUpdateVO : rateSet) {
                pc.setRoomTypeCode(adsRateUpdateVO.getRoomTypeCode());
                pc.setRatePlanCode(adsRateUpdateVO.getRateCode());
                String startDateStr = adsRateUpdateVO.getStartDate();
                String endDateStr = adsRateUpdateVO.getEndDate();
                
                List<Date> dateList =DateUtil.createDateList(startDateStr,endDateStr,adsRateUpdateVO.getWeekFlag());
                for (Date date : dateList) {
                    pc.setDate(DateUtil.convertDateTimeToString(date));
                    
                  //根据 HotelCode、RoomTypeCode、Date 查询是否存在，存在则更新。
                    
                    
                    int numberOfunits = 1;
                    BigDecimal rateAmount;
                    Map<String,String> rateMap = adsRateUpdateVO.getRateAmountTypeMap();
                    for(Map.Entry<String, String> entry : rateMap.entrySet())   
                    {   
                        if("TwoPerson".equalsIgnoreCase(entry.getKey())){
                            numberOfunits =2;
                            pc.setRatePlanCode(adsRateUpdateVO.getRateCode()+"P2X");
                        }else if("OnePerson".equalsIgnoreCase(entry.getKey())){
                            numberOfunits =1;
                            pc.setRatePlanCode(adsRateUpdateVO.getRateCode());
                        }
                        
                        rateAmount = new BigDecimal(entry.getValue());
                        pc.setNumberOfUnits(numberOfunits);
                        List<PriceCalc> pcList = priceCalcDao.searchPriceCalc(pc);
                        
                        if(CommonUtil.isNotEmpty(pcList)){
                            for (PriceCalc priceCalc : pcList) {
                                priceCalc.setAmount(rateAmount);
                                priceCalc.setNumberOfUnits(numberOfunits);
                                priceCalcDao.save(priceCalc);
                            }
                        }else{
                            pc.setAmount(rateAmount);
                            pc.setNumberOfUnits(numberOfunits);
                            pc.setPriceCalcId(null);
                            priceCalcDao.save(pc);
                        }
                    }
                }
                
                Map<String,String> rateMap = adsRateUpdateVO.getRateAmountTypeMap();
                for(Map.Entry<String, String> entry : rateMap.entrySet())   
                {   
                    String rateCode = "";
                    HashMap<String,String> returnMap = new HashMap<String, String>();
                    if("TwoPerson".equalsIgnoreCase(entry.getKey())){
                        rateCode = adsRateUpdateVO.getRateCode()+"P2X";
                    }else if("OnePerson".equalsIgnoreCase(entry.getKey())){
                        rateCode = adsRateUpdateVO.getRateCode();
                    }
                    returnMap.put("chainCode", pc.getChainCode()); 
                    returnMap.put("hotelCode",pc.getHotelCode());
                    returnMap.put("ratePlanCode",rateCode);
                    returnMap.put("roomTypeCode",pc.getRoomTypeCode());
                    returnMap.put("targetGDS",adsRateUpdateVO.getTargetGDS());
                    list.add(returnMap);
                }
            }
        }
        return list;
    }

    @Override
    public AdsMessageResult searchAdsLog(AdsLogMessageCriteria amc) {
        AdsMessageResult adsVoResult = new AdsMessageResult();
        List<AdsMessage> resultList = new ArrayList<AdsMessage>();
        Integer totalCount=new Integer(0);
        if(null!=amc.getStatusList()){
        	 if(amc.getStatusList().contains(AdsMessage.EXEC_INIT_STATUS)){
                 RoomMsgResult res = roomMsgDaoMongo.serachRoomMsg(getRoomMsgCriteria(amc)); 
                 resultList.addAll(getRmList(res.getResultList()));
                 totalCount+=res.getTotalCount();  
             }
        	 if(amc.getStatusList().contains(AdsMessage.EXEC_END_STATUS)){
             	resultList.addAll(adsMessageDao.searchAdsLogList(amc));
             	totalCount+=adsMessageDao.searchAdsLogCount(amc);
             }
        	 if(amc.getStatusList().contains(AdsMessage.EXEC_ERROR_STATUS)){
             	resultList.addAll(adsMessageErrDao.searchAdsLogList(amc));
             	totalCount+=adsMessageErrDao.searchAdsLogCount(amc);
             }
        }
        adsVoResult.setResultList(resultList);
        adsVoResult.setTotalCount(totalCount);
        return adsVoResult;
    }
    private List<AdsMessage> getRmList(List<RoomMsg> rmList){
        List<AdsMessage> adsList = new ArrayList<AdsMessage>();
        for (RoomMsg rm : rmList) {
            AdsMessage ads = new AdsMessage();
            ads.setAdsId(rm.getRoomMsgId());
            ads.setAdsType(rm.getAdsType());
            ads.setHotelCode(rm.getHotelCode());
            ads.setChainCode(rm.getChainCode());
            ads.setRatePlanCode(rm.getRateCode());
            ads.setRoomTypeCode(rm.getRoomTypeCode());
            ads.setTargetGDS(rm.getChannelCode());
            ads.setStatus(rm.getSendStatus());
            ads.setCreatedTime(rm.getCreatedTime());
            adsList.add(ads);
        }
        rmList.clear();
        return adsList;
    }
    
    private RoomMsgCriteria getRoomMsgCriteria(AdsLogMessageCriteria amc){
        RoomMsgCriteria  rmc = new RoomMsgCriteria();
        rmc.setPageNum(amc.getPageNum());
        rmc.setPageSize(amc.getPageSize());
        rmc.setSendStatus(amc.getStatus());
        rmc.setAdsType(amc.getAdsType());
        rmc.setChainCode(amc.getChainCode());
        rmc.setHotelCode(amc.getHotelCode());
        rmc.setRateCode(amc.getRatePlanCode());
        rmc.setRoomTypeCode(amc.getRoomTypeCode());
        rmc.setChannelCode(amc.getTargetGDS());
        rmc.setStartDate(amc.getStartDate());
        rmc.setEndDate(amc.getEndDate());
        
        rmc.setChannelCodeList(amc.getChannelCodeList());
        rmc.setChainCodeList(amc.getChainCodeList());
        rmc.setHotelCodeList(amc.getHotelCodeList());
        rmc.setAdsTypeList(amc.getAdsTypeList());
        rmc.setRoomTypeCodeList(amc.getRoomTypeCodeList());
        rmc.setSendStatusList(amc.getStatusList());
        
        return rmc;
    }
    @Override
    public List<AdsToTBLog> getTbLog(AdsToTBLog tbLog) {
        return adsMessageDao.getTbLog(tbLog);
    }

    @Override
    public String getAdsMessageFieldValue(String field, String adsId,String hotelCode,String status) {
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("field",field);
        map.put("adsId",adsId);
        map.put("hotelCode",hotelCode);
        String fieldValue="";
        if(AdsMessage.EXEC_INIT_STATUS.equals(status)){
            RoomMsg rm = roomMsgDaoMongo.get(adsId);
            fieldValue = rm != null ? JSONObject.toJSONString(rm) : "";
        }else if(AdsMessage.EXEC_END_STATUS.equals(status)){
            fieldValue = adsMessageDao.getAdsMessageFieldValue(map);
        }else if(AdsMessage.EXEC_ERROR_STATUS.equals(status)){
            fieldValue = adsMessageErrDao.getAdsMessageFieldValue(map);
        }
        return fieldValue;
    }

    @Override
    public String getAdsToTBLogFieldValue(String field, String adsToTBLogId) {
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("field",field);
        map.put("adsToTBLogId",adsToTBLogId);
        return adsMessageDao.getAdsToTBLogFieldValue(map);
    }

    @Override
    public AdsToTBLog getAdsToTBLog(String adsToTBLogId) {
        return adsToTBLogDao.get(adsToTBLogId);
    }

    @Override
    public boolean pushToTaobao(AdsMessage adsMsg) throws Exception {
        JSON json = JSONArray.parseArray(adsMsg.getRquestTbData());
        List<JSONObject> list = JSON.toJavaObject(json, List.class);
        String chainCode="";
        for (JSONObject map: list) {
            Date date = null;
            if(map.get("date") != null && StringUtils.hasText(map.get("date").toString())){
                date = DateUtil.convertStringToDate(map.get("date").toString());
            }
            chainCode = (String)map.get("chainCode");
            String gids = (String) map.get("productCode");
            boolean isPush = onlineUserManager.isPushByChainCode(chainCode);
            
            if(isPush == true){
              //调用发送到淘宝方法
                String[] gidsArr = new String[]{""};
                if(StringUtils.hasText(gids)){
                    gidsArr = gids.split(",");
                }
                
                for (int i = 0; i < gidsArr.length; i++) {
                    taobaoRoomManager.onlineToTaobao(chainCode, gidsArr[i], date, adsMsg.getEchoToken(),adsMsg.getAdsType());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public AdsMessage getMaxReqDateAdsMessage() {
        return adsMessageDao.getMaxReqDateAdsMessage();
    }

    public HashMap<String,String> getHotelByChainId(String chainId){
    	List<Hotel> hotelList = hotelDao.getHotelByChainId(chainId);
    	HashMap<String,String> hotelMap = new HashMap<String, String>();
		for (Hotel hotel : hotelList) {
			hotelMap.put(hotel.getHotelId(),hotel.getHotelCode());
		}
        return hotelMap;
    }

    @Override
    public HashMap<String, String> getRoomByHotelId(String hotelId) {
    	List<RoomType>  rtList = roomTypeDao.getRoomTypeByHotelId(hotelId);
    	HashMap<String,String> roomTypeMap = new HashMap<String, String>();
		for (RoomType rt : rtList) {
			roomTypeMap.put(rt.getRoomTypeId(),rt.getRoomTypeCode());
		}
        return roomTypeMap;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void sendRoomChangeToTB(HashMap<String, String> map, String adsType,AdsMessage ads) throws Exception {
        
        String chainCode =  map.get("chainCode");
        String hotelCode =  map.get("hotelCode");
        List<Hotel> hotelList = hotelDao.getHotelByHotelCode(hotelCode);
        if(CommonUtil.isEmpty(hotelList)){
            log.info("hotelCode is close :"+hotelCode);
//            saveAdsToTBLog(ads.getEchoToken(), adsType,0, map.toString(), "该酒店已经下架");
            //ads.setTbStatus(AdsMessage.SEND_ERROR_TBSTATUS);
            return ;
        }
        String roomTypeCode = map.get("roomTypeCode");
        RoomTypeVO vo = new RoomTypeVO();
        vo.setHotelId(hotelList.get(0).getHotelId());
        vo.setRoomTypeCode(roomTypeCode);
        RoomTypeVO rtVo = roomTypeDao.getRoomTypeByCode(vo);
        if(CommonUtil.isEmpty(rtVo)){
            log.info("roomTypeCode is close :"+roomTypeCode);
//            saveAdsToTBLog(ads.getEchoToken(), adsType,0, map.toString(), "该房型已经下架");
            //ads.setTbStatus(AdsMessage.SEND_ERROR_TBSTATUS);
            return ;
        }
        String ratePlanCode = map.get("ratePlanCode");
        if(CommonUtil.isNotEmpty(ratePlanCode)){
            Rateplan rp = rateplanDao.getRateplanByRateplanCode(ratePlanCode, hotelCode);
            if(CommonUtil.isEmpty(rp)){
//                saveAdsToTBLog(ads.getEchoToken(), adsType,0, map.toString(), "该房价已经下架");
                log.info("ratePlanCode is close :"+ratePlanCode+"  hotelCode:"+hotelCode);
                //ads.setTbStatus(AdsMessage.SEND_ERROR_TBSTATUS);
                return ;
            }
        }
        
        Map<String,String> codeMap = new HashMap<String, String>();
        codeMap.put("chainCode", chainCode);
        codeMap.put("hotelCode", hotelCode);
        codeMap.put("roomTypeCode", roomTypeCode);
        codeMap.put("ratePlanCode", ratePlanCode);
        //获取产品
        String status="";   //对应一条ads 消息处理结果
        List<ChannelGoods> cgList = channelgoodsDao.getChannelgoodsByCodeMap(codeMap);
        if(CommonUtil.isNotEmpty(cgList)){
            for (ChannelGoods cg : cgList) {
              if(ChannelGoods.TBSYNCSTATUS_SYNC.intValue() == cg.getTbSyncStatus().intValue()){
                
                String gid_rpid = cg.getGidAndRpid();
                
                String[] gr = gid_rpid.split("-");
                ratePlanCode=cg.getRatePlanCode();
                Date startDate = new Date();
                Date endDate = DateUtil.addDays(new Date(), 89);
                List<Map<String,Object>> productMap = adsMessageDao.getInventoryByCode(hotelCode,roomTypeCode,ratePlanCode,startDate,endDate);
                HashMap<String,Object> rateMap = new HashMap<String, Object>();
                rateMap.put("gid", gr[0]);
                rateMap.put("rp_id", gr[1]);
                rateMap.put("data", channelgoodsManager.getInventoryPrice(productMap));
                List rateList = new ArrayList();
                rateList.add(rateMap);
                String rate_inventory_price_map = JSON.toJSONString(rateList);
                String sessionKey = userManager.getChainUserSessionKey(chainCode);
                log.info(chainCode +" chainCode sessionKey :"+sessionKey);
                InvokeResponse res = taobaoApiManager.ratesUpdate(rate_inventory_price_map,sessionKey );
                Integer tbStatus=0;
                if(res.getResObj() != null){
                    List<String> grList = (List<String>)res.getResObj();
                    log.info(CommonUtil.join(grList, ","));
                    tbStatus = 1;
                }else{
                    status += "0";
                    res.setErrMsg("数据异常或淘宝api异常！"+res.getErrMsg());
                    log.error(res.getErrMsg());
                }
                saveAdsToTBLog(ads.getEchoToken(), adsType,tbStatus, rate_inventory_price_map, res.getErrMsg());
            }
            else{
                log.info("product is close :"+cg.getProduceName() +codeMap.toString());
//                status += "0";
//                saveAdsToTBLog(ads.getEchoToken(), adsType,0, codeMap.toString(), "该产品未同步");
            }
         }
       }else{
           //status +="0";
           log.info("not find product :"+codeMap.toString());
//           saveAdsToTBLog(ads.getEchoToken(), adsType,0, codeMap.toString(), "该code未对应淘宝产品");
       }
        ads.setTbStatus(status.indexOf("0")>-1 ? AdsMessage.SEND_ERROR_TBSTATUS : AdsMessage.SEND_SUCCESS_TBSTATUS);
    }
    
    /**
     * 保存日志
     */
    @Override
    public AdsToTBLog saveAdsToTBLog(String echoToken, String adsType,Integer status, String content, String errMsg) {
        AdsToTBLog log = new AdsToTBLog();
        log.setEchoToken(echoToken);
        log.setAdsType(adsType);
        log.setContent(content);
        log.setErrMsg(errMsg);
        log.setStatus(status);
        log.setCreatedTime(new Date());
        return adsToTBLogDao.save(log);
    }

    @Override
    public List<AdsMessage> exportAdsLog(AdsLogMessageCriteria amc) {
        List<AdsMessage> adsList = new ArrayList<AdsMessage>();
        Integer rowcount = adsMessageDao.searchAdsLogCount(amc);
        Integer pagesize = amc.getPageSize();
        int pagecount = ((rowcount%pagesize==0)?(rowcount/pagesize):(rowcount/pagesize+1)); 
        for (int i = 1; i <= pagecount; i++) {
            amc.setPageNum(i);
            adsList.addAll(adsMessageDao.searchAdsLogList(amc));
        }
        return adsList;
    }
    
    @Override
	public HashMap<String, HashMap<String, String>> getParamCode() {
        HashMap<String, HashMap<String, String>> param = new HashMap<String, HashMap<String,String>>();
        HashMap<String, String> channelMap = new HashMap<String, String>();
        
        HashMap<String,String> chainMap = new HashMap<String, String>();
        List<Chain> chainList = chainDao.getAllChain();
        for (Chain c : chainList) {
        	String chainCode = c.getChainCode();
        	chainMap.put(c.getChainId(),chainCode);
		}
        List<Channel> channelList = channelManager.getAll();
        for (Channel channel : channelList) {
        	channelMap.put(channel.getChannelCode(), channel.getChannelCode());
		}
        param.put("channelMap", channelMap);
        param.put("chainMap", chainMap);
        return param;
    }
}
