package br.com.radio.dto.cliente;

public class ClienteResumoFinanceiroDTO {
	
	private Integer totalAmbientes;
	
	private Integer ambientesAtivos;
	
	private Integer ambientesInativos;
	
	private Integer ambientesBloqueados;
	

	public Integer getTotalAmbientes()
	{
		return totalAmbientes;
	}

	public void setTotalAmbientes( Integer totalAmbientes )
	{
		this.totalAmbientes = totalAmbientes;
	}

	public Integer getAmbientesAtivos()
	{
		return ambientesAtivos;
	}

	public void setAmbientesAtivos( Integer ambientesAtivos )
	{
		this.ambientesAtivos = ambientesAtivos;
	}

	public Integer getAmbientesInativos()
	{
		return ambientesInativos;
	}

	public void setAmbientesInativos( Integer ambientesInativos )
	{
		this.ambientesInativos = ambientesInativos;
	}

	public Integer getAmbientesBloqueados()
	{
		return ambientesBloqueados;
	}

	public void setAmbientesBloqueados( Integer ambientesBloqueados )
	{
		this.ambientesBloqueados = ambientesBloqueados;
	}
	
	
	
	
	

}
