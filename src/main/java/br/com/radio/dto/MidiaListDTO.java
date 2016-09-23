package br.com.radio.dto;

import java.io.Serializable;
import java.util.List;

import br.com.radio.model.Midia;

public class MidiaListDTO implements Serializable {

	private static final long serialVersionUID = 2390163286977944387L;
	
	private List<Midia> lista;

	public List<Midia> getLista()
	{
		return lista;
	}

	public void setLista( List<Midia> lista )
	{
		this.lista = lista;
	}

}
