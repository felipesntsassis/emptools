package br.com.felipeassis.emptools.model.filter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import br.com.felipeassis.emptools.util.Dominios;

/**
 * Defined class to implement data filters.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.model.filter
 * @since 19/04/2018
 * @param <ENTITY>
 */
public class Filter<ENTITY> {

	private ENTITY entity;
	private int current;
	private int rowCount = Dominios.ROW_COUNT;
	private int total;
	private String searchPhrase;
	private String sortBy;
	private String sortDirection;
	private List<ENTITY> rows;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ENTITY getEntity() {
		try {
			if (entity == null) {
				Type type = getClass().getGenericSuperclass();
				ParameterizedType paramType = (ParameterizedType) type;
				this.entity = (ENTITY) ((Class<? extends Filter>) paramType.getActualTypeArguments()[0]).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	/**
	 * Retorna o valor do atributo current;
	 * @return current
	 */
	public int getCurrent() {
		if (current == 0) {
			current = 1;
		}
		
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public List<ENTITY> getRows() {
		return rows;
	}

	public void setRows(List<ENTITY> rows) {
		this.rows = rows;
	}

	public void setEntity(ENTITY entity) {
		this.entity = entity;
	}

	public void setFilterParams(Integer current, String searchPhrase, String sortBy, String sortDirection) {
		this.current = current;
		this.searchPhrase = searchPhrase;
		this.sortBy = sortBy;
		this.sortDirection = sortDirection;
	}
}
