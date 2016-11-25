/**
 * 
 */
package com.ccm.api.dao.common;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.common.PessimisticLock;

/**
 * @author Jenny
 * 
 */
public interface PessimisticLockDao extends GenericDao<PessimisticLock, String> {

	void deletePessimisticLock(PessimisticLock pl);

	void savePessimisticLock(PessimisticLock pl);

	PessimisticLock getPessimisticLockByKey(PessimisticLock pl);

}
