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

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Telefone
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="telefone")
public class Telefone implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_telefone", nullable = false )
	private Long idTelefone;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@Column( name = "ddd", length = 5)
	private String ddd;    
	
	@Column( name = "numero")
	private String numero;

	public Long getIdTelefone()
	{
		return idTelefone;
	}

	public void setIdTelefone( Long idTelefone )
	{
		this.idTelefone = idTelefone;
	}

	public String getDdd()
	{
		return ddd;
	}

	public void setDdd( String ddd )
	{
		this.ddd = ddd;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero( String numero )
	{
		this.numero = numero;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idTelefone == null ) ? 0 : idTelefone.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof Telefone ) )
			return false;
		Telefone other = (Telefone) obj;
		if ( idTelefone == null )
		{
			if ( other.idTelefone != null )
				return false;
		}
		else if ( !idTelefone.equals( other.idTelefone ) )
			return false;
		return true;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	@Override
	public String toString()
	{
		return String.format( "Telefone [idTelefone=%s, cliente=%s, ddd=%s, numero=%s]", idTelefone, cliente, ddd, numero );
	} 

	
	
}
