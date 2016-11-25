package com.ccm.api.dao.ads;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ads.OnlineUser;


public interface OnlineUserDao extends GenericDao<OnlineUser, String> {

    List<OnlineUser> searchOnlineUser(OnlineUser ou);
	

}
