package com.ccm.api.service.fax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

public class FaxUtil {
	Logger log = Logger.getLogger(FaxUtil.class);
	public final static int BASIC = 0;
	public final static int MD5 = 1;
	public final static int DES = 2;

	private String userid;
	private String pass;
	private int type;
	private String deskey;

	public FaxUtil(String userid, String pass, int type) {
		this(userid, pass, type, null);
	}

	public FaxUtil(String userid, String pass, int type, String deskey) {
		this.userid = userid;
		this.pass = pass;
		this.type = type;
		this.deskey = deskey;
	}

	private String getAuth() {
		BASE64Encoder en = new BASE64Encoder();

		if (type == BASIC) {
			return "basic "
					+ en.encode((userid + ":" + pass).getBytes()).replaceAll(
							"\r\n", "");
		} else if (type == MD5) {
			return "md5 "
					+ en.encode((userid + ":" + md5(pass)).getBytes())
							.replaceAll("\r\n", "");
		} else if (type == DES) {

			if (deskey == null) {
				return null;
			}

			byte[] des = null;
			try {
				des = desEncrypt(pass + "$" + System.currentTimeMillis(),
						deskey);

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return "des "
					+ en.encode((userid + ":" + en.encode(des)).getBytes())
							.replaceAll("\r\n", "");
		}
		return null;
	}

	private String md5(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		StringBuffer hexString = new StringBuffer();

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();

			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0"
							+ Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	private byte[] desEncrypt(String plainText, String deskey) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(deskey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		javax.crypto.SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(1, key, sr);
		byte encryptedData[] = cipher.doFinal(plainText.getBytes());

		return encryptedData;
	}

	public String getHttp(String strUrl) {
		HttpClient httpclient = new HttpClient();
		HttpMethod method = new GetMethod(strUrl);
		try {
			method.addRequestHeader("Authorization", this.getAuth());
			int statusCode = httpclient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			method.releaseConnection();
			return new String(responseBody,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String sendFax(String posturl, Map<String, String> datamap,
			List<String> filelist) {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(posturl);
		post.addRequestHeader("Authorization", this.getAuth());

		try {
			List<Part> list = new ArrayList<Part>();

			// 添加post字段
			if (datamap != null && datamap.size() > 0) {
				Iterator<String> it = datamap.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					list.add(new StringPart(key, datamap.get(key), "utf-8"));
				}
			}
			// 添加文件
			if (filelist != null && filelist.size() > 0) {
				for (String filepath : filelist) {
					File file = new File(filepath);
					list.add(new AllcomFilePart(file.getName(), file));
				}
			}

			post.setRequestEntity(new MultipartRequestEntity(list.toArray(new Part[list.size()]), post.getParams()));

			httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

			int statusCode = httpclient.executeMethod(post);

			if (statusCode == 403) {
				log.error("认证失败");
				return null;
			}

			InputStream inStream = post.getResponseBodyAsStream();
			InputStreamReader in = new InputStreamReader(inStream);
			BufferedReader br = new BufferedReader(in);

			int ch;
			StringBuffer sb = new StringBuffer();
			while ((ch = br.read()) != -1) {
				sb.append((char) ch);
			}
			br.close();
			in.close();
			inStream.close();
			post.releaseConnection();
			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	class AllcomFilePart extends FilePart {
		public AllcomFilePart(String filename, File file)
				throws FileNotFoundException {
			super(filename, file);
			this.setCharSet("utf-8");
		}

		protected void sendDispositionHeader(OutputStream out)
				throws IOException {
			// super.sendDispositionHeader(out);
			String filename = getSource().getFileName();
			if (filename != null) {
				out.write("Content-Disposition: form-data; name=".getBytes());
				out.write(QUOTE_BYTES);
				out.write(EncodingUtil.getBytes(filename, "utf-8"));
				out.write(QUOTE_BYTES);
				out.write("; filename=".getBytes());
				out.write(QUOTE_BYTES);
				out.write(EncodingUtil.getBytes(filename, "utf-8"));
				out.write(QUOTE_BYTES);
			}
		}
	}
}
