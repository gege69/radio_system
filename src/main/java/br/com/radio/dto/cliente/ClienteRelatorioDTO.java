package br.com.radio.dto.cliente;

import java.math.BigDecimal;

import br.com.radio.model.Cliente;
import br.com.radio.model.Usuario;

public class ClienteRelatorioDTO {

	private Usuario usuario;   // tentar achar o usuário mais relevante
	
	private Integer totalAmbientes;
	
	private Cliente cliente;

	// Somatórias de títulos não pagos
	private BigDecimal valorTotalLiquido;
	
	private BigDecimal valorTotalDescontos;
	
	// Após aplicar descontos e taxas
	private BigDecimal valorTotalAPagar;

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

	public Integer getTotalAmbientes()
	{
		return totalAmbientes;
	}

	public void setTotalAmbientes( Integer totalAmbientes )
	{
		this.totalAmbientes = totalAmbientes;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public BigDecimal getValorTotalLiquido()
	{
		return valorTotalLiquido;
	}

	public void setValorTotalLiquido( BigDecimal valorTotalLiquido )
	{
		this.valorTotalLiquido = valorTotalLiquido;
	}

	public BigDecimal getValorTotalDescontos()
	{
		return valorTotalDescontos;
	}

	public void setValorTotalDescontos( BigDecimal valorTotalDescontos )
	{
		this.valorTotalDescontos = valorTotalDescontos;
	}

	public BigDecimal getValorTotalAPagar()
	{
		return valorTotalAPagar;
	}

	public void setValorTotalAPagar( BigDecimal valorTotalAPagar )
	{
		this.valorTotalAPagar = valorTotalAPagar;
	}

	public ClienteRelatorioDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteRelatorioDTO( Usuario usuario, Integer totalAmbientes, Cliente cliente, BigDecimal valorTotalLiquido, BigDecimal valorTotalDescontos, BigDecimal valorTotalAPagar )
	{
		super();
		this.usuario = usuario;
		this.totalAmbientes = totalAmbientes;
		this.cliente = cliente;
		this.valorTotalLiquido = valorTotalLiquido;
		this.valorTotalDescontos = valorTotalDescontos;
		this.valorTotalAPagar = valorTotalAPagar;
	}
	
	
	
	

}
