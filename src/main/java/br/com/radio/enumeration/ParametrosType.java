package br.com.radio.enumeration;

public enum ParametrosType {
	
	// Armazenamento
	BASE_MIDIA_PATH("Pasta em disco onde serão armazenadas as músicas EX: C:\\Temp\\Musicas"),

	// Temas
	TEMA("Tema de CSS utilizado dentro do Gerenciador"),
	APARENCIA("Configuração que determina se vai usar TEMA ou BACKGROUNDCOLOR"),
	BACKGROUNDCOLOR("Cor de fundo utilizada dentro do Gerenciador. Não pode ser utilizada junto com tema."),
	
	// Conversão
	BITRATE_TYPE("Tipo de BitRate utilizado na Conversão de MP3 (Médio, Constante ou Variável)"),
	VALOR_BITRATE("Valor padrão do BitRate utilizado na Conversão de MP3 (Ex: 96, 128 para constante e médio ou 4, 5 no caso de variável)")
	;
	
	private String descricao;

	private ParametrosType(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}

	
}