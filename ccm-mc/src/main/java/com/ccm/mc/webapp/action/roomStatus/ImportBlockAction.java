package com.ccm.mc.webapp.action.roomStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccm.api.service.allotment.AllotmentManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;

public class ImportBlockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(ImportBlockAction.class);

	@Resource
	private AllotmentManager allotmentManager;

	public String importBlock() throws Exception{
		System.out.println("import block is starting");
		String xml = getXML();
		String header = xml.substring(xml.indexOf("<?Label"));
		header = header.substring(7, header.indexOf("?>"));
		StringTokenizer st = new StringTokenizer(header.trim(), "|");

		String OXIResortCode = st.nextToken().toUpperCase();
		String OXIMessageType = st.nextToken().toUpperCase();
		String OXISequence = st.nextToken().toUpperCase();
		String OXIStatus = st.nextToken().toUpperCase();
		
		Map reqMap=new HashMap();
		reqMap.put("charsetName", "UTF-8");
		reqMap.put("MessageType",OXIMessageType);// 消息类型
		reqMap.put("Message", xml);// 消息

		// 用于切面使用
		reqMap.put("interfaceName", "");
		reqMap.put("propertyName", OXIResortCode);// 酒店代码
		reqMap.put("transactionId", OXISequence);// 交易代码
		//map.put("status", status);
		reqMap.put("namespace", "allotment.fidelio.2.0"); // 命名空间
		reqMap.put("receiveMsgLogId", CommonUtil.generatePrimaryKey()); // 生成主键ID

		allotmentManager.proxyHandleAllotment(reqMap);
		this.ajaxResponse("success");
		return SUCCESS;
	}

	private String getXML() {
		try {
			HttpServletRequest request = getRequest();
			InputStream is = request.getInputStream();
			if (is == null) {
				return "";
			}
			int clen = request.getContentLength();
			if (clen == -1) {
				return "";
			}

			byte[] b = new byte[clen];
			int len = 0;
			while (len < clen) {
				len += is.read(b, len, clen - len);
			}
			is.close();
			return new String(b, request.getCharacterEncoding());

		} catch (IOException e) {
			log.info("接收文件流失败！");
			log.info(e);
			return "";
		}
	}
}
