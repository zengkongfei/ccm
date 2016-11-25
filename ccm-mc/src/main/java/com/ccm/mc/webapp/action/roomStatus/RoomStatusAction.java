package com.ccm.mc.webapp.action.roomStatus;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.pms.utils.DateUtils;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.common.PessimisticLock;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.vo.ChannelRateplanVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusJsonVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusSetVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.sell.OverbookingGroup;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.ChannelHotelManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.AsyncSendManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.service.rsvtype.RsvtypeManager;
import com.ccm.api.service.sell.OverbookingGroupManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 房态
 * @author carr
 *
 */
public class RoomStatusAction extends BaseAction {

	private static final long serialVersionUID = -4649790434025211247L;
	
	@Autowired
	private RoomTypeManager roomTypeManager;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
    private RsvtypeManager rsvtypeManager;
	@Resource
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private OverbookingGroupManager overbookingGroupManager;
	@Resource
	private ChannelHotelManager channelHotelManager;
	@Resource
	private AsyncSendManager asyncSendManager;
	
	private String roomTypeCodes;//多个房型代码
	private Integer year;//年
	private Integer month;//月
	private Integer day;//日
	private String currentQueryDate;//当前查询的年月
	private String queryFlag;//query:查看;up:上月;current:当前月;down:下月;
	private List<RoomTypeVO> roomTypeList;//房型列表
	private RoomStatusVO roomStatusVO;//日历表VO
	private RoomStatusSetVO setvo;//销售数据
	private List<List<RoomStatusJsonVO>> roomStatusCalendars;//房态日历
	private Rsvtype rsvtype;//房态日历
	private RsvtypeChannel rsvtypeChannel;//房态日历渠道销售数据
	
	private List<ChannelGoodsVO> cgList;
	private List<ChannelGoodsVO> cgAllList;
	private String channelId;
	private String channelGroupStr;
	private List<OverbookingGroup> obGroupList;
	private boolean isPush;

    private String channelIds;

	private String hotelCode;
    private String startDate;
    private String endDate;
    private String sellWeeks;
    private String chosenRoomTypeCodeStr;
    
    public String getChosenRoomTypeCodeStr() {
		return chosenRoomTypeCodeStr;
	}

	public void setChosenRoomTypeCodeStr(String chosenRoomTypeCodeStr) {
		this.chosenRoomTypeCodeStr = chosenRoomTypeCodeStr;
	}
	public String getSellWeeks() {
		return sellWeeks;
	}

	public void setSellWeeks(String sellWeeks) {
		this.sellWeeks = sellWeeks;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 房型列表显示
	 */
	public String list(){
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//获取房型列表
		roomTypeList = roomTypeManager.getAllRoomTypeByHotelId(hotelvo.getHotelId(),language);
		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		cgvo.setHotelId(user.getHotelvo().getHotelId());
		cgList = channelgoodsManager.getEnabledChannelGoods(cgvo);
		
		HashMap<String, String> channelMap = new HashMap<String, String>();
		cgAllList = new ArrayList<ChannelGoodsVO>();
		obGroupList = overbookingGroupManager.getObGroupByHotelId(hotelvo.getHotelId());
		
		if(CommonUtil.isNotEmpty(cgList)){
			for (Iterator<ChannelGoodsVO> iterator = cgList.iterator(); iterator.hasNext();) {
				ChannelGoodsVO cg = iterator.next();
				String channelId = cg.getChannelId();
				boolean isFind=true;
				if(user.getCompanyId().equals(CompanyType.CHANNEL)){
					isFind=false;
					for(Channel channel:user.getChannels()){
						if(channelId.equals(channel.getChannelId())){
							isFind=true;
							break;
						}
					}
				}
				if(isFind==false||channelMap.containsKey(channelId)){
				    iterator.remove();//去除重复
				    continue;
				}
				for (OverbookingGroup obGroup : obGroupList) {
                    for (ChannelHotel ch : obGroup.getChannelHotelList()) {
                        if(channelId.equals(ch.getChannelId())){
                            iterator.remove();//去除已经分组的渠道
                        }
                    }
                }
				cgAllList.add(cg);
                channelMap.put(channelId, null);
			}
		}
		
//		for (OverbookingGroup obGroup : obGroupList) {
//            for (ChannelHotel ch : obGroup.getChannelHotelList()) {
//                if(!channelMap.containsKey(ch.getChannelId())){
//                    log.info("删除已关闭的渠道:"+ch.getChannelCode());
//                	channelHotelManager.delete(ch.getChannelHotelId());
//                	//推送该渠道房量
//                }
//            }
//        }
		
		if(CommonUtil.isEmpty(cgList)){
		    cgList = new ArrayList<ChannelGoodsVO>();
		}
		//渠道排个序
		if(cgAllList.size()>0){
			Collections.sort(cgAllList, new Comparator<ChannelGoodsVO>() {
				@Override
				public int compare(ChannelGoodsVO o1, ChannelGoodsVO o2) {
					if(StringUtils.isBlank(o1.getChannelCode())
							||StringUtils.isBlank(o2.getChannelCode())){
						return 0;
					}
					return o1.getChannelCode().compareToIgnoreCase(o2.getChannelCode());
				}
			});
		}
		return "list";
	}

	public void ajaxGetRateCodes()throws Exception{
		 String[] weeks = sellWeeks.split(",");
	      String []chosenWeek=new String[]{"0","0","0","0","0","0","0"};
	      for(String day:weeks){
	    		  int indx=Integer.valueOf(day);
	    		  chosenWeek[indx]="1";
	      }
	      String weekRange="";
	      for(int i=0;i<chosenWeek.length;i++){
	    	  weekRange+=chosenWeek[i];
	      }
	  	//json={"roomTypeCode1":["rateCode1","rateCode2"]};
	      B2BUser user = getCurLoginUser();
	      HotelVO hotelvo = user.getHotelvo();
		
	      Map<String,List<String>> resultMap=new HashMap<String,List<String>>();
	     List<RateCodeWithRoomVO> rateCodeList=rsvtypeManager.getRateCodeFromRoomType(hotelvo.getHotelCode(), startDate, endDate, JSONArray.parseArray(chosenRoomTypeCodeStr,String.class), weekRange);
	     for(RateCodeWithRoomVO  rateCodeWithRoomVO:rateCodeList){
	    	 
	    	 
		    		 if(resultMap.get(rateCodeWithRoomVO.getRoomTypeCode())==null){
		    			 List<String>rateCodes=new ArrayList<String>();
		    			 rateCodes.add(rateCodeWithRoomVO.getRatePlanCode());
		    			 resultMap.put(rateCodeWithRoomVO.getRoomTypeCode(),rateCodes);
		    		 }else{
		    			 resultMap.get(rateCodeWithRoomVO.getRoomTypeCode()).add(rateCodeWithRoomVO.getRatePlanCode());
		    		 }
	     }
	    
		this.ajaxResponse(JSONObject.toJSONString(resultMap));
	}
	/**
	 * AJAX获取房态日历
	 */
	public void ajaxGetCalendars() throws Exception {
		log.info("方法调用开始");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String[] roomTypeCodeArray = {};
		Date startDate = null;
		Date endDate = null;
		
		if(null==currentQueryDate || "".equals(currentQueryDate)){
			currentQueryDate = sdf.format(new Date());
		}
		int y = Integer.parseInt(currentQueryDate.substring(0, 4));//当前查询年
		int m = Integer.parseInt(currentQueryDate.substring(5, 7));//当前查询月
		
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
//		log.info(hotelvo);
		if(null==roomTypeCodes || "".equals(roomTypeCodes)){
			List<RoomType> roomTypeList = roomTypeManager.getHotelRoomTypesByHotelId(hotelvo.getHotelId());
			List<String> roomTypeCodeList = new ArrayList<String>();
			if(!roomTypeList.isEmpty()){
				for(RoomType rt:roomTypeList){
					roomTypeCodeList.add(rt.getRoomTypeCode());
				}
			}
			roomTypeCodeArray = roomTypeCodeList.toArray(roomTypeCodeArray);
		}else{
			roomTypeCodeArray = roomTypeCodes.split(",");
		}
		
		if(null==queryFlag || "".equals(queryFlag)){
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		if("query".equals(queryFlag)){
			if(null==year || null==month){
				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;
			}
		}
		if("current".equals(queryFlag)){
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		if("up".equals(queryFlag)){
			cal.set(y, m-1, 1);
			cal.add(Calendar.MONTH, -1);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		if("down".equals(queryFlag)){
			cal.set(y, m-1, 1);
			cal.add(Calendar.MONTH, +1);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
		startDate = sdf.parse(year+"-"+month+"-01");
		endDate = sdf.parse(DateUtil.getMonthLastDay(year,month));
		
		RoomStatusVO vo = new RoomStatusVO();
		vo.setHotelCode(hotelvo.getHotelCode());
		vo.setRoomTypeCodes(roomTypeCodeArray);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		vo.setChannelIds(channelIds.split(","));
//		log.info(vo);
		Map<String,List<Rsvtype>> rsvMap = rsvtypeChannelManager.getRsvtypeByChannelIdsRoomTypeCodes(vo);
		String json = JSONArray.toJSONStringWithDateFormat(rsvMap,DateUtils.YYYYMMDD);
//		log.debug(json);
		log.info("方法调用结束");
		this.ajaxResponse(json);
	}
	
	
	public void ajaxRemoveSell()throws IOException{
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		setvo.setHotelCode(hotelvo.getHotelCode());
		List<String> daysList = null;
        Date fromDate = setvo.getFromDate();
        Date toDate = setvo.getToDate();
        String[] weeks = setvo.getWeeks().split(",");
        int[] ret = new int[weeks.length];
        for (int j = 0; j < weeks.length; j++) {
        	ret[j] = Integer.parseInt(weeks[j]);
		}
        try {
			daysList = DateUtil.getWeekDay(fromDate, toDate, ret);
			if(CommonUtil.isNotEmpty(daysList)){
				if(daysList.size()<=90){
					rsvtypeManager.removeBatchSell(daysList.subList(0, daysList.size()),setvo,isPush);
				}else{
					rsvtypeManager.removeBatchSell(daysList.subList(0, 90),setvo,isPush);
					
					final List<String> spList = daysList.subList(90, daysList.size());
					//异步处理
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								rsvtypeManager.removeBatchSell(spList,setvo,isPush);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					t.start();
				}
			}
		} catch (Exception e) {
			ajaxResponse("系统异常，请稍后再试！");
			e.printStackTrace();
		}
	}
	/**
	 * 保存房态设置数据
	 */
	public void ajaxSaveSell() throws IOException{
		Map <String,String>rateCodesMap=new HashMap<String,String>();
		if(setvo.getJsonRateCodesWithBlock()!=null&&setvo.getJsonRateCodesWithBlock().equalsIgnoreCase("")==false){
			JSONObject o=JSONObject.parseObject(setvo.getJsonRateCodesWithBlock());
			for(Entry<String, Object> set:o.entrySet()){
				List<String> rateCodeList=JSONArray.parseArray(set.getValue().toString(),String.class);
				String rateCodes="";
				for(String rateCode:rateCodeList){
					rateCodes+=rateCode+",";
				}
				rateCodesMap.put(set.getKey(),rateCodes.substring(0,rateCodes.length()-1));
			}
		}
		setvo.setRateCodesMap(rateCodesMap);
		
		
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		setvo.setHotelCode(hotelvo.getHotelCode());
		List<String> daysList = null;
        Date fromDate = setvo.getFromDate();
        Date toDate = setvo.getToDate();
        String[] weeks = setvo.getWeeks().split(",");
        int[] ret = new int[weeks.length];
        for (int j = 0; j < weeks.length; j++) {
        	ret[j] = Integer.parseInt(weeks[j]);
		}
        try {
			daysList = DateUtil.getWeekDay(fromDate, toDate, ret);
			if(CommonUtil.isNotEmpty(daysList)){
				if(daysList.size()<=90){
					rsvtypeManager.setBatchSaveSell(daysList.subList(0, daysList.size()),setvo,isPush);
				}else{
					rsvtypeManager.setBatchSaveSell(daysList.subList(0, 90),setvo,isPush);
					
					final List<String> spList = daysList.subList(90, daysList.size());
					//异步处理
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								rsvtypeManager.setBatchSaveSell(spList,setvo,isPush);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					t.start();
				}
			}
		} catch (Exception e) {
			ajaxResponse("系统异常，请稍后再试！");
			e.printStackTrace();
		}
	}
	
	/**
	 * AJAX设置FreeSell
	 */
	public void ajaxFreeSellSet() throws Exception {
	    Date date = getDate();
        if(date==null){
            return;
        }
		//设置提交数据
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		roomStatusVO.setHotelCode(hotelvo.getHotelCode());
		roomStatusVO.setDate(date);
		try {
		    rsvtypeChannel = rsvtypeChannelManager.setAllotmentAndFreeSell(roomStatusVO,Boolean.TRUE);
			this.ajaxResponse(JSONArray.toJSONString(rsvtypeChannel));
		} catch (Exception e) {
			this.ajaxResponse("fail");
			e.printStackTrace();
		}
	}
	public String testAvai() throws Exception{
//		String hcode = "AHHAN";
//		String rcdoe = "1KBDS";
//		String ccode = "ELONG";
//		String rcode = "WHL01";
//		Date ciDate = DateUtil.convertStringToDate("2014-10-30");
//		Date coDate = DateUtil.convertStringToDate("2014-10-31");
//		
//	    boolean isAvai = rsvtypeChannelManager.validataRsvtypeChannelAilable(rcdoe, hcode, ciDate, coDate, ccode,1,null);
//	    ajaxResponse(isAvai+"");
//	    if(isAvai){
//	        boolean res = rsvtypeChannelManager.confirmRsvtypeChannelAilable(rcdoe, hcode,rcode, ciDate, coDate, ccode,1);
//	        ajaxResponse(res+" 扣减");
//	    }else{
//	        log.info("没有房");
//	    }
	    
//	    rsvtypeChannelManager.cancelRsvtypeChannelAilable("1KDSS", "TEAZJ", DateUtil.convertStringToDate("2014-05-29"), DateUtil.convertStringToDate("2014-05-30"), "MANGOCITY",1);
	    
//	    boolean res = rsvtypeChannelManager.confirmSoldableCondition("AHHAN", "Test", DateUtil.convertStringToDate("2014-08-13"), DateUtil.convertStringToDate("2014-08-14"), 1);
//	    ajaxResponse(res+"");
        return null;
	}
	
	/**
	 * AJAX设置Allotment
	 */
	public void ajaxRateCodeSet() throws Exception {
	    Date date = getDate();
        if(date==null){
            return;
        }
        //设置提交数据
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        roomStatusVO.setHotelCode(hotelvo.getHotelCode());
        roomStatusVO.setDate(date);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String allotmentDate=df.format(date);
        try {
        	List<String> roomList=new ArrayList<String>();
        	roomList.add(roomStatusVO.getType());
        	  Map<String,List<String>> resultMap=new HashMap<String,List<String>>();
        	  List<RateCodeWithRoomVO> rateCodeList= rsvtypeManager.getRateCodeFromRoomType(hotelvo.getHotelCode(),allotmentDate,allotmentDate,roomList,null);
     	     for(RateCodeWithRoomVO  rateCodeWithRoomVO:rateCodeList){
     		    		 if(resultMap.get(rateCodeWithRoomVO.getRoomTypeCode())==null){
     		    			 List<String>rateCodes=new ArrayList<String>();
     		    			 rateCodes.add(rateCodeWithRoomVO.getRatePlanCode());
     		    			 resultMap.put(rateCodeWithRoomVO.getRoomTypeCode(),rateCodes);
     		    		 }else{
     		    			 resultMap.get(rateCodeWithRoomVO.getRoomTypeCode()).add(rateCodeWithRoomVO.getRatePlanCode());
     		    		 }
     	     }
     	     
     	    this.ajaxResponse(JSONObject.toJSONString(resultMap));
        } catch (Exception e) {
            ajaxResponse("fail");
            e.printStackTrace();
        }
	}
	
	public void ajaxCutOffDaysSet()throws Exception{
		
	}
	
	public void ajaxRateCodesSet()throws Exception{
	    Date date = getDate();
        if(date==null){
            return;
        }
        //设置提交数据
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        roomStatusVO.setHotelCode(hotelvo.getHotelCode());
        roomStatusVO.setDate(date);
        try {
            rsvtypeChannel = rsvtypeChannelManager.setAllotmentAndFreeSell(roomStatusVO,Boolean.TRUE);
            ajaxResponse(JSONArray.toJSONString(rsvtypeChannel));
        } catch (Exception e) {
            ajaxResponse("fail");
            e.printStackTrace();
        }
	
	}
	
	/**
	 * AJAX设置Allotment
	 */
	public void ajaxAllotmentSet() throws Exception {
	    Date date = getDate();
        if(date==null){
            return;
        }
        //设置提交数据
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        roomStatusVO.setHotelCode(hotelvo.getHotelCode());
        roomStatusVO.setDate(date);
        try {
            rsvtypeChannel = rsvtypeChannelManager.setAllotmentAndFreeSell(roomStatusVO,Boolean.TRUE);
            ajaxResponse(JSONArray.toJSONString(rsvtypeChannel));
        } catch (Exception e) {
            ajaxResponse("fail");
            e.printStackTrace();
        }
	}
	
	public void ajaxSetOverBooking()throws Exception{
	    Date date = getDate();
	    if(date==null){
            return;
        }
        
        //设置提交数据
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        roomStatusVO.setHotelCode(hotelvo.getHotelCode());
        roomStatusVO.setDate(date);
        try {
            rsvtypeManager.setOverBooking(roomStatusVO,Boolean.TRUE);
            //查询当前数据
            rsvtype = rsvtypeManager.getRsvtype(roomStatusVO);
            ajaxResponse(JSONArray.toJSONString(rsvtype));
        } catch (Exception e) {
            ajaxResponse("fail");
            e.printStackTrace();
        }
	}
	/**
     * 批量设置ob
     */
    public void ajaxBatchSetOverBooking(){
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        setvo.setHotelCode(hotelvo.getHotelCode());
        
        List<String> daysList = null;
        Date fromDate = setvo.getFromDate();
        Date toDate = setvo.getToDate();
        String[] weeks = setvo.getWeeks().split(",");
        int[] ret = new int[weeks.length];
        for (int j = 0; j < weeks.length; j++) {
        	ret[j] = Integer.parseInt(weeks[j]);
		}
        try {
			daysList = DateUtil.getWeekDay(fromDate, toDate, ret);
			if(CommonUtil.isNotEmpty(daysList)){
				if(daysList.size()<=90){
					rsvtypeManager.setBatchOverBooking(daysList.subList(0, daysList.size()),setvo,isPush);
				}else{
					rsvtypeManager.setBatchOverBooking(daysList.subList(0, 90),setvo,isPush);
					
					final List<String> spList = daysList.subList(90, daysList.size());
					//异步处理
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								rsvtypeManager.setBatchOverBooking(spList,setvo,isPush);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					t.start();
				}
			}
		} catch (Exception e) {
			ajaxResponse("系统异常，请稍后再试！");
			e.printStackTrace();
		}
    }
	public void ajaxSaveChannelGroup(){
		if(CommonUtil.isNotEmpty(channelGroupStr)){
			boolean isSuccess = overbookingGroupManager.saveChannelGroup(channelGroupStr,isPush);
		}
	}
	
	public Date getDate(){
	    Calendar cal = new GregorianCalendar();
        //没有提交数据
        if(null==roomStatusVO){
            return null;
        }
        //判断日期是否正常如：‘2014-02-31’
        cal.set(year, month-1, day,0,0,0);
        int m = cal.get(Calendar.MONTH)+1;
        if(m!=month){
            return null;
        }
        //判断是否今天之前
        Date currDate = DateUtil.getCleanDate(new Date());
        if(!cal.getTime().after(currDate)){
            return null;
        }
        return cal.getTime();
	}
	
	public List<RoomTypeVO> getRoomTypeList() {
		return roomTypeList;
	}
	public void setRoomTypeList(List<RoomTypeVO> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}
	public RoomStatusVO getRoomStatusVO() {
		return roomStatusVO;
	}
	public void setRoomStatusVO(RoomStatusVO roomStatusVO) {
		this.roomStatusVO = roomStatusVO;
	}
	public List<List<RoomStatusJsonVO>> getRoomStatusCalendars() {
		return roomStatusCalendars;
	}
	public void setRoomStatusCalendars(List<List<RoomStatusJsonVO>> roomStatusCalendars) {
		this.roomStatusCalendars = roomStatusCalendars;
	}
	public String getRoomTypeCodes() {
		return roomTypeCodes;
	}
	public void setRoomTypeCodes(String roomTypeCodes) {
		this.roomTypeCodes = roomTypeCodes;
	}
	public String getQueryFlag() {
		return queryFlag;
	}
	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getCurrentQueryDate() {
		return currentQueryDate;
	}
	public void setCurrentQueryDate(String currentQueryDate) {
		this.currentQueryDate = currentQueryDate;
	}
	public RoomStatusSetVO getSetvo() {
		return setvo;
	}
	public void setSetvo(RoomStatusSetVO setvo) {
		this.setvo = setvo;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Rsvtype getRsvtype() {
		return rsvtype;
	}
	public void setRsvtype(Rsvtype rsvtype) {
		this.rsvtype = rsvtype;
	}

	public List<ChannelGoodsVO> getCgList() {
		return cgList;
	}

	public void setCgList(List<ChannelGoodsVO> cgList) {
		this.cgList = cgList;
	}

	public String getChannelGroupStr() {
		return channelGroupStr;
	}

	public void setChannelGroupStr(String channelGroupStr) {
		this.channelGroupStr = channelGroupStr;
	}

	public List<OverbookingGroup> getObGroupList() {
		return obGroupList;
	}

	public void setObGroupList(List<OverbookingGroup> obGroupList) {
		this.obGroupList = obGroupList;
	}

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public RsvtypeChannel getRsvtypeChannel() {
        return rsvtypeChannel;
    }

    public void setRsvtypeChannel(RsvtypeChannel rsvtypeChannel) {
        this.rsvtypeChannel = rsvtypeChannel;
    }

    public List<ChannelGoodsVO> getCgAllList() {
        return cgAllList;
    }

    public void setCgAllList(List<ChannelGoodsVO> cgAllList) {
        this.cgAllList = cgAllList;
    }

    public boolean isPush() {
        return isPush;
    }

    public void setPush(boolean isPush) {
        this.isPush = isPush;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }
	
}
