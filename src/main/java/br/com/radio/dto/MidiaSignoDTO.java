package br.com.radio.dto;

import javax.validation.constraints.NotNull;

import br.com.radio.model.Midia;

public class MidiaSignoDTO {
	
	@NotNull
	private Midia midia;
	
	@NotNull
	private String signo;

	public Midia getMidia()
	{
		return midia;
	}

	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}

	public String getSigno()
	{
		return signo;
	}

	public void setSigno( String signo )
	{
		this.signo = signo;
	}
	
	

}
