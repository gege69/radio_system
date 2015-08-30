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
	private Long id_fusohorario;

	@NotNull( message = "O offset do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "offsetfuso", nullable = false, length = 20 )
	private String offsetfuso;

	@NotNull( message = "O ID canônico do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "canonid", nullable = false, length = 100 )
	private String canonid;
	
	@NotNull( message = "O alias do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "alias", nullable = false, length = 300 )
	private String alias;

	@Column( name = "ordercomum" )
	private Integer orderComum;  // atribui uma prioridade de mais utilizados para poder ordenar. Brasilia por exemplo será inserido com 0, Manaus como 1, etc

	public Long getId_fusohorario()
	{
		return id_fusohorario;
	}

	public void setId_fusohorario( Long id_fusohorario )
	{
		this.id_fusohorario = id_fusohorario;
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

	
	
	

}
