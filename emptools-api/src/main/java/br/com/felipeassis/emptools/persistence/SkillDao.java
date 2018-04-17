package br.com.felipeassis.emptools.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.felipeassis.emptools.model.Skill;

public class SkillDao extends Dao<Skill> {

	@Inject
	EntityManager manager;

}
