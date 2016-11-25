package com.ccm.api.dao.common;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.common.Textfile;

public interface TextfileDao extends GenericDao<Textfile, String> {
	Textfile addTextfile(Textfile textfile);
	void deleteTextfile(String fileId);
	Textfile getTextfile(String fileId);
}
