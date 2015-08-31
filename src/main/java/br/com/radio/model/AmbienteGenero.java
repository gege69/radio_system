package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ambiente_genero")
public class AmbienteGenero implements Serializable {

	private static final long serialVersionUID = 7846499560224915976L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_ambgen", nullable = false )
	private Long id_ambgen;
	
	@ManyToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;

	public Long getId_ambgen()
	{
		return id_ambgen;
	}

	public void setId_ambgen( Long id_ambgen )
	{
		this.id_ambgen = id_ambgen;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public Genero getGenero()
	{
		return genero;
	}

	public void setGenero( Genero genero )
	{
		this.genero = genero;
	}

	public AmbienteGenero()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AmbienteGenero( Ambiente ambiente, Genero genero )
	{
		super();
		this.ambiente = ambiente;
		this.genero = genero;
	}
	
	
	
	
}
