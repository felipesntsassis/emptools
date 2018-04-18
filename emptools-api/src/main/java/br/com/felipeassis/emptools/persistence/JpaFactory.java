package br.com.felipeassis.emptools.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.felipeassis.emptools.util.Dominios;

@ApplicationScoped
public class JpaFactory {

	@PersistenceContext(unitName = Dominios.APP_PERSISTENCE_UNIT)
	private EntityManager manager;

	@Produces
	public EntityManager getManager() {
		return manager;
	}
}
