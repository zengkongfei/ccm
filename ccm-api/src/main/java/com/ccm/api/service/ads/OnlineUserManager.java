package com.ccm.api.service.ads;

import java.util.List;

import com.ccm.api.model.ads.OnlineUser;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 */
public interface OnlineUserManager extends GenericManager<OnlineUser, String> {

    List<OnlineUser> searchOnlineUser(OnlineUser ou);

    boolean isPushByChainCode(String chainCode);
}