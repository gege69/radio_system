package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="funcionalidade")
public class Funcionalidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_funcionalidade", nullable = false )
	private Long id_funcionalidade;
	
	@Column( name = "nome", columnDefinition = "TEXT" )
	private String nome;

	@Column( name = "ordem")
	private Long ordem;
	
	@Column( name = "url", columnDefinition = "TEXT" )
	private String url;

	@Column( name = "icone", columnDefinition = "TEXT" )
	private String icone;

	
	public Long getId_funcionalidade()
	{
		return id_funcionalidade;
	}

	public void setId_funcionalidade( Long id_funcionalidade )
	{
		this.id_funcionalidade = id_funcionalidade;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public Long getOrdem()
	{
		return ordem;
	}

	public void setOrdem( Long ordem )
	{
		this.ordem = ordem;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl( String url )
	{
		this.url = url;
	}

	public String getIcone()
	{
		return icone;
	}

	public void setIcone( String icone )
	{
		this.icone = icone;
	}

	
	
	
	
}
