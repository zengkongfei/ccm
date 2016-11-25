package com.ccm.api.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class ApacheHttpProxyClient {

	private static Logger log = Logger.getLogger(ApacheHttpProxyClient.class
			.getName());

	public static String post(String url, Map<String, String> params,boolean https) {
		HttpClient httpclient = new DefaultHttpClient();
		if(https){
			httpclient=useSSL(httpclient);
			}
		String body = null;

		log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String get(String url,boolean https) {
		HttpClient httpclient = new DefaultHttpClient();
		if(https){
			httpclient=useSSL(httpclient);
			}
		String body = null;
		log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	private static String invoke(HttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

		log.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
		log.info(charset);
		String body = null;
		try {
			body = EntityUtils.toString(entity);
			log.info(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	private static HttpResponse sendRequest(HttpClient httpclient,
			HttpUriRequest httpost) {
		log.info("execute post...");
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	public static JSONObject postJson(String url, String json,boolean https) {
		HttpClient httpclient = new DefaultHttpClient();
		if(https){
			httpclient=useSSL(httpclient);
			}
		HttpPost post = new HttpPost(url);
		log.info("create httppost:" + url);
		String body = null;
		try {
			ByteArrayEntity b=new ByteArrayEntity(json.getBytes());
			b.setContentType("application/json");// 发送json数据需要设置contentType
			b.setContentEncoding("UTF-8");
			post.setEntity(b);
			body = invoke(httpclient, post);
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return JSONObject.parseObject(body);
	}


	public static HttpClient useSSL(HttpClient httpClient) {

		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("TLS");
			// 信任所有证书
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sslcontext.init(null, new TrustManager[] { tm }, null);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext);
		Scheme sch = new Scheme("https", socketFactory, 8443);
		httpClient.getConnectionManager().getSchemeRegistry().register(sch);
		return httpClient;
	}
}