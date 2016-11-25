/**
 * 
 */
package com.ccm.api.model.channel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Water
 * 
 */
public class ChannelHotelConfig extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416126012568185994L;
	
	/***以下为ChannelHotelConfig表字段*/
	private String channelHotelConfigId;//主键
	private String channelHotelConfig;//酒店配置
	private String channelRoomTypeConfig;//房型配置
	private String channelRateConfig;//房价配置
	private String channelId;//渠道代码
	private Integer pushMethod;//推送方法：单独推送=1  全量推送=2    页面单选  默认1 
	private Boolean ariSwitch;//即时推送ARI开关,默认为false
	
	public Boolean getAriSwitch() {
		if(null==ariSwitch){
			return false;
		}
		return ariSwitch;
	}
	public void setAriSwitch(Boolean ariSwitch) {
		this.ariSwitch = ariSwitch;
	}
	public String getChannelRateConfig() {
		return channelRateConfig;
	}
	public void setChannelRateConfig(String channelRateConfig) {
		this.channelRateConfig = channelRateConfig;
	}
	public Integer getPushMethod() {
		return pushMethod;
	}
	public void setPushMethod(Integer pushMethod) {
		this.pushMethod = pushMethod;
	}
	public String getChannelRoomTypeConfig() {
		return channelRoomTypeConfig;
	}
	public void setChannelRoomTypeConfig(String channelRoomTypeConfig) {
		this.channelRoomTypeConfig = channelRoomTypeConfig;
	}
	public String getChannelHotelConfigId() {
		return channelHotelConfigId;
	}
	public void setChannelHotelConfigId(String channelHotelConfigId) {
		this.channelHotelConfigId = channelHotelConfigId;
	}
	public String getChannelHotelConfig() {
		return channelHotelConfig;
	}
	public void setChannelHotelConfig(String channelHotelConfig) {
		this.channelHotelConfig = channelHotelConfig;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	@Override
	public String toString() {
		return "ChannelHotelConfig [channelHotelConfigId=" + channelHotelConfigId + ", channelHotelConfig="
				+ channelHotelConfig + ", channelRoomTypeConfig=" + channelRoomTypeConfig + ", channelRateConfig="
				+ channelRateConfig + ", channelId=" + channelId + ", pushMethod=" + pushMethod + ", ARISwitch="
				+ ariSwitch + "]";
	}
	
}
