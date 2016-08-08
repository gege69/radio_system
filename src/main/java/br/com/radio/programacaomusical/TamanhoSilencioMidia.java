package br.com.radio.programacaomusical;

import br.com.radio.model.Midia;

public enum TamanhoSilencioMidia {

	NENHUM("Nenhum", null),
	_30_SEGUNDOS("30 segundos", Midia.createSilencio(30)),
	_60_SEGUNDOS("60 segundos", Midia.createSilencio(60)),
	_90_SEGUNDOS("90 segundos", Midia.createSilencio(90)),
	_120_SEGUNDOS("120 segundos", Midia.createSilencio(120)),
	_150_SEGUNDOS("150 segundos", Midia.createSilencio(150)),
	_180_SEGUNDOS("180 segundos", Midia.createSilencio(180));
	
	private String descricao;

	private Midia midia;
	
	private TamanhoSilencioMidia(String descricao, Midia midia){
		this.descricao = descricao;
		this.midia = midia;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public Midia getMidia()
	{
		return midia;
	}

	public void setMidia( Midia midia )
	{
		this.midia = midia;
	}
	
}

