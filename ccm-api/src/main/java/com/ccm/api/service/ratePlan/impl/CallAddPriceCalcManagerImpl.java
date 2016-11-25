package com.ccm.api.service.ratePlan.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.mongodb.OldRateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.ratePlan.CallAddPriceCalcManager;
import com.ccm.api.service.ratePlan.DealRateDetailManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
@Service("callAddPriceCalcManager")
public class CallAddPriceCalcManagerImpl implements CallAddPriceCalcManager{
	protected final Log log = LogFactory.getLog(getClass());
	@Resource
	private RateDetailVODaoMongo rateDetailVODaoMongo;
	@Resource
	private OldRateDetailVODaoMongo oldRateDetailVODaoMongo;
	@Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	@Resource
	private RatePlanManager ratePlanManager;
	@Resource
	private DealRateDetailManager dealRateDetailManager;
	@Resource
	private EmailManager emailManager;
	
	
	@Async
	@Override
	public void startAddPmsPriceCalc(String uuidSign,Rateplan rp) {
		//获取new PriceCalc
		RatePlanVO rpvo = new RatePlanVO();
		rpvo.setRp(rp);
		List<RateDetailVO> newPricelist = rateDetailVODaoMongo.find(Query.query(Criteria.where("uuidSign").is(uuidSign)));
		if(CommonUtil.isEmpty(newPricelist)){
//			String tsid = rp.getTransactionId()+"_"+rp.getRatePlanCode();
//			EmailVO emailVO = new EmailVO();
//			emailVO.setTo("davin.deng@shijinet.com.cn");
//			emailVO.setContent(" transactionId: "+tsid + "  hotel:"+rp.getHotelCode() + "  " +rp.getHotelId()+"  "+ new Date().toLocaleString() +" uuidSign:"+uuidSign);
//			emailVO.setSubject("pms upload rate err");
//			emailManager.sendText(emailVO);
			return;
		}
		String err = null;
		try {
			//获取有效渠道
			HashMap<String,ArrayList<ChannelGoodsVO>> channelRateRoomMap = ratePlanManager.getValidChannel(rp);
			//获取旧的PriceCalc
            HashMap<String,Object> oldPriceCalcMap = dealRateDetailManager.getOldPriceCalcByOldDetail(uuidSign,rpvo); 
            if(rp.getIsPmsUpload()==true&&oldPriceCalcMap.get("pirceColseMap")!=null){
            	Map<String,PriceCalc>pirceColseMap=(Map<String,PriceCalc>)oldPriceCalcMap.get("pirceColseMap");
            	for(String key:pirceColseMap.keySet()){
            		if(pirceColseMap.get(key)!=null){
            			pirceColseMap.get(key).setDelFlag(true);
            		}
            	}
            }
            //需要推送的房价
            List<RoomMsg> rmList =  dealRateDetailManager.getNewPriceAndClosePrice(oldPriceCalcMap, newPricelist, channelRateRoomMap, rpvo);
			
			roomMsgDaoMongo.batchSave(rmList);
		} catch (Exception e) {
			err = CommonUtil.getExceptionMsg(e, new String[]{"ccm","mc"});
			e.printStackTrace();
		}
		if(err != null){
			rateDetailVODaoMongo.update(Query.query(Criteria.where("uuidSign").is(uuidSign)),
					Update.update("status", RateDetailVO.MONGO_STATUS_ERR).update("errMsg", err));
		}else{
			rateDetailVODaoMongo.remove(Query.query(Criteria.where("uuidSign").is(uuidSign)));
			oldRateDetailVODaoMongo.remove(Query.query(Criteria.where("uuidSign").is(uuidSign)));
		}
	}
}
