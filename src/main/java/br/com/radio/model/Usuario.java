package br.com.radio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="usuario")
public class Usuario implements Model<Long> {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuario_usu", nullable = false )
	private Long id_usuario_usu;
	
	@NotNull( message = "O nome do usuário é de preenchimento obrigatório" )
	@Column( name = "nm_usuario_usu", nullable = false, length = 200 )
	private String nm_usuario_usu;
	
	@NotNull( message = "O nome de login é de preenchimento obrigatório" )
	@Column( name = "cd_login_usu", nullable = false, length = 40 )
	private String cd_login_usu;
	
	@NotNull( message = "A senha é de preenchimento obrigatório" )
	@Column( name = "cd_password_usu", nullable = false, length = 200 )
	private String cd_password_usu;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_usu", nullable = false )
	private Date dt_criacao_usu;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_alteracao_usu" )
	private Date dt_alteracao_usu;
	
	@NotNull
	private Boolean fl_ativo_usu;
	
	@Override
	public Long getId()
	{
		return this.id_usuario_usu;
	}

	@Override
	public void setId( Long id )
	{
		this.id_usuario_usu = id;
	}

	

	public Long getId_usuario_usu()
	{
		return id_usuario_usu;
	}

	public void setId_usuario_usu( Long id_usuario_usu )
	{
		this.id_usuario_usu = id_usuario_usu;
	}

	public String getNm_usuario_usu()
	{
		return nm_usuario_usu;
	}

	public void setNm_usuario_usu( String nm_usuario_usu )
	{
		this.nm_usuario_usu = nm_usuario_usu;
	}

	public String getCd_login_usu()
	{
		return cd_login_usu;
	}

	public void setCd_login_usu( String cd_login_usu )
	{
		this.cd_login_usu = cd_login_usu;
	}

	public String getCd_password_usu()
	{
		return cd_password_usu;
	}

	public void setCd_password_usu( String cd_password_usu )
	{
		this.cd_password_usu = cd_password_usu;
	}

	public Date getDt_criacao_usu()
	{
		return dt_criacao_usu;
	}

	public void setDt_criacao_usu( Date dt_criacao_usu )
	{
		this.dt_criacao_usu = dt_criacao_usu;
	}

	public Date getDt_alteracao_usu()
	{
		return dt_alteracao_usu;
	}

	public void setDt_alteracao_usu( Date dt_alteracao_usu )
	{
		this.dt_alteracao_usu = dt_alteracao_usu;
	}

	public Boolean getFl_ativo_usu()
	{
		return fl_ativo_usu;
	}

	public void setFl_ativo_usu( Boolean fl_ativo_usu )
	{
		this.fl_ativo_usu = fl_ativo_usu;
	}

	public Usuario()
	{
		super();
		this.dt_criacao_usu = new Date();
	}

	
}