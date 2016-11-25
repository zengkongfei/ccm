/**
 * 
 */
package com.ccm.api.dao.user;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.user.Employee;
import com.ccm.api.model.user.EmployeeI18n;


/**
 * @author Justin Chen
 *
 */
public interface EmployeeDao extends GenericDao<Employee, String> {

	List<Employee> getCompanyContacts(String companyId); 
	
	void addEmployeeI18n(EmployeeI18n v);
	
	Employee getEmployee(String employeeId,String language);

	void deleteEmployeeI18nByEmployeeId(String employeeId);
	
	List<EmployeeI18n> getEmployeeI18nByEmployeeId(String employeeId,String language);

}
