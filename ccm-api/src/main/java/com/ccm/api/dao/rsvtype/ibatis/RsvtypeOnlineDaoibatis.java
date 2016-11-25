package com.ccm.api.dao.rsvtype.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.RsvtypeOnlineDao;
import com.ccm.api.model.rsvtype.RsvtypeOnline;

@Repository("rsvtypeOnlineDao")
public class RsvtypeOnlineDaoibatis extends GenericDaoiBatis<RsvtypeOnline, String> implements RsvtypeOnlineDao{

    public RsvtypeOnlineDaoibatis() {
        super(RsvtypeOnline.class);
    }

    @Override
    public List<RsvtypeOnline> searchRsvtypeOnlie(RsvtypeOnline ro) {
        List<RsvtypeOnline> list = getSqlMapClientTemplate().queryForList("searchRsvtypeOnline", ro);
        return list;
    }

    @Override
    public void update(RsvtypeOnline rodata) {
        getSqlMapClientTemplate().update("updateRsvtypeOnline",rodata);
    }

    @Override
    public void updateAvailable(RsvtypeOnline rodata) {
        getSqlMapClientTemplate().update("updateAvailable",rodata);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<RsvtypeOnline> getRsvtypeOnlineByGid(RsvtypeOnline ro) {
		return getSqlMapClientTemplate().queryForList("getRsvtypeOnlineByGid", ro);
	}
}
