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
 * Tabela de Ligação Cliente x Telefone
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="cliente_telefone")
public class ClienteTelefone implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_clitel", nullable = false )
	private Long idCliTel;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_telefone")
	private Telefone telefone;
	

	public Long getIdCliTel()
	{
		return idCliTel;
	}

	public void setIdCliTel( Long idCliTel )
	{
		this.idCliTel = idCliTel;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public Telefone getTelefone()
	{
		return telefone;
	}

	public void setTelefone( Telefone telefone )
	{
		this.telefone = telefone;
	}

	@Override
	public String toString()
	{
		return String.format( "ClienteTelefone [idCliTel=%s, cliente=%s, telefone=%s]", idCliTel, cliente, telefone );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idCliTel == null ) ? 0 : idCliTel.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof ClienteTelefone ) )
			return false;
		ClienteTelefone other = (ClienteTelefone) obj;
		if ( idCliTel == null )
		{
			if ( other.idCliTel != null )
				return false;
		}
		else if ( !idCliTel.equals( other.idCliTel ) )
			return false;
		return true;
	}

	
	
	
}
