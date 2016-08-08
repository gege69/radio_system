package br.com.radio.programacaomusical;

public enum PosicaoVinheta {

	ANTES_CADA_MUSICA( "Antes de cada música"),
	ANTES_BLOCO_COMERCIAL( "Antes do bloco Comercial"),
	DEPOIS_BLOCO_COMERCIAL( "Depois do bloco Comercial"),
	NAO_INCLUIR( "Não incluir vinhetas");

	private String descricao;
	
	PosicaoVinheta(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
		
}
