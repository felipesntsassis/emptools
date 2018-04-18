package br.com.felipeassis.emptools.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.felipeassis.emptools.enumerated.Mes;

/**
 * Classe utilitária responsável em implementar métodos para tratamento de
 * Datas.
 * 
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @package br.com.rhf.util
 * @since 26/01/2016
 */
public class TreatDate {

	/**
	 * Calcula a diferença em anos.
	 * @param dataInicio: {@link Date}
	 * @param dataFim: {@link Date}
	 * @return
	 */
	public static Integer calcularDiferencasEmAnos(Date dataInicio, Date dataFim) {
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(dataInicio);
		Calendar calFim = Calendar.getInstance();
		calFim.setTime(dataFim);
		int anoInicial = calInicio.get(Calendar.YEAR);
		int anoFim = calFim.get(Calendar.YEAR);
		
		return (anoInicial - anoFim);
	}

	/**
	 * Calcula a Diferença em meses.
	 * @param dataInicio: {@link Date}
	 * @param dataFim: {@link Date}
	 * @return Integer
	 */
	public static final Integer calcularDiferencasEmMeses(Date dataInicio, Date dataFim) {
		if (dataFim.before(dataInicio)) {
			throw new IllegalArgumentException("Datas invalidas para contagem de meses");
		}
		
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(dataInicio);
		Calendar calFim = Calendar.getInstance();
		calFim.setTime(dataFim);

		int mesesInicial = (calInicio.get(Calendar.MONTH)) + calInicio.get(Calendar.YEAR) * 12;
		int mesesFinal = (calFim.get(Calendar.MONTH)) + calFim.get(Calendar.YEAR) * 12;

		return (mesesFinal - mesesInicial);
	}

	
	/**
	 * Método para contar a diferença de dias no período de 30 dias.
	 * @param dataInicio: {@link Date}
	 * @param dataFim: {@link Date}
	 * @return Integer
	 */
	public static final Integer contarDiferencaEmDias30Dias(Date dataInicio, Date dataFim) {
		int meses = calcularDiferencasEmMeses(dataInicio, dataFim);
		
		if (meses == 1) {
			return contarDiferencaDentroMesEmDias30Dias(dataInicio, dataFim);
		}

		Integer diasMesInicio = contarDiferencaDentroMesEmDias30Dias(dataInicio, ultimoDiaMes(dataInicio));
		Integer diasMesFim = contarDiferencaDentroMesEmDias30Dias(primeiroDiaMes(dataFim), dataFim);
		Integer diasIntervalo = 0;

		if (meses >= 2) {
			diasIntervalo = (meses - 2) * 30;
		}
		
		return diasMesInicio + diasIntervalo + diasMesFim;
	}

	/**
	 * Conta a diferença em dias dentro de um mês com 30 dias.
	 * @param dataInicio: {@link Date}
	 * @param dataFim: {@link Date}
	 * @return Integer
	 */
	private static final Integer contarDiferencaDentroMesEmDias30Dias(Date dataInicio, Date dataFim) {
		Integer dias = contarDiferencaEmDias(dataInicio, dataFim);

		Calendar cal = Calendar.getInstance();
		cal.setTime(dataFim);

		int ultimoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		if (dias < ultimoDia) {
			if (cal.get(Calendar.DAY_OF_MONTH) != ultimoDia) {
				return dias;
			}
		}
		if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY) {
			return dias + (30 - ultimoDia);
		}

		return dias - (ultimoDia - 30);
	}

	/**
	 * Conta a diferença de dias em um intervalo de data.
	 * @param dataInicio: {@link Date}
	 * @param dataFim: {@link Date}
	 * @return Integer
	 */
	public static final Integer contarDiferencaEmDias(Date dataInicio, Date dataFim) {
		if (dataFim.before(dataInicio)) {
			throw new IllegalArgumentException("Datas invalidas para contagem de dias");
		}

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(clearDateTime(dataInicio));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(clearDateTime(dataFim));
		int count = 0;
		
		while (!isMesmaData(cal1.getTime(), cal2.getTime())) {
			cal1.add(Calendar.DAY_OF_MONTH, 1);
			count++;
		}
		return count + 1;
	}
	
	/**
	 * Conta e retorna a diferença de horas entre duas datas
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public static final Integer contarDiferencaEmHoras(Date dataInicio, Date dataFim) {
		if (dataFim.before(dataInicio)) {
			throw new IllegalArgumentException("Datas invalidas para contagem de horas.");
		}
		
		Calendar calInicial = Calendar.getInstance();
		calInicial.setTime(dataInicio);
		Calendar calFinal = Calendar.getInstance();
		calFinal.setTime(dataFim);
		Long minutos = ((calFinal.getTimeInMillis() - calInicial.getTimeInMillis()) / 60000);
		Long horas = minutos / 60;

		return horas.intValue();
	}

	/**
	 * Testa se as datas são o mesmo dia.
	 * @param data1: {@link Date}
	 * @param data2: {@link Date}
	 * @return Boolean
	 */
	public static Boolean isMesmaData(Date data1, Date data2) {
		return formatDefaultDate(data1).equals(formatDefaultDate(data2));
	}

	/**
	 * Obtém o ultimo dia do mês a partir da data.
	 * @param data: {@link Date}
	 * @return Date
	 */
	public static Date ultimoDiaMes(Date data) {
		Calendar atual = Calendar.getInstance();
		atual.setTime(clearDateTime(data));

		int ultimoDia = atual.getActualMaximum(Calendar.DAY_OF_MONTH);
		atual.set(Calendar.DAY_OF_MONTH, ultimoDia);
		return atual.getTime();
	}

	/**
	 * Obtem o ultimo dia do mês.
	 * @param mesReferencia : Integer
	 * @return Date
	 */
	public static Date ultimoDiaMes(int mesReferencia) {
		Calendar atual = Calendar.getInstance();
		atual.setTime(new Date());
		atual.set(Calendar.MONTH, mesReferencia - 1);
		return ultimoDiaMes(atual.getTime());
	}
	
	public static Integer ano(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Retorna o número do mês, iniciand de 1(janeiro) até 12(dezembro).
	 * @param data: {@link Date}
	 * @return Integer
	 */
	public static Integer mes(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * Retorna uma data sem horas minutos e segundos.
	 * @param data: {@link Date}
	 * @return Date
	 * @throws ParseException
	 */
	public static Date dataSemHorario(Date data) throws ParseException {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.parse(sdf.format(data));
		}

		return null;
	}

	/**
	 * Retorna o primeiro dia do mês a partir de uma data.
	 * @param data: {@link Date}
	 * @return: {@link Date}
	 */
	public static Date primeiroDiaMes(Date data) {
		Calendar atual = Calendar.getInstance();
		atual.setTime(clearDateTime(data));

		atual.set(Calendar.DAY_OF_MONTH, 1);
		return atual.getTime();
	}

	/**
	 * Retorna o primeiro dia do mês a partir de um número.
	 * @param mesReferencia : Integer
	 * @return date
	 */
	public static Date primeiroDiaMes(int mesReferencia) {
		Calendar atual = Calendar.getInstance();
		atual.setTime(new Date());
		atual.set(Calendar.MONTH, mesReferencia - 1);
		return primeiroDiaMes(atual.getTime());
	}

	/**
	 * Obtem o primeiro dia do mes a partir do mês e ano
	 * @param mes : Integer
	 * @param ano : Integer
	 * @return Date
	 */
	public static Date primeiroDiaDoMesAno(Integer mes, Integer ano) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(montaData(1, mes, ano)));
		return cal.getTime();
	}

	/**
	 * Verifica se uma data é anteruir a data atual.
	 * @param data : Boolean
	 * @return Boolean
	 */
	public static Boolean isPassado(Date data) {
		return clearDateTime(new Date()).after(clearDateTime(data));
	}

	/**
	 * Retorna a mesma data com horário minimo.
	 * @param date: {@link Date}
	 * @return Date
	 */
	public static final Date clearDateTime(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar calTemp = Calendar.getInstance();
		calTemp.setTime(date);

		Calendar cal = Calendar.getInstance();
		cal.clear();
		// seta apenas campos da data
		cal.set(Calendar.DATE, calTemp.get(Calendar.DATE));
		cal.set(Calendar.YEAR, calTemp.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, calTemp.get(Calendar.MONTH));
		
		return cal.getTime();
	}

	/**
	 * Retorna a mesma data com horário maximo.
	 * @param date: {@link Date}
	 * @return Date
	 */
	public static final Date maxDateTime(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		
		return cal.getTime();
	}

	/**
	 * Formata e retorna a data formatada no padrão (DD/MM/AAAA). 
	 * @param date: {@link Date}
	 * @return String
	 */
	public static final String formatDefaultDate(Date date) {
		return format("dd/MM/yyyy", date);
	}

	/**
	 * Formata data confome o padrao especificado.
	 * @param pattern : String
	 * @param date: {@link Date}
	 */
	public static final String format(String pattern, Date date) {
		if (date == null) {
			return "";
		}
		
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		
		return sd.format(date);
	}

	/**
	 * Retorna o primeiro dia de ano específico.
	 * @param ano : Integer
	 * @return Date
	 */
	public static Date primeiroDiaAno(Integer ano) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(new Date()));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.YEAR, ano);
		
		return cal.getTime();
	}

	/**
	 * Retorna o último dia de ano específico.
	 * @param ano : Integer
	 * @return Date
	 */
	public static Date ultimoDiaAno(Integer ano) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(new Date()));
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.YEAR, ano);
		
		return cal.getTime();
	}

	/**
	 * Retorna o ultimo dia do ano a partir de um mês e um ano específicos.
	 * @param mes : Integer
	 * @param ano : Integer
	 * @return Date
	 */
	public static Date ultimoDiaDoMesAno(Integer mes, Integer ano) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(montaData(1, mes, ano)));
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	/**
	 * Retorna o ultimo dia útil do ano a partir de um mês e um ano específicos.
	 * @param mes : Integer
	 * @param ano : Integer
	 * @return Date
	 */
	public static Date ultimoDiaUtilDoMesAno(Integer mes, Integer ano) {
		Date ultimoDiaMes = ultimoDiaDoMesAno(mes, ano);
		
		if (isDiaUtil(ultimoDiaMes)) {
			return ultimoDiaMes;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(ultimoDiaMes));
		
		do {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		} while (!isDiaUtil(cal.getTime()));
		
		return cal.getTime();
	}

	/**
	 * Obtem o ultimo dia útil do mes.
	 * @param ultimoDiaMes: {@link Date}
	 * @return Date
	 */
	public static Date ultimoDiaUtil(Date ultimoDiaMes) {
		ultimoDiaMes = ultimoDiaMes(ultimoDiaMes);

		if (isDiaUtil(ultimoDiaMes)) {
			return ultimoDiaMes;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(ultimoDiaMes));
		
		do {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		} while (!isDiaUtil(cal.getTime()));
		
		return cal.getTime();
	}

	/**
	 * Monta e retorna uma data a partir do dia, mês e ano.
	 * @param dia : Integer
	 * @param mes : Integer
	 * @param ano : Integer
	 * @return Date
	 */
	public static Date montaData(Integer dia, Integer mes, Integer ano) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(new Date()));
		cal.set(Calendar.MONTH, mes - 1);
		cal.set(Calendar.DAY_OF_MONTH, dia);
		cal.set(Calendar.YEAR, ano);
		return cal.getTime();
	}

	/**
	 * Vefifica se a data pertence a um dia útil.
	 * @param data: {@link Date}
	 * @return Boolean
	 */
	public static Boolean isDiaUtil(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int diaDaSemana = cal.get(Calendar.DAY_OF_WEEK);
		
		if (diaDaSemana >= Calendar.MONDAY && diaDaSemana <= Calendar.FRIDAY) {
			return true;
		}
	
		return false;
	}

	
	/**
	 * Obtém o primeiro dia útil a partir da ultima data.
	 * @param data: {@link Date}
	 * @return Date
	 */
	public static Date primeiroDiaUtilApartirDaData(Date data) {
		if (isDiaUtil(data)) {
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(data));
		
		do {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (!isDiaUtil(cal.getTime()));
		return cal.getTime();
	}

	/**
	 * Obtém o primeiro dia útil a partir até a uma respectiva data.
	 * @param data: {@link Date}
	 * @return Date
	 */
	public static Date primeiroDiaUtilAteData(Date data) {
		if (isDiaUtil(data)) {
			return data;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(data));
		
		do {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		} while (!isDiaUtil(cal.getTime()));
		
		return cal.getTime();
	}

	/**
	 * Adiciona dias úteis em uma data.
	 * @param data: {@link Date}
	 * @param dias : Integer
	 * @return Date
	 */
	public static Date adicionaDiasUteis(Date data, Integer dias) {
		Integer incremento = null;
		
		if (dias.equals(0)) {
			return data;
		} else if (dias.intValue() > 0) {
			incremento = 1;
		} else {
			incremento = -1;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(data));
		Integer contador = 0;
		
		while (!contador.equals(dias)) {
			cal.add(Calendar.DAY_OF_MONTH, incremento);
			if (isDiaUtil(cal.getTime())) {
				contador += incremento;
			}
		}
		
		return cal.getTime();
	}

	/**
	 * REtorna um dia a partir de uma data específica.
	 * @param data: {@link Date}
	 * @return Integer
	 */
	public static Integer dia(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Verifica se a data passada esta no mes atual
	 * @param data: {@link Date}
	 * @return Boolean
	 */
	public static Boolean isMesAtual(Date data) {
		Date comparar = clearDateTime(data);
		Date agora = new Date();

		if (primeiroDiaMes(agora).compareTo(comparar) <= 0 && ultimoDiaMes(agora).compareTo(comparar) >= 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * Retorna a descricao do mes passado
	 * @param mes : Integer- deve iniciar em 1.
	 * @return String - descricao do mes, ex "Janeiro" se passado valor 1
	 */
	public static String descricaoMes(Integer mes) {
		if (mes == null) {
			return "";
		}
		
		if (mes < 0 || mes > 12) {
			throw new IllegalArgumentException("Mes informado [" + mes + "] é inválido.");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, mes - 1);
		
		return format("MMMMM", cal.getTime());
	}

	/**
	 * Retrocede e retorna uma data a partir de uma data específica e sua quantidade de dias.
	 * @param data: {@link Date}
	 * @param dias : Integer
	 * @return Date
	 */
	public static Date retrocederDias(Date data, Integer dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(data));
		cal.add(Calendar.DAY_OF_MONTH, -dias);
		
		return cal.getTime();
	}

	/**
	 * Retrocede e retorna a quantidade de meses a partir de uma data específica e sua quantidade de meses.
	 * @param data: {@link Date}
	 * @param meses : Integer
	 * @return Date
	 */
	public static Date retrocederMeses(Date data, Integer meses) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(clearDateTime(data));
		cal.add(Calendar.MONTH, -meses);
		
		return cal.getTime();
	}

	/**
	 * Verifica a partir do mes, ano e data de comparação se o valor retornadao é um mês.
	 * @param mes : Integer
	 * @param ano : Integer
	 * @param dataComparar: {@link Date}
	 * @return Boolean
	 */
	public static Boolean isMes(Integer mes, Integer ano, Date dataComparar) {
		return (mes + "" + ano).equals(TreatDate.mes(dataComparar) + "" + TreatDate.ano(dataComparar));
	}

	/**
	 * Calcula a diferença proporcional em 30 dias.
	 * @param dataFim: {@link Date}
	 * @param diasCargaHoraria : Integer
	 * @return Integer
	 */
	public static final Integer calcularDiferencaProporcionalDiasEm30Dias(Date dataFim, Integer diasCargaHoraria) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataFim);

		if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY) {
			diasCargaHoraria = diasCargaHoraria + (30 - cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			diasCargaHoraria = diasCargaHoraria - (cal.getActualMaximum(Calendar.DAY_OF_MONTH) - 30);
		}
		return diasCargaHoraria;
	}


	/**
	 * Calcula a diferença entre períodos no formato "MM/AAAA".
	 * @param dataInicial	
	 * @param dataFinal 
	 * @return {@link Integer}
	 * @throws ParseException
	 */
	public static Integer calcularDiferencaPeriodo(Date dataFinal, Date dataInicial) throws ParseException {
		int anos, meses;
		
		anos = calcularDiferencasEmAnos(dataInicial, dataFinal);
		
		if (anos == 0) {
			meses = calcularDiferencasEmMeses(dataInicial, dataFinal);
			
			if (meses == 0) {
				return contarDiferencaDentroMesEmDias30Dias(dataInicial, dataFinal);
			}
			
			return meses;
		}
		
		return anos;
	}

	/**
	 * Testa se a data pertence ao mesmo mês.
	 * @param dataMes: {@link Date}
	 * @param dataComparar: {@link Date}
	 * @return Boolean
	 */
	public static Boolean isMesmoMes(Date dataMes, Date dataComparar) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dataMes);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dataComparar);

		return cal2.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)
				&& cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR);
	}

	/**
	 * Formata data confome o padrao especificado.
	 * @throws ParseException
	 */
	public static final Date format(String pattern, String data) throws ParseException {
		if (TreatString.isBlank(data)) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		return new Date(format.parse(data).getTime());
	}

	/**
	 * Método responsável por concatenar duas datas e forma período.
	 * @param dataInicia :Date
	 * @param dataFinal :Date
	 * @return Strig : dd/MM/yyyy a dd/MM/yyyy
	 */
	public static String concatenarPeriodo(Date dataInicial, Date dataFinal) {
		StringBuilder periodo = new StringBuilder();
		periodo.append(TreatDate.format("dd/MM/yyyy 'a' ", dataInicial));
		periodo.append(TreatDate.format("dd/MM/yyyy", dataFinal));
		
		return periodo.toString();
	}

	/**
	 * Método responsável em retornar se uma data está presente em um determinado período.
	 * @param data: {@link Date} - Data a ser verificada
	 * @param dataInicial: {@link Date} - Data incial do período
	 * @param dataFinal: {@link Date} - Data final do período
	 * @return Boolean
	 */
	public static Boolean pertenceAoPeriodo(Date data, Date dataInicial, Date dataFinal) {
		if ((data.equals(dataInicial) || data.after(dataInicial))
				&& (data.equals(dataFinal) || data.before(dataFinal))) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * Método responsável em retornar se uma data pertence ao período de uma mesma data em específico.
	 * @param data: {@link Date} - Data a ser verificada
	 * @param dataInicial: {@link Date} - Data inicial do período
	 * @param dataFinal: {@link Date} - Data final do período
	 * @return Boolean
	 */
	public static Boolean periodosIguais(Date data, Date dataInicial, Date dataFinal) {
		if (data.equals(dataInicial) && data.equals(dataFinal)) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * Retorna o formato literal de um período no formato "MM/aaaa".
	 * @param periodo : String
	 * @return String
	 */
	public static String formatarPeriodo(String periodo) {
		if (periodo != null && periodo.contains("/")) {
			String[] partesPeriodo = periodo.split("/");
			String mes = "";
			String ano = partesPeriodo[1];
			
			if (partesPeriodo[0].length() == 2) {
				mes = Mes.obterPorLiteral(partesPeriodo[0]).getNome();
			} if (partesPeriodo[0].length() == 1) {
				mes = Mes.obter(Integer.valueOf(partesPeriodo[0])).getNome();
			}
			
			return mes + " de " + ano;
			
		}
		
		return null;
	}

}
