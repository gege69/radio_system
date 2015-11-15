package br.com.radio.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Registro de programação musical.
 * 
 * Vai guardar o horário do bloco musical e quais gêneros musicais vão tocar nesse periodo.
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="programacao")
public class Programacao implements Serializable {

	private static final long serialVersionUID = -8326442337053628035L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_programacao", nullable = false )
	private Long idProgramacao;
	
	@JsonIgnore
	@NotNull( message = "O Ambiente é de preenchimento obrigatório" )
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name= "id_ambiente" )
	private Ambiente ambiente;
	
	@NotNull( message = "O dia da semana é de preenchimento obrigatório" )
	@Enumerated(EnumType.STRING)
	@Column( name = "dia" )
	private DiaSemana diaSemana;
	
	@NotNull( message = "A Hora de Início é de preenchimento obrigatório" )
	@Min(value=0)
	@Max(value=23)
	@Column(name="hora_inicio")
	private Integer horaInicio;

	@NotNull( message = "O Minuto de Início é de preenchimento obrigatório" )
	@Min(value=0)
	@Max(value=59)
	@Column(name="minuto_inicio")
	private Integer minutoInicio;
	
	@NotNull( message = "A Data de Início é de preenchimento obrigatório" )
	@Column(name="datetime_inicio", columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeInicio;
	
	@NotNull( message = "A Hora do Fim é de preenchimento obrigatório" )
	@Min(value=0)
	@Max(value=23)
	@Column(name="hora_fim")
	private Integer horaFim;

	@NotNull( message = "O Minuto do Fim é de preenchimento obrigatório" )
	@Min(value=0)
	@Max(value=59)
	@Column(name="minuto_fim")
	private Integer minutoFim;
	
	@NotNull( message = "O Data de Fim é de preenchimento obrigatório" )
	@Column(name="datetime_fim", columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeFim;

	@Column(name="ativo")
	private Boolean ativo;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao" )
	private Date dataCriacao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datainativo" )
	private Date dataInativo;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	   @JoinTable(name="programacao_genero", joinColumns = { 
	        @JoinColumn(name="id_programacao", nullable=false, updatable=false) }, inverseJoinColumns = { 
	        @JoinColumn(name="id_genero", nullable=false, updatable=false) })
	private List<Genero> generos;

	@Column(name="custom")
	private Boolean custom = false;
	
	@JsonIgnore
	@Transient
	private Set<Genero> generosSet;
	
	@Transient
	private Map<String,String> progAPI = new HashMap<String,String>();
	
	
	
	// registros de transmissão 
	
	
	public Long getIdProgramacao()
	{
		return idProgramacao;
	}

	public void setIdProgramacao( Long idProgramacao )
	{
		this.idProgramacao = idProgramacao;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public DiaSemana getDiaSemana()
	{
		return diaSemana;
	}

	public void setDiaSemana( DiaSemana diaSemana )
	{
		this.diaSemana = diaSemana;
	}

	public Integer getHoraInicio()
	{
		return horaInicio;
	}

	public void setHoraInicio( Integer horaInicio )
	{
		this.horaInicio = horaInicio;
	}

	public Integer getMinutoInicio()
	{
		return minutoInicio;
	}

	public void setMinutoInicio( Integer minutoInicio )
	{
		this.minutoInicio = minutoInicio;
	}

	public Date getDateTimeInicio()
	{
		return dateTimeInicio;
	}

	public void setDateTimeInicio( Date dateTimeInicio )
	{
		this.dateTimeInicio = dateTimeInicio;
	}

	public Integer getHoraFim()
	{
		return horaFim;
	}

	public void setHoraFim( Integer horaFim )
	{
		this.horaFim = horaFim;
	}

	public Integer getMinutoFim()
	{
		return minutoFim;
	}

	public void setMinutoFim( Integer minutoFim )
	{
		this.minutoFim = minutoFim;
	}

	public Date getDateTimeFim()
	{
		return dateTimeFim;
	}

	public void setDateTimeFim( Date dateTimeFim )
	{
		this.dateTimeFim = dateTimeFim;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	public Programacao()
	{
		super();
		this.ativo = true;
		this.dataCriacao = new Date();
	}

	public Programacao( Ambiente ambiente, DiaSemana diaSemana, Integer horaInicio )
	{
		super();
		this.ambiente = ambiente;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.ativo = true;
		this.dataCriacao = new Date();

	}


	
	/**
	 * Esse método vai usar o dia 01/01/2000 pra armazenar as horas .... é só ignorar a parte Date e focar apenas na parte Time do campo
	 * 
	 * @param hora
	 * @param minuto
	 * @return
	 */
	public Date getDate( Integer hora, Integer minuto )
	{
		LocalDateTime dt = LocalDateTime.of( 2000, 1, 1, hora, minuto, 0 );
		
		ZonedDateTime zdt = dt.atZone( ZoneId.systemDefault() );
		
		Date result = Date.from( zdt.toInstant() );

		return result;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idProgramacao == null ) ? 0 : idProgramacao.hashCode() );
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
		Programacao other = (Programacao) obj;
		if ( idProgramacao == null )
		{
			if ( other.idProgramacao != null )
				return false;
		}
		else if ( !idProgramacao.equals( other.idProgramacao ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Programacao [idProgramacao=%s, diaSemana=%s, horaInicio=%s, minutoInicio=%s, horaFim=%s, minutoFim=%s]", idProgramacao, diaSemana, horaInicio, minutoInicio, horaFim,
				minutoFim );
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataInativo()
	{
		return dataInativo;
	}

	public void setDataInativo( Date dataInativo )
	{
		this.dataInativo = dataInativo;
	}

	public List<Genero> getGeneros()
	{
		return generos;
	}

	public void setGeneros( List<Genero> generos )
	{
		this.generos = generos;
	}

	@JsonAnyGetter
	public Map<String, String> getProgAPI()
	{
		return progAPI;
	}

	@JsonAnySetter
	public void setProgAPI( Map<String, String> progAPI )
	{
		this.progAPI = progAPI;
	}

	public Set<Genero> getGenerosSet()
	{
		return generosSet;
	}

	public void setGenerosSet( Set<Genero> generosSet )
	{
		this.generosSet = generosSet;
	}

	public Boolean getCustom()
	{
		return custom;
	}

	public void setCustom( Boolean custom )
	{
		this.custom = custom;
	}
	
	
	
	

}


