/**
 * 
 */
package com.ccm.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Jenny
 * 
 */
public class ReadDataFromPost {

	private static Log log = LogFactory.getLog(ReadDataFromPost.class);

	public static String getMessage(HttpServletRequest request) {
		String message = "";
		try {
			String encoding = request.getCharacterEncoding();
			if (encoding == null) {
				encoding = "UTF-8";
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), encoding));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			message = sb.toString();
		} catch (IOException e) {
			log.info("接收文件流失败！");
			log.info(e);
		}

		return message;
	}

}
