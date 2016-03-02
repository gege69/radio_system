package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.radio.enumeration.SignoZodiaco;



/**
 * Tabela que relaciona um Signo do Zodíaco com uma mídia para a Categoria Horóscopo
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="signo_midia")
public class SignoMidia implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_signomidia", nullable = false )
	private Long idSignomidia;

	@Enumerated(EnumType.STRING)
	@Column( name = "signo" )
	private SignoZodiaco signo;	
	
	@OneToOne
	@JoinColumn( name="id_midia", nullable=false )
	private Midia midia;

	public Long getIdSignomidia()
	{
		return idSignomidia;
	}

	public void setIdSignomidia( Long idSignomidia )
	{
		this.idSignomidia = idSignomidia;
	}

	public SignoZodiaco getSigno()
	{
		return signo;
	}

	public void setSigno( SignoZodiaco signo )
	{
		this.signo = signo;
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
		result = prime * result + ( ( idSignomidia == null ) ? 0 : idSignomidia.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof SignoMidia ) )
			return false;
		SignoMidia other = (SignoMidia) obj;
		if ( idSignomidia == null )
		{
			if ( other.idSignomidia != null )
				return false;
		}
		else if ( !idSignomidia.equals( other.idSignomidia ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "SignoMidia [idSignomidia=%s, signo=%s, midia=%s]", idSignomidia, signo, midia );
	}
	
	
	

}
