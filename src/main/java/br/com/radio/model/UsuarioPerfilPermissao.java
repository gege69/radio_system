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
 * Tabela de Ligação
 * 
 * UsuarioPerfil x Permissao = UPP
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="usuario_perfil_permissao")
public class UsuarioPerfilPermissao implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_usuperfperm_upp", nullable = false )
	private Long id_usuperfperm_upp;

	@OneToOne
	@JoinColumn(name="id_usuperf_upf")
	private UsuarioPerfil usuarioPerfil;
	
	@OneToOne
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
		return id_usuperfperm_upp;
	}

	@Override
	public void setId( Long id )
	{
		this.id_usuperfperm_upp = id;
	}

	public Long getId_usuperfperm_upp()
	{
		return id_usuperfperm_upp;
	}

	public void setId_usuperfperm_upp( Long id_usuperfperm_upp )
	{
		this.id_usuperfperm_upp = id_usuperfperm_upp;
	}

	public UsuarioPerfil getUsuarioPerfil()
	{
		return usuarioPerfil;
	}

	public void setUsuarioPerfil( UsuarioPerfil usuarioPerfil )
	{
		this.usuarioPerfil = usuarioPerfil;
	}

	public Permissao getPermissao()
	{
		return permissao;
	}

	public void setPermissao( Permissao permissao )
	{
		this.permissao = permissao;
	}

	public UsuarioPerfilPermissao()
	{
		super();
		this.dt_criacao_upp = new Date();
	}

		
	
}
