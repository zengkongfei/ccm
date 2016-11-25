package com.ccm.api.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.common.PessimisticLockDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.common.PessimisticLock;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.vo.ReceivePmsLogCriteria;
import com.ccm.api.model.log.vo.ReceivePmsLogResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.BatchManager;
import com.ccm.api.service.email.HotelEmailMapperManager;
import com.ccm.api.service.log.ReceiveMsgLogManager;
import com.ccm.api.service.log.ReceivePmsLogManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.order.ReservationFromPmsService;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.util.CommonUtil;

@Service("batchManager")
public class BatchManagerImpl extends GenericManagerImpl<String, String> implements BatchManager {

	private final Log log = LogFactory.getLog(BatchManagerImpl.class);

	@Resource
	private PessimisticLockDao pessimisticLockDao;

	@Resource
	private OrderManager orderManager;
	@Resource
	private ReservationService reservationService;
	@Resource
	private ReservationFromPmsService reservationFromPmsService;
	@Resource
	private ReceiveMsgLogManager receiveMsgLogManager;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private ReceivePmsLogManager receivePmsLogManager;
	@Resource
	private HotelEmailMapperManager hotelEmailMapperManager;

	/**
	 * 每5分钟 批量取消没有付款的订单
	 */
	@Override
	public void updateOrder() {

		log.info(this.getClass().getName() + "  start " + "updateOrder");

		orderManager.autoCancelNotPaidOrder();

		log.info(this.getClass().getName() + "  end " + "updateOrder");
	}

	public void saveOrder2Pms() {
		List<PessimisticLock> plList = pessimisticLockDao.getAll();
		for (PessimisticLock pl : plList) {
			if (!StringUtils.hasText(pl.getOrderId())) {
				continue;
			}
			try {
				if (OrderStatus.ADD.equals(pl.getBizType())) {
					reservationService.createOrder(pl.getOrderId());
					pessimisticLockDao.deletePessimisticLock(pl);
				} else if (OrderStatus.EDIT.equals(pl.getBizType())) {
					reservationService.changeOrder(pl.getOrderId());
					pessimisticLockDao.deletePessimisticLock(pl);
				} else if (OrderStatus.CANCEL.equals(pl.getBizType())) {
					reservationService.cancelOrder(pl.getOrderId());
					pessimisticLockDao.deletePessimisticLock(pl);
				} else if ("PmsUpRserve".equals(pl.getBizType())) {

					ReceiveMsgLog rml = receiveMsgLogManager.getReceiveMsgLog(pl.getOrderId(), pl.getHotelCode());
					if (rml != null) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("charsetName", pl.getCharsetName());
						map.put("transactionId", pl.getTransactionId());// 交易代码
						map.put("receiveMsgLogId", pl.getOrderId()); // 生成主键ID
						map.put("propertyName", pl.getHotelCode());// 酒店代码
						map.put("Message", rml.getMessage());// 消息
						reservationFromPmsService.pmsChangeOrder(map);
						pessimisticLockDao.deletePessimisticLock(pl);
					}
				}
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			}
		}
	}

	public void sendEmail2HotelOfInterface() {
		List<HotelVO> hvoList = hotelDao.getAllDirectPmsHotel(LanguageCode.MAIN_LANGUAGE_CODE, true);
		List<String> hotelCodes = new ArrayList<String>();
		Map<String, HotelVO> hotelCodeEmail = new HashMap<String, HotelVO>();
		for (HotelVO hvo : hvoList) {
			if (StringUtils.hasText(hvo.getEmail())) {
				hotelCodes.add(hvo.getHotelCode());
				hotelCodeEmail.put(hvo.getHotelCode(), hvo);
			}
		}
		if (hotelCodeEmail.isEmpty()) {
			return;
		}
		ReceivePmsLogCriteria soc = new ReceivePmsLogCriteria();
		soc.setHoteCodes(hotelCodes);
		soc.setNeedPage(false);
		soc.setStatus("off");
		ReceivePmsLogResult receivePmsLogResult = receivePmsLogManager.searchList(soc);
		for (ReceivePmsLog rpl : receivePmsLogResult.getResultList()) {
			HotelVO hvo = hotelCodeEmail.get(rpl.getHotelCode());
			hotelEmailMapperManager.sendEmail2HotelOff(rpl.getHotelCode(), hvo.getHotelName(), hvo.getEmail());
		}

	}
}
