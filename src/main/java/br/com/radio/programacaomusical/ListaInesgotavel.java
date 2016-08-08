package br.com.radio.programacaomusical;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;


public interface ListaInesgotavel {

	boolean temRegistro();

	Midia getNextRandom( ThreadLocalRandom rnd );
	
	
	List<Midia> getMidiasConsumir();

	List<Midia> getMidiasUtilizadas();

	Categoria getCategoria();
}
