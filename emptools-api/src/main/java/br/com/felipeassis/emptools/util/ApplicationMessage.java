package br.com.felipeassis.emptools.util;

import java.io.Serializable;

import com.google.gson.JsonObject;

/**
 * Classe definida para disparar mensagens (gerais ou de regras negociais em
 * todo sistema).
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 06/07/2015
 */
public class ApplicationMessage implements Serializable {

	private static final long serialVersionUID = -5823010829871034096L;

	/**
	 * Constante referente ao ícone de sucesso.
	 */
	public static final int ICON_SUCESSO = 1;

	/**
	 * Constante referente ao ícone de informação.
	 */
	public static final int ICON_INFO = 2;

	/**
	 * Constante referente ao tipo de mensagem
	 */
	public static final String MSG_TYPE_ERROR = "error";
	public static final String MSG_TYPE_WARNING = "warning";
	public static final String MSG_TYPE_SUCCESS = "error";

	/**
	 * Tipo de ícone apresentado na mensagem
	 */
	private int icone = -1;

	private String mensagem;

	public ApplicationMessage(String chaveMensagem) {
		mensagem = Mensagem.getInstance().getMessage(chaveMensagem);
	}

	public ApplicationMessage(String chaveMensagem, String... parametros) {
		mensagem = Mensagem.getInstance().getMessage(chaveMensagem, parametros);
	}

	public ApplicationMessage(String chaveMensagem, int tipoIcone) {
		mensagem = Mensagem.getInstance().getMessage(chaveMensagem);
		icone = tipoIcone;
	}

	public ApplicationMessage(String chaveMensagem, String[] parametros, int tipoIcone) {
		mensagem = Mensagem.getInstance().getMessage(chaveMensagem, parametros);
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
	 * Retorna o valor do atributo mensagem.
	 * @return mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * Seta o valor do atributo mensagem.
	 * @param mensagem mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Returns the message in JSON format.
	 * @param type: String
	 * @param message: String
	 * @return JsonObject
	 */
	public static JsonObject getJsonMessage(String type, String message) {
		JsonObject json = new JsonObject();
		json.addProperty("type", type);
		json.add("message", json);
		
		return json;
	}
}
