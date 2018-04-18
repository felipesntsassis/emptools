package br.com.felipeassis.emptools.enumerated;

import java.util.Arrays;
import java.util.List;

/**
 * Enumerador definido para mapear os meses do ano.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.rhf.enums
 * @since 02/01/2017
 */
public enum Mes {
	JANEIRO		(1, "01", "Janeiro"),
	FEVEREIRO	(2, "02", "Fevereiro"),
	MARCO		(3, "03", "Março"),
	ABRIL		(4, "04", "Abril"),
	MAIO		(5, "05", "Maio"),
	JUNHO		(6, "06", "Junho"),
	JULHO		(7, "07", "Julho"),
	AGOSTO		(8, "08", "Agosto"),
	SETEMBRO	(9, "09", "Setembro"),
	OUTUBRO		(10, "10", "Outubro"),
	NOVEMBRO	(11, "11", "Novembro"),
	DEZEMBRO	(12, "12", "Dezembro");
	
	private Integer codigo;
	private String literal;
	private String nome;
	
	private Mes(Integer codigo, String literal, String nome) {
		this.codigo = codigo;
		this.literal = literal;
		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo codigo;
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Seta o valor do atributo codigo.
	 * @param codigo : Integer
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o valor do atributo literal;
	 * @return the literal
	 */
	public String getLiteral() {
		return literal;
	}

	/**
	 * Seta o valor do atributo literal.
	 * @param literal : String
	 */
	public void setLiteral(String literal) {
		this.literal = literal;
	}

	/**
	 * Retorna o valor do atributo nome;
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o valor do atributo nome.
	 * @param nome : String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Lista todos os meses
	 * @return List<Mes>
	 */
	public static List<Mes> listar() {
		return Arrays.asList(Mes.values());
	}
	
	/**
	 * Obtem o Mes a partir do código
	 * @param codigo : Integer
	 * @return Mes
	 */
	public static Mes obter(Integer codigo) {
		for (Mes mes : Mes.values()) {
			if (codigo.equals(mes.getCodigo())) {
				return mes;
			}
		}
		
		return null;
	}
	
	/**
	 * Obtem o Mes a partir do código literal.
	 * @param literal : String
	 * @return Mes
	 */
	public static Mes obterPorLiteral(String literal) {
		for (Mes mes : Mes.values()) {
			if (literal.equals(mes.getLiteral())) {
				return mes;
			}
		}
		
		return null;
	}
}
