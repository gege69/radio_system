package br.com.radio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * FUso Horario = FUH
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="fuso_horario")
public class FusoHorario implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_fusohorario_fuh", nullable = false )
	private Long id;

	@NotNull( message = "O offset do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "ds_offset_fuh", nullable = false, length = 20 )
	private String offset;

	@NotNull( message = "O ID canônico do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "ds_canonid_fuh", nullable = false, length = 100 )
	private String canonid;
	
	@NotNull( message = "O alias do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "nm_alias_fuh", nullable = false, length = 300 )
	private String alias;

	@Column( name = "id_ordercomum_fuh" )
	private Integer orderComum;  // atribui uma prioridade de mais utilizados para poder ordenar. Brasilia por exemplo será inserido com 0, Manaus como 1, etc

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getOffset()
	{
		return offset;
	}

	public void setOffset( String offset )
	{
		this.offset = offset;
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
