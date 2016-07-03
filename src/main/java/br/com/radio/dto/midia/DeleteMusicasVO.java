package br.com.radio.dto.midia;


public class DeleteMusicasVO {
	
	private String search;
	
	private Long idGeneroPesquisa;
	
	private Long[] idMidias;

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

	public Long[] getIdMidias()
	{
		return idMidias;
	}

	public void setIdMidias( Long[] idMidias )
	{
		this.idMidias = idMidias;
	}

}
