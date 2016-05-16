package br.com.radio.dto;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class PerfilPermissaoDTO {

	@NotNull
	private Long idPerfil;
	
	private ArrayList<Long> idPermissoes;

	public Long getIdPerfil()
	{
		return idPerfil;
	}

	public void setIdPerfil( Long idPerfil )
	{
		this.idPerfil = idPerfil;
	}

	public ArrayList<Long> getIdPermissoes()
	{
		return idPermissoes;
	}

	public void setIdPermissoes( ArrayList<Long> idPermissoes )
	{
		this.idPermissoes = idPermissoes;
	}
	
	

}
