package com.ccm.api.model.constant;

import com.ccm.api.util.PropertiesUtil;
import com.taobao.api.AutoRetryTaobaoClient;
import com.taobao.api.TaobaoClient;

public class TaobaoApi {

	public static String APP_KEY = PropertiesUtil.getProperty("app_key");
    public static String APP_SECRET = PropertiesUtil.getProperty("app_secret");
    public static String APP_PARTNER = PropertiesUtil.getProperty("app_partner");//"2088101568358171";
    public static String APP_SIGN_KEY = PropertiesUtil.getProperty("app_sign_key");//"uxt01uurwxvstkxpmleeok76ezicp8k4";
    public static String SESSION_KEY;
    
    public static String TAOBAO_API_URL = "http://gw.api.taobao.com/router/rest";
    static{
        if("1021754351".equals(TaobaoApi.APP_KEY)){ //沙箱地址
            TAOBAO_API_URL = "http://gw.api.tbsandbox.com/router/rest";
        }
    }
    
    public static final String AUTHORIZE_URL = "http://container.open.taobao.com/container?appkey=" + APP_KEY;
    /**
     * 超级收款网关地址
     */
    public static final String TAOBAO_PAY_URL = "https://mapi.alipay.com/gateway.do";
    
    public static final TaobaoClient taobaoClient = new AutoRetryTaobaoClient(TaobaoApi.TAOBAO_API_URL, TaobaoApi.APP_KEY, TaobaoApi.APP_SECRET);
    
    public static final Integer TAOBAO_ORDER_SEND_STATUS_NOSEND = 0; //未推送
    public static final Integer TAOBAO_ORDER_SEND_STATUS_SENT = 1; //已推送
    
}
