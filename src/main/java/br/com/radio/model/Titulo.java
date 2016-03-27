package br.com.radio.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Titulo
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="titulo")
public class Titulo implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_titulo", nullable = false )
	private Long idTitulo;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do Título é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataemissao", nullable = false )
	private Date dataEmissao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datavencimento", nullable = false )
	private Date dataVencimento;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datapagamento")
	private Date dataPagamento;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacancelamento")
	private Date dataCancelamento;
	
	@Column(name="numeronotafiscal")
	private String numeroNotaFiscal;
	
	@Column(name="valor_liquido", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorLiquido;
	
	@Column(name="valor_taxas", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorTaxas;
	
	@Column(name="valor_descontos", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorDescontos;

	@Column(name="valor_juros", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorJuros;
	
	@Column(name="valor_acresc", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorAcresc;
	
	@Column(name="valor_total", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorTotal;
	
	@Column(name="valor_pago", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorPago;
	
	@Column(name="linhadigitavel", columnDefinition = "TEXT")
	private String linhadigitavel;
	
	@Column(name="historico", columnDefinition = "TEXT")
	private String historico;

	public Long getIdTitulo()
	{
		return idTitulo;
	}

	public void setIdTitulo( Long idTitulo )
	{
		this.idTitulo = idTitulo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Date getDataEmissao()
	{
		return dataEmissao;
	}

	public void setDataEmissao( Date dataEmissao )
	{
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento()
	{
		return dataVencimento;
	}

	public void setDataVencimento( Date dataVencimento )
	{
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento()
	{
		return dataPagamento;
	}

	public void setDataPagamento( Date dataPagamento )
	{
		this.dataPagamento = dataPagamento;
	}

	public Date getDataCancelamento()
	{
		return dataCancelamento;
	}

	public void setDataCancelamento( Date dataCancelamento )
	{
		this.dataCancelamento = dataCancelamento;
	}

	public String getNumeroNotaFiscal()
	{
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal( String numeroNotaFiscal )
	{
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public BigDecimal getValorLiquido()
	{
		return valorLiquido;
	}

	public void setValorLiquido( BigDecimal valorLiquido )
	{
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getValorTaxas()
	{
		return valorTaxas;
	}

	public void setValorTaxas( BigDecimal valorTaxas )
	{
		this.valorTaxas = valorTaxas;
	}

	public BigDecimal getValorDescontos()
	{
		return valorDescontos;
	}

	public void setValorDescontos( BigDecimal valorDescontos )
	{
		this.valorDescontos = valorDescontos;
	}

	public BigDecimal getValorJuros()
	{
		return valorJuros;
	}

	public void setValorJuros( BigDecimal valorJuros )
	{
		this.valorJuros = valorJuros;
	}

	public BigDecimal getValorAcresc()
	{
		return valorAcresc;
	}

	public void setValorAcresc( BigDecimal valorAcresc )
	{
		this.valorAcresc = valorAcresc;
	}

	public BigDecimal getValorTotal()
	{
		return valorTotal;
	}

	public void setValorTotal( BigDecimal valorTotal )
	{
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorPago()
	{
		return valorPago;
	}

	public void setValorPago( BigDecimal valorPago )
	{
		this.valorPago = valorPago;
	}

	public String getLinhadigitavel()
	{
		return linhadigitavel;
	}

	public void setLinhadigitavel( String linhadigitavel )
	{
		this.linhadigitavel = linhadigitavel;
	}

	public String getHistorico()
	{
		return historico;
	}

	public void setHistorico( String historico )
	{
		this.historico = historico;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idTitulo == null ) ? 0 : idTitulo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof Titulo ) )
			return false;
		Titulo other = (Titulo) obj;
		if ( idTitulo == null )
		{
			if ( other.idTitulo != null )
				return false;
		}
		else if ( !idTitulo.equals( other.idTitulo ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String
				.format(
						"Titulo [idTitulo=%s, cliente=%s, usuario=%s, dataEmissao=%s, dataVencimento=%s, dataPagamento=%s, dataCancelamento=%s, numeroNotaFiscal=%s, valorLiquido=%s, valorTaxas=%s, valorDescontos=%s, valorJuros=%s, valorAcresc=%s, valorTotal=%s, valorPago=%s, linhadigitavel=%s, historico=%s]",
						idTitulo, cliente, usuario, dataEmissao, dataVencimento, dataPagamento, dataCancelamento, numeroNotaFiscal, valorLiquido, valorTaxas, valorDescontos, valorJuros, valorAcresc,
						valorTotal, valorPago, linhadigitavel, historico );
	}
	
	
	
	
}
