package com.ccm.api.service.log.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.bdp.BdpDisconnectedMsgMongo;
import com.ccm.api.dao.bdp.DSInfoMongo;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.ratePlan.RateDetailDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.model.bdp.AdsPushErrorCount;
import com.ccm.api.model.bdp.AriPushErrorMsg;
import com.ccm.api.model.bdp.BdpRecord;
import com.ccm.api.model.bdp.BdpTb;
import com.ccm.api.model.bdp.DSInfo;
import com.ccm.api.model.bdp.OWSReservation;
import com.ccm.api.model.bdp.OxiApiDisconnectedMsg;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.log.BdpMsgManager;
import com.ccm.api.util.ApacheHttpProxyClient;
import com.ccm.api.util.BdpDataUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("bdpMsgManager")
public class BdpMsgManagerImpl implements BdpMsgManager {

	@Resource 
	private BdpDisconnectedMsgMongo bdpDisconnectedMsgMongo;
    @Resource
    private AdsMessageDao adsMessageErrDao;
    @Resource
    private MasterDao masterDao;
    @Resource
    DSInfoMongo dsInfoMongo;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private ChannelgoodsManager channelgoodsManager;
    @Resource
    private RateDetailDao rateDetailDao;
    @Resource
    private RateplanDao rateplanDao;
    @Resource
    private RestrictionCalcDao restrictionCalcDao ;
	// 项目配置属性
	private Properties projectProperties;
	
	@Resource
	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}
    private void initBDPConfig(){
    	DSInfo dsInfo=dsInfoMongo.addBDPInfo();
    	BdpDataUtil.ACCESS_TOKEN=dsInfo.getAccessToken();
    	BdpDataUtil.DS_ID=dsInfo.getDsId();
    	BdpDataUtil.URL_GET_DS=dsInfo.getGetDsURL();
    	BdpDataUtil.URL_GET_TB=dsInfo.getGetTbURL();
    	BdpDataUtil.URL_CREATE_TB=dsInfo.getCreateTbURL();
    	BdpDataUtil.URL_CLS_TB=dsInfo.getCleanTbURL();
    	BdpDataUtil.URL_INSERT_DATA=dsInfo.getCreateDataURL();
    	BdpDataUtil.URL_UPDATE_DATA=dsInfo.getModifyDataURL();
    	BdpDataUtil.URL_COMMIT_DATA=dsInfo.getCommitDataURL();
    	Map <String,String> tbIdMap=dsInfo.getTbIdMap();
    	BdpDataUtil.OXI_API_DISCONNECTED_MSG_ID=tbIdMap.get(OxiApiDisconnectedMsg.class.getSimpleName());
    	BdpDataUtil.ADS_PUSH_ERROR_MSG_ID=tbIdMap.get(AriPushErrorMsg.class.getSimpleName());
    	BdpDataUtil.CCM_OWS_RESERVATION_ID=tbIdMap.get(OWSReservation.class.getSimpleName());
    }
    @Override
    public List<AdsPushErrorCount> getAdsMessageErrorCountLastDate(){
    	List<HotelVO>hotelList=hotelDao.getAllHotels();
    	int num=100;
		List<List> hotelArrayLists = CommonUtil.splitList(hotelList, num);
    	List<AdsPushErrorCount>  resultList=new Vector<AdsPushErrorCount>();
    	for(List<HotelVO> subList:hotelArrayLists){
    		List<String> hotelCodeList= new Vector<String>();
    		Map<String,String>hotelMap=new HashMap<String,String>();
    		Set<String>hotelSet=new HashSet<String>();
	    	for(HotelVO h:subList){
	    		if(h.getHotelCode().equalsIgnoreCase("AAAAA")||h.getHotelCode().equalsIgnoreCase("TESTCCM"))
					continue;
	    		hotelCodeList.add(h.getHotelCode());
	    		hotelMap.put(h.getHotelCode(), h.getHotelName());
	    		hotelSet.add(h.getHotelCode());
	    	}
	    	AdsPushErrorCount adsPushErrorCount=new AdsPushErrorCount();
        	adsPushErrorCount.setLastDate(getLastDateStr());
        	adsPushErrorCount.setHotelCodeList(hotelCodeList);
        	List <AdsPushErrorCount> hoteladsPushCountList=adsMessageErrDao.getAdsErrorMsgLastDate(adsPushErrorCount);
        	for(AdsPushErrorCount adspusherror:hoteladsPushCountList){
        		adspusherror.setHotelName(hotelMap.get(adspusherror.getHotelCode()));
        		hotelSet.remove(adspusherror.getHotelCode());
        	}
        	resultList.addAll(hoteladsPushCountList);
        	for(String hotelCode:hotelSet){
        		AdsPushErrorCount missHotelAdsPushCount=new AdsPushErrorCount();
        		missHotelAdsPushCount.setHotelCode(hotelCode);
        		missHotelAdsPushCount.setHotelName(hotelMap.get(hotelCode));
        		missHotelAdsPushCount.setQtr(1);
        		missHotelAdsPushCount.setNumberOfTimes(1);
        		resultList.add(missHotelAdsPushCount);
        		}
        	
    	}
    	return resultList;
    }
    
    private String getLastDateStr(){
    	String lastDateStr=DateUtil.getDate(DateUtil.addDays(DateUtil.currentDate(),-1));
    	return lastDateStr;
    }
    
    private String getDateOfTomorrow(){
    	String tomorrowStr=DateUtil.getDate(DateUtil.addDays(DateUtil.currentDate(),1));
    	return tomorrowStr;
    }
    
    @Override
	public void addAllDisconnectedMsgOfToday(){
		List<HotelVO> hotelList = hotelDao.getAllDirectPmsHotel("zh_CN",true);
		for(HotelVO h:hotelList){
				if(h.getHotelCode().equalsIgnoreCase("AAAAA")||h.getHotelCode().equalsIgnoreCase("TESTCCM"))
					continue;
				OxiApiDisconnectedMsg newMsg=new OxiApiDisconnectedMsg();
				if(h.getIsPMSHeartBeat()){
					newMsg.setIsMonitorHeartBeat("Y");
				}else{
					newMsg.setIsMonitorHeartBeat("N");
				}
				newMsg.setId(UUID.randomUUID().toString().replace("-", ""));
				newMsg.setDisconnectedQuantum(new Long(60*60));
				newMsg.setNumberOfTimes(Double.valueOf(Math.floor(newMsg.getDisconnectedQuantum()/300)).intValue());
				newMsg.setHotelCode(h.getHotelCode());
				newMsg.setHotelName(h.getHotelName());
				Calendar calendar = Calendar.getInstance();
				Date nextTime=DateUtil.addHours(calendar.getTime(),1);
				newMsg.setMsgDate(DateUtil.getDate(nextTime));
				newMsg.setHour(nextTime.getHours());
				bdpDisconnectedMsgMongo.save(newMsg);
		}
	}
    // add tomorrow data every day
	@Override
	public void addAllDisconnectedMsgOfTmr(){
		Calendar calendar = Calendar.getInstance();
		Date nextTime=DateUtil.addHours(calendar.getTime(),1);
		List<HotelVO> hotelList = hotelDao.getAllDirectPmsHotel("zh_CN",true);
		for(HotelVO h:hotelList){
				if(h.getHotelCode().equalsIgnoreCase("AAAAA")||h.getHotelCode().equalsIgnoreCase("TESTCCM"))
					continue;
				OxiApiDisconnectedMsg newMsg=new OxiApiDisconnectedMsg();
				if(h.getIsPMSHeartBeat()){
					newMsg.setIsMonitorHeartBeat("Y");
				}else{
					newMsg.setIsMonitorHeartBeat("N");
				}
				newMsg.setId(UUID.randomUUID().toString().replace("-", ""));
				newMsg.setDisconnectedQuantum(new Long(60*60));
				newMsg.setNumberOfTimes(Double.valueOf(Math.floor(newMsg.getDisconnectedQuantum()/300)).intValue());
				newMsg.setHotelId(h.getHotelId());
				newMsg.setHotelCode(h.getHotelCode());
				newMsg.setHotelName(h.getHotelName());
				newMsg.setMsgDate(DateUtil.getDate(nextTime));
				newMsg.setHour(nextTime.getHours());
				bdpDisconnectedMsgMongo.save(newMsg);
		}
	}
	
	@Override
	public void updateOxiApiDisconnectedMsg(ReceivePmsLog receivePmsLog,ReceivePmsLog oldReceivePmsLog) {
		// TODO Auto-generated method stub
		try{
		OxiApiDisconnectedMsg  msg=buildMsg(receivePmsLog,oldReceivePmsLog);
		if(CommonUtil.isNotEmpty(msg)){
			bdpDisconnectedMsgMongo.findAndUpdateMsg(msg);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private OxiApiDisconnectedMsg buildMsg(ReceivePmsLog newReceivePmsLog,ReceivePmsLog oldReceivePmsLog){
		Date now=null;
		
		if(newReceivePmsLog.getReceiveMsgLogLastTime()==null){
			now=newReceivePmsLog.getLastModifyTime();
		}else{
			now=newReceivePmsLog.getReceiveMsgLogLastTime();
			}
		if(now==null)
			now=new Date();
		OxiApiDisconnectedMsg conditionMsg=new OxiApiDisconnectedMsg();
		conditionMsg.setHotelCode(newReceivePmsLog.getHotelCode());
		conditionMsg.setMsgDate(DateUtil.getDate(now));
		conditionMsg.setHour(now.getHours());
		long intervalSec=300;
		Date beginDate=null;
		try {
			beginDate=DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",DateUtil.convertDateHourToString(now));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(CommonUtil.isNotEmpty(oldReceivePmsLog)){
			Date lastTime=null;
			
			if(oldReceivePmsLog.getReceiveMsgLogLastTime()==null){
				lastTime=oldReceivePmsLog.getLastModifyTime();
			}else{
				lastTime=oldReceivePmsLog.getReceiveMsgLogLastTime();
				}
			if(oldReceivePmsLog.getReceiveMsgLogLastTime()!=null&&oldReceivePmsLog.getLastModifyTime()!=null){
				if(oldReceivePmsLog.getReceiveMsgLogLastTime().after(oldReceivePmsLog.getLastModifyTime())){
					lastTime=oldReceivePmsLog.getReceiveMsgLogLastTime();
				}else
					lastTime=oldReceivePmsLog.getLastModifyTime();
			}
			
			if(lastTime.getHours()!=now.getHours()){
			//在断开时间在前一个小时
				intervalSec=(now.getTime()-beginDate.getTime())/1000;
			}else
				intervalSec=(now.getTime()-lastTime.getTime())/1000;
		}else{
			intervalSec=(now.getTime()-beginDate.getTime())/1000;
		}
		OxiApiDisconnectedMsg disconntectedMsg=bdpDisconnectedMsgMongo.findMsgByParam(conditionMsg);
		if(CommonUtil.isEmpty(disconntectedMsg))
			return null;
		if(intervalSec>=300){
			disconntectedMsg.setDisconnectedQuantum(disconntectedMsg.getDisconnectedQuantum()-300);
		}else{
			disconntectedMsg.setDisconnectedQuantum(disconntectedMsg.getDisconnectedQuantum()-intervalSec);
		}
		long disQuantum=disconntectedMsg.getDisconnectedQuantum();
		disconntectedMsg.setDisconnectedQuantum(disQuantum<0?0:disQuantum);
		int numberOfTimes=Double.valueOf(Math.ceil(new Float(disconntectedMsg.getDisconnectedQuantum().floatValue())/new Float(300))).intValue();
		disconntectedMsg.setNumberOfTimes(numberOfTimes<0?0:numberOfTimes);
		return disconntectedMsg;
	}

	private List<OxiApiDisconnectedMsg> getDisconnectedMsgsOfYesterday(Date yeasterday) {
		// TODO Auto-generated method stub
		OxiApiDisconnectedMsg msg=new OxiApiDisconnectedMsg();
		msg.setMsgDate(DateUtil.getDate(yeasterday));
		List<OxiApiDisconnectedMsg> msgList=bdpDisconnectedMsgMongo.findMsgList(msg);
		long t1=System.currentTimeMillis();
		System.out.println("开始 这段代码运行了:"+t1/1000.0 + "秒！"+"一共  "+msgList.size()+" 条");
		int effectiveCount=0;
		for(OxiApiDisconnectedMsg oxiApiDisconnectedMsg:msgList){
			String channels=retrieveChannelsForYesterday(oxiApiDisconnectedMsg);
			if(CommonUtil.isNotEmpty(channels)){
				oxiApiDisconnectedMsg.setChannels(channels);
				effectiveCount++;
			}else{
				oxiApiDisconnectedMsg.setNumberOfTimes(0);
				oxiApiDisconnectedMsg.setDisconnectedQuantum(new Long(0));
			}
		}
		System.out.println("结束 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"有效记录  "+effectiveCount+" 条");
		return msgList;
	
	}
	
	private List<OxiApiDisconnectedMsg> getDisconnectedMsgsOfLastHour(Date lastHour) {
		// TODO Auto-generated method stub
		OxiApiDisconnectedMsg msg=new OxiApiDisconnectedMsg();
		msg.setMsgDate(DateUtil.getDate(lastHour));
		msg.setHour(lastHour.getHours());
		List<OxiApiDisconnectedMsg> msgList=bdpDisconnectedMsgMongo.findMsgList(msg);
		long t1=System.currentTimeMillis();
		System.out.println("开始 这段代码运行了:"+t1/1000.0 + "秒！"+"一共  "+msgList.size()+" 条");
		int effectiveCount=0;
		for(OxiApiDisconnectedMsg oxiApiDisconnectedMsg:msgList){
			String channels=retrieveChannels(oxiApiDisconnectedMsg);
			if(CommonUtil.isNotEmpty(channels)){
				oxiApiDisconnectedMsg.setChannels(channels);
				effectiveCount++;
			}else{
				oxiApiDisconnectedMsg.setNumberOfTimes(0);
				oxiApiDisconnectedMsg.setDisconnectedQuantum(new Long(0));
			}
		}
		System.out.println("结束 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"有效记录  "+effectiveCount+" 条");
		return msgList;
	
	}
	
	private String retrieveChannelsForYesterday(OxiApiDisconnectedMsg oxiApiDisconnectedMsg){
		StringBuffer sb=new StringBuffer();
		ChannelGoodsVO cgvoOfCondtion = new ChannelGoodsVO();
		cgvoOfCondtion.setHotelId(oxiApiDisconnectedMsg.getHotelId());
		List<ChannelGoodsVO>cgvoList=channelgoodsManager.getEnabledChannelGoods(cgvoOfCondtion);
		if(CommonUtil.isEmpty(cgvoList))
			return null;
		Date yesterday=DateUtil.addDays(DateUtil.currentDate(),-1);
		Map <String,String> channelMap=new HashMap <String,String>();
		for(ChannelGoodsVO cgvo:cgvoList){
			//验证房价90天有效性
			if(channelMap.containsKey(cgvo.getChannelCode())){
				continue;
			}
			Integer validRateCount=rateDetailDao.getValidCountOfRateDetail(cgvo.getRatePlanId(),DateUtil.addDays(yesterday,89));
			if(validRateCount==0){
				continue;
			}
			//验证产品开关90天有效性
			RestrictionCalc rc = new RestrictionCalc();
			rc.setChannelCode(cgvo.getChannelCode());
			rc.setHotelCode(oxiApiDisconnectedMsg.getHotelCode());
			rc.setRoomTypeCode(cgvo.getRoomTypeCode());
			rc.setRatePlanCode(cgvo.getRatePlanCode());
			rc.setStartDate(DateUtil.getDate(yesterday));
			rc.setEndDate(DateUtil.getDate(DateUtil.addDays(yesterday,89)));
			//关闭
			rc.setOnOff(0);
			Integer rcCount = restrictionCalcDao.getRestrictionCalcCountByObj(rc);
			if(rcCount>89){
				continue;
			}
			channelMap.put(cgvo.getChannelCode(),null);
		}
		for(String channelCode:channelMap.keySet()){
			sb.append(channelCode+",");
		}
		if(sb.length()>0){
			return sb.substring(0, sb.length()-1);
		}else{
			return null;
		}
	}
	
	private String retrieveChannels(OxiApiDisconnectedMsg oxiApiDisconnectedMsg){
		StringBuffer sb=new StringBuffer();
		ChannelGoodsVO cgvoOfCondtion = new ChannelGoodsVO();
		cgvoOfCondtion.setHotelId(oxiApiDisconnectedMsg.getHotelId());
		List<ChannelGoodsVO>cgvoList=channelgoodsManager.getEnabledChannelGoods(cgvoOfCondtion);
		if(CommonUtil.isEmpty(cgvoList))
			return null;
		Date curDate=DateUtil.currentDate();
		Map <String,String> channelMap=new HashMap <String,String>();
		for(ChannelGoodsVO cgvo:cgvoList){
			//验证房价90天有效性
			if(channelMap.containsKey(cgvo.getChannelCode())){
				continue;
			}
			Rateplan rp=rateplanDao.getRatePlanById(cgvo.getRatePlanId());
			//验证房价存在
			if(CommonUtil.isEmpty(rp)){
				continue;
				}
			String rpId=null;
			//validate whether the rate is parent or child
			if(CommonUtil.isEmpty(rp.getInheritRatePlanId())){
				rpId=cgvo.getRatePlanId();
			}else{
				rpId=rp.getInheritRatePlanId();
			}
			Integer validRateCount=rateDetailDao.getValidCountOfRateDetail(rpId,DateUtil.addDays(curDate,89));
			if(validRateCount==0){
				continue;
			}
			//验证产品开关90天有效性
			RestrictionCalc rc = new RestrictionCalc();
			rc.setChannelCode(cgvo.getChannelCode());
			rc.setHotelCode(oxiApiDisconnectedMsg.getHotelCode());
			rc.setRoomTypeCode(cgvo.getRoomTypeCode());
			rc.setRatePlanCode(cgvo.getRatePlanCode());
			rc.setStartDate(DateUtil.getDate(curDate));
			rc.setEndDate(DateUtil.getDate(DateUtil.addDays(curDate,89)));
			//关闭
			rc.setOnOff(0);
			Integer rcCount = restrictionCalcDao.getRestrictionCalcCountByObj(rc);
			if(rcCount>89){
				continue;
			}
			channelMap.put(cgvo.getChannelCode(),null);
		}
		for(String channelCode:channelMap.keySet()){
			sb.append(channelCode+",");
		}
		if(sb.length()>0){
			return sb.substring(0, sb.length()-1);
		}else{
			return null;
		}
	}
	@Override
	public void removeDisconnectedMsgsOfLastHour(Date lastHour){
		OxiApiDisconnectedMsg msg=new OxiApiDisconnectedMsg();
		msg.setMsgDate(DateUtil.getDate(lastHour));
		msg.setHour(lastHour.getHours());
		bdpDisconnectedMsgMongo.removeMsg(msg);
	}
	
	// send  the data of yesterday to bdp
	@Override
	public void createOxiApiDisconnectedMsgOfYesterday(){
		initBDPConfig();
		Date yesterday=DateUtil.addDays(new Date(),-1);
		List<BdpRecord> records=BdpRecord.buildRecords(getDisconnectedMsgsOfYesterday(yesterday),BdpDataUtil.ACCESS_TOKEN, BdpDataUtil.OXI_API_DISCONNECTED_MSG_ID);
		createData(records);
		BdpTb.updateTable(BdpDataUtil.OXI_API_DISCONNECTED_MSG_ID);
	}
	// send  the data of last hour to bdp
	@Override
	public void createOxiApiDisconnectedMsgOfLastDate(){
		initBDPConfig();
		Date lastHour=DateUtil.addHours(new Date(),-1);
		List<BdpRecord> records=BdpRecord.buildRecords(getDisconnectedMsgsOfLastHour(lastHour),BdpDataUtil.ACCESS_TOKEN, BdpDataUtil.OXI_API_DISCONNECTED_MSG_ID);
		createData(records);
		removeDisconnectedMsgsOfLastHour(lastHour);
		BdpTb.updateTable(BdpDataUtil.OXI_API_DISCONNECTED_MSG_ID);
	}
	
	@Override
	public void createAdsMsgCountOfLastDate(){
		initBDPConfig();
		List<AdsPushErrorCount> adsMsgCountList=getAdsMessageErrorCountLastDate();
		List<AriPushErrorMsg> ariPushErrorMsgList=AriPushErrorMsg.buildMsgList(adsMsgCountList, getLastDateStr());
		List<BdpRecord> records=BdpRecord.buildRecords(ariPushErrorMsgList,BdpDataUtil.ACCESS_TOKEN, BdpDataUtil.ADS_PUSH_ERROR_MSG_ID);
		createData(records);
		BdpTb.updateTable(BdpDataUtil.ADS_PUSH_ERROR_MSG_ID);
	}
	
	private void createData(List<BdpRecord> records){
		Boolean bdpLaunch=new Boolean(projectProperties.getProperty("bdpLaunch"));
		if(bdpLaunch){
		for(BdpRecord record:records){
			ApacheHttpProxyClient.postJson(record.getOperationURL(BdpDataUtil.URL_INSERT_DATA), JSONObject.toJSONString(record.getValues()),true).toJSONString();
			ApacheHttpProxyClient.get(record.getCommitURL(BdpDataUtil.URL_COMMIT_DATA),true);
		}
		}
	}
	
	private void modifyData(List<BdpRecord> records){
		Boolean bdpLaunch=new Boolean(projectProperties.getProperty("bdpLaunch"));
		if(bdpLaunch){
		for(BdpRecord record:records){
			ApacheHttpProxyClient.postJson(record.getOperationURL(BdpDataUtil.URL_UPDATE_DATA), JSONObject.toJSONString(record.getValues()),true).toJSONString();
			ApacheHttpProxyClient.get(record.getCommitURL(BdpDataUtil.URL_COMMIT_DATA),true);
		}
		}
	}
	@Override
	public void printRecords(List<BdpRecord> records){
		for(BdpRecord record:records){
			System.out.println(record.getOperationURL(BdpDataUtil.URL_INSERT_DATA));
			System.out.println(JSONObject.toJSONString(record.getValues()));
		}
	}
	
	@Override
	public void createOWSReservationOfLastDate(){
		initBDPConfig();
		List<OWSReservation> owReservationList=masterDao.getCreatedOrderHistoryYesterDay(getLastDateStr());
		List<BdpRecord> records=BdpRecord.buildRecords(owReservationList,BdpDataUtil.ACCESS_TOKEN, BdpDataUtil.CCM_OWS_RESERVATION_ID);
		createData(records);
	}
	
	@Override
	public void modifyOWSReservationOfLastDate(){
		initBDPConfig();
		List<OWSReservation> owReservationList=masterDao.getModifedOrderHistoryYesterDay(getLastDateStr());
		List<BdpRecord> records=BdpRecord.buildRecords(owReservationList,BdpDataUtil.ACCESS_TOKEN, BdpDataUtil.CCM_OWS_RESERVATION_ID);
		modifyData(records);
	}
	
	@Override
	public void pushOWSReservationOfLastDate(){
		createOWSReservationOfLastDate();
		modifyOWSReservationOfLastDate();
		BdpTb.updateTable(BdpDataUtil.CCM_OWS_RESERVATION_ID);
	}
}
