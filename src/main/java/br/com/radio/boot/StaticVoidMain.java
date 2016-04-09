package br.com.radio.boot;

import java.util.concurrent.ThreadLocalRandom;

import br.com.radio.service.programacaomusical.ListaInesgotavelRandomAlternada;

public class StaticVoidMain {
	
	public static void main(String[] args)
	{
		ListaInesgotavelRandomAlternada lira = new ListaInesgotavelRandomAlternada( null );
		
		ThreadLocalRandom rnd = ThreadLocalRandom.current();

		lira.getNextRandom( rnd );
	}

}
