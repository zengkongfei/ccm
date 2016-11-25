/**
 * 
 */
package com.ccm.api.service.pay.mpay.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPay;
import com.ccm.api.service.order.OrderPayManager;
import com.ccm.api.service.pay.mpay.MpayManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PropertiesUtil;
import com.mobiletech.mpay.client.MerchantClient;

/**
 * @author Jenny
 * 
 */
@Service("mpayManager")
public class MpayManagerImpl implements MpayManager {

	private Log log = LogFactory.getLog("reservation");

	public static final String MpayPaymentUrl = PropertiesUtil.getProperty("mpayPaymentURL");
	public static final String Return_Url = PropertiesUtil.getProperty("returnurl");
	public static final String Notify_Url = PropertiesUtil.getProperty("notifyurl");

	@Resource
	private HotelDao hotelDao;

	@Resource
	private OrderPayManager orderPayManager;

	/**
	 * 构造支付接口
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @return 表单提交HTML信息
	 */
	public String buildPayInfo(Master order) {

		Map<String, String> map = new HashMap<String, String>();

		Hotel h = hotelDao.getHotel(order.getHotelId(), "merchantid,merchant_tid,securekey");
		if (h == null) {
			log.error("wbe pay hotel is not found");
			throw new BizException("商家mPay账户没设置");
		}

		// 保存支付方式，订单金额，订单号
		MasterPay mPay = new MasterPay();
		mPay.setMasterId(order.getMasterId());
		mPay.setCost(order.getCharge2Scale().doubleValue());
		mPay.setCreateDate(new Date());
		mPay.setGateway("MPay");
		mPay = orderPayManager.save(mPay);

		// 增加参数配置
		map.put("merchantid", h.getMerchantid());
		map.put("merchant_tid", h.getMerchant_tid());
		map.put("securekey", h.getSecurekey());
		map.put("ordernum", CommonUtil.generateSequenceNo());
		map.put("datetime", DateUtil.getDateTime("yyyyMMddHHmmss", order.getChanged()));
		map.put("amt", order.getCharge2Scale() + "");
		map.put("currency", "RMB");
		map.put("cardtype", "0");
		map.put("locale", order.getLang());
		map.put("returnurl", Return_Url);
		map.put("notifyurl", Notify_Url);
		map.put("customizeddata", mPay.getOrderPayId());

		String strButtonName = "确认";

		return buildForm(map, "post", strButtonName);
	}

	/**
	 * 构造提交表单HTML数据
	 * 
	 * @param sParaTemp
	 *            请求参数数组
	 * @param gateway
	 *            网关地址
	 * @param strMethod
	 *            提交方式。两个值可选：post、get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @return 提交表单HTML文本
	 */
	private static String buildForm(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
		// 待请求参数数组
		MerchantClient merchantClient = new MerchantClient();
		String salt = merchantClient.genSalt();
		String hash = merchantClient.genHashValue(sParaTemp.get("merchantid"), sParaTemp.get("merchant_tid"), sParaTemp.get("ordernum"), sParaTemp.get("datetime"), sParaTemp.get("amt"), sParaTemp.get("currency"), sParaTemp.get("cardtype"), sParaTemp.get("locale"), sParaTemp.get("returnurl"), sParaTemp.get("notifyurl"), sParaTemp.get("customizeddata"), sParaTemp.get("extrafield1"), sParaTemp.get("extrafield2"), sParaTemp.get("extrafield3"), salt, sParaTemp.get("securekey"));

		// 签名结果与签名方式加入请求提交参数组中
		sParaTemp.put("salt", salt);
		sParaTemp.put("hash", hash);

		List<String> keys = new ArrayList<String>(sParaTemp.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"mpaysubmit\" name=\"mpaysubmit\" action=\"" + MpayPaymentUrl + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sParaTemp.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		// submit按钮控件请不要含有name属性
		// sbHtml.append("<input type=\"submit\" value=\"" + strButtonName +
		// "\" style=\"display:;\">");
		sbHtml.append("</form>");
		sbHtml.append("<script>document.forms['mpaysubmit'].submit();</script>");
		return sbHtml.toString();
	}

}
