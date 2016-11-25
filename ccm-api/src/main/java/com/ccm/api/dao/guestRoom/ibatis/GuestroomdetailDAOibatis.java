package com.ccm.api.dao.guestRoom.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.guestRoom.GuestroomdetailDAO;
import com.ccm.api.model.guestroom.Guestroomdetail;

@Repository("guestroomdetailDAO")
public class GuestroomdetailDAOibatis extends GenericDaoiBatis<Guestroomdetail, String> implements GuestroomdetailDAO{

    public GuestroomdetailDAOibatis() {
        super(Guestroomdetail.class);
    }
}