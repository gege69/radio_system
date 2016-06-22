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

import br.com.radio.json.JSONBigDecimalDeserializer;
import br.com.radio.json.JSONBigDecimalSerializer;
import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateTimeSerializer;
import br.com.radio.json.JSONOnlyDateSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne
	@JoinColumn(name="id_cliente", nullable = false)
	private Cliente cliente;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONOnlyDateSerializer.class)
	@NotNull( message = "A data de criação do Título é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataemissao", nullable = false )
	private Date dataEmissao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONOnlyDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datavencimento", nullable = false )
	private Date dataVencimento;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONOnlyDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datapagamento")
	private Date dataPagamento;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONOnlyDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacancelamento")
	private Date dataCancelamento;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_usuario_cancel")
	private Usuario usuarioCancelamento;

	// Mesmo que exista uma data de emissão vou deixar a data de criação do regsitro aqui também
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@NotNull( message = "A data de criação do Ambiente é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;	

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao")
	private Date dataAlteracao;
	
	@Column(name="numeronotafiscal")
	private String numeroNotaFiscal;
	

	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@NotNull( message = "O valor líquido é de preenchimento obrigatório" )	
	@Column(name="valor_liquido", columnDefinition = "numeric(12,2)", precision=2, scale=2, nullable = false)
	private BigDecimal valorLiquido;
	
	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@Column(name="valor_taxas", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorTaxas;
	
	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@Column(name="valor_descontos", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorDescontos;

	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@Column(name="valor_juros", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorJuros;
	
	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@Column(name="valor_acresc", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorAcresc;
	
	// LIQUIDO + TAXAS + JUROS + ACRESCIMOS - DESCONTOS
	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@Column(name="valor_total", columnDefinition = "numeric(12,2)", precision=2, scale=2, nullable = false)
	private BigDecimal valorTotal;
	
	@JsonDeserialize(using=JSONBigDecimalDeserializer.class)
//	@JsonSerialize(using=JSONBigDecimalSerializer.class)
	@Column(name="valor_pago", columnDefinition = "numeric(12,2)", precision=2, scale=2)
	private BigDecimal valorPago;
	
	@Column(name="linhadigitavel", columnDefinition = "TEXT")
	private String linhadigitavel;
	
	@Column(name="historico", columnDefinition = "TEXT")
	private String historico;
	
	/**
	 * Determina se esse titulo foi gerado pelo sistema de acordo com a data de vencimento do Cliente.
	 * Um cliente pode ter vários títulos... mas os fechamentos de cobrança no mês devem ser únicos (ativos).
	 * Podem existir vários títulos de fechamento mensais desde que estejam cancelados.
	 */
	@Column(name="is_fechamento", columnDefinition = "BOOLEAN default false")
	private boolean isFechamento;

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
		if ( valorLiquido == null )
			valorLiquido = BigDecimal.ZERO;
		return valorLiquido;
	}

	public void setValorLiquido( BigDecimal valorLiquido )
	{
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getValorTaxas()
	{
		if ( valorTaxas == null )
			valorTaxas = BigDecimal.ZERO;
		return valorTaxas;
	}

	public void setValorTaxas( BigDecimal valorTaxas )
	{
		this.valorTaxas = valorTaxas;
	}

	public BigDecimal getValorDescontos()
	{
		if ( valorDescontos == null )
			valorDescontos = BigDecimal.ZERO;
		return valorDescontos;
	}

	public void setValorDescontos( BigDecimal valorDescontos )
	{
		this.valorDescontos = valorDescontos;
	}

	public BigDecimal getValorJuros()
	{
		if ( valorJuros == null )
			valorJuros = BigDecimal.ZERO;
		return valorJuros;
	}

	public void setValorJuros( BigDecimal valorJuros )
	{
		this.valorJuros = valorJuros;
	}

	public BigDecimal getValorAcresc()
	{
		if ( valorAcresc == null )
			valorAcresc = BigDecimal.ZERO;
		return valorAcresc;
	}

	public void setValorAcresc( BigDecimal valorAcresc )
	{
		this.valorAcresc = valorAcresc;
	}

	public BigDecimal getValorTotal()
	{
		if ( valorTotal == null )
			valorTotal = BigDecimal.ZERO;
		return valorTotal;
	}

	public void setValorTotal( BigDecimal valorTotal )
	{
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorPago()
	{
		if ( valorPago == null )
			valorPago = BigDecimal.ZERO;
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
						"Titulo [idTitulo=%s, cliente=%s, usuario=%s, dataEmissao=%s, dataVencimento=%s, dataPagamento=%s, dataCancelamento=%s, dataCriacao=%s, dataAlteracao=%s, numeroNotaFiscal=%s, valorLiquido=%s, valorTaxas=%s, valorDescontos=%s, valorJuros=%s, valorAcresc=%s, valorTotal=%s, valorPago=%s, linhadigitavel=%s, historico=%s]",
						idTitulo, cliente, usuario, dataEmissao, dataVencimento, dataPagamento, dataCancelamento, dataCriacao, dataAlteracao, numeroNotaFiscal, valorLiquido, valorTaxas,
						valorDescontos, valorJuros, valorAcresc, valorTotal, valorPago, linhadigitavel, historico );
	}

	public Titulo()
	{
		super();
		this.dataCriacao = new Date();
		this.valorLiquido = BigDecimal.ZERO;
		this.valorAcresc = BigDecimal.ZERO;
		this.valorDescontos = BigDecimal.ZERO;
		this.valorJuros = BigDecimal.ZERO;
		this.valorPago = BigDecimal.ZERO;
		this.valorTaxas = BigDecimal.ZERO;
		this.valorTotal= BigDecimal.ZERO;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAlteracao()
	{
		return dataAlteracao;
	}

	public void setDataAlteracao( Date dataAlteracao )
	{
		this.dataAlteracao = dataAlteracao;
	}

	public boolean isFechamento()
	{
		return isFechamento;
	}

	public void setFechamento( boolean isFechamento )
	{
		this.isFechamento = isFechamento;
	}
	
	
	
}
