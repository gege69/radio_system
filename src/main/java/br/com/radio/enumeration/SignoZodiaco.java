package br.com.radio.enumeration;

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

}
