package br.com.felipeassis.emptools.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import com.google.common.hash.Hashing;

import br.com.felipeassis.emptools.exception.ApplicationException;

/**
 * Classe utilitária responsável em implementar métodos para tratamento de String.
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 26/01/2016
 */
public class TreatString {
	/**
	 * Método responsável em alterar o primeiro caractere de uma String para maíusculos e e outros para letras 
	 * minúsculos.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 28/07/2015
	 * @param str : {@link String}
	 * @return {@link String}
	 */
	public static String formatarPrimeiraLetraMaiuscula (String str) {
		String valor = "";
		
		if (StringUtils.isNotBlank(str)) {
			valor = Character.toUpperCase(str.charAt(0)) + str.substring(1);
			valor = valor.substring(0, 1) +  valor.substring(1, valor.length()).toLowerCase();
		}
		
		return valor;
	}
	
	/**
	 * Método responsável em remover toda máscara de entrada em campos como (CPF, CEP, RG, Telefone, 
	 * entre outros) de um determinado campo. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 03/08/2015
	 * @param valor : {@link String}
	 * @return {@link String}
	 */
	public static String removerMascara(String valor) {
		if (StringUtils.isNotBlank(valor)) {
			valor = valor.replaceAll("\\.", "");
			valor = valor.replaceAll("/", "");
			valor = valor.replaceAll("-", "");
			valor = valor.replace("\\", "");
			valor = valor.replace("(", "");
			valor = valor.replace(")", "");	
			
			return valor;
		}
		
		return null;
	}
	
	/**
	 * Método responsável em verificar se o valor de uma string é nula ou vazia.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 09/10/2015
	 * @param valor : {@link String}
	 * @return {@link String}
	 */
	public static Boolean isBlank(String valor) {
		return (valor == null || valor.trim() == "");
	}
	
	/**
	 * Método responsável verificar se o valor de uma string não é nula ou vazia. 
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 09/10/2015
	 * @param valor : {@link String}
	 * @return {@link Boolean}
	 */
	public static Boolean isNotBlank(String valor) {
		return (valor != null && valor.length() > 0);
	}
	
	/**
	 * Método responsável em aplicar máscara de entrada em um determinado valor.
	 * 
	 * @funcionalidade rhftalentos - Nome_Funcionalidade
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @param pattern: {@link String}
	 * @param valor: valor {@link Object}
	 * @since 26/12/2016
	 * @return {@link String}
	 */
	public static String formatar(String pattern, Object valor) throws ParseException {
		MaskFormatter mascara = new MaskFormatter(pattern);
		mascara.setValueContainsLiteralCharacters(false);
		
		try {
			return mascara.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Função responsável em formatar um CPF com máscara de entrada.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 26/10/2016
	 * @return {@link String}
	 */
	public static String formatarCPF(String valor) throws ParseException {
		return TreatString.formatar("###.###.###-##", valor);
	}
	
	/**
	 * Função responsável em formatar um CNPJ com máscara de entrada.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 26/10/2016
	 * @return {@link String}
	 */
	public static String formatarCNPJ(String valor) throws ParseException {
		return TreatString.formatar("##.###.###/####-##", valor);
	}
	
	/**
	 * Função responsável em formatar um CEP com máscara de entrada.
	 * 
	 * @funcionalidade rhftalentos - Nome_Funcionalidade
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 26/10/2016
	 * @return {@link String}
	 */
	public static String formatarCEP(String valor) throws ParseException {
		return TreatString.formatar("##.###-##", valor);
	}
	
	/**
	 * Função responsável em formatar um CEP com máscara de entrada.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 26/10/2016
	 * @return {@link String}
	 */
	public static String formatarTelefone(String valor) throws ParseException {
		if (valor.length() == 11) {
			return TreatString.formatar("(##)#####-####", valor);
		} else {
			return TreatString.formatar("(##)####-####", valor);
		}
	}
	
	/**
	 * Formata uma data no padrão dd/MM/yyyy.
	 * 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 26/10/2016
	 * @return {@link String}
	 */
	public static String formatarData(Date data) {
		if (data == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return sdf.format(data);
	}

	/**
	 * Formata e retorna um valor em formato monetário.
	 * @funcionalidade rhftalentos - Nome_Funcionalidade
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 27/01/2016
	 * @return {@link String}
	 */
	public static String formatarMoeda(Double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		return nf.format(valor);
	}
	
	/**
	 * Remove todas as acentuações e retorna a string sem acentuações.
	 * @param valor : String
	 * @return String
	 */
	public static String removerAcentos(String valor) {
		valor = Normalizer.normalize(valor, Normalizer.Form.NFD);
		valor = valor.replaceAll("[^\\p{ASCII}]", "");
		
		return valor;
	}
	
	/**
	 * Gera uma chave de segurança no padrão MD5 a partir de uma combinação qualquer.
	 * @param str : String
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String gerarToken(String str) throws NoSuchAlgorithmException {
		try {
			if (isBlank(str)) {
				return null;
			}
			
			MessageDigest digest = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, digest.digest());
			str= hash.toString();
			
			if (str.length() % 2 != 0) {
				str = "0" + str;
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	/**
	 * Remove todos os espaços em branco de todos os atributos do tipo String de uma classe por reflection.
	 * @param obj : Object
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void trimAll(Object obj) throws IllegalArgumentException, IllegalAccessException {
		if (TreatNull.isNotNull(obj)) {
			for (Field field : obj.getClass().getDeclaredFields()) {
				Boolean accessible = field.isAccessible();
				field.setAccessible(true);
				
				if (field.getType().isAssignableFrom(String.class)) {
					if (TreatNull.isNotNull(field.get(obj))) {
						String value = (String) field.get(obj);
						field.set(obj, value.trim());
						field.setAccessible(accessible);
					}
				}
			}
		}
	}
	
	/**
	 * Codifica uma senha com Hash MD5.
	 * @param senha : String
	 * @return String
	 */
	public static String criptografarSenha(String senha) throws Exception {
		if (isNotBlank(senha)) {
			try {
				return Hashing.md5().hashBytes(senha.getBytes("UTF-8")).toString();
			} catch (UnsupportedEncodingException e) {
				throw new Exception("Erro a criptografar senha: " + e.getMessage());
			}
		}
		
		return null;
	}
	
	/**
	 * Codifica o valor de uma string com Hash SHA-256.
	 * @param string: String
	 * @return String
	 * @throws Exception
	 */
	public static String cripitografarStringSha256(String string) throws Exception {
		if (isNotBlank(string)) {
			return Hashing.sha256().hashBytes(string.getBytes("UTF-8")).toString();
		}
		
		return null;
	}

	/**
	 * Codifica e retorna um valor numérico em Base64.
	 * @param codigo : {@link Number}
	 * @return {@link String}
	 * @throws ApplicationException
	 */
	public static String codificarBase64(Number codigo) throws ApplicationException {
		try {
			if (codigo != null) {
				return Base64.getEncoder().encodeToString(codigo.toString().getBytes("UTF-8"));
			}
			
			return null;
		} catch (UnsupportedEncodingException e) {
			throw new ApplicationException("msg.ERRO.padrao", new String[] { e.getMessage() });
		}
	}

	/**
	 * Decodifica e retorna um valor formatado em base 64.
	 * @param codigo
	 * @return
	 * @throws ApplicationException
	 */
	public static String decodificarBase64(String codigo) throws ApplicationException {
		if (isNotBlank(codigo)) {
			return new String(Base64.getDecoder().decode(codigo));
		}
		
		return null;
	}

	/**
	 * Inclui a cláusula "Where" e retorna a consulta JPQL completa.
	 * @param jpql: StringBuilder
	 * @return StringBuilder
	 */
	public static StringBuilder replaceWhere(StringBuilder jpql) {
		if (TreatNull.isNull(jpql)) {
			return null;
		}
		if (jpql.toString().contains(Dominios.OPERADOR_AND)) {
			int index = jpql.indexOf(Dominios.OPERADOR_AND);
			
			jpql = jpql.replace(index, index + 3, Dominios.CLAUSULA_WHERE);
		}
		
		return jpql;
	}

	/**
	 * Inclui a cláusula "Where" opcional e retorna a consulta JPQL completa.
	 * @param jpql: StringBuilder
	 * @return StringBuilder
	 */
	public static StringBuilder replaceWhereOpcional(StringBuilder jpql) {
		if (TreatNull.isNull(jpql)) {
			return null;
		}
		if (jpql.toString().contains(Dominios.OPERADOR_OR)) {
			int index = jpql.indexOf(Dominios.OPERADOR_OR);
			
			jpql = jpql.replace(index, index + 2, Dominios.CLAUSULA_WHERE);
		}
		
		return jpql;
	}

	/**
	 * Método responsável em gerar uma senha aleatória. 
	 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
	 * @since 27/10/2015
	 * @return {@link String}
	 */
	public static String gerarSenha() {
		String[] caracteres = { "0","1", "2", "3", "4", "5", "6", "7", "8", "9", 
			"a", "b", "c","d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "x", "w", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "X", "W", "Y", "Z"
		}; 
		
		String senha = "";
		
		for (int i = 0; i < 8; i++) {
			int j = (int) (Math.random() * caracteres.length);
			senha += caracteres[j];
		}
		
		System.out.println("Sua senha gerada é: " + senha);
		
		return senha;
	}

	/**
	 * Retorna a extenção de um arquivo composta em uma String.
	 * @param nomeArquivo
	 * @return {@link String}
	 */
	public static String obterExtensao(String nomeArquivo) {
		if (isNotBlank(nomeArquivo)) {
			return nomeArquivo.substring(nomeArquivo.lastIndexOf('.'), nomeArquivo.length());
		}
		
		return null;
	}
	
	public static String obterNomeArquivo(String nomeArquivo) {
		
		if (isNotBlank(nomeArquivo)) {
			return nomeArquivo.substring(0, nomeArquivo.lastIndexOf('.'));
		}
		
		return null;
	}

	/**
	 * Remove o último caracter de uma String.
	 * @param valor : {@link String}
	 * @param caractere : {@link String}
	 * @return {@link String}
	 */
	public static String removerUltimoCaractere(String valor, String caractere) {
		if (isNotBlank(valor) && isNotBlank(caractere)) {
			return valor.substring(0, valor.lastIndexOf(caractere));
		}
		
		return null;
	}

	/**
	 * Remove todas as acentuações, espaços e converte todos os caracteres para letra minúscula.
	 * @param valor : {@link String}
	 * @return {@link String}
	 */
	public static String minificarString(String valor) {
		if (isNotBlank(valor)) {
			valor = valor.replaceAll(" ", "");
			valor = valor.toLowerCase();
			valor = removerAcentos(valor);
			
			return valor;
		}

		return null;
	}

	/**
	 * Retorna uma String de acordo com a condição da flag.
	 * @param flag: boolean
	 * @param whenTrue: {@link String}
	 * @param whenFalse: {@link String}
	 * @return {@link String}
	 */
	public static String formatarFlag(boolean flag, String whenTrue, String whenFalse) {
		if (whenTrue != null && whenFalse != null) {
			if (flag == true) {
				return whenTrue;
			} else {
				return whenFalse;
			}
		}
		
		return "N/A";
	}
	/**
	 * Trunca a entrada de valores concatenando-os com reticiências (...).
	 * @param valor: {@link String}
	 * @param limite: {@link Integer}
	 * @return {@link String}
	 */
	public static String truncarValor(String valor, Integer limite) {
		if (valor.length() >= limite) {
			valor = valor.substring(0, limite) + "...";
		}
		
		return valor;
	}

	/**
	 * Capitaliza o valor de uma string convertendo a primeira letra de uma cadeia de strings para letra maíuscula.
	 * @param valor: {@link String}
	 * @return {@link String}
	 */
	public static String capitalizar(String valor) {
		if (!isBlank(valor)) {
			return StringUtils.capitalize(valor);
		}
		
		return "";
	}
}
