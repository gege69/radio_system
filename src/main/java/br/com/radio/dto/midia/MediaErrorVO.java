package br.com.radio.dto.midia;

import java.util.Date;

import br.com.radio.model.Ambiente;

public class MediaErrorVO {

	private Ambiente ambiente;
	
	private Date data;
	
	private String mediaError;
	
	private Long idTransmissao;
	
	private Long idMidia;

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public Date getData()
	{
		return data;
	}

	public void setData( Date data )
	{
		this.data = data;
	}

	public String getMediaError()
	{
		return mediaError;
	}

	public void setMediaError( String mediaError )
	{
		this.mediaError = mediaError;
	}

	public Long getIdTransmissao()
	{
		return idTransmissao;
	}

	public void setIdTransmissao( Long idTransmissao )
	{
		this.idTransmissao = idTransmissao;
	}

	public Long getIdMidia()
	{
		return idMidia;
	}

	public void setIdMidia( Long idMidia )
	{
		this.idMidia = idMidia;
	}
	
	
}
