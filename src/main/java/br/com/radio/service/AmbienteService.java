package br.com.radio.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.dto.GeneroListDTO;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Empresa;
import br.com.radio.model.Genero;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.EmpresaRepository;
import br.com.radio.repository.GeneroRepository;

@Service
public class AmbienteService {

	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	@Autowired
	private AmbienteGeneroRepository ambienteGeneroRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;


	/**
	 * Esse método salva o ambiente tomando cuidado para verificar os emails e endereços.
	 * 
	 * @param ambiente
	 */
	public Ambiente saveAmbiente( Ambiente ambiente )
	{
		// colocar aqui validações de endereço antes de salvar...
		if ( ambiente.getEmpresa() == null )
		{
			Empresa empresa = empresaRepo.findByCodigo( "Eterion" );
			ambiente.setEmpresa( empresa );
			
//			throw new RuntimeException("Empresa não determinada");
		}
				
		return ambienteRepo.saveAndFlush( ambiente );
		
	}
	
	
	@Transactional
	public boolean saveGeneros( Long idAmbiente, GeneroListDTO generoList )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		boolean result = false;
		
		if ( ambiente != null )
		{
			List<Genero> generos = generoList.getLista();
			
			List<Long> idsGeneros = generos.stream().map( Genero::getIdGenero ).collect( Collectors.toList() );

			ambienteGeneroRepo.deleteByAmbienteNotInIds( idAmbiente, idsGeneros ); 
			
			generos.stream().forEach( g -> {
				Genero genero = generoRepo.findOne( g.getIdGenero() );
				
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
	
	
	
	public Boolean saveExpediente( Long idAmbiente, Ambiente dto ) 
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		ambiente.setHoraIniExpediente( dto.getHoraIniExpediente() );
		ambiente.setHoraFimExpediente( dto.getHoraFimExpediente() );
		ambiente.setMinutoIniExpediente( dto.getMinutoIniExpediente() );
		ambiente.setMinutoFimExpediente( dto.getMinutoFimExpediente() );
			
		ambienteRepo.save( ambiente );
		
		return true;
	}
	
	
	
	public Boolean isExpedienteOn( Long idAmbiente )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		return this.isExpedienteOn( ambiente );
	}
	
	
	public Boolean isExpedienteOn( Ambiente ambiente )
	{
		Boolean result = false;
		
		if ( ambiente != null )
		{
			LocalTime agora = LocalTime.now(); 
			
			LocalTime inicio = LocalTime.of( ambiente.getHoraIniExpediente(), ambiente.getMinutoIniExpediente() );
			
			LocalTime fim = LocalTime.of( ambiente.getHoraFimExpediente(), ambiente.getMinutoFimExpediente() );
			
			result = agora.isAfter( inicio ) && agora.isBefore( fim );
		}
		
		return result;
	}
	
	
}

