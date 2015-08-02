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

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Conexao = CNX
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="conexao")
public class Conexao implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_conexao_cnx", nullable = false )
	private Long id_conexao_cnx;
	
	@ManyToOne
	@JoinColumn(name="id_ambiente_amb")
	private Ambiente ambiente;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_conexao_cnx", nullable = false )
	private Date dt_conexao_cnx;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_desconexao_cnx", nullable = true )
	private Date dt_desconexao_cnx;
	
	@Column( name = "ds_enderecoip_acu", nullable = false, length = 50 )
	private String ds_enderecoip_acu;
	
	// Outros dados da conexão ... 
	@Column( name = "ds_dados_acu", columnDefinition = "TEXT" )
	private String ds_dados_acu;
	
	
	@Override
	public Long getId()
	{
		return id_conexao_cnx;
	}

	@Override
	public void setId( Long id )
	{
		this.id_conexao_cnx = id;
	}

	public Long getId_conexao_cnx()
	{
		return id_conexao_cnx;
	}

	public void setId_conexao_cnx( Long id_conexao_cnx )
	{
		this.id_conexao_cnx = id_conexao_cnx;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public Date getDt_conexao_cnx()
	{
		return dt_conexao_cnx;
	}

	public void setDt_conexao_cnx( Date dt_conexao_cnx )
	{
		this.dt_conexao_cnx = dt_conexao_cnx;
	}

	public Date getDt_desconexao_cnx()
	{
		return dt_desconexao_cnx;
	}

	public void setDt_desconexao_cnx( Date dt_desconexao_cnx )
	{
		this.dt_desconexao_cnx = dt_desconexao_cnx;
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

	
	

}
