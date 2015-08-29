package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Tabela de Ligação
 * 
 * Usuario x Permissao
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="usuario_permissao")
public class UsuarioPermissao implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuperm", nullable = false )
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_permissao")
	private Permissao permissao;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da permisão de usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;

	
	public Long getId_usuperm_upp()
	{
		return id;
	}

	public void setId_usuperm_upp( Long id_usuperm_upp )
	{
		this.id = id_usuperm_upp;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Permissao getPermissao()
	{
		return permissao;
	}

	public void setPermissao( Permissao permissao )
	{
		this.permissao = permissao;
	}

	public Date getDt_criacao_upp()
	{
		return dataCriacao;
	}

	public void setDt_criacao_upp( Date dt_criacao_upp )
	{
		this.dataCriacao = dt_criacao_upp;
	}
	
	
}
