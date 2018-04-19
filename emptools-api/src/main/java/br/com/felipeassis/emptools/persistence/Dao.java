package br.com.felipeassis.emptools.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.com.felipeassis.emptools.exception.ApplicationException;
import br.com.felipeassis.emptools.util.Dominios;


public class Dao<T> {

	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável em tornar a entidade gerenciada pela JPA e incluí-la no banco de dados.  
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 10/06/2015
	 * @param entity - Entidade a ser persistida no banco de dados.
	 */
	public void insert(T entity) throws ApplicationException {
		try {
			manager.persist(entity);
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "incluir registro: " + e.getMessage() });
		}
	}
	
	/**
	 * Método responsável em atualizar um registro da entidade no banco de dados. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 10/06/2015
	 * @param entity - Entidade a ser atualizada no banco de dados.
	 * @throws ApplicationException 
	 */
	public void update(T entity) throws ApplicationException {
		try {
			manager.merge(entity);
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "alterar registro: " + e.getMessage() });
		}
	}
	
	/**
	 * Método responsável em excluir um registro do banco de dados.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 10/06/2015
	 * @param entity
	 * @throws ApplicationException 
	 */
	public void delete(T entity) throws ApplicationException {
		try {
			manager.remove(entity);
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "excluir registro: " + e.getMessage() });
		}
	}
	
	/**
	 * Método responsável em listar todos os registros persistidos no banco de dados sem ordenação.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 10/06/2015
	 * @return {@link List}<T>
	 * @throws ApplicationException 
	 */
	public List<T> listAll() throws ApplicationException {
		try {
			CriteriaQuery<T> criteria = (CriteriaQuery<T>) manager.getCriteriaBuilder().createQuery(getThisClass());
			criteria.from(getThisClass());
			
			return manager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "consultar: " + e.getMessage() });
		}
	}
	
	/**
	 * Método responsável em listar todos os registros persistidos no banco de dados. com ou sem ordenação
	 * 
	 * Exemplo para ordenar: new String[] {"atributo", "ordem(asc, desc)"}
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 14/07/2015
	 * @param ordem : {@link String}[]
	 * @return {@link List}
	 * @throws ApplicationException 
	 */
	public List<T> listAll(String... ordem) throws ApplicationException {
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(getThisClass());
			Root<T> rootEntry = criteria.from(getThisClass());
			CriteriaQuery<T> todos = criteria.select(rootEntry);
			
			if (ordem != null) {
				List<Order> orders = new ArrayList<Order>();
				
				for (int i  = 0; i  < ordem.length; i += 2) {
					if (ordem[i + 1].equals(Dominios.ORDER_ASC)) {
						orders.add(builder.asc(rootEntry.get(ordem[i])));
					} else if (ordem[i + 1].equals(Dominios.ORDER_DESC)) {
						orders.add(builder.desc(rootEntry.get(ordem[i])));
					}
				}
				
				criteria.orderBy(orders);
			}
			
			TypedQuery<T> qTodos = manager.createQuery(todos);
			
			return qTodos.getResultList();
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "listar: " + e.getMessage() });
		}
	}
	
	/**
	 * Método responsável em obter um registro de uma entidade no banco de dados a partir de sua chave primária.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 10/06/2015
	 * @param id {@link Serializable}
	 * @return entidade
	 * @throws ApplicationException 
	 */
	public T getEntity(Serializable id) throws ApplicationException {
		try {
			return manager.find(this.getThisClass(), id);
		} catch (NoResultException e) {
			throw new ApplicationException("msg.AVISO.registro.nao.encontrado");
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.consulta", new String[] { e.getMessage() });
		}
	}
	
	/**
	 * Traduz o valor de um campo substituindo os caracteres com acentuação para caracteres sem acentuação.
	 * @param campo : String
	 * @return : String
	 * @throws ApplicationException
	 */
	protected String translate(String campo) throws ApplicationException {
		try {
			return "TRANSLATE(" + campo + ", 'ÀÁáàãÉÈéèÍíÓóÒòÚúÇç', 'AAaaaEEeeIiOoOoUuCc')";
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "consultar: " + e.getMessage() });
		}
	}
	
	/**
	 * Prepara a declarção WHERE da consulta JPQL.
	 * @param jpql : StringBuilder
	 * @return String
	 * @throws ApplicationException
	 */
	protected String prepareWhere(StringBuilder jpql) throws ApplicationException {
		try {
			return jpql.toString().replace(jpql.toString().substring(4, jpql.toString().length()), "WHERE ");
		} catch (Exception e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { "consultar: " + e.getMessage() });
		}
	}
	
	/**
	 * Método responsável em obter o a classe tipada da instância .
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 14/07/2015
	 * @return {@link Class}
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getThisClass () {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) (type).getActualTypeArguments()[0];
	}
	
}
