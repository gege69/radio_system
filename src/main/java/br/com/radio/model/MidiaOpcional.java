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
 * Midia x Opcional
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia_opcional")
public class MidiaOpcional implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mediaopcional", nullable = false )
	private Long idMediaopcional;

	@ManyToOne
	@JoinColumn(name="id_opcional")
	private AudioOpcional opcional;
	
	@ManyToOne
	@JoinColumn(name="id_midia")
	private Midia midia;

	public Long getIdMediaopcional()
	{
		return idMediaopcional;
	}

	public void setIdMediaopcional( Long idMediaopcional )
	{
		this.idMediaopcional = idMediaopcional;
	}

	public AudioOpcional getOpcional()
	{
		return opcional;
	}

	public void setOpcional( AudioOpcional opcional )
	{
		this.opcional = opcional;
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
		result = prime * result + ( ( idMediaopcional == null ) ? 0 : idMediaopcional.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof MidiaOpcional ) )
			return false;
		MidiaOpcional other = (MidiaOpcional) obj;
		if ( idMediaopcional == null )
		{
			if ( other.idMediaopcional != null )
				return false;
		}
		else if ( !idMediaopcional.equals( other.idMediaopcional ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "MidiaOpcional [idMediaopcional=%s, opcional=%s, midia=%s]", idMediaopcional, opcional, midia );
	}

	

}
