package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * Tabela de Ligação 
 * 
 * Usuario Perfil
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="usuario_perfil")
public class UsuarioPerfil implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuperf", nullable = false )
	private Long idUsuperf;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	public Long getIdUsuperf()
	{
		return idUsuperf;
	}

	public void setIdUsuperf( Long idUsuperf )
	{
		this.idUsuperf = idUsuperf;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Perfil getPerfil()
	{
		return perfil;
	}

	public void setPerfil( Perfil perfil )
	{
		this.perfil = perfil;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idUsuperf == null ) ? 0 : idUsuperf.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		UsuarioPerfil other = (UsuarioPerfil) obj;
		if ( idUsuperf == null )
		{
			if ( other.idUsuperf != null )
				return false;
		}
		else if ( !idUsuperf.equals( other.idUsuperf ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "UsuarioPerfil [idUsuperf=%s, usuario=%s, perfil=%s]", idUsuperf, usuario, perfil );
	}

}
