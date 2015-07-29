package br.com.radio.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="ambiente")
//@JsonDeserialize(using=JSONAmbienteDeserializer.class)
public class Ambiente implements Model<Long> {

	private static final long serialVersionUID = -703457623897298000L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_ambiente_amb", nullable = false )
	private Long id_ambiente_amb;

	@NotNull( message = "O nome do Ambiente é de preenchimento obrigatório" )
	@Column( name = "nm_ambiente_amb", nullable = false, length = 200 )
	private String nm_ambiente_amb;
	
	// Depois mudar esses telefones para tabelas
	private String cd_telefone1_amb;
	
	private String cd_telefone2_amb;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ambiente" )
//	private List<AmbienteEmail> emails;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ambiente" )
	private List<AmbienteEndereco> enderecos;
	
	@Column( name = "ds_anotacoes_amb", length = 4000 )
	private String ds_anotacoes_amb;
	
	// incluir o fuso horário
	
	@NotNull( message = "O nome de login é de preenchimento obrigatório" )
	@Column( name = "cd_login_amb", nullable = false, length = 40 )
	private String cd_login_amb;
	
	@NotNull( message = "A senha é de preenchimento obrigatório" )
	@Column( name = "cd_password_amb", nullable = false, length = 200 )
	private String cd_password_amb;
	
	private Boolean fl_opcionais_amb;

	private Boolean fl_sincronizar_amb;

	private Boolean fl_download_amb;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do Ambiente é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_amb", nullable = false )
	private Date dt_criacao_amb;
	
	@OneToOne
	@JoinColumn(name = "id_usuario_usu", referencedColumnName = "id_usuario_usu")
	private Usuario usuarioCriacao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_alteracao_amb" )
	private Date dt_alteracao_amb;
	
	
	@Override
	public Long getId()
	{
		return this.id_ambiente_amb;
	}

	@Override
	public void setId( Long id )
	{
		this.id_ambiente_amb = id;
	}

	public Long getId_ambiente_amb()
	{
		return id_ambiente_amb;
	}

	public void setId_ambiente_amb( Long id_ambiente_amb )
	{
		this.id_ambiente_amb = id_ambiente_amb;
	}

	public String getNm_ambiente_amb()
	{
		return nm_ambiente_amb;
	}

	public void setNm_ambiente_amb( String nm_ambiente_amb )
	{
		this.nm_ambiente_amb = nm_ambiente_amb;
	}

	public String getCd_telefone1_amb()
	{
		return cd_telefone1_amb;
	}

	public void setCd_telefone1_amb( String cd_telefone1_amb )
	{
		this.cd_telefone1_amb = cd_telefone1_amb;
	}

	public String getCd_telefone2_amb()
	{
		return cd_telefone2_amb;
	}

	public void setCd_telefone2_amb( String cd_telefone2_amb )
	{
		this.cd_telefone2_amb = cd_telefone2_amb;
	}

	public List<AmbienteEndereco> getEnderecos()
	{
		return enderecos;
	}

	public void setEnderecos( List<AmbienteEndereco> enderecos )
	{
		this.enderecos = enderecos;
	}

	public String getDs_anotacoes_amb()
	{
		return ds_anotacoes_amb;
	}

	public void setDs_anotacoes_amb( String ds_anotacoes_amb )
	{
		this.ds_anotacoes_amb = ds_anotacoes_amb;
	}

	public String getCd_login_amb()
	{
		return cd_login_amb;
	}

	public void setCd_login_amb( String cd_login_amb )
	{
		this.cd_login_amb = cd_login_amb;
	}

	public String getCd_password_amb()
	{
		return cd_password_amb;
	}

	public void setCd_password_amb( String cd_password_amb )
	{
		this.cd_password_amb = cd_password_amb;
	}

	public Boolean getFl_opcionais_amb()
	{
		return fl_opcionais_amb;
	}

	public void setFl_opcionais_amb( Boolean fl_opcionais_amb )
	{
		this.fl_opcionais_amb = fl_opcionais_amb;
	}

	public Boolean getFl_sincronizar_amb()
	{
		return fl_sincronizar_amb;
	}

	public void setFl_sincronizar_amb( Boolean fl_sincronizar_amb )
	{
		this.fl_sincronizar_amb = fl_sincronizar_amb;
	}

	public Boolean getFl_download_amb()
	{
		return fl_download_amb;
	}

	public void setFl_download_amb( Boolean fl_download_amb )
	{
		this.fl_download_amb = fl_download_amb;
	}
	
	public Date getDt_criacao_amb()
	{
		return dt_criacao_amb;
	}

	public void setDt_criacao_amb( Date dt_criacao_amb )
	{
		this.dt_criacao_amb = dt_criacao_amb;
	}

	public Usuario getUsuarioCriacao()
	{
		return usuarioCriacao;
	}

	public void setUsuarioCriacao( Usuario usuarioCriacao )
	{
		this.usuarioCriacao = usuarioCriacao;
	}

	public Date getDt_alteracao_amb()
	{
		return dt_alteracao_amb;
	}

	public void setDt_alteracao_amb( Date dt_alteracao_amb )
	{
		this.dt_alteracao_amb = dt_alteracao_amb;
	}

	public Ambiente()
	{
		super();
		this.fl_opcionais_amb = false;
		this.fl_sincronizar_amb = false;
		this.fl_download_amb = false;
		this.dt_criacao_amb = new Date();
	}

	

}
