/**
 * 
 */
package com.ccm.api.dao.common.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.PessimisticLockDao;
import com.ccm.api.model.common.PessimisticLock;

/**
 * @author Jenny
 * 
 */
@Repository("pessimisticLockDao")
public class PessimisticLockDaoiBatis extends GenericDaoiBatis<PessimisticLock, String> implements PessimisticLockDao {

	public PessimisticLockDaoiBatis() {
		super(PessimisticLock.class);
	}

	public void deletePessimisticLock(PessimisticLock pl) {
		getSqlMapClientTemplate().delete("deletePessimisticLock", pl);
	}

	public void savePessimisticLock(PessimisticLock pl) {
		if (getPessimisticLockByKey(pl) != null) {
			throw new BizException("DuplicateKeyException");
		}
		getSqlMapClientTemplate().insert("addPessimisticLock", pl);
	}

	public PessimisticLock getPessimisticLockByKey(PessimisticLock pl) {
		return (PessimisticLock) getSqlMapClientTemplate().queryForObject("getPessimisticLockByKey", pl);
	}

}
