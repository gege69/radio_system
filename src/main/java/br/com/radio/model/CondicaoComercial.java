package br.com.radio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.radio.enumeration.DefinicaoTaxa;
import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Condição Comercial para determinar cobrança
 * 
 * Pode ser genérica ( caso nenhum cliente esteja especificado )
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="condicao_comercial")
public class CondicaoComercial implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_condcom", nullable = false )
	private Long idCondcom;
	
	// Pode ser null
	@JsonIgnore
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn( name="id_cliente")
	private Cliente cliente;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn( name="id_tipotaxa", nullable = false)
	private TipoTaxa tipoTaxa;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao" )
	private Date dataAlteracao;
	
	// Porcentagem ou Valor 
	@Enumerated(EnumType.STRING)
	@Column( name = "operacaotaxa" , columnDefinition= " TEXT default 'GERENCIADOR' ")
	private DefinicaoTaxa operacaoTaxa;	
	
	@Column(name="valor", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valor;
	
	@Transient
	private Map<String, String> ccView = new HashMap<String,String>();

	public Long getIdCondcom()
	{
		return idCondcom;
	}

	public void setIdCondcom( Long idCondcom )
	{
		this.idCondcom = idCondcom;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public TipoTaxa getTipoTaxa()
	{
		return tipoTaxa;
	}

	public void setTipoTaxa( TipoTaxa tipoTaxa )
	{
		this.tipoTaxa = tipoTaxa;
	}

	public Date getDataAlteracao()
	{
		return dataAlteracao;
	}

	public void setDataAlteracao( Date dataAlteracao )
	{
		this.dataAlteracao = dataAlteracao;
	}

	public DefinicaoTaxa getOperacaoTaxa()
	{
		return operacaoTaxa;
	}

	public void setOperacaoTaxa( DefinicaoTaxa operacaoTaxa )
	{
		this.operacaoTaxa = operacaoTaxa;
	}

	public BigDecimal getValor()
	{
		return valor;
	}

	public void setValor( BigDecimal valor )
	{
		this.valor = valor;
	}

	@Override
	public String toString()
	{
		return String.format( "CondicaoComercial [idCondcom=%s, cliente=%s, tipoTaxa=%s, dataAlteracao=%s, operacaoTaxa=%s, valor=%s]", idCondcom, cliente, tipoTaxa, dataAlteracao, operacaoTaxa,
				valor );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idCondcom == null ) ? 0 : idCondcom.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof CondicaoComercial ) )
			return false;
		CondicaoComercial other = (CondicaoComercial) obj;
		if ( idCondcom == null )
		{
			if ( other.idCondcom != null )
				return false;
		}
		else if ( !idCondcom.equals( other.idCondcom ) )
			return false;
		return true;
	}

	@JsonAnyGetter
	public Map<String, String> getCcView()
	{
		return ccView;
	}

	@JsonAnySetter
	public void setCcView( Map<String, String> ccView )
	{
		this.ccView = ccView;
	}
	
}
