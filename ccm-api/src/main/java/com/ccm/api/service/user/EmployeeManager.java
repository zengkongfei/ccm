/**
 * 
 */
package com.ccm.api.service.user;

import java.util.List;

import com.ccm.api.model.user.Employee;
import com.ccm.api.service.base.GenericManager;


/**
 * @author Justin Chen
 *
 */
public interface EmployeeManager extends GenericManager<Employee, String> {
	

	
	/**
	 * 取回所有联系人
	 * @param companyId
	 * @return
	 */
	List<Employee> getCompanyContacts(String companyId);
	
	
	/**
	 * 保存公司联系人
	 * @param companyId
	 * @param contact
	 */
	void saveCompanyContact(String companyId, Employee contact);
	
	
	/**
	 * 删除联系人,逻辑删除
	 * @param employeeId
	 */
	void removeCompanyContact(String employeeId);
	
	
	
	
}

