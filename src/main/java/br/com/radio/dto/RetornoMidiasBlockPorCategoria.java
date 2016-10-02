package br.com.radio.dto;

import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Midia;

public class RetornoMidiasBlockPorCategoria {
	
	private String codigoCategoria;
	
	private String descricaoCategoria;

	private JSONListWrapper<Midia> midias;

	public String getCodigoCategoria()
	{
		return codigoCategoria;
	}

	public void setCodigoCategoria( String codigoCategoria )
	{
		this.codigoCategoria = codigoCategoria;
	}

	public String getDescricaoCategoria()
	{
		return descricaoCategoria;
	}

	public void setDescricaoCategoria( String descricaoCategoria )
	{
		this.descricaoCategoria = descricaoCategoria;
	}

	public JSONListWrapper<Midia> getMidias()
	{
		return midias;
	}

	public void setMidias( JSONListWrapper<Midia> midias )
	{
		this.midias = midias;
	}

	public RetornoMidiasBlockPorCategoria( String codigoCategoria, String descricaoCategoria, JSONListWrapper<Midia> midias )
	{
		super();
		this.codigoCategoria = codigoCategoria;
		this.descricaoCategoria = descricaoCategoria;
		this.midias = midias;
	}

}
