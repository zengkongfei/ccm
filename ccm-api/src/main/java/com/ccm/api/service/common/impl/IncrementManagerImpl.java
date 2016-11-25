package com.ccm.api.service.common.impl;

import org.springframework.stereotype.Service;

import com.ccm.api.aop.ExceptionPointcut;
import com.ccm.api.service.common.IncrementManager;
import com.ccm.api.util.PushDataUtil;

@Service("incrementManager")
public class IncrementManagerImpl implements IncrementManager {

	@ExceptionPointcut
	public String postIncrement(String url) throws Exception {
		return PushDataUtil.postData(url, "");
	}
}
