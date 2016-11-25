package com.ccm.api.service.order.impl;

import com.taobao.api.internal.stream.connect.ConnectionLifeCycleListener;

public class TaobaoConnectionLifeCycleListenerImpl implements ConnectionLifeCycleListener {

	public void onBeforeConnect() {
	}

	public void onException(Throwable e) {
		e.printStackTrace();
	}

	public void onMaxReadTimeoutException() {
	}

}
