/**
 * 
 */
package com.ccm.api.dao.common.ibatis;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.ConstantDao;
import com.ccm.api.model.common.Constant;

/**
 * @author Jenny Liao
 * 
 */
@Repository("constantDao")
public class ConstantDaoImpl extends GenericDaoiBatis<Constant, String> implements ConstantDao {

	private Log log = LogFactory.getLog(ConstantDaoImpl.class);

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public ConstantDaoImpl() {
		super(Constant.class);
	}

	public void updateConstantFlagById(String id, int flag) {
		try {
			Constant c = get(id);
			if (c != null) {
				c.setFlag(flag);
				save(c);
				log.info("updateConstantFlagByIdSuccess");
			}
		} catch (Exception e) {
			log.warn(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Constant> getConstantByName(String name) {
		return getSqlMapClientTemplate().queryForList("getConstantByName", name);
	}

	@SuppressWarnings("unchecked")
	public List<Constant> getConstantByObj(Constant c) {
		return getSqlMapClientTemplate().queryForList("getConstantByObj", c);
	}

	@SuppressWarnings("unchecked")
	public List<Constant> getConstantByHotelCode() {
		return getSqlMapClientTemplate().queryForList("getConstantByHotelCode");
	}
	
	@SuppressWarnings("unchecked")
	public void updateFlagConstant(Constant c){
		 getSqlMapClientTemplate().update("updateFlagConstant",c);
	} 
}
