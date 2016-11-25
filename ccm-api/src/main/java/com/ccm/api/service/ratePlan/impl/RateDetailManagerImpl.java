package com.ccm.api.service.ratePlan.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.ratePlan.PriceCalcDao;
import com.ccm.api.dao.ratePlan.RateDetailDao;
import com.ccm.api.dao.ratePlan.RoomRateDao;
import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.vo.PriceValidSearchResult;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.SearchPriceValidCriteria;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.ratePlan.RateDetailManager;
import com.ccm.api.service.roomType.RoomTypeManager;

@Service("rateDetailManager")
public class RateDetailManagerImpl extends GenericManagerImpl<RateDetail, String> implements RateDetailManager {
    @Resource
    private PriceCalcDao priceCalcDao;
    @Resource
    private RatePlanManager ratePlanManager;
    @Resource
    private RoomRateDao roomRateDao;
    @Resource
    private RoomTypeManager roomTypeManager;
    @Resource
    private RateDetailDao rateDetailDao;
    @Resource 
    private RateDetailVODaoMongo rateDetailVODaoMongo;
    @Autowired
    public RateDetailManagerImpl(RateDetailDao genericDao) {
        super(genericDao);
    }

    @Override
    public RateDetail addRateDetail(RateDetail detail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteRateDetailByRatePlanId(String ratePlanId) {
        rateDetailDao.deleteRateDetailByRatePlanId(ratePlanId);
    }

    @Override
    public List<RateDetailVO> getRateDetailVOByRatePlanId(String ratePlanId) {
        return rateDetailDao.getRateDetailVOByRatePlanId(ratePlanId);
    }

    @Override
    public boolean delRateDetail(String rateDetailId) {
        HotelVO hv = SecurityHolder.getB2BUser().getHotelvo();
//        RateDetail rd = this.get(rateDetailId);
//        Rateplan rp = ratePlanManager.get(rd.getRatePlanId());
//        PriceCalc pc = new PriceCalc();
//        pc.setChainCode(hv.getChainCode());
//        pc.setHotelCode(hv.getHotelCode());
//        pc.setRatePlanCode(rp.getRatePlanCode());
//        pc.setStartDate(DateUtil.convertDateToString(rd.getEffectiveDate()));
//        pc.setEndDate(DateUtil.convertDateToString(rd.getExpireDate()));
//      //房型
//        List<RoomRateVO> roomRateList = roomRateDao.getRoomRateVOByRateDetailId(rateDetailId);
//        for (RoomRateVO roomRateVO : roomRateList) {
//            RoomType roomType = roomTypeManager.getRoomTypeById(roomRateVO.getRoomTypeId());
//            pc.setRoomTypeCode(roomType.getRoomTypeCode());
//            List<PriceCalc> priceList = priceCalcDao.searchPriceCalc(pc);
//            
//            if(priceList != null && priceList.size()>0){
//                for (PriceCalc priceCalc : priceList) {
//                   priceCalcDao.deletePriceCalc(priceCalc.getPriceCalcId());
//                }
//                log.info("已删除房价代码："+rp.getRatePlanCode()+" 该房价："+priceList.size()+"条");
//            }
//        }
        boolean res = false;
        try {
			priceCalcDao.deletePriceCalcByDetailIdAndRatePlanCode(rateDetailId, null,hv.getHotelCode());
			this.remove(rateDetailId);
			res = true;
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		}
        return res;
    }

	@Override
	public PriceValidSearchResult searchPriceValidTimes(
			SearchPriceValidCriteria criteria) {
		return rateDetailDao.searchPriceValidTimes(criteria);
	}
	
}
