/**
 * 
 */
package com.ccm.api.service.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.user.EmployeeDao;
import com.ccm.api.model.user.Employee;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.user.EmployeeManager;

@Service("employeeManager")
public class EmployeeManagerImpl extends GenericManagerImpl<Employee, String> implements EmployeeManager {

	private EmployeeDao employeeDao;

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.dao = employeeDao;
		this.employeeDao = employeeDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Employee> getCompanyContacts(String companyId) {
		return employeeDao.getCompanyContacts(companyId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveCompanyContact(String companyId, Employee contact) {

		contact.setCompanyId(companyId);
		contact.setContactFlag("1");
		boolean isNew = (contact.getEmployeeId() == null || "".equals(contact.getEmployeeId()));
		if (isNew) {
			contact.setCreatedTime(new Date());
		}

		employeeDao.save(contact);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeCompanyContact(String employeeId) {

		boolean isNull = (employeeId == null || "".equals(employeeId));
		if (!isNull) {
			Employee contact = employeeDao.get(employeeId);
			contact.setDelFlag(true);
			employeeDao.save(contact);
		}
	}
}
