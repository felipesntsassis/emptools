package br.com.felipeassis.emptools.util;

import java.io.File;
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
	public static final String APP_PERSISTENCE_UNIT = "RHFTalentos";
	public static final String TEST_PERSISTENCE_UNIT = "RHFTalentosTeste";
	public static final String ARQUIVO_MESSAGES = "messages_pt_BR.properties";

	/**
	 * Constantes para apontar os caminhos físicos de armazenamento de arquivos no servidor.
	 */
	public static final String PATH_WILDFLY_DATA_DIR = System.getProperty("jboss.server.data.dir");
	public static final String PATH_RHFTALENTOS_DATA_DIR = PATH_WILDFLY_DATA_DIR + File.separator + "rhftalentos";
	public static final String PATH_FRANQUEADO = PATH_RHFTALENTOS_DATA_DIR + File.separator + "franquias";
	public static final String PATH_FRANQUEADO_CAPA = PATH_FRANQUEADO + File.separator + "portal" + File.separator + "web";
	public static final String PATH_FRANQUEADO_CAPA_MOBILE = PATH_FRANQUEADO + File.separator + "portal" + File.separator + "mobile";
	public static final String PATH_FRANQUEADO_AUXILIAR = PATH_FRANQUEADO + File.separator + "usuarios";
	public static final String PATH_EMPRESA = PATH_RHFTALENTOS_DATA_DIR + File.separator + "empresas";
	public static final String PATH_CANDIDATO = PATH_RHFTALENTOS_DATA_DIR + File.separator + "candidatos";
	public static final String PATH_USUARIO = PATH_RHFTALENTOS_DATA_DIR + File.separator + "usuarios";
	public static final String PATH_IMAGENS_PADRAO = PATH_RHFTALENTOS_DATA_DIR + File.separator + "padroes";
	public static final String PATH_IMAGEM_AVATAR_MASCULINO = PATH_IMAGENS_PADRAO + File.separator + "avatar-h.png";
	public static final String PATH_IMAGEM_AVATAR_FEMININO = PATH_IMAGENS_PADRAO + File.separator + "avatar-m.png";

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
	 * Constantes para definir o nome das seções do portal.
	 */
	public static final String NOME_SECAO_HOME = "home";
	public static final String NOME_SECAO_CANDIDATOS = "candidatos";
	public static final String NOME_SECAO_EMPRESAS = "empresas";
	public static final String NOME_SECAO_FRANQUIAS = "franquias";

	/**
	 * Constantes para definir o nome dos módulos dos Paineis.
	 */
	public static final String MODULO_INICIO = "inicio";
	public static final String MODULO_CADASTROS = "cadastros";
	public static final String MODULO_FRANQUIAS = "franquias";
	public static final String MODULO_EMPRESAS = "empresas";
	public static final String MODULO_CURRICULO = "curriculo";
	public static final String MODULO_CURRICULOS = "candidatos";
	public static final String MODULO_CANDIDATOS = "candidatos";
	public static final String MODULO_FINANCEIRO = "financeiro";
	public static final String MODULO_PUBLICIDADE = "publicidade";
	public static final String MODULO_USUARIO = "usuario";
	public static final String MODULO_MATERIAIS = "materiais";
	public static final String MODULO_CONFIGURACOES = "configuracoes";
	public static final String MODULO_SAIR = "sair";
	public static final String MODULO_FOLLOW_UP = "followUp";
	public static final String MODULO_VAGAS = "vagas";
	public static final Object MODULO_PROCESSOS_SELETIVOS = "processosSeletivos";
	public static final String MODULO_COMPROMISSOS = "compromissos";
	public static final String MODULO_DOCUMENTOS = "documentos";
	public static final String MODULO_SERVICOS = "servicos";
	public static final String MODULO_ALTERAR_SENHA = "alterarSenha";
	public static final String MODULO_PUBLICAR_DEPOIMENTO = "publicarDepoimento";
	public static final String MODULO_FAQ = "faq";
	public static final String MODULO_GRUPOS_ACESSO = "gruposDeAcesso";

	/**
	 * Constante para parametrizar limites de dermiados tipos de dados.
	 */
	public static final Integer LIMITE_CONTATOS_TELEFONICOS = 10;

	/**
	 * Constantes para definir a ordenação das consultas.
	 */
	public static final String ORDEM_ASC = "ASC";
	public static final String ORDEM_DESC = "DESC";

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
	 * Constantes para definir as ações de tela.
	 */
	public static final String ACAO_TELA_CADASTRAR = "Cadastrar";
	public static final String ACAO_TELA_CANCELAR = "Cancelar";
	public static final String ACAO_TELA_EDITAR = "Editar";
	public static final String ACAO_TELA_ENCERRAR = "Encerrar";
	public static final String ACAO_TELA_EXIBIR = "Exibir";
	public static final String ACAO_TELA_EXCLUIR = "Excluir";
	public static final String ACAO_TELA_LISTAR = "Listar";
	public static final String ACAO_TELA_NOVO = "Novo";
	public static final String ACAO_TELA_NOVA = "Nova";
	public static final String ACAO_TELA_ANUNCIAR = "Anunciar";
	public static final String ACAO_TELA_PRE_CADASTRAR = "Pré-Cadastrar";
	public static final String ACAO_TELA_PROCURAR = "Procurar";
	public static final String ACAO_TELA_MINHAS_VAGAS = "Minhas Vagas";
	public static final String ACAO_TELA_VAGAS_CLIENTES = "Vagas de Clientes";
	public static final String ACAO_FOLLOW_UP_FRANQUEADO = "Follow Up! - Franqueado";
	public static final String ACAO_FOLLOW_UP_EMPRESA = "Follow Up! - Empresa";
	public static final String ACAO_VINCULAR = "Vincular";
	public static final String ACAO_DESVINCULAR = "Desvincular";

	/**
	 * Constantes para consultas SQL/JPQL
	 */
	public static final String OPERADOR_AND = "AND";
	public static final String OPERADOR_OR = "OR";
	public static final String CLAUSULA_WHERE = "WHERE";
	
	/**
	 * Constantes para o componente de Paginação
	 */
	public static final int ROW_COUNT = 10;
	public static final int ROW_COUNT_AMOSTRA = 50;
	public static final int ROW_COUNT_FILTRO_PAGINA_INICIAL= 50;

	/**
	 * Constantes para definir as cores de estado do compromisso.
	 */
	public static final String COR_COMPROMISSO_CANCELADO = "#D9534F";
	public static final String COR_COMPROMISSO_ENCERRADO = "#5CB85C";
	public static final String COR_COMPROMISSO_NAO_CONFIRMADO = "#F0AD4E";
	public static final String COR_COMPROMISSO_FUTURO = "#5BC0DE";

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
