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
	private Long idFuncionalidade;
	
	@Column( name = "nome", columnDefinition = "TEXT" )
	private String nome;

	@Column( name = "codigo", columnDefinition = "TEXT" )
	private String codigo;

	@Column( name = "ordem")
	private Long ordem;
	
	@Column( name = "url", columnDefinition = "TEXT" )
	private String url;

	@Column( name = "icone", columnDefinition = "TEXT" )
	private String icone;
	
	@Column( name="extrahtml", columnDefinition = "TEXT" )
	private String extrahtml;

	@Column( name = "ativo", columnDefinition = "BOOL default true")
	private Boolean ativo;

	public Long getIdFuncionalidade()
	{
		return idFuncionalidade;
	}

	public void setIdFuncionalidade( Long idFuncionalidade )
	{
		this.idFuncionalidade = idFuncionalidade;
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
	
	

	public String getExtrahtml()
	{
		return extrahtml;
	}

	public void setExtrahtml( String extrahtml )
	{
		this.extrahtml = extrahtml;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idFuncionalidade == null ) ? 0 : idFuncionalidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Funcionalidade other = (Funcionalidade) obj;
		if ( idFuncionalidade == null )
		{
			if ( other.idFuncionalidade != null )
				return false;
		}
		else if ( !idFuncionalidade.equals( other.idFuncionalidade ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Funcionalidade [idFuncionalidade=%s, nome=%s]", idFuncionalidade, nome );
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}
	
}
