package br.com.radio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private Long idCategoria;

	@NotNull( message = "O nome da Categoria é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, length = 100 )
	private String nome;
	
	@Column( name = "descricao", columnDefinition = "TEXT" )
	private String descricao;
	
	@Column( name = "codigo", columnDefinition = "TEXT" )
	private String codigo;
	
	public Long getIdCategoria()
	{
		return idCategoria;
	}

	public void setIdCategoria( Long idCategoria )
	{
		this.idCategoria = idCategoria;
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

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	public Categoria()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Categoria( Long idCategoria )
	{
		super();
		this.idCategoria = idCategoria;
	}

	public Categoria( Long idCategoria, String nome )
	{
		super();
		this.idCategoria = idCategoria;
		this.nome = nome;
	}

	
	public static List<Categoria> listByIds( Long... categorias )
	{
		List<Categoria> result = new ArrayList<Categoria>();

		for ( Long id : categorias )
		{
			result.add( new Categoria( id ) );
		}
		
		return result;
	}

}
