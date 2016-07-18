package br.com.radio.conversao;

import br.com.radio.json.VariableBitRateOptionSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using=VariableBitRateOptionSerializer.class)
public enum VariableBitRateOption {

	ZERO("0","Melhor qualidade, maior arquivo"),
	UM("1","Muito boa qualidade, arquivo grande"),
	DOIS("2","boa qualidade, arquivo grande"),
	TRES("3","boa qualidade, arquivo médio"),
	QUATRO("4","Padrão"),
	CINCO("5","qualidade razoável, arquivo médio"),
	SEIS("6","qualidade razoável, arquivo menor"),
	SETE("7","qualidade ruim, arquivo menor"),
	OITO("8","qualidade ruim, arquivo menor"),
	NOVE("9","Pior qualidade, arquivo pequeno");
	
	private String commandValue;
	
	private String descricao;

	private VariableBitRateOption( String commandValue, String descricao )
	{
		this.commandValue = commandValue;
		this.descricao = descricao;
	}

	public String getCommandValue()
	{
		return commandValue;
	}

	public void setCommandValue( String commandValue )
	{
		this.commandValue = commandValue;
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
