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
	private Long idUsuario;
	
	@NotNull( message = "O nome do usuário é de preenchimento obrigatório" )
	@Column( name = "nm_usuario_usu", nullable = false, length = 200 )
	private String nmUsuario;
	
	@NotNull( message = "O email do usuário é de preenchimento obrigatório" )
	@Column( name = "cd_email_usu", nullable = false, length = 200 )
	private String cdEmail;
	
	@NotNull( message = "O nome de login é de preenchimento obrigatório" )
	@Column( name = "cd_login_usu", nullable = false, length = 40, unique = true )
	private String cdLogin;
	
	@NotNull( message = "A senha é de preenchimento obrigatório" )
	@Column( name = "cd_password_usu", nullable = false )
	private String cdPassword;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_usu", nullable = false )
	private Date dtCriacao;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_alteracao_usu" )
	private Date dtAlteracao;
	
	@NotNull
	@Column( name = "fl_ativo_usu" )
	private Boolean flAtivo;
	
	@Override
	public Long getId()
	{
		return this.idUsuario;
	}

	@Override
	public void setId( Long id )
	{
		this.idUsuario = id;
	}

	public Long getIdUsuario()
	{
		return idUsuario;
	}

	public void setIdUsuario( Long idUsuario )
	{
		this.idUsuario = idUsuario;
	}

	public String getNmUsuario()
	{
		return nmUsuario;
	}

	public void setNmUsuario( String nmUsuario )
	{
		this.nmUsuario = nmUsuario;
	}

	public String getCdEmail()
	{
		return cdEmail;
	}

	public void setCdEmail( String cdEmail )
	{
		this.cdEmail = cdEmail;
	}

	public String getCdLogin()
	{
		return cdLogin;
	}

	public void setCdLogin( String cdLogin )
	{
		this.cdLogin = cdLogin;
	}

	public String getCdPassword()
	{
		return cdPassword;
	}

	public void setCdPassword( String cdPassword )
	{
		this.cdPassword = cdPassword;
	}

	public Date getDtCriacao()
	{
		return dtCriacao;
	}

	public void setDtCriacao( Date dtCriacao )
	{
		this.dtCriacao = dtCriacao;
	}

	public Date getDtAlteracao()
	{
		return dtAlteracao;
	}

	public void setDtAlteracao( Date dtAlteracao )
	{
		this.dtAlteracao = dtAlteracao;
	}

	public Boolean getFlAtivo()
	{
		return flAtivo;
	}

	public void setFlAtivo( Boolean flAtivo )
	{
		this.flAtivo = flAtivo;
	}

	public Usuario()
	{
		super();
		this.dtCriacao = new Date();
	}

	
	
	
}