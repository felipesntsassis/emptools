package br.com.felipeassis.emptools.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Model to represent the Projects that Employee participates.
 * 
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.model
 * @since 17/04/2018
 */
@Entity
@Table(name = "tb_project")
@SequenceGenerator(name = "projectSequence", sequenceName = "tb_project_seq", allocationSize = 1, initialValue = 1)
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectSequence")
	@Column(name = "project_id")
	private Long id;

	@Column(name = "description")
	@Length(max = 100)
	@NotNull
	private String customer;

	@Column(name = "value", length = 10, precision = 2)
	@NotNull
	private BigDecimal valueOfProject;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_begin")
	@NotNull
	private Date dtBegin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_end")
	private Date dtEnd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public BigDecimal getValueOfProject() {
		return valueOfProject;
	}

	public void setValueOfProject(BigDecimal valueOfProject) {
		this.valueOfProject = valueOfProject;
	}

	public Date getDtBegin() {
		return dtBegin;
	}

	public void setDtBegin(Date dtBegin) {
		this.dtBegin = dtBegin;
	}

	public Date getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}
}
