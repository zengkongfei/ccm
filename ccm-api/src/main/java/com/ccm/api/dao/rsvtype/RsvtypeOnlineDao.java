package com.ccm.api.dao.rsvtype;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.RsvtypeOnline;

public interface RsvtypeOnlineDao extends GenericDao<RsvtypeOnline, String>{

    List<RsvtypeOnline> searchRsvtypeOnlie(RsvtypeOnline ro);

    void update(RsvtypeOnline rodata);

    void updateAvailable(RsvtypeOnline rodata);

    /**
     * 根据gid和结束日期查询RsvtypeOnline
     */
    List<RsvtypeOnline> getRsvtypeOnlineByGid(RsvtypeOnline ro);
}
