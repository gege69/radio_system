package br.com.radio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import br.com.radio.json.JSONAmbienteEmailDeserializer;


/**
 * Ambiente eMaiL = AML
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="ambiente_email")
@JsonDeserialize(using=JSONAmbienteEmailDeserializer.class)
public class AmbienteEmail implements Model<Long> {

	private static final long serialVersionUID = -2750334711556132683L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_email_aml", nullable = false )
	private Long id_email_aml;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_ambiente_amb")
	private Ambiente ambiente;
	
	@NotNull( message = "O endereço de email é de preenchimento obrigatório" )
	@Column( name = "ds_email_aml", nullable = false, length = 200 )
	private String ds_email_aml;


	@Override
	public Long getId()
	{
		return this.id_email_aml;
	}

	@Override
	public void setId( Long id )
	{
		this.id_email_aml = id;
	}

	public Long getId_email_aml()
	{
		return id_email_aml;
	}

	public void setId_email_aml( Long id_email_aml )
	{
		this.id_email_aml = id_email_aml;
	}

	public String getDs_email_aml()
	{
		return ds_email_aml;
	}

	public void setDs_email_aml( String ds_email_aml )
	{
		this.ds_email_aml = ds_email_aml;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}
	
		
	
}
