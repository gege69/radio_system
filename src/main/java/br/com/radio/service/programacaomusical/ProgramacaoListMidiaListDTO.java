package br.com.radio.service.programacaomusical;

import java.util.List;

import br.com.radio.model.Midia;
import br.com.radio.model.Programacao;

public class ProgramacaoListMidiaListDTO {
	
	private List<Programacao> programacoes;
	
	private List<Midia> midias;
	
	private List<Integer> midiaIds;

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

	public ProgramacaoListMidiaListDTO( List<Programacao> programacoes, List<Midia> midias, List<Integer> midiaIds )
	{
		super();
		this.programacoes = programacoes;
		this.midias = midias;
		this.midiaIds = midiaIds;
	}

	public List<Integer> getMidiaIds()
	{
		return midiaIds;
	}

	public void setMidiaIds( List<Integer> midiaIds )
	{
		this.midiaIds = midiaIds;
	}
	
	
	

}
