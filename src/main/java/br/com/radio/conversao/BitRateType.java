package br.com.radio.conversao;

public enum BitRateType {

	AVERAGE("--abr", "BitRate Médio"),
	CONSTANT("-b", "BitRate Constante"),
	VARIABLE("-V", "BitRate Variável");
	
	private String command;
	
	private String descricao;

	private BitRateType( String command, String descricao )
	{
		this.command = command;
		this.descricao = descricao;
	}

	public String getCommand()
	{
		return command;
	}

	public String getDescricao()
	{
		return descricao;
	};

}
