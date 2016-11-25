package com.ccm.api.service.hotel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.enume.ProfileType;
import com.ccm.api.model.order.Master;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.order.OrderManager;

public class CustomManagerTest extends BaseManagerTestCase {
	@Autowired
	private OrderManager orderManager;
	@Test
	public void testUpdateTotalRoomRev(){
		System.out.println("begin");
		Master master = new Master();
		BigDecimal charge = new BigDecimal(100.00);
		master.setCharge(charge);
		master.setQualifyingIdType(ProfileType.CORPORATE.name());
		master.setQualifyingIdValue("OWS");
		master.setPayment(GuaranteeCode.DUE);
		List<Master> mList = new ArrayList<Master>();
		
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
//			Date date = sdf.parse("2015-11-13");
//			System.out.println(DateUtil.getDate(date));
//			List<MasterRate>  list = orderManager.getMasterRateByOrderNo("98021714", DateUtil.getDate(date));
//			System.out.println(list);
			orderManager.cancelOrder(master);
//			String order_ows = orderManager.saveOrUpdateMasterOrderRate(master, mList);
//			String order_wbe = orderManager.saveOrUpdateWBEOrder(master, mList, null);
//			String order_ccm = orderManager.savrOrUpdateOrder(master, mList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
}	
