package com.ccm.api.service.rsvtype.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.rsvtype.RsvtypeOnlineDao;
import com.ccm.api.model.rsvtype.RsvtypeOnline;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.rsvtype.RsvtypeOnlineManager;

@Service("rsvtypeOnlineManager")
public class RsvtypeOnlineManagerImpl extends GenericManagerImpl<RsvtypeOnline, String> implements RsvtypeOnlineManager {

	@Resource
	private RsvtypeOnlineDao rsvtypeOnlineDao;
	
	@Resource
    public void setRsvtypeOnlineDao(RsvtypeOnlineDao rsvtypeOnlineDao) {
        this.dao = rsvtypeOnlineDao;
        this.rsvtypeOnlineDao = rsvtypeOnlineDao;
    }

    @Override
    public List<RsvtypeOnline> searchRsvtypeOnlie(RsvtypeOnline ro) {
        return rsvtypeOnlineDao.searchRsvtypeOnlie(ro);
    }

    @Override
    public void update(RsvtypeOnline rodata) {
        rsvtypeOnlineDao.update(rodata);
    }

    @Override
    public void updateAvailable(RsvtypeOnline rodata) {
        rsvtypeOnlineDao.updateAvailable(rodata);
    }
}
