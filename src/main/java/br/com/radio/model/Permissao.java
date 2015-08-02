package br.com.radio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Permissao = PRM
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="permissao")
public class Permissao implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_permissao_prm", nullable = false )
	private Long id_permissao_prm;
	
	@NotNull( message = "O alias da Permissão é de preenchimento obrigatório" )
	@Column( name = "cd_permiss_per", nullable = false, length = 100 )
	private String cd_permiss_per;

	@NotNull( message = "A descrição da Permissão é de preenchimento obrigatório" )
	@Column( name = "ds_permiss_per", nullable = false, length = 400 )
	private String ds_permiss_per;
	
	@Override
	public Long getId()
	{
		return id_permissao_prm;
	}

	@Override
	public void setId( Long id )
	{
		this.id_permissao_prm = id;
	}

	public Long getId_permissao_prm()
	{
		return id_permissao_prm;
	}

	public void setId_permissao_prm( Long id_permissao_prm )
	{
		this.id_permissao_prm = id_permissao_prm;
	}

	public String getCd_permiss_per()
	{
		return cd_permiss_per;
	}

	public void setCd_permiss_per( String cd_permiss_per )
	{
		this.cd_permiss_per = cd_permiss_per;
	}

	public String getDs_permiss_per()
	{
		return ds_permiss_per;
	}

	public void setDs_permiss_per( String ds_permiss_per )
	{
		this.ds_permiss_per = ds_permiss_per;
	}

	

}
