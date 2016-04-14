package br.com.radio.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.enumeration.PosicaoComercial;
import br.com.radio.enumeration.PosicaoVinheta;
import br.com.radio.enumeration.StatusPlayback;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.AudioOpcional;
import br.com.radio.model.Bloco;
import br.com.radio.model.Categoria;
import br.com.radio.model.Evento;
import br.com.radio.model.EventoHorario;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.Programacao;
import br.com.radio.model.ProgramacaoGenero;
import br.com.radio.model.Transmissao;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.AudioOpcionalRepository;
import br.com.radio.repository.BlocoRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.ProgramacaoGeneroRepository;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.repository.TransmissaoRepository;
import br.com.radio.service.programacaomusical.ListaInesgotavel;
import br.com.radio.service.programacaomusical.ListaInesgotavelRandom;
import br.com.radio.service.programacaomusical.ListaInesgotavelRandomAlternada;
import br.com.radio.service.programacaomusical.ProgramacaoListMidiaListDTO;
import br.com.radio.util.UtilsDates;

import com.google.common.collect.Iterators;


@Service
public class ProgramacaoMusicalService {
	
	@Autowired
	private ProgramacaoRepository programacaoRepo;
	
	@Autowired
	private ProgramacaoGeneroRepository programacaoGeneroRepo;
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private MidiaRepository midiaRepo;

	@Autowired
	private MidiaService midiaService;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired 
	private BlocoRepository blocoRepo;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired 
	private TransmissaoRepository transmissaoRepo;
	
	@Autowired
	private AmbienteService ambienteService;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	@Autowired
	private AmbienteGeneroRepository ambienteGeneroRepo;

	@Autowired
	private AudioOpcionalRepository opcionalRepo;
	
	private static final Logger logger = Logger.getLogger(ProgramacaoMusicalService.class);
	
	/**
	 * Esse método vai obter os registros de programação do banco de dados e validar se existe colisão.
	 * 
	 * Se existir vai tentar inativar.
	 * 
	 * @param ambiente
	 * @return
	 */
	public List<Programacao> getProgramacaoAtivaByAmbiente( Ambiente ambiente )
	{
		List<Programacao> result = null;
		
		result = programacaoRepo.findByAmbienteAndAtivoTrue( ambiente );

		result.forEach( prog -> {

			Map<String, String> api = prog.getProgAPI();
			
			if ( prog.getGeneros() == null )
				api.put( "generosCount", "0" );
			else
				api.put( "generosCount", Integer.toString( prog.getGeneros().size() ) );
			
			if ( prog.getGeneros() != null && prog.getGeneros().size() == 1 )
				api.put( "generoSingle", prog.getGeneros().get( 0 ).getNome() );
			
		});
		
//		Map<DiaSemana, List<Programacao>> mapProdPorDia = result.stream().collect( Collectors.groupingBy( Programacao::getDiaSemana ) );
//
//		mapProdPorDia.forEach( ( dia, progs ) -> {
//			
//			// Quadrático... uma bosta
//			progs.forEach( p -> {
//				
//				progs.forEach( outro -> {
//					
//					if ( !p.equals( outro ) )
//					{
//						if ( UtilsDates.isOverlapping( p.getDateTimeInicio(), p.getDateTimeFim(), outro.getDateTimeInicio(), outro.getDateTimeFim() ) )
//						{
//							System.out.println( p + " overlap com " + outro );
//							throw new RuntimeException( "Existe programação com overlap" );
//						}
//					}
//				});
//			});
//		});
		
		return result;
	}
	
	
	
	public void verificaECriaProgramacaoDefault( Long idAmbiente )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		verificaECriaProgramacaoDefault( ambiente );
	}
	
	
	
//	@Async
	@Transactional
	public void verificaECriaProgramacaoDefault( Ambiente ambiente )
	{
		// verificar se tem transmissão
		List<Programacao> progList = programacaoRepo.findByAmbienteAndAtivoTrue( ambiente );
		
		if ( progList == null || progList.size() <= 0 )
		{
			Genero genero = generoRepo.findOne( 1L );  // Top 300 .... caso mude repensar isso
			
			AmbienteGenero ambienteGenero = ambienteGeneroRepo.findByAmbienteAndGenero( ambiente, genero );
			
			if ( ambienteGenero == null )
				ambienteGeneroRepo.save( new AmbienteGenero( ambiente, genero ) );
			
			for ( DiaSemana dia : DiaSemana.values() )
				gravaGenerosProgramacaoDiaInteiro( ambiente, dia, genero );
			
			geraTransmissao( ambiente );
		}
		
	}
	
	
	
	
	
	public boolean gravaGenerosProgramacaoDiaInteiro( Ambiente ambiente, DiaSemana dia, Genero genero )
	{
		List<Genero> generos = new ArrayList<Genero>();
		generos.add( genero );
		
		return gravaGenerosProgramacaoDiaInteiro( ambiente, dia, generos );
	}
	
	
	
	/**
	 * Esse método vai pegar os gêneros passados por parâmetro e copiar para o dia inteiro ( apenas o determinado pelo expediente )
	 * 
	 * @param ambiente
	 * @param dia
	 * @param generos
	 * @return
	 */
	@Transactional
	public boolean gravaGenerosProgramacaoDiaInteiro( Ambiente ambiente, DiaSemana dia, List<Genero> generos )
	{
		if ( ambiente.getHoraIniExpediente() == null || 
			 ambiente.getHoraFimExpediente() == null || 
			 ambiente.getMinutoIniExpediente() == null ||
			 ambiente.getMinutoFimExpediente() == null )
			throw new RuntimeException("O Expediente desse ambiente não está configurado.");
		
		List<Programacao> programacoesDoDia = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, dia );
		
		programacoesDoDia.forEach( prog -> {
			prog.setAtivo( false );
			prog.setDataInativo( new Date() );
		});
		
		programacaoRepo.save( programacoesDoDia );
		
		if ( generos != null && generos.size() > 0 )
		{
			Integer horaInicioDia = ambiente.getHoraIniExpediente();
			Integer horaFimDia = 0;

			if ( ambiente.getMinutoFimExpediente() > 0 )
			{
				if ( ambiente.getHoraFimExpediente().equals(23) )
					horaFimDia = 23;
				else
					horaFimDia = ambiente.getHoraFimExpediente() + 1;
			}
			else
				horaFimDia = ambiente.getHoraFimExpediente();

			for ( int hora = horaInicioDia; hora <= horaFimDia; hora++ )
			{
				Programacao nova = gravaNovaProgramacao( ambiente, new Programacao( ambiente, dia, hora ), false );
				
				gravaGeneros( generos, nova );
			}
		}
		
		return true;
	}
	

	
	/**
	 * Vai inativar o registro antigo da programação e criar um registro novo ( histórico )
	 * 
	 * Vai inserir os novos gêneros para o registro novo
	 *
	 * @param ambiente
	 * @param dto
	 * @param generos
	 * @return
	 */
	@Transactional
	public Programacao gravaGenerosProgramacao( Ambiente ambiente, Programacao dto, List<Genero> generos )	
	{
		if ( ambiente == null )
			throw new RuntimeException("Código do ambiente inválido");
		
		if ( dto == null )
			throw new RuntimeException( "Código da programação inválido");
		
		dto.setAtivo( false );
		dto.setDataInativo( new Date() );
		
		programacaoRepo.save( dto );
		
		Programacao nova = null;
		
		if ( generos != null && generos.size() > 0 )
		{
			nova = gravaNovaProgramacao( ambiente, dto );

			gravaGeneros( generos, nova );
		}

		return nova;
	}


	private void gravaGeneros( List<Genero> generos, Programacao nova )
	{
		List<ProgramacaoGenero> progGeneros = new ArrayList<ProgramacaoGenero>();
		
		generos.forEach( gen -> {
			
			ProgramacaoGenero progGen = new ProgramacaoGenero();
			
			progGen.setGenero( gen );
			progGen.setProgramacao( nova );
			
			progGeneros.add( progGen );
		});
		
		programacaoGeneroRepo.save( progGeneros );

	}
	
	

	public Programacao gravaNovaProgramacao( Ambiente ambiente, Programacao dto )
	{
		return this.gravaNovaProgramacao( ambiente, dto, true );
	}
	
	
	public Programacao gravaNovaProgramacao( Ambiente ambiente, Programacao dto, Boolean testOverlap )
	{
		if ( dto.getDiaSemana() == null )
			throw new RuntimeException("Dia da Semana não preenchido");
		
		if ( dto.getHoraInicio() == null )
			throw new RuntimeException("Início não determinado");
		
//		if ( dto.getHoraFim() == null || dto.getMinutoFim() == null )
//			throw new RuntimeException("Fim não determinado");
		
		if ( dto.getMinutoInicio() == null )
			dto.setMinutoInicio( 0 );
		
		if ( dto.getHoraFim() == null )
		{
			if ( dto.getHoraInicio().equals( 23 ) )
				dto.setHoraFim( 0 );
			else
				dto.setHoraFim( dto.getHoraInicio() + 1 );
		}
		
		if ( dto.getMinutoFim() == null )
			dto.setMinutoFim( 0 );
		
		Programacao p = new Programacao();
		
		p.setAmbiente( ambiente );
		p.setAtivo( true );	
		p.setDiaSemana( dto.getDiaSemana() );
		
		p.setHoraInicio( dto.getHoraInicio() );
		p.setMinutoInicio( dto.getMinutoInicio() );
		
		p.setHoraFim( dto.getHoraFim() );
		p.setMinutoFim( dto.getMinutoFim() );
		
		p.setDateTimeInicio( p.getDate( p.getHoraInicio(), p.getMinutoInicio() ) );
		p.setDateTimeFim( p.getDate( p.getHoraFim(), p.getMinutoFim() ) );

		if ( testOverlap )
		{
			List<Programacao> progs = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, dto.getDiaSemana() );
			
			progs.forEach( outro -> {
				
				if ( !p.equals( outro ) )
				{
					if ( UtilsDates.isOverlapping( p.getDateTimeInicio(), p.getDateTimeFim(), outro.getDateTimeInicio(), outro.getDateTimeFim() ) )
					{
						System.out.println( p + " overlap com " + outro );
						throw new RuntimeException( "Essa programação está em overlap com outra já existente" );
					}
				}
			});
		}
		
		programacaoRepo.save( p );
		
		return p;
	}
	
	
	
	/**
	 * 
	 * Uma vez gravado o novo expediente ( ou até mesmo antes de gravar ) é necessário mostrar para o usuário que ficaram programações musicais programadas para depois do expediente.
	 * 
	 * Isso não é um erro pois o player vai obedecer o Expediente mas é bom avisar.
	 * 
	 * @param ambiente
	 * @return
	 */
	public Boolean verificaExisteProgramacaoForaDoNovoExpediente( Ambiente ambiente )
	{
//		List<Programacao> programacoes = programacaoRepo.findbyambiente
		
		return false;
	}
	
	
	
	

	public Map<Set<Genero>, ProgramacaoListMidiaListDTO> selecaoMusicas( Ambiente ambiente )
	{
		// Baseado no ambiente e no dia atual... vai buscar os gêneros
		Categoria categoria = categoriaRepo.findByCodigo( Categoria.MUSICA );
		
		LocalDateTime hoje = LocalDateTime.now();
		
		DiaSemana diaSemana = DiaSemana.getByIndex( hoje.getDayOfWeek().getValue() );

		
		// só verificar se já a música foi tocada durante o dia.
		List<Transmissao> transmissoesTocadas = transmissaoRepo.findByAmbienteAndStatusPlaybackAndDiaPlayBetween( ambiente, StatusPlayback.FIM, UtilsDates.asUtilMidnightDate( hoje ), UtilsDates.asUtilLastSecondDate( hoje ) );
		
		Set<Midia> musicasJaTocadas = transmissoesTocadas.stream().map( Transmissao::getMidia ).collect( Collectors.toCollection( HashSet::new ) );
		
		System.out.println( "Já Tocadas : " + musicasJaTocadas.size() );

		List<Programacao> programacaoDia = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, diaSemana );
		
		programacaoDia.forEach( p -> {
 			p.getGeneros().size(); // inicializando o hibernate lazy loader 
			p.setGenerosSet( new HashSet<Genero>( p.getGeneros() ) );
		});
		
		Map<Set<Genero>, List<Programacao>> mapProgramacaoPorGeneros = programacaoDia.stream().collect( Collectors.groupingBy( Programacao::getGenerosSet ) );
		
		Map<Set<Genero>, ProgramacaoListMidiaListDTO> result = new HashMap<>();
		
		// Primeiro separa a lista de programação pelos seus gêneros.
		// As músicas precisam estar agrupada por gêneros
		// Cria outro mapa com um objeto contendo a lista de programação e a lista de músicas para ser consumida.
		mapProgramacaoPorGeneros.forEach( ( generosSet, programacaoList ) -> {

			// Esse select pode ser melhorado para buscar as músicas mais famosas para não incluir músicas ruins de artistas famosos...
			List<Midia> midias = null;
			
			if ( musicasJaTocadas != null && musicasJaTocadas.size() > 0 ){
				
				//TODO: aqui tem que tentar evitar trazer música sem artista.... isso dá problema no shuffle

				midias = midiaRepo.findByAmbientesAndCategoriasAndGenerosInAndMidiaNotInGroupBy( ambiente, categoria, generosSet, musicasJaTocadas );
				
				// isso não pode ser zero.... não posso gerar
				if ( midias == null || midias.size() == 0 )
					midias = midiaRepo.findByAmbientesAndCategoriasAndGenerosInGroupBy( ambiente, categoria, generosSet );
			}
			else
				midias = midiaRepo.findByAmbientesAndCategoriasAndGenerosInGroupBy( ambiente, categoria, generosSet );
			
			ProgramacaoListMidiaListDTO dto = new ProgramacaoListMidiaListDTO( programacaoList, midias, categoria );
			
			result.put( generosSet, dto );
		});
		
		return result;
	}

	
	
	private Transmissao getProximaTransmissaoPeloIdAtual( Ambiente ambiente, Transmissao atual )
	{
		Transmissao result = null;
		
		if ( atual != null )
			result = transmissaoRepo.findByAmbienteAndLinkativoTrueAndPosicaoplay( ambiente.getIdAmbiente(), atual.getPosicaoplay() );
		
		return result;
	}
	
	


	private Transmissao getTransmissaoAtual( Ambiente ambiente )
	{
		Transmissao result = null;
		
		// Vai utilizar a hora do servidor do banco de dados para encontrar a música que deveria estar tocando.... horário do servidor tem que estar configurado corretamente ( TIMEZONE BRASIL GMT +3 SÃO PAULO )
		result = transmissaoRepo.findByIdAmbienteAndLinkativoTrueAndPrevisaoAtual( ambiente.getIdAmbiente() );

		// Se não tem música no horário atual ... é preciso ver se o expediente já começou e pegar o primeiro que tiver pra tocar...
		if ( result == null && ambienteService.isExpedienteOn( ambiente  ) )
		{
			LocalDate hoje = LocalDate.now();
			result = transmissaoRepo.findFirstByAmbienteAndLinkativoTrueAndDiaPlayOrderByIdTransmissaoAscPosicaoplayAsc( ambiente, UtilsDates.asUtilDate( hoje ) );
		}
		
		// Não tem música nem transmissões configuradas... talvez seja necessário gerar 
		if ( result == null )  // isso pode ser explorado... tem que fazer um batch pra rodar todo dia de manhã...
		{
			LocalDateTime hoje = LocalDateTime.now();
			
			DiaSemana diaSemana = DiaSemana.getByIndex( hoje.getDayOfWeek().getValue() );

			Integer existeProgramacao = programacaoRepo.getExisteProgramacaoParaHorarioAtual( ambiente, diaSemana.name() );
			
			if ( existeProgramacao != null && existeProgramacao > 0 )
			{
				geraTransmissao( ambiente );  // espera terminar...
				
				result = transmissaoRepo.findByIdAmbienteAndLinkativoTrueAndPrevisaoAtual( ambiente.getIdAmbiente() );
			}
		}
		
		return result;
	}
	
	
	
	@Transactional
	public Transmissao getTransmissaoAoVivo( Ambiente ambiente )
	{
		Transmissao result = getTransmissaoAtual( ambiente ); 
		
		if ( result != null && result.getIdTransmissao() != null )
			transmissaoRepo.setLinkInativoAnteriores( ambiente, result.getIdTransmissao() );
		
		return result;
	}


	@Transactional
	public Transmissao getTransmissaoAoVivoSkipForward( Ambiente ambiente )
	{
		Transmissao atual = getTransmissaoAtual( ambiente ); 
		
		Transmissao result = getProximaTransmissaoPeloIdAtual( ambiente, atual );

		Long idTransmissao = null;
		
		if ( result == null )
			idTransmissao = atual.getIdTransmissao();
		else
			idTransmissao = result.getIdTransmissao();

		if ( idTransmissao != null )
			transmissaoRepo.setLinkInativoAnteriores( ambiente, idTransmissao );
		
		//TODO: gerar nova midia incremental aqui
		
		return result;
	}
	
	
	
	@Transactional
	public void geraTransmissao( Ambiente ambiente )
	{

		// melhorar... se estiver quase acabando o dia.. gerar para o dia posterior também

		// Gerar sempre....  inativando os registros de transmissão anteriores		
		int ignoradas = transmissaoRepo.setStatusIgnorada( ambiente ); // o que não tocou não será mais tocado... vou gerar uma nova playlist
		int inativos = transmissaoRepo.setLinkInativo( ambiente );  // inativando os registros 
 
		Bloco bloco = blocoRepo.findByAmbiente( ambiente ); 
		
		if ( bloco.getQtdMusicas() > 0 ) {
			geraTransmissaoComMusica( ambiente );
		}
		else {
			geraTransmissaoSemMusica( ambiente );
		}
		
	}



	private void geraTransmissaoComMusica( Ambiente ambiente )
	{
		Map<Set<Genero>, ProgramacaoListMidiaListDTO> musicasPorGenero = selecaoMusicas( ambiente );
		
		musicasPorGenero.forEach( ( generosSet, dto ) -> {
			
//				printaInformacoes( generosSet, dto );

			// algoritmo do spotify
			applySpotifyShuffle( dto );

			validaClusters( dto );

			applyMergeBlocos( ambiente, dto );

			// imprimindo
//				dto.getMidias().forEach( m -> {
//					System.out.println( m.toString() );
//				});

			// caso tenha alguma parametrização o URL request fazer antes... o método de consumir não precisa saber só gravar.
			consomeMidias( ambiente, dto );
		});
	}

	
	private void geraTransmissaoSemMusica( Ambiente ambiente )
	{
		LocalDateTime hoje = LocalDateTime.now();
		
		DiaSemana diaSemana = DiaSemana.getByIndex( hoje.getDayOfWeek().getValue() );

		List<Programacao> programacaoDia = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, diaSemana );
		
		programacaoDia.forEach( p -> {
 			p.getGeneros().size(); // inicializando o hibernate lazy loader 
			p.setGenerosSet( new HashSet<Genero>( p.getGeneros() ) );
		});

		ProgramacaoListMidiaListDTO dto = new ProgramacaoListMidiaListDTO( programacaoDia, null );

		applyMergeBlocosSemMusica( ambiente, dto );

		// caso tenha alguma parametrização o URL request fazer antes... o método de consumir não precisa saber só gravar.
		consomeMidias( ambiente, dto );
	}
	
	/**
	 * Esse método vai criar programação caso não exista nenhuma para o ambiente.
	 * 
	 *  Esse método vai rodar todo dia de madrugada ( TarefasAgendadas.java )
	 * 
	 * @param ambiente
	 */
	@Transactional
	public void criaProgramacaoMusicalDoDiaParaAmbiente( Ambiente ambiente )
	{
		LocalDate hoje = LocalDate.now();
		
		Transmissao transmissao = transmissaoRepo.findFirstByAmbienteAndLinkativoTrueAndDiaPlayOrderByIdTransmissaoAscPosicaoplayAsc( ambiente, UtilsDates.asUtilDate( hoje ) );
		
		if ( transmissao == null )  // se não tem, gerar
		{
			this.geraTransmissao( ambiente );
		}
	}
	



	private void validaClusters( ProgramacaoListMidiaListDTO dto )
	{
		List<Midia> midias = dto.getMidias();
		
		for ( int i = 0; i < midias.size(); i++ )
		{
			Midia atual = midias.get( i );
			
			Midia next = null;
			
			Midia nextnext = null;
			
			if ( i+1 < midias.size() )
				next = midias.get( i+1 );
			
			if ( i+2 < midias.size() )
				nextnext = midias.get( i+2 );
			
//			if ( next != null && StringUtils.equals( atual.getArtist(), next.getArtist() ) )
//				System.out.println( String.format( "Artista %s está próximo em ids ( %d , %d ) ", atual.getArtist(), atual.getIdMidia(), next.getIdMidia()) );
//			
//			if ( nextnext != null && StringUtils.equals( atual.getArtist(), nextnext.getArtist() ) )
//				System.out.println( String.format( "Artista %s está próximo (2) em ids ( %d , %d ) ", atual.getArtist(), atual.getIdMidia(), nextnext.getIdMidia()) );
				
		}
	}



	private void printaInformacoes( Set<Genero> generosSet, ProgramacaoListMidiaListDTO dto )
	{
		System.out.println( "_______________" );
		
		Integer duracaoTotal = dto.getMidias().stream().mapToInt( Midia::getDuracao ).sum();

		System.out.println( generosSet.toString() );
		System.out.println( dto.getMidias().size() );
		System.out.println( duracaoTotal + " segundos " );
		System.out.println( ( duracaoTotal / 60 ) + " minutos " );
		System.out.println( ( ( duracaoTotal / 60 ) / 60 )+ " horas " );
		System.out.println( dto.getProgramacoes().size() + " progamacoes (1 hora cada) : total " + dto.getProgramacoes().size() + " horas " );
	}
	
	
	
	private void applyFisherYatesShuffle( ProgramacaoListMidiaListDTO dto ) 
	{
		ThreadLocalRandom r = ThreadLocalRandom.current();
		Collections.shuffle( dto.getMidias(), r );
	}
	
	
	
	private void addIfNotNull( List<Midia> novaListaMidias, Midia midia )
	{
		if ( midia != null )
			novaListaMidias.add( midia );
	}


	


	private ListaInesgotavelRandomAlternada getListaInesgotavelOpcionais( Bloco bloco ){
		
		List<ListaInesgotavel> listasInesgotaveis = new ArrayList<ListaInesgotavel>();
		
		Categoria categoriaOpcional = categoriaRepo.findByCodigo( Categoria.OPCIONAL );

		Ambiente ambiente = bloco.getAmbiente();
		
		for ( AudioOpcional opcional : bloco.getOpcionais() ){

			List<Midia> midiasOpcional = midiaService.getMidiasOpcionais( ambiente, opcional );
			
			ListaInesgotavel li = new ListaInesgotavelRandom( new ArrayList<Midia>( midiasOpcional ), categoriaOpcional );
			
			listasInesgotaveis.add( li );
		}
		
		ListaInesgotavelRandomAlternada result = new ListaInesgotavelRandomAlternada( listasInesgotaveis );

		return result;
	}

	


	private String geraStringExemploMusica( int qtd ) {
		
		if ( qtd > 1 )
			return String.format( "%d Músicas", qtd );
		else
			return "Música";
	}


	private String geraStringExemploComercial( int qtd ) {
		
		if ( qtd > 1 )
			return String.format( "%d Comerciais", qtd );
		else
			return "Comercial";
	}


	public List<String> getExemploSequenciaBlocoComMusicas( Bloco bloco ){
		
		List<String> result = new ArrayList<String>();
		
		PosicaoVinheta posicaoVinheta = bloco.getPosicaoVinheta();
		PosicaoComercial posicaoComercial = bloco.getPosicaoComercial();
		
		if ( posicaoVinheta == null || posicaoComercial == null )
			return null;
		
		if ( bloco.getQtdMusicas() == null || bloco.getQtdComerciais() == null )
			return null;
		
		if ( bloco.getIndexInstitucionais() == null )
			bloco.setIndexInstitucionais( 0 );

		if ( bloco.getIndexProgrametes() == null )
			bloco.setIndexProgrametes( 0 );

		if ( bloco.getIndexOpcionais() == null )
			bloco.setIndexOpcionais( 0 );
		
		Iterator<AudioOpcional> iteratorOpcionais = null;
		List<AudioOpcional> listaOpcionais = new ArrayList<AudioOpcional>();
		
		if ( bloco.getOpcionais() != null ){
			
			for ( AudioOpcional opc : bloco.getOpcionais() ){
				
				AudioOpcional audioOpcional = opcionalRepo.findOne( opc.getIdOpcional() );
				listaOpcionais.add( audioOpcional );
			}
		}
		
		if ( bloco.getOpcionais() != null )
			iteratorOpcionais = Iterators.cycle( listaOpcionais );
		
		int qtdMusicasSequencia = bloco.getQtdMusicas();
		int qtdComerciaisSequencia = bloco.getQtdComerciais();
		
		int stepInstitucionais = bloco.getIndexInstitucionais();
		int stepProgrametes = bloco.getIndexProgrametes();
		int stepOpcionais = bloco.getIndexOpcionais();

		int countMusicasInseridas = 0;

		for ( int i = 0; i <= 4; i++ ){

			if ( qtdMusicasSequencia > 0 ) {

				if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_CADA_MUSICA ) ) {
					result.add( "Vinheta" );
				}
				
				result.add( geraStringExemploMusica( qtdMusicasSequencia ) );
				countMusicasInseridas += qtdMusicasSequencia;

				if ( posicaoComercial.equals( PosicaoComercial.DEPOIS_MUSICAS ) ) {
					
					if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
					
					result.add( geraStringExemploComercial( qtdComerciaisSequencia ) );
					
					if ( posicaoVinheta.equals( PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
				}
			}
				
			if ( stepInstitucionais > 0 && countMusicasInseridas % stepInstitucionais == 0 )  // Depois de n músicas
			{
				if ( posicaoComercial.equals( PosicaoComercial.ANTES_INSTITUCIONAL ) ){
					if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
					
					result.add( geraStringExemploComercial( qtdComerciaisSequencia ) );
					
					if ( posicaoVinheta.equals( PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
				}
				
				result.add("Institucional");

				if ( posicaoComercial.equals( PosicaoComercial.DEPOIS_INSTITUCIONAL ) ){
					if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
					
					result.add( geraStringExemploComercial( qtdComerciaisSequencia ) );
					
					if ( posicaoVinheta.equals( PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
				}
			}
			
			if ( stepProgrametes > 0 && countMusicasInseridas % stepProgrametes == 0 )  // Depois de n músicas
			{
				if ( posicaoComercial.equals( PosicaoComercial.ANTES_PROGRAMETE ) ){
					if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
					
					result.add( geraStringExemploComercial( qtdComerciaisSequencia ) );
					
					if ( posicaoVinheta.equals( PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
				}
				
				result.add( "Programete");
				
				if ( posicaoComercial.equals( PosicaoComercial.DEPOIS_PROGRAMETE ) ){
					if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );
					
					result.add( geraStringExemploComercial( qtdComerciaisSequencia ) );
					
					if ( posicaoVinheta.equals( PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL ) )
						result.add( "Vinheta" );				
				}
			}
				
			if ( stepOpcionais > 0 && countMusicasInseridas % stepOpcionais == 0 ) 
			{
				// OPCIONAL
				if ( iteratorOpcionais != null && iteratorOpcionais.hasNext() ){
					AudioOpcional opcional = iteratorOpcionais.next();
					result.add( String.format( "Opcional (%s)", opcional.getNome() ) );
				}
			}	
			
		}
		
		result = result.subList( 0, Math.min( 15, result.size() ) );

		result.add( "...etc" );
		
		return result;
	}
	


	private void applyMergeBlocosSemMusica( Ambiente ambiente, ProgramacaoListMidiaListDTO dto )
	{
		Bloco bloco = blocoRepo.findByAmbiente( ambiente ); 
		
		PosicaoVinheta posicaoVinheta = bloco.getPosicaoVinheta();
		PosicaoComercial posicaoComercial = bloco.getPosicaoComercial();
		
		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		
		Categoria vinheta = categoriaRepo.findByCodigo( Categoria.VINHETA );
		Categoria comercial = categoriaRepo.findByCodigo( Categoria.COMERCIAL );
		Categoria institucional = categoriaRepo.findByCodigo( Categoria.INSTITUCIONAL );
		Categoria programete = categoriaRepo.findByCodigo( Categoria.PROGRAMETE );
		
		ListaInesgotavelRandom liVinhetas = new ListaInesgotavelRandom( midiaService.getMidiasComerciais( ambiente, vinheta ), vinheta );
		ListaInesgotavelRandom liComerciais = new ListaInesgotavelRandom( midiaService.getMidiasComerciais( ambiente, comercial ), comercial );
		ListaInesgotavelRandom liInstitucionais = new ListaInesgotavelRandom( midiaService.getMidiasComerciais( ambiente, institucional ), institucional );
		ListaInesgotavelRandom liProgrametes = new ListaInesgotavelRandom( midiaService.getMidiasComerciais( ambiente, programete ), programete );
		ListaInesgotavel liOpcionais = getListaInesgotavelOpcionais( bloco );

		int qtdComerciaisSequencia = bloco.getQtdComerciais();
		int stepInstitucionais = bloco.getIndexInstitucionais();
		int stepProgrametes = bloco.getIndexProgrametes();
		int stepOpcionais = bloco.getIndexOpcionais();
		
		LinkedList<Midia> result = new LinkedList<Midia>();
		
		List<Programacao> programacoes = dto.getProgramacoes();
		
		// Usando convenção de que uma programação é de 1 hora
		int countMinutosNecessarios = 60 * programacoes.size();
		
		int countMinutosProgramados = 0;

		while ( countMinutosProgramados <= countMinutosNecessarios )
		{
			LinkedList<Midia> novaListaMidias = new LinkedList<Midia>();

			if ( stepInstitucionais > 0 ) 
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				// INSTITUCIONAL
				addIfNotNull( novaListaMidias, liInstitucionais.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
			}
			
			if ( stepProgrametes > 0 ) 
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				// PROGRAMETE
				addIfNotNull( novaListaMidias, liProgrametes.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
			}
			
			if ( stepOpcionais > 0 ) 
			{
				// OPCIONAL
				addIfNotNull( novaListaMidias, liOpcionais.getNextRandom( rnd ) );
			}
			
			if ( novaListaMidias.size() == 0 ) // sinal de configuração ruim.... evitar loop infinito
				break;
			
			// aqui calcular o tamanho de cada midia
			int totalMinutosSublista = novaListaMidias.stream().mapToInt( midia -> midia.getDuracao() ).sum();
			
			if ( totalMinutosSublista <= 0 )  // outro sinal de configuração ruim ou problemas nas midias... evitar loop infinito
				break;
			
			countMinutosProgramados += totalMinutosSublista;
			
			result.addAll( novaListaMidias );
		}
		
		dto.setMidias( result );
	}

	
	
	private boolean verificaMomentoVinhetaMerge( PosicaoVinheta posicaoVinhetaAtual, PosicaoVinheta posicaoVinhetaVerificar, ListaInesgotavelRandom blocoVinhetas )  
	{
		return ( posicaoVinhetaAtual.equals( posicaoVinhetaVerificar ) && blocoVinhetas.temRegistro() );
	}
	
	
	private boolean verificaMomentoComercialMerge( PosicaoComercial posicaoComercialConfigurado, PosicaoComercial posicaoComercialVerificar, ListaInesgotavelRandom blocoComerciais )  
	{
		return ( posicaoComercialConfigurado.equals( posicaoComercialVerificar ) && blocoComerciais.temRegistro() );
	}
	
	
	private void applyMergeBlocos( Ambiente ambiente, ProgramacaoListMidiaListDTO dto )
	{
		ArrayDeque<Midia> musicasEmbaralhadas = new ArrayDeque<Midia>( dto.getMidias() );

		Bloco bloco = blocoRepo.findByAmbiente( ambiente ); 
		
		PosicaoVinheta posicaoVinheta = bloco.getPosicaoVinheta();
		PosicaoComercial posicaoComercial = bloco.getPosicaoComercial();
		
		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		
		Categoria vinheta = categoriaRepo.findByCodigo( Categoria.VINHETA );
		Categoria comercial = categoriaRepo.findByCodigo( Categoria.COMERCIAL );
		Categoria institucional = categoriaRepo.findByCodigo( Categoria.INSTITUCIONAL );
		Categoria programete = categoriaRepo.findByCodigo( Categoria.PROGRAMETE );
		
		ListaInesgotavelRandom liVinhetas = new ListaInesgotavelRandom( midiaService.getMidiasAtivasPorAmbienteCategoria( ambiente, vinheta ), vinheta );
		ListaInesgotavelRandom liComerciais = new ListaInesgotavelRandom( midiaService.getMidiasAtivasPorAmbienteCategoria( ambiente, comercial ), comercial );
		ListaInesgotavelRandom liInstitucionais = new ListaInesgotavelRandom( midiaService.getMidiasAtivasPorAmbienteCategoria( ambiente, institucional ), institucional );
		ListaInesgotavelRandom liProgrametes = new ListaInesgotavelRandom( midiaService.getMidiasAtivasPorAmbienteCategoria( ambiente, programete ), programete );
		ListaInesgotavel liOpcionais = getListaInesgotavelOpcionais( bloco );

		int qtdMusicasSequencia = bloco.getQtdMusicas();
		int qtdComerciaisSequencia = bloco.getQtdComerciais();
		
		int stepInstitucionais = bloco.getIndexInstitucionais();
		int stepProgrametes = bloco.getIndexProgrametes();
		int stepOpcionais = bloco.getIndexOpcionais();
		
		LinkedList<Midia> novaListaMidias = new LinkedList<Midia>();
		
		int countMusicasInseridas = 0;

		while ( !musicasEmbaralhadas.isEmpty() )
		{
			// Talvez mudar isso para o caso onde existam poucas músicas ficar em loop inserindo as mesmas músicas sempre
			List<Midia> sequenciaMusicas = consomeN( musicasEmbaralhadas, qtdMusicasSequencia );
			
			if ( verificaMomentoVinhetaMerge( posicaoVinheta, PosicaoVinheta.ANTES_CADA_MUSICA, liVinhetas ) )
			{
				for ( Midia m : sequenciaMusicas )
				{
					addIfNotNull( novaListaMidias, liVinhetas.getNextRandom( rnd ) );
					novaListaMidias.add( m );
					countMusicasInseridas++;
				}
			}
			else
			{
				novaListaMidias.addAll( sequenciaMusicas );
				countMusicasInseridas = countMusicasInseridas + sequenciaMusicas.size();
			}
				
			if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_MUSICAS, liComerciais ) )
				adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );

			
			if ( stepInstitucionais > 0 && countMusicasInseridas % stepInstitucionais == 0 )  // Depois de n músicas
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				// INSTITUCIONAL
				addIfNotNull( novaListaMidias, liInstitucionais.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
			}
			
			
			if ( stepProgrametes > 0 && countMusicasInseridas % stepProgrametes == 0 )  // Depois de n músicas
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				// PROGRAMETE
				addIfNotNull( novaListaMidias, liProgrametes.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
			}
				
			if ( stepOpcionais > 0 && countMusicasInseridas % stepOpcionais == 0 ) 
			{
				// OPCIONAL
				addIfNotNull( novaListaMidias, liOpcionais.getNextRandom( rnd ) );
			}
		}
		
		dto.setMidias( novaListaMidias );
	}



	private void adicionaComercialMerge( PosicaoVinheta posicaoVinheta, ThreadLocalRandom rnd, ListaInesgotavelRandom blocoVinhetas, ListaInesgotavelRandom blocoComerciais, int qtdComerciaisSequencia, LinkedList<Midia> novaListaMidias )
	{
		if ( verificaMomentoVinhetaMerge( posicaoVinheta, PosicaoVinheta.ANTES_BLOCO_COMERCIAL, blocoVinhetas ) )
			addIfNotNull( novaListaMidias, blocoVinhetas.getNextRandom( rnd ) );
		
		novaListaMidias.addAll( consomeNFromListaInesgotavel( blocoComerciais, qtdComerciaisSequencia, rnd ) );
		
		if ( verificaMomentoVinhetaMerge( posicaoVinheta, PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL, blocoVinhetas ) )
			addIfNotNull( novaListaMidias, blocoVinhetas.getNextRandom( rnd ) );
	}
	
	
	
	public static <T extends Object> List<T> consomeN( ArrayDeque<T> lista, Integer n )
	{
		List<T> result = new ArrayList<T>();

		int x = 0;
		
		while( !lista.isEmpty() && x < n )
		{
			x++;
			result.add( lista.removeFirst() );
		}
		
		return result;
	}
	


	/**
	 * Consome N elementos da Lista Inesgotável 
	 * 
	 * @param blocoDTO
	 * @param n
	 * @param rnd
	 * @return
	 */
	public List<Midia> consomeNFromListaInesgotavel( ListaInesgotavelRandom blocoDTO, Integer n, ThreadLocalRandom rnd )
	{
		List<Midia> result = new LinkedList<Midia>();
		
		while ( result.size() < n )
			result.add( blocoDTO.getNextRandom( rnd ) );
		
		return result;
	}
	
	
	
	
	
	private void applySpotifyShuffle( ProgramacaoListMidiaListDTO dto )
	{
		// hack para ignorar musicas sem artistas...
		dto.setMidias( dto.getMidias().stream().filter( m -> StringUtils.isNotBlank( m.getArtist() ) ).collect( Collectors.toList() ) );

		List<Midia> musicas = dto.getMidias();
		
		Double tamanhoLista = Integer.valueOf( musicas.size() ).doubleValue();
		
		Map<String, List<Midia>> musicasPorArtista = musicas.stream().collect( Collectors.groupingBy( Midia::getArtist ) );
		
		ThreadLocalRandom rnd = ThreadLocalRandom.current();

		musicasPorArtista.forEach( ( artista, musicasArtista ) -> {

			double tamanhoListaLocal = Integer.valueOf( musicasArtista.size() ).doubleValue();
			double distancia = tamanhoLista / tamanhoListaLocal;
			double limitePadding = tamanhoLista - ( distancia * tamanhoListaLocal );  
			double initialPadding = 1 + (limitePadding - 1) * rnd.nextDouble();
			double posicao = initialPadding;
			
			for ( Midia musica : musicasArtista )
			{
				// Marcando a mídia como música... faço isso nesse ponto para não ter que fazer OUTRO for... estou aproveitando esse LOOP
				musica.setCategoriaSelecionada( dto.getMusicaCategoria() );
				
				musica.setPosicaoShuffle( Double.valueOf( posicao ) );
				
				int coinSignal = rnd.nextInt(2);
				
				double randomAmount = rnd.nextDouble(0.12);
				
				if ( coinSignal == 0 )
					posicao += ( distancia + ( distancia * randomAmount ) );
				else
					posicao += ( distancia - ( distancia * randomAmount ) );
			};
		});
		
		
		Comparator<Midia> pelaPosicaoShuffle = ( m1, m2 ) -> m1.getPosicaoShuffle().compareTo( m2.getPosicaoShuffle() );
		
		musicas.sort( pelaPosicaoShuffle );
 
	}
	
	
	public void consomeMidias( Ambiente ambiente, ProgramacaoListMidiaListDTO dto )
	{
		List<Programacao> programacoes = dto.getProgramacoes();
		
		// Na verdade é bagunçadas.. porque nesse ponto o shuffle já deveria ter ocorrido
		List<Midia> midiasOrdenadas = dto.getMidias();
		
		String url = "/api/ambientes/"+ ambiente.getIdAmbiente() +"/transmissoes/" ;
		
		Integer batchSize = getBatchSize();

		int index = 0;

		// Ordenando as programações para ter uma ordemplay geral pela lista inteira
		Comparator<Programacao> pelaHoraInicial = ( p1, p2 ) -> p1.getHoraInicio().compareTo( p2.getHoraInicio() );
		programacoes.sort( pelaHoraInicial );
		
		Double posicaoplay = 1D;
		
		for ( Programacao prog : programacoes )
		{
			int horafim = prog.getHoraFim();
			
			if ( horafim <= 0 )
				horafim = 24;
			
			int duracaoProgramacao = ( ( horafim - prog.getHoraInicio() ) * 60 ) * 60;  // por enquanto fica simples assim ( sem contar os minutos )
			
			// Obtém uma sublista com mais ou menos a duração da programação
			List<Midia> midiasPeriodoProgramacao = consomePorPeriodoTempo( midiasOrdenadas, index, duracaoProgramacao );
			
			index += midiasPeriodoProgramacao.size();
			
			// Melhorar isso aqui para dar uma previsão melhor.... hoje a partir do segundo período ele perde precisão.
			// Problema vai ser quando as programações não forem uma seguida da outra
			LocalDateTime inicio = LocalDateTime.now().withHour( prog.getHoraInicio() ).withMinute( prog.getMinutoInicio() ).withSecond( 0 ).withNano( 0 );
			
			LocalDate hoje = LocalDate.now();
			
			int qtdTransmissoes = 0;
			
			for ( int i = 0; i < midiasPeriodoProgramacao.size(); i++ )
			{
				Midia midia = midiasPeriodoProgramacao.get( i );
				
				Transmissao transmissao = new Transmissao();
				
				transmissao.setAmbiente( prog.getAmbiente() );
				transmissao.setDataCriacao( new Date() );
				transmissao.setDiaPlay( UtilsDates.asUtilDate( hoje ) );  //Só o dia
				transmissao.setDuracao( midia.getDuracao() );
				transmissao.setCategoria( midia.getCategoriaSelecionada() );
				
				// posso ir armazenando todas essas transmissões e no final ir dando updates sucessivos... dessa maneira o consigo ver se pula horários ou não...
				
				transmissao.setDataPrevisaoPlay( UtilsDates.fromLocalDateTime( inicio ) );
				inicio = inicio.plusSeconds( midia.getDuracao() );
				
				transmissao.setLinkativo( true );
				transmissao.setMidia( midia );
				transmissao.setProgramacao( prog );
				transmissao.setStatusPlayback( StatusPlayback.GERADA );
				transmissao.setPosicaoplay( posicaoplay++ );

				transmissaoRepo.save( transmissao );
				
				if ( i % batchSize == 0 )
				{
					entityManager.flush();
					entityManager.clear();
				}
				
				index = i + 1;
				
				qtdTransmissoes++;
			}
			
			entityManager.flush();
			entityManager.clear();
			
//			System.out.println( String.format( "Programação das %d as %d produzida com %d músicas", prog.getHoraInicio(), prog.getHoraFim(), qtdTransmissoes ) );
		}

		transmissaoRepo.setLinkFor( url );
		
		System.out.println("finish gerar transmissão");
	}




	private Integer getBatchSize()
	{
		String batchSizeStr = env.getRequiredProperty( "hibernate.jdbc.batch_size" );
		
		Integer batchSize = 50;
		
		if ( StringUtils.isNotBlank( batchSizeStr ) )
		{
		 	try
			{
				batchSize = Integer.valueOf( batchSizeStr );
			}
			catch ( NumberFormatException e )
			{
				batchSize = 50;
				e.printStackTrace();
			}
		}
		return batchSize;
	}
	
	
	private List<Midia> consomePorPeriodoTempo( List<Midia> midiasOrdenadas, int inicio, int duracaoMaxima ) 
	{
		List<Midia> result = new ArrayList<Midia>();
		
		int duracaoAtual = 0;
		
		for ( int i = inicio; i < midiasOrdenadas.size(); i++ )
		{
			Midia m = midiasOrdenadas.get( i );
			
			if ( m == null || m.getDuracao() == null || m.getDuracao().equals( 0 ) )
			{
				logger.error( "Mídia está nula ou com duração nula" );
				continue;
			}
			
			duracaoAtual += m.getDuracao();
			
			result.add( m );
			
			if ( duracaoAtual >= duracaoMaxima )
				break;
		}
		
		return result;
	}

	
	
	public Transmissao getTransmissaoEmHorarioAproximado( Ambiente ambiente, LocalTime horario )
	{
		LocalDateTime agora = LocalDateTime.now().withHour( horario.getHour() ).withMinute( horario.getMinute() );
		
		Transmissao result = transmissaoRepo.findByIdAmbienteAndLinkativoTrueAndDataPrevisaoplay( ambiente.getIdAmbiente(), UtilsDates.fromLocalDateTime( agora ) );

		Transmissao eventoJaProgramado = transmissaoRepo.findByIdAmbienteAndLinkativoTrueAndEventoJaProgramado( ambiente.getIdAmbiente(), result.getPosicaoplay() );
		
		if ( eventoJaProgramado != null )
		{
			logger.info( eventoJaProgramado.toString() );
			result = eventoJaProgramado;
		}
		
		return result;
	}
	
	
	
	/**
	 * Esse método vai inserir o evento logo após o registro "anterior" que está em um horário próximo
	 * 
	 * @param evento
	 * @param anterior
	 * @param ambiente
	 */
	@Transactional
	public void criaRegistroTransmissaoPorEvento( Evento evento, EventoHorario eventoHorario, Transmissao anterior, Ambiente ambiente )
	{
		Midia midia = evento.getMidia();

		LocalDate hoje = LocalDate.now();
		
		Transmissao transmissao = new Transmissao();
		
		transmissao.setAmbiente( ambiente );
		transmissao.setDataCriacao( new Date() );
		transmissao.setDiaPlay( UtilsDates.asUtilDate( hoje ) );  //Só o dia
		transmissao.setDuracao( midia.getDuracao() );
		
		transmissao.setCategoria( anterior.getCategoria() );
		transmissao.setDataPrevisaoPlay( anterior.getDataPrevisaoPlay() );  // copia do anterior.. não importa
		
		transmissao.setLinkativo( true );
		transmissao.setMidia( midia );
		transmissao.setProgramacao( anterior.getProgramacao() );
		transmissao.setStatusPlayback( StatusPlayback.GERADA );
		
		// Para o caso de vários eventos no mesmo minuto.... vai incrementando 0.1. Ou seja, é possível até 9 eventos no mesmo slot. ( futuramente para acomodar mais é só diminuir o decimal para 0.01 )
		if ( anterior.getEventoHorario() != null )
		{
			double novoPosicaoplay = anterior.getPosicaoplay();
			
			if ( ( anterior.getPosicaoplay() % 1 ) != 0 || anterior.getEventoHorario() != null ) 
				novoPosicaoplay = novoPosicaoplay + 0.1;
			
			transmissao.setPosicaoplay( novoPosicaoplay );
		}
		else
			transmissao.setPosicaoplay( anterior.getPosicaoplay() + 0.1 );
		
		
		transmissao.setEventoHorario( eventoHorario );
		
		transmissaoRepo.save( transmissao );
		
		String url = "/api/ambientes/"+ ambiente.getIdAmbiente() +"/transmissoes/" ;
		
		transmissaoRepo.setLinkFor( url );
		
		logger.info( String.format( "Transmissao de evento inserida (%d)", transmissao.getIdTransmissao() ) );
	}
	
	
	
}
