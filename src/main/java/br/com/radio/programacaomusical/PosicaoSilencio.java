package br.com.radio.programacaomusical;

public enum PosicaoSilencio {
	
	ANTES_BLOCO_COMERCIAL("Antes do bloco Comercial"),
	DEPOIS_BLOCO_COMERCIAL("Depois do bloco Comercial"),
	ANTES_INSTITUCIONAL("Antes do Institucional"),
	DEPOIS_INSTITUCIONAL("Depois do Institucional"),
	ANTES_PROGRAMETE("Antes do Programete"),
	DEPOIS_PROGRAMETE("Depois do Programete"),
	NAO_INCLUIR("Não incluir silêncio");
	
	private String descricao;

	private PosicaoSilencio(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

}
