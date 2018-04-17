package br.com.felipeassis.emptools.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.felipeassis.emptools.model.Project;

public class ProjectDao extends Dao<Project> {

	@Inject
	EntityManager manager;

}
