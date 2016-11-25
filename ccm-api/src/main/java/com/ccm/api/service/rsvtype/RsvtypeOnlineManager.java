package com.ccm.api.service.rsvtype;

import java.util.List;

import com.ccm.api.model.rsvtype.RsvtypeOnline;
import com.ccm.api.service.base.GenericManager;

public interface RsvtypeOnlineManager extends GenericManager<RsvtypeOnline, String>{

    List<RsvtypeOnline> searchRsvtypeOnlie(RsvtypeOnline ro);

    void update(RsvtypeOnline rodata);

    void updateAvailable(RsvtypeOnline rodata);

}
