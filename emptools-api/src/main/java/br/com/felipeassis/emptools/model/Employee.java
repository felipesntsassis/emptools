package br.com.felipeassis.emptools.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Model to represent the Employee.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.model
 * @since 17/04/2018
 */
@Entity
@Table(name = "tb_employee")
@SequenceGenerator(name = "employeeSequence", sequenceName = "tb_employee_seq", allocationSize = 1, initialValue = 1)
public class Employee implements Serializable {

	private static final long serialVersionUID = -148639098873875484L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSequence")
	@Column(name = "employee_id")
	@NotNull
	private Long id;

	@Column(name = "name")
	@NotNull
	@Length(max = 100)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	@NotNull
	private Role role;

	@OneToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
	@NotNull
	private Employee manager;

	@Column(name = "gcm")
	@Length(max = 25)
	@NotNull
	private String gcm;

	@Column(name = "salary", length = 10, precision = 2)
	@NotNull
	private BigDecimal salary;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_employee_project",
		joinColumns = @JoinColumn(name = "employee_id"),
		inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Set<Project> projects;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_employee_skill",
		joinColumns = @JoinColumn(name = "employee_id"),
		inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private Set<Skill> skills;

	@ManyToMany
	@JoinTable(name = "tb_employee_certification",
		joinColumns = @JoinColumn(name = "employee_id"),
		inverseJoinColumns = @JoinColumn(name = "certification_id"))
	private Set<Certification> certifications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public String getGcm() {
		return gcm;
	}

	public void setGcm(String gcm) {
		this.gcm = gcm;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(Set<Certification> certifications) {
		this.certifications = certifications;
	}

}
