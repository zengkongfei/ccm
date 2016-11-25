/**
 * 
 */
package com.ccm.api.dao.ads.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.OnlineUserDao;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.model.ads.OnlineUser;

@Repository("onlineUserDao")
public class OnlineUserDaoiBatis extends GenericDaoiBatis<OnlineUser, String> implements OnlineUserDao{


	public OnlineUserDaoiBatis() {
		super(OnlineUser.class);
	}

    @Override
    public List<OnlineUser> searchOnlineUser(OnlineUser ou) {
        return getSqlMapClientTemplate().queryForList("searchOnlineUser",ou);
    }

}
