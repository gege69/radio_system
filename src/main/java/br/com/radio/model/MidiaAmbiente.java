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
	private Long id_midiaamb;
	
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


	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public Long getId_midiaamb()
	{
		return id_midiaamb;
	}

	public void setId_midiaamb( Long id_midiaamb )
	{
		this.id_midiaamb = id_midiaamb;
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
	
	

	

}
