package br.com.radio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.json.JSONDateTimeSerializer;
import br.com.radio.util.UtilsDates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * HistoricoStatusAmbiente 
 * 
 * Essa classe vai representar um registro de transição de status do Ambiente.
 * Ao ser criado o ambiente já precisa de um registro nessa tabela indicando que está ativo...
 * 
 * Ex: 
 *			Amb			Inicio	  Fim		 Status 
 *
 * 		Ambiente 1      10/06     null       ATIVO
 * 		Ambiente 2      10/06     15/06      ATIVO
 * 		Ambiente 2      16/06     null       INATIVO
 * 
 * @author pazin
 *
 */
@Entity
@Table( name="historico_status_ambiente")
public class HistoricoStatusAmbiente implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_hist_status_amb", nullable = false )
	private Long idHistStatusAmb;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;

	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataInicio", nullable = false )
	private Date dataInicio;

	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataFim", nullable = false )
	private Date dataFim;
	
	// Arredondado para dias... a diferença real é precisa pela diferença de datas com hora
	@Column(name="diferencadias")
	private Integer diferenciaDias;

	@Enumerated(EnumType.STRING)
	@Column( name = "status", columnDefinition = "TEXT default 'ATIVO' " )
	private StatusAmbiente status;

	public Long getIdHistStatusAmb()
	{
		return idHistStatusAmb;
	}

	public void setIdHistStatusAmb( Long idHistStatusAmb )
	{
		this.idHistStatusAmb = idHistStatusAmb;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public StatusAmbiente getStatus()
	{
		return status;
	}

	public void setStatus( StatusAmbiente status )
	{
		this.status = status;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idHistStatusAmb == null ) ? 0 : idHistStatusAmb.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof HistoricoStatusAmbiente ) )
			return false;
		HistoricoStatusAmbiente other = (HistoricoStatusAmbiente) obj;
		if ( idHistStatusAmb == null )
		{
			if ( other.idHistStatusAmb != null )
				return false;
		}
		else if ( !idHistStatusAmb.equals( other.idHistStatusAmb ) )
			return false;
		return true;
	}


	@Override
	public String toString()
	{
		return String.format( "HistoricoStatusAmbiente [idHistStatusAmb=%s, ambiente=%s, dataInicio=%s, dataFim=%s, diferenciaDias=%s, status=%s]", idHistStatusAmb, ambiente, dataInicio, dataFim,
				diferenciaDias, status );
	}

	public HistoricoStatusAmbiente()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoricoStatusAmbiente( Ambiente ambiente, LocalDate dataInicio, StatusAmbiente status)
	{
		super();
		this.ambiente = ambiente;
		this.dataInicio = UtilsDates.asUtilDate( dataInicio );
		this.status = status;
	}


	public HistoricoStatusAmbiente( Ambiente ambiente, LocalDate dataInicio, LocalDate dataFim, Integer diferenciaDias, StatusAmbiente status )
	{
		super();
		this.ambiente = ambiente;
		this.dataInicio = UtilsDates.asUtilDate( dataInicio );
		this.dataFim = UtilsDates.asUtilDate( dataFim );
		this.diferenciaDias = diferenciaDias;
		this.status = status;
	}

	public HistoricoStatusAmbiente( Ambiente ambiente, Date data, StatusAmbiente status)
	{
		super();
		this.ambiente = ambiente;
		this.dataInicio = data;
		this.status = status;
	}

	public Date getDataInicio()
	{
		return dataInicio;
	}

	public LocalDate getDataInicioAsLocalDate()
	{
		return UtilsDates.asLocalDate( dataInicio );
	}

	public void setDataInicio( Date dataInicio )
	{
		this.dataInicio = dataInicio;
	}

	public Date getDataFim()
	{
		return dataFim;
	}

	public LocalDate getDataFimAsLocalDate()
	{
		return UtilsDates.asLocalDate( dataFim );
	}

	public void setDataFim( Date dataFim )
	{
		this.dataFim = dataFim;
	}

	public Integer getDiferenciaDias()
	{
		return diferenciaDias;
	}

	public void setDiferenciaDias( Integer diferenciaDias )
	{
		this.diferenciaDias = diferenciaDias;
	}
	
	

	
}
