package br.com.felipeassis.emptools.util;

/**
 * Classe utilitária definida para implementar componentes HTML 
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.rhf.util
 * @since 14/04/2017
 */
public class TreatHtml {

	/**
	 * Renderiza um ícone de notificação;
	 * @param descricao: {@link String}
	 * @param classeCss: {@link String}
	 * @param icone: {@link String}
	 * @param iconePequeno: {@link String}
	 * @return {@link String}
	 */
	public static String renderizarStatus(String descricao, String classeCss, String icone, Boolean iconePequeno) {
		StringBuilder situacao = new StringBuilder("<div class=\"text-center\">")
			.append("<i class=\"status-rhf fa " + icone + (!iconePequeno ? " fa-2x" : " fa-lg"))
			.append(" " + classeCss + "\"")
			.append((TreatString.isNotBlank(descricao) ? " data-toggle=\"tooltip\" title=\"" + descricao + "\"" : ""))
			.append("></i>")
			.append("</div>");
		
		return situacao.toString();
	}

	/**
	 * Rendereiza uma label de status descritivo.
	 * @param descricao: {@link String}
	 * @param cssCor: {@link String}
	 * @return {@link String}
	 */
	public static String renderizarLabel(String descricao, String cssCor) {
		StringBuilder label = new StringBuilder("<div class=\"text-center\">")
			.append("<span class=\"label label-lg " + cssCor + "\">")
			.append(descricao)
			.append("</span>")
			.append("</div>");
		
		return label.toString();
	}
	/**
	 * Renderiza o conteúdo vazio de uma coluna de tabela ou datagrid.
	 * @return {@link String}
	 */
	public static String renderizarColunaVazia() {
		StringBuilder campoVazio = new StringBuilder("<div class=\"text-center\">")
				.append(Dominios.CAMPO_VAZIO)
				.append("</div>");
		return campoVazio.toString();
	}
}
