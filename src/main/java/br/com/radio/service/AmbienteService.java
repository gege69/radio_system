package br.com.radio.service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.dto.GeneroListDTO;
import br.com.radio.enumeration.PosicaoVinheta;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Bloco;
import br.com.radio.model.Empresa;
import br.com.radio.model.Genero;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.BlocoRepository;
import br.com.radio.repository.EmpresaRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.util.UtilsStr;

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
	
	@Autowired
	private ProgramacaoMusicalService progMusicalService;
	
	@Autowired
	private MidiaService midiaService;
	
	@Autowired
	private BlocoRepository blocoRepository;


	/**
	 * Esse método salva o ambiente tomando cuidado para verificar os emails e endereços.
	 * 
	 * @param ambiente
	 */
	@Transactional
	public Ambiente saveAmbiente( Ambiente ambiente )
	{
		// colocar aqui validações de endereço antes de salvar...
		if ( ambiente.getEmpresa() == null )
		{
			Empresa empresa = empresaRepo.findByCodigo( "Eterion" );
			ambiente.setEmpresa( empresa );
			
//			throw new RuntimeException("Empresa não determinada");
		}

		validaLogin( ambiente );
		
		preencheValoresDefault( ambiente );
		
		ambiente = ambienteRepo.saveAndFlush( ambiente );
		
		midiaService.associaTodasMidiasParaAmbiente( ambiente );
		
		criaBlocoDefault( ambiente );
		
		
		return ambiente; 
	}


	private void validaLogin( Ambiente ambiente )
	{
		if ( StringUtils.isNotBlank( ambiente.getLogin() ) )
		{
			String login = ambiente.getLogin();
			
			Long count = ambienteRepo.countByLogin( login );
			
			if ( count > 0 )
				throw new RuntimeException( "O login informado não está disponível por favor insira outro." );
			
			if ( StringUtils.contains( login , " " ) )
				throw new RuntimeException( "O login informado não deve ter espaços." );
			
			if ( UtilsStr.isAlphaNumeric( login ) )
				throw new RuntimeException( "Existem caracteres inválidos no login. Apenas números e letras são aceitos." );
		}
	}


	private void preencheValoresDefault( Ambiente ambiente )
	{
		if ( ambiente.getHoraIniExpediente() == null )
			ambiente.setHoraIniExpediente( 0 );
		
		if ( ambiente.getMinutoIniExpediente() == null )
			ambiente.setMinutoIniExpediente( 0 );

		if ( ambiente.getHoraFimExpediente() == null )
			ambiente.setHoraFimExpediente( 23 );
		
		if ( ambiente.getMinutoFimExpediente() == null )
			ambiente.setMinutoFimExpediente( 59 );
	}
	
	private void criaBlocoDefault( Ambiente ambiente )
	{
		Bloco bloco = new Bloco();
		
		bloco.setAmbiente( ambiente );
		bloco.setIndexHoraCerta( 0 );
		bloco.setIndexInstitucionais( 0 );
		bloco.setIndexProgrametes( 0 );
		bloco.setPosicaoVinheta( PosicaoVinheta.ANTES_BLOCO_COMERCIAL );
		bloco.setQtdMusicas( 3 );
		bloco.setQtdComerciais( 2 );

		blocoRepository.saveAndFlush( bloco );
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

