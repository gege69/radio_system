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



/**
 * Gênero
 * 
 * TODO: Talvez colocar um ID_CLIENTE porque alguns gêneros são muito característicos de uma certa empresa....
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="genero")
public class Genero implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_genero", nullable = false )
	private Long idGenero;

	@NotNull( message = "O nome do Gênero é de preenchimento obrigatório" )
	@Column( name = "genero", nullable = false, columnDefinition = "TEXT" )
	private String nome;

	@NotNull( message = "A descrição do Gênero é de preenchimento obrigatório" )
	@Column( name = "descricao", nullable = true, columnDefinition = "TEXT" )
	private String descricao;
	
	@Column( name= "sucesso", columnDefinition = "BOOL default false")
	private Boolean sucesso;

	// No futuro adicionar algum tipo de hierarquia / árvore de subgêneros

//	@JsonDeserialize(using=JSONDateDeserializer.class)
//	@JsonSerialize(using=JSONDateSerializer.class)
	@JsonIgnore
	@NotNull( message = "A data de criação do Gênero é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;

	public Long getIdGenero()
	{
		return idGenero;
	}

	public void setIdGenero( Long idGenero )
	{
		this.idGenero = idGenero;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idGenero == null ) ? 0 : idGenero.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Genero other = (Genero) obj;
		if ( idGenero == null )
		{
			if ( other.idGenero != null )
				return false;
		}
		else if ( !idGenero.equals( other.idGenero ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Genero [idGenero=%s, nome=%s]", idGenero, nome );
	}

	public Genero()
	{
		super();
		this.dataCriacao = new Date();
	}

	public Boolean getSucesso()
	{
		return sucesso;
	}

	public void setSucesso( Boolean sucesso )
	{
		this.sucesso = sucesso;
	}
	

}
