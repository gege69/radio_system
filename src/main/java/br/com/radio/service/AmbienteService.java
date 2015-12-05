package br.com.radio.service;

import java.time.LocalTime;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.dto.GeneroListDTO;
import br.com.radio.enumeration.PosicaoVinheta;
import br.com.radio.enumeration.VozLocucao;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Bloco;
import br.com.radio.model.Empresa;
import br.com.radio.model.Evento;
import br.com.radio.model.EventoHorario;
import br.com.radio.model.Genero;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.BlocoRepository;
import br.com.radio.repository.EmpresaRepository;
import br.com.radio.repository.EventoHorarioRepository;
import br.com.radio.repository.EventoRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.util.Constantes;
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
	private MidiaService midiaService;
	
	@Autowired
	private BlocoRepository blocoRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AmbienteConfiguracaoRepository ambienteConfigRepo;
	
	@Autowired
	private EventoRepository eventoRepo;
	
	@Autowired
	private EventoHorarioRepository eventoHorarioRepo;



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
		
		criaExpedienteDefault( ambiente );
		
		byte[] logo = Base64.getDecoder().decode( Constantes.logoRadio );
		
		ambiente.setLogomarca( logo );
		ambiente.setLogomimetype( "image/png" );
		

		Usuario usuario = usuarioService.registerNewUserPlayer( ambiente );
		
		ambiente.setPassword( "" );
		
		ambiente = ambienteRepo.saveAndFlush( ambiente );
		
		usuario.setAmbiente( ambiente );
		usuarioService.save( usuario );
		
		criaConfiguracoesDefault( ambiente );
		
		midiaService.associaTodasMidiasParaAmbiente( ambiente );
		
		criaBlocoDefault( ambiente );

		return ambiente; 
	}


	private void criaExpedienteDefault( Ambiente ambiente )
	{
		 ambiente.setHoraIniExpediente( 0 ); 
		 ambiente.setHoraFimExpediente( 23 ); 
		 ambiente.setMinutoIniExpediente( 0 );
		 ambiente.setMinutoFimExpediente( 59 );
	}


	private void validaLogin( Ambiente ambiente )
	{
		if ( StringUtils.isNotBlank( ambiente.getLogin() ) )
		{
			String login = ambiente.getLogin();
			
			Long count = 0l;

			// Contando pra ver se tem algum outro login que não seja esse ambiente ( para o caso de update )
			if ( ambiente.getIdAmbiente() != null )
				count = ambienteRepo.countByLoginAndIdAmbienteNot( login, ambiente.getIdAmbiente() );
			else
				count = ambienteRepo.countByLogin( login );
			
			if ( usuarioService.countByLogin( login ) > 0 )
				throw new RuntimeException( "O login informado não está disponível por favor insira outro." );
			 
			if ( count > 0 )
				throw new RuntimeException( "O login informado não está disponível por favor insira outro." );
			
			if ( StringUtils.contains( login , " " ) )
				throw new RuntimeException( "O login informado não deve ter espaços." );
			
			if ( !UtilsStr.isAlphaNumeric( login ) )
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
		
		byte[] logo = Base64.getDecoder().decode( Constantes.logoRadio );
		
		ambiente.setLogomarca( logo );
		ambiente.setLogomimetype( "image/png" );
	}
	
	private void criaConfiguracoesDefault( Ambiente ambiente )
	{
		AmbienteConfiguracao config = new AmbienteConfiguracao();
		
		config.setAmbiente( ambiente );
		
		config.setAgendMidia( true );
		config.setAtendimento( true );
		config.setAutoplay( true );
		config.setAvancarRetornar( true );   // Apenas avançar...
		config.setChamFuncionarios( true );
		config.setChamInstantanea( true );
		config.setChamVariosFuncionarios( true );
		config.setChamVeiculo( true );
		config.setControleBlocos( true );
		config.setControleComerciais( true );
		config.setControleInstitucionais( true );
		config.setControleProgrametes( true );
		config.setGenerosByCC( true	);
		config.setHoroscopo( true );
		config.setLocutorVirtual( true );
		config.setMenuDownloads( true );
		config.setOpcionais( true );
		config.setPedidoMusical( true );
		config.setPedidoMusicalVinheta( true );
		config.setRelatoriosMidia( true );
		config.setRodoviarias( true );
		config.setSelecaoGenero( true );
		
		config.setControleVolumeIndividual( false );
		config.setVolumeChamadas( 100 );
		config.setVolumeComerciais( 100 );
		config.setVolumeGeral( 100 );
		config.setVolumeMusicas( 100 );
		config.setVozLocucao( VozLocucao.MASCULINA );

		ambienteConfigRepo.save( config );
		
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
	
	
	@Transactional
	public Evento saveEvento( Long idAmbiente, Evento evento )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			evento.setAmbiente( ambiente );
			eventoRepo.save( evento );
			
			Comparator<EventoHorario> porHoraMinuto = ( um, outro ) -> {
				int i = um.getHora().compareTo( outro.getHora() );
				if ( i != 0 ) return i;
				return um.getMinuto().compareTo( outro.getMinuto() );
			};
			
			eventoHorarioRepo.deleteByEvento( evento );
			
			List<EventoHorario> horarios = evento.getHorarios();
			
			horarios = horarios.stream().filter( h -> h != null ).collect( Collectors.toList() );
			
			horarios.sort( porHoraMinuto );
			
			for ( EventoHorario horario : horarios )
			{
				horario.setEvento( evento );
				eventoHorarioRepo.save( horario );
			}
			
			// depois verificar os repetidos.
		}

		return evento;
	}
	
	
}

