package br.com.felipeassis.emptools.business;

import java.util.List;

import br.com.felipeassis.emptools.model.Employee;
import br.com.felipeassis.emptools.model.filter.Filter;

/**
 * Defined class to implement filter to Employee entity.
 * 
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.business
 * @since 19/04/2018
 */
public class EmployeeFilter extends Filter<Employee> {

	private String initialSalary;
	private String finalSalary;
	private List<Long> projects;
	private List<Long> skills;
	private List<Long> certifications;

	public String getInitialSalary() {
		return initialSalary;
	}

	public void setInitialSalary(String initialSalary) {
		this.initialSalary = initialSalary;
	}

	public String getFinalSalary() {
		return finalSalary;
	}

	public void setFinalSalary(String finalSalary) {
		this.finalSalary = finalSalary;
	}

	public List<Long> getProjects() {
		return projects;
	}

	public void setProjects(List<Long> projects) {
		this.projects = projects;
	}

	public List<Long> getSkills() {
		return skills;
	}

	public void setSkills(List<Long> skills) {
		this.skills = skills;
	}

	public List<Long> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Long> certifications) {
		this.certifications = certifications;
	}
}
