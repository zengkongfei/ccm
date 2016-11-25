package com.ccm.api.service.data;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.channel.AdsGoods;

public class AdsgoodsTest {
	public static void main(String ... args){
		AdsGoods adsGoods=new AdsGoods();
		adsGoods.setChannelCode("TAOBAO");
		adsGoods.setForcePush(true);
		adsGoods.setHotelCode("SIHJI");
		adsGoods.setHotelId("af1567e1248a49cc8f87500dfa249a15");
		adsGoods.setRatePlanCode("WHL03");
		adsGoods.setRoomTypeCode("1KDXS");
		AdsGoods adsGoods2=JSONObject.parseObject(JSONObject.toJSONString(adsGoods), AdsGoods.class);
		System.out.println(JSONObject.toJSONString(adsGoods));
		System.out.println(adsGoods2.toString());
	}
}
