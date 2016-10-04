package br.com.radio.enumeration;

public enum StatusPlayback {
	
	GERADA("Gerada"),
	NAFILA("Na Fila"),
	TOCANDO("Tocando"),
	FIM("Fim"),
	IGNORADA("Ignorada");  // IGNORADA É QUANDO É GERADA UMA NOVA PLAYLIST (TRANSMISSÃO)  // ou quando o player entra em ação no meio da programação (as músicas do começo não foram tocadas)
	
	private String descricao;
	
	private StatusPlayback(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
	

}
