package com.ccm.api.util;

import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.service.common.IncrementManager;

public class IncrementService {

	private static IncrementManager iManage = SpringContextUtil.getBean(IncrementManager.class);

	public static String orderId() {

		try {
			return iManage.postIncrement(ProjectConfigConstant.incrementService + "increment_orderId.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String profileId() {

		try {
			return iManage.postIncrement(ProjectConfigConstant.incrementService + "increment_profileId.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String msgId(String hotelCode) {

		try {
			return iManage.postIncrement(ProjectConfigConstant.incrementService + "increment_msgId.do?hotelCode=" + hotelCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static String refreshJob() {

		try {
			return iManage.postIncrement(ProjectConfigConstant.jobService + "jobAction_refreshJob.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
