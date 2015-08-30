package br.com.radio.model;

import java.io.Serializable;
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
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuario", nullable = false )
	private Long id_usuario;
	
	@NotNull( message = "O nome do usuário é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, length = 200 )
	private String nome;
	
	@NotNull( message = "O email do usuário é de preenchimento obrigatório" )
	@Column( name = "email", nullable = false, length = 200 )
	private String email;
	
	@NotNull( message = "O nome de login é de preenchimento obrigatório" )
	@Column( name = "login", nullable = false, length = 40, unique = true )
	private String login;
	
	@NotNull( message = "A senha é de preenchimento obrigatório" )
	@Column( name = "password", nullable = false )
	private String password;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao" )
	private Date dataAlteracao;
	
	@NotNull
	@Column( name = "ativo" )
	private Boolean ativo;

	public Usuario()
	{
		super();
		this.dataCriacao = new Date();
	}

	public Long getId_usuario()
	{
		return id_usuario;
	}

	public void setId_usuario( Long id_usuario )
	{
		this.id_usuario = id_usuario;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
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

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAlteracao()
	{
		return dataAlteracao;
	}

	public void setDataAlteracao( Date dataAlteracao )
	{
		this.dataAlteracao = dataAlteracao;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	
	
	
}