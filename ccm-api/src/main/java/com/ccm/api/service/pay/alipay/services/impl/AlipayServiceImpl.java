package com.ccm.api.service.pay.alipay.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPay;
import com.ccm.api.service.order.OrderPayManager;
import com.ccm.api.service.pay.alipay.config.AlipayConfig;
import com.ccm.api.service.pay.alipay.services.AlipayService;
import com.ccm.api.service.pay.alipay.util.AlipaySubmit;
import com.ccm.api.util.DateUtil;

/* *
 *类名：AlipayService
 *功能：支付宝各接口构造类
 *详细：构造支付宝各接口请求参数
 *版本：3.2
 *修改日期：2011-03-17
 *说明：
 */

@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

	@Resource
	private OrderPayManager orderPayManager;
	@Resource
	private HotelDao hotelDao;

	public final transient Log log = LogFactory.getLog(getClass());

	/**
	 * 构造即时到帐接口
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @return 表单提交HTML信息
	 */
	public String create_direct_pay_by_user(Master orderInfo, Map<String, String> map) {
		Map<String, String> sParaTemp = getParameterMap(orderInfo);
		MasterPay orderPay = saveOrderPay(orderInfo);
		sParaTemp.put("extra_common_param", orderPay.getOrderPayId());
		if (map != null && map.size() > 0) {
			sParaTemp.putAll(map);
		}

		log.info(orderInfo.getHotelId());
		Hotel h = hotelDao.getHotel(orderInfo.getHotelId());
		if (h == null) {
			log.error("wbe pay hotel is not found");
			throw new BizException("商家账户没设置");
		}

		// 增加基本配置
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", h.getPartner());
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("seller_email", h.getSellerEmail());
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);

		String strButtonName = "确认";

		return AlipaySubmit.buildForm(sParaTemp, "post", strButtonName, h.getCheckCode());
	}

	/**
	 * 
	 * @param orderInfo
	 * @return
	 */
	private Map<String, String> getParameterMap(Master orderInfo) {

		// 请与贵网站订单系统中的唯一订单号匹配
		String out_trade_no = orderInfo.getMasterId();
		// 订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
		String subject = orderInfo.getRoomTypeName();
		if (!StringUtils.hasLength(subject)) {
			subject = orderInfo.getType();
		}
		// 订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
		String body = DateUtil.convertDateToString(orderInfo.getArr()) + "日入住" + orderInfo.getRoomTypeName();
		// 订单总金额，显示在支付宝收银台里的“应付总额”里

		String total_fee = orderInfo.getCharge2Scale() + "";

		// 扩展功能参数——默认支付方式//

		// 默认支付方式，取值见“即时到帐接口”技术文档中的请求参数列表
		String paymethod = "";
		// 默认网银代号，代号列表见“即时到帐接口”技术文档“附录”→“银行列表”
		String defaultbank = "";

		// 扩展功能参数——防钓鱼//

		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// 获取客户端的IP地址，建议：编写获取客户端IP地址的程序
		String exter_invoke_ip = "";
		// 注意：
		// 1.请慎重选择是否开启防钓鱼功能
		// 2.exter_invoke_ip、anti_phishing_key一旦被设置过，那么它们就会成为必填参数
		// 3.开启防钓鱼功能后，服务器、本机电脑必须支持远程XML解析，请配置好该环境。
		// 4.建议使用POST方式请求数据
		// 示例：
		// anti_phishing_key = AlipayService.query_timestamp(); //获取防钓鱼时间戳函数
		// exter_invoke_ip = "202.1.1.1";

		// 扩展功能参数——其他///

		// 自定义参数，可存放任何内容（除=、&等特殊字符外），不会显示在页面上
		// String extra_common_param = "";
		// 默认买家支付宝账号
		String buyer_email = "";
		// 商品展示地址，要用http:// 格式的完整路径，不允许加?id=123这类自定义参数
		String show_url = "";

		// 扩展功能参数——分润(若要使用，请按照注释要求的格式赋值)//

		// 提成类型，该值为固定值：10，不需要修改
		String royalty_type = "";
		// 提成信息集
		String royalty_parameters = "";
		// 注意：
		// 与需要结合商户网站自身情况动态获取每笔交易的各分润收款账号、各分润金额、各分润说明。最多只能设置10条
		// 各分润金额的总和须小于等于total_fee
		// 提成信息集格式为：收款方Email_1^金额1^备注1|收款方Email_2^金额2^备注2
		// 示例：
		// royalty_type = "10"
		// royalty_parameters = "111@126.com^0.01^分润备注一|222@126.com^0.01^分润备注二"

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("body", body);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("paymethod", paymethod);
		sParaTemp.put("defaultbank", defaultbank);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// sParaTemp.put("it_b_pay", "2h"); // 支付交易关闭时间
		sParaTemp.put("buyer_email", buyer_email);
		sParaTemp.put("royalty_type", royalty_type);
		sParaTemp.put("royalty_parameters", royalty_parameters);

		return sParaTemp;
	}

	private MasterPay saveOrderPay(Master orderInfo) {
		// 保存支付方式，订单金额，订单号
		MasterPay op = new MasterPay();
		op.setMasterId(orderInfo.getMasterId());
		op.setCost(orderInfo.getCharge2Scale().doubleValue());
		op.setCreateDate(new Date());
		op.setGateway("Alipay");

		op = orderPayManager.save(op);
		return op;

	}

}
