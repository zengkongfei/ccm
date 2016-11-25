package com.ccm.api.service.system;

import java.util.List;

import com.ccm.api.model.system.BookChannel;
import com.ccm.api.service.base.GenericManager;


public interface BookChannelManage extends GenericManager<BookChannel, String>{
    List<BookChannel> getBookChannelsByUserId(String userId);
    
    boolean checkBookChannelByUserIdAndCode(String userId,String code);
}
