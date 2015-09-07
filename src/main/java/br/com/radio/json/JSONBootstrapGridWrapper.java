package br.com.radio.json;

import java.io.Serializable;
import java.util.List;

public class JSONBootstrapGridWrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> rows;
	
	private int total;
	
	public JSONBootstrapGridWrapper(List<T> rows, int total){
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows()
	{
		return rows;
	}

	public int getTotal()
	{
		return total;
	}

	
}
