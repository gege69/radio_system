package br.com.radio.dto;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Usuario;

public class UsuarioAmbienteDTO {
	
	private Usuario usuario;
	
	private Ambiente ambiente;
	
	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public UsuarioAmbienteDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioAmbienteDTO( Usuario usuario, Ambiente ambiente )
	{
		super();
		this.usuario = usuario;
		this.ambiente = ambiente;
	}
	
	

}
