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
	private Long id_usuperf;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	public Long getId_usuperf()
	{
		return id_usuperf;
	}

	public void setId_usuperf( Long id_usuperf )
	{
		this.id_usuperf = id_usuperf;
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

		
	
}
