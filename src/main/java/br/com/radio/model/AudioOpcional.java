package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="audio_opcional")
public class AudioOpcional implements Serializable {

	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_opcional", nullable = false )
	private Long idOpcional;
	
	@NotNull( message = "O nome curto é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, length = 20 )
	private String nome;
	
	@Column( name = "descricao", nullable = true, columnDefinition = "TEXT" )
	private String descricao;
	
	@JsonIgnore
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao" )
	private Date dataCriacao;
	
	@Column( name="ativo")
	private Boolean ativo;

	public Long getIdOpcional()
	{
		return idOpcional;
	}

	public void setIdOpcional( Long idOpcional )
	{
		this.idOpcional = idOpcional;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public AudioOpcional()
	{
		super();
		this.dataCriacao = new Date();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idOpcional == null ) ? 0 : idOpcional.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof AudioOpcional ) )
			return false;
		AudioOpcional other = (AudioOpcional) obj;
		if ( idOpcional == null )
		{
			if ( other.idOpcional != null )
				return false;
		}
		else if ( !idOpcional.equals( other.idOpcional ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "AudioOpcional [idOpcional=%s, nome=%s, descricao=%s, dataCriacao=%s]", idOpcional, nome, descricao, dataCriacao );
	}


	
}
