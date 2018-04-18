package br.com.felipeassis.emptools.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.felipeassis.emptools.model.Employee;

public class EmployeeDao extends Dao<Employee> {

	@Inject
	EntityManager manager;

	/**
	 * List all Employee's data with details about projects, skills and certifications.
	 * @return List<Employee>
	 */
	public List<Employee> listAllWithDetails() {
		try {
			StringBuilder jpql = new StringBuilder("SELECT emp FROM Employee emp ")
					.append("LEFT JOIN FETCH emp.projects ")
					.append("LEFT JOIN FETCH emp.skills ")
					.append("LEFT JOIN FETCH emp.certifications ");
			TypedQuery<Employee> query = manager.createNamedQuery(jpql.toString(), Employee.class);
			
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
