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
	private Long idParametro;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn( name="id_cliente", nullable=false )
	private Cliente cliente;
	
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

	public Long getIdParametro()
	{
		return idParametro;
	}

	public void setIdParametro( Long idParametro )
	{
		this.idParametro = idParametro;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idParametro == null ) ? 0 : idParametro.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Parametro other = (Parametro) obj;
		if ( idParametro == null )
		{
			if ( other.idParametro != null )
				return false;
		}
		else if ( !idParametro.equals( other.idParametro ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Parametro [idParametro=%s, cliente=%s, codigo=%s, valor=%s, descricao=%s, type=%s]", idParametro, cliente, codigo, valor, descricao, type );
	}
	
}