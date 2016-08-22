package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateTimeSerializer;
import br.com.radio.util.UtilsStr;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Representa um texto dentro de uma conversação
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="mensagem")
public class Mensagem implements Serializable {
	
	private static final long serialVersionUID = -3036876590707088140L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mensagem", nullable = false )
	private Long idMensagem;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataenvio" )
	private Date dataEnvio;
	
	@Column(name="conteudo", columnDefinition= "TEXT")
	private String conteudo;
	
	@ManyToOne()
	@JoinColumn(name="id_conversa")
	@Fetch( FetchMode.JOIN )
	private Conversa conversa;

	@Transient
	private Map<String,String> mensagemView = new HashMap<String,String>();
	

	public Long getIdMensagem()
	{
		return idMensagem;
	}

	public void setIdMensagem( Long idMensagem )
	{
		this.idMensagem = idMensagem;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Date getDataEnvio()
	{
		return dataEnvio;
	}

	public void setDataEnvio( Date dataEnvio )
	{
		this.dataEnvio = dataEnvio;
	}

	public String getConteudo()
	{
		return conteudo;
	}

	public void setConteudo( String conteudo )
	{
		this.conteudo = conteudo;
	}

	@Override
	public String toString()
	{
		return String.format( "Mensagem [idMensagem=%s, usuario=%s, dataEnvio=%s, conteudo=%s]", idMensagem, usuario, dataEnvio, conteudo );
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

	public Conversa getConversa()
	{
		return conversa;
	}

	public void setConversa( Conversa conversa )
	{
		this.conversa = conversa;
	}

	@JsonAnyGetter
	public Map<String, String> getMensagemView()
	{
		return mensagemView;
	}

	@JsonAnySetter
	public void setMensagemView( Map<String, String> mensagemView )
	{
		this.mensagemView = mensagemView;
	}


	public void buildView( Usuario usuarioLogado )
	{
		if ( getUsuario() == null )
			mensagemView.put( "usuario", "Sistema" );
		else
			mensagemView.put( "usuario", getUsuario().getNome() );
		
		if ( getUsuario() != null && getUsuario().getIdUsuario().equals( usuarioLogado.getIdUsuario() ) ){
			mensagemView.put( "htmlclass", "self" );
			mensagemView.put( "alertclass", "alert-success" );
		}
		else {
			mensagemView.put( "htmlclass", "other" );
			mensagemView.put( "alertclass", "alert-info" );
		} 
		
		mensagemView.put( "conteudoHtml", UtilsStr.replaceNewLineHtml( conteudo ) );
	}
	

}
