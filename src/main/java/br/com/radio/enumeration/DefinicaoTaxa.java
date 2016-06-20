package br.com.radio.enumeration;

public enum DefinicaoTaxa {

	VALOR("$"),
	PORCENTAGEM("%");
	
	private String simbolo;

	private DefinicaoTaxa( String simbolo )
	{
		this.simbolo = simbolo;
	}

	public String getSimbolo()
	{
		return simbolo;
	}

	public void setSimbolo( String simbolo )
	{
		this.simbolo = simbolo;
	}
	
}
