package br.com.radio.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum SignoZodiaco {
	
	ARIES( "Áries" ),
	TOURO( "Touro" ),
	GEMEOS( "Gêmeos" ),
	CANCER( "Câncer" ),
	LEAO( "Leão" ),
	VIRGEM( "Virgem" ),
	LIBRA( "Libra" ),
	ESCORPIAO( "Escorpião" ),
	SAGITARIO( "Sagitário" ),
	CAPRICORNIO( "Capricórnio" ),
	AQUARIO( "Aquário" ),
	PEIXES( "Peixes");
	
	private String descricao;
	
	private SignoZodiaco( String descricao )
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
	
	public static List<SignoZodiaco> getListByNames( String[] signos )
	{
		List<SignoZodiaco> result = new ArrayList<>();

		for ( String s : signos )
		{
//			SignoZodiaco sig = 
		}
		
		return result;
	}
}
