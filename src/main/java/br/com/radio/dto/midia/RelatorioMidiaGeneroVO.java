package br.com.radio.dto.midia;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RelatorioMidiaGeneroVO {

	private Long idGenero;
	
	private String genero;
	
	private Long quantidadeMidias;

	public Long getIdGenero()
	{
		return idGenero;
	}

	public void setIdGenero( Long idGenero )
	{
		this.idGenero = idGenero;
	}

	public String getGenero()
	{
		return genero;
	}

	public void setGenero( String genero )
	{
		this.genero = genero;
	}

	public Long getQuantidadeMidias()
	{
		return quantidadeMidias;
	}

	public void setQuantidadeMidias( Long quantidadeMidias )
	{
		this.quantidadeMidias = quantidadeMidias;
	}

	public RelatorioMidiaGeneroVO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public RelatorioMidiaGeneroVO( Long quantidadeMidias, Long idGenero, String genero )
	{
		super();
		this.idGenero = idGenero;
		this.genero = genero;
		this.quantidadeMidias = quantidadeMidias;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString( this );
	}
	
	
	
}
