package com.ccm.api.dao.rsvtype.mongodb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.dao.common.mongo.page.Pagination;
import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.RsvtypeChannelCode;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.rsvtype.vo.RsvtypeChannelCriteria;
import com.ccm.api.model.rsvtype.vo.RsvtypeChannelResult;
import com.ccm.api.util.CommonUtil;

@Repository("rsvtypeChannelDaoMongo")
public class RsvtypeChannelDaoMongo extends MongodbBaseDao<RsvtypeChannel> implements RsvtypeChannelDao{
	protected Class<RsvtypeChannel> getEntityClass(){
		return RsvtypeChannel.class;
	}
	@Override
	public List<RsvtypeChannelCode> getRsvtypeChannelCode(){
		List<RsvtypeChannelCode> rsvtypeChannelCodeList = this.getMongoTemplate().findAll(RsvtypeChannelCode.class);
		return rsvtypeChannelCodeList;
	}
	@Override
	public void refreshRsvtypeChannelCode(){
		List<RsvtypeChannelCode> rsvtypeChannelCodeList = new ArrayList<RsvtypeChannelCode>();
		MongoTemplate mt = this.getMongoTemplate();
		GroupBy gb = new GroupBy(new String[]{"chainCode","channelCode"});//,"hotelCode"
		gb.initialDocument("{count:0}");
		gb.reduceFunction("function(doc, prev){prev.count+=1}");
		GroupByResults<RsvtypeChannelCode> gbResult = mt.group(Criteria.where("sendStatus").is("0"), "rsvtypeChannel", gb, RsvtypeChannelCode.class);
		
		Iterator<RsvtypeChannelCode> it = gbResult.iterator();
		while (it.hasNext()) {
			RsvtypeChannelCode rsvtypeChannel = it.next();
			rsvtypeChannelCodeList.add(rsvtypeChannel);
		}
		Collections.sort(rsvtypeChannelCodeList,new Comparator<RsvtypeChannelCode>() {
			public int compare(RsvtypeChannelCode o1, RsvtypeChannelCode o2) {
				return o2.getCount().compareTo(o1.getCount());
			}
		});
		this.dropEntity(RsvtypeChannelCode.class);
		this.saveALL(rsvtypeChannelCodeList);
	}
	@Override
	public RsvtypeChannelResult searchRsvtypeChannel(RsvtypeChannelCriteria amc) {
		Pagination<RsvtypeChannel> page = this.getPage(amc.getPageNum(), amc.getPageSize(), buildQuery(amc));
		List<RsvtypeChannel> rsvList = page.getDatas();
		RsvtypeChannelResult rsvRes = new RsvtypeChannelResult();
		rsvRes.setResultList(rsvList);
		rsvRes.setTotalCount(page.getTotalCount());
		return rsvRes;
	}
	private Query buildQuery(RsvtypeChannelCriteria amc){
		Query query = new Query();
		if(CommonUtil.isNotEmpty(amc.getChainCode())){
			query.addCriteria(Criteria.where("chainCode").is(amc.getChainCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getChannelCode())){
			query.addCriteria(Criteria.where("channelCode").is(amc.getChannelCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getHotelid())){
			query.addCriteria(Criteria.where("hotelid").is(amc.getHotelid()));
		}
		if(CommonUtil.isNotEmpty(amc.getHotelCode())){
			query.addCriteria(Criteria.where("hotelCode").is(amc.getHotelCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getType())){
			query.addCriteria(Criteria.where("type").is(amc.getType()));
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
	@Override
	public void addBatchRsvtypeChannel(List<RsvtypeChannel> rsvcList) {
		this.saveALL(rsvcList);
	}
	
	@Override
	public void updateRsvtypeChannelSendStatus(RsvtypeChannel rc) {
		this.updateById(rc);
	}
	
	@Override
	public RsvtypeChannel getRsvtypeChannelByRsvIdAndChannelId(String rsvId,
			String channelId) {
		return null;
	}

	@Override
	public List<RsvtypeChannel> getRsvtypeChannelAilable(
			HashMap<String, Object> paramMap) {
		return null;
	}

	@Override
	public List<Rsvtype> getRsvtypeByChannelIdsRoomTypeCodes(RoomStatusVO vo) {
		return null;
	}

	@Override
	public void updateBatchRsvtypeChannel(List<RsvtypeChannel> list) {
		
	}

	@Override
	public List<RsvtypeChannel> getRsvtypeChannelAvailable(String rsvtypeId,
			Set<String> channelIdSet) {
		return null;
	}
	
	@Override
	public Integer removeRsvtypeChannel(String channelId, String rsvtypeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addRsvtypeChannel(RsvtypeChannel rsvtypeChannel) {
		// TODO Auto-generated method stub
	}
	@Override
	public List<RsvtypeChannel> getRsvChannelListByHeaderDetail(
			String hotelCode, String channelId, String roomType,
			Date inventoryDate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer updateAllotmentToRsvtypeChannel(RsvtypeChannel rsvtypeChannel) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer updateRsvtypeForDeduct(Rsvtype rsvtype) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer updateRsvtypeChannelForDeduct(Rsvtype RsvtypeChannel) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
