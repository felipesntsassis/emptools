package br.com.felipeassis.emptools.util;

import java.io.Serializable;
import java.util.Collection;

/**
 * Classe utilitária responsável em implementar métodos para tratamento valores nulos.
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 26/01/2016
 */
public class TreatNull {
	/**
	 * Método responsável em testar se um objeto está nulo. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 03/08/2015
	 * @param object : {@link Object}
	 * @return {@link Boolean}
	 */
	public static Boolean isNull(Object object) {
		return (object == null);
}
	
	/**
	 * Método responsável em testar se um objeto não está nulo. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 09/10/2015
	 * @param object : {@link Object}O
	 * @return {@link Boolean}
	 */
	public static Boolean isNotNull(Object object) {
		return (object != null);
	}

	/**
	 * Método responsável em verificar se um valor serializável é nulo ou igual à 0. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 21/08/2015
	 * @param valor : {@link Serializable}
	 * @return {@link Boolean}
	 */
	public static Boolean isNullOrZero(Serializable valor) {
		return (valor == null || valor.equals(0));
	}

	/**
	 * Método responsável em verificar se um valor serializável não é nulo ou igual à 0.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 10/09/2015
	 * @param valor : {@link Serializable}
	 * @return {@link Boolean}
	 */
	public static Boolean isNotNullOrZero(Serializable valor) {
		return (valor != null && !valor.equals(0));
		
	}

	/**
	 * Verifica se uma {@link Collection} está nula ou vazia.
	 * @param collection : {@link Collection}
	 * @return {@link Boolean}
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * Verifica se uma {@link Collection} não está nula ou vazia.
	 * @param collection : {@link Collection}
	 * @return {@link Boolean}
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean isNotEmpty(Collection collection) {
		return (collection != null && !collection.isEmpty());
	}
}
