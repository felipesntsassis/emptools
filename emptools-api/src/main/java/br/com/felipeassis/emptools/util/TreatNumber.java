package br.com.felipeassis.emptools.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.felipeassis.emptools.exception.ApplicationException;


/**O
 * Classe utilitária responsável em implementar métodos para tratamento de atributos numéricos.  
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 26/01/2016
 */
public class TreatNumber {
	/**
	 * Método responsável em converter um valor em moeda (R$) em BigDecimal 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 02/08/2015
	 * @param moeda {@link String}
	 * @return {@link BigDecimal}
	 */
	
	public static BigDecimal converterMoedaParaBigDecimal(String moeda) {
		BigDecimal valor = null;
		
		if (StringUtils.isNotBlank(moeda)) {
			moeda = moeda.replace("R$ ", "");
			valor = BigDecimal.valueOf(Double.parseDouble(moeda.replaceAll("\\.", "").replaceAll(",", ".")));
		}
		
		return valor;
	}
	
	/**
	 * Método responsável em converter um valor em moeda (R$) em Float. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 02/08/2015
	 * @param moeda {@link String}
	 * @return {@link Float}
	 */
	public static Float converterMoedaParaFloat(String moeda) {
		Float valor = null;
		
		if (StringUtils.isNotBlank(moeda)) {
			moeda = moeda.replace("R$ ", "");
			valor = Float.valueOf(Float.parseFloat(moeda.replaceAll("\\.", "").replaceAll(",", ".")));
		}
		
		return valor;
	}
	
	/**
	 * Verifica se um valor numérico é nulo ou vazio.
	 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
	 * @since 02/08/2015
	 * @param valor : {@link Number}
	 * @return {@link Boolean}
	 */
	public static Boolean isNullOrZero(Number valor) {
		return (valor == null || valor.equals(0) ? true : false);
	}
	
	/**
	 * Verifica se um determinado valor numérico nãoé nulo ou vazio. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 02/08/2015
	 * @param valor : {@link Number}
	 * @return {@link Boolean}
	 */
	public static Boolean isNotNullOrZero(Number valor) {
		if (valor == null) {
			return false;
		}
		if (valor.intValue() <= 0) {
			return false;
		}
		
		return true;
	}

	/**
	 * Converte uma lista de códigos codificados em base64 para Inteiro
	 * @param listaCodificada
	 * @return
	 * @throws NumberFormatException
	 * @throws ApplicationException
	 */
	public static List<Integer> converterListaParaInteiro(List<String> listaCodificada) throws NumberFormatException, 
		ApplicationException {
		List<Integer> listaCodigo = new ArrayList<Integer>();
		
		for (String codigo : listaCodificada) {
			if (!codigo.equals(Dominios.OPCAO_ALL)) {
				listaCodigo.add(Integer.valueOf(TreatString.decodificarBase64(codigo)));
			}
		}
		
		return listaCodigo;
	}

	/**
	 * Verifica se uma String é numérica.
	 * @param value: {@link String}
	 * @return {@link Boolean}
	 */
	public static Boolean isNumeric(String value) {
		if (value != null) {
			return StringUtils.isNumeric(value);
		}
		
		return false;
	}
}
