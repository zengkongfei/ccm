/**
 * 
 */
package com.ccm.api.dao.channel;

import java.util.HashMap;
import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

public interface RateplanDao extends GenericDao<Rateplan, String> {
	/**
	 * 根据TB用户查询房价列表
	 * 
	 * @param hotelIdList
	 * @return
	 */
	List<RatePlanVO> getRatePlanOfTB(List<String> hotelIdList);

	RatePlanVO getRatePlanI18nByCodeHotelId(String ratePlanCode, String hotelId, String language);

	RatePlanVO getRatePlanI18nByCodeHotelId(String ratePlanCode, String hotelId);

	List<CancelPolicyVO> getRateCancelByIdOfTB(String ratePlanId);

	List<Rateplan> getRatePlanByHotelId(String hotelId);
	
	List<Rateplan> getRatePlanByHotelIdList(List<String> hotelIdList);

	RatePlanVO getRateChainHotelByRateplanIdHotelId(String hotelId, String language, String ratePlanId);

	/**
	 * 保存房价定义
	 */
	Rateplan addRateplan(Rateplan rateplan);

	/**
	 * 修改房价定义
	 */
	void updateRateplan(Rateplan rateplan);

	/**
	 * 根据房价代码查找房价定义
	 */
	Rateplan getRateplanByRateplanCode(String rateplanCode, String hotelCode);

	public RatePlanI18n addRateplani18n(RatePlanI18n rpi18n);

	public List<HashMap<String, String>> getRateNameByHotelId(String hotelId);

	RatePlanI18n getRatePlanI18nById(String ratePlanId);

	RatePlanI18n updateRateplani18n(RatePlanI18n rpi18n);

	public List<HashMap<String, Object>> getNoInheritRateNameByHotelId(String hotelId);

	public List<Rateplan> getRefRateplan(String hotelId, String ratePlanId);

	Rateplan getRatePlanByObj(Rateplan r);

	List<RatePlanI18n> getRatePlanI18ns(String ratePlanId);

	void deleteRatePlanI18nByRatePlanId(String ratePlanId);

	List<HashMap<String, String>> getRateNameByHotelId(String hotelId, String language);

	List<HashMap<String, String>> getValidRatePlanByHotelIdLang(String hotelId, String language);

	List<HashMap<String, Object>> getNoInheritRateNameByHotelId(String hotelId, String language);

	RatePlanI18n getRatePlanI18nById(String ratePlanId, String language);

	RatePlanVO getRatePlanI18nByHotelRateCode(String hotelCode, String ratePlanCode, String language);

	void updateRateSoldableCondition(String ratePlanId, String soldableCondition);
	
	Rateplan getRatePlanById(String id) ;
	
	void updateAccessCode(Rateplan r);
}
