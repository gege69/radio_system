package br.com.radio.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Perfil
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="perfil")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;

	public static final Perfil DESENVOLVEDOR = new Perfil( 1l, "DESENVOLVEDOR" );
	public static final Perfil ADMINISTRADOR = new Perfil( 2l, "ADMINISTRADOR" );
	public static final Perfil GERENTE = new Perfil( 3l, "GERENTE" );
	public static final Perfil SUPERVISOR = new Perfil( 4l, "SUPERVISOR" );
	public static final Perfil USUARIO = new Perfil( 5l, "USUARIO" );

	public static final List<Perfil> DONOS = Arrays.asList( Perfil.ADMINISTRADOR, Perfil.DESENVOLVEDOR );
	
	@Id
	@Column( name = "id_perfil", nullable = false )
	private Long idPerfil;

	@NotNull( message = "O nome do Perfil é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, columnDefinition = "TEXT" )
	private String nome;

	@Column( name="comum", columnDefinition = "boolean default true" )
	private boolean comum;

	public Long getIdPerfil()
	{
		return idPerfil;
	}

	public void setIdPerfil( Long idPerfil )
	{
		this.idPerfil = idPerfil;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	@Override
	public String toString()
	{
		return String.format( "Perfil [idPerfil=%s, nome=%s]", idPerfil, nome );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idPerfil == null ) ? 0 : idPerfil.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Perfil other = (Perfil) obj;
		if ( idPerfil == null )
		{
			if ( other.idPerfil != null )
				return false;
		}
		else if ( !idPerfil.equals( other.idPerfil ) )
			return false;
		return true;
	}

	public boolean isComum()
	{
		return comum;
	}

	public void setComum( boolean comum )
	{
		this.comum = comum;
	}

	public Perfil()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Perfil( Long idPerfil, String nome )
	{
		super();
		this.idPerfil = idPerfil;
		this.nome = nome;
	}


	
}
