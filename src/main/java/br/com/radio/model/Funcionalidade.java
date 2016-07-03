package br.com.radio.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

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

	@Column( name = "classes_icone", columnDefinition = "TEXT" )
	private String classesIcone;
	
	@Column( name="extrahtml", columnDefinition = "TEXT" )
	private String extrahtml;

	@Column( name = "size_small", columnDefinition = "TEXT" )
	private String sizeSmall;

	@Column( name = "size_big", columnDefinition = "TEXT" )
	private String sizeBig;

	@Column( name = "ativo", columnDefinition = "BOOL default true")
	private Boolean ativo;

	@Transient
	private Map<String, String> funcView = new HashMap<String,String>();
	

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

	public String getClassesIcone()
	{
		return classesIcone;
	}

	public void setClassesIcone( String classesIcone )
	{
		this.classesIcone = classesIcone;
	}

	public String getSizeSmall()
	{
		return sizeSmall;
	}

	public void setSizeSmall( String sizeSmall )
	{
		this.sizeSmall = sizeSmall;
	}

	public String getSizeBig()
	{
		return sizeBig;
	}

	public void setSizeBig( String sizeBig )
	{
		this.sizeBig = sizeBig;
	}

	@JsonAnyGetter
	public Map<String, String> getFuncView()
	{
		return funcView;
	}

	@JsonAnySetter
	public void setFuncView( Map<String, String> funcView )
	{
		this.funcView = funcView;
	}
	
}
