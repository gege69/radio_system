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



/**
 * Tabela de ligação 
 * 
 * Midia x Gênero = MGN
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia_genero")
public class MidiaGenero implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mediagen_mgn", nullable = false )
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_genero_gen")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name="id_midia_mid")
	private Midia midia;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Genero getGenero()
	{
		return genero;
	}

	public void setGenero( Genero genero )
	{
		this.genero = genero;
	}

	public Midia getMidia()
	{
		return midia;
	}

	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}

	


}
