package com.ccm.api.service.common;

import com.ccm.api.model.common.Textfile;
import com.ccm.api.service.base.GenericManager;

public interface TextfileManager extends GenericManager<Textfile,String>{
	Textfile addTextfile(Textfile textfile);
	void deleteTextfile(String fileId);
	Textfile getTextfile(String fileId);
	public Textfile createTextfile(String fileFileName,String hotelId) throws Exception;
}
