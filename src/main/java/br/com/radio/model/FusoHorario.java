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
 * Fuso Horario
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="fuso_horario")
public class FusoHorario implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_fusohorario", nullable = false )
	private Long idFusohorario;

	@NotNull( message = "O offset do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "offsetfuso", nullable = false, length = 20 )
	private String offsetfuso;

	@NotNull( message = "O ID canônico do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "canonid", nullable = false, columnDefinition = "TEXT" )
	private String canonid;
	
	@NotNull( message = "O alias do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "alias", nullable = false, columnDefinition = "TEXT" )
	private String alias;

	@Column( name = "ordercomum" )
	private Integer orderComum;  // atribui uma prioridade de mais utilizados para poder ordenar. Brasilia por exemplo será inserido com 0, Manaus como 1, etc

	public Long getIdFusohorario()
	{
		return idFusohorario;
	}

	public void setIdFusohorario( Long idFusohorario )
	{
		this.idFusohorario = idFusohorario;
	}

	public String getOffsetfuso()
	{
		return offsetfuso;
	}

	public void setOffsetfuso( String offsetfuso )
	{
		this.offsetfuso = offsetfuso;
	}

	public String getCanonid()
	{
		return canonid;
	}

	public void setCanonid( String canonid )
	{
		this.canonid = canonid;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias( String alias )
	{
		this.alias = alias;
	}

	public Integer getOrderComum()
	{
		return orderComum;
	}

	public void setOrderComum( Integer orderComum )
	{
		this.orderComum = orderComum;
	}

	public FusoHorario()
	{
		super();
		orderComum = 99;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idFusohorario == null ) ? 0 : idFusohorario.hashCode() );
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
		FusoHorario other = (FusoHorario) obj;
		if ( idFusohorario == null )
		{
			if ( other.idFusohorario != null )
				return false;
		}
		else if ( !idFusohorario.equals( other.idFusohorario ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "FusoHorario [idFusohorario=%s, alias=%s]", idFusohorario, alias );
	}
	

}
