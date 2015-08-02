package br.com.radio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Perfil = PER
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="perfil")
public class Perfil implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_perfil_per", nullable = false )
	private Long id_perfil_per;

	@NotNull( message = "O nome do Perfil é de preenchimento obrigatório" )
	@Column( name = "nm_perfil_per", nullable = false, length = 100 )
	private String nm_perfil_per;

	@Override
	public Long getId()
	{
		return id_perfil_per;
	}

	@Override
	public void setId( Long id )
	{
		this.id_perfil_per = id;
	}

	public Long getId_perfil_per()
	{
		return id_perfil_per;
	}

	public void setId_perfil_per( Long id_perfil_per )
	{
		this.id_perfil_per = id_perfil_per;
	}

	public String getNm_perfil_per()
	{
		return nm_perfil_per;
	}

	public void setNm_perfil_per( String nm_perfil_per )
	{
		this.nm_perfil_per = nm_perfil_per;
	}
	
	

}
