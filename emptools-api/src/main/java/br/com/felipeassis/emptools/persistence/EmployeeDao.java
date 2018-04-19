package br.com.felipeassis.emptools.persistence;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.com.felipeassis.emptools.business.EmployeeFilter;
import br.com.felipeassis.emptools.exception.ApplicationException;
import br.com.felipeassis.emptools.model.Employee;
import br.com.felipeassis.emptools.util.TreatNull;
import br.com.felipeassis.emptools.util.TreatNumber;
import br.com.felipeassis.emptools.util.TreatString;

public class EmployeeDao extends Dao<Employee> {

	private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class);

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
			TypedQuery<Employee> query = manager.createQuery(jpql.toString(), Employee.class);
			
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public EmployeeFilter findByFilter(EmployeeFilter filter) throws ApplicationException {
		try {
			StringBuilder jpql = new StringBuilder("SELECT emp FROM Employee emp ");
			configJoins(filter, jpql);
			configCriteria(filter, jpql);
			configOrderBy(filter, jpql);
			TypedQuery<Employee> query = manager.createQuery(jpql.toString(), Employee.class);
			setParamValues(filter, query);
			
			filter.setRows(query.getResultList());
			
			return filter;
		} catch (Exception e) {
			LOGGER.error(e);
			throw new ApplicationException("msg.ERRO.listagem", new String[] { e.getMessage() }, 
					ApplicationException.ICON_ERRO);
		}
	}

	private void configJoins(EmployeeFilter filter, StringBuilder jpql) throws ApplicationException {
		if (TreatNull.isNotEmpty(filter.getProjects())) {
			jpql.append("LEFT JOIN emp.projects prj ");
		}
		if (TreatNull.isNotEmpty(filter.getSkills())) {
			jpql.append("LEFT JOIN emp.skills skl ");
		}
		if (TreatNull.isNotEmpty(filter.getCertifications())) {
			jpql.append("LEFT JOIN emp.certifications crt ");
		}
	}

	/**
	 * Monta a clausula WHERE da consulta conforme os parâmetros informados no filtro.
	 * @param filter: EmployeeFilter
	 * @param jpql: StringBuilder
	 * @throws ApplicationException
	 */
	private void configCriteria(EmployeeFilter filter, StringBuilder jpql) throws ApplicationException {
		if (TreatNumber.isNotNullOrZero(filter.getEntity().getId())) {
			jpql.append("AND emp.id= :id");
		}
		if (TreatString.isNotBlank(filter.getEntity().getName())) {
			jpql.append("AND LOWER(" + translate("emp.name") + ") LIKE LOWER("+ translate(":name") + ") ");
		}
		if (TreatNull.isNotNull(filter.getEntity().getRole())) {
			jpql.append("AND emp.role.id = :role");
		}
		if (TreatString.isNotBlank(filter.getInitialSalary())) {
			jpql.append("AND emp.salary >= :initialSalary ");
		}
		if (TreatString.isNotBlank(filter.getFinalSalary())) {
			jpql.append("AND emp.salary >= :finalSalary ");
		}
		if (TreatNull.isNotEmpty(filter.getProjects())) {
			jpql.append("AND prj.id IN (:projects) ");
		}
		if (TreatNull.isNotEmpty(filter.getSkills())) {
			jpql.append("AND skl.id IN (:skills) ");
		}
		if (TreatNull.isNotEmpty(filter.getCertifications())) {
			jpql.append("AND crt.id IN (:certifications) ");
		}
		
		jpql = TreatString.replaceWhere(jpql);
	}


	/**
	 * Método responsável em montar os parametros do filtro.
	 * @param filtro: FranqueadoFilter
	 * @param query: TypedQuery<Franqueado> query
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	private void setParamValues(EmployeeFilter filter, TypedQuery query) throws ParseException {
		if (TreatNull.isNotNullOrZero(filter.getEntity().getId())) {
			query.setParameter("id", filter.getEntity().getId());
		}
		if (TreatString.isNotBlank(filter.getEntity().getName())) {
			query.setParameter("name", "%" + filter.getEntity().getName() + "%");
		}
		if (TreatNull.isNotNull(filter.getEntity().getRole())) {
			query.setParameter("role", filter.getEntity().getRole().getId());
		}
		if (TreatString.isNotBlank(filter.getInitialSalary())) {
			query.setParameter("initialSalary", TreatNumber.converterMoedaParaBigDecimal(filter.getInitialSalary()));
		}
		if (TreatString.isNotBlank(filter.getFinalSalary())) {
			query.setParameter("finalSalary", TreatNumber.converterMoedaParaBigDecimal(filter.getFinalSalary()));
		}
		if (TreatNull.isNotEmpty(filter.getProjects())) {
			query.setParameter("projects", filter.getEntity().getProjects());
		}
		if (TreatNull.isNotEmpty(filter.getSkills())) {
			query.setParameter("skills", filter.getEntity().getSkills());
		}
		if (TreatNull.isNotEmpty(filter.getCertifications())) {
			query.setParameter("certifications", filter.getEntity().getCertifications());
		}
	}

	/**
	 * Monta o parametro Order By da Consulta.
	 * @param filter : {@link FranqueadoFilter}
	 * @param jpql : {@link StringBuilder}
	 */
	private void configOrderBy(EmployeeFilter filter, StringBuilder jpql) {
		if (TreatString.isNotBlank(filter.getSortBy()) && TreatString.isNotBlank(filter.getSortDirection())) {
			jpql.append("ORDER BY emp." + filter.getSortBy() + " " + filter.getSortDirection().toUpperCase());
		}
	}
}
