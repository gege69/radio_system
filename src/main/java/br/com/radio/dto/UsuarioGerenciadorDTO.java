package br.com.radio.dto;

import java.util.List;

import br.com.radio.model.Perfil;
import br.com.radio.model.Usuario;

public class UsuarioGerenciadorDTO {
	
	private Usuario usuario;
	
	private List<Perfil> perfis;

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public List<Perfil> getPerfis()
	{
		return perfis;
	}

	public void setPerfis( List<Perfil> perfis )
	{
		this.perfis = perfis;
	}
	
	
	
}
