package br.com.felipeassis.emptools.exception;

import br.com.felipeassis.emptools.util.Mensagem;

/**
 * Classe definida para disparar as Exceções com mensagens em toda aplicação. 
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.exception
 * @since 11/06/2015
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = -9216438322217273635L;
	
	/**
	 * Constante referente ao ícone de erro.
	 */
	public static final int ICON_ERRO = 1;
	
	/**
	 * Constante referente ao ícone de alerta.
	 */
	public static final int ICON_AVISO = 2;
	
	/**
	 * Guarda o tipo de ícone apresentado na mensagem.
	 */
	private int icone = -1;
	
	/**
	 * Guarda a exceção ocorrida.
	 */
	protected Throwable causaRaiz = null;
	
	/**
	 * 
	 * Construtor da classe ApplicationException 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 11/06/2015
	 * @param chaveMensagem : {@link String}
	 */
	public ApplicationException(String chaveMensagem) {
		super(Mensagem.getInstance().getMessage(chaveMensagem));
	}

	/**
	 * Construtor da classe ApplicationException .
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param tipoIcone : int
	 */
	public ApplicationException(String chaveMensagem, int tipoIcone) {
		super(Mensagem.getInstance().getMessage(chaveMensagem));
		icone = tipoIcone;
	}
	
	/**
	 * Construtor da classe ApplicationException.
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param parametros : {@link String}[]
	 */
	public ApplicationException(String chaveMensagem, String[] parametros) {
		super(Mensagem.getInstance().getMessage(chaveMensagem, parametros));
	}
	
	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param parametros : {@link String}[]
	 * @param tipoIcone : int
	 */
	public ApplicationException(String chaveMensagem, String[] parametros, int tipoIcone) {
		super(Mensagem.getInstance().getMessage(chaveMensagem, parametros));
		icone = tipoIcone;
	}
	
	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param causa : {@link Throwable}
	 */
	public ApplicationException(Throwable causa) {
		super(Mensagem.getInstance().getMessage("msg.ERRO.padrao"));
		setCausaRaiz(causa);
	}
	
	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param causa : {@link Throwable}
	 * @param tipoIcone : int
	 */
	public ApplicationException(Throwable causa, int tipoIcone) {
		super(Mensagem.getInstance().getMessage("msg.ERRO.padrao"));
		setCausaRaiz(causa);
		icone = tipoIcone;
	}
	
	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param causa : {@link Throwable}
	 */
	public ApplicationException(String chaveMensagem, Throwable causa) {
		super(Mensagem.getInstance().getMessage(chaveMensagem));
		setCausaRaiz(causa);
	}
	
	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param causa : {@link Throwable}
	 * @param tipoIcone : int
	 */
	public ApplicationException(String chaveMensagem, Throwable causa, int tipoIcone) {
		super(Mensagem.getInstance().getMessage(chaveMensagem));
		setCausaRaiz(causa);
		icone = tipoIcone;
	}

	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param parametros : {@link String}[]
	 * @param causa : {@link Throwable}
	 */
	public ApplicationException(String chaveMensagem, String[] parametros, Throwable causa) {
		super(Mensagem.getInstance().getMessage(chaveMensagem, parametros));
		setCausaRaiz(causa);
	}
	
	/**
	 * Construtor da classe ApplicationException. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 25/07/2015
	 * @param chaveMensagem : {@link String}
	 * @param parametros : {@link String}[]
	 * @param causa : {@link Throwable}
	 * @param tipoIcone : int
	 */
	public ApplicationException(String chaveMensagem, String[] parametros, Throwable causa, int tipoIcone) {
		super(Mensagem.getInstance().getMessage(chaveMensagem, parametros));
		setCausaRaiz(causa);
		icone = tipoIcone;
	}

	/**
	 * Retorna o valor do atributo icone.
	 * @return icone
	 */
	public int getIcone() {
		return icone;
	}

	/**
	 * Seta o valor do atributo icone.
	 * @param icone icone
	 */
	public void setIcone(int icone) {
		this.icone = icone;
	}

	/**
	 * Retorna o valor do atributo causaRaiz.
	 * @return causaRaiz
	 */
	public Throwable getCausaRaiz() {
		return causaRaiz;
	}

	/**
	 * Seta o valor do atributo causaRaiz.
	 * @param causaRaiz causaRaiz
	 */
	public void setCausaRaiz(Throwable causaRaiz) {
		this.causaRaiz = causaRaiz;
	}
}
