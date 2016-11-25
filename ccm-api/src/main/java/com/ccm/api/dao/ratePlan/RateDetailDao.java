package com.ccm.api.dao.ratePlan;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.vo.RoomRateDetailComparableVO;
import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.vo.PriceValidSearchResult;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.SearchPriceValidCriteria;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;

public interface RateDetailDao extends GenericDao<RateDetail, String> {

	/**
	 * 保存房价明细
	 */
	RateDetail addRateDetail(RateDetail detail);
	
	
	/**
	 * 根据房价ID查找房价明细
	 */
	List<RateDetail> getRateDetailByRatePlanId(String ratePlanId);

    List<RateDetailVO> getRateDetailVOByRatePlanId(String ratePlanId);

    RateDetail updateRateDetail(RateDetail detail);

    /**
     * 根据房价ID删除所有的rateDetail
     * @param ratePlanId
     */
	void deleteRateDetailByRatePlanId(String ratePlanId);

	void deleteRateDetailById(String rateDetailId);
	
	RateDetailVO getRateDetailVO(String detailId);


	PriceValidSearchResult searchPriceValidTimes(
			SearchPriceValidCriteria criteria);
	
	void addBatchRateDetails(List<RateDetailVO> rateDetailList);


	List<RateDetailVO> getRateDetailVOByRatePlanId(String rpId,
			String rateDetailId);
	
	List<RoomRateDetailComparableVO> getCompareableRateDetailVOByRatePlanId(Map<String,Object>map);
	
	
	List<RateCodeWithRoomVO> getRateCodeFromRoomTypeId(String hotelId,
			String startDate, String endDate, String roomTypeId,List<String> ratePlanCodeList,String accessCode,String channelId);


	Integer getValidCountOfRateDetail(String ratePlanId, Date endDate);
}
