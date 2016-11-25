package com.ccm.mc.webapp.action.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.ads.vo.AdsMessageResult;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.vo.RoomMsgCode;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.rsvtype.AdsToTBLog;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.ads.AdsManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.system.PushManage;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 畅联推送消息接收Action
 */
public class AdsBeqAction extends BaseAction {
    
    private static final long serialVersionUID = 3761240993639876089L;
    
    protected String message;
    protected Log onlineLog = LogFactory.getLog("online");

    private AdsLogMessageCriteria amc;

    private AdsMessageResult adsResult;

    private List<AdsToTBLog> tbLogList;

    private AdsToTBLog tbLog;

    private String adsId;

    private String adsToTBLogId;
    private String chainId;
    private String hotelId;
    private String field;
    private String hotelCode;
    private String channelCode;
    private String status;
    HashMap<String,HashMap<String,String>> configMap = new HashMap<String, HashMap<String,String>>();
    @Autowired
    private AdsManager adsTb2Manager;
    @Resource
    private PushManage pushManage;
    @Resource
    private ChannelgoodsManager channelgoodsManager;
    private HashMap<String,String> channelMap;
    @Resource
    private RatePlanManager ratePlanManager;
    @Resource
    private ChainManager chainManager;
    @Resource
    private HotelManager hotelManager;
    @Resource
    private RoomTypeManager roomTypeManager;
    @Resource
	private MongoTemplate mongoTemplate;
    @Resource
	private AdsMessageDao adsMessageErrDao;
    
	@Resource
	private ChannelManager channelManager;
	@Resource
	private HotelMCManager hotelMCManager;
	
    private List<String> channelCodeList;     //渠道代码
    private List<String> chainCodeList;      //集团代码
    private List<String> hotelCodeList;     //酒店代码
    private List<String> adsTypeList;      //消息类型
    private List<String> roomTypeCodeList;//房型代码
    private List<String> statusList;     //推送状态
    
	// 集团列表
	private List<Chain> chainList;
	// 渠道列表
	private List<Channel> channelList;
    
    public String extractMessage() {
        HttpServletRequest request = this.getRequest();
        String result = null;

        try {
            //读取消息
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
//             InputStream in = new FileInputStream(new File("D:/workspace/ccm/ccm-b2b/src/main/java/com/ccm/b2b/webapp/action/ads/1.xml"));
//             BufferedReader  br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            onlineLog.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
        }
        return result;
    }

    /**
     * 接收消息入口
     *
     * @return
     */
    public String execute() {
        return SUCCESS;
    }
    /**
     * 查询ads消息
     * @return
     */
    public String searchLog(){
        try {
			//configMap = adsTb2Manager.getParamCode();
			int pageSize = 30;//this.getCurrentPageSize("adsMsg");
			int pageNo = this.getCurrentPageNo("adsMsg");
			if(amc == null){
			    amc = new AdsLogMessageCriteria();
			    try {
			        amc.setStartDate(DateUtil.convertStringToDate(DateUtil.convertDateToString(new Date())));
			    } catch (ParseException e) {
			        e.printStackTrace();
			    }
			}
			//非导出数据时，按照外部分页的要求，仅提取一页的数据用于显示。
			amc.setPageNum(pageNo);
			amc.setPageSize(pageSize);
			amc.setMsgType(AdsMessage.MSGTYPE_PUSH);

			//集团必选
			if(CommonUtil.isNotEmpty(amc.getChainCodeList())){
				adsResult = adsTb2Manager.searchAdsLog(amc);
			}else{
				adsResult = new AdsMessageResult();
			}
			//configMap.put("hotelMap", adsTb2Manager.getHotelByChainId(amc.getChainId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//集团代码
		chainList = chainManager.getAllChain();
    	this.getRequest().setAttribute("chainList", chainList);
    	
		//渠道用户渠道权限
		B2BUser user = getCurLoginUser();
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			channelList=user.getChannels();
		}else{
			channelList = channelManager.getAll();
		}
		this.getRequest().setAttribute("channelList", channelList);
		
		//房型代码
		roomTypeCodeList=amc.getRoomTypeCodeList();
		List<String> roomTypeCodeListHidden=amc.getRoomTypeCodeListHidden();
		if(null!=roomTypeCodeListHidden && roomTypeCodeListHidden.size()>0){
			this.getRequest().setAttribute("roomTypeCodeListHidden", roomTypeCodeListHidden);
		}
		if(null!=roomTypeCodeList && roomTypeCodeList.size()>0){
			this.getRequest().setAttribute("roomTypeCodeList", roomTypeCodeList);
		}
		
		//推送状态
		HashMap<String,String> pushStatus = new HashMap<String,String>();
		pushStatus.put("1", getText("ccm.MessagePushLog.SentSuccessfully"));
		pushStatus.put("0", getText("ccm.MessagePushLog.Waiting"));
		pushStatus.put("9", getText("ccm.MessagePushLog.FailedToSent"));
		this.getRequest().setAttribute("pushStatus",pushStatus);
		
		List<String> pushStatusList = new ArrayList<String>();
		pushStatusList.add("1");
		pushStatusList.add("0");
		pushStatusList.add("9");
		this.getRequest().setAttribute("pushStatusList",pushStatusList);

		//消息类型
		HashMap<String,String> messageType = new HashMap<String,String>();
		messageType.put("FIDELIO_HotelProductNotifRQ","全量静态消息");
		messageType.put("FIDELIO_AvailabilityStatusRQ",getText("ccm.MessagePushLog.Rooms"));
		messageType.put("FIDELIO_RateUpdateNotifRQ",getText("ccm.RestrictionsManagement.Rate"));
		messageType.put("FIDELIO_AvailUpdateNotifRQ",getText("ccm.Channel.AvailabilityRestriction"));
		messageType.put("Switch_StayHistoryNotificationRQ","stayHistory");
		messageType.put("HOTELCODE",getText("ccm.ReservationMonitorReport.PropertyCode"));
		messageType.put("ROOMCODE",getText("ccm.Channel.RoomTypeCode"));
		messageType.put("RATECODE",getText("ccm.RestrictionsManagement.RateCode"));
		messageType.put("OTAHotelResModify","OTAHotelResModify");
		this.getRequest().setAttribute("messageType",messageType);
		
		List<String> messageTypeList = new ArrayList<String>();
		messageTypeList.add("FIDELIO_HotelProductNotifRQ");
		messageTypeList.add("FIDELIO_AvailabilityStatusRQ");
		messageTypeList.add("FIDELIO_RateUpdateNotifRQ");
		messageTypeList.add("FIDELIO_AvailUpdateNotifRQ");
		messageTypeList.add("Switch_StayHistoryNotificationRQ");
		messageTypeList.add("HOTELCODE");
		messageTypeList.add("ROOMCODE");
		messageTypeList.add("RATECODE");
		messageTypeList.add("OTAHotelResModify");
		this.getRequest().setAttribute("messageTypeList",messageTypeList);
		
        return "searchLog";
    }
    
    public void exportLog(){
        int pageSize = 1000;//this.getCurrentPageSize("adsMsg");
        if(amc == null){
            amc = new AdsLogMessageCriteria();
            try {
                amc.setStartDate(DateUtil.convertStringToDate(DateUtil.convertDateToString(new Date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        amc.setMsgType(AdsMessage.MSGTYPE_PUSH);
        amc.setPageSize(pageSize);
       
        try{
             //@SuppressWarnings("rawtypes")
			//List adsList = adsTb2Manager.exportAdsLog(amc);
            adsResult = adsTb2Manager.searchAdsLog(amc);
            if(null==adsResult){
            	adsResult = new AdsMessageResult();
            }
            // 生成excel文件名
            String excelName = ExportUtil.createFileName("adsLog");
            // 得到excel工作薄对象
            SXSSFWorkbook workbook = ExportUtil.createExcel_2007("adsLog", adsResult.getResultList());
            // 设置导出的excel文件从页面中下载
            getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
            getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + excelName + ".xlsx" + "\"");
            // 输出流
            OutputStream os = getResponse().getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            try {
                getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
                getResponse().getWriter().flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
	
    public void count(){
    	Query query = new Query();
    	if(CommonUtil.isNotEmpty(channelCode)){
    		query.addCriteria(Criteria.where("channelCode").is(channelCode));
    	}
    	if(CommonUtil.isNotEmpty(hotelCode)){
    		query.addCriteria(Criteria.where("hotelCode").is(hotelCode));
    	}
    	/*
     	if(CommonUtil.isNotEmpty(channelCodeList)){
    		query.addCriteria(Criteria.where("channelCode").in(channelCodeList));
    	}
     	if(CommonUtil.isNotEmpty(hotelCodeList)){
    		query.addCriteria(Criteria.where("hotelCode").in(hotelCodeList));
    	}
     	*/
    	query.addCriteria(Criteria.where("sendStatus").is("0"));
    	long count = this.mongoTemplate.count(query, RoomMsg.class);
    	ajaxResponse("count: "+count+"\n");
    }
   
	@SuppressWarnings("static-access")
	public void msgCount(){
    	//运营用户权限
    	B2BUser user=getCurLoginUser();	
    	if("1".equals(user.getCompanyId())){
    		List<RoomMsgCode> rcList = mongoTemplate.find(new Query(), RoomMsgCode.class);
    		ajaxResponse(JSONArray.toJSONString(rcList),this.CONTENT_TYPE_JSON);
    	}
    	
    }
    //动态加载酒店代码
    public void ajaxGetHotelCode() throws Exception{
        //HashMap<String, String> hotelMap = adsTb2Manager.getHotelByChainId(chainId);
    	B2BUser user = getCurLoginUser();
        List<Hotel> hotels = new ArrayList<Hotel>();
    	String chainCodes = getRequest().getParameter("chainCodes");
    	if (null!=chainCodes && StringUtils.isNotBlank(chainCodes)) 
		{
			String[] chainCodeArr = chainCodes.split(",");
			for (String chainCode : chainCodeArr) {
				// 如果是运营人员
				if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
					hotels.addAll(hotelManager.getHotelByChainCode(chainCode));
				} else {
					hotels.addAll(hotelMCManager.getUserHotelByChainCodeAndUserId(chainCode, user.getUserId()));
				}
			}
		}
        String hotelJosnStr = "";
        if(hotels != null && hotels.size() > 0){
        	hotelJosnStr = JSON.toJSONString(hotels);
        }
        ajaxResponse(hotelJosnStr,CONTENT_TYPE_JSON);
    }
    
    public void ajaxGetRoomTypeCode() throws Exception{
    	String hotelCodes = getRequest().getParameter("hotelCodes");
    	List<String> roomTypeList = new ArrayList<String>();
    	HashSet<String> mSets = new HashSet<String>(); 
    	if (null!=hotelCodes && StringUtils.isNotBlank(hotelCodes)) 
		{
			String[] hotelCodeArr = hotelCodes.split(",");
			if(null!=hotelCodeArr && hotelCodeArr.length<51){
				for (String hotelCode : hotelCodeArr) {
					List<Hotel> hList=hotelManager.getHotelByHotelCode(hotelCode);
					if(null!=hList && null!=hList.get(0)){
						List<RoomType> rts=roomTypeManager.getRoomTypeByHotelId(hList.get(0).getHotelId());
						for (RoomType roomType : rts) {
							if(null!=roomType){
								mSets.add(roomType.getRoomTypeCode());
							}
						}
					}
				}
			}
		 }
    	
		/*
		 if(hotelCodeArr.length==1 && hotelCodeArr[0].equals("ALLHotelCodes")){
				List<Hotel> hList=hotelManager.getAll();
				for (Hotel hotel : hList) {
					if(null!=hotel){
						List<RoomType> rts=roomTypeManager.getRoomTypeByHotelId(hotel.getHotelId());
						for (RoomType roomType : rts) {
							if(null!=roomType){
								mSets.add(roomType.getRoomTypeCode());
							}
						}
				    }
			     }
		     }else{
				for (String hotelCode : hotelCodeArr) {
					List<Hotel> hList=hotelManager.getHotelByHotelCode(hotelCode);
					if(null!=hList && null!=hList.get(0)){
							List<RoomType> rts=roomTypeManager.getRoomTypeByHotelId(hList.get(0).getHotelId());
							for (RoomType roomType : rts) {
								if(null!=roomType){
									mSets.add(roomType.getRoomTypeCode());
								}
							}
						}
					}
			    }
		} 
		*/	
    	
        String roomJosnStr = ""; 
        if(mSets.size() > 0){
        	roomTypeList.addAll(mSets);
        	roomTypeList.remove(null);
        	roomJosnStr = JSON.toJSONString(roomTypeList);
        }
        ajaxResponse(roomJosnStr,CONTENT_TYPE_JSON);
    }

    public void ajaxGetRatePlanCode() throws Exception{
    	List<Rateplan> rpList = ratePlanManager.getRatePlanByHotelId(hotelId);
        HashSet<String> rateplanSet = new HashSet<String>();
        for (Rateplan rp : rpList) {
        	rateplanSet.add(rp.getRatePlanCode());
		}
        String rateJosnStr =  JSON.toJSONString(rateplanSet) ;
        ajaxResponse(rateJosnStr,CONTENT_TYPE_JSON);
    }
    
    public void ajaxGetRatePlan() throws Exception{
        ajaxResponse(JSON.toJSONString(channelgoodsManager.getChannelRatePlanMap().get(field)),CONTENT_TYPE_JSON);
    }
    public void ajaxGetRoomType() throws Exception{
    	HashSet<String> roomSet = new HashSet<String>();
    	
    	if(CommonUtil.isNotEmpty(field)){
    		String[] f = field.replaceAll("null", "").split("_");
    		if(f != null && f.length > 0){
    			String channel = f[0];
        		
        		HashMap<String, HashSet<String>> map = channelgoodsManager.getRatePlanRoomTypeMap();
        		if(f.length > 1){
        			String rate = f[1];
        			roomSet = map.get(channel+rate);
        		}else{
        			for (Entry<String, HashSet<String>> e : map.entrySet()) {
        				if(e.getKey().indexOf(channel) > -1){
        					roomSet.addAll(e.getValue());
        				}
        			}
        		}
        		ajaxResponse(JSON.toJSONString(roomSet),CONTENT_TYPE_JSON);
    		}
    	}
    }
    public String handSend(){
        channelMap = channelgoodsManager.getEnabledChannelCodeMap();
        return "handSend";
    }
    
    public void ajaxSendErrRoomMsg(){
    	System.out.println("ajaxSendErrRoomMsg....");
    	try {
    		
    		Map<String,HotelVO> hotelMap = new HashMap<String, HotelVO>();
    		List<HotelVO> hvoList = hotelManager.getAllHotels();
    		for (HotelVO hotelVO : hvoList) {
    			hotelVO.setChainCode("CCM");
    			hotelMap.put(hotelVO.getHotelCode(),hotelVO);
			}
    		String[] adsTypes = new String[]{AdsMessage.ADSTYPE_AVAILABILITY,AdsMessage.ADSTYPE_AVAILUPDATE,AdsMessage.ADSTYPE_RATE};
    		for (String adsType : adsTypes) {
    			AdsLogMessageCriteria amc = new AdsLogMessageCriteria();
    			amc.setStatus(AdsMessage.EXEC_ERROR_STATUS);
    			amc.setStartDate(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", "2016-04-28 11:30:00"));
    			amc.setEndDate(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", "2016-04-28 14:30:00"));
    			amc.setPageSize(500);
    			amc.setAdsType(adsType);
    			adsResult = adsTb2Manager.searchAdsLog(amc);
    			List<AdsMessage> adsList = adsResult.getResultList();
    			while(CommonUtil.isNotEmpty(adsList) && adsList.size() > 0){
    System.out.println("adsErrResult TotalCount:"+adsResult.getTotalCount()+"  adsList.size:"+adsList.size());
    				long startMili2 = System.currentTimeMillis();
    				this.sendErrRoomMsg(adsResult,hotelMap);
    System.out.println("sendErrRoomMsg 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
    				adsResult = adsTb2Manager.searchAdsLog(amc);
    				adsList = adsResult.getResultList();
    			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse(CommonUtil.getExceptionMsg(e, "ccm"));
		}
System.out.println("ajaxSendErrRoomMsg  END");
    }
    private void sendErrRoomMsg(AdsMessageResult adsResult,Map<String,HotelVO> hotelMap) throws Exception{
    	List<AdsMessage> adsList = adsResult.getResultList();
		for (AdsMessage ads : adsList) {
			amc = new AdsLogMessageCriteria();
			amc.setTargetGDS(ads.getTargetGDS());
			amc.setRoomTypeCode(ads.getRoomTypeCode());
			amc.setRatePlanCode(ads.getRatePlanCode());
			amc.setStartDate(ads.getActionDate());
			amc.setEndDate(amc.getStartDate());
			amc.setAdsType(ads.getAdsType());
			if(ads.getAdsType().equals(AdsMessage.ADSTYPE_AVAILABILITY)
					|| ads.getAdsType().equals(AdsMessage.ADSTYPE_AVAILUPDATE)
					|| ads.getAdsType().equals(AdsMessage.ADSTYPE_RATE)){
				HotelVO hvo = hotelMap.get(ads.getHotelCode());
				if (hvo != null) {
					String errMsg = "";
					String sta="";
					try {
						if (pushManage.handSend(amc, hvo, LanguageCode.MAIN_LANGUAGE_CODE)) {
							sta="8";//成功
						}else{
							sta="7";//推送失败
						}
					} catch (Exception e) {
						e.printStackTrace();
						errMsg = CommonUtil.getExceptionMsg(e, "ccm");
					}
					adsMessageErrDao.updateAdsMessageStatus(ads.getAdsId(), sta, errMsg, ads.getHotelCode());
				}
			}
		}
    }
    
    public void ajaxSendRoomMsg() throws Exception{
        try {
            final HotelVO hotelvo = SecurityHolder.getB2BUser().getHotelvo();
            Locale locale = ActionContext.getContext().getLocale();
            final String language = locale.getLanguage()+"_"+locale.getCountry();
            if(hotelvo==null){
            	log.warn(getText("ccm.Channel.message.UserFailure"));
            	ajaxResponse(getText("ccm.Channel.message.UserFailure"));
            	return;
            }
          //异步处理
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						pushManage.handSend(amc,hotelvo,language);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			ajaxResponse(getText("ccm.Channel.message.ActivityIsDone"));
		} catch (Exception e) {
			ajaxResponse(getText("ccm.Channel.message.ActivityIsFailed"));
			e.printStackTrace();
		}
    }
    public String getAdsMessageField(){
//        field="content";
        if(tbLog == null){
            tbLog = new AdsToTBLog();
        }
        String content = adsTb2Manager.getAdsMessageFieldValue(field,adsId,hotelCode,status);
        try {
        	getResponse().setContentType("text/plain; charset=utf-8");
            ajaxResponse(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getAdsMessageActionValue(){
//    	field="actionValue";
    	String content = adsTb2Manager.getAdsMessageFieldValue(field,adsId,hotelCode,status);
        try {
        	getResponse().setContentType("text/plain; charset=utf-8");
            ajaxResponse(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdsLogMessageCriteria getAmc() {
        return amc;
    }

    public void setAmc(AdsLogMessageCriteria amc) {
        this.amc = amc;
    }

    public AdsMessageResult getAdsResult() {
        return adsResult;
    }

    public void setAdsResult(AdsMessageResult adsResult) {
        this.adsResult = adsResult;
    }

    public List<AdsToTBLog> getTbLogList() {
        return tbLogList;
    }

    public void setTbLogList(List<AdsToTBLog> tbLogList) {
        this.tbLogList = tbLogList;
    }

    public void setTbLog(AdsToTBLog tbLog) {
        this.tbLog = tbLog;
    }

    public AdsToTBLog getTbLog() {
        return tbLog;
    }

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    public String getAdsToTBLogId() {
        return adsToTBLogId;
    }

    public void setAdsToTBLogId(String adsToTBLogId) {
        this.adsToTBLogId = adsToTBLogId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public HashMap<String, HashMap<String, String>> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(HashMap<String, HashMap<String, String>> configMap) {
        this.configMap = configMap;
    }

    public HashMap<String,String> getChannelMap() {
        return channelMap;
    }

    public void setChannelMap(HashMap<String,String> channelMap) {
        this.channelMap = channelMap;
    }

	public String getChainId() {
		return chainId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public List<String> getChannelCodeList() {
		return channelCodeList;
	}

	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}

	public List<String> getChainCodeList() {
		return chainCodeList;
	}

	public void setChainCodeList(List<String> chainCodeList) {
		this.chainCodeList = chainCodeList;
	}

	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}

	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
	}

	public List<String> getAdsTypeList() {
		return adsTypeList;
	}

	public void setAdsTypeList(List<String> adsTypeList) {
		this.adsTypeList = adsTypeList;
	}

	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}

	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
    
}
