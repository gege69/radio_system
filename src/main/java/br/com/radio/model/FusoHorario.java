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
	private Long id_fusohorario_fuh;

	@NotNull( message = "O offset do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "ds_offset_fuh", nullable = false, length = 20 )
	private String ds_offset_fuh;

	@NotNull( message = "O ID canônico do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "ds_canonid_fuh", nullable = false, length = 100 )
	private String ds_canonid_fuh;
	
	@NotNull( message = "O alias do Fuso Horário é de preenchimento obrigatório" )
	@Column( name = "nm_alias_fuh", nullable = false, length = 300 )
	private String nm_alias_fuh;

	@Override
	public Long getId()
	{
		return id_fusohorario_fuh;
	}

	@Override
	public void setId( Long id )
	{
		this.id_fusohorario_fuh = id;
	}

	public Long getId_fusohorario_fuh()
	{
		return id_fusohorario_fuh;
	}

	public void setId_fusohorario_fuh( Long id_fusohorario_fuh )
	{
		this.id_fusohorario_fuh = id_fusohorario_fuh;
	}

	public String getDs_offset_fuh()
	{
		return ds_offset_fuh;
	}

	public void setDs_offset_fuh( String ds_offset_fuh )
	{
		this.ds_offset_fuh = ds_offset_fuh;
	}

	public String getDs_canonid_fuh()
	{
		return ds_canonid_fuh;
	}

	public void setDs_canonid_fuh( String ds_canonid_fuh )
	{
		this.ds_canonid_fuh = ds_canonid_fuh;
	}

	public String getNm_alias_fuh()
	{
		return nm_alias_fuh;
	}

	public void setNm_alias_fuh( String nm_alias_fuh )
	{
		this.nm_alias_fuh = nm_alias_fuh;
	}
	
	

}
