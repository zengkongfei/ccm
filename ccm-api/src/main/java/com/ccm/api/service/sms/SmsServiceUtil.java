package com.ccm.api.service.sms;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * webService 方式发送短信
 * @author edwin
 *
 */
public class SmsServiceUtil {
	
	private static String SUCCESS = "发送成功";   //表示发送成功
	private static String USERID = "rpm";      //你的用户名
	private static String PASSWORD = "wr@459M";  //你的密码
	//消息对应Map
	private static Map<String, String> resultMap = new HashMap<String, String>();
	
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
		resultMap.put("-13", "文件传输错误");
		resultMap.put("000", "发送成功！");
		resultMap.put("0808191630319344", "发送成功！");
	}
	
	/**
	 * 发送短信的方法
	 * @param mobiles
	 * @param msg
	 * @return
	 */
	public static String sendSms(String mobiles,String msg) {

		String time = "";
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getSoapInputStream(USERID, PASSWORD, mobiles, msg,time);
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("SendMessagesResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			
			if(resultMap.containsKey(result)){
				return resultMap.get(result);
			}
		} catch (Exception e) {
			System.out.print("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}
		return SUCCESS;
	}
	

	private static String getSoapSmssend(String userid, String pass, String mobiles,
			String msg, String time) {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<SendMessages xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid + "</uid>" + "<pwd>" + pass + "</pwd>" + "<tos>"
					+ mobiles + "</tos>" + "<msg>" + msg + "</msg>" + "<otime>"
					+ time + "</otime>" + "</SendMessages>" + "</soap:Body>"
					+ "</soap:Envelope>";
			return soap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static InputStream getSoapInputStream(String userid, String pass,
			String mobiles, String msg, String time) throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapSmssend(userid, pass, mobiles, msg, time);
			if (soap == null) {
				return null;
			}
			try {
				URL url = new URL("http://service2.winic.org:8003/Service.asmx");
				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length",
						Integer.toString(soap.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/SendMessages\"");
				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception ex) {
				System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}

	private String getSoapUserInfo(String userid, String pass) {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<GetUserInfo xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid + "</uid>" + "<pwd>" + pass + "</pwd>"
					+ "</GetUserInfo>" + "</soap:Body>" + "</soap:Envelope>";
			return soap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private InputStream getUserInfoInputStream(String userid, String pass)
			throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapUserInfo(userid, pass);
			if (soap == null) {
				return null;
			}
			try {
				URL url = new URL("http://service2.winic.org/Service.asmx");
				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length",
						Integer.toString(soap.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/GetUserInfo\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception ex) {
				System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}

	public String GetInfo(String userid, String pass) {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getUserInfoInputStream(userid, pass);
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("GetUserInfoResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			System.out.print("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}
	}

	private String getVoiceSend(String userid, String pwd, String txtPhone,
			String vtxt, String Svmode, byte[] buffer, String svrno,
			String str_time, String end_time) throws Exception {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<SendVoice xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid
					+ "</uid>"
					+ "<pwd>"
					+ pwd
					+ "</pwd>"
					+ "<vto>"
					+ txtPhone
					+ "</vto>"
					+ "<vtxt>"
					+ vtxt
					+ "</vtxt>"
					+ "<mode>"
					+ Svmode
					+ "</mode>"
					+ "<FileBytes>"
					+ buffer
					+ "</FileBytes>"
					+ "<svrno>"
					+ svrno
					+ "</svrno>"
					+ "<str_time>"
					+ str_time
					+ "</str_time>"
					+ "<end_time>"
					+ end_time
					+ "</end_time>"
					+ "</SendVoice>"
					+ "</soap:Body>" + "</soap:Envelope>";
			return soap;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private InputStream getVoiceInputStream(String userid, String pwd,
			String txtPhone, String vtxt, String Svmode, String vfbtye,
			String svrno, String str_time, String end_time) throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			byte[] buffer1 = null;
			if (Svmode == "3") {
				String vPath = vfbtye;
				if (vPath == "") {
					String xx = "请选择语音文件。格式为.WAV 大小不要超过 2M";
					return null;
				} else {
					int i = vPath.lastIndexOf(".");
					// 取得文档扩展名
					String newext = vPath.substring(i + 1).toLowerCase();
					if (!newext.equals("wav")) {
						String xx = "语音文件格式不正确";
						return null;
					}
				}

			} else {
				buffer1 = new byte[0];
			}

			String soap = getVoiceSend(userid, pwd, txtPhone, vtxt, Svmode,
					buffer1, svrno, str_time, end_time);
			if (soap == null) {
				return null;
			}
			try {
				URL url = new URL("http://service2.winic.org/Service.asmx");

				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length",
						Integer.toString(soap.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/SendVoice\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception ex) {
				System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}

	public String sendVoice(String userid, String pwd, String txtPhone,
			String vtxt, String Svmode, String vfbtye1, String svrno,
			String str_time, String end_time) {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getVoiceInputStream(userid, pwd, txtPhone, vtxt,
					Svmode, vfbtye1, svrno, str_time, end_time);
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("SendVoiceResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			System.out.print("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}
	}

	public static void main(String[] args) {
		// 发送短信
		String mobiles = "13524374816"; // 对方接收的手机号
		String msg = "JAVA测试短信通过2014-05-27"; // 内容

		System.out.println(SmsServiceUtil.sendSms(mobiles, msg));
	}
}
