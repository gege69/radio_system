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

@Entity
@Table(name="ambiente_genero")
public class AmbienteGenero implements Serializable {

	private static final long serialVersionUID = 7846499560224915976L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_ambgen", nullable = false )
	private Long idAmbgen;
	
	@ManyToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;

	public Long getIdAmbgen()
	{
		return idAmbgen;
	}

	public void setIdAmbgen( Long idAmbgen )
	{
		this.idAmbgen = idAmbgen;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public Genero getGenero()
	{
		return genero;
	}

	public void setGenero( Genero genero )
	{
		this.genero = genero;
	}

	public AmbienteGenero()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AmbienteGenero( Ambiente ambiente, Genero genero )
	{
		super();
		this.ambiente = ambiente;
		this.genero = genero;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idAmbgen == null ) ? 0 : idAmbgen.hashCode() );
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
		AmbienteGenero other = (AmbienteGenero) obj;
		if ( idAmbgen == null )
		{
			if ( other.idAmbgen != null )
				return false;
		}
		else if ( !idAmbgen.equals( other.idAmbgen ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "AmbienteGenero [idAmbgen=%s, ambiente=%s, genero=%s]", idAmbgen, ambiente, genero );
	}
	
	
	
	
}
