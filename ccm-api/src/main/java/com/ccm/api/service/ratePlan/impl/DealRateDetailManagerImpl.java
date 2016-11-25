package com.ccm.api.service.ratePlan.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.ratePlan.mongodb.OldRateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.vo.OldRateDetailVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVOCriteria;
import com.ccm.api.model.ratePlan.vo.RateDetailVOResult;
import com.ccm.api.model.ratePlan.vo.RoomMsgCriteria;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.ratePlan.DealRateDetailManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.service.rsvtype.RsvtypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
@Service("dealRateDetailManager")
public class DealRateDetailManagerImpl implements
		DealRateDetailManager {
	protected final Log log = LogFactory.getLog(getClass());
	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private RateDetailVODaoMongo rateDetailVODaoMongo;
	@Resource
	private PriceCalcManager priceCalcManager;
	@Resource
	private OldRateDetailVODaoMongo oldRateDetailVODaoMongo;
	@Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	@Resource
	private RatePlanManager ratePlanManager;
	@Resource
	private ChannelManager channelManager;
	@Resource
	private RsvtypeManager rsvtypeManager;
	@Resource
	private RoomJmsManager roomJmsManager;
	@Resource
	private RateAmountManager rateAmountManager;
	
	@Override
	public void dealRateDetail(String customerId) {
		HashMap<String,Rateplan> ratePlanMap = new HashMap<String,Rateplan>();
		while(true){
			log.info("startAddPriceCalc ... "+customerId);
			long startMili = System.currentTimeMillis();
			RateDetailVOCriteria criteria = new RateDetailVOCriteria();
			criteria.setSortBy("createdTime");
			criteria.setDesc(RoomMsgCriteria.ASC);
			criteria.setPageSize(500);
			criteria.setRateType(RateDetailVO.RATETYPE_CCM);
			criteria.setStatus(RateDetailVO.MONGO_STATUS_INIT);
			criteria.setCustomerId(customerId);
			RateDetailVOResult result = rateDetailVODaoMongo.searchRateDetailVO(criteria);
			List<RateDetailVO> rateDetailVOList = result.getResultList();
			
			if(CommonUtil.isEmpty(rateDetailVOList)){
				log.info("rateDetailVOList isEmpty");
				break;
			}
			log.info("add priceCalc run!!! rateDetailVOList.size: "+rateDetailVOList.size());
			for (RateDetailVO rateDetailVO : rateDetailVOList) {
				try {
					String hotelCode = rateDetailVO.getHotelCode();
					String rateCode = rateDetailVO.getRatePlanCode();
					Rateplan rp = ratePlanMap.get(hotelCode+rateCode);
					if(null == rp){
						rp = rateplanDao.getRateplanByRateplanCode(rateCode, hotelCode);
						ratePlanMap.put(hotelCode+rateCode, rp);
					}
					if(rp == null){
						rateDetailVO.setErrMsg(hotelCode+"_"+rateCode+" rp is null");
						rateDetailVO.setStatus(RateDetailVO.MONGO_STATUS_ERR);
						rateDetailVODaoMongo.updateById(rateDetailVO);
						continue;
					}
					rp.setHotelCode(hotelCode);
					compareRateDetailVO(rateDetailVO, rp);
				} catch (Exception e) {
					log.error("dealRateDetailVOError"+e.getMessage());
					e.printStackTrace();
					rateDetailVO.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
					rateDetailVO.setStatus(RateDetailVO.MONGO_STATUS_ERR);
					rateDetailVODaoMongo.updateById(rateDetailVO);
				}
			}
		log.info(" dealRateDetailVO 耗时为："+ (System.currentTimeMillis() - startMili)/1000.0 + "秒  rateDetailVOList.size:"+rateDetailVOList.size());
		}
	}

	@Override
	public void compareRateDetailVO(RateDetailVO rateDetailVO, Rateplan rp) {
		RatePlanVO rpvo = new RatePlanVO();
		rpvo.setRp(rp);
		List<RateDetailVO> newPricelist = new ArrayList<RateDetailVO>();
		newPricelist.add(rateDetailVO);
		String err = null;
		String uuidSign = rateDetailVO.getUuidSign();
		try {
			//获取有效渠道
			HashMap<String,ArrayList<ChannelGoodsVO>> channelRateRoomMap =  ratePlanManager.getValidChannel(rp);
			//获取旧的PriceCalc
            HashMap<String,Object> oldPriceCalcMap = getOldPriceCalcByOldDetail(uuidSign,rpvo); 
            //需要推送的房价
            List<RoomMsg> rmList =  this.getNewPriceAndClosePrice(oldPriceCalcMap, newPricelist, channelRateRoomMap, rpvo);
			
			roomMsgDaoMongo.batchSave(rmList);
		} catch (Exception e) {
			err = CommonUtil.getExceptionMsg(e, new String[]{"ccm","mc"});
			rateDetailVO.setStatus(RateDetailVO.MONGO_STATUS_ERR);
			rateDetailVO.setErrMsg(err);
			e.printStackTrace();
		}
		if(err != null){
			rateDetailVODaoMongo.updateById(rateDetailVO);
		}else{
			rateDetailVODaoMongo.deleteRateDetailVO(rateDetailVO);
			oldRateDetailVODaoMongo.remove(Query.query(Criteria.where("uuidSign").is(uuidSign)));
		}
	}
	/**
	 * 从rateDetail取出房型价格日期set
	 * pirceColseMap: roomTypeCode+ratePlanCode+date
	 * priceSet:roomTypeCode+ratePlanCode+date+numberOfUnits+amount
	 */
	@Override
	public HashMap<String,Object> getOldPriceCalcByOldDetail(String uuidSign,RatePlanVO rpvo) throws Exception{
		HashMap<String,Object> priceMap = new HashMap<String, Object>();
		List<OldRateDetailVO> oldRdList = oldRateDetailVODaoMongo.find(Query.query(Criteria.where("uuidSign").is(uuidSign)));
		HashSet<String> priceSet = new HashSet<String>();
		List<RateDetailVO> rdList = new ArrayList<RateDetailVO>();
		//获取原来的detail
		rdList.addAll(oldRdList);
		//获取PriceCalc
		List<PriceCalc> pcList = priceCalcManager.getPriceCalcListByDetail(rdList, rpvo);
		HashMap<String,PriceCalc> map = new HashMap<String,PriceCalc>();
		for (PriceCalc priceCalc : pcList) {
			//Map里房价信息key：房型code+房价code+房价日期；value：priceCalc
			String pirceColseKey = priceCalc.getRoomTypeCode()+priceCalc.getRatePlanCode()+priceCalc.getDate();
			map.put(pirceColseKey,priceCalc);//以日期为单位
			for (Iterator<RateAmount> iterator = priceCalc.getRateAmountList().iterator(); iterator.hasNext();) {
				RateAmount rateAmount = iterator.next();
				//set里放房型code+房价code+房价日期+人数+价格
				priceSet.add(pirceColseKey+rateAmount.getNumberOfUnits()+rateAmount.getBaseAmount().floatValue());
			}
		}
		priceMap.put("pirceColseMap", map);//
		priceMap.put("priceSet", priceSet);
		return priceMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomMsg> getNewPriceAndClosePrice(HashMap<String,Object> oldMap,
			List<RateDetailVO> newPricelist,HashMap<String,ArrayList<ChannelGoodsVO>> channelRateRoomMap, RatePlanVO rpvo) throws Exception{
		HashMap<String,PriceCalc> oldPriceCalcMap = (HashMap<String, PriceCalc>) oldMap.get("pirceColseMap");//用于匹配关
		HashSet<String> oldpriceSet = (HashSet<String>) oldMap.get("priceSet");	//用于匹配房价
		Set<String> containChannelCodeSet = new HashSet<String>();
		//房价消息的list
		List<RoomMsg> rateList =  new ArrayList<RoomMsg>();
		//开关消息
		List<RoomMsg> rmList =  new ArrayList<RoomMsg>();
		//推送新房价的MAP
		HashMap<String,PriceCalc> newOpenPriceCalcMap = new HashMap<String, PriceCalc>();
		//新detail PriceCalc
		Map<String,Channel> channelMap = new HashMap<String, Channel>();
		List<PriceCalc> newpcList = priceCalcManager.getPriceCalcListByDetail(newPricelist, rpvo);
		for (PriceCalc priceCalc : newpcList) {
			//key：房型code+房价code+房价日期
			String priceCalcCloseKey = priceCalc.getRoomTypeCode()+priceCalc.getRatePlanCode()+priceCalc.getDate();
			if(oldPriceCalcMap.containsKey(priceCalcCloseKey)){
				oldPriceCalcMap.remove(priceCalcCloseKey);//从旧的房价里删除新的，留下需要关的房价
			}else{
				//新的房价，需推开
				newOpenPriceCalcMap.put(priceCalcCloseKey, priceCalc);
			}
			
			List<RateAmount> rateAmountList = priceCalc.getRateAmountList();
			if(CommonUtil.isEmpty(rateAmountList)){
				continue;
			}
			int findNum = 0;
			for (Iterator<RateAmount> iterator = rateAmountList.iterator(); iterator.hasNext();) {
				RateAmount rateAmount = iterator.next();
				//匹配新旧价格 set
				String priceChangeKey = priceCalcCloseKey+rateAmount.getNumberOfUnits()+""+rateAmount.getBaseAmount().floatValue();
				if(oldpriceSet.contains(priceChangeKey)){
					findNum++;
				}
			}
			if(findNum == rateAmountList.size()){//如果新旧价格都相等则忽略该天
				continue;
			}
			
			//匹配渠道绑定
			Set<String> bindChannelSet = getBindChannelSet(channelRateRoomMap, channelMap, priceCalc);
			for (String channelCode : bindChannelSet) {
				containChannelCodeSet.add(channelCode);
				RoomMsg rm = new RoomMsg();
				rm.setAdsType(AdsMessage.ADSTYPE_RATE);
				rm.setChainCode(priceCalc.getChainCode());
				rm.setChannelCode(channelCode);
				rm.setHotelCode(priceCalc.getHotelCode());
				rm.setRoomTypeCode(priceCalc.getRoomTypeCode());
				rm.setRateCode(priceCalc.getRatePlanCode());
				rm.setStartDate(priceCalc.getDate());
				rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
				rm.setCreatedTime(new Date());
				rm.setCurrencyCode(priceCalc.getCurrencyCode());
				rm.setRateAmountList(rateAmountList);
				rateList.add(rm);// 推送新的房价
			}
		}
		
		//推送日期增加的产品状态
		log.info("DealRateDetailManager.getNewPriceAndClosePrice->"+AdsMessage.ADSTYPE_AVAILUPDATE);
		Collection<PriceCalc> productPriceList = newOpenPriceCalcMap.values();
		if(CommonUtil.isNotEmpty(productPriceList)){
				for (PriceCalc pc : productPriceList) {
					Rsvtype rt= new Rsvtype();
					rt.setDate(DateUtil.convertStringToDate(pc.getDate()));
					rt.setHotelCode(pc.getHotelCode());
					rt.setType(pc.getRoomTypeCode());
					Rsvtype rt2 = rsvtypeManager.getRsvtype(rt);
					if(rt2!=null){
						rt=rt2;
					}
					rt.setForceSend(true);
					rt.setRateCode(pc.getRatePlanCode());
					roomJmsManager.sendRoomStatusMsg2ToJms(rt,false);
				}
			}
		
		
		//推送旧的房价为关
		log.info("DealRateDetailManager.getNewPriceAndClosePrice->"+AdsMessage.ADSTYPE_AVAILUPDATE);
		Collection<PriceCalc> closePriceList = oldPriceCalcMap.values();
		if (CommonUtil.isNotEmpty(closePriceList)) {
			for (PriceCalc priceCalc : closePriceList) {
				// 匹配渠道绑定
				if(priceCalc.getDelFlag()==true){
					Set<String> bindChannelSet = getBindChannelSet(channelRateRoomMap, channelMap, priceCalc);
					for (String channelCode : bindChannelSet) {
						RoomMsg rm = new RoomMsg();
						rm.setOnOff(Boolean.FALSE);
						rm.setChannelCode(channelCode);
						rm.setChainCode(priceCalc.getChainCode());
						rm.setHotelCode(priceCalc.getHotelCode());
						rm.setRoomTypeCode(priceCalc.getRoomTypeCode());
						rm.setRateCode(priceCalc.getRatePlanCode());
						rm.setStartDate(priceCalc.getDate());
						rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());
						rmList.add(rm);
					}
				}
			}
		}
		// 设置AmountAfterTax值
		rateAmountManager.setAfterAmountWithTaxForRoomMsg(rateList);
		rmList.addAll(rateList);
		return rmList;
	}
	
	/***
	 * 获取该天对应绑定的渠道
	 * @param channelRateRoomMap
	 * @param channelMap
	 * @param priceCalc
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<String> getBindChannelSet(HashMap<String,ArrayList<ChannelGoodsVO>> channelRateRoomMap,Map<String,Channel> channelMap,PriceCalc priceCalc) throws Exception{
		String channelBindkey = priceCalc.getHotelCode()+priceCalc.getRatePlanCode()+priceCalc.getRoomTypeCode();
		Set<String> containChannelCodeSet=new HashSet<String>();
		if(channelRateRoomMap.containsKey(channelBindkey)){
			ArrayList<ChannelGoodsVO> cgList = channelRateRoomMap.get(channelBindkey);
			for (ChannelGoodsVO cg : cgList) {
				if(cg.getEffectiveDate()==null){
					continue;
				}
				if(CommonUtil.isEmpty(cg.getExpireDate())){
					cg.setExpireDate(DateUtil.addMonths(new Date(), 36));
				}
				Date date = DateUtil.convertStringToDate(priceCalc.getDate());
				Calendar c = Calendar.getInstance();
				date = DateUtil.setDateHHmmss(date, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
				if(DateUtil.judge(cg.getEffectiveDate(), cg.getExpireDate(), date)){
					Channel channel = null;
					if(channelMap.get(cg.getChannelCode())==null){
						channel = channelManager.getChannelByChannelCode(cg.getChannelCode());
						channelMap.put(cg.getChannelCode(), channel);
					}else{
						channel = channelMap.get(cg.getChannelCode());
					}
					
					if(channel==null || !channel.isPush(DateUtil.convertStringToDate(priceCalc.getDate()))){
						continue;
					}
					containChannelCodeSet.add(cg.getChannelCode());
				}
			}
		}
		return containChannelCodeSet;
	}
}
