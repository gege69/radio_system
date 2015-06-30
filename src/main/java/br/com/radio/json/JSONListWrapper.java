package br.com.radio.json;

import java.io.Serializable;
import java.util.List;

public class JSONListWrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> data;
	
	private int total;
	
	public JSONListWrapper(List<T> data, int total){
		this.data = data;
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}
	
	public int getTotal(){
		return total;
	}
}
