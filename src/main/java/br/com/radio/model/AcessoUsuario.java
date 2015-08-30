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

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Acesso Usuario
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="acesso_usuario")
public class AcessoUsuario implements Serializable {
	
	private static final long serialVersionUID = 454354660695909060L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_acesso", nullable = false )
	private Long id_acesso;

	@OneToOne
	@JoinColumn( name="id_usuario" )
	private Usuario usuario;
	
	@Column( name = "enderecoip", nullable = false, length = 50 )
	private String enderecoIp;
	
	// Todos os dados da conexão 
	@Column( name = "dados", columnDefinition = "TEXT" )
	private String dados;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
		
	public AcessoUsuario()
	{
		super();
		this.dataCriacao = new Date();
	}

	public Long getId_acesso()
	{
		return id_acesso;
	}

	public void setId_acesso( Long id_acesso )
	{
		this.id_acesso = id_acesso;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public String getEnderecoIp()
	{
		return enderecoIp;
	}

	public void setEnderecoIp( String enderecoIp )
	{
		this.enderecoIp = enderecoIp;
	}

	public String getDados()
	{
		return dados;
	}

	public void setDados( String dados )
	{
		this.dados = dados;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	
	
}
