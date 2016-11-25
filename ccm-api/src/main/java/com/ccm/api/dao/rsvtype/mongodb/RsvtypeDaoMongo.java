package com.ccm.api.dao.rsvtype.mongodb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.dao.common.mongo.page.Pagination;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeCode;
import com.ccm.api.model.rsvtype.vo.RsvtypeCriteria;
import com.ccm.api.model.rsvtype.vo.RsvtypeResult;
import com.ccm.api.util.CommonUtil;


@Repository("rsvtypeDaoMongo")
public class RsvtypeDaoMongo extends MongodbBaseDao<Rsvtype> {
	
	protected final Log log = LogFactory.getLog(getClass());

	protected Class<Rsvtype> getEntityClass(){
		return Rsvtype.class;
	}
	
	/**
	 * 分页查询
	 * @param amc
	 * @return
	 */
	public RsvtypeResult searchRsvtype(RsvtypeCriteria amc) {
		log.info("amc:"+JSON.toJSONString(amc));
		Pagination<Rsvtype> page = this.getPage(amc.getPageNum(), amc.getPageSize(), buildQuery(amc));
		List<Rsvtype> rsvList = page.getDatas();
		RsvtypeResult rsvRes = new RsvtypeResult();
		rsvRes.setResultList(rsvList);
		rsvRes.setTotalCount(page.getTotalCount());
		return rsvRes;
	}
	
	/**
	 * 抓取所有符合条件的记录
	 * @param amc
	 * @return
	 */
	public List<Rsvtype> searchRsvtypeList(RsvtypeCriteria amc){
		log.info("amc:"+JSON.toJSONString(amc));
		return this.getMongoTemplate().find(buildQuery(amc), Rsvtype.class, "rsvtype");
	}

	public void updateRsvtypeStatus(Rsvtype rt) {
		this.updateById(rt);
	}

	public List<RsvtypeCode> getRsvtypeCode(String machine) {
		//List<RsvtypeCode> list = this.getMongoTemplate().findAll(RsvtypeCode.class);
		Query query = new Query(Criteria.where("machine").is(machine));
		List<RsvtypeCode> list = this.getMongoTemplate().find(query, RsvtypeCode.class);
		return list;
	}

	public void refreshRsvtypeCode(String machine, int machineCount) {
		List<RsvtypeCode> rsvtypeCodeList = new ArrayList<RsvtypeCode>();
		MongoTemplate mt = this.getMongoTemplate();
		GroupBy gb = new GroupBy(new String[]{"chainCode","hotelCode"});//,"hotelCode"
		gb.initialDocument("{count:0}");
		gb.reduceFunction("function(doc, prev){prev.count+=1}");
		GroupByResults<RsvtypeCode> gbResult = mt.group(Criteria.where("status").is("0"), "rsvtype", gb, RsvtypeCode.class);
		Iterator<RsvtypeCode> it = gbResult.iterator();
		while (it.hasNext()) {
			RsvtypeCode rsvtypeC = it.next();
			String hotelCode = rsvtypeC.getHotelCode();
			if(StringUtils.isBlank(hotelCode)) continue;
			
			int asciiInt = (int)hotelCode.charAt(0);
			int m = asciiInt % machineCount + 1;    //取模得到要执行的机器
			rsvtypeC.setMachine(m+"");
			rsvtypeCodeList.add(rsvtypeC);
		}
		
		Collections.sort(rsvtypeCodeList,new Comparator<RsvtypeCode>() {
			public int compare(RsvtypeCode o1, RsvtypeCode o2) {
				return o2.getCount().compareTo(o1.getCount());
			}
		});
		
		//this.getMongoTemplate().findAndRemove(new Query(Criteria.where("machine").is(machine)), RsvtypeCode.class);
		this.dropEntity(RsvtypeCode.class);
		this.saveALL(rsvtypeCodeList);
	}
	
	
	private Query buildQuery(RsvtypeCriteria amc){
		Query query = new Query();
		if(CommonUtil.isNotEmpty(amc.getChainCode())){
			query.addCriteria(Criteria.where("chainCode").is(amc.getChainCode()));
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
		if(CommonUtil.isNotEmpty(amc.getTypes())){
			query.addCriteria(Criteria.where("type").in(amc.getTypes()));
		}
		if(CommonUtil.isNotEmpty(amc.getStatus())){
			query.addCriteria(Criteria.where("status").is(amc.getStatus()));
		}
		if(CommonUtil.isNotEmpty(amc.getStartDate()) && CommonUtil.isNotEmpty(amc.getEndDate())){
			query.addCriteria(Criteria.where("date").gte(amc.getStartDate())
							.andOperator(Criteria.where("date").lte(amc.getEndDate())));
		}
		if(CommonUtil.isNotEmpty(amc.getStartDate()) && CommonUtil.isEmpty(amc.getEndDate())){
			query.addCriteria(Criteria.where("date").gte(amc.getStartDate()));
		}
		if(CommonUtil.isNotEmpty(amc.getEndDate()) && CommonUtil.isEmpty(amc.getStartDate())){
			query.addCriteria(Criteria.where("date").lte(amc.getEndDate()));
		}
		if(CommonUtil.isNotEmpty(amc.getSortBy())){
			query.with(new Sort(amc.ASC.equalsIgnoreCase(amc.getDesc()) ? Sort.Direction.ASC : Sort.Direction.DESC, amc.getSortBy()));
		}else{
			query.with(new Sort(Sort.Direction.DESC, "mongoTime"));
		}
		return query;
	}
	
	public void batchSave(List<Rsvtype> rsvList) {
		this.saveALL(rsvList);
	}
	
}
