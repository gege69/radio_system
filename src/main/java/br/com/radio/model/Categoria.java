package br.com.radio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Categoria = CAT
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="categoria")
public class Categoria implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_categoria_cat", nullable = false )
	private Long id_categoria_cat;

	@NotNull( message = "O nome da Categoria é de preenchimento obrigatório" )
	@Column( name = "nm_categoria_cat", nullable = false, length = 100 )
	private String nm_categoria_cat;
	
	@Column( name = "ds_descricao_cat", columnDefinition = "TEXT" )
	private String ds_descricao_cat;
	
	@Override
	public Long getId()
	{
		return id_categoria_cat;
	}

	@Override
	public void setId( Long id )
	{
		this.id_categoria_cat = id;
	}

	public Long getId_categoria_cat()
	{
		return id_categoria_cat;
	}

	public void setId_categoria_cat( Long id_categoria_cat )
	{
		this.id_categoria_cat = id_categoria_cat;
	}

	public String getNm_categoria_cat()
	{
		return nm_categoria_cat;
	}

	public void setNm_categoria_cat( String nm_categoria_cat )
	{
		this.nm_categoria_cat = nm_categoria_cat;
	}

	public String getDs_descricao_cat()
	{
		return ds_descricao_cat;
	}

	public void setDs_descricao_cat( String ds_descricao_cat )
	{
		this.ds_descricao_cat = ds_descricao_cat;
	}
	

}
