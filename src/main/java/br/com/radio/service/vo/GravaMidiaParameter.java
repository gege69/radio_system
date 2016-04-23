package br.com.radio.service.vo;

import java.util.Date;

import br.com.radio.model.Cliente;

public class GravaMidiaParameter {

	private String originalName;
	private Cliente cliente;
	private Long[] categorias;
	private String hash;
	private String contentType;
	private String descricao;
	private Integer fileSize;

	private Date dataInicioValidade;
	private Date dataFimValidade;

	public GravaMidiaParameter( String originalName, Cliente cliente, Long[] categorias, String hash, String contentType, String descricao, Integer fileSize )
	{
		this.originalName = originalName;
		this.cliente = cliente;
		this.categorias = categorias;
		this.hash = hash;
		this.contentType = contentType;
		this.descricao = descricao;
		this.fileSize = fileSize;
	}

	public GravaMidiaParameter( String originalName, Cliente cliente, Long[] categorias, String hash, String contentType, String descricao, Integer fileSize, Date dataInicioValidade, Date dataFimValidade )
	{
		this.originalName = originalName;
		this.cliente = cliente;
		this.categorias = categorias;
		this.hash = hash;
		this.contentType = contentType;
		this.descricao = descricao;
		this.fileSize = fileSize;
		
		this.dataInicioValidade = dataInicioValidade;
		this.dataFimValidade = dataFimValidade;
	}
	
	public void validar(){
		if ( dataInicioValidade != null && dataFimValidade != null && dataInicioValidade.compareTo( dataFimValidade ) >= 0 )
			throw new RuntimeException( "Data de Início da Validade não pode ser igual ou superior ao Fim da Validade." );
	}

	public String getOriginalName()
	{
		return originalName;
	}

	public void setOriginalName( String originalName )
	{
		this.originalName = originalName;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public Long[] getCategorias()
	{
		return categorias;
	}

	public void setCategorias( Long[] categorias )
	{
		this.categorias = categorias;
	}

	public String getHash()
	{
		return hash;
	}

	public void setHash( String hash )
	{
		this.hash = hash;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType( String contentType )
	{
		this.contentType = contentType;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public Integer getFileSize()
	{
		return fileSize;
	}

	public void setFileSize( Integer fileSize )
	{
		this.fileSize = fileSize;
	}

	public Date getDataInicioValidade()
	{
		return dataInicioValidade;
	}

	public void setDataInicioValidade( Date dataInicioValidade )
	{
		this.dataInicioValidade = dataInicioValidade;
	}

	public Date getDataFimValidade()
	{
		return dataFimValidade;
	}

	public void setDataFimValidade( Date dataFimValidade )
	{
		this.dataFimValidade = dataFimValidade;
	}


}