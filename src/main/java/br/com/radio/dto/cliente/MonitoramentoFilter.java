package br.com.radio.dto.cliente;

import java.time.LocalDate;

import br.com.radio.enumeration.TipoMonitoramento;
import br.com.radio.model.Cliente;

public class MonitoramentoFilter {
	
	private Cliente cliente;
	private TipoMonitoramento tipo;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	public static MonitoramentoFilter create(){
		return new MonitoramentoFilter();
	}

	public Cliente getCliente()
	{
		return cliente;
	}
	public MonitoramentoFilter setCliente( Cliente cliente )
	{
		this.cliente = cliente;
		return this;
	}
	public TipoMonitoramento getTipo()
	{
		return tipo;
	}
	public MonitoramentoFilter setTipo( TipoMonitoramento tipo )
	{
		this.tipo = tipo;
		return this;
	}
	public LocalDate getDataInicio()
	{
		return dataInicio;
	}
	public MonitoramentoFilter setDataInicio( LocalDate dataInicio )
	{
		this.dataInicio = dataInicio;
		return this;
	}
	public LocalDate getDataFim()
	{
		return dataFim;
	}
	public MonitoramentoFilter setDataFim( LocalDate dataFim )
	{
		this.dataFim = dataFim;
		return this;
	}

}
