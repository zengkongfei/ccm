package com.ccm.api.service.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送短信基础类
 * 
 * @author edwin
 * 
 */
public class SmsBaseUtil {
	
	public static String SUCCESS = "发送成功";   //表示发送成功
	public static String FAILURE = "发送失败";   //表示发送失败

	
	//消息对应Map
	public static Map<String, String> resultMap = new HashMap<String, String>();
	
	static{
		resultMap.put("-01", "当前账号余额不足！");
		resultMap.put("-02", "当前用户ID错误！");
		resultMap.put("-03", "当前密码错误！");
		resultMap.put("-04", "参数不够或参数内容的类型错误！");
		resultMap.put("-05", "手机号码格式不对！（目前还未实现）");
		resultMap.put("-06", "短信内容编码不对！（目前还未实现）");
		resultMap.put("-07", "短信内容含有敏感字符！（目前还未实现）");
		resultMap.put("-08", "无接收数据（目前还未实现）");
		resultMap.put("-09", "系统维护中.. （目前还未实现）");
		resultMap.put("-10", "手机号码数量超长！ （目前还未实现）");
		resultMap.put("-11", "短信内容超长！（70个字符）（目前不会返回-11，超长会分成多条发送）");
		resultMap.put("-12", "其它错误！");
		resultMap.put("-14", "用户名或者密码为空!");
		resultMap.put("000", SUCCESS);
		resultMap.put("111", FAILURE);
		
	}
	
	
	/**
	 * 发送短信
	 * @param mobiles  手机号(多个中间用逗号隔开)
	 * @param content 内容
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String sendSms(String mobiles, String content, String x_id,String x_pwd)
			throws UnsupportedEncodingException {
		HttpURLConnection httpconn = null;
		String result = "-20";
//		Integer x_ac = 10;// 发送信息
		String memo = content.trim() ;
		StringBuilder sb = new StringBuilder();
		sb.append("http://service.winic.org/sys_port/gateway/?");
		sb.append("id=").append(x_id);
		sb.append("&pwd=").append(x_pwd);
		sb.append("&to=").append(mobiles);
		sb.append("&content=").append(URLEncoder.encode(memo, "gb2312")); // 注意乱码的话换成gb2312编码
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					httpconn.getInputStream()));
			result = rd.readLine();
			rd.close();
			
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 发送多个号码
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendMoreSms(String[] mobiles,String content) throws UnsupportedEncodingException{
		//保存出错信息
		StringBuffer erorMsg = new StringBuffer("");
		Map<String, String> errorMap = new HashMap<String, String>();
		if(mobiles!=null&&mobiles.length>0){
			String x_id = "rpm";
			String x_pwd = "wr@459M";
			for (String mobile : mobiles) {
				
				String result = sendSms(mobile, content, x_id, x_pwd);
				//如果存在不成功的
				if(!SUCCESS.equals(result)){
					errorMap.put(mobile, result);
				}
			}
		}
		
		//如果存在错误的发送记录
		if(errorMap.size()>0){
			for (String key : errorMap.keySet()) {
				erorMsg.append(key+ ":" + errorMap.get(key)+";");
			}
			return erorMsg.toString();
		}

		return SUCCESS;
	}

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String content = "╰叶子的飘落，是因为风的追逐，还是因为，树的不挽留╮";
		
		String mobiles2 = "13524374816";
		String x_id = "rpm";
		String x_pwd = "wr@459M";
		System.out.println(SmsBaseUtil.sendSms(mobiles2, content,x_id,x_pwd));
	}
	
	
}
