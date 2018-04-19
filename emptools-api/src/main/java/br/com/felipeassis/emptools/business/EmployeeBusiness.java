package br.com.felipeassis.emptools.business;

import java.util.List;

import javax.inject.Inject;

import br.com.felipeassis.emptools.exception.ApplicationException;
import br.com.felipeassis.emptools.model.Employee;
import br.com.felipeassis.emptools.persistence.EmployeeDao;
import br.com.felipeassis.emptools.util.Dominios;

/**
 * Classs defined to implement the Employees businessrules.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.business
 * @since 18/04/2018
 */
public class EmployeeBusiness {

	@Inject
	private EmployeeDao employeeDao;

	/**
	 * List all Employee's basic data.
	 * @return List
	 * @throws ApplicationException
	 */
	public List<Employee> listAll() throws ApplicationException {
		return employeeDao.listAll(new String[] { "name", Dominios.ORDER_ASC });
	}

	/**
	 * List all Employee's data with details about projects, skills and certifications.
	 * @return List
	 * @throws ApplicationException
	 */
	public List<Employee> listAllWithDetails() throws ApplicationException {
		return employeeDao.listAllWithDetails();
	}

	/**
	 * List all Employees by Filter.
	 * @param filter: EmployeeFilter
	 * @throws ApplicationException
	 */
	public void findByFilter(EmployeeFilter filter) throws ApplicationException {
		employeeDao.findByFilter(filter);
	}
}
