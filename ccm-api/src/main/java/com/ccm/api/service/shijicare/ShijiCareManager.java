package com.ccm.api.service.shijicare;

import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.model.shijicare.vo.ShijicareCriteria;
import com.ccm.api.model.shijicare.vo.ShijicareSearchResult;
import com.ccm.api.service.base.GenericManager;

public interface ShijiCareManager extends GenericManager<ShijiCare, String>{
	ShijicareSearchResult searchShijiCareVO(ShijicareCriteria sc);

}
