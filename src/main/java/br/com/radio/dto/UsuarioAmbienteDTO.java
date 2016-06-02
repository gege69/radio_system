package br.com.radio.dto;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Usuario;

public class UsuarioAmbienteDTO {
	
	private Usuario usuario;
	
	private Ambiente ambiente;
	
	private boolean gerenciador;	
	
	private boolean player;
	
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

	public UsuarioAmbienteDTO( Usuario usuario, Ambiente ambiente, boolean gerenciador, boolean player )
	{
		super();
		this.usuario = usuario;
		this.ambiente = ambiente;
		this.gerenciador = gerenciador;
		this.player = player;
	}

	public boolean isGerenciador()
	{
		return gerenciador;
	}

	public void setGerenciador( boolean gerenciador )
	{
		this.gerenciador = gerenciador;
	}

	public boolean isPlayer()
	{
		return player;
	}

	public void setPlayer( boolean player )
	{
		this.player = player;
	}
	

}
