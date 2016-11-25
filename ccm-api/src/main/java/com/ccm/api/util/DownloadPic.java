package com.ccm.api.util;
/**
 * 
 */


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Jenny
 * 
 */
public class DownloadPic {
	public static void main(String[] args) {
		try {
			System.out.println(DownloadPic.getUrlDetail("http://www.baidu.com", true));
			saveUrlFile("http://www.baidu.com/img/baidu_jgylogo3.gif", "D:\\1.gif");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取网络文件，转存到fileDes中，fileDes需要带文件后缀名
	public static void saveUrlFile(String fileUrl, String fileDes) throws Exception {
		File toFile = new File(fileDes);
		if (toFile.exists()) {
			// throw new Exception("file exist");
			return;
		}
		toFile.createNewFile();
		FileOutputStream outImgStream = new FileOutputStream(toFile);
		outImgStream.write(getUrlFileData(fileUrl));
		outImgStream.close();
	}

	// 获取链接地址文件的byte数据
	public static byte[] getUrlFileData(String fileUrl) throws Exception {
	    InputStream cin = null;
	    if(fileUrl.indexOf("http:") > -1){
	        URL url = new URL(fileUrl);
	        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	        httpConn.connect();
	        cin = httpConn.getInputStream();
	    }else{
	        cin = new FileInputStream(new File(fileUrl));
	    }
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = cin.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		cin.close();
		byte[] fileData = outStream.toByteArray();
		outStream.close();
		return fileData;
	}

	// 获取链接地址的字符数据，wichSep是否换行标记
	public static String getUrlDetail(String urlStr, boolean withSep) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.connect();
		InputStream cin = httpConn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(cin, "UTF-8"));
		StringBuffer sb = new StringBuffer();
		String rl = null;
		while ((rl = reader.readLine()) != null) {
			if (withSep) {
				sb.append(rl).append(System.getProperty("line.separator"));
			} else {
				sb.append(rl);
			}
		}
		return sb.toString();
	}

}
