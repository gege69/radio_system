package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.radio.enumeration.OperacaoFinanceiraTaxa;



/**
 * Tipo de Taxa que irá compor as condições comerciais do cliente
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="tipo_taxa")
public class TipoTaxa implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_tipotaxa", nullable = false )
	private Long idTipotaxa;
	
	@NotNull(message="A Descrição é de preenchimento obrigatório")
	@Column(name="descricao", columnDefinition="TEXT", nullable = false)
	private String descricao;
	
	@Column(name="por_ambiente", nullable = false )
	private Boolean porambiente;
	
	@NotNull(message="A Operação Financeira é de preenchimento obrigatório")
	// Determina se é cobrança ou desconto
	@Enumerated(EnumType.STRING)
	@Column( name = "operacao" , columnDefinition= " TEXT default 'COBRANCA' ", nullable = false)
	private OperacaoFinanceiraTaxa operacao;

	
	public Long getIdTipotaxa()
	{
		return idTipotaxa;
	}

	public void setIdTipotaxa( Long idTipotaxa )
	{
		this.idTipotaxa = idTipotaxa;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public Boolean getPorambiente()
	{
		return porambiente;
	}

	public void setPorambiente( Boolean porambiente )
	{
		this.porambiente = porambiente;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idTipotaxa == null ) ? 0 : idTipotaxa.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof TipoTaxa ) )
			return false;
		TipoTaxa other = (TipoTaxa) obj;
		if ( idTipotaxa == null )
		{
			if ( other.idTipotaxa != null )
				return false;
		}
		else if ( !idTipotaxa.equals( other.idTipotaxa ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "TipoTaxa [idTipotaxa=%s, descricao=%s, por_ambiente=%s]", idTipotaxa, descricao, porambiente );
	}

	public OperacaoFinanceiraTaxa getOperacao()
	{
		return operacao;
	}

	public void setOperacao( OperacaoFinanceiraTaxa operacao )
	{
		this.operacao = operacao;
	}

	public TipoTaxa()
	{
		super();
		this.porambiente = false;
	}
	

	
}
