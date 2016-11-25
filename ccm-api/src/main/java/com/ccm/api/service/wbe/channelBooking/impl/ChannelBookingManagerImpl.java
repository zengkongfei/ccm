package com.ccm.api.service.wbe.channelBooking.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.ccm.api.dao.hotel.CustomDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.ows.vo.OrderDailyRateVO;
import com.ccm.api.model.wbe.WbeCalendarRow;
import com.ccm.api.model.wbe.WbeGuestVO;
import com.ccm.api.model.wbe.WbeOrderVO;
import com.ccm.api.model.wbe.WbeSearchCreteria;
import com.ccm.api.service.wbe.channelBooking.ChannelBookingManager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.annotation.Resource;

import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.dao.ratePlan.RateAmountDao;
import com.ccm.api.dao.ratePlan.RateDetailDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.model.wbe.EffectiveGuarrntee;
import com.ccm.api.model.wbe.WbeCalendarCell;
import com.ccm.api.model.wbe.WbeEffectiveData;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.service.ratePlan.RateGuaranteeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
@Service("channelBookingManager")
public class ChannelBookingManagerImpl implements ChannelBookingManager{
	@Autowired
	private MasterDao masterDao;
	@Resource
	private RateDetailDao rateDetailDao;
	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private RateAmountDao rateAmountDao;
	@Resource
	private RoomTypeDao roomTypeDao;
	@Resource
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
	private RateGuaranteeManager rateGuaranteeManager;
	@Resource
	private CancelPolicyDao cancelPolicyDao;
	@Resource
	private GuaranteePolicyDao guaranteePolicyDao;
	@Resource
	private CustomDao customDao;
	@Resource
	private RateAmountManager rateAmountManager;
	@Resource
	private ChannelDao channelDao;
	private Log log = LogFactory.getLog(ChannelBookingManagerImpl.class);
	@Override
	public Map<String, List<WbeCalendarRow>> getBookingCanlendar(
			WbeSearchCreteria creteria)  throws ParseException{
		// TODO Auto-generated method stub
		//resultMap
		Map<String, List<WbeCalendarRow>>  resultMap= new HashMap<String, List<WbeCalendarRow>>();
		try{
		GuaranteePolicyVO defaultGuaranteePolicyVO=null;
		CancelPolicyVO  defaultCancelPolicyVO=null;
		Channel channel=channelDao.getChannelByChannelCode(creteria.getChannelCode());
		Custom custom=customDao.searchCustomByHotelIdAndAccessCode(creteria.getHotelId(),creteria.getAccessCode());
		if(custom!=null){
			if(custom.getDefGuaranteeId()!=null)
				defaultGuaranteePolicyVO=guaranteePolicyDao.getGuaranteePolicyById(custom.getDefGuaranteeId(),creteria.getLanguage()!=null?creteria.getLanguage():LanguageCode.ZH_CN);
			if(custom.getDefCancelId()!=null)
				defaultCancelPolicyVO=cancelPolicyDao.getCancelPolicyById(custom.getDefCancelId(),creteria.getLanguage()!=null?creteria.getLanguage():LanguageCode.ZH_CN);
		}
		List<RoomTypeVO> roomTypeVOList =roomTypeDao.getRoomTypeInfosByIds(creteria.getRoomTypeIds(),creteria.getLanguage()!=null?creteria.getLanguage():LanguageCode.ZH_CN);
		//查询条件：roomTypeCode,roomTypeName
		Map<String,String> roomTypeMap=new HashMap<String,String>();
		for(RoomTypeVO roomTypeVO:roomTypeVOList){
			roomTypeMap.put(roomTypeVO.getRoomTypeCode(),roomTypeVO.getRoomTypeName());
		}
		roomTypeVOList=null;
		
		// part1.获取有效的房价
		//key:roomType:value:rateCodeList
		Map<String,Set<String>> roomTypeWithRatecodeMap=new HashMap<String,Set<String>>();
		
		//key:rateCode;value:price
		Map<String,Map<Date,BigDecimal>>effiectivePriceWithRateCodeMap=new HashMap<String,Map<Date,BigDecimal>>();
		//key:rateCode
		Map<String, Map<Date, List<RateAmount>>> rateAmountWithRatecodeMap = new HashMap<String, Map<Date, List<RateAmount>>>();
			String rateStartDateStr = creteria.getArrDate();
			Date rateEndDate=DateUtil.addDays(
					DateUtil.convertStringToDate(creteria.getDepDate()), -1);
			String rateEndDateStr = DateUtil.getDate(rateEndDate);

			Date checkInDate = DateUtil.convertStringToDate(creteria
					.getArrDate());
			Date checkOutDate = DateUtil.convertStringToDate(creteria
					.getDepDate());
			
			//recycle roomTypeList;获取所有符合要求的rateplan
			//key:roomTypeId;Value:ratePlanCode
			Set<String>roomTypeCodeSet=roomTypeMap.keySet();
			String[] roomTypeCodeArray=new String[roomTypeCodeSet.size()];
			int count=0;
			for(String roomTypeCode:roomTypeCodeSet){
				roomTypeCodeArray[count]=roomTypeCode;
				count++;
			}
			Map<String,Set<String>> channelBindRoomRateCodeMap=getchannelBindRateCode(creteria.getHotelId(),creteria.getChannelCode(),roomTypeCodeArray,rateEndDate);
			for (String roomTypeId : creteria.getRoomTypeIds()) {
				Set<String>ratePlanCodeSet=channelBindRoomRateCodeMap.get(roomTypeId);
				if(ratePlanCodeSet==null||ratePlanCodeSet.isEmpty()){
					continue;
				}
				List<RateCodeWithRoomVO> rateCodeWithRoomList = rateDetailDao
						.getRateCodeFromRoomTypeId(creteria.getHotelId(),
								rateStartDateStr, rateEndDateStr, roomTypeId,new Vector<String>(ratePlanCodeSet),creteria.getAccessCode(),creteria.getChannelId());
				// filter week
				int dateDiff = DateUtil.dateDiff(checkInDate, checkOutDate);
				for (int i = 0; i < dateDiff; i++) {
					Date currentDate = DateUtil.addDays(checkInDate, i);
					String currentDateStr=DateUtil.getDate(currentDate);
					inner:for (RateCodeWithRoomVO rateCodeWithRoomVO : rateCodeWithRoomList) {
						//judge whether it is the sub rate plan
						if (CommonUtil.isNotEmpty(rateCodeWithRoomVO.getInheritRatePlanId())) {
							List<RateDetailVO> rdVOList= rateDetailDao.getRateDetailVOByRatePlanId(rateCodeWithRoomVO.getRatePlanId(), rateCodeWithRoomVO.getRateDetailId());
							if(rdVOList.size()>0)
								continue;
						}
						
						RateDetail rd = rateDetailDao.get(rateCodeWithRoomVO
								.getRateDetailId());
						// valid date
						if (	currentDate.after(rateCodeWithRoomVO.getExpireDate()) == false
								&& currentDate.before(rateCodeWithRoomVO.getEffectiveDate()) == false
								&&currentDate.after(rd.getExpireDate()) == false
								&& currentDate.before(rd.getEffectiveDate()) == false) {
							boolean findFitWeekDay=false;
							Integer dayNum = DateUtil.getWeekday(currentDate) - 1;
							if (dayNum == 0) {
								boolean isSun = rd.getIsApplyToSunday() == null ? false
										: rd.getIsApplyToSunday();
								if (isSun == true) {
									findFitWeekDay=true;
								}
							} else if (dayNum == 1) {
								boolean isMon = rd.getIsApplyToMonday() == null ? false
										: rd.getIsApplyToMonday();
								if (isMon == true) {
									findFitWeekDay=true;
								}
							} else if (dayNum == 2) {
								boolean isTue = rd.getIsApplyToTuesday() == null ? false
										: rd.getIsApplyToTuesday();
								if (isTue == true) {
									findFitWeekDay=true;
								}
							} else if (dayNum == 3) {
								boolean isWes = rd.getIsApplyToWednesday() == null ? false
										: rd.getIsApplyToWednesday();
								if (isWes == true) {
									findFitWeekDay=true;
								}
							} else if (dayNum == 4) {
								boolean isThu = rd.getIsApplyToThursday() == null ? false
										: rd.getIsApplyToThursday();
								if (isThu == true) {
									findFitWeekDay=true;
								}
							} else if (dayNum == 5) {
								boolean isFri = rd.getIsApplyToFriday() == null ? false
										: rd.getIsApplyToFriday();
								if (isFri == true) {
									findFitWeekDay=true;
								}
							} else if (dayNum == 6) {
								boolean isSat = rd.getIsApplyToSaturday() == null ? false
										: rd.getIsApplyToSaturday();
								if (isSat == true) {
									findFitWeekDay=true;
								}
							}
							if(findFitWeekDay==false)
									continue inner;
							//搜集房型的rate code
							if(roomTypeWithRatecodeMap.get(rateCodeWithRoomVO.getRoomTypeCode())==null){
								Set<String> rateCodeSet=new HashSet<String>();
								roomTypeWithRatecodeMap.put(rateCodeWithRoomVO.getRoomTypeCode(),rateCodeSet);
							}
								Set<String> rateCodeSet=roomTypeWithRatecodeMap.get(rateCodeWithRoomVO.getRoomTypeCode());
								rateCodeSet.add(rateCodeWithRoomVO.getRatePlanCode());
							
							
							// 收集日期的rate amount
							if (rateAmountWithRatecodeMap
									.get(rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr) == null) {
								// put amount
								Map<Date, List<RateAmount>> effectiveRateAmountMap = new HashMap<Date, List<RateAmount>>();
								rateAmountWithRatecodeMap.put(rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr,effectiveRateAmountMap);
							} 
								//put amount
								Map<Date,List<RateAmount>> effectiveRateAmountMap = rateAmountWithRatecodeMap.get(rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr);
								List<RateAmount> effectiveRateAmountList=null;
								if (CommonUtil.isNotEmpty(rateCodeWithRoomVO.getInheritRatePlanId())) {
									Rateplan rp=rateplanDao.getRatePlanById(rateCodeWithRoomVO.getRatePlanId());
									effectiveRateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateCodeWithRoomVO.getRateDetailId(), rp);
								}else{
									effectiveRateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateCodeWithRoomVO.getRateDetailId());
								}
								effectiveRateAmountMap.put(currentDate,effectiveRateAmountList);
							
							//put price
							if(effiectivePriceWithRateCodeMap.get(rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr)==null){
								Map<Date,BigDecimal>effectivePriceWithDateMap= new HashMap<Date,BigDecimal>();
								effiectivePriceWithRateCodeMap.put(rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr, effectivePriceWithDateMap);
							}
							Map<Date,BigDecimal>effectivePriceWithDateMap=effiectivePriceWithRateCodeMap.get(rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr);
							for(RateAmount rateAmount:effectiveRateAmountMap.get(currentDate)){
								if(creteria.getNumberOfUnits().equals(rateAmount.getNumberOfUnits())){
									effectivePriceWithDateMap.put(currentDate,rateAmount.getBaseAmount());
									log.info("get price=>"+rateCodeWithRoomVO.getRatePlanCode()+"||"+rateCodeWithRoomVO.getRoomTypeCode()+"||"+currentDateStr+"=>rateAmount:"+rateAmount.getBaseAmount()+",rateDetailId:"+rateAmount.getRateDetailId());
								}
							}
						}
					}
				}
				rateCodeWithRoomList=null;
			}
			channelBindRoomRateCodeMap=null;
			//一定有数据的
			Set<String>effectiveRoomTypeSet =roomTypeWithRatecodeMap.keySet();
			
			
			// part2.获取最小房价
			
			String[] inventTypeArray=new String[]{WbeCalendarRow.INVENT_ALLOT,WbeCalendarRow.INVENT_FREESELL};
			for(String roomTypeCode:effectiveRoomTypeSet){
				
				//key:ivnentType
				List<WbeCalendarRow>wbeCalendarRowList=new Vector<WbeCalendarRow>();
				for(String inventType:inventTypeArray){
					//ratecode,inventAvaiPerDate
					Map<String,Map<Date,Integer>> inventAvaiWithRateCodeMap=rsvtypeChannelManager.getRoomTypeAllInventForWBE(inventType,creteria.getHotelCode(),creteria.getChannelCode(),roomTypeCode,checkInDate,checkOutDate,new Vector<String>(roomTypeWithRatecodeMap.get(roomTypeCode)));
					//key:rateCode value:Date,Avail,Price
					Map<String,List<WbeEffectiveData>> effectiveDataMap=new HashMap<String,List<WbeEffectiveData>>();
					//days,rateCodeList
					Map<Integer,Set<String>> comparedAvailWithRateCodeMap=new HashMap<Integer,Set<String>>();
					//一个房型的invent价格集合
					for(String rateCode:inventAvaiWithRateCodeMap.keySet()){
						//有效房量天数
						Map<Date,Integer>effectiveAvaiWithDateMap =inventAvaiWithRateCodeMap.get(rateCode);
						
						
						int dateDiff = DateUtil.dateDiff(checkInDate, checkOutDate);
				 		for (int i = 0; i < dateDiff; i++) {
				 			Date curDate = DateUtil.addDays(checkInDate, i);
				 			String curDateStr=DateUtil.getDate(curDate);
				 			//有效房价天数
							Map<Date,BigDecimal>effectivePriceWithDateMap=effiectivePriceWithRateCodeMap.get(rateCode+"||"+roomTypeCode+"||"+curDateStr);
				 			if(effectivePriceWithDateMap==null)
				 				continue;
							if(effectivePriceWithDateMap.get(curDate)!=null&&effectiveAvaiWithDateMap.get(curDate)!=null){
				 				List<WbeEffectiveData> effectiveDataList=effectiveDataMap.get(rateCode);
				 				//整理有效数据
				 				if(effectiveDataList==null){
				 					effectiveDataList=new Vector<WbeEffectiveData>();
				 					effectiveDataMap.put(rateCode, effectiveDataList);
				 				}
				 					WbeEffectiveData  effectiveData=new WbeEffectiveData();
				 					effectiveData.setCurDate(curDate);
				 					effectiveData.setAvai(effectiveAvaiWithDateMap.get(curDate));
				 					effectiveData.setBaseAmount(effectivePriceWithDateMap.get(curDate));
				 					effectiveDataList.add(effectiveData);
				 			}
				 		}
				 		if(effectiveDataMap.get(rateCode)==null)
				 			continue;
						
						if(comparedAvailWithRateCodeMap.get(effectiveDataMap.get(rateCode).size())==null){
							Set<String> rateCodeSet=new HashSet<String>();
							comparedAvailWithRateCodeMap.put(effectiveDataMap.get(rateCode).size(),rateCodeSet);
							}
							Set<String> rateCodeSet=comparedAvailWithRateCodeMap.get(effectiveDataMap.get(rateCode).size());
							rateCodeSet.add(rateCode);
					}
					if(comparedAvailWithRateCodeMap.size()==0) continue;
					 //a.获取房量最大天数
				    List<Integer>maximumDaysList = new ArrayList<Integer>(comparedAvailWithRateCodeMap.keySet());
				    Collections.sort(maximumDaysList);
				    Integer maximumDays= maximumDaysList.get(maximumDaysList.size()-1);
				    Set<String> maximumAvailWithRateCodeSet=comparedAvailWithRateCodeMap.get(maximumDays);
				    
				    
				    
				    
				    //b.获取最大天数的最小房价
				    Map <BigDecimal,String>comparedAmountWithRateCodeMap=new HashMap<BigDecimal,String>();
			         for(String rateCodeWithMaximumAvail:maximumAvailWithRateCodeSet){
			        	 List<WbeEffectiveData> wbeEffectiveDataList=effectiveDataMap.get(rateCodeWithMaximumAvail);
			        	 BigDecimal rateAmountWithDays=new BigDecimal(0);
			        	 for(WbeEffectiveData effectiveData:wbeEffectiveDataList){
			        		 rateAmountWithDays=rateAmountWithDays.add(effectiveData.getBaseAmount());
			        	 }
			        	 comparedAmountWithRateCodeMap.put(rateAmountWithDays,rateCodeWithMaximumAvail);
			         }
					 //比较房价的大小
			         if(comparedAmountWithRateCodeMap.size()==0) continue;
			         List<BigDecimal>minimumAmountDaysList = new ArrayList<BigDecimal>(comparedAmountWithRateCodeMap.keySet());
			         Collections.sort(minimumAmountDaysList);
			         String finalRateCode=comparedAmountWithRateCodeMap.get(minimumAmountDaysList.get(0));
			         log.info("finalRateCode=>roomTypeCode:"+roomTypeCode+",invnentoryType:"+inventType+",ratecode:"+finalRateCode);
			         //创建row数据
			         WbeCalendarRow wbeCalendarRow=new WbeCalendarRow();
			         wbeCalendarRowList.add(wbeCalendarRow);
			         wbeCalendarRow.setInventType(inventType);
			         wbeCalendarRow.setRowNumberOfUnits(creteria.getNumberOfUnits());
			         wbeCalendarRow.setArrDate(checkInDate);
			         wbeCalendarRow.setDepDate(checkOutDate);
			         wbeCalendarRow.setRoomTypeCode(roomTypeCode);
			         Rateplan rp=rateplanDao.getRateplanByRateplanCode(finalRateCode, creteria.getHotelCode());
			         wbeCalendarRow.setRoomTypeName(roomTypeMap.get(roomTypeCode));
			         wbeCalendarRow.setRatePlanCode(finalRateCode);
			         wbeCalendarRow.setRatePlanId(rp.getRatePlanId());
			         wbeCalendarRow.setRateplan(rp);
			         
			         //添加子明细
			         Map<Date, WbeCalendarCell> webCalendarCellMap=wbeCalendarRow.getCellMap();
			         Map<Date,Integer> finalRoomTypeAvailMap=inventAvaiWithRateCodeMap.get(finalRateCode);
			        
			         //
			         int dateDiff = DateUtil.dateDiff(checkInDate, checkOutDate);
				 		for (int i = 0; i < dateDiff; i++) {
				 			Date curDate = DateUtil.addDays(checkInDate, i);
				 			String curDateStr=DateUtil.getDate(curDate);
				 			 Map<Date,List<RateAmount>> finalRoomRateAmountMap=rateAmountWithRatecodeMap.get(finalRateCode+"||"+roomTypeCode+"||"+curDateStr);
				 			if(finalRoomRateAmountMap==null)
				 				continue;
				 			 if(finalRoomTypeAvailMap.get(curDate)!=null){
				 				List<RateAmount> rateAmountList=finalRoomRateAmountMap.get(curDate);
				 				if(rateAmountList!=null){
					 				WbeCalendarCell wbeCalendarCell=new WbeCalendarCell();
					 				wbeCalendarCell.setCellDate(curDate);
					 				wbeCalendarCell.setCellInventAvai(finalRoomTypeAvailMap.get(curDate));
					 				wbeCalendarCell.setCellNumberOfUnits(creteria.getNumberOfUnits());
					 				Map<String,Object> params=new HashMap<String,Object>();
					 				params.put("ratePlanId", rp.getRatePlanId());
					 				params.put("language",creteria.getLanguage()!=null?creteria.getLanguage():LanguageCode.ZH_CN);
					 				params.put("curDate", curDate);
					 				if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToMonday",true);
					 				} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToTuesday",true);
					 				} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToWednesday",true);
					 				} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToThursday",true);
					 				} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToFriday",true);
					 				} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToSaturday",true);
					 				} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
					 					params.put("isApplyToSunday",true);
					 				}
					 				
					 				//担保规则
							         List<EffectiveGuarrntee> effectiveGuarrnteeList =new Vector<EffectiveGuarrntee>();
							         List<GuaranteePolicyVO>guaranteePolicyList=rateGuaranteeManager.getEnabledGuaranteePolicy(rp.getRatePlanId(),creteria.getLanguage()!=null?creteria.getLanguage():LanguageCode.ZH_CN, curDate);
							         if(guaranteePolicyList!=null){
								         for(GuaranteePolicyVO guaranteePolicyVO:guaranteePolicyList){
								        	 EffectiveGuarrntee effectiveGuarrntee= new EffectiveGuarrntee();
								        	 effectiveGuarrntee.setGuaranteeId(guaranteePolicyVO.getGuaranteeId());
								        	 effectiveGuarrntee.setRatePlanId(rp.getRatePlanId());
								        	 effectiveGuarrntee.setPolicyName(guaranteePolicyVO.getGuaranteeCode());
								        	 effectiveGuarrnteeList.add(effectiveGuarrntee);
								         }
							         }
							         if(effectiveGuarrnteeList.size()==0&&defaultGuaranteePolicyVO!=null){
							        	 EffectiveGuarrntee effectiveGuarrntee= new EffectiveGuarrntee();
							        	 effectiveGuarrntee.setGuaranteeId(defaultGuaranteePolicyVO.getGuaranteeId());
							        	 effectiveGuarrntee.setRatePlanId(rp.getRatePlanId());
							        	 effectiveGuarrntee.setPolicyName(defaultGuaranteePolicyVO.getGuaranteeCode());
							        	 effectiveGuarrnteeList.add(effectiveGuarrntee);
							        	 }
							         wbeCalendarCell.setGuarrnteeList(effectiveGuarrnteeList);
							        //取消规则 
					 				List<CancelPolicyVO> cancelPolicyVOList=cancelPolicyDao.getRateCancelI18nByCondition(params);
					 				if(cancelPolicyVOList!=null){
					 					for(CancelPolicyVO cancelPolicyVO:cancelPolicyVOList){
					 						CancelPolicyVO copyedCancelPolicyVO=new CancelPolicyVO();
					 						copyedCancelPolicyVO.setCancelPolicyCode(cancelPolicyVO.getCancelPolicyCode());
					 						copyedCancelPolicyVO.setPolicyName(cancelPolicyVO.getPolicyName());
					 						copyedCancelPolicyVO.setDescription(cancelPolicyVO.getDescription());
					 						wbeCalendarCell.setCancelPolicyVO(copyedCancelPolicyVO);
					 					}
					 				}
					 				if(wbeCalendarCell.getCancelPolicyVO()==null&&defaultCancelPolicyVO!=null){
					 					CancelPolicyVO copyedCancelPolicyVO=new CancelPolicyVO();
				 						copyedCancelPolicyVO.setCancelPolicyCode(defaultCancelPolicyVO.getCancelPolicyCode());
				 						copyedCancelPolicyVO.setPolicyName(defaultCancelPolicyVO.getPolicyName());
				 						copyedCancelPolicyVO.setDescription(defaultCancelPolicyVO.getDescription());
					 					wbeCalendarCell.setCancelPolicyVO(copyedCancelPolicyVO);
					 				}
					 				for(RateAmount rateAmount:rateAmountList){
					 					RateAmount copyedRateAmount=new RateAmount();
					 					copyedRateAmount.setNumberOfUnits(rateAmount.getNumberOfUnits());
					 					copyedRateAmount.setBaseAmount(rateAmount.getBaseAmount());
					 					wbeCalendarCell.getRateAmountMap().put(copyedRateAmount.getNumberOfUnits(),copyedRateAmount);
					 					if(creteria.getNumberOfUnits().equals(copyedRateAmount.getNumberOfUnits())){
					 						wbeCalendarCell.setCellPrice(copyedRateAmount.getBaseAmount());
					 						 log.info("celldata=>roomTypeCode:"+roomTypeCode+",invnentoryType:"+inventType+",ratecode:"+finalRateCode+",rateAmount:"+wbeCalendarCell.getCellPrice());
					 					}
					 				}
					 				webCalendarCellMap.put(curDate,wbeCalendarCell);
				 				}
				 			}
				 		}
				 		rateAmountManager.getAmountAfterTaxWithDetailPackForWEB(channel,finalRateCode,creteria.getHotelCode(),roomTypeCode,webCalendarCellMap,rp.getRatePlanId(),null);
				 	}
			 		resultMap.put(roomTypeCode, wbeCalendarRowList);
			}
		}catch(Exception ext){
			ext.printStackTrace();
		}
		System.out.println("UI DATA result=>"+JSONArray.toJSONString(resultMap));
		return resultMap;
	}
	//key:roomTypeId;Value:ratePlanCode
	private Map<String,Set<String>> getchannelBindRateCode(String hotelId, String channelCode,
			String[] roomTypeCodes, Date endDate) {
		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		cgvo.setChannelCodes(new String[]{channelCode});
		cgvo.setHotelId(hotelId);
		cgvo.setExpireDate(endDate);
		cgvo.setRoomTypeCodes(roomTypeCodes);
		cgvo.setStatus("2");
		Map<String,Set<String>> roomTypeWithRatecodesMap=new HashMap<String,Set<String>>();
		List<ChannelGoodsVO> cgvoList = channelgoodsManager
				.getEnabledChannelGoods2(cgvo);
		if (cgvoList != null && cgvoList.size() > 0) {
			for(ChannelGoodsVO channelGoodsVO:cgvoList){
				if(roomTypeWithRatecodesMap.get(channelGoodsVO.getRoomTypeId())==null){
					Set<String> rateCodeSet=new HashSet<String>();
					rateCodeSet.add(channelGoodsVO.getRatePlanCode());
					roomTypeWithRatecodesMap.put(channelGoodsVO.getRoomTypeId(),rateCodeSet);
				}else{
					Set<String> rateCodeSet=roomTypeWithRatecodesMap.get(channelGoodsVO.getRoomTypeId());
					rateCodeSet.add(channelGoodsVO.getRatePlanCode());
				}
			}
		} 
			return roomTypeWithRatecodesMap;
	}

	@Override
	public WbeOrderVO getWbeOrderVO(String hotelId, String channelId,String name1, String masterId,String crsno) {
		Master master = masterDao.getMasterOrderByOrderNo( hotelId,  channelId, name1,  masterId, crsno);
		if(master==null){
			return null;
		}
		List<MasterRate> MasterRateList = masterDao.getMasterRate(masterId);
		WbeOrderVO wbeOrderVO = new WbeOrderVO();
		wbeOrderVO.setCrsno(master.getCrsno());
		wbeOrderVO.setSta(master.getSta());
		wbeOrderVO.setRestype(master.getRestype());
		wbeOrderVO.setMasterId(masterId);
		wbeOrderVO.setHotelId(master.getHotelId());
		wbeOrderVO.setChannelCode(master.getChainCode());
		wbeOrderVO.setChannelCode(master.getChannel());
		wbeOrderVO.setChannelId(master.getChannelId());
		wbeOrderVO.setHotelCode(master.getHotelCode());
		wbeOrderVO.setNumberOfUnits(master.getGstno());
		wbeOrderVO.setArr(master.getArr());
		wbeOrderVO.setDep(master.getDep());
		wbeOrderVO.setNumber(1);
		wbeOrderVO.setRoomTypeCode(master.getType());
		wbeOrderVO.setRoomTypeName(master.getRoomTypeName());
		wbeOrderVO.setRatePlanCode(master.getRatePlanCode());
		wbeOrderVO.setRatePlanId(master.getRatePlanId());
		wbeOrderVO.setAccessCode(master.getQualifyingIdValue());
		wbeOrderVO.setAccessCodeType(master.getQualifyingIdType());
		wbeOrderVO.setPayment(master.getPayment());
		wbeOrderVO.setCardCode(master.getCardCode());
		wbeOrderVO.setCardHolderName(master.getCardHolderName());
		wbeOrderVO.setCardNumber(master.getCardNumber());
		wbeOrderVO.setCurrencyCode(master.getCurrencyCode());
		if(master.getExpirationDate()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			wbeOrderVO.setExpirationDate(sdf.format(master.getExpirationDate()));;
		}
		wbeOrderVO.setCharge(master.getCharge());
		
		List<OrderDailyRateVO> dailRateList = new ArrayList<OrderDailyRateVO>();
		for(MasterRate m:MasterRateList){
			OrderDailyRateVO o = new OrderDailyRateVO();
			o.setPrice(m.getSetrate().doubleValue());
			o.setPriceDate(m.getDate());
			dailRateList.add(o);
		}
		wbeOrderVO.setDailRateList(dailRateList);
		
		List<WbeGuestVO> wbeGuestVoList = new ArrayList<WbeGuestVO>();
		WbeGuestVO w = new WbeGuestVO();
		w.setArr(master.getEarliestTime());
		w.setName1(master.getName());
		w.setName2(master.getName2());
		w.setName3(master.getName4());
		w.setTel(master.getMobile());
		w.setNeed(master.getRef());
		wbeGuestVoList.add(w);
		wbeOrderVO.setWbeGuestVoList(wbeGuestVoList);
		wbeOrderVO.setWbeGuestVO(wbeGuestVoList.get(0));
		
		return wbeOrderVO;
	}

}
