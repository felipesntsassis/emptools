package br.com.felipeassis.emptools.model;

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
 * Model to represent the Employee's role.
 * @author Felipe dos Santos Assis <felipe.assis@sigma.com.br>
 * @since 17/04/2018 12:52 (Projeto/Implementação)
 */
@Entity
@Table(name = "tb_role")
@SequenceGenerator(name = "roleSequence", sequenceName = "tb_role_seq", allocationSize = 1, initialValue = 1)
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
	@Column(name = "role_id")
	@NotNull
	private Long id;
	
	@Column(name = "description")
	@Length(max = 250)
	@NotNull
	private String description;
}
