package br.com.radio.enumeration;

public enum PosicaoComercial {
	
	DEPOIS_MUSICAS( "Depois das Músicas" ),
	ANTES_INSTITUCIONAL( "Antes do Institucional"),
	DEPOIS_INSTITUCIONAL( "Depois do Institucional"),
	ANTES_PROGRAMETE( "Antes do Programete" ),
	DEPOIS_PROGRAMETE( "Depois do Programete" ),
	NAO_INCLUIR( "Não incluir comerciais");
	
	private String descricao;
	
	private PosicaoComercial( String descricao )
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}

	

}
