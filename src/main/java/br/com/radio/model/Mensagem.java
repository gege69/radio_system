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



/**
 * Mensagem
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="mensagem")
public class Mensagem implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mensagem", nullable = false )
	private Long id_mensagem;

	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@NotNull( message = "O assunto da mensagem é de preenchimento obrigatório" )
	@Column( name = "assunto", nullable = false, columnDefinition = "TEXT" )
	private String assunto;
	
	@Column( name = "emailcopia", nullable = true, columnDefinition = "TEXT" )
	private String emailCopia;
	
	@OneToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@NotNull( message = "O conteúdo da mensagem é de preenchimento obrigatório" )
	@Column( name = "conteudo", nullable = false, columnDefinition = "TEXT" )
	private String conteudo;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;

	public Long getId_mensagem()
	{
		return id_mensagem;
	}

	public void setId_mensagem( Long id_mensagem )
	{
		this.id_mensagem = id_mensagem;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public void setAssunto( String assunto )
	{
		this.assunto = assunto;
	}

	public String getEmailCopia()
	{
		return emailCopia;
	}

	public void setEmailCopia( String emailCopia )
	{
		this.emailCopia = emailCopia;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public String getConteudo()
	{
		return conteudo;
	}

	public void setConteudo( String conteudo )
	{
		this.conteudo = conteudo;
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
