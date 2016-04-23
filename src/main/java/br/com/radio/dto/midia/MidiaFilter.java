package br.com.radio.dto.midia;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;

public class MidiaFilter {

	private Ambiente ambiente;
	private Categoria categoria;
	private List<Long> categoriaIds;
	private String search;
	private boolean verificaValidade;
	
	public static MidiaFilter create(){
		return new MidiaFilter();
	}
	
	
	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public MidiaFilter setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
		return this;
	}

	public Categoria getCategoria()
	{
		return categoria;
	}

	public MidiaFilter setCategoria( Categoria categoria )
	{
		this.categoria = categoria;
		return this;
	}

	public String getSearch()
	{
		return search;
	}

	public String getPreparedSearch(){
		return "%"+search+"%";
	}

	public boolean hasSearch(){
		return StringUtils.isNotBlank( search );
	}

	public MidiaFilter setSearch( String search )
	{
		this.search = search;
		return this;
	}

	public MidiaFilter setIdAmbiente( Long idAmbiente ){

		if ( this.ambiente == null )
			this.ambiente = new Ambiente( idAmbiente );
		else
			this.ambiente.setIdAmbiente( idAmbiente );

		return this;
	}
	
	public MidiaFilter setIdCategoria( Long idCategoria ){
		
		if ( this.categoria == null )
			this.categoria = new Categoria( idCategoria );
		else{
			this.categoria.setIdCategoria( idCategoria );
			this.categoria.setCodigo( null );
		}

		return this;
	}

	public MidiaFilter setCategoriaIds( Long... ids ){
		this.categoriaIds = Arrays.asList( ids );
		return this;
	}

	public List<Long> getCategoriaIds()
	{
		return categoriaIds;
	}
	
	public MidiaFilter setCodigoCategoria( String codigo ){
		
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


	public boolean isVerificaValidade()
	{
		return verificaValidade;
	}

	public MidiaFilter setVerificaValidade( boolean verificaValidade )
	{
		this.verificaValidade = verificaValidade;
		return this;
	}

	
}
