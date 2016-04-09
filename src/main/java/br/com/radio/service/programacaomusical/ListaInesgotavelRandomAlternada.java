package br.com.radio.service.programacaomusical;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;

import com.google.common.collect.Iterators;


/**
 * 
 * Essa classe vai conter mais de uma ListaInesgotávelRandom e vai consumir alternadamente delas.
 * 
 * Utilizada para poder alterar entre Blocos de Opcionais
 * 
 * @author pazin
 */
public class ListaInesgotavelRandomAlternada implements ListaInesgotavel {
	
	private List<ListaInesgotavel> listasInesgotaveis;
	
	private Iterator<ListaInesgotavel> iteratorListas;
	
	private ListaInesgotavel listaInesgotavelAtual;

	
	public ListaInesgotavelRandomAlternada( List<ListaInesgotavel> listasInesgotaveis )
	{
		if ( listasInesgotaveis != null ){
			this.listasInesgotaveis = listasInesgotaveis;
			this.iteratorListas = Iterators.cycle( listasInesgotaveis );
			
			alternarLista();
		}
	}
	
	private void alternarLista(){
		if ( iteratorListas != null && iteratorListas.hasNext() )
			this.listaInesgotavelAtual = iteratorListas.next();
	}

	public ListaInesgotavel getListaInesgotavelAtual()
	{
		if ( this.listaInesgotavelAtual == null )
			alternarLista();

		return listaInesgotavelAtual;
	}

	public boolean temRegistro()
	{
		return listaInesgotavelAtual.temRegistro();
	}

	@Override
	public Midia getNextRandom( ThreadLocalRandom rnd )
	{
		if ( getListaInesgotavelAtual() == null )
			return null;

		Midia result = getListaInesgotavelAtual().getNextRandom( rnd );
		
		// Primeiro pega o registro depois alterna.
		alternarLista();
		
		return result;
	}

	@Override
	public List<Midia> getMidiasConsumir()
	{
		if ( getListaInesgotavelAtual() == null )
			return null;

		return getListaInesgotavelAtual().getMidiasConsumir();
	}

	@Override
	public List<Midia> getMidiasUtilizadas()
	{
		if ( getListaInesgotavelAtual() == null )
			return null;

		return getListaInesgotavelAtual().getMidiasUtilizadas();
	}

	@Override
	public Categoria getCategoria()
	{
		if ( getListaInesgotavelAtual() == null )
			return null;

		return getListaInesgotavelAtual().getCategoria();
	}
	

}
