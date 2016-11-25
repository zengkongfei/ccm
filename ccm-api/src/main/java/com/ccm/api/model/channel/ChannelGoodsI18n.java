package com.ccm.api.model.channel;

import java.io.Serializable;

import com.ccm.api.model.base.BaseObject;

public class ChannelGoodsI18n extends BaseObject implements Serializable {

	private static final long serialVersionUID = 136860410321544217L;

	private String channelGoodsMId;// 序号
	private String channelGoodsId;// 渠道序号
	private String title;// 宝贝名称
	private String guide;// 购买须知
	private String description;// 宝贝描述
	private String receipt_other_type_desc;// 发票类型为其他时的发票描述,不能超过30个字
	private String receipt_info;// 发票说明，不能超过100个字

	public String getChannelGoodsMId() {
		return channelGoodsMId;
	}

	public void setChannelGoodsMId(String channelGoodsMId) {
		this.channelGoodsMId = channelGoodsMId;
	}

	public String getChannelGoodsId() {
		return channelGoodsId;
	}

	public void setChannelGoodsId(String channelGoodsId) {
		this.channelGoodsId = channelGoodsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceipt_other_type_desc() {
		return receipt_other_type_desc;
	}

	public void setReceipt_other_type_desc(String receipt_other_type_desc) {
		this.receipt_other_type_desc = receipt_other_type_desc;
	}

	public String getReceipt_info() {
		return receipt_info;
	}

	public void setReceipt_info(String receipt_info) {
		this.receipt_info = receipt_info;
	}

	@Override
	public String toString() {
		return "ChannelGoodsI18n [channelGoodsMId=" + channelGoodsMId + ", channelGoodsId=" + channelGoodsId + ", title=" + title + ", guide=" + guide + ", description=" + description + ", receipt_other_type_desc=" + receipt_other_type_desc + ", receipt_info=" + receipt_info + "]";
	}

}
