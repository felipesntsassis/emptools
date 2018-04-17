package br.com.felipeassis.emptools.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.felipeassis.emptools.model.Certification;

public class CertificationDao extends Dao<Certification> {

	@Inject
	EntityManager manager;

}
