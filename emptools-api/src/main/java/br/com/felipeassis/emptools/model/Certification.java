package br.com.felipeassis.emptools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Model to represent the Employee's certifications.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.model
 * @since 17/04/2018
 */
@Entity
@Table(name = "tb_certification")
@SequenceGenerator(name = "certificationSequence", sequenceName = "tb_certification_seq", allocationSize = 1, initialValue = 1)
public class Certification implements Serializable {

	private static final long serialVersionUID = -7648948892850224186L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificationSequence")
	@Column(name = "certification_id")
	@NotNull
	private Long id;

	@Column(name = "description")
	@Length(max = 250)
	@NotNull
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
