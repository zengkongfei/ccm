/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.common.ParamInterfaceDao;
import com.ccm.api.model.common.ParamInterface;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.ParamInterfaceManager;

/**
 * @author Jenny
 * 
 */
@Service("ParamInterfaceManager")
public class ParamInterfaceManagerImpl extends GenericManagerImpl<ParamInterface, String> implements ParamInterfaceManager {

	private ParamInterfaceDao paramInterfaceDao;

	private Map<String, String> primaryKeMap = new HashMap<String, String>();
	private Map<String, String> foreignTable = new HashMap<String, String>();

	@Autowired
	public ParamInterfaceManagerImpl(ParamInterfaceDao paramInterfaceDao) {
		super(paramInterfaceDao);
		this.paramInterfaceDao = paramInterfaceDao;
	}

	public ParamInterface getParamInterfaceByObj(ParamInterface pi) {
		List<ParamInterface> piList = paramInterfaceDao.getParamInterfaceByObj(pi);
		if (!piList.isEmpty()) {
			return piList.get(0);
		}
		return null;
		
	}
	
	public ParamInterface getParamInterfaceByObj(ParamInterface pi,String language) {
		List<ParamInterface> piList = paramInterfaceDao.getParamInterfaceByObj(pi,language);
		if (!piList.isEmpty()) {
			return piList.get(0);
		}
		return null;

	}

	public String getCode(String sql, String param) {
		String key = sql + param;
		if (foreignTable.get(key) == null) {
			String[] params = param.split(",");
			if (params.length == 1) {
				param = "='" + params[0] + "'";
			} else if (params.length > 1) {
				param = "in('" + param.replace(",", "','") + "')";
			}
			List<String> strList = paramInterfaceDao.getCode(sql, param);
			StringBuffer sb = new StringBuffer();
			if (!strList.isEmpty()) {
				for (String str : strList) {
					sb.append(str).append(",");
				}
			}
			if (sb.toString().lastIndexOf(",") > 0) {
				foreignTable.put(key, sb.toString().substring(0, sb.toString().length() - 1));
			} else {
				foreignTable.put(key, sb.toString());
			}
		}
		return foreignTable.get(key);
	}

	public String getPrimaryKeyByCond(String urlId) {
		String key = urlId + "Id";
		if (primaryKeMap.get(key) == null) {
			ParamInterface pi = new ParamInterface();
			pi.setUrlId(urlId);
			pi.setFieldValue("Id");
			pi = getParamInterfaceByObj(pi);
			if (pi != null) {
				primaryKeMap.put(key, pi.getFieldName());
			} else {
				pi = new ParamInterface();
				pi.setUrlId(urlId);
				pi.setFieldValue("Ids");
				pi = getParamInterfaceByObj(pi);
				if (pi != null) {
					primaryKeMap.put(key, pi.getFieldName());
				} else {
					primaryKeMap.put(key, "");
				}
			}
		}
		return primaryKeMap.get(key);
	}
}
