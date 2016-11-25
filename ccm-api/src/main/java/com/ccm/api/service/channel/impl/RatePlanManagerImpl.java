/**
 * 
 */
package com.ccm.api.service.channel.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.pms.utils.DateUtils;
import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.dao.ratePlan.RateAmountDao;
import com.ccm.api.dao.ratePlan.RateCancelRelationshipDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.ratePlan.RateDetailDao;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.dao.ratePlan.RatePackageDao;
import com.ccm.api.dao.ratePlan.RoomPackageDao;
import com.ccm.api.dao.ratePlan.RoomRateDao;
import com.ccm.api.dao.ratePlan.mongodb.OldRateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.dao.sell.MarketDao;
import com.ccm.api.dao.sell.SourceDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.channel.vo.RoomRateDetailComparableVO;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.model.sell.vo.MarketVO;
import com.ccm.api.model.sell.vo.SourceVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.log.ReceiveReqResManager;
import com.ccm.api.service.ratePlan.CallAddPriceCalcManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RateDetailManager;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * 
 */
@Service("ratePlanManager")
public class RatePlanManagerImpl extends GenericManagerImpl<Rateplan, String> implements RatePlanManager {

	@Autowired
	private RateplanDao rateplanDao;
	@Resource
	private RatePackageDao ratePackageDao;
	@Resource
	private RateCancelRelationshipDao rateCancelRelationshipDao;
	@Resource
	private RateGuaranteeRelationshipDao rateGuaranteeRelationshipDao;
	@Resource
	private MarketDao marketDao;
	@Resource
	private SourceDao sourceDao;
	@Resource
	private DictCodeManager dictCodeManager;
	@Resource
	private RateDetailDao rateDetailDao;
	@Resource
	private RoomRateDao roomRateDao;
	@Resource
	private RoomPackageDao roomPackageDao;
	@Resource
	private RateAmountDao rateAmountDao;
	@Autowired
	private CancelPolicyDao cancelPolicyDao;
	@Resource
	private PriceCalcManager priceCalcManager;

	@Autowired
	private UserManager userManager;
	@Autowired
	private HotelManager hotelManager;

	@Resource
	private TaobaoApiManager taobaoApiManager;
	@Resource
	private ReceiveReqResManager receiveReqResManager;
	@Resource
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Resource
	private RateDetailManager rateDetailManager;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private RateDetailVODaoMongo rateDetailVODaoMongo;
	@Resource
	private CallAddPriceCalcManager callAddPriceCalcManager;
	@Resource
	private OldRateDetailVODaoMongo oldRateDetailVODaoMongo;
	@Resource
	private ChannelgoodsManager channelgoodsManager;
	
	@Autowired
	public void setRateplanDao(RateplanDao rateplanDao) {
		this.rateplanDao = rateplanDao;
		this.dao = rateplanDao;
	}

	public boolean compareObject(Object b1, Object b2) {
		if (b1 != null && b2 != null) {
			if (b1.equals(b2)) {
				return true;
			}
		} else if (b1 == b2) {
			return true;
		}
		return false;
	}

    // getRateDetailId from Map
    private String getSavedRateDetailId(String rateDetailDateKey,List <RateAmount> needComparedRateAmountList,Map<String,Map<String,Set<String>>>savedRateDetailMap){
 	   Map<String,Set<String>> savedOldRateAmountMap=savedRateDetailMap.get(rateDetailDateKey);
 	   if(savedOldRateAmountMap!=null){
 		   for(Map.Entry<String,Set<String>> entry :savedOldRateAmountMap.entrySet()){
 	   			Set<String> savedOldRateAMountSet= savedOldRateAmountMap.get(entry.getKey());
 	   			int sameCount=0;
 		   		for(RateAmount existedRateAmount:needComparedRateAmountList){
 		 		   String existedPriceKey = existedRateAmount.getNumberOfUnits()+"|"+existedRateAmount.getBaseAmount().floatValue();
 		 		   		if(savedOldRateAMountSet.contains(existedPriceKey)){
 		 		   		sameCount++;
 		 		   		}
 		   			}
 		   		if(sameCount==needComparedRateAmountList.size()){
 		   			return entry.getKey();
 		   		}
 	   		}
 	   }
 	   return null;
    }
    // Map<key:date+week,Map<rateDetailId,RateAmountSet>>
    private void setRateAmountKeyIntoMap(String rateDetailMapKey,String rateDetailId,String rateAmountKey,Map<String,Map<String,Set<String>>>savedRateDetailMap){
    	if(savedRateDetailMap.get(rateDetailMapKey)==null){
    		Map<String,Set<String>>rateAmountMap=new HashMap<String,Set<String>>();
    		Set<String>rateAmountSet=new HashSet<String>();
    		rateAmountSet.add(rateAmountKey);
    		rateAmountMap.put(rateDetailId, rateAmountSet);
    		savedRateDetailMap.put(rateDetailMapKey,rateAmountMap);
    	}else{
    		Map<String,Set<String>>rateAmountMap=savedRateDetailMap.get(rateDetailMapKey);
    		if(rateAmountMap.get(rateDetailId)==null){
    			Set<String>rateAmountSet=new HashSet<String>();
        		rateAmountSet.add(rateAmountKey);
        		rateAmountMap.put(rateDetailId, rateAmountSet);
    		}else{
    			rateAmountMap.get(rateDetailId).add(rateAmountKey);
    		}
    	}
    }
    
    private String getRateAmountKey(RateAmount rateAmount){
    	return rateAmount.getNumberOfUnits()+"|"+rateAmount.getBaseAmount().floatValue();
    }
    //保存RATEDETAIL日期全新部分
   public boolean saveRateDetailForNewPart(String savedRateDetailMapKey,Map<String,Map<String,Set<String>>>savedRateDetailMap,RateDetailVO rateDetailVO,RoomRateVO roomRateVO){

	   //1.判断有没有相同时间的记录,没有就保存rateDetail&rateAmount
	   	List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
		if(savedRateDetailMap.get(savedRateDetailMapKey)==null||(savedRateDetailMap.get(savedRateDetailMapKey)!=null&&getSavedRateDetailId(savedRateDetailMapKey,rateAmountList,savedRateDetailMap)==null)){
			RateDetail rateDetail = rateDetailDao.addRateDetail(rateDetailVO);
			for (int i=0;i<rateAmountList.size();i++) {
				RateAmount rateAmount=rateAmountList.get(i);
				rateAmount.setRateAmountId(null);
				rateAmount.setRateDetailId(rateDetail.getRateDetailId());
				rateAmountDao.save(rateAmount);
				setRateAmountKeyIntoMap(savedRateDetailMapKey,rateDetail.getRateDetailId(),getRateAmountKey(rateAmount),savedRateDetailMap);
			}
			//2.根据当前房型保存rateRoom
			roomRateVO.setRoomRateId(null);
			roomRateVO.setRateDetailId(rateDetail.getRateDetailId());
			roomRateDao.addRoomRateVO(roomRateVO);
		}else{
		//2.根据当前房型保存rateRoom
			roomRateVO.setRoomRateId(null);
			roomRateVO.setRateDetailId(getSavedRateDetailId(savedRateDetailMapKey,rateAmountList,savedRateDetailMap));
			roomRateDao.addRoomRateVO(roomRateVO);
		}
			return true;
   }
   
   public Boolean intToBoolean(int value){
	   if(value==0){
		   return new Boolean(false);
	   }else{
		   return new Boolean(true);
	   }
	
   }
   public RateDetail setDaysIsApply(RateDetail rateDetail,String dayRange){
	   for(int i=0;i<dayRange.length();i++){
		   char isApplyOfChar=dayRange.charAt(i);
		   int booleanVal=Integer.parseInt(String.valueOf(isApplyOfChar));		
		   switch (i){
			   case 0: {
				   rateDetail.setIsApplyToSunday(intToBoolean(booleanVal));
			   }
			   case 1:{
				   rateDetail.setIsApplyToMonday(intToBoolean(booleanVal));
			   }
			   case 2:{
				   rateDetail.setIsApplyToTuesday(intToBoolean(booleanVal));
			   }
			   case 3:{
				   rateDetail.setIsApplyToWednesday(intToBoolean(booleanVal));
			   }
			   case 4:{
				   rateDetail.setIsApplyToThursday(intToBoolean(booleanVal));
			   }
			   case 5:{
				   rateDetail.setIsApplyToFriday(intToBoolean(booleanVal));
			   }
			   case 6:{
				   rateDetail.setIsApplyToSaturday(intToBoolean(booleanVal));
			   }
		   }
	   }
	   return rateDetail;
   }
    //创建旧单据日期被覆盖部分，但星期未包含的部分
   public void createRateDetailForDiffDaysOfCoveredOldPart(String uncoveredDayRange,Date changedEffictiveDate,Date changedExpireDate,String existedRateDetailId,Map<String,Map<String,Set<String>>>savedRateDetailMap,RateDetailVO rateDetailVO,RoomRateVO roomRateVO)throws CloneNotSupportedException{
	   RateDetail existedRateDetail=rateDetailDao.get(existedRateDetailId);
	   List <RateAmount> existedRateAmountList=rateAmountDao.getRateAmountByRateDetailId(existedRateDetailId);
	   
	   String coveredFromDate=DateUtil.getDate(changedEffictiveDate);
	   String coveredToDate=DateUtil.getDate(changedExpireDate);
	   String modifyOldRateDetailDateKey=coveredFromDate+"|"+coveredToDate+"|"+uncoveredDayRange;
	  
	
	   //create
		   //1.create rateDetail&根据原理的 rateAmount创建对应的数据
		   if(savedRateDetailMap.get(modifyOldRateDetailDateKey)==null||(savedRateDetailMap.get(modifyOldRateDetailDateKey)!=null&&getSavedRateDetailId(modifyOldRateDetailDateKey,existedRateAmountList,savedRateDetailMap)==null)){
				RateDetail copyExistedRateDetail= existedRateDetail.clone();
				copyExistedRateDetail.setRateDetailId(null);
				copyExistedRateDetail.setEffectiveDate(changedEffictiveDate);
				copyExistedRateDetail.setExpireDate(changedExpireDate);
				copyExistedRateDetail=setDaysIsApply(copyExistedRateDetail,uncoveredDayRange);
				copyExistedRateDetail=rateDetailDao.addRateDetail(copyExistedRateDetail);
				
				for(int i=0;i<existedRateAmountList.size();i++){
					RateAmount existedRateAmount=existedRateAmountList.get(i);
					RateAmount copyRateAmount=existedRateAmount.clone();
					copyRateAmount.setRateAmountId(null);
					copyRateAmount.setRateDetailId(copyExistedRateDetail.getRateDetailId());
					rateAmountDao.save(copyRateAmount);
					setRateAmountKeyIntoMap(modifyOldRateDetailDateKey,copyExistedRateDetail.getRateDetailId(),getRateAmountKey(copyRateAmount),savedRateDetailMap);
				}
				//2.根据当前房型保存rateRoom
			    RoomRateVO copyedRoomRateVO=roomRateVO.clone();
			    copyedRoomRateVO.setRoomRateId(null);
			    copyedRoomRateVO.setRateDetailId(copyExistedRateDetail.getRateDetailId());
			    roomRateDao.addRoomRateVO(copyedRoomRateVO);
		   	}else{
			 //2.根据当前房型保存rateRoom
			    RoomRateVO copyedRoomRateVO=roomRateVO.clone();
			    copyedRoomRateVO.setRoomRateId(null);
			    copyedRoomRateVO.setRateDetailId(getSavedRateDetailId(modifyOldRateDetailDateKey,existedRateAmountList,savedRateDetailMap));
				roomRateDao.addRoomRateVO(copyedRoomRateVO);
		   	}
   }
   //移除旧记录
   public void removeOldRateDetail(String existedRateDetailId,String existedRoomRateId){
	   List<RoomRateVO>existedRoomRateVOList=roomRateDao.getRoomRateVOByRateDetailId(existedRateDetailId);
	   //如果多条记录,清除RATEROOM
	   if(existedRoomRateVOList.size()>1){
		   roomRateDao.deleteRoomRateById(existedRoomRateId);
	   }else{
		   //清除RATEDETAIL
		   rateDetailDao.deleteRateDetailById(existedRateDetailId);
		   roomRateDao.deleteRoomRateById(existedRoomRateId);
		   rateAmountDao.deleteRateAmountByrRateDetailId(existedRateDetailId);
	   }
   }
   
   //移除旧记录
   public void removeOldRateDetail(String existedRateDetailId){
		   //清除RATEDETAIL
		   rateDetailDao.deleteRateDetailById(existedRateDetailId);
		   roomRateDao.deleteRoomRateByRateDetailId(existedRateDetailId);
		   rateAmountDao.deleteRateAmountByrRateDetailId(existedRateDetailId);
	   
   }
   
   public void removeOldRateDetailByRoomTypeId(String existedRateDetailId,String existedRoomTypeId){
	   List<RoomRateVO>existedRoomRateVOList=roomRateDao.getRoomRateVOByRateDetailId(existedRateDetailId);
	   //如果多条记录,清除RATEROOM
	   if(existedRoomRateVOList.size()>1){
		   roomRateDao.deleteRoomRateByRoomTypeId(existedRateDetailId,existedRoomTypeId);
	   }else{
		   //清除RATEDETAIL
		   rateDetailDao.deleteRateDetailById(existedRateDetailId);
		   roomRateDao.deleteRoomRateByRoomTypeId(existedRateDetailId,existedRoomTypeId);
		   rateAmountDao.deleteRateAmountByrRateDetailId(existedRateDetailId);
	   }
   }
    //创建旧单据独立的部分
   public void  createdRateDetailForIndependentOldPart(Date changedEffictiveDate,Date changedExpireDate,String existedRateDetailId,String existedRoomRateId,Map<String,Map<String,Set<String>>>savedRateDetailMap,RateDetailVO rateDetailVO,RoomRateVO roomRateVO) throws CloneNotSupportedException{
	   RateDetail existedRateDetail=rateDetailDao.get(existedRateDetailId);
	   List <RateAmount> existedRateAmountList=rateAmountDao.getRateAmountByRateDetailId(existedRateDetailId);
	   
	   //独立部分时间记录
	   String newFromDate=DateUtil.getDate(changedEffictiveDate);
	   String newToDate=DateUtil.getDate(changedExpireDate);
	   String createdRateDetailDateKey=newFromDate+"|"+newToDate+"|"+getDayType(existedRateDetail);
	
			   //1.create rateDetail&根据原理的 rateAmount创建对应的数据
			   if(savedRateDetailMap.get(createdRateDetailDateKey)==null||(savedRateDetailMap.get(createdRateDetailDateKey)!=null&&getSavedRateDetailId(createdRateDetailDateKey,existedRateAmountList,savedRateDetailMap)==null)){
					RateDetail copyExistedRateDetail= existedRateDetail.clone();
					copyExistedRateDetail.setRateDetailId(null);
					copyExistedRateDetail.setEffectiveDate(changedEffictiveDate);
					copyExistedRateDetail.setExpireDate(changedExpireDate);
					copyExistedRateDetail=rateDetailDao.addRateDetail(copyExistedRateDetail);
					
					for(int i=0;i<existedRateAmountList.size();i++){
						RateAmount existedRateAmount=existedRateAmountList.get(i);
						RateAmount copyRateAmount=existedRateAmount.clone();
						copyRateAmount.setRateAmountId(null);
						copyRateAmount.setRateDetailId(copyExistedRateDetail.getRateDetailId());
						rateAmountDao.save(copyRateAmount);
						setRateAmountKeyIntoMap(createdRateDetailDateKey,copyExistedRateDetail.getRateDetailId(),getRateAmountKey(copyRateAmount),savedRateDetailMap);
					}
					//2.根据当前房型保存rateRoom
				   	RoomRateVO copyRoomRateVO=roomRateVO.clone();
				   	copyRoomRateVO.setRoomRateId(null);
				   	copyRoomRateVO.setRateDetailId(copyExistedRateDetail.getRateDetailId());
					roomRateDao.addRoomRateVO(copyRoomRateVO);
			   	}else{
				 //2.根据当前房型保存rateRoom
				   	RoomRateVO copyRoomRateVO=roomRateVO.clone();
				   	copyRoomRateVO.setRoomRateId(null);
				   	copyRoomRateVO.setRateDetailId(getSavedRateDetailId(createdRateDetailDateKey,existedRateAmountList,savedRateDetailMap));
					roomRateDao.addRoomRateVO(copyRoomRateVO);
				}
	
   }
   
   public String getDayType(RateDetail rateDetail){
	   String daysType="";
	   if(rateDetail.getIsApplyToSunday()==null){
		   rateDetail.setIsApplyToSunday(false);
	   }
	   if(rateDetail.getIsApplyToMonday()==null){
		   rateDetail.setIsApplyToMonday(false);
	   }
	   if(rateDetail.getIsApplyToTuesday()==null){
		   rateDetail.setIsApplyToTuesday(false);
	   }
	   if(rateDetail.getIsApplyToWednesday()==null){
		   rateDetail.setIsApplyToWednesday(false);
	   }
	   if(rateDetail.getIsApplyToThursday()==null){
		   rateDetail.setIsApplyToThursday(false);
	   }
	   if(rateDetail.getIsApplyToFriday()==null){
		   rateDetail.setIsApplyToFriday(false);
	   }
	   if(rateDetail.getIsApplyToSaturday()==null){
		   rateDetail.setIsApplyToSaturday(false);
	   }
	   
	   daysType+=rateDetail.getIsApplyToSunday()==true?1:0;
	   daysType+=rateDetail.getIsApplyToMonday()==true?1:0;
	   daysType+=rateDetail.getIsApplyToTuesday()==true?1:0;
	   daysType+=rateDetail.getIsApplyToWednesday()==true?1:0;
	   daysType+=rateDetail.getIsApplyToThursday()==true?1:0;
	   daysType+=rateDetail.getIsApplyToFriday()==true?1:0 ;
	   daysType+=rateDetail.getIsApplyToSaturday()==true?1:0;
	   return daysType;
   }
   
   public boolean compareRateAmountIsSame(List<RateAmount> newRateAmountList,List <RateAmount> existedRateAmountList){
	   if(newRateAmountList.size()!= existedRateAmountList.size()){
		   return false;
	   }else{
		 outer:  for(int i=0;i<newRateAmountList.size();i++){
			   RateAmount newRateAmount=newRateAmountList.get(i);
			   Integer unitsOfnewRateAmount=newRateAmount.getNumberOfUnits();
			   	  for(int j=0;j<existedRateAmountList.size();j++){
				   RateAmount existedRateAmount=existedRateAmountList.get(j);
				   if(existedRateAmount.getNumberOfUnits().intValue()==unitsOfnewRateAmount.intValue()){
					   if(existedRateAmount.getBaseAmount().compareTo(newRateAmount.getBaseAmount())!=0){
						   return false;
					   }else{
						   continue outer;
					   }
				   }
			   }
		   }
	   }
	   return true;
   }
	public boolean autoHandleRateDetail(RatePlanVO vo,boolean isSplit){
		//找到其先相关的rateplan数据
		Rateplan rp = vo.getRp() == null ? rateplanDao.get(vo.getRatePlanId()) : vo.getRp();
		//原子房价明细数据集合(childrenlist)
		List<RateDetailVO> oldRefRdList = new ArrayList<RateDetailVO>();
		//原房价明细数据集合
		List<RateDetailVO> oldRdList = new ArrayList<RateDetailVO>();
		
		//设置rateplan的detailId
		rp.setRateDetailId(null);
		//获取原始房价明细数据
		List<RateDetailVO> rdvoList = getRateDetailVOList(rp);
		//并全部添加到oldRdList
		oldRdList.addAll(rdvoList);
		//通过rateplanId等于inheritRatePlanId获取子房价数据,获得子房价
		List<Rateplan> refRpList = getRefRateplan(rp.getHotelId(), rp.getRatePlanId());
		//循环子房价数据
		for (Rateplan refRp : refRpList) {
			//设置房价明细id
			refRp.setRateDetailId(null);
			//获取子房价的房价明细数据
			List<RateDetailVO> refRdVoList = getRateDetailVOList(refRp);
			//将字房价明细添加到oldRefRdList(childrenlist)
			if(oldRefRdList.contains(refRdVoList)==false){
				oldRefRdList.addAll(refRdVoList);
			}
		}
		//////////////////////////////////////////////////////////////////
		/*获取新单明细数据*/
		List <RateDetailVO> rateDetailVOList=vo.getRateDetailVOList();
		
		/*记录已存储相同的rateDetailId*/
		Map<String,Map<String,Set<String>>>savedRateDetailMap=new HashMap<String,Map<String,Set<String>>>();		
		//循环新的房价明细数据
		for (Iterator<RateDetailVO> iterator = rateDetailVOList.iterator(); iterator.hasNext();) {
			RateDetailVO rateDetailVO = iterator.next();
			if (rateDetailVO == null) {
				continue;
			}
			
			if(isSplit==true){
				rateDetailVO.setRateDetailId(null);
			}
			String	updatedRateDetailId = CommonUtil.isNotEmpty(rateDetailVO.getRateDetailId()) ? rateDetailVO.getRateDetailId():null;				
			
			
			
			rateDetailVO.setRatePlanId(vo.getRatePlanId());
			rateDetailVO.setRateDetailId(null);
			Date newEffectiveDate= rateDetailVO.getEffectiveDate();
			Date newExpireDate=rateDetailVO.getExpireDate();
			String fromDate=DateUtil.getDate(newEffectiveDate);
			String toDate=DateUtil.getDate(newExpireDate);
			
			/*比较现有数据,来判断日期范围的交集*/
			for(int i=0;i<rateDetailVO.getRoomRateList().size();i++){
				rateDetailVO.setRateDetailId(null);
				rateDetailVO.setEffectiveDate(newEffectiveDate);
				rateDetailVO.setExpireDate(newExpireDate);
				RoomRateVO roomRateVO=rateDetailVO.getRoomRateList().get(i);
				roomRateVO.setRoomRateId(null);
				Map<String,Object>conditionRateDetailMap=new HashMap<String,Object>();
				conditionRateDetailMap.put("ratePlanId", rateDetailVO.getRatePlanId());
				conditionRateDetailMap.put("roomTypeId", roomRateVO.getRoomTypeId());
				conditionRateDetailMap.put("newFromDate", fromDate);
				conditionRateDetailMap.put("newToDate", toDate);
				conditionRateDetailMap.put("weekIsApply", getDayType(rateDetailVO));
				conditionRateDetailMap.put("newToSunday", rateDetailVO.getIsApplyToSunday()==true?1:0);
				conditionRateDetailMap.put("newToMonday", rateDetailVO.getIsApplyToMonday()==true?1:0);
				conditionRateDetailMap.put("newToTuesday", rateDetailVO.getIsApplyToTuesday()==true?1:0);
				conditionRateDetailMap.put("newToWendsday", rateDetailVO.getIsApplyToWednesday()==true?1:0);
				conditionRateDetailMap.put("newToTursday", rateDetailVO.getIsApplyToThursday()==true?1:0);
				conditionRateDetailMap.put("newToFriday", rateDetailVO.getIsApplyToFriday()==true?1:0);
				conditionRateDetailMap.put("newToSaturday", rateDetailVO.getIsApplyToSaturday()==true?1:0);
				if(updatedRateDetailId!=null){
					conditionRateDetailMap.put("updatedRateDetailId",updatedRateDetailId);
					for(RateDetailVO oldRdVO:oldRdList){
						if(oldRdVO.getRateDetailId().equals(updatedRateDetailId)){
							oldRdVO.setDelFlag(true);
						}
					}
					for(RateDetailVO oldRefRdVO:oldRefRdList){
						if(oldRefRdVO.getRateDetailId().equals(updatedRateDetailId)){
							oldRefRdVO.setDelFlag(true);					
						}
					}
				}
				List<RoomRateDetailComparableVO> comparableList=rateDetailDao.getCompareableRateDetailVOByRatePlanId(conditionRateDetailMap);
				if(comparableList.size()>0){
					//数据库有roomRate相关的数据
					//创建新数据标识
					boolean isCreated=false;
					for(int j=0;j<comparableList.size();j++){
						RoomRateDetailComparableVO comparableRoomRateDetail=comparableList.get(j);
						String existedRateDetailId=comparableRoomRateDetail.getRateDetailid();
						Date existedEffectiveDate=comparableRoomRateDetail.getEffectiveDate();
						Date existedExpireDate=comparableRoomRateDetail.getExpireDate();
						String existedRoomRateId=comparableRoomRateDetail.getRoomRateid();
						Integer dateRangeType=comparableRoomRateDetail.getDateRangeType();
						String uncoveredDayRange=comparableRoomRateDetail.getUncoveredDayRange();
						Integer isDaysSame= comparableRoomRateDetail.getIsDaysSame();
						try {
							switch (dateRangeType){
								case 7:{//7
									//新单结束时间小于旧单开始时间(无包含)||新单开始时间大于旧单结束时间(无交集)
									if(!isCreated){
										String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
										isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									break;
								}
								case 4:{//4
									//新单结束时间小于旧单结束时间&新单开始时间小于旧单开始时间(前半部分超出,后半部分未到)
									//星期相同&&价格一致,合并
									if(isDaysSame==1&&compareRateAmountIsSame(rateDetailVO.getRateAmountList(),rateAmountDao.getRateAmountByRateDetailId(existedRateDetailId))){
										//使用最大范围日期
										if(!isCreated){
											String createdRateDetailDateKey=fromDate+"|"+DateUtil.getDate(existedExpireDate)+"|"+getDayType(rateDetailVO);
											rateDetailVO.setExpireDate(existedExpireDate);
											isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
									}else{
										//星期不一致或价格不一致,拆分
										if(!isCreated){
											String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
											isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
										//已包含部分，判断星期不一致,不一致就处理
										if(!comparableRoomRateDetail.getUncoveredDayRange().equals("0000000")){
											//old start date & new end date
											createRateDetailForDiffDaysOfCoveredOldPart(uncoveredDayRange,existedEffectiveDate,rateDetailVO.getExpireDate(),existedRateDetailId,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
										//没有包含的日期，用老数据创建后半部分
										Date modifyEffictiveDate = DateUtil.addDays(rateDetailVO.getExpireDate(), 1);
										createdRateDetailForIndependentOldPart(modifyEffictiveDate,existedExpireDate,existedRateDetailId,existedRoomRateId,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									removeOldRateDetail(existedRateDetailId,existedRoomRateId);
									break;
								}
								case 2:{//2
									//新单结束时间等于旧单结束时间&新单开始时间小于旧单开始时间(前半部分超出,后半部分重合)
									if(!isCreated){
										String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
										isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									//已包含部分，判断星期不一致,不一致就处理
									if(!comparableRoomRateDetail.getUncoveredDayRange().equals("0000000")){
										//old start date & new end date
										createRateDetailForDiffDaysOfCoveredOldPart(uncoveredDayRange,existedEffectiveDate,rateDetailVO.getExpireDate(),existedRateDetailId,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									//清除老数据的rateroom数据
									removeOldRateDetail(existedRateDetailId,existedRoomRateId);
									break;
								}
								case 1:{//1
									//新单结束时间小于等于旧单结束时间&新单开始时间大于等于旧单开始时间(新单在旧单范围内)
									//星期相同&&价格一致,合并
									if(isDaysSame==1&&compareRateAmountIsSame(rateDetailVO.getRateAmountList(),rateAmountDao.getRateAmountByRateDetailId(existedRateDetailId))){
										//使用最大范围日期
										if(!isCreated){
											String createdRateDetailDateKey=DateUtil.getDate(existedEffectiveDate)+"|"+DateUtil.getDate(existedExpireDate)+"|"+getDayType(rateDetailVO);
											rateDetailVO.setEffectiveDate(existedEffectiveDate);
											rateDetailVO.setExpireDate(existedExpireDate);
											isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
									}else{
										if(!isCreated){
											String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
											isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
										
										//已包含部分，判断星期不一致,不一致就处理
										if(!comparableRoomRateDetail.getUncoveredDayRange().equals("0000000")){
											//new start date & new end date
											createRateDetailForDiffDaysOfCoveredOldPart(uncoveredDayRange,rateDetailVO.getEffectiveDate(),rateDetailVO.getExpireDate(),existedRateDetailId,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
										
										//是否旧单开始时间超出了新单开始时间
										if(existedEffectiveDate.before(rateDetailVO.getEffectiveDate())){
											Date modifyExpireDate = DateUtil.addDays(rateDetailVO.getEffectiveDate(), -1);
											createdRateDetailForIndependentOldPart(existedEffectiveDate,modifyExpireDate,existedRateDetailId,existedRoomRateId,savedRateDetailMap,rateDetailVO,roomRateVO);
											}
										//是否旧单结束时间超出了新单结束时间
										if(existedExpireDate.after(rateDetailVO.getExpireDate())){
											Date modifyEffectiveDate = DateUtil.addDays(rateDetailVO.getExpireDate(), +1);
											createdRateDetailForIndependentOldPart(modifyEffectiveDate,existedExpireDate,existedRateDetailId,existedRoomRateId,savedRateDetailMap,rateDetailVO,roomRateVO);
											}
									}
									//清除老数据的rateroom数据
									removeOldRateDetail(existedRateDetailId,existedRoomRateId);
									break;
								}
								case 5:{//5
									 //新单开始时间大于旧单开始时间&新单结束时间大于旧单结束时间(后半部分超出，前半部分未到)
									//星期相同&&价格一致,合并
									if(isDaysSame==1&&compareRateAmountIsSame(rateDetailVO.getRateAmountList(),rateAmountDao.getRateAmountByRateDetailId(existedRateDetailId))){
										//使用最大范围日期
										if(!isCreated){
											String createdRateDetailDateKey=DateUtil.getDate(existedEffectiveDate)+"|"+toDate+"|"+getDayType(rateDetailVO);
											rateDetailVO.setEffectiveDate(existedEffectiveDate);
											isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
									}else{
										if(!isCreated){
											String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
											isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
										//已包含部分，判断星期不一致,不一致就处理
										if(!comparableRoomRateDetail.getUncoveredDayRange().equals("0000000")){
											//new start date & old end date
											createRateDetailForDiffDaysOfCoveredOldPart(uncoveredDayRange,rateDetailVO.getEffectiveDate(),existedExpireDate,existedRateDetailId,savedRateDetailMap,rateDetailVO,roomRateVO);
										}
										//没有包含的日期，用老数据创建前半部分
										Date modifyExpireDate = DateUtil.addDays(rateDetailVO.getEffectiveDate(),-1);
										createdRateDetailForIndependentOldPart(existedEffectiveDate,modifyExpireDate,existedRateDetailId,existedRoomRateId,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									//清除老数据的rateroom数据
									removeOldRateDetail(existedRateDetailId,existedRoomRateId);	
									break;
								}
								case 3:{//3
									//新单开始时间等于旧单开始时间&新单结束时间大于旧单结束时间(后半部分超出，前半部分重合)
									if(!isCreated){
										String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
										isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									//已包含部分，判断星期不一致,不一致就处理
									if(!comparableRoomRateDetail.getUncoveredDayRange().equals("0000000")){
										createRateDetailForDiffDaysOfCoveredOldPart(uncoveredDayRange,rateDetailVO.getEffectiveDate(),existedExpireDate,existedRateDetailId,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									removeOldRateDetail(existedRateDetailId,existedRoomRateId);
									break;
								}
								case 6:{//6
									//新单开始时间小于旧单开始时间&新单结束时间大于旧单结束时间(两头超出)
									if(!isCreated){
										String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
										isCreated=saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									//已包含部分，判断星期不一致,不一致就处理
									if(!comparableRoomRateDetail.getUncoveredDayRange().equals("0000000")){
										// old start date or old end date
										createRateDetailForDiffDaysOfCoveredOldPart(uncoveredDayRange,existedEffectiveDate,existedExpireDate,existedRateDetailId,savedRateDetailMap,rateDetailVO,roomRateVO);
									}
									removeOldRateDetail(existedRateDetailId,existedRoomRateId);
									break;
								}
								}
							}catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}else{
					//数据库无roomRate相关数据,就直接存新单据
					String createdRateDetailDateKey=fromDate+"|"+toDate+"|"+getDayType(rateDetailVO);
					saveRateDetailForNewPart(createdRateDetailDateKey,savedRateDetailMap,rateDetailVO,roomRateVO);
					
				}

			}
			if(updatedRateDetailId!=null){
				removeOldRateDetail(updatedRateDetailId);
			}
			
			rateDetailVO.setEffectiveDate(newEffectiveDate);
			rateDetailVO.setExpireDate(newExpireDate);
		}
		
		String uuidSign = UUID.randomUUID().toString().replace("-", "");
		 rateDetailVODaoMongo.batchSaveMongo(rateDetailVOList,vo.getChainCode(), vo.getHotelCode(),rp.getRatePlanCode(),uuidSign,RateDetailVO.RATETYPE_CCM);
		 oldRateDetailVODaoMongo.batchSaveMongo(oldRdList, vo.getChainCode(), vo.getHotelCode(),rp.getRatePlanCode(),uuidSign,RateDetailVO.RATETYPE_CCM  );
		 rp.setHotelCode(vo.getHotelCode());
		 rp.setIsPmsUpload(false);
		 callAddPriceCalcManager.startAddPmsPriceCalc(uuidSign,rp);
		 //循环子rateplan,并保存rate
		for (Rateplan rateplan : refRpList) {
			String refuuidSign = UUID.randomUUID().toString().replace("-", "");
			Object obj = CommonUtil.objectCopy(rateDetailVOList);
			List<RateDetailVO> refRateDetailVOList = obj != null? (List<RateDetailVO>)obj : null;
			List<RateDetailVO> refRdList = getRateDetailVOList(rateplan, refRateDetailVOList);
				rateDetailVODaoMongo.batchSaveMongo(refRdList,vo.getChainCode(), vo.getHotelCode(),rateplan.getRatePlanCode(),refuuidSign,RateDetailVO.RATETYPE_CCM);
				oldRateDetailVODaoMongo.batchSaveMongo(oldRefRdList, vo.getChainCode(), vo.getHotelCode(),rateplan.getRatePlanCode(),refuuidSign,RateDetailVO.RATETYPE_CCM);
		
				rateplan.setHotelCode(vo.getHotelCode());
				rateplan.setIsPmsUpload(false);
				callAddPriceCalcManager.startAddPmsPriceCalc(refuuidSign,rateplan);
		}
		
		return true;
	}
	
	public void addRateplan(Rateplan rateplan) {
		rateplanDao.addRateplan(rateplan);
	}
	
	@Override
	public RatePlanVO addRateplanVO(RatePlanVO vo,String language) {
		boolean isNewRateplan = false;
		//判断传入的对象是否包含rateplan数据模型
		if (CommonUtil.isNotEmpty(vo.getRp()) && CommonUtil.isEmpty(vo.getRp().getRatePlanId())) {
			isNewRateplan = true;
		}
		// 如果当前用户已登陆
		HotelVO hv = null;
		if (SecurityHolder.getB2BUser() != null) {
			hv = SecurityHolder.getB2BUser().getHotelvo();
		} else {
			hv = hotelDao.getHotelChainByHotelId(vo.getRp().getHotelId());
		}
		vo.setHotelCode(hv.getHotelCode());
		vo.setChainCode(hv.getChainCode());
		//已保存的rateplanId
		String savedRatePlanId = "";
		if (vo.getRp() != null) {
			vo.getRp().setHotelId(hv.getHotelId());
			Rateplan oldRp = CommonUtil.isEmpty(vo.getRp().getRatePlanId()) ? new Rateplan() : rateplanDao.get(vo.getRp().getRatePlanId());
			Rateplan rp = rateplanDao.addRateplan(vo.getRp());
			vo.setRp(rp);
			vo.getRpi18n().setRatePlanId(rp.getRatePlanId());
			vo.setRatePlanId(vo.getRp().getRatePlanId());
			savedRatePlanId = rp.getRatePlanId();

			String newInheritRatePlanId = rp.getInheritRatePlanId();

			String oldInheritRatePlanId = oldRp.getInheritRatePlanId();
			if (!isNewRateplan) {
				newInheritRatePlanId = newInheritRatePlanId == null ? "" : newInheritRatePlanId;
				oldInheritRatePlanId = oldInheritRatePlanId == null ? "" : oldInheritRatePlanId;
				if (!compareObject(rp.getPercent(), oldRp.getPercent()) || !compareObject(rp.getAmount(), oldRp.getAmount()) || !compareObject(newInheritRatePlanId, oldInheritRatePlanId)) {

					if (!compareObject(newInheritRatePlanId, oldInheritRatePlanId) && CommonUtil.isEmpty(newInheritRatePlanId)) {
						// 根据rp.getRatePlanId()删除当前detail
						List<RateDetailVO> rdList = rateDetailManager.getRateDetailVOByRatePlanId(rp.getRatePlanId());
						for (RateDetailVO rateDetailVO : rdList) {
							rateDetailManager.delRateDetail(rateDetailVO.getRateDetailId());
						}

						// 根据oldRp 找到detail ，添加到当前detail
						List<RateDetailVO> oldRdList = getRateDetailVOList(oldRp);

						for (RateDetailVO rateDetailVO : oldRdList) {
							rateDetailVO.setRateDetailId(null);
							List<RoomRateVO> roomRateList = rateDetailVO.getRoomRateList();
							for (RoomRateVO roomRateVO : roomRateList) {
								roomRateVO.setRoomRateId(null);
							}
							List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
							for (RateAmount rateAmount : rateAmountList) {
								rateAmount.setRateAmountId(null);
							}
						}
						// 添加detail、PriceCalc
						List<RateDetailVO> rateDetailVOList = this.addRateDetailVOList(oldRdList, vo);
						try {
							RatePlanVO rpv = new RatePlanVO();
							rpv.setRp(rp);
							rpv.setRateDetailVOList(rateDetailVOList);
//							保存旧的detail到mongodb
							rateDetailVODaoMongo.batchSaveMongo(rateDetailVOList, hv.getChainCode(), hv.getHotelCode(), rp.getRatePlanCode(),null,RateDetailVO.RATETYPE_CCM);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (CommonUtil.isNotEmpty(newInheritRatePlanId)) {
						String uuidSign = UUID.randomUUID().toString().replace("-", "");
						List<RateDetailVO> oldRdList = getRateDetailVOList(oldRp);// 获取原来的detail
						oldRateDetailVODaoMongo.batchSaveMongo(oldRdList, vo.getChainCode(), vo.getHotelCode(),rp.getRatePlanCode(),uuidSign,RateDetailVO.RATETYPE_CCM_SUB_RATE  );
						try {
							RatePlanVO rpv = new RatePlanVO();
							rp.setInheritRatePlanId(newInheritRatePlanId);// 设置成父detail
							rpv.setRp(rp);
							rpv.setRateDetailVOList(getRateDetailVOList(rp));
							// priceCalcManager.addPriceCalc(rpv,false);
							rateDetailVODaoMongo.batchSaveMongo(rpv.getRateDetailVOList(), hv.getChainCode(), hv.getHotelCode(), rp.getRatePlanCode(),uuidSign,RateDetailVO.RATETYPE_CCM_SUB_RATE);
							rp.setHotelCode(hv.getHotelCode());
							rp.setIsPmsUpload(false);
							callAddPriceCalcManager.startAddPmsPriceCalc(uuidSign,rp);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			// 获取多语言列表
			List<RatePlanI18n> i18nList = vo.getRatePlanI18nList();
			// 添加默认语言
			RatePlanI18n i18n = vo.getRpi18n();
			i18n.setRatePlanMId(null);
			i18n.setLanguage(language);
			i18nList.add(i18n);

			// 多语言维护,删除对应的所有多语言记录
			rateplanDao.deleteRatePlanI18nByRatePlanId(savedRatePlanId);
			// 循环保存多语言记录
			for (RatePlanI18n ratePlanI18n : i18nList) {
				ratePlanI18n.setRatePlanId(savedRatePlanId);
				ratePlanI18n.setCreatedBy(SecurityHolder.getUserId());
				ratePlanI18n.setCreatedTime(new Date());
				rateplanDao.addRateplani18n(ratePlanI18n);
			}

			ratePackageDao.deleteRatePackageByRatePlanId(vo.getRp().getRatePlanId());
			ratePackageDao.saveRatePackageList(vo.getRatePackageList());

			List<RateGuaranteeRelationship> guarrnteeList = vo.getGuarrnteeList();
			HashMap<String, String> dataGuanteeMap = new HashMap<String, String>();
			for (RateGuaranteeRelationship rateGuaranteeRelationship : guarrnteeList) {
				rateGuaranteeRelationship.setRatePlanId(savedRatePlanId);
				rateGuaranteeRelationshipDao.save(rateGuaranteeRelationship);
				dataGuanteeMap.put(rateGuaranteeRelationship.getRateGuaranteeRelationshipId(), null);
			}
			// 数据库所有的担保规则
			guarrnteeList = rateGuaranteeRelationshipDao.getRateGuaranteeRelationshipByRatePlanId(savedRatePlanId);
			for (RateGuaranteeRelationship rateGuaranteeRelationship : guarrnteeList) {
				String rateGuaranteeId = rateGuaranteeRelationship.getRateGuaranteeRelationshipId();
				if (!dataGuanteeMap.containsKey(rateGuaranteeId)) {// 不存在则删除
					rateGuaranteeRelationshipDao.remove(rateGuaranteeId);
				}
			}
			HashMap<String, String> cancelRuleMap = new HashMap<String, String>();
			List<RateCancelRelationship> cancelRuleList = vo.getCancelRuleList();
			for (RateCancelRelationship rateCancelRelationship : cancelRuleList) {
				rateCancelRelationship.setRatePlanId(savedRatePlanId);
				rateCancelRelationshipDao.save(rateCancelRelationship);
				cancelRuleMap.put(rateCancelRelationship.getRateCancelRelationshipId(), null);
			}
			// 数据库所有的取消规则
			cancelRuleList = rateCancelRelationshipDao.getRateCancelRelationshipByRatePlanId(savedRatePlanId);
			for (RateCancelRelationship rateCancelRelationship : cancelRuleList) {
				String rateCancelId = rateCancelRelationship.getRateCancelRelationshipId();
				if (!cancelRuleMap.containsKey(rateCancelId)) {
					rateCancelRelationshipDao.remove(rateCancelId);
				}
			}
			// ACCESS CODE
			rateCustomRelationshipDao.deleteRateCustomRelationshipByRatePlanId(savedRatePlanId);
			if (vo.getCustomList() != null) {
				for (String customId : vo.getCustomList()) {
					RateCustomRelationship rcr = new RateCustomRelationship();
					rcr.setRatePlanId(savedRatePlanId);
					rcr.setCustomId(customId);
					rateCustomRelationshipDao.save(rcr);
				}
			}

		} else {
			//将rateplanId赋值
			savedRatePlanId = vo.getRatePlanId();
		}

		// 第2步 start to handle the rateDetail data of the new record
		List<RateDetailVO> rateDetailVOList = vo.getRateDetailVOList();
		if(CommonUtil.isNotEmpty(rateDetailVOList)){
			vo.setRateDetailVOList(this.addRateDetailVOList(rateDetailVOList, vo));
		}
		return vo;
	}
	
	
		//添加价格明细
	public List<RateDetailVO> addRateDetailVOList(List<RateDetailVO> rateDetailVOList, RatePlanVO vo) {
		List<RateDetailVO> rateDetailSavedList = new ArrayList<RateDetailVO>();
		//找到其先相关的rateplan数据
		Rateplan rp = vo.getRp() == null ? rateplanDao.get(vo.getRatePlanId()) : vo.getRp();
		//生成uuid签名
		String uuidSign = UUID.randomUUID().toString().replace("-", "");
		//生成引用uuid签名
		String refuuidSign = UUID.randomUUID().toString().replace("-", "");
		//通过rateplanId等于inheritRatePlanId获取子房价数据,获得子房价
		List<Rateplan> refRpList = getRefRateplan(rp.getHotelId(), rp.getRatePlanId());
		//原子房价明细数据集合(childrenlist)
		List<RateDetailVO> oldRefRdList = new ArrayList<RateDetailVO>();
		//原房价明细数据集合
		List<RateDetailVO> oldRdList = new ArrayList<RateDetailVO>();
		//循环新的房价明细数据
		for (Iterator<RateDetailVO> iterator = rateDetailVOList.iterator(); iterator.hasNext();) {
			RateDetailVO rateDetailVO = iterator.next();
			if (rateDetailVO == null) {
				continue;
			}
			//设置新房价明细数据的id
			rateDetailVO.setUuidSign(uuidSign);
			//获取原数据房价明细id,判断是修改还是新建
			String oldRateDetailId = CommonUtil.isNotEmpty(rateDetailVO.getRateDetailId()) ? rateDetailVO.getRateDetailId(): "newRateDetailId";
			//设置rateplan的detailId
			rp.setRateDetailId(oldRateDetailId);
			//获取原始房价明细数据
			List<RateDetailVO> rdvoList = getRateDetailVOList(rp);
			//并全部添加到oldRdList
			oldRdList.addAll(rdvoList);
			//循环子房价数据
			for (Rateplan refRp : refRpList) {
				//设置房价明细id
				refRp.setRateDetailId(oldRateDetailId);
				//获取子房价的房价明细数据
				List<RateDetailVO> refRdVoList = getRateDetailVOList(refRp);
				//将字房价明细添加到oldRefRdList(childrenlist)
				oldRefRdList.addAll(refRdVoList);
			}
			//设置rateplanId为rateDetailVO
			rateDetailVO.setRatePlanId(vo.getRatePlanId());
			//保存rateDetail后,获取rateDetail数据模型
			RateDetail rateDetail = rateDetailDao.addRateDetail(rateDetailVO);
			//将保存后的rateDetailVo放入rateDetailSavedList集合中
			rateDetailSavedList.add(rateDetailVO);
			//获取保存的rateDetailId
			String rateDetailId = rateDetail.getRateDetailId();
			
			HashMap<String, String> roomRateMap = new HashMap<String, String>();
			List<RoomRateVO> roomRateList = rateDetailVO.getRoomRateList();
			//循环保存新纪录中的roomrate,并将roomrateId记录放到roomRateMap
			for (RoomRateVO roomRateVO : roomRateList) {
				roomRateVO.setRateDetailId(rateDetailId);
				roomRateDao.addRoomRateVO(roomRateVO);
				roomRateMap.put(roomRateVO.getRoomRateId(), null);
			}
			//从数据库获取与ratedetail有关的roomRate,进行数据准确性校验
			roomRateList = roomRateDao.getRoomRateVOByRateDetailId(rateDetailId);
			for (RoomRateVO roomRateVO : roomRateList) {
				if (!roomRateMap.containsKey(roomRateVO.getRoomRateId())) {// 不存在则删除
					roomRateDao.remove(roomRateVO.getRoomRateId());
				}
			}
			//设置房价总额的rateDetailId后保存，并将房价总额的id放到rateAmountMap
			HashMap<String, String> rateAmountMap = new HashMap<String, String>();
			List<RateAmount> rateAmountList = rateDetailVO.getRateAmountList();
			for (RateAmount rateAmount : rateAmountList) {
				rateAmount.setRateDetailId(rateDetailId);
				rateAmountDao.save(rateAmount);
				rateAmountMap.put(rateAmount.getRateAmountId(), null);
			}
			//获取房价金额信息，并保存
			rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailId);
			for (RateAmount rateAmount : rateAmountList) {
				if (!rateAmountMap.containsKey(rateAmount.getRateAmountId())) {// 不存在则删除
					rateAmountDao.remove(rateAmount.getRateAmountId());
				}
			}
			//不需要存储
			if("noSaveMongo".equals(rateDetailVO.getRateType())){
				iterator.remove();
			}
		}
		//判断ratedetailtype是否分割类型(不是),保存到mongo
		if(!"splitRateDetail".equals(vo.getHandleRateDetailType())){
			oldRateDetailVODaoMongo.batchSaveMongo(oldRdList, vo.getChainCode(), vo.getHotelCode(),rp.getRatePlanCode(),uuidSign,RateDetailVO.RATETYPE_CCM  );
		}
		//循环子rateplan,并保存rate
		for (Rateplan rateplan : refRpList) {
			Object obj = CommonUtil.objectCopy(rateDetailVOList);
			List<RateDetailVO> refRateDetailVOList = obj != null? (List<RateDetailVO>)obj : null;
			List<RateDetailVO> refRdList = getRateDetailVOList(rateplan, refRateDetailVOList);
			
			rateDetailVODaoMongo.batchSaveMongo(refRdList,vo.getChainCode(), vo.getHotelCode(),rateplan.getRatePlanCode(),refuuidSign,RateDetailVO.RATETYPE_CCM );
			if(!"splitRateDetail".equals(vo.getHandleRateDetailType())){
				oldRateDetailVODaoMongo.batchSaveMongo(oldRefRdList, vo.getChainCode(), vo.getHotelCode(),rateplan.getRatePlanCode(),refuuidSign,RateDetailVO.RATETYPE_CCM);
			}
		}
		
		rateDetailVODaoMongo.batchSaveMongo(rateDetailVOList,vo.getChainCode(), vo.getHotelCode(),rp.getRatePlanCode(),uuidSign,RateDetailVO.RATETYPE_CCM );
		return rateDetailSavedList;
	}
	   
	@Override
	public void updateRateplan(RatePlanVO vo) {

	}

	@Override
	public Rateplan getRateplanByRateplanCode(String rateplanCode, String hotelCode) {
		return rateplanDao.getRateplanByRateplanCode(rateplanCode, hotelCode);
	}

	public RatePlanI18n getRatePlanI18nById(String ratePlanId) {
		return rateplanDao.getRatePlanI18nById(ratePlanId);
	}
	
	public RatePlanI18n getRatePlanI18nById(String ratePlanId,String language) {
		return rateplanDao.getRatePlanI18nById(ratePlanId, language);
	}

	@Override
	public List<Rateplan> getRatePlanByHotelId(String hotelId) {
		return rateplanDao.getRatePlanByHotelId(hotelId);
	}
	/*obtain basic info of rateplan */
	@Override
	public List<HashMap<String, String>> getRateNameByHotelId(String hotelId,String language) {
		List<HashMap<String, String>> list = rateplanDao.getRateNameByHotelId(hotelId,language);
		List<HashMap<String, String>> listDisable = new ArrayList<HashMap<String, String>>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			HashMap<String, String> hashMap = (HashMap<String, String>) iterator.next();
			String expireDate = hashMap.get("expireDate");
			if (CommonUtil.isNotEmpty(expireDate)) {
				try {
					Date exdate = DateUtil.convertStringToDate(expireDate);
					if (exdate.before(DateUtil.currentDate())) {
						hashMap.put("disable", "true");
						listDisable.add(hashMap);
						iterator.remove();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		list.addAll(listDisable);
		return list;
	}

	@Override
	public RatePlanVO getRatePlanVO(String ratePlanId, boolean isNeedHead,String language) {
		RatePlanVO vo = new RatePlanVO();
		Rateplan rp = get(ratePlanId);
		if (isNeedHead) {
			vo.setRp(rp);
			vo.setRatePlanCode(rp.getRatePlanCode());
			DictCode dc = dictCodeManager.searchByCodeNo(DictCode.DICT_RPT, rp.getRatePlanType() + "",language);
			if (CommonUtil.isNotEmpty(dc)) {
				vo.setRatePlanType(dc.getCodeLabel());
			}

			vo.setRpi18n(rateplanDao.getRatePlanI18nById(ratePlanId,language));
			vo.setRatePlanName(vo.getRpi18n().getRatePlanName());
			// 获取多语言列表
			vo.setRatePlanI18nList(rateplanDao.getRatePlanI18ns(ratePlanId));

			MarketVO marketVo = marketDao.getMarketByCode(rp.getMarketCode(),language);
			if (marketVo != null) {
				vo.setMarketDescription(marketVo.getDescription());
			}
			SourceVO svo = sourceDao.getSourceByCode(rp.getSourceCode(),language);
			if (svo != null) {
				vo.setSourceDescription(svo.getDescription());
			}
			vo.setRatePackageList(ratePackageDao.getRatePackageByRatePlanId(ratePlanId,language));
			List<RateGuaranteeRelationship> guarrnteeList = rateGuaranteeRelationshipDao.getRateGuaranteeRelationshipByRatePlanId(ratePlanId);
			vo.setGuarrnteeList(guarrnteeList);
			String guaranteeJsonArr = JSON.toJSONStringWithDateFormat(guarrnteeList, DateUtils.YYYYMMDD);
			vo.setGuaranteeJsonArr(guaranteeJsonArr);
			vo.setCancelRuleList(rateCancelRelationshipDao.getRateCancelRelationshipByRatePlanId(ratePlanId));
		}

		List<RateDetailVO> rateDetailVOList = getRateDetailVOList(rp,language);
		/*set maxDetailExpireDate to today*/
		vo.setMaxDetailExpireDate(new Date());
		for (int i = 0; i < rateDetailVOList.size(); i++) {
			RateDetailVO rv = rateDetailVOList.get(i);
			/*compare expire date*/
			if (vo.getMaxDetailExpireDate().before(rv.getExpireDate())) {
				vo.setMaxDetailExpireDate(rv.getExpireDate());
			}
		}
		vo.setRateDetailVOList(rateDetailVOList);
		return vo;
	}

	/** 获取房价明细 */
	@Override
	public List<RateDetailVO> getRateDetailVOList(Rateplan rp) {
		String rpId = null;
		if (CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())) {
			rpId = rp.getInheritRatePlanId();
		} else {
			rpId = rp.getRatePlanId();
		}
		List<RateDetailVO> rateDetailVOList = rateDetailDao.getRateDetailVOByRatePlanId(rpId,rp.getRateDetailId());
		return this.getRateDetailVOList(rp, rateDetailVOList);
	}
	
	@Override
	public List<RateDetailVO> getRateDetailVOList(Rateplan rp,String language) {
		String rpId = null;
		if (CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())) {
			rpId = rp.getInheritRatePlanId();
		} else {
			rpId = rp.getRatePlanId();
		}
		List<RateDetailVO> rateDetailVOList = rateDetailDao.getRateDetailVOByRatePlanId(rpId,rp.getRateDetailId());
		return this.getRateDetailVOList(rp, rateDetailVOList,language);
	}

	/** 获取房价明细 ,根据已有的明细计算子房价 */
	@Override
	public List<RateDetailVO> getRateDetailVOList(Rateplan rp, List<RateDetailVO> rateDetailVOList) {
		if (CommonUtil.isEmpty(rateDetailVOList)) {
			// rateDetailVOList =
			// rateDetailDao.getRateDetailVOByRatePlanId(rpId);
			return rateDetailVOList;
		}

		for (RateDetailVO rateDetailVO : rateDetailVOList) {
			// 房型
			List<RoomRateVO> roomRateList = null;
			if(CommonUtil.isNotEmpty(rateDetailVO.getRateDetailId())){
				roomRateList = roomRateDao.getRoomRateVOByRateDetailId(rateDetailVO.getRateDetailId());
			}else{
				roomRateList = rateDetailVO.getRoomRateList();
			}
			
			for (RoomRateVO roomRateVO : roomRateList) {
				// 房型打包服务
				roomRateVO.setRoomPackageList(roomPackageDao.getRoomPackageByRoomRateId(roomRateVO.getRoomRateId()));
			}
			rateDetailVO.setRoomRateList(roomRateList);
			List<RateAmount> rateAmountList = null;
			// 房价
			if (CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())) {
				if(CommonUtil.isNotEmpty(rateDetailVO.getRateDetailId())){
					rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailVO.getRateDetailId(), rp);
				}else{
					for (RateAmount rateAmount : rateDetailVO.getRateAmountList()) {
			            BigDecimal baseAmount=null;
			            if(CommonUtil.isNotEmpty(rp.getPercent())){
			                baseAmount = rateAmount.getBaseAmount().multiply(rp.getPercent()).divide(new BigDecimal(100));
			            }else if(CommonUtil.isNotEmpty(rp.getAmount())){
			                baseAmount = rateAmount.getBaseAmount().add(rp.getAmount());
			            }else{
			                baseAmount = rateAmount.getBaseAmount();
			            }
			            if(baseAmount != null){
//			            	baseAmount = baseAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
			            	baseAmount = baseAmount.add(new BigDecimal(0.000001));
			            	DecimalFormat df = new DecimalFormat("#.##"); 
			            	baseAmount = new BigDecimal(df.format(baseAmount));
			            }
			            rateAmount.setBaseAmount(baseAmount);
			        }
					rateAmountList = rateDetailVO.getRateAmountList();
				}
				
			} else {
				if(CommonUtil.isNotEmpty(rateDetailVO.getRateDetailId())){
					rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailVO.getRateDetailId());
				}else{
					rateAmountList = rateDetailVO.getRateAmountList();
				}
			}
			rateDetailVO.setRateAmountList(rateAmountList);
		}
		return rateDetailVOList;
	}
// check old info from roomrate
	@Override
	public List<RateDetailVO> getRateDetailVOList(Rateplan rp, List<RateDetailVO> rateDetailVOList,String language) {
		if (CommonUtil.isEmpty(rateDetailVOList)) {
			return rateDetailVOList;
		}

		for (RateDetailVO rateDetailVO : rateDetailVOList) {
			// 房型
			List<RoomRateVO> roomRateList = roomRateDao.getRoomRateVOByRateDetailId(rateDetailVO.getRateDetailId(),language);
			List<RoomRateVO> newList = new ArrayList<RoomRateVO>();
			if(!language.equalsIgnoreCase(LanguageCode.MAIN_LANGUAGE_CODE)){
				for(RoomRateVO vo:roomRateList){
					if(vo.getRoomTypeName()!=null){
						newList.add(vo);
					}else{
						RoomRateVO r = roomRateDao.getRoomRateVOByRateDetailIdAndRoomTYpeCode(rateDetailVO.getRateDetailId(), vo.getRoomTypeCode(), LanguageCode.MAIN_LANGUAGE_CODE);
						newList.add(r);
					}
				}
			}else{
				newList = roomRateList;
			}
//			for (RoomRateVO roomRateVO : roomRateList) {
//				// 房型打包服务
//				roomRateVO.setRoomPackageList(roomPackageDao.getRoomPackageByRoomRateId(roomRateVO.getRoomRateId()));
//			}
//			rateDetailVO.setRoomRateList(roomRateList);
//			List<RateAmount> rateAmountList = null;
//			// 房价
//			if (CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())) {
//				rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailVO.getRateDetailId(), rp);
//			} else {
//				rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailVO.getRateDetailId());
//			}
//			rateDetailVO.setRateAmountList(rateAmountList);
			rateDetailVO = RateDetailVOSetRateAmountList(rateDetailVO, newList, rp);
		}
		return rateDetailVOList;
	}
	
	private RateDetailVO RateDetailVOSetRateAmountList(RateDetailVO vo,List<RoomRateVO> roomRateList,Rateplan rp){
		for (RoomRateVO roomRateVO : roomRateList) {
			// 房型打包服务
			roomRateVO.setRoomPackageList(roomPackageDao.getRoomPackageByRoomRateId(roomRateVO.getRoomRateId()));
		}
		vo.setRoomRateList(roomRateList);
		List<RateAmount> rateAmountList = null;
		// 房价
		if (CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())) {
			rateAmountList = rateAmountDao.getRateAmountByRateDetailId(vo.getRateDetailId(), rp);
		} else {
			rateAmountList = rateAmountDao.getRateAmountByRateDetailId(vo.getRateDetailId());
		}
		vo.setRateAmountList(rateAmountList);
		return vo;
	}
	
	@Override
	public boolean validateRateDetail(RatePlanVO ratePlanVO,String language) {
		RatePlanVO ratePlanVO2 = getRatePlanVO(ratePlanVO.getRatePlanId(), false,language);
		List<RateDetailVO> list = ratePlanVO2.getRateDetailVOList();
		//new data list
		List<RateDetailVO> validatelist = ratePlanVO.getRateDetailVOList();
		if (CommonUtil.isNotEmpty(validatelist)) {
			for (RateDetailVO rateDetailVO : list) {
				RateDetailVO validateRd = validatelist.get(0);
				//if the ratedetails is the same as existed data , to continue
				if (rateDetailVO.getRateDetailId().equals(validateRd.getRateDetailId())) {
					continue;
				}

				List<RoomRateVO> roomRatelist = rateDetailVO.getRoomRateList();
				List<RoomRateVO> roomRatelistVali = validateRd.getRoomRateList();
				boolean isFindRoomType = false;
				for (RoomRateVO roomRateVO : roomRatelist) {
					for (RoomRateVO roomRateVOVali : roomRatelistVali) {
						log.info(roomRateVO.getRoomTypeId() + "   " + roomRateVOVali.getRoomTypeId());
						if (roomRateVO.getRoomTypeId() != null) {
							if (roomRateVO.getRoomTypeId().equals(roomRateVOVali.getRoomTypeId())) {
								isFindRoomType = true;
								break;
							}
						}
					}
				}
				if (isFindRoomType) {
					//如果找到数据库中相同房型的房价数据
					if (DateUtil.judge(rateDetailVO.getEffectiveDate(), rateDetailVO.getExpireDate(), validateRd.getEffectiveDate()) || DateUtil.judge(rateDetailVO.getEffectiveDate(), rateDetailVO.getExpireDate(), validateRd.getExpireDate()) || (validateRd.getEffectiveDate().before(rateDetailVO.getEffectiveDate())) && validateRd.getExpireDate().after(rateDetailVO.getExpireDate())) {
						int[] weeks = priceCalcManager.getWeek(rateDetailVO);
						int[] weeksValidate = priceCalcManager.getWeek(validateRd);
						for (int w : weeks) {
							for (int wv : weeksValidate) {
								if (w == wv) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public List<HashMap<String, Object>> getNoInheritRateNameByHotelId(String hotelId,String language) {
		return rateplanDao.getNoInheritRateNameByHotelId(hotelId,language);
	}

	public void updateRefRateplan(RatePlanVO ratePlanVO) {
		Rateplan rp = ratePlanVO.getRp();
		List<Rateplan> rateplanList = getRefRateplan(rp.getHotelId(), ratePlanVO.getRatePlanId());
		Hotel h = hotelDao.getHotel(rp.getHotelId());
		for (Rateplan rateplan : rateplanList) {
			// 删除该价格定义 PriceCalc
			// priceCalcManager.deletePriceCalcByRateRateplan(rateplan);
			priceCalcManager.deletePriceCalcByDetailVOList(ratePlanVO.getRateDetailVOList(), rateplan.getRatePlanCode(), h.getHotelCode());
			// 保存现在的PriceCalc
			try {
				RatePlanVO rpv = new RatePlanVO();
				rateplan.setInheritRatePlanId(rp.getRatePlanId());// 设置成父detail
				rpv.setRp(rateplan);
				rpv.setRateDetailVOList(getRateDetailVOList(rateplan));
				// priceCalcManager.addPriceCalc(rpv,true);

				rateDetailVODaoMongo.batchSaveMongo(rpv.getRateDetailVOList(), ratePlanVO.getChainCode(), ratePlanVO.getHotelCode(), rateplan.getRatePlanCode(),null,RateDetailVO.RATETYPE_CCM);
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, new String[] { "mc", "ccm" }));
				e.printStackTrace();
			}
			log.info("******update ratePlanCode:" + rateplan.getRatePlanCode());
		}
	}

	@Override
	public List<Rateplan> getRefRateplan(String hotelId, String ratePlanId) {
		List<Rateplan> rateplanList = rateplanDao.getRefRateplan(hotelId, ratePlanId);
		if (CommonUtil.isEmpty(rateplanList)) {
			rateplanList = new ArrayList<Rateplan>();
		}
		return rateplanList;
	}

	public RatePlanVO getRatePlanI18nByCodeHotelId(String rateplanCode, String hotelId) {
		return rateplanDao.getRatePlanI18nByCodeHotelId(rateplanCode, hotelId);
	}

	public RatePlanVO getRatePlanI18nByCodeHotelId(String rateplanCode, String hotelId, String language) {
		return rateplanDao.getRatePlanI18nByCodeHotelId(rateplanCode, hotelId, language);
	}

	public List<CancelPolicyVO> getRateCancelByIdOfTB(String ratePlanId) {
		return rateplanDao.getRateCancelByIdOfTB(ratePlanId);
	}

	/**
	 * 获取有效渠道绑定状态
	 * @param rp
	 * @return
	 */
	@Override
	public HashMap<String,ArrayList<ChannelGoodsVO>> getValidChannel(Rateplan rp){
		HashMap<String,ArrayList<ChannelGoodsVO>> map = new HashMap<String, ArrayList<ChannelGoodsVO>>();
		ChannelGoodsVO cgvo = new ChannelGoodsVO();
        cgvo.setHotelId(rp.getHotelId());
        cgvo.setRatePlanCode(rp.getRatePlanCode());
        cgvo.setIsBind(true);
        List<ChannelGoodsVO> crpList = channelgoodsManager.getEnabledChannelGoods2(cgvo);//所有绑定状态
        if (CommonUtil.isEmpty(crpList)) {
            crpList = new ArrayList<ChannelGoodsVO>();
            log.debug("该房价没有对应的渠道");
            return map;
        }
        
        for (ChannelGoodsVO cv : crpList) { // 渠道
        	if(ChannelGoodsStatus.publish.equals(cv.getStatus())){
        		String key = rp.getHotelCode()+cv.getRatePlanCode()+cv.getRoomTypeCode();
            	if(map.containsKey(key)){
            		map.get(key).add(cv);
            	}else{
            		ArrayList<ChannelGoodsVO> valList = new ArrayList<ChannelGoodsVO>();
            		valList.add(cv);
                	map.put(key, valList);
            	}
        	}
        }
        return map;
	}
	@Override
	public List<RatePlanI18n> getRatePlanI18ns(String ratePlanId) {
		return rateplanDao.getRatePlanI18ns(ratePlanId);
	}

	@Override
	public void deleteRatePlanI18nByRatePlanId(String ratePlanId) {
		rateplanDao.deleteRatePlanI18nByRatePlanId(ratePlanId);
	}

	public List<HashMap<String, String>> getValidRatePlanByHotelIdLang(String hotelId, String language) {
		return rateplanDao.getValidRatePlanByHotelIdLang(hotelId, language);
	}

	@Override
	public void updateRateSoldableCondition(String ratePlanId, String soldableCondition) {
		rateplanDao.updateRateSoldableCondition(ratePlanId, soldableCondition);
	}

	@Override
	public void updateAccessCode(Rateplan r) {
		rateplanDao.updateAccessCode(r);
	}

	@Override
	public List<Rateplan> getRatePlanByHotelIdList(List<String> hotelIdList) {
		
		return rateplanDao.getRatePlanByHotelIdList(hotelIdList);
	}

}
