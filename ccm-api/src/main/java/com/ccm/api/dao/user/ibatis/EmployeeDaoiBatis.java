/**
 * 
 */
package com.ccm.api.dao.user.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.user.EmployeeDao;
import com.ccm.api.model.user.Employee;
import com.ccm.api.model.user.EmployeeI18n;

/**
 * @author Justin Chen
 * 
 */
@Repository("employeeDao")
public class EmployeeDaoiBatis extends GenericDaoiBatis<Employee, String> implements EmployeeDao {

	public EmployeeDaoiBatis() {
		super(Employee.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getCompanyContacts(String companyId) {
		return getSqlMapClientTemplate().queryForList("getCompanyContacts", companyId);
	}

	@Override
	public void addEmployeeI18n(EmployeeI18n v) {
		v.setEmployeeI18n_MId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addEmployeeI18n",v);
	}

	@Override
	public void deleteEmployeeI18nByEmployeeId(String employeeId) {
		getSqlMapClientTemplate().delete("deleteEmployeeI18n", employeeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeI18n> getEmployeeI18nByEmployeeId(String employeeId,String language) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("employeeId", employeeId);
		map.put("language", language);
		return getSqlMapClientTemplate().queryForList("getEmployeeI18n", map);
	}

	@Override
	public Employee getEmployee(String employeeId, String language) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("employeeId", employeeId);
		map.put("language", language);
		return (Employee) getSqlMapClientTemplate().queryForObject("getEmployee_i18n", map);
	}

}
