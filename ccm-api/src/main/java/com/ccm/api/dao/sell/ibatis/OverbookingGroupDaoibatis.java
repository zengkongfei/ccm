package com.ccm.api.dao.sell.ibatis;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.sell.OverbookingGroupDao;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.sell.OverbookingGroup;
import com.ccm.api.util.CommonUtil;

@Repository("overbookingGroupDao")
public class OverbookingGroupDaoibatis extends GenericDaoiBatis<OverbookingGroup, String> implements OverbookingGroupDao {

    @Resource
    private HotelDao hotelDao;
    
	public OverbookingGroupDaoibatis() {
		super(OverbookingGroup.class);
	}

	@Override
	public List<OverbookingGroup> getObGroupByHotelId(String hotelId) {
		return this.getSqlMapClientTemplate().queryForList("getObGroupByHotelId",hotelId);
	}

    @Override
    public OverbookingGroup searchOverbookingChannelHotelGroup(String channelId, String hotelCode) {
        List<Hotel> hotelList = hotelDao.getHotelByHotelCode(hotelCode);
        if(CommonUtil.isEmpty(hotelList)){
            log.error("hotelCode not find :"+hotelCode);
            return null;
        }
        HashMap<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("channelId", channelId);
        paramMap.put("hotelId", hotelList.get(0).getHotelId());
        return (OverbookingGroup) getSqlMapClientTemplate().queryForObject("searchOverbookingChannelHotelGroup", paramMap);
    }
}
