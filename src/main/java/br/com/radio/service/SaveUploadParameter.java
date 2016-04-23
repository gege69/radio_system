package br.com.radio.service;

import org.springframework.web.multipart.MultipartFile;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Cliente;

public class SaveUploadParameter {

	private MultipartFile multiPartFile;
	private Ambiente ambiente;
	private Categoria categoria;
	private Long[] categorias;
	private Cliente cliente;
	private String descricao;

	public SaveUploadParameter( MultipartFile multiPartFile, Categoria categoria, Cliente cliente, String descricao )
	{
		this.multiPartFile = multiPartFile;
		this.categoria = categoria;
		this.cliente = cliente;
		this.descricao = descricao;
	}

	public SaveUploadParameter( MultipartFile multiPartFile, Ambiente ambiente, Long[] categorias, Cliente cliente, String descricao )
	{
		super();
		this.ambiente = ambiente;
		this.multiPartFile = multiPartFile;
		this.categorias = categorias;
		this.cliente = cliente;
		this.descricao = descricao;
	}
	
	
	public Long[] getCategorias()
	{
		return categorias;
	}

	public void setCategorias( Long[] categorias )
	{
		this.categorias = categorias;
	}

	public MultipartFile getMultiPartFile()
	{
		return multiPartFile;
	}

	public void setMultiPartFile( MultipartFile multiPartFile )
	{
		this.multiPartFile = multiPartFile;
	}

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria( Categoria categoria )
	{
		this.categoria = categoria;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}
}