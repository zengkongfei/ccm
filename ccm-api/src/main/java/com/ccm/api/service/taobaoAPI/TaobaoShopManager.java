package com.ccm.api.service.taobaoAPI;

import com.taobao.api.domain.Shop;


/**
 * 店铺定义
 * 
 * @author carr
 * 店铺地址shop+sid.taobao.com
 */
public interface TaobaoShopManager {
	public Shop getShop(String name);
}
