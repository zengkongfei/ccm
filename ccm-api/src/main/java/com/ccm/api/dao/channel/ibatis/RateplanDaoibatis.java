/**
 * 
 */
package com.ccm.api.dao.channel.ibatis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

/**
 * 
 */
@Repository("rateplanDao")
public class RateplanDaoibatis extends GenericDaoiBatis<Rateplan, String> implements RateplanDao {
	
	@Resource
	private RedisDao redisDao;

	public RateplanDaoibatis() {
		super(Rateplan.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanVO> getRatePlanOfTB(List<String> hotelIdList) {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("hotelIdList", hotelIdList);
		req.put("type", MessageType.TB_RATEPLAN);
		req.put("interfaceId", ChannelCodeEnum.TAOBAO.getName());
		req.put("language", LanguageCode.MAIN_LANGUAGE_CODE);
		return getSqlMapClientTemplate().queryForList("getRatePlanOfTB", req);
	}

	@Override
	public RatePlanVO getRatePlanI18nByHotelRateCode(String hotelCode, String ratePlanCode, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelCode", hotelCode);
		params.put("ratePlanCode", ratePlanCode);
		params.put("language", language);
		return (RatePlanVO) getSqlMapClientTemplate().queryForObject("getRatePlanI18nByHotelRateCode", params);
	}
	
	@Override
	public RatePlanVO getRatePlanI18nByCodeHotelId(String ratePlanCode,
			String hotelId) {
		return this.getRatePlanI18nByCodeHotelId(ratePlanCode, hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RatePlanVO getRatePlanI18nByCodeHotelId(String ratePlanCode,String hotelId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ratePlanCode", ratePlanCode);
		params.put("hotelId", hotelId);		
		params.put("language", language);
		return (RatePlanVO) getSqlMapClientTemplate().queryForObject("getRatePlanI18nByCodeHotelId", params);
	}

	@SuppressWarnings("unchecked")
	public List<CancelPolicyVO> getRateCancelByIdOfTB(String ratePlanId) {
		return getSqlMapClientTemplate().queryForList("getRateCancelByIdOfTB", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rateplan> getRatePlanByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getRatePlanByHotelId", hotelId);
	}
	
	public RatePlanVO getRateChainHotelByRateplanIdHotelId(String hotelId, String language, String ratePlanId) {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("ratePlanId", ratePlanId);
		req.put("hotelId", hotelId);
		req.put("language", language);
		return (RatePlanVO) getSqlMapClientTemplate().queryForObject("getRateChainHotelByRateplanIdHotelId", req);
	}

	@Override
	public Rateplan addRateplan(Rateplan rateplan) {
		if (StringUtils.isEmpty(rateplan.getRatePlanId())) {
			// 新增
			String key = rateplan.getHotelId() + rateplan.getRatePlanCode();
			Serializable s = redisDao.readObject(key);
			if (s == null) {
				redisDao.save(key, 1, 60, TimeUnit.SECONDS);
				return save(rateplan);
			}
		} else {
			// 更新
			return save(rateplan);
		}
		return null;
	}

	@Override
	public void updateRateplan(Rateplan rateplan) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rateplan getRateplanByRateplanCode(String rateplanCode, String hotelCode) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("rateplanCode", rateplanCode);
		map.put("hotelCode", hotelCode);
		return (Rateplan) getSqlMapClientTemplate().queryForObject("getRateplanByRateplanCode", map);
	}

	@Override
	public RatePlanI18n addRateplani18n(RatePlanI18n rpi18n) {
		if (StringUtils.hasText(rpi18n.getRatePlanMId())) {
			return updateRateplani18n(rpi18n);
		} else {
			rpi18n.setRatePlanMId(UUID.randomUUID().toString().replace("-", ""));
			return (RatePlanI18n) getSqlMapClientTemplate().insert("addRatePlanI18n", rpi18n);
		}
	}

	@Override
	public RatePlanI18n updateRateplani18n(RatePlanI18n rpi18n) {
		getSqlMapClientTemplate().update("updateRatePlanI18n", rpi18n);
		return rpi18n;
	}

	@Override
	public List<HashMap<String, String>> getRateNameByHotelId(String hotelId) {
		return this.getRateNameByHotelId(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> getRateNameByHotelId(String hotelId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRateNameByHotelId", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getValidRatePlanByHotelIdLang(String hotelId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getValidRatePlanByHotelIdLang", params);
	}

	@Override
	public RatePlanI18n getRatePlanI18nById(String ratePlanId) {
		return this.getRatePlanI18nById(ratePlanId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public RatePlanI18n getRatePlanI18nById(String ratePlanId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ratePlanId", ratePlanId);
		params.put("language", language);
		return (RatePlanI18n) getSqlMapClientTemplate().queryForObject("getRatePlanI18nById", params);
	}

	@Override
	public List<HashMap<String, Object>> getNoInheritRateNameByHotelId(String hotelId) {
		return this.getNoInheritRateNameByHotelId(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> getNoInheritRateNameByHotelId(String hotelId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getNoInheritRateNameByHotelId", params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Rateplan> getRefRateplan(String hotelId, String ratePlanId) {
		HashMap paramMap = new HashMap();
		paramMap.put("hotelId", hotelId);
		paramMap.put("ratePlanId", ratePlanId);
		return getSqlMapClientTemplate().queryForList("getRefRateplan", paramMap);
	}

	public Rateplan getRatePlanByObj(Rateplan r) {
		return (Rateplan) getSqlMapClientTemplate().queryForObject("getRatePlanByObj", r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RatePlanI18n> getRatePlanI18ns(String ratePlanId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("ratePlanId", ratePlanId);
		return getSqlMapClientTemplate().queryForList("getRatePlanI18ns", params);
	}
	
	@Override
	public void deleteRatePlanI18nByRatePlanId(String ratePlanId){
		getSqlMapClientTemplate().update("deleteRatePlanI18nByRatePlanId",ratePlanId);
	}

    @Override
    public void updateRateSoldableCondition(String ratePlanId,
            String soldableCondition) {
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("ratePlanId", ratePlanId);
        params.put("soldableCondition", soldableCondition);
        getSqlMapClientTemplate().update("updateRateSoldableCondition",params);
    }

	@Override
	public Rateplan getRatePlanById(String id) {
		// TODO Auto-generated method stub
		return (Rateplan)getSqlMapClientTemplate().queryForObject("getRateplan",id);
	}

	@Override
	public void updateAccessCode(Rateplan r) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateAccessCodeById",r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rateplan> getRatePlanByHotelIdList(List<String> hotelIdList) {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("hotelIdList", hotelIdList);
		return getSqlMapClientTemplate().queryForList("getRatePlanByHotelIdList", req);	
	}
}
