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

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Acesso Usuario = ACU
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="acesso_usuario")
public class AcessoUsuario implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_acesso_acu", nullable = false )
	private Long id_acesso_acu;

	@OneToOne
	@JoinColumn( name="id_usuario_usu" )
	private Usuario usuario;
	
	@Column( name = "ds_enderecoip_acu", nullable = false, length = 50 )
	private String ds_enderecoip_acu;
	
	// Todos os dados da conexão 
	@Column( name = "ds_dados_acu", columnDefinition = "TEXT" )
	private String ds_dados_acu;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_acu", nullable = false )
	private Date dt_criacao_acu;
	

	@Override
	public Long getId()
	{
		return id_acesso_acu;
	}

	@Override
	public void setId( Long id )
	{
		this.id_acesso_acu = id;
	}

	public Long getId_acesso_acu()
	{
		return id_acesso_acu;
	}

	public void setId_acesso_acu( Long id_acesso_acu )
	{
		this.id_acesso_acu = id_acesso_acu;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public String getDs_enderecoip_acu()
	{
		return ds_enderecoip_acu;
	}

	public void setDs_enderecoip_acu( String ds_enderecoip_acu )
	{
		this.ds_enderecoip_acu = ds_enderecoip_acu;
	}

	public String getDs_dados_acu()
	{
		return ds_dados_acu;
	}

	public void setDs_dados_acu( String ds_dados_acu )
	{
		this.ds_dados_acu = ds_dados_acu;
	}

	public Date getDt_criacao_acu()
	{
		return dt_criacao_acu;
	}

	public void setDt_criacao_acu( Date dt_criacao_acu )
	{
		this.dt_criacao_acu = dt_criacao_acu;
	}

	public AcessoUsuario()
	{
		super();
		this.dt_criacao_acu = new Date();
	}

	
	
}
