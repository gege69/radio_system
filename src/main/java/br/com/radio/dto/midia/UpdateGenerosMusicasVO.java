package br.com.radio.dto.midia;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdateGenerosMusicasVO {
	
	private String search;
	
	private Long idGeneroPesquisa;
	
	@NotNull
	@NotEmpty
	private Long[] idGenerosNovos;

	public String getSearch()
	{
		return search;
	}

	public void setSearch( String search )
	{
		this.search = search;
	}

	public Long getIdGeneroPesquisa()
	{
		return idGeneroPesquisa;
	}

	public void setIdGeneroPesquisa( Long idGeneroPesquisa )
	{
		this.idGeneroPesquisa = idGeneroPesquisa;
	}

	public Long[] getIdGenerosNovos()
	{
		return idGenerosNovos;
	}

	public void setIdGenerosNovos( Long[] idGenerosNovos )
	{
		this.idGenerosNovos = idGenerosNovos;
	}
	
	
}
