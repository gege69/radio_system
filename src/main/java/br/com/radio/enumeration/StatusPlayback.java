package br.com.radio.enumeration;

public enum StatusPlayback {
	
	GERADA,
	NAFILA,
	TOCANDO,
	FIM,
	IGNORADA;  // IGNORADA É QUANDO É GERADA UMA NOVA PLAYLIST (TRANSMISSÃO)  // ou quando o player entra em ação no meio da programação (as músicas do começo não foram tocadas)

}
