package com.ccm.api.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class PushDataUtil {
	private static int CONNECTION_TIMEOUT = 5000;
	private static int READTIME_OUT = 30000;

	public static void main(String[] args) {
		// try {
		// ArrayList list = new ArrayList();
		// Map map = new HashMap();
		//
		// map.put("name", "Cow");
		// map.put("price", "$100.00");
		// list.add(map);
		//
		// map = new HashMap();
		// map.put("name", "Eagle");
		// map.put("price", "$59.99");
		// list.add(map);
		//
		// map = new HashMap();
		// map.put("name", "Shark");
		// map.put("price", "$3.99");
		// list.add(map);
		// // sendXml("http://xxx.do",
		// //
		// "/src/main/java/com/ccm/b2b/webapp/action/system/post/veloCity.xml",
		// // list);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// String str =
		// postData("http://10.2.11.23/GetProductCode.aspx?hotelCode=WASBT&roomTypeCode=GENR&rateCode=LOS",
		// "");
		// System.out.println("test:" + str);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// testCancelOrder();
		// testAdsOrder();
		// Long sTime = System.currentTimeMillis();
		// testCancelOrder();
		// System.out.println((System.currentTimeMillis()-sTime));
	}

	public static void testAdsOrder() {
		try {
			postData(
					"http://ccmtest.localhost.com/adsOrder.do",
					"<?xml version=\"1.0\" encoding=\"Gb2312\"?><StayHistoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/\"><StayHistoryNotification><StayInfoList><HotelReference chainCode=\"JLG\" hotelCode=\"078\"><!--chainCode为畅联提供的集团代码，hotelCode为CRS的酒店代码--><StayInfo xmlns=\"http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/\"><StayInfoDetail><ChannelResID>TAOBAO333233</ChannelResID><!--渠道订单号--><ConfirmationNo>C140670032</ConfirmationNo><!--CRS订单号--><PmsResID>F14C110199</PmsResID><Status>CHECKED OUT</Status><GuestFirstName>WU</GuestFirstName><GuestLastName>FUMENG</GuestLastName><Arrival>2014-03-11T17:08:09</Arrival><Departure>2014-03-12T09:03:41</Departure><RoomType>ST</RoomType><RateCode>CNS-TJ</RateCode><RoomNo>1516 </RoomNo><Channel>TAOBAO</Channel><!--畅联提供渠道代码--><ResDate>2014-03-11T16:22:07</ResDate><Remark/></StayInfoDetail></StayInfo></HotelReference></StayInfoList></StayHistoryNotification></StayHistoryRequest>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testCancelOrder() {
		try {
			postData("http://www.youtube.com", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<CancelRQ>" + "<AuthenticationToken>" + "<Username>taobao</Username>" + "<Password>taobao</Password>" + "<CreateToken>taobao125484778-1387789907859</CreateToken>" + "</AuthenticationToken>" + "<OrderId>32345737</OrderId>" + "<Reason>reason</Reason>" + "</CancelRQ>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testCreateOrder() {
		try {
			postData("http://ccmtest.localhost.com/order_make.do", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><BookRQ><AuthenticationToken>	<Username>taobao</Username>	<Password>taobao</Password>	<CreateToken>taobao1387784033263-1387784033266</CreateToken></AuthenticationToken>" + "<TaoBaoOrderId>1387784033275</TaoBaoOrderId>" + "<TaoBaoHotelId>11455303375</TaoBaoHotelId>" + "<HotelId>2014022720074840190</HotelId>" + "<TaoBaoRoomTypeId>11494257375</TaoBaoRoomTypeId>" + "<RoomTypeId>2014022113222325473</RoomTypeId>" + "<TaoBaoRatePlanId>1996299375</TaoBaoRatePlanId>" + "<RatePlanCode>ZBAR</RatePlanCode>" + "<TaoBaoGid>1234567890</TaoBaoGid>" + "<CheckIn>2014-03-08</CheckIn>" + "<CheckOut>2014-03-09</CheckOut>"
					+ "<EarliestArriveTime>2014-03-08 20:00:00</EarliestArriveTime>" + "<LatestArriveTime>2014-03-08 22:00:00</LatestArriveTime>" + "<RoomNum>2</RoomNum><TotalPrice>63850</TotalPrice><ContactName>测试联系人</ContactName><ContactTel>13920682209</ContactTel>" + "<PaymentType>5</PaymentType>" + "<DailyInfos>" + "<DailyInfo>" + "<Day>2014-03-08</Day>" + "<Price>17800</Price>" + "</DailyInfo>" + "</DailyInfos>" + "<OrderGuests>" + "<OrderGuest>" + "<Name>入住人1</Name>" + "<RoomPos>1</RoomPos>" + "</OrderGuest>" + "<OrderGuest>" + "<Name>入住人2</Name>" + "<RoomPos>2</RoomPos>" + "</OrderGuest>" + "</OrderGuests>" + "<Comment>测试</Comment><Extensions>{\"key\":\"value\"}</Extensions></BookRQ>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getVelocityStr(String tmp, Object obj) throws Exception {
		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.description", "Velocity Classpath Resource Loader");
		p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");

		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		VelocityContext context = new VelocityContext();
		context.put("obj", obj);
		Template t = ve.getTemplate(tmp);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}

	/**
	 * http post 推送数据
	 * 
	 * @param postUrl
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String postData(String postUrl, String data) throws Exception {
		// System.out.println("postUrl:" + postUrl + " data:" + data);
		if (data == null) {
			return "";
		}
		// System.out.println("data:" + data);
		URL url = new URL(postUrl);
		// data = URLEncoder.encode(data, "UTF-8");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("content-type", "text/xml");
		urlConnection.setRequestMethod("POST");
		urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
		urlConnection.setReadTimeout(READTIME_OUT);
		BufferedOutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
		out.write(data.getBytes());
		out.flush();
		out.close();
		int code = urlConnection.getResponseCode();
		StringBuffer response = new StringBuffer();
		if (code == HttpURLConnection.HTTP_OK) {
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				response.append(readLine);
			}
			br.close();
			is.close();
			// System.out.println("res:" + response);
		}
		urlConnection.disconnect();
		return response.toString();
	}

	/** 返回是否成功返回状态 */
	public static boolean postDataIsOK(String postUrl, String data) throws Exception {
		// System.out.println("postUrl:" + postUrl + " data:" + data);
		if (CommonUtil.isEmpty(postUrl) || data == null) {
			return false;
		}
		URL url = new URL(postUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("content-type", "text/xml");
		urlConnection.setRequestMethod("POST");
		urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
		urlConnection.setReadTimeout(READTIME_OUT);
		BufferedOutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
		out.write(data.getBytes());
		out.flush();
		out.close();
		int code = urlConnection.getResponseCode();
		// System.out.println("ResponseCode:"+code);
		urlConnection.disconnect();
		return code == HttpURLConnection.HTTP_OK;
	}

	public static String postDataResponse(String postUrl, String data) throws Exception {
		if (data == null) {
			return "";
		}
		URL url = new URL(postUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("content-type", "text/xml");
		urlConnection.setRequestMethod("POST");
		urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
		urlConnection.setReadTimeout(READTIME_OUT);
		BufferedOutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
		out.write(data.getBytes());
		out.flush();
		out.close();
		StringBuffer response = new StringBuffer();
		InputStream is = urlConnection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			response.append(readLine);
		}
		br.close();
		is.close();
		urlConnection.disconnect();
		return response.toString();
	}

}
