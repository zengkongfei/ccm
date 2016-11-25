package com.ccm.api.service.pay.alipay.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.2
 *日期：2011-03-17
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner ;
	
	
	// 交易安全检验码，由数字和字母组成的32位字符串
	public static String key;
	
	// 签约支付宝账号或卖家收款支付宝帐户
	public static String seller_email;
	
	// 支付宝服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	// 必须保证其地址能够在互联网中访问的到"http://180.169.85.6:8580/frontend/pay/alipay/notify_url.jsp"
	public static String notify_url ;
	
	// 当前页面跳转后的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	// 域名不能写成http://localhost/create_direct_pay_by_user_jsp_utf8/return_url.jsp ，否则会导致return_url执行无效
	public static String return_url;

	// 支付宝服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	// 必须保证其地址能够在互联网中访问的到
	public static String refundNotify_url;
	
	public static String authReturn_url;
	
	//无线支付回调页面
	public static String wap_pay_return_url;
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志路径
	public static String log_path = "D:\\alipay_log\\alipay_log_" + System.currentTimeMillis()+".txt";;

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "UTF-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	public static HashMap<String, String> resultCodes = new HashMap<String, String>();

	/**支付宝帐号*/
    public static String bindAlipayAccountNotify_url;


    public static String quickBindAuthReturn_url;


	static{
		
			try {
				InputStream inputStream =AlipayConfig.class.getClassLoader().getResourceAsStream("payConfig.properties");
				
				Properties propertie = new Properties();
				propertie.load(inputStream);
				partner = propertie.getProperty("partner");
				key = propertie.getProperty("key");
				seller_email = propertie.getProperty("seller_email");
				notify_url = propertie.getProperty("notify_url");
				return_url = propertie.getProperty("return_url");
				authReturn_url = propertie.getProperty("authReturn_url");
				refundNotify_url = propertie.getProperty("refundNotify_url");
				wap_pay_return_url = propertie.getProperty("alipay_wap_pay_backcall_url");
				bindAlipayAccountNotify_url = propertie.getProperty("bindAlipayAccountNotify_url");
				quickBindAuthReturn_url = propertie.getProperty("quickBindAuthReturn_url");
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("配置文件 payConfig.properties不存在!");
	        }
			
			resultCodes.put("WHOLE_DETAIL_FORBID_REFUND", "整条退款明细都禁止退款");
			resultCodes.put("TRADE_HAS_CLOSED", "交易已关闭，不允许退款");
			resultCodes.put("TRADE_HAS_FINISHED", "交易已结束，不允许退款");
			resultCodes.put("REFUND_TRADE_FEE_ERROR", "退交易金额不合法 ");
			resultCodes.put("TXN_RESULT_ACCOUNT_STATUS_NOT_VALID", "卖家账户被冻结");
			resultCodes.put("TXN_RESULT_ACCOUNT_BALANCE_NOT_ENOUGH", "卖家账户余额不足");
			resultCodes.put("REFUND_AMOUNT_NOT_VALID", "退款金额不合法");
			resultCodes.put("REFUND_CHARGE_FEE_ERROR", "退收费金额不合法"); 
			resultCodes.put("REASON_REFUND_CHARGE_ERR", "退收费失败"); 
			resultCodes.put("RESULT_AMOUNT_NOT_VALID", "退收费金额错误 ");
			resultCodes.put("SUCCESS", "退款成功");
	}
}
