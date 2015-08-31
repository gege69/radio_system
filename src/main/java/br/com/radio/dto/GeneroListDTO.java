package br.com.radio.dto;

import java.io.Serializable;
import java.util.List;

import br.com.radio.model.Genero;

public class GeneroListDTO implements Serializable {

	private static final long serialVersionUID = 2390163286977944387L;
	
	private List<Genero> lista;

	public List<Genero> getLista()
	{
		return lista;
	}

	public void setLista( List<Genero> lista )
	{
		this.lista = lista;
	}
	
	
	
}
