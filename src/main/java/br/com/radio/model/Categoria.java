package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Categoria
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_categoria", nullable = false )
	private Long id_categoria;

	@NotNull( message = "O nome da Categoria é de preenchimento obrigatório" )
	@Column( name = "categoria", nullable = false, length = 100 )
	private String nome;
	
	@Column( name = "descricao", columnDefinition = "TEXT" )
	private String descricao;

	public Long getId_categoria()
	{
		return id_categoria;
	}

	public void setId_categoria( Long id_categoria )
	{
		this.id_categoria = id_categoria;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}
	
	
	

}
