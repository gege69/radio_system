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
 * Perfil
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="perfil")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_perfil", nullable = false )
	private Long id;

	@NotNull( message = "O nome do Perfil é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, length = 100 )
	private String nome;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}


	
}
