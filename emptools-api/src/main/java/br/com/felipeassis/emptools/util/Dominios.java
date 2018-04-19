package br.com.felipeassis.emptools.util;

import java.util.Calendar;

/**
 * Classe definida para responsável em implementar as constantes padrões utilizadas pela aplicação. 
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 15/06/2015
 */
public class Dominios {
	/**
	 * Constantes responsáveis em definir o nome das unidades de persistência JPA da aplicação.
	 */
	public static final String APP_PERSISTENCE_UNIT = "EmpTools";
	public static final String ARQUIVO_MESSAGES = "messages_pt_BR.properties";

	/**
	 * Constante para proibir a especificação
	 */
	public static final String NAO_ESPECIFICADO = "Não especificado";

	/**
	 * Constantes para definir qual painel é acesseado no momento
	 */
	public static final Integer PAINEL_ADMINISTRATIVO = 1;
	public static final Integer PAINEL_CANDIDATO = 2;
	public static final Integer PAINEL_EMPRESA = 3;
	public static final Integer PAINEL_FRANQUEADO = 4;

	/**
	 * Constantes para definir a ordenação das consultas.
	 */
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";

	/**
	 * Constantes para definir etapa do cadastro
	 */
	public static final String ETAPA_DADOS_PESSOAIS = "Dados Pessoais";
	public static final String ETAPA_DADOS_PROFISSIONAIS = "Dados Profissionais";
	public static final String ETAPA_DADOS_SEU_PERFIL = "Seu Perfil";
	public static final String ETAPA_CONFIRMACAO_FORMULARIO = "Confirmação do Formulário";

	/**
	 * Constante para definir o ano mínimo permitido ao informar o período de uma experiência Profissional.
	 */
	public static final Integer ANO_EXPERIENCIA_MINIMO = Calendar.getInstance().get(Calendar.YEAR) - 60;
	
	/**
	 * Constantes para obter os grupos de acesso do sistema
	 */
	public static final Integer GRUPO_ACESSO_FRANQUIAS = 2;

	/**
	 * Constantes para consultas SQL/JPQL
	 */
	public static final String AND_OPERATOR = "AND";
	public static final String OR_OPERATOR = "OR";
	public static final String WHERE_CLAUSULE = "WHERE";
	
	/**
	 * Constantes para o componente de Paginação
	 */
	public static final int ROW_COUNT = 10;
	public static final int ROW_COUNT_AMOSTRA = 50;
	public static final int ROW_COUNT_FILTRO_PAGINA_INICIAL= 50;

	/**
	 * Constantes para definir a origem de inativação de conta
	 */
	public static final Integer ORIGEM_INATIVACAO_CANDIDATO = 1;
	public static final Integer ORIGEM_INATIVACAO_EMPRESA = 1;

	/**
	 * Constantes para definir opções do datagrid
	 */
	public static final Object OPCAO_ALL = "all";
	public static final String CONFIDENCIAL = "Confidencial";

	/**
	 * Constantes para definir as URLs padrão
	 */
	public static final String URL_CADASTRO_CURRICULO = "http://www.rhf.com.br/candidato/cadastro";
	public static final String URL_RHF = "http://www.rhf.com.br";
	public static final String URL_LOCALHOST = "http://localhost:8080";
	
	/**
	 * Constantes para definir campos vazios
	 */
	public static final String CAMPO_VAZIO = "---";
	public static final String LIVRE_PARA_VINCULO = "Livre para Vínculo";
}
