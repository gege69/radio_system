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
 * Perfil x Permissao
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="perfil_permissao")
public class PerfilPermissao implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_perfperm", nullable = false )
	private Long idPerfperm;

	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="id_permissao")
	private Permissao permissao;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da permisão de Perfil é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;

	public Long getIdPerfperm()
	{
		return idPerfperm;
	}

	public void setIdPerfperm( Long idPerfperm )
	{
		this.idPerfperm = idPerfperm;
	}

	public Perfil getPerfil()
	{
		return perfil;
	}

	public void setPerfil( Perfil perfil )
	{
		this.perfil = perfil;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idPerfperm == null ) ? 0 : idPerfperm.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		PerfilPermissao other = (PerfilPermissao) obj;
		if ( idPerfperm == null )
		{
			if ( other.idPerfperm != null )
				return false;
		}
		else if ( !idPerfperm.equals( other.idPerfperm ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "PerfilPermissao [idPerfperm=%s, perfil=%s, permissao=%s ]", idPerfperm, perfil, permissao );
	}

	public PerfilPermissao( Perfil perfil, Permissao permissao, Date dataCriacao )
	{
		super();
		this.perfil = perfil;
		this.permissao = permissao;
		this.dataCriacao = dataCriacao;
	}
	

}
