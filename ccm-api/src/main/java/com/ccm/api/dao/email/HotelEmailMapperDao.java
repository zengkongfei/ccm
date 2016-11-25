package com.ccm.api.dao.email;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.email.HotelEmailMapper;

public interface HotelEmailMapperDao extends GenericDao<HotelEmailMapper,String>{

    List<HotelEmailMapper> searchHotelEmailMapper(HotelEmailMapper hem);

}
