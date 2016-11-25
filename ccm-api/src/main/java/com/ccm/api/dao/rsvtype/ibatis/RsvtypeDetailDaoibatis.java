package com.ccm.api.dao.rsvtype.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.RsvtypeDetailDao;
import com.ccm.api.model.rsvtype.RsvtypeDetail;

@Repository("rsvtypeDetailDao")
public class RsvtypeDetailDaoibatis extends GenericDaoiBatis<RsvtypeDetail, String> implements RsvtypeDetailDao {

	public RsvtypeDetailDaoibatis() {
		super(RsvtypeDetail.class);
	}

	@Override
	public void addRsvtypeDetail(RsvtypeDetail rsd) {
		getSqlMapClientTemplate().insert("addRsvtypeDetail", rsd);
	}

	@Override
	public void updateRsvtypeDetail(RsvtypeDetail rsd) {
		getSqlMapClientTemplate().update("updateRsvtypeDetail", rsd);
	}

	@Override
	public RsvtypeDetail getRsvtypeDetail(RsvtypeDetail rsd) {
		return (RsvtypeDetail) getSqlMapClientTemplate().queryForObject("getRsvtypeDetail", rsd);
	}
}
