package com.ccm.api.dao.guestRoom.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.guestRoom.GuestroomdetailamenityDAO;
import com.ccm.api.model.guestroom.Guestroomdetailamenity;

@Repository("guestroomdetailamenityDAO")
public class GuestroomdetailamenityDAOibatis  extends GenericDaoiBatis<Guestroomdetailamenity, String> implements GuestroomdetailamenityDAO{

    public GuestroomdetailamenityDAOibatis() {
        super(Guestroomdetailamenity.class);
    }


}