package br.com.radio.business;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.radio.dto.GeneroListDTO;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Genero;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.GeneroRepository;

@Component
public class AmbienteBusiness {

	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	@Autowired
	private AmbienteGeneroRepository ambienteGeneroRepo;


	/**
	 * Esse método salva o ambiente tomando cuidado para verificar os emails e endereços.
	 * 
	 * @param ambiente
	 */
	public Ambiente saveAmbiente( Ambiente ambiente )
	{
		// colocar aqui validações de endereço antes de salvar...
		
				
		return ambienteRepo.saveAndFlush( ambiente );
		
	}
	
	
	@Transactional
	public boolean saveGeneros( Long id_ambiente, GeneroListDTO generoList )
	{
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );
		
		boolean result = false;
		
		if ( ambiente != null )
		{
			List<Genero> generos = generoList.getLista();
			
			List<Long> idsGeneros = generos.stream().map( Genero::getId_genero ).collect( Collectors.toList() );

			ambienteGeneroRepo.deleteByAmbienteNotInIds( id_ambiente, idsGeneros ); 
			
			generos.stream().forEach( g -> {
				Genero genero = generoRepo.findOne( g.getId_genero() );
				
				if ( genero != null )
				{
					AmbienteGenero ambienteGenero = ambienteGeneroRepo.findByAmbienteAndGenero( ambiente, genero );
					
					if ( ambienteGenero == null )
					{
						ambienteGenero = new AmbienteGenero( ambiente, g );
						
						ambienteGeneroRepo.save( ambienteGenero );
					}
				}
			});
			
			result = true;
		}
		
		return result;
	}
	
}

