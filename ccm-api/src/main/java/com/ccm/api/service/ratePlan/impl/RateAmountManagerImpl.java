package com.ccm.api.service.ratePlan.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.ratePlan.RateAmountDao;
import com.ccm.api.dao.ratePlan.RatePackageDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.wbe.WbeCalendarCell;
@Service("rateAmountManager")
public class RateAmountManagerImpl extends GenericManagerImpl<RateAmount, String> implements RateAmountManager{

	@Resource
    private RateAmountDao rateAmountDao;
	@Resource
	private RatePackageDao ratePackageDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private RateplanDao rateplanDao;
	
	@Override
	public RateAmount addRateAmount(RateAmount rateAmount) {
		return rateAmountDao.addRateAmount(rateAmount);
	}

	@Override
	public void deleteRateAmountByrRateDetailId(String rateDetailId) {
		rateAmountDao.deleteRateAmountByrRateDetailId(rateDetailId);
	}

	@Override
	public List<RoomMsg> setAfterAmountWithTaxForRoomMsg(List<RoomMsg> rmList){
		for(RoomMsg rm:rmList){
				Rateplan rp =rateplanDao.getRateplanByRateplanCode(rm.getRateCode(),rm.getHotelCode());
				setAmountAfterTaxForCalendar(rm,rp.getRatePlanId());
		}
		return rmList;
	}
	
	// usage for push rate msg
	@Override
	public List<?> getAmountAfterTaxForCalendar(String ratePlanId,List<?>oList){
		return null;
	}
	
	@Override
	public void getAmountAfterTaxWithDetailPackForJSON(Channel channel,String ratePlanCode,String hotelCode,String roomTypeCode,JSONObject jsonCalendarCellMap,Date endDate){
		if(channel.getUseAmountAfterTax()==true){
			Rateplan rp =rateplanDao.getRateplanByRateplanCode(ratePlanCode,hotelCode);
			getAmountAfterTaxForJSON(jsonCalendarCellMap,rp.getRatePlanId(),hotelCode,roomTypeCode,endDate);
		}
	}
	
	@Override
	public void getAmountAfterTaxForJSON(String channelCode,String ratePlanCode,String hotelCode,JSONObject jsonCalendarCellMap,Date endDate){
		Channel channel=channelDao.getChannelByChannelCode(channelCode);
		if(channel.getUseAmountAfterTax()==true){
			Rateplan rp =rateplanDao.getRateplanByRateplanCode(ratePlanCode,hotelCode);
			getAmountAfterTaxForJSON(jsonCalendarCellMap,rp.getRatePlanId(),endDate);
		}
	}
	
	@Override
	public void setAmountAfterTaxForCalendar(Object o,String ratePlanId){
		try {
			Date curDate=DateUtil.convertStringToDate(((RoomMsg)o).getStartDate());
			String roomTypeCode=((RoomMsg)o).getRoomTypeCode();
			String hotelCode=((RoomMsg)o).getHotelCode();
			if(o instanceof RoomMsg){
				List<RateAmount> rateAmountList=((RoomMsg)o).getRateAmountList();
				List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(ratePlanId);
				Map paramMap=new HashMap();
				paramMap.put("hotelCode",hotelCode);
				paramMap.put("ratePlanId",ratePlanId);
				paramMap.put("roomTypeCode",roomTypeCode);
				paramMap.put("stayDate",DateUtil.getDate(curDate));
				
					if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToMonday",true);
					} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToTuesday",true);
					} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToWednesday",true);
					} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToThursday",true);
					} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToFriday",true);
					} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToSaturday",true);
					} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToSunday",true);
					}
				List<Package> detailPackList=ratePackageDao.getSvcOrTaxFromRateDetail(paramMap);
				detailPackList=wrapPackageList(packList,detailPackList);
				for(RateAmount ratemount:rateAmountList){
					BigDecimal dateBaseAmount=ratemount.getBaseAmount();
					ratemount.setAmountAfterTax(dateBaseAmount);
					Integer numberOfUnits=ratemount.getNumberOfUnits();
					BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,numberOfUnits,detailPackList, curDate,null);
					ratemount.setAmountAfterTax(totalAmount);
				}
			}
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// fee to everyday for use
	@Override
	public void setAmountAfterTaxForCalendar(PriceCalc priceCalc){
			Rateplan rp =rateplanDao.getRateplanByRateplanCode(priceCalc.getRatePlanCode(),priceCalc.getHotelCode());
			List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(rp.getRatePlanId());
			BigDecimal dateBaseAmount=priceCalc.getAmount();
			Date curDate=null;
			try {
				curDate=DateUtil.convertStringToDate(priceCalc.getDate());
				Map paramMap=new HashMap();
				paramMap.put("hotelCode", priceCalc.getHotelCode());
				paramMap.put("ratePlanId",rp.getRatePlanId());
				paramMap.put("roomTypeCode",priceCalc.getRoomTypeCode());
				paramMap.put("stayDate",priceCalc.getDate());
				
					if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToMonday",true);
					} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToTuesday",true);
					} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToWednesday",true);
					} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToThursday",true);
					} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToFriday",true);
					} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToSaturday",true);
					} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToSunday",true);
					}
				List<Package> detailPackList=ratePackageDao.getSvcOrTaxFromRateDetail(paramMap);
				detailPackList=wrapPackageList(packList,detailPackList);
				BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,priceCalc.getNumberOfUnits(),detailPackList,curDate,null);
				priceCalc.setAmount(totalAmount);
				priceCalc.setAmountAfterTax(totalAmount);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//fee to last day for use
	@Override
	public void setAmountAfterTaxForOrder(PriceCalc priceCalc,Date svcDate,Boolean isUpdateAmount){
		Rateplan rp =rateplanDao.getRateplanByRateplanCode(priceCalc.getRatePlanCode(),priceCalc.getHotelCode());
		List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(rp.getRatePlanId());
		BigDecimal dateBaseAmount=priceCalc.getAmount();
		Date curDate=null;
		try {
			curDate=DateUtil.convertStringToDate(priceCalc.getDate());
			Map paramMap=new HashMap();
			paramMap.put("hotelCode", priceCalc.getHotelCode());
			paramMap.put("ratePlanId",rp.getRatePlanId());
			paramMap.put("roomTypeCode",priceCalc.getRoomTypeCode());
			paramMap.put("stayDate",priceCalc.getDate());
			
			if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToMonday",true);
			} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToTuesday",true);
			} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToWednesday",true);
			} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToThursday",true);
			} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToFriday",true);
			} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToSaturday",true);
			} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
				paramMap.put("isApplyToSunday",true);
			}
			List<Package> detailPackList=ratePackageDao.getSvcOrTaxFromRateDetail(paramMap);
			detailPackList=wrapPackageList(packList,detailPackList);
			BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,priceCalc.getNumberOfUnits(),detailPackList,curDate,svcDate);
			if(isUpdateAmount)
				priceCalc.setAmount(totalAmount);
			priceCalc.setAmountAfterTax(totalAmount);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void setAmountAfterTaxForOrderOws(PriceCalc priceCalc,Date svcDate,Boolean isUpdateAmount){
		Rateplan rp =rateplanDao.getRateplanByRateplanCode(priceCalc.getRatePlanCode(),priceCalc.getHotelCode());
		List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(rp.getRatePlanId());
		BigDecimal dateBaseAmount=priceCalc.getAmount();
		Date curDate=null;
		try {
			curDate=DateUtil.convertStringToDate(priceCalc.getDate());
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("hotelCode", priceCalc.getHotelCode());
			paramMap.put("ratePlanId",rp.getRatePlanId());
			paramMap.put("roomTypeCode",priceCalc.getRoomTypeCode());
			paramMap.put("stayDate",priceCalc.getDate());
			
				if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToMonday",true);
				} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToTuesday",true);
				} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToWednesday",true);
				} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToThursday",true);
				} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToFriday",true);
				} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToSaturday",true);
				} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToSunday",true);
				}
			List<Package> detailPackList=ratePackageDao.getSvcOrTaxFromRateDetail(paramMap);
			detailPackList=wrapPackageList(packList,detailPackList);
			BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,priceCalc.getNumberOfUnits(),detailPackList,curDate,svcDate);
			if(isUpdateAmount)
				priceCalc.setAmount(totalAmount);
			priceCalc.setAmountAfterTax(totalAmount);
			priceCalc =calSvcOrTax(priceCalc.getNumberOfUnits(),detailPackList,curDate,svcDate,priceCalc);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void getAmountAfterTaxForWEB(Map<Date, WbeCalendarCell> webCalendarCellMap,String ratePlanId,Date svcDate){
		List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(ratePlanId);
		Set<Date>dateSet=webCalendarCellMap.keySet();
		for(Date curDate:dateSet){
			WbeCalendarCell wbeCalendarCell=webCalendarCellMap.get(curDate);
				//baseAmount of perDay
				BigDecimal dateBaseAmount=wbeCalendarCell.getCellPrice();
				Integer numberOfunits=wbeCalendarCell.getCellNumberOfUnits();
				BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,numberOfunits,packList,curDate,svcDate);
				wbeCalendarCell.setCellPrice(totalAmount);
		}
	}
	
	@Override
	public void getAmountAfterTaxWithDetailPackForWEB(Channel channel,String ratePlanCode,String hotelCode,String roomTypeCode,Map<Date, WbeCalendarCell> webCalendarCellMap,String ratePlanId,Date svcDate){
		if(channel.getUseAmountAfterTax()==true){
			List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(ratePlanId);
			Set<Date>dateSet=webCalendarCellMap.keySet();
			for(Date curDate:dateSet){
				Map paramMap=new HashMap();
				paramMap.put("hotelCode", hotelCode);
				paramMap.put("ratePlanId", ratePlanId);
				paramMap.put("roomTypeCode",roomTypeCode);
				paramMap.put("stayDate", DateUtil.getDate(curDate));
				
					if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToMonday",true);
					} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToTuesday",true);
					} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToWednesday",true);
					} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToThursday",true);
					} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToFriday",true);
					} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToSaturday",true);
					} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
						paramMap.put("isApplyToSunday",true);
					}
				List<Package> detailPackList=ratePackageDao.getSvcOrTaxFromRateDetail(paramMap);
				detailPackList=wrapPackageList(packList,detailPackList);
				WbeCalendarCell wbeCalendarCell=webCalendarCellMap.get(curDate);
					//baseAmount of perDay
					BigDecimal dateBaseAmount=wbeCalendarCell.getCellPrice();
					Integer numberOfunits=wbeCalendarCell.getCellNumberOfUnits();
					BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,numberOfunits,detailPackList,curDate,svcDate);
					wbeCalendarCell.setCellPrice(totalAmount);
			}
		}
	}
	
	private List <Package> wrapPackageList(List<Package>headerPackageList,List<Package>detailPackageList){
		Map<String,Package>packageMap=new HashMap<String,Package>();
		for(Package p:detailPackageList){
			packageMap.put(p.getPackageCode(), p);
		}
		for(Package p:headerPackageList){
			packageMap.put(p.getPackageCode(), p);
		}
		List<Package> packList=new ArrayList<Package>();
		packList.addAll(packageMap.values());
		return packList;
	}
	private  void getAmountAfterTaxForJSON(JSONObject jsonCalendarCellMap,String ratePlanId,String hotelCode,String roomTypeCode,Date svcDate){
		List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(ratePlanId);
		Set<String>dateSet=jsonCalendarCellMap.keySet();
		for(String curDateStr:dateSet){
			Date curDate=new Date(new Long(curDateStr));
			
			Map paramMap=new HashMap();
			paramMap.put("hotelCode", hotelCode);
			paramMap.put("ratePlanId", ratePlanId);
			paramMap.put("roomTypeCode",roomTypeCode);
			paramMap.put("stayDate", DateUtil.getDate(curDate));
			
				if (Calendar.MONDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToMonday",true);
				} else if (Calendar.TUESDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToTuesday",true);
				} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToWednesday",true);
				} else if (Calendar.THURSDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToThursday",true);
				} else if (Calendar.FRIDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToFriday",true);
				} else if (Calendar.SATURDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToSaturday",true);
				} else if (Calendar.SUNDAY == DateUtil.getWeekday(curDate)) {
					paramMap.put("isApplyToSunday",true);
				}
			List<Package> detailPackList=ratePackageDao.getSvcOrTaxFromRateDetail(paramMap);
			detailPackList=wrapPackageList(packList,detailPackList);
			JSONObject jsonCalendarCell=jsonCalendarCellMap.getJSONObject(curDateStr);
			//Map<Integer,RateAmount>
			Integer cellNumberOfUnits=jsonCalendarCell.getInteger("cellNumberOfUnits");
			JSONObject jsonRateAMountMap=jsonCalendarCell.getJSONObject("rateAmountMap");
			Set<String> numberOfunitsKeySet= jsonRateAMountMap.keySet();
			for(String numberOfunitsKey:numberOfunitsKeySet){
				//baseAmount of perDay
				Integer numberOfunitsInt= new Integer(numberOfunitsKey);
				BigDecimal dateBaseAmount=jsonRateAMountMap.getJSONObject(numberOfunitsKey).getBigDecimal("baseAmount");
				BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,numberOfunitsInt,detailPackList,curDate,svcDate);
				jsonRateAMountMap.getJSONObject(numberOfunitsKey).put("baseAmount", totalAmount);
				if(cellNumberOfUnits.equals(numberOfunitsInt))
					jsonCalendarCell.put("cellPrice",totalAmount);
			}
		}
	}
	@Override
	public void getAmountAfterTaxForJSON(JSONObject jsonCalendarCellMap,String ratePlanId,Date svcDate){
		List<Package> packList=ratePackageDao.getSvcTaxPackageByRatePlanId(ratePlanId);
		Set<String>dateSet=jsonCalendarCellMap.keySet();
		for(String curDateStr:dateSet){
			Date curDate=new Date(new Long(curDateStr));
			JSONObject jsonCalendarCell=jsonCalendarCellMap.getJSONObject(curDateStr);
			//Map<Integer,RateAmount>
			Integer cellNumberOfUnits=jsonCalendarCell.getInteger("cellNumberOfUnits");
			JSONObject jsonRateAMountMap=jsonCalendarCell.getJSONObject("rateAmountMap");
			Set<String> numberOfunitsKeySet= jsonRateAMountMap.keySet();
			for(String numberOfunitsKey:numberOfunitsKeySet){
				//baseAmount of perDay
				Integer numberOfunitsInt= new Integer(numberOfunitsKey);
				BigDecimal dateBaseAmount=jsonRateAMountMap.getJSONObject(numberOfunitsKey).getBigDecimal("baseAmount");
				BigDecimal totalAmount=calSvcOrTax(dateBaseAmount,numberOfunitsInt,packList,curDate,svcDate);
				jsonRateAMountMap.getJSONObject(numberOfunitsKey).put("baseAmount", totalAmount);
				if(cellNumberOfUnits.equals(numberOfunitsInt))
					jsonCalendarCell.put("cellPrice",totalAmount);
			}
		}
	}
	
	private BigDecimal getValueOfPercentageOrFee(String packageCode,int endIndex){
		String svcOrTaxRate=packageCode.substring(5, endIndex);
		svcOrTaxRate=svcOrTaxRate.replace("X", ".");
		BigDecimal svcOrTaxRateValue=new  BigDecimal(svcOrTaxRate);
		return svcOrTaxRateValue;
	}
	
	private BigDecimal calSvcOrTax(BigDecimal dateBaseAmount,Integer numberOfUnits,List<Package>packList,Date curDate,Date svcDate){
		BigDecimal addtionalFee=new BigDecimal(0);
		BigDecimal totalAmount=new BigDecimal(0);
		if(CommonUtil.isNotEmpty(packList)){
			for(Package pack:packList){
				if(!(pack.getIssvcortax()==null||pack.getIssvcortax().booleanValue()==false)){
					String packageCode=pack.getPackageCode().trim();
					String codeType=packageCode.substring(4,5);
					if(packageCode.startsWith("TAX_")||packageCode.startsWith("SVC_")){
							// per person per night
						 if(packageCode.endsWith("PN")){
						 		int endIndex=packageCode.indexOf("PN",4);
								//percentage or fee
								BigDecimal svcOrTaxRateValue=getValueOfPercentageOrFee(packageCode,endIndex);
								if(codeType.equals("F")){
									//fee type
									svcOrTaxRateValue=svcOrTaxRateValue.multiply(new BigDecimal(numberOfUnits));
									addtionalFee=AmountUtil.add(addtionalFee,svcOrTaxRateValue);
								}else if(codeType.equals("P")){
									//percentage type
									svcOrTaxRateValue=svcOrTaxRateValue.multiply(new BigDecimal(0.01));
									svcOrTaxRateValue=dateBaseAmount.multiply(svcOrTaxRateValue).multiply(new BigDecimal(numberOfUnits));
									totalAmount=AmountUtil.add(totalAmount,svcOrTaxRateValue);
								}
						}else if(packageCode.endsWith("PS")){
							//per person per time
							int endIndex=packageCode.indexOf("PS",4);
							//percentage or fee
							BigDecimal svcOrTaxRateValue=getValueOfPercentageOrFee(packageCode,endIndex);
							if(codeType.equals("F")){
								//fee type
								if(svcDate==null||curDate.equals(svcDate)){
									svcOrTaxRateValue=svcOrTaxRateValue.multiply(new BigDecimal(numberOfUnits));
									addtionalFee=AmountUtil.add(addtionalFee,svcOrTaxRateValue);
									}
							}else if(codeType.equals("P")){
								//percentage type
								svcOrTaxRateValue=svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								svcOrTaxRateValue=dateBaseAmount.multiply(svcOrTaxRateValue).multiply(new BigDecimal(numberOfUnits));
								totalAmount=totalAmount.add(svcOrTaxRateValue);
							}
						}else if(packageCode.endsWith("N")){
							//per room per night
							int endIndex=packageCode.indexOf("N",4);
							//percentage or fee
							BigDecimal svcOrTaxRateValue=getValueOfPercentageOrFee(packageCode,endIndex);
							if(codeType.equals("F")){
								//fee type
								addtionalFee=AmountUtil.add(addtionalFee,svcOrTaxRateValue);
							}else if(codeType.equals("P")){
								//percentage type
								svcOrTaxRateValue=svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								svcOrTaxRateValue=dateBaseAmount.multiply(svcOrTaxRateValue);
								totalAmount=AmountUtil.add(totalAmount,svcOrTaxRateValue);
							}
						}else if(packageCode.endsWith("S")){
							//per time
							int endIndex=packageCode.indexOf("S",4);
							//percentage or fee
							BigDecimal svcOrTaxRateValue=getValueOfPercentageOrFee(packageCode,endIndex);
							
							if(codeType.equals("F")){
								//fee type
								if(svcDate==null||curDate.equals(svcDate)){
									addtionalFee=AmountUtil.add(addtionalFee,svcOrTaxRateValue);
								}
							}else if(codeType.equals("P")){
								//percentage type
								svcOrTaxRateValue=svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								svcOrTaxRateValue=dateBaseAmount.multiply(svcOrTaxRateValue);
								totalAmount=AmountUtil.add(totalAmount,svcOrTaxRateValue);
							}
							
						}
				}
			}
		}
		}
		totalAmount=AmountUtil.convert2ByFloorMode(totalAmount);
		addtionalFee=AmountUtil.convert2ByFloorMode(addtionalFee);
		totalAmount=AmountUtil.add(totalAmount,dateBaseAmount);
		totalAmount=AmountUtil.add(totalAmount,addtionalFee);
		totalAmount=AmountUtil.convert2ByFloorMode(totalAmount);
		return totalAmount;
	}
	
	/**
	 * 计算固定税费和税率
	 * @param numberOfUnits
	 * @param packList
	 * @param curDate
	 * @param svcDate
	 * @param pc
	 * @return
	 */
	private PriceCalc calSvcOrTax(Integer numberOfUnits,List<Package> packList, Date curDate, Date svcDate, PriceCalc pc) {
		BigDecimal taxFeeN = new BigDecimal("0");//固定税费 每间房每晚,
		BigDecimal taxFeeS = new BigDecimal("0");//固定税费 每间房每次入住
		BigDecimal taxFeePS= new BigDecimal("0");//固定税费 每人每次入住
		BigDecimal taxFeePN= new BigDecimal("0");//固定税费 每人每晚
		
		BigDecimal taxRateN = new BigDecimal("0");//税率 每间房每晚,
		BigDecimal taxRateS = new BigDecimal("0");//税率 每间房每次入住
		BigDecimal taxRatePS= new BigDecimal("0");//税率 每人每次入住
		BigDecimal taxRatePN= new BigDecimal("0");//税率 每人每晚
		if (CommonUtil.isNotEmpty(packList)) {
			for (Package pack : packList) {
				if (!(pack.getIssvcortax() == null || pack.getIssvcortax().booleanValue() == false)) {
					String packageCode = pack.getPackageCode().trim();
					String codeType = packageCode.substring(4, 5);
					if (packageCode.startsWith("TAX_")|| packageCode.startsWith("SVC_")) {
						// per person per night
						if (packageCode.endsWith("PN")) {
							int endIndex = packageCode.indexOf("PN", 4);
							// percentage or fee
							BigDecimal svcOrTaxRateValue = getValueOfPercentageOrFee(packageCode, endIndex);
							if (codeType.equals("F")) {
								// fee type
								taxFeePN = AmountUtil.add(taxFeePN,svcOrTaxRateValue);
							} else if (codeType.equals("P")) {
								// percentage type
								svcOrTaxRateValue = svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								taxRatePN = AmountUtil.add(taxRatePN,svcOrTaxRateValue);
							}
						} else if (packageCode.endsWith("PS")) {
							// per person per time
							int endIndex = packageCode.indexOf("PS", 4);
							// percentage or fee
							BigDecimal svcOrTaxRateValue = getValueOfPercentageOrFee(packageCode, endIndex);
							if (codeType.equals("F")) {
								// fee type
								if (svcDate == null || curDate.equals(svcDate)) {
									taxFeePS = AmountUtil.add(taxFeePS,svcOrTaxRateValue);
								}
							} else if (codeType.equals("P")) {
								// percentage type
								svcOrTaxRateValue = svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								taxRatePS = AmountUtil.add(taxRatePS,svcOrTaxRateValue);
							}
						} else if (packageCode.endsWith("N")) {
							// per room per night
							int endIndex = packageCode.indexOf("N", 4);
							// percentage or fee
							BigDecimal svcOrTaxRateValue = getValueOfPercentageOrFee(packageCode, endIndex);
							if (codeType.equals("F")) {
								// fee type
								taxFeeN = AmountUtil.add(taxFeeN,svcOrTaxRateValue);
							} else if (codeType.equals("P")) {
								// percentage type
								svcOrTaxRateValue = svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								taxRateN = AmountUtil.add(taxRateN,svcOrTaxRateValue);
							}
						} else if (packageCode.endsWith("S")) {
							// per time
							int endIndex = packageCode.indexOf("S", 4);
							// percentage or fee
							BigDecimal svcOrTaxRateValue = getValueOfPercentageOrFee(packageCode, endIndex);

							if (codeType.equals("F")) {
								// fee type
								if (svcDate == null || curDate.equals(svcDate)) {
									taxFeeS = AmountUtil.add(taxFeeS,svcOrTaxRateValue);
								}
							} else if (codeType.equals("P")) {
								// percentage type
								svcOrTaxRateValue = svcOrTaxRateValue.multiply(new BigDecimal(0.01));
								taxRateS = AmountUtil.add(taxRateS,svcOrTaxRateValue);
							}
						}
					}
				}
			}
		}
		
		pc.setTaxFeePS(taxFeePS);
		pc.setTaxFeePN(taxFeePN);
		pc.setTaxFeeS(taxFeeS);
		pc.setTaxFeeN(taxFeeN);
		pc.setTaxRateN(taxRateN);
		pc.setTaxRateS(taxRateS);
		pc.setTaxRatePN(taxRatePN);
		pc.setTaxRatePS(taxRatePS);
		return pc;
	}


}
