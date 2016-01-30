package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Tabela de Ligação
 * 
 * Usuario x Permissao
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="usuario_permissao")
public class UsuarioPermissao implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuperm", nullable = false )
	private Long idUsuperm;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_permissao")
	private Permissao permissao;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da permisão de usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;

	public Long getIdUsuperm()
	{
		return idUsuperm;
	}

	public void setIdUsuperm( Long idUsuperm )
	{
		this.idUsuperm = idUsuperm;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Permissao getPermissao()
	{
		return permissao;
	}

	public void setPermissao( Permissao permissao )
	{
		this.permissao = permissao;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public UsuarioPermissao()
	{
		super();
		this.dataCriacao = new Date();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idUsuperm == null ) ? 0 : idUsuperm.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		UsuarioPermissao other = (UsuarioPermissao) obj;
		if ( idUsuperm == null )
		{
			if ( other.idUsuperm != null )
				return false;
		}
		else if ( !idUsuperm.equals( other.idUsuperm ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "UsuarioPermissao [idUsuperm=%s, usuario=%s, permissao=%s]", idUsuperm, usuario, permissao );
	}
		
}
