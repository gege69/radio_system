package br.com.radio.model.fixture;

import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;

public class FixtureAmbiente {

	public static Ambiente criaAmbiente(Cliente cliente, String nome){
		
		Ambiente amb = new Ambiente();
		
		amb.setCliente( cliente );
		amb.setNome( nome );
		amb.setStatus( StatusAmbiente.ATIVO );
		
		return amb;
	}

}
