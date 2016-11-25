package com.ccm.api.dao.ratePlan.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.dao.common.mongo.page.Pagination;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVOCriteria;
import com.ccm.api.model.ratePlan.vo.RateDetailVOResult;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Repository("rateDetailVODaoMongo")
public class RateDetailVODaoMongo extends MongodbBaseDao<RateDetailVO> {
	protected Class<RateDetailVO> getEntityClass(){
		return RateDetailVO.class;
	}
	public void batchSave(List<RateDetailVO> rdList) {
		this.saveALL(rdList);
	}

	private Query buildQuery(RateDetailVOCriteria amc){
		Query query = new Query();
		if(CommonUtil.isNotEmpty(amc.getChainCode())){
			query.addCriteria(Criteria.where("chainCode").is(amc.getChainCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getHotelCode())){
			query.addCriteria(Criteria.where("hotelCode").is(amc.getHotelCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getRatePlanCode())){
			query.addCriteria(Criteria.where("ratePlanCode").is(amc.getRatePlanCode()));
		}
		if(CommonUtil.isNotEmpty(amc.getStatus())){
			query.addCriteria(Criteria.where("status").in(amc.getStatus()));
		}
		if(CommonUtil.isNotEmpty(amc.getEffectiveDate()) && CommonUtil.isNotEmpty(amc.getExpireDate())){
			query.addCriteria(Criteria.where("effectiveDate").gte(amc.getEffectiveDate())
							.orOperator(Criteria.where("expireDate").lte(amc.getExpireDate())));
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
	
	public RateDetailVOResult searchRateDetailVO(RateDetailVOCriteria criteria) {
		Pagination<RateDetailVO> page = this.getPage(criteria.getPageNum(), criteria.getPageSize(), buildQuery(criteria));
		List<RateDetailVO> rsvList = page.getDatas();
		RateDetailVOResult res = new RateDetailVOResult();
		res.setResultList(rsvList);
		res.setTotalCount(page.getTotalCount());
		return res;
	}
	
	public void deleteRateDetailVO(RateDetailVO rdv){
		this.delete(rdv);
	}
	public void batchSaveMongo(List<RateDetailVO> rdList, String chainCode, String hotelCode, String ratePlanCode, String uuidSign, String rateType){
		if(rdList==null){
			return;
		}
		List<RateDetailVO> saverdList = new ArrayList<RateDetailVO>();
		for (RateDetailVO rateDetailVO : rdList) {
			rateDetailVO.setRateDetailVOId(null);
			if(rateDetailVO.getExpireDate().before(DateUtil.getCleanDate(new Date()))){
				continue;
			}
			Date temStartDate = DateUtil.cleanTimeDate(new Date());
	    	if(rateDetailVO.getEffectiveDate().before(temStartDate)){
	    		rateDetailVO.setEffectiveDate(temStartDate);
	    	}
			rateDetailVO.setChainCode(chainCode);
			rateDetailVO.setHotelCode(hotelCode);
			rateDetailVO.setRatePlanCode(ratePlanCode);
//			rateDetailVO.setRatePlanId(null);
			rateDetailVO.setCreatedTime(new Date());
			rateDetailVO.setStatus(RateDetailVO.MONGO_STATUS_INIT);
			char h = hotelCode.charAt(0);
			rateDetailVO.setCustomerId((h % 3 + 1)+"");
			rateDetailVO.setUuidSign(uuidSign);
			rateDetailVO.setRateType(rateType);
			saverdList.add(rateDetailVO);
		}
		batchSave(saverdList);
	}
}
