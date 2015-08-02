package br.com.radio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Gênero = GEN
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="genero")
public class Genero implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_genero_gen", nullable = false )
	private Long id_genero_gen;

	@NotNull( message = "O nome do Gênero é de preenchimento obrigatório" )
	@Column( name = "nm_genero_gen", nullable = false, length = 100 )
	private String nm_genero_gen;

	@Column( name = "ds_descricao_gen", nullable = true, columnDefinition = "TEXT" )
	private String ds_descricao_gen;
	
	// No futuro adicionar algum tipo de hierarquia / árvore de subgêneros

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do Gênero é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_gen", nullable = false )
	private Date dt_criacao_gen;
	

	@Override
	public Long getId()
	{
		return id_genero_gen;
	}

	@Override
	public void setId( Long id )
	{
		this.id_genero_gen = id;
	}

	public Long getId_genero_gen()
	{
		return id_genero_gen;
	}

	public void setId_genero_gen( Long id_genero_gen )
	{
		this.id_genero_gen = id_genero_gen;
	}

	public String getNm_genero_gen()
	{
		return nm_genero_gen;
	}

	public void setNm_genero_gen( String nm_genero_gen )
	{
		this.nm_genero_gen = nm_genero_gen;
	}

	public String getDs_descricao_gen()
	{
		return ds_descricao_gen;
	}

	public void setDs_descricao_gen( String ds_descricao_gen )
	{
		this.ds_descricao_gen = ds_descricao_gen;
	}

	public Date getDt_criacao_gen()
	{
		return dt_criacao_gen;
	}

	public void setDt_criacao_gen( Date dt_criacao_gen )
	{
		this.dt_criacao_gen = dt_criacao_gen;
	}

	
	
	

}
