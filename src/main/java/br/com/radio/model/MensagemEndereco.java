package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.radio.enumeration.TipoUsuarioMensagem;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * 
 * Essa classe é análoga ao "endereço de email".
 * 
 * Tanto Usuário do sistema quando um Ambiente ( classes distintas ) podem trocar mensagens portanto é necessário 
 * uma entidade única para poder representar o "DE" e o "PARA" no endereçamento das mensagens
 * 
 * Usuário vai ter um endereço
 * 
 * Ambiente vai ter um endereço
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="mensagem_endereco")
public class MensagemEndereco implements Serializable {

	private static final long serialVersionUID = 5341117961820300209L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_mensagemendereco", nullable = false )
	private Long idMensagemEndereco;
	
	@Column( name = "ativo") 
	private Boolean ativo;

	@Enumerated( EnumType.STRING )
	@Column(name="tipoUsuarioMensagem")
	private TipoUsuarioMensagem tipoUsuarioMensagem;
	
	// Um desses dois está null sempre... fazendo o hibernate proucrar pra mim
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, mappedBy="mensagemEndereco")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, mappedBy="mensagemEndereco")
	private Ambiente ambiente;
	

	public Long getIdMensagemEndereco()
	{
		return idMensagemEndereco;
	}

	public void setIdMensagemEndereco( Long idMensagemEndereco )
	{
		this.idMensagemEndereco = idMensagemEndereco;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	public TipoUsuarioMensagem getTipoUsuarioMensagem()
	{
		return tipoUsuarioMensagem;
	}

	public void setTipoUsuarioMensagem( TipoUsuarioMensagem tipoUsuarioMensagem )
	{
		this.tipoUsuarioMensagem = tipoUsuarioMensagem;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idMensagemEndereco == null ) ? 0 : idMensagemEndereco.hashCode() );
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
		MensagemEndereco other = (MensagemEndereco) obj;
		if ( idMensagemEndereco == null )
		{
			if ( other.idMensagemEndereco != null )
				return false;
		}
		else if ( !idMensagemEndereco.equals( other.idMensagemEndereco ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "MensagemEndereco [tipoUsuarioMensagem=%s, usuario=%s, ambiente=%s]", tipoUsuarioMensagem, usuario, ambiente );
	}
	
	

}
