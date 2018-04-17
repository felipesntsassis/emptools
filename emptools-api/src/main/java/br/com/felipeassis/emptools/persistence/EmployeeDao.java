package br.com.felipeassis.emptools.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.felipeassis.emptools.model.Employee;

public class EmployeeDao extends Dao<Employee> {

	@Inject
	EntityManager manager;

}
