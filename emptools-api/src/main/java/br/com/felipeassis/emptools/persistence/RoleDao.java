package br.com.felipeassis.emptools.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.felipeassis.emptools.model.Role;

public class RoleDao extends Dao<Role> {

	@Inject
	EntityManager manager;

}
