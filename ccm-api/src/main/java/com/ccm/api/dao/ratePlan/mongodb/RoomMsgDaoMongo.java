package com.ccm.api.dao.ratePlan.mongodb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.dao.common.mongo.page.Pagination;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.vo.RoomMsgCode;
import com.ccm.api.model.ratePlan.vo.RoomMsgCriteria;
import com.ccm.api.model.ratePlan.vo.RoomMsgResult;
import com.ccm.api.util.CommonUtil;

@Repository("roomMsgDaoMongo")
public class RoomMsgDaoMongo extends MongodbBaseDao<RoomMsg> {
	protected Class<RoomMsg> getEntityClass(){
		return RoomMsg.class;
	}

	public RoomMsgResult serachRoomMsg(RoomMsgCriteria amc){
		Pagination<RoomMsg> page = this.getPage(amc.getPageNum(), amc.getPageSize(), buildQuery(amc));
		List<RoomMsg> rsvList = page.getDatas();
		RoomMsgResult rmRes = new RoomMsgResult();
		rmRes.setResultList(rsvList);
		rmRes.setTotalCount(page.getTotalCount());
		return rmRes;
	}

	private Query buildQuery(RoomMsgCriteria amc){
		Query query = new Query();
		
		if(CommonUtil.isNotEmpty(amc.getChainCodeList())){
			query.addCriteria(Criteria.where("chainCode").in(amc.getChainCodeList()));
		}
		if(CommonUtil.isNotEmpty(amc.getChannelCodeList())){
			query.addCriteria(Criteria.where("channelCode").in(amc.getChannelCodeList()));
		}
		if(CommonUtil.isNotEmpty(amc.getHotelCodeList())){
			query.addCriteria(Criteria.where("hotelCode").in(amc.getHotelCodeList()));
		}
		if(CommonUtil.isNotEmpty(amc.getRoomTypeCodeList())){
			query.addCriteria(Criteria.where("roomTypeCode").in(amc.getRoomTypeCodeList()));
		}
		if(CommonUtil.isNotEmpty(amc.getSendStatusList())){
			query.addCriteria(Criteria.where("sendStatus").in(amc.getSendStatusList()));
		}
		if(CommonUtil.isNotEmpty(amc.getAdsTypeList())){
			query.addCriteria(Criteria.where("adsType").in(amc.getAdsTypeList()));
		}

		if(CommonUtil.isNotEmpty(amc.getChainCode())){
			query.addCriteria(Criteria.where("chainCode").is(amc.getChainCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getChannelCode())){
			query.addCriteria(Criteria.where("channelCode").is(amc.getChannelCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getHotelCode())){
			query.addCriteria(Criteria.where("hotelCode").is(amc.getHotelCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getRoomTypeCode())){
			query.addCriteria(Criteria.where("roomTypeCode").is(amc.getRoomTypeCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getSendStatus())){
			query.addCriteria(Criteria.where("sendStatus").is(amc.getSendStatus()));
		}
		if(CommonUtil.isNotEmpty(amc.getStartDate()) && CommonUtil.isNotEmpty(amc.getEndDate())){
			query.addCriteria(Criteria.where("createdTime").gte(amc.getStartDate())
							.andOperator(Criteria.where("createdTime").lte(amc.getEndDate())));
		}
		if(CommonUtil.isNotEmpty(amc.getStartDate()) && CommonUtil.isEmpty(amc.getEndDate())){
			query.addCriteria(Criteria.where("createdTime").gte(amc.getStartDate()));
		}
		if(CommonUtil.isNotEmpty(amc.getEndDate()) && CommonUtil.isEmpty(amc.getStartDate())){
			query.addCriteria(Criteria.where("createdTime").lte(amc.getEndDate()));
		}
		if(CommonUtil.isNotEmpty(amc.getSortBy())){
			query.with(new Sort(amc.ASC.equalsIgnoreCase(amc.getDesc()) ? Sort.Direction.ASC : Sort.Direction.DESC, amc.getSortBy()));
		}else{
			query.with(new Sort(Sort.Direction.DESC, "createdTime"));
		}
		return query;
	}
	public void batchSave(List<RoomMsg> rmList) {
		this.saveALL(rmList);
	}
	
	public List<RoomMsgCode> refreshRoomMsgCode(){
		List<RoomMsgCode> roomMsgCodeList = new ArrayList<RoomMsgCode>();
		MongoTemplate mt = this.getMongoTemplate();
		GroupBy gb = new GroupBy(new String[]{"chainCode","channelCode","hotelCode","roomTypeCode"});//,"hotelCode"
		gb.initialDocument("{count:0}");
		gb.reduceFunction("function(doc, prev){prev.count+=1}");
		GroupByResults<RoomMsgCode> gbResult = mt.group(Criteria.where("sendStatus").is("0"), "roomMsg", gb, RoomMsgCode.class);
		
		Iterator<RoomMsgCode> it = gbResult.iterator();
		while (it.hasNext()) {
			RoomMsgCode roomMsgCode = it.next();
			roomMsgCodeList.add(roomMsgCode);
		}
		Collections.sort(roomMsgCodeList,new Comparator<RoomMsgCode>() {
			public int compare(RoomMsgCode o1, RoomMsgCode o2) {
				return o2.getCount().compareTo(o1.getCount());
			}
		});
		this.dropEntity(RoomMsgCode.class);
		this.saveALL(roomMsgCodeList);
		return roomMsgCodeList;
	}

	public void updateRoomMsgSendStatus(RoomMsg rm) {
		RoomMsg uprm = new RoomMsg();
		uprm.setRoomMsgId(rm.getRoomMsgId());
		uprm.setErrMsg(rm.getErrMsg());
		uprm.setSendStatus(rm.getSendStatus());
		this.updateById(uprm);
	}
}
