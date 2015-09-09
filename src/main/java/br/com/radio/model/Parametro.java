package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="parametro")
public class Parametro implements Serializable {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_parametro", nullable = false )
	private Long id_parametro;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn( name="id_empresa", nullable=false )
	private Empresa empresa;
	
	@NotNull( message = "O código do parâmetro é de preenchimento obrigatório" )
	@Column( name = "codigo", nullable = false, columnDefinition = "TEXT" )
	private String codigo;
	
	@NotNull( message = "O valor do parâmetro é de preenchimento obrigatório" )
	@Column( name = "valor", nullable = false, columnDefinition = "TEXT" )
	private String valor;
	
	@Column( name = "descricao", columnDefinition = "TEXT" )
	private String descricao;

	@Column( name = "type", columnDefinition = "TEXT" )
	private String type;

	public Long getId_parametro()
	{
		return id_parametro;
	}

	public void setId_parametro( Long id_parametro )
	{
		this.id_parametro = id_parametro;
	}

	public Empresa getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa( Empresa empresa )
	{
		this.empresa = empresa;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	public String getValor()
	{
		return valor;
	}

	public void setValor( String valor )
	{
		this.valor = valor;
	}
	
		
	
	
}