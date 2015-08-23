package br.com.radio.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.radio.model.Ambiente;
import br.com.radio.repository.AmbienteRepository;

@Component
public class AmbienteBusiness {

	@Autowired
	private AmbienteRepository ambienteRepository;
	

	/**
	 * Esse método salva o ambiente tomando cuidado para verificar os emails e endereços.
	 * 
	 * @param ambiente
	 */
	public void saveAmbiente( Ambiente ambiente )
	{
		// colocar aqui validações de endereço antes de salvar...
				
		ambienteRepository.saveAndFlush( ambiente );
		
	}
	
}

