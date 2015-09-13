package br.com.radio.enumeration;

public enum VozLocucao {

	MASCULINA("M", "MASCULINA"),
	FEMININA("F", "FEMININA");

	private String codigo;
	
	private String descricao;
	
	VozLocucao(String codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}
