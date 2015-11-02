package br.com.radio.service.programacaomusical;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;

/**
 * 
 * Caso existam mais de um comercial ativo não é possível utilizar aleatóriedade pura pois pode haver repetição.
 * 
 * Então nesse caso será feito sorteio random com eliminação. Caso a lista se esgote a lista de Utilizadas vai "recarregar" a lista de consumir para poder começar novamente.
 * 
 * @author pazin
 */
public class BlocosManipulacaoDTO {
	
	private List<Midia> midiasConsumir = new ArrayList<Midia>();
	
	private List<Midia> midiasUtilizadas = new ArrayList<Midia>();
	
	private Categoria categoria;
	
	public Midia getNextRandom( ThreadLocalRandom rnd )
	{
		if ( midiasConsumir.size() == 0 && midiasUtilizadas.size() == 0 )
			return null;
		
		if ( midiasConsumir.size() == 0 )
		{
			// resetando
			midiasConsumir.addAll( midiasUtilizadas );
			midiasUtilizadas.clear();
		}
			
		int indexSelect = rnd.nextInt( midiasConsumir.size() );
		
		Midia m = midiasConsumir.get( indexSelect );
		
		midiasUtilizadas.add( m );
		midiasConsumir.remove( indexSelect );

		
		// Mesmo que essa mídia seja várias coisas ao mesmo tempo ( Comercial e Programete ) preciso saber qual foi a escolha dela
		m.setCategoriaSelecionada( this.categoria );
		
		return m;
	}
	
	public BlocosManipulacaoDTO( List<Midia> midiasConsumir, Categoria categoria )
	{
		super();
		this.midiasConsumir = midiasConsumir;
		this.categoria = categoria;
	}

	@Override
	public String toString()
	{
		return String.format( "BlocosManipulacaoDTO [midiasConsumir=%s, midiasUtilizadas=%s]", midiasConsumir, midiasUtilizadas );
	}


	public BlocosManipulacaoDTO( List<Midia> midiasConsumir )
	{
		super();
		this.midiasConsumir = midiasConsumir;
	}

	public BlocosManipulacaoDTO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Midia> getMidiasConsumir()
	{
		return midiasConsumir;
	}

	public void setMidiasConsumir( List<Midia> midiasConsumir )
	{
		this.midiasConsumir = midiasConsumir;
	}

	public List<Midia> getMidiasUtilizadas()
	{
		return midiasUtilizadas;
	}

	public void setMidiasUtilizadas( List<Midia> midiasUtilizadas )
	{
		this.midiasUtilizadas = midiasUtilizadas;
	}

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria( Categoria categoria )
	{
		this.categoria = categoria;
	}
	
}
