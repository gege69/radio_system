package br.com.radio.conversao;

import java.util.ArrayList;
import java.util.List;

import br.com.radio.json.VariableBitRateOptionSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using=VariableBitRateOptionSerializer.class)
public enum VariableBitRateOption {

	ZERO("0","Melhor qualidade, maior arquivo", false),
	UM("1","Muito boa qualidade, arquivo grande", false),
	DOIS("2","boa qualidade, arquivo grande", false),
	TRES("3","boa qualidade, arquivo médio", false),
	QUATRO("4","boa qualidade, arquivo médio", false),
	CINCO("5","qualidade boa, arquivo pouco menor", true),
	SEIS("6","qualidade razoável, arquivo menor", true),
	SETE("7","qualidade ruim, arquivo menor", true),
	OITO("8","qualidade ruim, arquivo menor", true),
	NOVE("9","Pior qualidade, arquivo pequeno", true);
	
	private String valor;
	
	private String descricao;
	
	private boolean exibe;

	private VariableBitRateOption( String valor, String descricao, Boolean exibe )
	{
		this.valor = valor;
		this.descricao = descricao;
		this.exibe = exibe;
	}

	public String getValor()
	{
		return valor;
	}

	public void setValor( String valor )
	{
		this.valor = valor;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public boolean isExibe()
	{
		return exibe;
	}

	public void setExibe( boolean exibe )
	{
		this.exibe = exibe;
	}

	public static List<VariableBitRateOption> listaExibicao(){
		
		List<VariableBitRateOption> variables = new ArrayList<VariableBitRateOption>();
		
		for (VariableBitRateOption option : VariableBitRateOption.values()){
			if ( option.exibe )
				variables.add( option );
		}
		
		return variables;
	}
	
	public static VariableBitRateOption getByValue(String value){

		VariableBitRateOption result = null;

		for (VariableBitRateOption option : VariableBitRateOption.values()){
			if ( option.valor.equals( value )){
				result = option;
				break;
			}
		}
		
		return result;
	}
}
