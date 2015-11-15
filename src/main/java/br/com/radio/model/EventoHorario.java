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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="evento_horario")
public class EventoHorario implements Serializable {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id_eventohorario", nullable = false )
	private Long idEventoHorario;
	
	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name="id_evento", nullable=false )
	private Evento evento;

	@NotNull
	@Column( name = "hora", nullable = false )
	private Integer hora;
	
	@NotNull
	@Column( name = "minuto", nullable = false )
	private Integer minuto;

	@NotNull
	@Column( name = "segundo", nullable = false )   // por enquanto não suportado
	private Integer segundo;
	
	@JsonSerialize( using = JSONDateSerializer.class )
	@NotNull( message = "A data de criação do usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	
	public EventoHorario()
	{
		super();
		this.dataCriacao = new Date();
	}


	public Long getIdEventoHorario()
	{
		return idEventoHorario;
	}


	public void setIdEventoHorario( Long idEventoHorario )
	{
		this.idEventoHorario = idEventoHorario;
	}


	public Evento getEvento()
	{
		return evento;
	}


	public void setEvento( Evento evento )
	{
		this.evento = evento;
	}


	public Integer getHora()
	{
		return hora;
	}


	public void setHora( Integer hora )
	{
		this.hora = hora;
	}


	public Integer getMinuto()
	{
		return minuto;
	}


	public void setMinuto( Integer minuto )
	{
		this.minuto = minuto;
	}


	public Integer getSegundo()
	{
		return segundo;
	}


	public void setSegundo( Integer segundo )
	{
		this.segundo = segundo;
	}


	public Date getDataCriacao()
	{
		return dataCriacao;
	}


	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

}