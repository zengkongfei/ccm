package com.ccm.api.dao.guestRoom.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.guestRoom.GuestroomamenityDAO;
import com.ccm.api.model.guestroom.Guestroomamenity;

@Repository("guestroomamenityDAO")
public class GuestroomamenityDAOibatis extends GenericDaoiBatis<Guestroomamenity, String> implements GuestroomamenityDAO{
    public GuestroomamenityDAOibatis() {
        super(Guestroomamenity.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> getGuestRoomAmenity(String guestRoomId) {
        return getSqlMapClientTemplate().queryForMap("getRoomAmenityByGRID", guestRoomId, "codeNo","guestroomamenityId");
    }
    
}