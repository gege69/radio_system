package br.com.radio.json;

import java.io.Serializable;
import java.util.Collection;

public class JSONListWrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Collection<T> rows;
	
	private int total;
	
	public JSONListWrapper(Collection<T> rows, int total){
		this.rows = rows;
		this.total = total;
	}
	
	public JSONListWrapper(Collection<T> rows, Long total){
		this.rows = rows;
		this.total = total.intValue();
	}
	

	public Collection<T> getRows()
	{
		return rows;
	}

	public int getTotal(){
		return total;
	}
}
