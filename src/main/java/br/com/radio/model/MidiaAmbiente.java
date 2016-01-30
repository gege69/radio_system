package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Tabela de ligação 
 * 
 * Midia x Ambiente
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia_ambiente")
public class MidiaAmbiente implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_midiaamb", nullable = false )
	private Long idMidiaamb;
	
	@ManyToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@ManyToOne
	@JoinColumn(name="id_midia")
	private Midia midia;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataassociacao" )
	private Date dataAssociacao;
	

	// talvez guardar o usuário que associou?
	public Long getIdMidiaamb()
	{
		return idMidiaamb;
	}

	public void setIdMidiaamb( Long idMidiaamb )
	{
		this.idMidiaamb = idMidiaamb;
	}
	
	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public Midia getMidia()
	{
		return midia;
	}

	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}

	public Date getDataAssociacao()
	{
		return dataAssociacao;
	}

	public void setDataAssociacao( Date dataAssociacao )
	{
		this.dataAssociacao = dataAssociacao;
	}

	public MidiaAmbiente()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public MidiaAmbiente( Ambiente ambiente, Midia midia, Date dataAssociacao )
	{
		super();
		this.ambiente = ambiente;
		this.midia = midia;
		this.dataAssociacao = dataAssociacao;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idMidiaamb == null ) ? 0 : idMidiaamb.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		MidiaAmbiente other = (MidiaAmbiente) obj;
		if ( idMidiaamb == null )
		{
			if ( other.idMidiaamb != null )
				return false;
		}
		else if ( !idMidiaamb.equals( other.idMidiaamb ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "MidiaAmbiente [idMidiaamb=%s, ambiente=%s, midia=%s]", idMidiaamb, ambiente, midia );
	}
	
	

}
