package com.ccm.api.service.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.service.base.GenericManager;

public interface RatePlanManager extends GenericManager<Rateplan, String> {

	RatePlanVO getRatePlanI18nByCodeHotelId(String ratePlanCode, String hotelId, String language);

	RatePlanVO getRatePlanI18nByCodeHotelId(String ratePlanCode, String hotelId);

	List<CancelPolicyVO> getRateCancelByIdOfTB(String ratePlanId);
	
	void addRateplan(Rateplan rateplan);

	/**
	 * 保存房价定义
	 * @param language 
	 */
	RatePlanVO addRateplanVO(RatePlanVO vo, String language);

	/**
	 * 修改房价定义
	 */
	void updateRateplan(RatePlanVO vo);

	/**
	 * 根据房价代码查找房价定义
	 */
	Rateplan getRateplanByRateplanCode(String rateplanCode, String hotelCode);

	RatePlanI18n getRatePlanI18nById(String ratePlanId);
	
	RatePlanI18n getRatePlanI18nById(String ratePlanId, String language);

	List<Rateplan> getRatePlanByHotelId(String hotelId);

	List<HashMap<String, String>> getRateNameByHotelId(String hotelId,String language);
	
	List<Rateplan> getRatePlanByHotelIdList(List<String> hotelIdList);

	List<HashMap<String, String>> getValidRatePlanByHotelIdLang(String hotelId, String language);

	/***
	 * 获取房价相关
	 * 
	 * @param ratePlanId
	 * @param isNeedHead
	 *            是否需要获取第一步信息
	 * @return
	 */
	RatePlanVO getRatePlanVO(String ratePlanId, boolean isNeedHead,String language);

	/***
	 * 验证房价明细是否可用
	 * 
	 * @param ratePlanVO
	 * @return 可用返回true
	 */
	boolean validateRateDetail(RatePlanVO ratePlanVO,String language);

	List<RateDetailVO> getRateDetailVOList(Rateplan rp);
	
	/**
	 * 获取房价明细 
	 * 	备注：多语言使用
	 * @param rp
	 * @param language
	 * @return
	 */
	List<RateDetailVO> getRateDetailVOList(Rateplan rp,String language);

	List<HashMap<String, Object>> getNoInheritRateNameByHotelId(String hotelId,String language);

	void updateRefRateplan(RatePlanVO ratePlanVO);

	List<Rateplan> getRefRateplan(String hotelId, String ratePlanId);

	List<RatePlanI18n> getRatePlanI18ns(String ratePlanId);

	void deleteRatePlanI18nByRatePlanId(String ratePlanId);

	void updateRateSoldableCondition(String ratePlanId, String jsonString);

	List<RateDetailVO> getRateDetailVOList(Rateplan rateplan, List<RateDetailVO> rateDetailVOList);
	
	/**
	 * 获取房价明细 ,根据已有的明细计算子房价   
	 * 	备注：多语言使用
	 * @param rateplan
	 * @param rateDetailVOList
	 * @param language
	 * @return
	 */
	List<RateDetailVO> getRateDetailVOList(Rateplan rateplan, List<RateDetailVO> rateDetailVOList,String language);

	HashMap<String, ArrayList<ChannelGoodsVO>> getValidChannel(Rateplan rp);

	boolean autoHandleRateDetail(RatePlanVO ratePlanVO,boolean isSplit);
	
	void updateAccessCode(Rateplan r);
}
