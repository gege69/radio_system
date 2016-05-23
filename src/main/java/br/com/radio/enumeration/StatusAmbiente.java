package br.com.radio.enumeration;

public enum StatusAmbiente {
	
	ATIVO( "Ativo" ),
	INATIVO( "Inativo" ),
	BLOQUEADO( "Bloqueado" );
	
	private String descricao;
	
	private StatusAmbiente( String descricao )
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}

	

}
