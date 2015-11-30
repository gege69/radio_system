package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="conversa_usuario")
public class ConversaUsuario implements Serializable {
	
	private static final long serialVersionUID = -7851147505477901911L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_transmissao", nullable = false )
	private Long idTransmissao;

	@ManyToOne
	@JoinColumn(name="id_conversa")
	private Conversa conversa;
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Long getIdTransmissao()
	{
		return idTransmissao;
	}

	public void setIdTransmissao( Long idTransmissao )
	{
		this.idTransmissao = idTransmissao;
	}

	public Conversa getConversa()
	{
		return conversa;
	}

	public void setConversa( Conversa conversa )
	{
		this.conversa = conversa;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}
	
	
	
}
