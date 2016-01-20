package br.com.radio.boot;

import br.com.radio.enumeration.PosicaoVinheta;

public class StaticVoidMain {
	
	public static void main(String[] args)
	{
		
		PosicaoVinheta pos = PosicaoVinheta.valueOf( "ANTES_CADA_MUSICA" );
		
		System.out.println( pos );
		
	}

}
