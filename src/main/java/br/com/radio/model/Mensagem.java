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
	private Long idMensagem;

	@OneToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@OneToOne
	@JoinColumn(name="id_endereco_origem")
	private MensagemEndereco de;
	
	@OneToOne
	@JoinColumn(name="id_endereco_destino")
	private MensagemEndereco para;
	
	@Column( name = "assunto", nullable = false, columnDefinition = "TEXT" )
	private String assunto;
	
	@NotNull( message = "O conteúdo da mensagem é de preenchimento obrigatório" )
	@Column( name = "conteudo", nullable = false, columnDefinition = "TEXT" )
	private String conteudo;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;

	@Column( name="lida", columnDefinition= "bool default false")
	private Boolean lida;

	public Long getIdMensagem()
	{
		return idMensagem;
	}

	public void setIdMensagem( Long idMensagem )
	{
		this.idMensagem = idMensagem;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public MensagemEndereco getDe()
	{
		return de;
	}

	public void setDe( MensagemEndereco de )
	{
		this.de = de;
	}

	public MensagemEndereco getPara()
	{
		return para;
	}

	public void setPara( MensagemEndereco para )
	{
		this.para = para;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public void setAssunto( String assunto )
	{
		this.assunto = assunto;
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

	public Boolean getLida()
	{
		return lida;
	}

	public void setLida( Boolean lida )
	{
		this.lida = lida;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idMensagem == null ) ? 0 : idMensagem.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Mensagem other = (Mensagem) obj;
		if ( idMensagem == null )
		{
			if ( other.idMensagem != null )
				return false;
		}
		else if ( !idMensagem.equals( other.idMensagem ) )
			return false;
		return true;
	}


	
}
