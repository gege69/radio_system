package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="programacao_genero")
public class ProgramacaoGenero implements Serializable {

	private static final long serialVersionUID = 3498540024881404119L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_proggen", nullable = false )
	private Long idProggen;
	
	@OneToOne
	@JoinColumn(name="id_programacao")
	private Programacao programacao;

	@OneToOne
	@JoinColumn(name="id_genero")
	private Genero genero;
	

	public Long getIdProggen()
	{
		return idProggen;
	}

	public void setIdProggen( Long idProggen )
	{
		this.idProggen = idProggen;
	}

	public Programacao getProgramacao()
	{
		return programacao;
	}

	public void setProgramacao( Programacao programacao )
	{
		this.programacao = programacao;
	}

	public Genero getGenero()
	{
		return genero;
	}

	public void setGenero( Genero genero )
	{
		this.genero = genero;
	}
	
	
	
	
}
