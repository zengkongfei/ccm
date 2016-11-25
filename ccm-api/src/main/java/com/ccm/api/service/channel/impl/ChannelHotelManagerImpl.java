/**
 * 
 */
package com.ccm.api.service.channel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelHotelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Service("channelHotelManager")
public class ChannelHotelManagerImpl extends GenericManagerImpl<ChannelHotel, String> implements ChannelHotelManager {
	@Resource
	private ChannelDao channelDao;

	@Autowired
	private ChannelHotelDao channelHotelDao;

	@Autowired
	public ChannelHotelManagerImpl(ChannelHotelDao channelHotelDao) {
		super(channelHotelDao);
		this.channelHotelDao = channelHotelDao;
	}

	/**
	 * 根据渠道ID，酒店ID删除渠道酒店关系
	 */
	public void logicDeleteChannelHotel(String hotelId, String channelId, Date addTime, Date delTime) {
		ChannelHotel ch = new ChannelHotel();
		ch.setDelFlag(true);
		ch.setLastModifyTime(new Date());
		ch.setUpdatedBy(SecurityHolder.getUserId());
		ch.setHotelId(hotelId);
		ch.setChannelId(channelId);
		ch.setAddTime(addTime);
		ch.setDelTime(delTime);
		channelHotelDao.logicDeleteChannelHotel(ch);
	}

	/**
	 * 通过酒店ID查询渠道酒店表
	 */
	public List<ChannelHotel> getChannelHotelCodeByHotelId(String hotelId) {
		return channelHotelDao.getChannelHotelCodeByHotelId(hotelId);
	}
	
	
	public List<Channel> getChannelsByHotelId(String hotelId){
		List<Channel> channelList = new ArrayList<Channel>();
		List<ChannelHotel> channelHotels = channelHotelDao.getChannelHotelCodeByHotelId(hotelId);
		if(channelHotels != null && channelHotels.size() > 0 ){
			for (ChannelHotel channelHotel : channelHotels) {
				Channel ch = channelDao.get(channelHotel.getChannelId());
				channelList.add(ch);
			}
			Collections.sort(channelList, new Comparator<Channel>() {
				@Override
				public int compare(Channel c1, Channel c2) {
					return c1.getChannelCode().compareTo(c2.getChannelCode());
				}
			});
		}
		return channelList;
	}

	/**
	 * 根据渠道ID，酒店ID保存或更新渠道酒店关系
	 */
	public void saveOrUpdateChannelHotel(ChannelHotel ch) {

		// 判断ch.getChannelId()是否有值,若无值则给个默认渠道ID
		String channelId = ch.getChannelId();
		if (!StringUtils.hasText(channelId)) {
			Channel c = channelDao.getChannelByChannelCode(ChannelCodeEnum.TAOBAO.getName());
			if (c != null) {
				channelId = c.getChannelId();
			}
		}
		// 根据渠道ID,酒店ID查询是否已经存在，若存在则更新，否则添加
		ch.setChannelId(channelId);
		ch.setAddTime(DateUtil.currentDateTime());

		List<ChannelHotel> chList = channelHotelDao.getChannelHotelByHotelIdChannelId(ch);
		if (CommonUtil.isNotEmpty(chList) && chList.size() > 0) {
			ChannelHotel ch2 = chList.get(0);
			ch2.setMatchStatus(ch.getMatchStatus());
			ch2.setChannelStatus(ch.getChannelStatus());
			channelHotelDao.save(ch2);
		} else {
			channelHotelDao.save(ch);
		}
	}

	/**
	 * 通过渠道代码，渠道酒店代码查询酒店ID
	 */
	public String getHotelIdByChannelCodeChannelHotelCode(String channelCode, String channelHotelCode) {
		return channelHotelDao.getHotelIdByChannelCodeChannelHotelCode(channelCode, channelHotelCode);
	}

	/**
	 * 通过渠道代码,酒店ID查询渠道酒店表
	 */
	public ChannelHotel getChannelHotelByHotelIdChannelCode(String channelCode, String hotelId) {
		return channelHotelDao.getChannelHotelByHotelIdChannelCode(channelCode, hotelId);
	}

	@Override
	public void delete(String channelHotelId) {
		channelHotelDao.delete(channelHotelId);
	}

}
