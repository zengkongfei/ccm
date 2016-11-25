package com.ccm.api.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.system.BookChannelDao;
import com.ccm.api.model.system.BookChannel;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.system.BookChannelManage;
@Service("bookChannelManage")
public class BookChannelManageImpl extends GenericManagerImpl<BookChannel, String> implements BookChannelManage{
@Autowired    
private BookChannelDao bookChannelDao;

public BookChannelDao getBookChannelDao() {
    return bookChannelDao;
}
@Autowired  
public void setBookChannelDao(BookChannelDao bookChannelDao) {
    this.dao = bookChannelDao;
    this.bookChannelDao = bookChannelDao;
}
    public void remove(String id){
        bookChannelDao.softDelete(id);
    }
    @Override
    public List<BookChannel> getBookChannelsByUserId(String userId) {
        return bookChannelDao.getBookChannelsByUserId(userId);
    }
    @Override
    public boolean checkBookChannelByUserIdAndCode(String userId, String code) {
        if(StringUtils.hasText(userId) && StringUtils.hasText(code)){
            List<BookChannel> bcList = getBookChannelsByUserId(userId);
            for (BookChannel bookChannel : bcList) {
                if(code.equals(bookChannel.getCode())){
                    return true;
                }
            }
        }
        return false;
    }
}
