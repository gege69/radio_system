package br.com.radio.dto;

import java.io.Serializable;
import java.util.List;

import br.com.radio.model.Ambiente;

public class AmbienteListDTO implements Serializable {

	private static final long serialVersionUID = 2390163286977944387L;
	
	private List<Ambiente> lista;

	public List<Ambiente> getLista()
	{
		return lista;
	}

	public void setLista( List<Ambiente> lista )
	{
		this.lista = lista;
	}
	
	
	
}
