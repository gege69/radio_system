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
 * Tabela de ligação 
 * 
 * Midia x Gênero
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia_genero")
public class MidiaGenero implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mediagen", nullable = false )
	private Long idMediagen;

	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name="id_midia")
	private Midia midia;

	public Long getIdMediagen()
	{
		return idMediagen;
	}

	public void setIdMediagen( Long idMediagen )
	{
		this.idMediagen = idMediagen;
	}

	public Genero getGenero()
	{
		return genero;
	}

	public void setGenero( Genero genero )
	{
		this.genero = genero;
	}

	public Midia getMidia()
	{
		return midia;
	}

	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idMediagen == null ) ? 0 : idMediagen.hashCode() );
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
		MidiaGenero other = (MidiaGenero) obj;
		if ( idMediagen == null )
		{
			if ( other.idMediagen != null )
				return false;
		}
		else if ( !idMediagen.equals( other.idMediagen ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "MidiaGenero [idMediagen=%s, genero=%s, midia=%s]", idMediagen, genero, midia );
	}

	


}
