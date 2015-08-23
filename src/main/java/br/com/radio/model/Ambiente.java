package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Ambiente implements Serializable {

	private static final long serialVersionUID = -703457623897298000L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_ambiente_amb", nullable = false )
	private Long id;

	@NotNull( message = "O nome do Ambiente é de preenchimento obrigatório" )
	@Column( name = "nm_ambiente_amb", nullable = false, length = 200 )
	private String nome;

	@Column( name = "cd_telefone1_amb" )
	private String telefone1;
	
	@Column( name = "cd_telefone2_amb" )
	private String telefone2;
	
	@Column( name = "cd_email1_amb", nullable = true )
	private String email1;
	
	@Column( name = "cd_email2_amb" )
	private String email2;
	
	@Column( name = "ds_anotacoes_amb", columnDefinition = "TEXT" )
	private String anotacoes;

	@Column( name = "nm_logradouro_amb", length = 200 )
	private String logradouro;

	@Column( name = "cd_numero_amb", length = 20 )
	private String numero;
	
	@Column( name = "nm_bairro_amb", length = 100 )
	private String bairro;

	@Column( name = "nm_cidade_amb", length = 100 )
	private String cidade;

	@Column( name = "nm_estado_amb", length = 200 )
	private String estado;
	
	@NotNull( message = "O nome de login é de preenchimento obrigatório" )
	@Column( name = "cd_login_amb", nullable = false, length = 40 )
	private String login;
	
	@NotNull( message = "A senha é de preenchimento obrigatório" )
	@Column( name = "cd_password_amb", length = 200 )
	private String password;
	
	@Column( name = "fl_opcionais_amb" )
	private Boolean flagOpcionais;

	@Column( name = "fl_sincronizar_amb" )
	private Boolean flagSincronizar;

	@Column( name = "fl_download_amb" )
	private Boolean flagDownload;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do Ambiente é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_amb", nullable = false )
	private Date dataCriacao;
	
	@OneToOne
	@JoinColumn(name = "id_usuario_usu" )
	private Usuario usuarioCriacao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_alteracao_amb" )
	private Date dataAlteracao;

	
	@OneToOne
	@JoinColumn(name="id_fusohorario_fuh")
	private FusoHorario fusoHorario;
	

	public Ambiente()
	{
		super();
		this.flagOpcionais = false;
		this.flagSincronizar = false;
		this.flagDownload = false;
		this.dataCriacao = new Date();
	}


	public Long getId()
	{
		return id;
	}


	public void setId( Long id )
	{
		this.id = id;
	}


	public String getNome()
	{
		return nome;
	}


	public void setNome( String nome )
	{
		this.nome = nome;
	}


	public String getTelefone1()
	{
		return telefone1;
	}


	public void setTelefone1( String telefone1 )
	{
		this.telefone1 = telefone1;
	}


	public String getTelefone2()
	{
		return telefone2;
	}


	public void setTelefone2( String telefone2 )
	{
		this.telefone2 = telefone2;
	}


	public String getEmail1()
	{
		return email1;
	}


	public void setEmail1( String email1 )
	{
		this.email1 = email1;
	}


	public String getEmail2()
	{
		return email2;
	}


	public void setEmail2( String email2 )
	{
		this.email2 = email2;
	}


	public String getAnotacoes()
	{
		return anotacoes;
	}


	public void setAnotacoes( String anotacoes )
	{
		this.anotacoes = anotacoes;
	}


	public String getLogradouro()
	{
		return logradouro;
	}


	public void setLogradouro( String logradouro )
	{
		this.logradouro = logradouro;
	}


	public String getNumero()
	{
		return numero;
	}


	public void setNumero( String numero )
	{
		this.numero = numero;
	}


	public String getBairro()
	{
		return bairro;
	}


	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}


	public String getCidade()
	{
		return cidade;
	}


	public void setCidade( String cidade )
	{
		this.cidade = cidade;
	}


	public String getEstado()
	{
		return estado;
	}


	public void setEstado( String estado )
	{
		this.estado = estado;
	}


	public String getLogin()
	{
		return login;
	}


	public void setLogin( String login )
	{
		this.login = login;
	}


	public String getPassword()
	{
		return password;
	}


	public void setPassword( String password )
	{
		this.password = password;
	}


	public Boolean getFlagOpcionais()
	{
		return flagOpcionais;
	}


	public void setFlagOpcionais( Boolean flagOpcionais )
	{
		this.flagOpcionais = flagOpcionais;
	}


	public Boolean getFlagSincronizar()
	{
		return flagSincronizar;
	}


	public void setFlagSincronizar( Boolean flagSincronizar )
	{
		this.flagSincronizar = flagSincronizar;
	}


	public Boolean getFlagDownload()
	{
		return flagDownload;
	}


	public void setFlagDownload( Boolean flagDownload )
	{
		this.flagDownload = flagDownload;
	}


	public Date getDataCriacao()
	{
		return dataCriacao;
	}


	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}


	public Usuario getUsuarioCriacao()
	{
		return usuarioCriacao;
	}


	public void setUsuarioCriacao( Usuario usuarioCriacao )
	{
		this.usuarioCriacao = usuarioCriacao;
	}


	public Date getDataAlteracao()
	{
		return dataAlteracao;
	}


	public void setDataAlteracao( Date dataAlteracao )
	{
		this.dataAlteracao = dataAlteracao;
	}


	public FusoHorario getFusoHorario()
	{
		return fusoHorario;
	}


	public void setFusoHorario( FusoHorario fusoHorario )
	{
		this.fusoHorario = fusoHorario;
	}

	

}
