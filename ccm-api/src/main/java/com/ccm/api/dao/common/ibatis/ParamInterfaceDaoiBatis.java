/**
 * 
 */
package com.ccm.api.dao.common.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.ParamInterfaceDao;
import com.ccm.api.model.common.ParamInterface;

/**
 * @author Jenny
 * 
 */
@Repository("ParamInterfaceDao")
public class ParamInterfaceDaoiBatis extends GenericDaoiBatis<ParamInterface, String> implements ParamInterfaceDao {

	public ParamInterfaceDaoiBatis() {
		super(ParamInterface.class);
	}

	@SuppressWarnings("unchecked")
	public List<ParamInterface> getParamInterfaceByObj(ParamInterface pi) {
		return getSqlMapClientTemplate().queryForList("getParamInterfaceByObj", pi);
	}
	@SuppressWarnings("unchecked")
	public List<ParamInterface> getParamInterfaceByObj(ParamInterface pi,String language) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("language", language);
		params.put("fieldName", pi.getFieldName());
		params.put("fieldValue", pi.getFieldValue());
		params.put("urlId", pi.getUrlId());
		
		return getSqlMapClientTemplate().queryForList("getParamInterfaceByObj_i18n", params);
	}

	@SuppressWarnings("unchecked")
	public List<String> getCode(String sql, String param) {
		Map<String,String> paramM=new HashMap<String,String>();
		paramM.put("sql", sql);
		paramM.put("param", param);
		return getSqlMapClientTemplate().queryForList("getCodeBySql", paramM);
	}
}
