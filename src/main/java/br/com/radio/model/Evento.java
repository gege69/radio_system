package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_evento", nullable = false )
	private Long idEvento;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn( name="id_ambiente", nullable=false )
	private Ambiente ambiente;

	@NotNull
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn( name="id_categoria", nullable=false )
	private Categoria categoria;

	@NotNull
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn( name="id_midia", nullable=false )
	private Midia midia;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataInicio", nullable = false )
	private Date dataInicio;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataFim", nullable = false )
	private Date dataFim;
	
	@Column( name = "repete_domingo", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteDomingo;
	
	@Column( name = "repete_segunda", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteSegunda;
	
	@Column( name = "repete_terca", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteTerca;
	
	@Column( name = "repete_quarta", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteQuarta;
	
	@Column( name = "repete_quinta", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteQuinta;
	
	@Column( name = "repete_sexta", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteSexta;

	@Column( name = "repete_sabado", nullable = false, columnDefinition = " boolean default false " )
	private boolean repeteSabado;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="evento", cascade=CascadeType.REMOVE)
	@OrderBy("hora, minuto")
    private List<EventoHorario> horarios;
	
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@NotNull( message = "A data de criação do usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	@Column( name = "ativo" )
	private Boolean ativo;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn( name="id_usuario" ) 
	private Usuario usuarioCriacao;
	
	
	public Evento()
	{
		super();
		this.dataCriacao = new Date();
		this.ativo = true;
	}


	public Long getIdEvento()
	{
		return idEvento;
	}


	public void setIdEvento( Long idEvento )
	{
		this.idEvento = idEvento;
	}


	public Ambiente getAmbiente()
	{
		return ambiente;
	}


	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}


	public Categoria getCategoria()
	{
		return categoria;
	}


	public void setCategoria( Categoria categoria )
	{
		this.categoria = categoria;
	}


	public Midia getMidia()
	{
		return midia;
	}


	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}


	public Boolean getRepeteDomingo()
	{
		return repeteDomingo;
	}


	public void setRepeteDomingo( Boolean repeteDomingo )
	{
		this.repeteDomingo = repeteDomingo;
	}


	public Boolean getRepeteSegunda()
	{
		return repeteSegunda;
	}


	public void setRepeteSegunda( Boolean repeteSegunda )
	{
		this.repeteSegunda = repeteSegunda;
	}


	public Boolean getRepeteTerca()
	{
		return repeteTerca;
	}


	public void setRepeteTerca( Boolean repeteTerca )
	{
		this.repeteTerca = repeteTerca;
	}


	public Boolean getRepeteQuarta()
	{
		return repeteQuarta;
	}


	public void setRepeteQuarta( Boolean repeteQuarta )
	{
		this.repeteQuarta = repeteQuarta;
	}


	public Boolean getRepeteQuinta()
	{
		return repeteQuinta;
	}


	public void setRepeteQuinta( Boolean repeteQuinta )
	{
		this.repeteQuinta = repeteQuinta;
	}


	public Boolean getRepeteSexta()
	{
		return repeteSexta;
	}


	public void setRepeteSexta( Boolean repeteSexta )
	{
		this.repeteSexta = repeteSexta;
	}


	public Boolean getRepeteSabado()
	{
		return repeteSabado;
	}


	public void setRepeteSabado( Boolean repeteSabado )
	{
		this.repeteSabado = repeteSabado;
	}


	public Date getDataCriacao()
	{
		return dataCriacao;
	}


	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}


	public Boolean getAtivo()
	{
		return ativo;
	}


	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}


	public Usuario getUsuarioCriacao()
	{
		return usuarioCriacao;
	}


	public void setUsuarioCriacao( Usuario usuarioCriacao )
	{
		this.usuarioCriacao = usuarioCriacao;
	}


	public List<EventoHorario> getHorarios()
	{
		return horarios;
	}


	public void setHorarios( List<EventoHorario> horarios )
	{
		this.horarios = horarios;
	}


	public Date getDataInicio()
	{
		return dataInicio;
	}


	public void setDataInicio( Date dataInicio )
	{
		this.dataInicio = dataInicio;
	}


	public Date getDataFim()
	{
		return dataFim;
	}


	public void setDataFim( Date dataFim )
	{
		this.dataFim = dataFim;
	}


	@Override
	public String toString()
	{
		return String
				.format(
						"Evento [idEvento=%s, ambiente=%s, categoria=%s, midia=%s, dataInicio=%s, dataFim=%s, repeteDomingo=%s, repeteSegunda=%s, repeteTerca=%s, repeteQuarta=%s, repeteQuinta=%s, repeteSexta=%s, repeteSabado=%s, horarios=%s, ativo=%s]",
						idEvento, ambiente, categoria, midia, dataInicio, dataFim, repeteDomingo, repeteSegunda, repeteTerca, repeteQuarta, repeteQuinta, repeteSexta, repeteSabado, horarios, ativo );
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idEvento == null ) ? 0 : idEvento.hashCode() );
		return result;
	}


	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Evento other = (Evento) obj;
		if ( idEvento == null )
		{
			if ( other.idEvento != null )
				return false;
		}
		else if ( !idEvento.equals( other.idEvento ) )
			return false;
		return true;
	}

	

	
	

}