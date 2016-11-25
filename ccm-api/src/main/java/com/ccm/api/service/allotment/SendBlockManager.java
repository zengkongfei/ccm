package com.ccm.api.service.allotment;

import java.util.Map;

import javax.xml.bind.JAXBException;

public interface SendBlockManager {
	public void done(Map<String,Object>paramMap);
	public boolean handleResp(String otaRespXml) throws JAXBException; 
}
