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
	private Long idAcesso;

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

	public Long getIdAcesso()
	{
		return idAcesso;
	}

	public void setIdAcesso( Long idAcesso )
	{
		this.idAcesso = idAcesso;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idAcesso == null ) ? 0 : idAcesso.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		AcessoUsuario other = (AcessoUsuario) obj;
		if ( idAcesso == null )
		{
			if ( other.idAcesso != null )
				return false;
		}
		else if ( !idAcesso.equals( other.idAcesso ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "AcessoUsuario [idAcesso=%s, usuario=%s]", idAcesso, usuario );
	}

	
	
}
