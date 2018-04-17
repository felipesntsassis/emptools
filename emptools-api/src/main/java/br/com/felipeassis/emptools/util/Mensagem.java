package br.com.felipeassis.emptools.util;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.PropertyResourceBundle;

import org.apache.log4j.Logger;

/**
 * Classe definida para implementar o sistema de manipulação das mensagens da aplicação. 
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 12/06/2015
 */
public class Mensagem {
	
	/**
	 * Atributo singleton da classe.
	 */
	private static Mensagem instance;
	private PropertyResourceBundle bundle;
	
	private static final Logger LOGGER = Logger.getLogger(Mensagem.class);
	
	/**
	 * Construtor da classe Mensagem. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since //2015
	 * @throws Exception
	 */
	public Mensagem() throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		try (InputStream resourceStream = loader.getResourceAsStream(Dominios.ARQUIVO_MESSAGES)){
			bundle = new PropertyResourceBundle(resourceStream);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Método responsável em retornar a instância do singleton Mensagem 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 12/06/2015
	 * @return {@link Mensagem}
	 * @throws Exception 
	 */
	public static Mensagem getInstance() {
		
		// Inicializa o objeto caso o mesmo ainda não tenha sido instanciado
		if (instance == null) {
			try {
				return new Mensagem();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		
		return instance;
	}
	
	/**
	 * Método responsável em retornar uma mensagem simples de erro padrão. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @return {@link String}
	 */
	public String getMessage() {
		return bundle.getString("msg.ERRO.padrao");
	}
	
	/**
	 * Método responsável em uma mensagem com parâmetros no arquivo properties. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem {@link String}
	 * @return {@link String}
	 */
	public String getMessage(String chaveMensagem) {
		return bundle.getString(chaveMensagem);
	}
	
	/**
	 * Método responsável em uma mensagem com parâmetros no arquivo properties. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem {@link String}
	 * @param parametros {@link String}[]
	 * @return {@link String}
	 */
	public String getMessage(String chaveMensagem, String... parametros) {
		return MessageFormat.format(bundle.getString(chaveMensagem), getParametros(parametros));
	}
	
	/**
	 * Método responsável em converter a lista de parâmetros do tipo String para Object para fins
	 * de resolver conflito de tipos para o método MessageFormat.format 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param parametros {@link String}[]
	 * @return {@link Object}[]
	 */
	private Object[] getParametros(String... parametros) {
		Object[] params = new Object[parametros.length];
		
		for (int i = 0; i < parametros.length; i++) {
			params[i] = parametros[i];
		}
		
		return params;
	}
}
