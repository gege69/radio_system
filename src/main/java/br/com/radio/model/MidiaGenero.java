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
 * Midia x Gênero
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
	@Column( name = "id_mediagen", nullable = false )
	private Long id_mediagen;

	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name="id_midia")
	private Midia midia;

	public Long getId_mediagen()
	{
		return id_mediagen;
	}

	public void setId_mediagen( Long id_mediagen )
	{
		this.id_mediagen = id_mediagen;
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
