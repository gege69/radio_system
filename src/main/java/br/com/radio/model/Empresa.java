package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_empresa", nullable = false )
	private Long id_empresa;
	
	@NotNull( message = "O CNPJ da empresa é de preenchimento obrigatório" )
	@Column( name = "cnpj", nullable = false )
	private String cnpj;

	@Column( name = "codigo" )
	private String codigo;
	
	@NotNull( message = "A razão social da empresa é de preenchimento obrigatório" )
	@Column( name = "razaosocial", nullable = false )
	private String razaosocial;
	
	@Column( name = "nomefantasia" )
	private String nomefantasia;
	
	@Column( name = "dominio" )
	private String dominio;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da empresa é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao" )
	private Date dataAlteracao;
	
	@NotNull
	@Column( name = "ativo" )
	private Boolean ativo;
		
	public Empresa()
	{
		super();
		this.dataCriacao = new Date();
	}

	public Long getId_empresa()
	{
		return id_empresa;
	}

	public void setId_empresa( Long id_empresa )
	{
		this.id_empresa = id_empresa;
	}

	public String getCnpj()
	{
		return cnpj;
	}

	public void setCnpj( String cnpj )
	{
		this.cnpj = cnpj;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	public String getRazaosocial()
	{
		return razaosocial;
	}

	public void setRazaosocial( String razaosocial )
	{
		this.razaosocial = razaosocial;
	}

	public String getNomefantasia()
	{
		return nomefantasia;
	}

	public void setNomefantasia( String nomefantasia )
	{
		this.nomefantasia = nomefantasia;
	}

	public String getDominio()
	{
		return dominio;
	}

	public void setDominio( String dominio )
	{
		this.dominio = dominio;
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

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}
	
}