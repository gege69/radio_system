package br.com.radio.model;

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
 * Mensagem = MSG
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="mensagem")
public class Mensagem implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mensagem_msg", nullable = false )
	private Long id_mensagem_msg;

	@OneToOne
	@JoinColumn(name="id_usuario_usu")
	private Usuario usuario;
	
	@NotNull( message = "O assunto da mensagem é de preenchimento obrigatório" )
	@Column( name = "ds_assunto_msg", nullable = false, length = 200 )
	private String ds_assunto_msg;
	
	@Column( name = "ds_emailcopia_msg", nullable = true, length = 200 )
	private String ds_emailcopia_msg;
	
	@OneToOne
	@JoinColumn(name="id_ambiente_amb")
	private Ambiente ambiente;
	
	@NotNull( message = "O conteúdo da mensagem é de preenchimento obrigatório" )
	@Column( name = "ds_conteudo_msg", nullable = false, columnDefinition = "TEXT" )
	private String ds_conteudo_msg;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_msg", nullable = false )
	private Date dt_criacao_msg;
	
	
	@Override
	public Long getId()
	{
		return id_mensagem_msg;
	}

	@Override
	public void setId( Long id )
	{
		this.id_mensagem_msg = id;
	}

	public Long getId_mensagem_msg()
	{
		return id_mensagem_msg;
	}

	public void setId_mensagem_msg( Long id_mensagem_msg )
	{
		this.id_mensagem_msg = id_mensagem_msg;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public String getDs_assunto_msg()
	{
		return ds_assunto_msg;
	}

	public void setDs_assunto_msg( String ds_assunto_msg )
	{
		this.ds_assunto_msg = ds_assunto_msg;
	}

	public String getDs_emailcopia_msg()
	{
		return ds_emailcopia_msg;
	}

	public void setDs_emailcopia_msg( String ds_emailcopia_msg )
	{
		this.ds_emailcopia_msg = ds_emailcopia_msg;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public String getDs_conteudo_msg()
	{
		return ds_conteudo_msg;
	}

	public void setDs_conteudo_msg( String ds_conteudo_msg )
	{
		this.ds_conteudo_msg = ds_conteudo_msg;
	}

	public Date getDt_criacao_msg()
	{
		return dt_criacao_msg;
	}

	public void setDt_criacao_msg( Date dt_criacao_msg )
	{
		this.dt_criacao_msg = dt_criacao_msg;
	}

	
	

}
