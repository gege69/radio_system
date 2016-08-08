package br.com.radio.programacaomusical;

import java.util.List;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;
import br.com.radio.model.Programacao;

public class ProgramacaoListMidiaListDTO {
	
	private List<Programacao> programacoes;
	
	private List<Midia> midias;
	
	private Categoria musicaCategoria;

	public List<Programacao> getProgramacoes()
	{
		return programacoes;
	}

	public void setProgramacoes( List<Programacao> programacoes )
	{
		this.programacoes = programacoes;
	}

	public List<Midia> getMidias()
	{
		return midias;
	}

	public void setMidias( List<Midia> midias )
	{
		this.midias = midias;
	}

	public ProgramacaoListMidiaListDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public ProgramacaoListMidiaListDTO( List<Programacao> programacoes, List<Midia> midias )
	{
		super();
		this.programacoes = programacoes;
		this.midias = midias;
	}

	public ProgramacaoListMidiaListDTO( List<Programacao> programacoes, List<Midia> midias, Categoria musicaCategoria )
	{
		super();
		this.programacoes = programacoes;
		this.midias = midias;
		this.musicaCategoria = musicaCategoria;
	}

	public Categoria getMusicaCategoria()
	{
		return musicaCategoria;
	}

	public void setMusicaCategoria( Categoria musicaCategoria )
	{
		this.musicaCategoria = musicaCategoria;
	}
	
	
	

}
