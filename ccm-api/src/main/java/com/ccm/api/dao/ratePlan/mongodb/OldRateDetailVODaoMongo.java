package com.ccm.api.dao.ratePlan.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.ratePlan.vo.OldRateDetailVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.util.DateUtil;

@Repository("oldRateDetailVODaoMongo")
public class OldRateDetailVODaoMongo extends MongodbBaseDao<OldRateDetailVO> {
	protected Class<OldRateDetailVO> getEntityClass(){
		return OldRateDetailVO.class;
	}
	private void batchSave(List<OldRateDetailVO> rdList) {
		this.saveALL(rdList);
	}
	
	public void deleteRateDetailVO(RateDetailVO rdv){
		this.delete(rdv);
	}
	public void batchSaveMongo(List<RateDetailVO> rdList, String chainCode, String hotelCode, String ratePlanCode,String uuidSign,String rateType){
		List<OldRateDetailVO> oldRdList = new ArrayList<OldRateDetailVO>();
		for (RateDetailVO rateDetailVO : rdList) {
			if(rateDetailVO.getExpireDate().before(DateUtil.getCleanDate(new Date()))){
				continue;
			}
			Date temStartDate = DateUtil.cleanTimeDate(new Date());
	    	if(rateDetailVO.getEffectiveDate().before(temStartDate)){
	    		rateDetailVO.setEffectiveDate(temStartDate);
	    	}
			OldRateDetailVO OldRd = new OldRateDetailVO();
			BeanUtils.copyProperties(rateDetailVO, OldRd);
			OldRd.setUuidSign(uuidSign);
			OldRd.setRateType(rateType);
			OldRd.setChainCode(chainCode);
			OldRd.setHotelCode(hotelCode);
			OldRd.setRatePlanCode(ratePlanCode);
//			OldRd.setRatePlanId(null);
			OldRd.setCreatedTime(new Date());
			OldRd.setStatus(RateDetailVO.MONGO_STATUS_INIT);
			oldRdList.add(OldRd);
		}
		batchSave(oldRdList);
	}
}
