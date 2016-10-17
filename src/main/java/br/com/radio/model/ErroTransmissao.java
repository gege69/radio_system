package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.radio.json.views.TransmissaoView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Erros reportados pelo player serão armazenados aqui...
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="erro_transmissao")
public class ErroTransmissao implements Serializable {

	private static final long serialVersionUID = -66420223362665206L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_erro", nullable = false )
	@JsonView(TransmissaoView.Sumario.class)
	private Long idErro;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_transmissao", nullable= true )
	private Transmissao transmissao;
	
	@OneToOne
	@JoinColumn(name="id_midia", nullable=true )
	private Midia midia;
	
	@JsonIgnore
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao" )
	private Date dataCriacao;

	@JsonIgnore
	@Column( name = "mediaError", nullable = true, columnDefinition = "TEXT" )
	private String mediaError;


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


	public Date getDataCriacao()
	{
		return dataCriacao;
	}


	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}


	public ErroTransmissao()
	{
		super();
		this.dataCriacao = new Date();
	}


	public Long getIdErro()
	{
		return idErro;
	}


	public void setIdErro( Long idErro )
	{
		this.idErro = idErro;
	}


	public Transmissao getTransmissao()
	{
		return transmissao;
	}


	public void setTransmissao( Transmissao transmissao )
	{
		this.transmissao = transmissao;
	}


	public String getMediaError()
	{
		return mediaError;
	}


	public void setMediaError( String mediaError )
	{
		this.mediaError = mediaError;
	}


	@Override
	public String toString()
	{
		return String.format( "ErroTransmissao [idErro=%s, ambiente=%s, transmissao=%s, midia=%s, dataCriacao=%s]", idErro, ambiente, transmissao, midia, dataCriacao);
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( ambiente == null ) ? 0 : ambiente.hashCode() );
		result = prime * result + ( ( dataCriacao == null ) ? 0 : dataCriacao.hashCode() );
		result = prime * result + ( ( idErro == null ) ? 0 : idErro.hashCode() );
		result = prime * result + ( ( mediaError == null ) ? 0 : mediaError.hashCode() );
		result = prime * result + ( ( midia == null ) ? 0 : midia.hashCode() );
		result = prime * result + ( ( transmissao == null ) ? 0 : transmissao.hashCode() );
		return result;
	}


	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof ErroTransmissao ) )
			return false;
		ErroTransmissao other = (ErroTransmissao) obj;
		if ( ambiente == null )
		{
			if ( other.ambiente != null )
				return false;
		}
		else if ( !ambiente.equals( other.ambiente ) )
			return false;
		if ( dataCriacao == null )
		{
			if ( other.dataCriacao != null )
				return false;
		}
		else if ( !dataCriacao.equals( other.dataCriacao ) )
			return false;
		if ( idErro == null )
		{
			if ( other.idErro != null )
				return false;
		}
		else if ( !idErro.equals( other.idErro ) )
			return false;
		if ( mediaError == null )
		{
			if ( other.mediaError != null )
				return false;
		}
		else if ( !mediaError.equals( other.mediaError ) )
			return false;
		if ( midia == null )
		{
			if ( other.midia != null )
				return false;
		}
		else if ( !midia.equals( other.midia ) )
			return false;
		if ( transmissao == null )
		{
			if ( other.transmissao != null )
				return false;
		}
		else if ( !transmissao.equals( other.transmissao ) )
			return false;
		return true;
	}
	
	

	
}
