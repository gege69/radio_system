package br.com.radio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Ambiente ENdereco = AEN
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="ambiente_endereco")
public class AmbienteEndereco implements Model<Long> {

	private static final long serialVersionUID = 2428608915078223201L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_endereco_aen", nullable = false )
	private Long id_endereco_aen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_ambiente_amb")
	private Ambiente ambiente;

	@NotNull( message = "O nome da rua é de preenchimento obrigatório" )
	@Column( name = "nm_logradouro_aen", nullable = false, length = 200 )
	private String nm_logradouro_aen;

	@NotNull( message = "O número é de preenchimento obrigatório" )
	@Column( name = "cd_numero_aen", nullable = false, length = 20 )
	private String cd_numero_aen;
	
	@NotNull( message = "O bairro é de preenchimento obrigatório" )
	@Column( name = "nm_bairro_aen", nullable = false, length = 100 )
	private String nm_bairro_aen;

	@NotNull( message = "O nome da Cidade é de preenchimento obrigatório" )
	@Column( name = "nm_cidade_aen", nullable = false, length = 100 )
	private String nm_cidade_aen;

	@NotNull( message = "O Estado é de preenchimento obrigatório" )
	@Column( name = "nm_estado_aen", nullable = false, length = 200 )
	private String nm_estado_aen;
	
	@Override
	public Long getId()
	{
		return this.id_endereco_aen;
	}

	@Override
	public void setId( Long id )
	{
		this.id_endereco_aen = id;		
	}

	public Long getId_endereco_aen()
	{
		return id_endereco_aen;
	}

	public void setId_endereco_aen( Long id_endereco_aen )
	{
		this.id_endereco_aen = id_endereco_aen;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public String getNm_logradouro_aen()
	{
		return nm_logradouro_aen;
	}

	public void setNm_logradouro_aen( String nm_logradouro_aen )
	{
		this.nm_logradouro_aen = nm_logradouro_aen;
	}

	public String getCd_numero_aen()
	{
		return cd_numero_aen;
	}

	public void setCd_numero_aen( String cd_numero_aen )
	{
		this.cd_numero_aen = cd_numero_aen;
	}

	public String getNm_bairro_aen()
	{
		return nm_bairro_aen;
	}

	public void setNm_bairro_aen( String nm_bairro_aen )
	{
		this.nm_bairro_aen = nm_bairro_aen;
	}

	public String getNm_cidade_aen()
	{
		return nm_cidade_aen;
	}

	public void setNm_cidade_aen( String nm_cidade_aen )
	{
		this.nm_cidade_aen = nm_cidade_aen;
	}

	public String getNm_estado_aen()
	{
		return nm_estado_aen;
	}

	public void setNm_estado_aen( String nm_estado_aen )
	{
		this.nm_estado_aen = nm_estado_aen;
	}

}
