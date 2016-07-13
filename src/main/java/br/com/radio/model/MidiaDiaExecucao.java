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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.radio.enumeration.DiaSemana;



/**
 * Tabela que determina em quais dias a midia irá tocar. Caso não exista nenhum registro ela irá tocar todos os dias.
 * 
 * Midia x Dia de Execução
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia_dia_execucao")
public class MidiaDiaExecucao implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mediadia", nullable = false )
	private Long idMediadia;

	@NotNull( message = "O dia da semana é de preenchimento obrigatório" )
	@Enumerated(EnumType.STRING)
	@Column( name = "dia" )
	private DiaSemana diaSemana;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_midia")
	private Midia midia;

	public Long getIdMediadia()
	{
		return idMediadia;
	}

	public void setIdMediadia( Long idMediadia )
	{
		this.idMediadia = idMediadia;
	}

	public DiaSemana getDiaSemana()
	{
		return diaSemana;
	}

	public void setDiaSemana( DiaSemana diaSemana )
	{
		this.diaSemana = diaSemana;
	}

	public Midia getMidia()
	{
		return midia;
	}

	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}

	public MidiaDiaExecucao()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public MidiaDiaExecucao( DiaSemana diaSemana, Midia midia )
	{
		super();
		this.diaSemana = diaSemana;
		this.midia = midia;
	}

	@Override
	public String toString()
	{
		return String.format( "MidiaDiaExecucao [idMediadia=%s, diaSemana=%s, midia=%s]", idMediadia, diaSemana, midia );
	}


}
