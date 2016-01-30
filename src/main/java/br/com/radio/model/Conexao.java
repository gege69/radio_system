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

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Conexao
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="conexao")
public class Conexao implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_conexao", nullable = false )
	private Long idConexao;
	
	@ManyToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataconexao", nullable = false )
	private Date dataConexao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datadesconexao", nullable = true )
	private Date dataDesconexao;
	
	@Column( name = "enderecoip", nullable = false, length = 50 )
	private String enderecoIp;
	
	// Outros dados da conexão ... 
	@Column( name = "dados", columnDefinition = "TEXT" )
	private String dados;

	public Long getIdConexao()
	{
		return idConexao;
	}

	public void setIdConexao( Long idConexao )
	{
		this.idConexao = idConexao;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public Date getDataConexao()
	{
		return dataConexao;
	}

	public void setDataConexao( Date dataConexao )
	{
		this.dataConexao = dataConexao;
	}

	public Date getDataDesconexao()
	{
		return dataDesconexao;
	}

	public void setDataDesconexao( Date dataDesconexao )
	{
		this.dataDesconexao = dataDesconexao;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idConexao == null ) ? 0 : idConexao.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Conexao other = (Conexao) obj;
		if ( idConexao == null )
		{
			if ( other.idConexao != null )
				return false;
		}
		else if ( !idConexao.equals( other.idConexao ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Conexao [idConexao=%s, ambiente=%s, dataConexao=%s]", idConexao, ambiente, dataConexao );
	}
	

}
