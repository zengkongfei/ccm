package com.ccm.api.dao.channel.mongodb;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.channel.ChannelHotelConfig;

@Repository("channelHotelConfigDao")
public class ChannelHotelConfigDaoMongo extends MongodbBaseDao<ChannelHotelConfig> {
	protected Class<ChannelHotelConfig> getEntityClass(){
		return ChannelHotelConfig.class;
	}

	/**
	 * 根据channelId查询一个结果
	 * @param channelId
	 * @return
	 */
	public ChannelHotelConfig getChannelHotelConfigByChannelId(String channelId){
		Query query=new Query();
		query.addCriteria(Criteria.where("channelId").in(channelId));
		return this.findOne(query);
	}
	
	/**
	 * 根据channelId更新一条记录
	 * @param channelHotelConfig
	 */
	public void updateChannelHotelConfig(ChannelHotelConfig channelHotelConfig){
		if(channelHotelConfig!=null){
			Query query=new Query();
			query.addCriteria(Criteria.where("channelId").in(channelHotelConfig.getChannelId()));
			this.update(query, channelHotelConfig);
		}
	}
	/**
	 * 根据channelId新增一条记录
	 * @param channelHotelConfig
	 */
	public void addChannelHotelConfig(ChannelHotelConfig channelHotelConfig){
		this.save(channelHotelConfig);
	}
	
}
