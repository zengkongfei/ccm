package com.ccm.api.service.ads.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.OnlineUserDao;
import com.ccm.api.model.ads.OnlineUser;
import com.ccm.api.service.ads.OnlineUserManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
@Repository("onlineUserManager")
public class OnlineUserManagerImpl extends GenericManagerImpl<OnlineUser, String> implements OnlineUserManager{
    
    private OnlineUserDao onlineUserDao;
    
    @Resource
    public void setOnlineUserDao(OnlineUserDao onlineUserDao) {
        this.onlineUserDao = onlineUserDao;
        this.dao = onlineUserDao;
    }

    @Override
    public List<OnlineUser> searchOnlineUser(OnlineUser ou) {
        return onlineUserDao.searchOnlineUser(ou);
    }

    @Override
    public boolean isPushByChainCode(String chainCode) {
        List<OnlineUser> ouList = onlineUserDao.getAll();
        if(ouList != null && ouList.size() > 0){
            for (OnlineUser ou : ouList) {
                if(ou.getChainCode().equals(chainCode)){
                    return ou.isPush();
                }
            }
        }
        return false;
    }
}
