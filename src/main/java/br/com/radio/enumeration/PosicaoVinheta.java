package br.com.radio.enumeration;

public enum PosicaoVinheta {

	ANTES_CADA_MUSICA("AM", "Antes de cada música"),
	ANTES_BLOCO_COMERCIAL("AC", "Antes do bloco Comercial"),
	DEPOIS_BLOCO_COMERCIAL("DC", "Depois do bloco Comercial"),
	NAO_INCLUIR("NI", "Não incluir vinhetas");

	private String codigo;
	
	private String descricao;
	
	PosicaoVinheta(String codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public static PosicaoVinheta getByCodigo( String codigo )
	{
		PosicaoVinheta result = null;
		
		for ( PosicaoVinheta p : PosicaoVinheta.values() )
		{
			if ( p.getCodigo().equals( codigo ) )
			{
				result = p;
				break;
			}
		}
		
		return result;
	}
}
