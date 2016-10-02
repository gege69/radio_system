package br.com.radio.dto.midia;

import java.util.Date;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;

public class TransmissaoFilter {
	
	private Ambiente ambiente;
	private Categoria categoria;
	private Date dataInicio;
	private Date dataFim;
	private boolean futuro; // caso esteja TRUE tem que trazer Transmissões que ainda não tocaram.... em caso de FALSE só as transmissões TOCANDO e FIM

	
	public static TransmissaoFilter create(){
		return new TransmissaoFilter();
	}

	
	public Ambiente getAmbiente()
	{
		return ambiente;
	}
	
	public Boolean isFuturo()
	{
		return futuro;
	}
	public TransmissaoFilter setFuturo( Boolean futuro )
	{
		this.futuro = futuro;
		return this;
	}
	public TransmissaoFilter setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
		return this;
	}
	public Categoria getCategoria()
	{
		return categoria;
	}
	public TransmissaoFilter setCategoria( Categoria categoria )
	{
		this.categoria = categoria;
		return this;
	}
	public Date getDataInicio()
	{
		return dataInicio;
	}
	public TransmissaoFilter setDataInicio( Date dataInicio )
	{
		this.dataInicio = dataInicio;
		return this;
	}
	public Date getDataFim()
	{
		return dataFim;
	}
	public TransmissaoFilter setDataFim( Date dataFim )
	{
		this.dataFim = dataFim;
		return this;
	}
	
	public TransmissaoFilter setCodigoCategoria( String codigo ){
		
		if ( this.categoria == null ){
			this.categoria = new Categoria();
			this.categoria.setCodigo( codigo );
		}
		else{
			this.categoria.setCodigo( codigo );
			this.categoria.setIdCategoria( null );
		}

		return this;
	}

	public TransmissaoFilter setIdCategoria( Long idCategoria ){
		
		if ( this.categoria == null )
			this.categoria = new Categoria( idCategoria );
		else{
			this.categoria.setIdCategoria( idCategoria );
			this.categoria.setCodigo( null );
		}

		return this;
	}

}
