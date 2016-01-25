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
 * Midia x Categoria
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia_categoria")
public class MidiaCategoria implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mediacat", nullable = false )
	private Long idMediacat;

	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="id_midia")
	private Midia midia;

	
	public Long getIdMediacat()
	{
		return idMediacat;
	}

	public void setIdMediacat( Long idMediacat )
	{
		this.idMediacat = idMediacat;
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

	
	

}
