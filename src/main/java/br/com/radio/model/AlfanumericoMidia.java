package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * Tabela que relaciona um dígit alfanumérico ( letra ou número ) com uma Mídia
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="alfanumerico_midia")
public class AlfanumericoMidia implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_alfamidia", nullable = false )
	private Long idAlfamidia;

	@Column( name = "alfanumerico" )
	private String alfanumerico;	
	
	@OneToOne
	@Column( name="id_midia")
	private Midia midia;

	
	
	public Long getIdAlfamidia()
	{
		return idAlfamidia;
	}

	public void setIdAlfamidia( Long idAlfamidia )
	{
		this.idAlfamidia = idAlfamidia;
	}

	public String getAlfanumerico()
	{
		return alfanumerico;
	}

	public void setAlfanumerico( String alfanumerico )
	{
		this.alfanumerico = alfanumerico;
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
		result = prime * result + ( ( alfanumerico == null ) ? 0 : alfanumerico.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof AlfanumericoMidia ) )
			return false;
		AlfanumericoMidia other = (AlfanumericoMidia) obj;
		if ( alfanumerico == null )
		{
			if ( other.alfanumerico != null )
				return false;
		}
		else if ( !alfanumerico.equals( other.alfanumerico ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "AlfanumericoMidia [idAlfamidia=%s, alfanumerico=%s, midia=%s]", idAlfamidia, alfanumerico, midia );
	}

	public AlfanumericoMidia()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AlfanumericoMidia( String alfanumerico, Midia midia )
	{
		super();
		this.alfanumerico = alfanumerico;
		this.midia = midia;
	}

	
	

}
