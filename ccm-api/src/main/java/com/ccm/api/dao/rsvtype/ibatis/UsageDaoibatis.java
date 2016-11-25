package com.ccm.api.dao.rsvtype.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.UsageDao;
import com.ccm.api.model.rsvtype.Usage;
import com.ccm.api.model.rsvtype.vo.UsageCriteria;


@Repository("usageDao")
public class UsageDaoibatis extends GenericDaoiBatis<Usage, String> implements UsageDao {
    
	public UsageDaoibatis() {
		super(Usage.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usage> getUsages(UsageCriteria usageCriteria) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelIdList", usageCriteria.getHotelIdList());
		param.put("channelIdList", usageCriteria.getChannelIdList());
		param.put("roomTypeList", usageCriteria.getRoomTypeList());
		//param.put("resvDate", usageCriteria.getResvDate());
		param.put("resvDateBegin", usageCriteria.getResvDateBegin());
		param.put("resvDateEnd", usageCriteria.getResvDateEnd());
		return getSqlMapClientTemplate().queryForList("getUsages", param);
	}

	@Override
	public Integer getUsagesCount(UsageCriteria usageCriteria) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelIdList", usageCriteria.getHotelIdList());
		param.put("channelIdList", usageCriteria.getChannelIdList());
		param.put("roomTypeList", usageCriteria.getRoomTypeList());
		//param.put("resvDate", usageCriteria.getResvDate());
		param.put("resvDateBegin", usageCriteria.getResvDateBegin());
		param.put("resvDateEnd", usageCriteria.getResvDateEnd());
		return (Integer) getSqlMapClientTemplate().queryForObject("getUsagesCount", param);
	}

	@Override
	public Usage getUsagesSum(UsageCriteria usageCriteria) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelIdList", usageCriteria.getHotelIdList());
		param.put("channelIdList", usageCriteria.getChannelIdList());
		param.put("roomTypeList", usageCriteria.getRoomTypeList());
		param.put("resvDate", usageCriteria.getResvDate());
		return (Usage) getSqlMapClientTemplate().queryForObject("getUsagesSum", param);
	}

}
