package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_perfil", nullable = false )
	private Long idPerfil;

	@NotNull( message = "O nome do Perfil é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, columnDefinition = "TEXT" )
	private String nome;

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
		if ( getClass() != obj.getClass() )
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


	
}
