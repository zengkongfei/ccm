package com.ccm.api.dao.system;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.system.BookChannel;

public interface BookChannelDao extends GenericDao<BookChannel, String>{

    List<BookChannel> getBookChannelsByUserId(String userId);

}
