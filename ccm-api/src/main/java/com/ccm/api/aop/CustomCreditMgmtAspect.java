package com.ccm.api.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.constant.ChannelCode;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.order.Master;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelSwitchManager;


public class CustomCreditMgmtAspect {
	private Log logger = LogFactory.getLog(CustomCreditMgmtAspect.class);
	
	@Autowired
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Autowired
	private CustomManager customManager;
	
	public void updateTotalRoomRev(JoinPoint joinPoint) {
		
		Object[] args = joinPoint.getArgs();
		logger.info("目标参数列表：");
		if (args != null) {
			for (Object obj : args) {
				try {
					if (obj instanceof Master) {
						Master master = (Master) obj;
						String channelCode = master.getChannel();
						
						Custom c = new Custom();
						c.setHotelId(master.getHotelId());
						c.setAccessCode(master.getQualifyingIdValue());
						c.setType(master.getQualifyingIdType());
						Custom custom = rateCustomRelationshipDao.getCustomIdByRateCustom(c, master.getRatePlanId());
						//是否启用保证金
						boolean flag = false;
						if(custom!=null&&custom.getBookingManagment()!=null&&custom.getBookingManagment()){
							//不启用保证金
							flag=true;
						}
						//执行credit limit逻辑
						if(flag){
							//TAOBAO渠道单独处理
							if(ChannelCode.TAOBAO.equalsIgnoreCase(channelCode)){
								if(GuaranteeCode.VOUCHER.equalsIgnoreCase(master.getPayment())||GuaranteeCode.TAGTD.equalsIgnoreCase(master.getPayment())||GuaranteeCode.TAGTD_GT.equalsIgnoreCase(master.getPayment())){
									customManager.updateTotalRoomRev(master,custom);
								}
							}else{
								if(master.getPayment().equalsIgnoreCase(GuaranteeCode.DUE)||master.getPayment().equalsIgnoreCase(GuaranteeCode.PREPAID)){
									customManager.updateTotalRoomRev(master,custom);
								}
							}
							
						}
					} 
				} catch (Exception e) {
					logger.warn(e);
				}
			}
		}

	}
}
