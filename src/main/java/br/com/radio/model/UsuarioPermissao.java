package br.com.radio.model;

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
 * Usuario x Permissao = USP
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="usuario_permissao")
public class UsuarioPermissao implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuperm_upp", nullable = false )
	private Long id_usuperm_upp;

	@ManyToOne
	@JoinColumn(name="id_usuario_usu")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_permissao_prm")
	private Permissao permissao;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da permisão de usuário é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_upp", nullable = false )
	private Date dt_criacao_upp;
	
	@Override
	public Long getId()
	{
		return id_usuperm_upp;
	}

	@Override
	public void setId( Long id )
	{
		this.id_usuperm_upp = id;
	}

	public Long getId_usuperm_upp()
	{
		return id_usuperm_upp;
	}

	public void setId_usuperm_upp( Long id_usuperm_upp )
	{
		this.id_usuperm_upp = id_usuperm_upp;
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
		return dt_criacao_upp;
	}

	public void setDt_criacao_upp( Date dt_criacao_upp )
	{
		this.dt_criacao_upp = dt_criacao_upp;
	}

	public UsuarioPermissao()
	{
		super();
		this.dt_criacao_upp = new Date();
	}

		
	
}
