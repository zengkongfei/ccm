package com.ccm.api.service.rsvtype.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.rsvtype.RsvtypeDetailDao;
import com.ccm.api.model.rsvtype.RsvtypeDetail;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.rsvtype.RsvtypeDetailManager;

@Service("rsvtypeDetailManager")
public class RsvtypeDetailManagerImpl extends GenericManagerImpl<RsvtypeDetail, String> implements RsvtypeDetailManager {

	@Autowired
	private RsvtypeDetailDao rsvtypeDetailDao;

}
