package com.ccm.api.dao.email.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.email.HotelEmailMapperDao;
import com.ccm.api.model.email.HotelEmailMapper;

@Repository("hotelEmailMapperDao")
public class HotelEmailMapperDaoibatis extends GenericDaoiBatis<HotelEmailMapper, String> implements HotelEmailMapperDao{

    public HotelEmailMapperDaoibatis() {
        super(HotelEmailMapper.class);
    }

    @Override
    public List<HotelEmailMapper> searchHotelEmailMapper(HotelEmailMapper hem) {
        return getSqlMapClientTemplate().queryForList("searchHotelEmailMapper",hem);
    }
    
}
