package com.ccm.api.dao.system.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.system.BookChannelDao;
import com.ccm.api.model.system.BookChannel;
@Repository("BookChannelDao")
public class BookChannelDaoImpl extends GenericDaoiBatis<BookChannel, String> implements BookChannelDao{

    public BookChannelDaoImpl() {
        super(BookChannel.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BookChannel> getBookChannelsByUserId(String userId) {
        return this.getSqlMapClientTemplate().queryForList("getBookChannelsByUserId", userId);
    }

}
