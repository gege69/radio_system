package br.com.radio.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.radio.model.Midia;

public class MidiaListDTO implements Serializable {

	private static final long serialVersionUID = 2390163286977944387L;
	
	private List<Midia> lista;

	private Map<Long, Boolean> mapMidiasBlock;

	public List<Midia> getLista()
	{
		return lista;
	}

	public void setLista( List<Midia> lista )
	{
		this.lista = lista;
	}

	public Map<Long, Boolean> getMapMidiasBlock()
	{
		return mapMidiasBlock;
	}

	public void setMapMidiasBlock( Map<Long, Boolean> mapMidiasBlock )
	{
		this.mapMidiasBlock = mapMidiasBlock;
	}

}
