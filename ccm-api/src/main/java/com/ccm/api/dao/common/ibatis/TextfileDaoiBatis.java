package com.ccm.api.dao.common.ibatis;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.TextfileDao;
import com.ccm.api.model.common.Textfile;

@Repository("textfileDao")
public class TextfileDaoiBatis extends GenericDaoiBatis<Textfile, String> implements TextfileDao{
	/**
	 * Constructor
	 */
	public TextfileDaoiBatis() {
		super(Textfile.class);
	}

	@Override
	public Textfile addTextfile(Textfile textfile) {
		return (Textfile) getSqlMapClientTemplate().insert("addTextfile", textfile);
	}

	@Override
	public void deleteTextfile(String fileId) {
		getSqlMapClientTemplate().delete("deleteTextfile", fileId);
	}

	@Override
	public Textfile getTextfile(String fileId) {
		
		return (Textfile) getSqlMapClientTemplate().queryForObject("getTextfile", fileId);
	}

}
