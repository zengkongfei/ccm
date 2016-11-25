package com.ccm.api.service.taobaoAPI.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.constant.TaobaoApi;
import com.ccm.api.service.taobaoAPI.TaobaoShopManager;
import com.taobao.api.domain.Shop;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.response.ShopGetResponse;

@Service("taobaoShopManager")
public class TaobaoShopManagerImpl implements TaobaoShopManager {

	private Log log = LogFactory.getLog(TaobaoShopManager.class);

	@Override
	public Shop getShop(String name) {
		ShopGetRequest req=new ShopGetRequest();
		req.setFields("sid");
		req.setNick(name);
		ShopGetResponse  response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req);
			if(null!=response.getErrorCode()){
				if(null==response.getSubMsg()){
					throw new BizException("TB00001","淘宝接口调用失败，错误信息为null");
				}
				throw new BizException("TB00001",response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":")+1));
			}
			return response.getShop();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001","淘宝api接口调用失败!");
		}
	}


}
