package br.com.radio.programacaomusical;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.dto.CadastrarSenhaPlayerDTO;
import br.com.radio.dto.GeneroListDTO;
import br.com.radio.dto.MidiaListDTO;
import br.com.radio.dto.UsuarioAmbienteDTO;
import br.com.radio.dto.midia.MidiaFilter;
import br.com.radio.dto.midia.TransmissaoFilter;
import br.com.radio.enumeration.DiaSemana;
import br.com.radio.enumeration.StatusPlayback;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
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
import br.com.radio.repository.AmbienteConfiguracaoRepository;
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
import br.com.radio.service.AmbienteService;
import br.com.radio.service.midia.MidiaService;
import br.com.radio.util.UtilsDates;
import br.com.radio.util.UtilsStr;

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
	private AmbienteConfiguracaoRepository ambienteConfigRepo;

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




	@Transactional
	public void espelharProgramacaoMusical( Ambiente ambienteOrigem, Ambiente ambienteAlvo ){
		
		// Inativando as velhas
		List<Programacao> programacoesVelhas = programacaoRepo.findByAmbienteAndAtivoTrue( ambienteAlvo );

		programacoesVelhas.forEach( prog -> {
			prog.setAtivo( false );
			prog.setDataInativo( new Date() );
		});
		
		programacaoRepo.save( programacoesVelhas );


		// Copiando as novas
		List<Programacao> programacoesOrigem = programacaoRepo.findByAmbienteAndAtivoTrue( ambienteOrigem );

		Map<DiaSemana, List<Programacao>> programacaoOrigemPorDia = programacoesOrigem.stream().collect( Collectors.groupingBy( Programacao::getDiaSemana ) );
		
		programacaoOrigemPorDia.forEach( ( dia, list ) -> {
			list.forEach( original -> {
				Programacao nova = gravaNovaProgramacao( ambienteAlvo, new Programacao( ambienteAlvo, original.getDiaSemana(), original.getHoraInicio(), false ), false);
				gravaGeneros( original.getGeneros(), nova);
			});
		});
		
	}

	


	public boolean gravaGenerosProgramacaoDiaInteiro( Ambiente ambiente, DiaSemana dia, List<Genero> generos){
		return this.gravaGenerosProgramacaoDiaInteiro( ambiente, dia, generos, false );
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
	public boolean gravaGenerosProgramacaoDiaInteiro( Ambiente ambiente, DiaSemana dia, List<Genero> generos, boolean custom )
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
				Programacao nova = gravaNovaProgramacao( ambiente, new Programacao( ambiente, dia, hora, custom ), false );
				
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
		
		p.setCustom( dto.getCustom() );
		
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
		List<Transmissao> transmissoesTocadas = transmissaoRepo.findByAmbienteAndStatusPlaybackAndDiaPlayBetweenAndMidiaNotNull( ambiente, StatusPlayback.FIM, UtilsDates.asUtilMidnightDate( hoje ), UtilsDates.asUtilLastSecondDate( hoje ) );
		
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
	public Transmissao getTransmissaoAoVivo( Ambiente ambiente, UsuarioAmbienteDTO usuAmb )
	{
		Transmissao result = getTransmissaoAtual( ambiente ); 
		
		// Só vai incrementar para música seguinte se for um player legítimo... simulação não vai mudar. Vai pegar pela posição
		if (  result != null && result.getIdTransmissao() != null && usuAmb.isPlayer() ){
			transmissaoRepo.setLinkInativoAnteriores( ambiente, result.getIdTransmissao() );
		
			transmissaoRepo.setStatus( StatusPlayback.TOCANDO, result.getIdTransmissao() );
		}

		return result;
	}


	@Transactional
	public Transmissao getTransmissaoAoVivoSkipForward( Long idTransmissaoAtual, UsuarioAmbienteDTO usuAmb )
	{
		Ambiente ambiente = usuAmb.getAmbiente();
		
		Transmissao atual = null;
		
		if ( idTransmissaoAtual == null || idTransmissaoAtual.equals( 0L ) )
			atual = getTransmissaoAtual( ambiente ); 
		else
			atual = transmissaoRepo.findOne( idTransmissaoAtual );
		
		if ( atual == null )
			throw new RuntimeException("Transmissão atual não encontrada!");
		
		Transmissao result = getProximaTransmissaoPeloIdAtual( ambiente, atual );

		Long idTransmissao = null;
		
		if ( result == null )
			idTransmissao = atual.getIdTransmissao();
		else
			idTransmissao = result.getIdTransmissao();

		// Só vai incrementar para música seguinte se for um player legítimo... simulação não vai mudar. Vai pegar pela posição
		if ( idTransmissao != null && usuAmb.isPlayer() ){
			transmissaoRepo.setLinkInativoAnteriores( ambiente, idTransmissao );
		
			transmissaoRepo.setStatus( StatusPlayback.FIM, atual.getIdTransmissao() );
			transmissaoRepo.setStatus( StatusPlayback.TOCANDO, result.getIdTransmissao() );
		}

		//TODO: gerar nova midia incremental aqui
		
		return result;
	}
	
	
	
	@Transactional
	public void geraTransmissao( Ambiente ambiente )
	{

		// melhorar... se estiver quase acabando o dia.. gerar para o dia posterior também

		// Gerar sempre....  inativando os registros de transmissão anteriores		
		int ignoradas = transmissaoRepo.setStatusIgnoradas( ambiente ); // o que não tocou não será mais tocado... vou gerar uma nova playlist
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
			
			printaInformacoes( generosSet, dto );

			// algoritmo do spotify
			applySpotifyShufflePorArtista( dto );
			
			// Essa foi uma tentativa de melhorar o espalhamento dos gêneros... melhor que isso só se o carregamento for equivalente ( trazer mais ou menos a mesma quantidade de músicas )
			applySpotifyShufflePorGeneros( dto );

			applyMergeBlocos( ambiente, dto );

			// imprimindo
//			dto.getMidias().forEach( m -> {
//				logger.info( m.toString() );
//			});
			
//			validaClusters( dto );

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
		List<Midia> midias = dto.getMidias().stream().filter( m -> StringUtils.isNotBlank( m.getArtist() ) ).collect( Collectors.toList() );
		
		for ( int i = 0; i < midias.size(); i++ )
		{
			Midia atual = midias.get( i );
			
			Midia next = null;
			
			Midia nextnext = null;
			
			if ( i+1 < midias.size() )
				next = midias.get( i+1 );
			
			if ( i+2 < midias.size() )
				nextnext = midias.get( i+2 );

//			if ( next != null && atual.getGeneros() != null && StringUtils.equals( atual.getGeneros().get( 0 ).getNome(), next.getGeneros().get( 0 ).getNome() ) ){
//				System.out.println( String.format( "Gênero %s está próximo em ids ( %d , %d ) ", atual.getGeneros().get( 0 ).getNome(), atual.getIdMidia(), next.getIdMidia()) );
//				logger.info( String.format( "Gênero %s está próximo em ids ( %d , %d ) ", atual.getGeneros().get( 0 ).getNome(), atual.getIdMidia(), next.getIdMidia()) );
//			}
			
//			if ( next != null && StringUtils.equals( atual.getArtist(), next.getArtist() ) )
//				System.out.println( String.format( "Artista %s está próximo em ids ( %d , %d ) ", atual.getArtist(), atual.getIdMidia(), next.getIdMidia()) );
////			
//			if ( nextnext != null && StringUtils.equals( atual.getArtist(), nextnext.getArtist() ) )
//				System.out.println( String.format( "Artista %s está próximo (2) em ids ( %d , %d ) ", atual.getArtist(), atual.getIdMidia(), nextnext.getIdMidia()) );
				
		}
	}



	private void printaInformacoes( Set<Genero> generosSet, ProgramacaoListMidiaListDTO dto )
	{
		Integer duracaoTotal = dto.getMidias().stream().mapToInt( Midia::getDuracao ).sum();

		logger.info( "_______________" );
		

		logger.info( generosSet.toString() );
		logger.info( dto.getMidias().size() );
		logger.info( duracaoTotal + " segundos " );
		logger.info( ( duracaoTotal / 60 ) + " minutos " );
		logger.info( ( ( duracaoTotal / 60 ) / 60 )+ " horas " );
		logger.info( dto.getProgramacoes().size() + " programações é (1 hora cada) : total " + dto.getProgramacoes().size() + " horas " );

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
	
	
	private String fmtSilencio(TamanhoSilencioMidia tamanho){
		if ( tamanho == null || tamanho.equals( TamanhoSilencioMidia.NENHUM ) )
			return "";
		else 
			return String.format( "Silêncio (%s)", tamanho.getDescricao() );
	}


	public List<String> getExemploSequenciaBlocoComMusicas( Bloco bloco ){
		
		List<String> result = new ArrayList<String>();
		
		PosicaoVinheta posicaoVinheta = bloco.getPosicaoVinheta();
		PosicaoComercial posicaoComercial = bloco.getPosicaoComercial();
		PosicaoSilencio posicaoSilencio = bloco.getPosicaoSilencio();
		
		TamanhoSilencioMidia tamanhoSilencio = bloco.getTamanhoSilencio();
		
		if ( posicaoVinheta == null || posicaoComercial == null )
			return null;
		
		if ( bloco.getQtdMusicas() == null || bloco.getQtdComerciais() == null )
			return null;
		
		if ( posicaoSilencio == null )
			posicaoSilencio = PosicaoSilencio.NAO_INCLUIR;
		
		if ( tamanhoSilencio == null || tamanhoSilencio.equals( TamanhoSilencioMidia.NENHUM ))
			posicaoSilencio = PosicaoSilencio.NAO_INCLUIR;
		
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

				if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_CADA_MUSICA ) ) 
					result.add( "Vinheta" );

				if ( posicaoSilencio.equals( PosicaoSilencio.ANTES_MUSICA ))
					result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );
				
				result.add( geraStringExemploMusica( qtdMusicasSequencia ) );
				countMusicasInseridas += qtdMusicasSequencia;

				if ( posicaoSilencio.equals( PosicaoSilencio.DEPOIS_MUSICA ))
					result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );

				if ( posicaoComercial.equals( PosicaoComercial.DEPOIS_MUSICAS ) ) 
					insereExemploComercial( bloco, result, posicaoVinheta, posicaoSilencio, qtdComerciaisSequencia ); 

			}
				
			if ( stepInstitucionais > 0 && countMusicasInseridas % stepInstitucionais == 0 )  // Depois de n músicas
			{
				if ( posicaoComercial.equals( PosicaoComercial.ANTES_INSTITUCIONAL ) )
					insereExemploComercial( bloco, result, posicaoVinheta, posicaoSilencio, qtdComerciaisSequencia );

				if ( posicaoSilencio.equals( PosicaoSilencio.ANTES_INSTITUCIONAL ))
					result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );
				
				result.add("Institucional");

				if ( posicaoSilencio.equals( PosicaoSilencio.DEPOIS_INSTITUCIONAL ))
					result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );
				
				if ( posicaoComercial.equals( PosicaoComercial.DEPOIS_INSTITUCIONAL ) )
					insereExemploComercial( bloco, result, posicaoVinheta, posicaoSilencio, qtdComerciaisSequencia );
			}
			
			if ( stepProgrametes > 0 && countMusicasInseridas % stepProgrametes == 0 )  // Depois de n músicas
			{
				if ( posicaoComercial.equals( PosicaoComercial.ANTES_PROGRAMETE ) )
					insereExemploComercial( bloco, result, posicaoVinheta, posicaoSilencio, qtdComerciaisSequencia );
				
				if ( posicaoSilencio.equals( PosicaoSilencio.ANTES_PROGRAMETE ))
					result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );

				result.add( "Programete");

				if ( posicaoSilencio.equals( PosicaoSilencio.DEPOIS_PROGRAMETE ))
					result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );
				
				if ( posicaoComercial.equals( PosicaoComercial.DEPOIS_PROGRAMETE ) )
					insereExemploComercial( bloco, result, posicaoVinheta, posicaoSilencio, qtdComerciaisSequencia );
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




	private void insereExemploComercial( Bloco bloco, List<String> result, PosicaoVinheta posicaoVinheta, PosicaoSilencio posicaoSilencio, int qtdComerciaisSequencia )
	{
		if ( posicaoVinheta.equals( PosicaoVinheta.ANTES_BLOCO_COMERCIAL ) )
			result.add( "Vinheta" );
		
		if ( posicaoSilencio.equals( PosicaoSilencio.ANTES_BLOCO_COMERCIAL ))
			result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );
		
		result.add( geraStringExemploComercial( qtdComerciaisSequencia ) );
		
		if ( posicaoVinheta.equals( PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL ) )
			result.add( "Vinheta" );

		if ( posicaoSilencio.equals( PosicaoSilencio.DEPOIS_BLOCO_COMERCIAL ))
			result.add( fmtSilencio( bloco.getTamanhoSilencio() ) );
	}
	


	private void applyMergeBlocosSemMusica( Ambiente ambiente, ProgramacaoListMidiaListDTO dto )
	{
		Bloco bloco = blocoRepo.findByAmbiente( ambiente ); 
		
		PosicaoVinheta posicaoVinheta = bloco.getPosicaoVinheta();
		PosicaoComercial posicaoComercial = bloco.getPosicaoComercial();
		PosicaoSilencio posicaoSilencio = bloco.getPosicaoSilencio();
		
		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		
		Categoria vinheta = categoriaRepo.findByCodigo( Categoria.VINHETA );
		Categoria comercial = categoriaRepo.findByCodigo( Categoria.COMERCIAL );
		Categoria institucional = categoriaRepo.findByCodigo( Categoria.INSTITUCIONAL );
		Categoria programete = categoriaRepo.findByCodigo( Categoria.PROGRAMETE );
		Categoria silencio = categoriaRepo.findByCodigo( Categoria.SILENCIO );
		
		ListaInesgotavelRandom liVinhetas = getListasRandomPorCategoria( ambiente, vinheta );
		ListaInesgotavelRandom liComerciais = getListasRandomPorCategoria( ambiente, comercial );
		ListaInesgotavelRandom liInstitucionais = getListasRandomPorCategoria( ambiente, institucional );
		ListaInesgotavelRandom liProgrametes = getListasRandomPorCategoria( ambiente, programete );
		ListaInesgotavel liOpcionais = getListaInesgotavelOpcionais( bloco );

		int qtdComerciaisSequencia = bloco.getQtdComerciais();
		int stepInstitucionais = bloco.getIndexInstitucionais();
		int stepProgrametes = bloco.getIndexProgrametes();
		int stepOpcionais = bloco.getIndexOpcionais();
		
		TamanhoSilencioMidia tamanhoSilencio = bloco.getTamanhoSilencio();

		Midia midiaSilencio = null;
		
		if ( tamanhoSilencio != null && !tamanhoSilencio.equals( TamanhoSilencioMidia.NENHUM ) ){
			midiaSilencio = tamanhoSilencio.getMidia();
			midiaSilencio.setCategoriaSelecionada( silencio );
		} else {
			// Se o tamanho estiver abaixo do ideal vou roubar e não deixar incluir
			posicaoSilencio = PosicaoSilencio.NAO_INCLUIR;
		}
		
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
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.ANTES_INSTITUCIONAL ))
					addIfNotNull( novaListaMidias, midiaSilencio);
				
				// INSTITUCIONAL
				addIfNotNull( novaListaMidias, liInstitucionais.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );

				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.DEPOIS_INSTITUCIONAL ))
					addIfNotNull( novaListaMidias, midiaSilencio);
			}
			
			if ( stepProgrametes > 0 ) 
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.ANTES_PROGRAMETE ))
					addIfNotNull( novaListaMidias, midiaSilencio);

				// PROGRAMETE
				addIfNotNull( novaListaMidias, liProgrametes.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );

				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.DEPOIS_PROGRAMETE ))
					addIfNotNull( novaListaMidias, midiaSilencio);
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
	

	private boolean verificaMomentoSilencioMerge( PosicaoSilencio posicaoSilencioConfigurado, PosicaoSilencio posicaoSilencioVerificar )  
	{
		return ( posicaoSilencioConfigurado.equals( posicaoSilencioVerificar ) );
	}
	

	private ListaInesgotavelRandom getListasRandomPorCategoria( Ambiente ambiente, Categoria categoria ){

		MidiaFilter filter = MidiaFilter.create()
								.setAmbiente( ambiente )
								.setCategoria( categoria )
								.setAtivo( true )
								.setVerificaValidade( true )
								.setVerificaDiaAtual( true );

		List<Midia> midias = midiaService.findMidiasCategorias( filter );

		ListaInesgotavelRandom listaInesgotavelRandom = new ListaInesgotavelRandom( midias, categoria );
		
		return listaInesgotavelRandom;
	}

	
	private void applyMergeBlocos( Ambiente ambiente, ProgramacaoListMidiaListDTO dto )
	{
		ArrayDeque<Midia> musicasEmbaralhadas = new ArrayDeque<Midia>( dto.getMidias() );

		Bloco bloco = blocoRepo.findByAmbiente( ambiente ); 
		
		PosicaoVinheta posicaoVinheta = bloco.getPosicaoVinheta();
		PosicaoComercial posicaoComercial = bloco.getPosicaoComercial();
		PosicaoSilencio posicaoSilencio = bloco.getPosicaoSilencio();
		
		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		
		Categoria vinheta = categoriaRepo.findByCodigo( Categoria.VINHETA );
		Categoria comercial = categoriaRepo.findByCodigo( Categoria.COMERCIAL );
		Categoria institucional = categoriaRepo.findByCodigo( Categoria.INSTITUCIONAL );
		Categoria programete = categoriaRepo.findByCodigo( Categoria.PROGRAMETE );
		Categoria silencio = categoriaRepo.findByCodigo( Categoria.SILENCIO );
		
		ListaInesgotavelRandom liVinhetas = getListasRandomPorCategoria( ambiente, vinheta );
		ListaInesgotavelRandom liComerciais = getListasRandomPorCategoria( ambiente, comercial );
		ListaInesgotavelRandom liInstitucionais = getListasRandomPorCategoria( ambiente, institucional );
		ListaInesgotavelRandom liProgrametes = getListasRandomPorCategoria( ambiente, programete );
		ListaInesgotavel liOpcionais = getListaInesgotavelOpcionais( bloco );

		int qtdMusicasSequencia = bloco.getQtdMusicas();
		int qtdComerciaisSequencia = bloco.getQtdComerciais();
		
		int stepInstitucionais = bloco.getIndexInstitucionais();
		int stepProgrametes = bloco.getIndexProgrametes();
		int stepOpcionais = bloco.getIndexOpcionais();
		
		TamanhoSilencioMidia tamanhoSilencio = bloco.getTamanhoSilencio();

		Midia midiaSilencio = null;
		
		if ( tamanhoSilencio != null && !tamanhoSilencio.equals( TamanhoSilencioMidia.NENHUM ) ){
			midiaSilencio = tamanhoSilencio.getMidia();
			midiaSilencio.setCategoriaSelecionada( silencio );
		} else {
			// Se o tamanho estiver abaixo do ideal vou roubar e não deixar incluir
			posicaoSilencio = PosicaoSilencio.NAO_INCLUIR;
		}
			

		LinkedList<Midia> novaListaMidias = new LinkedList<Midia>();
		
		int countMusicasInseridas = 0;

		while ( !musicasEmbaralhadas.isEmpty() )
		{
			// Talvez mudar isso para o caso onde existam poucas músicas ficar em loop inserindo as mesmas músicas sempre
			List<Midia> sequenciaMusicas = consomeN( musicasEmbaralhadas, qtdMusicasSequencia );
			

			if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.ANTES_MUSICA ))
				addIfNotNull( novaListaMidias, midiaSilencio);

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

			if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.DEPOIS_MUSICA ))
				addIfNotNull( novaListaMidias, midiaSilencio);
				
			if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_MUSICAS, liComerciais ) )
				adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );

			
			if ( stepInstitucionais > 0 && countMusicasInseridas % stepInstitucionais == 0 )  // Depois de n músicas
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.ANTES_INSTITUCIONAL ))
					addIfNotNull( novaListaMidias, midiaSilencio);

				// INSTITUCIONAL
				addIfNotNull( novaListaMidias, liInstitucionais.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_INSTITUCIONAL, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );

				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.DEPOIS_INSTITUCIONAL ))
					addIfNotNull( novaListaMidias, midiaSilencio);
			}
			
			
			if ( stepProgrametes > 0 && countMusicasInseridas % stepProgrametes == 0 )  // Depois de n músicas
			{
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.ANTES_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );
				
				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.ANTES_PROGRAMETE ))
					addIfNotNull( novaListaMidias, midiaSilencio);

				// PROGRAMETE
				addIfNotNull( novaListaMidias, liProgrametes.getNextRandom( rnd ) );
				
				if ( verificaMomentoComercialMerge( posicaoComercial, PosicaoComercial.DEPOIS_PROGRAMETE, liComerciais ) )
					adicionaComercialMerge( posicaoVinheta, posicaoSilencio, midiaSilencio, rnd, liVinhetas, liComerciais, qtdComerciaisSequencia, novaListaMidias );

				if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.DEPOIS_PROGRAMETE ))
					addIfNotNull( novaListaMidias, midiaSilencio);
			}
				
			if ( stepOpcionais > 0 && countMusicasInseridas % stepOpcionais == 0 ) 
			{
				// OPCIONAL
				addIfNotNull( novaListaMidias, liOpcionais.getNextRandom( rnd ) );
			}
		}
		
		dto.setMidias( novaListaMidias );
	}



	private void adicionaComercialMerge( PosicaoVinheta posicaoVinheta, PosicaoSilencio posicaoSilencio, Midia midiaSilencio, ThreadLocalRandom rnd, ListaInesgotavelRandom blocoVinhetas, ListaInesgotavelRandom blocoComerciais, int qtdComerciaisSequencia, LinkedList<Midia> novaListaMidias )
	{
		if ( verificaMomentoVinhetaMerge( posicaoVinheta, PosicaoVinheta.ANTES_BLOCO_COMERCIAL, blocoVinhetas ) )
			addIfNotNull( novaListaMidias, blocoVinhetas.getNextRandom( rnd ) );

		if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.ANTES_BLOCO_COMERCIAL ))
			addIfNotNull( novaListaMidias, midiaSilencio);
		
		novaListaMidias.addAll( consomeNFromListaInesgotavel( blocoComerciais, qtdComerciaisSequencia, rnd ) );
		
		if ( verificaMomentoVinhetaMerge( posicaoVinheta, PosicaoVinheta.DEPOIS_BLOCO_COMERCIAL, blocoVinhetas ) )
			addIfNotNull( novaListaMidias, blocoVinhetas.getNextRandom( rnd ) );

		if ( verificaMomentoSilencioMerge( posicaoSilencio, PosicaoSilencio.DEPOIS_BLOCO_COMERCIAL ))
			addIfNotNull( novaListaMidias, midiaSilencio);
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
	
	
	
	
	
	private void applySpotifyShufflePorArtista( ProgramacaoListMidiaListDTO dto )
	{
		Double tamanhoLista = Integer.valueOf( dto.getMidias().size() ).doubleValue();

		Map<String, List<Midia>> musicasPorArtista = dto.getMidias().stream().collect( Collectors.groupingBy( Midia::getArtist ) );
		
		espalhaPosicaoShuffle( dto.getMusicaCategoria(), musicasPorArtista, tamanhoLista );
		
		Comparator<Midia> pelaPosicaoShuffle = ( m1, m2 ) -> m1.getPosicaoShuffle().compareTo( m2.getPosicaoShuffle() );
		
		dto.getMidias().sort( pelaPosicaoShuffle );
	}



	/**
	 * Vai selecionar apenas o primeiro gênero.
	 * Caso esteja em mais de um gênero não vai ser ponderado.
	 * 
	 * @param dto
	 */
	private void applySpotifyShufflePorGeneros( ProgramacaoListMidiaListDTO dto )
	{
		Double tamanhoLista = Integer.valueOf( dto.getMidias().size() ).doubleValue();

		Map<Genero, List<Midia>> musicasPorGeneros = new HashMap<Genero, List<Midia>>();
		
		for ( Midia m : dto.getMidias() ){
			
			Genero primeiroGenero = null;
			
			if ( m.getGeneros() != null && m.getGeneros().size() > 0 )
				primeiroGenero = m.getGeneros().get( 0 );
			
			List<Midia> subLista = musicasPorGeneros.get( primeiroGenero );
			
			if ( subLista == null ){
				subLista = new ArrayList<Midia>();
				musicasPorGeneros.put( primeiroGenero, subLista );
			}
			
			subLista.add( m );
		}
		
		espalhaPosicaoShuffle( dto.getMusicaCategoria(), musicasPorGeneros, tamanhoLista );
		
		Comparator<Midia> pelaPosicaoShuffle = ( m1, m2 ) -> m1.getPosicaoShuffle().compareTo( m2.getPosicaoShuffle() );
		
		dto.getMidias().sort( pelaPosicaoShuffle );
	}



	/**
	 * Básico do algorítmo
	 * 
	 * <ul>
	 *   <li>Precisa do tamanho da lista total
	 *   <li>Agrupa músicas por alguma "coisa" ( Artista, Gênero )
	 *   <li>Para cada "coisa" vai distanciar as músicas igualmente com um random número que não supera o tamanho total da lista
	 *   <li>"Projeta" cada uma das listas distanciadas em uma lista resultado. Como se estivesse combinando baralhos com espaçamentos diferentes.
	 * </ul>
	 * 
	 * 
	 * @param dto
	 */
	private <T> void espalhaPosicaoShuffle( Categoria categoria, Map<T, List<Midia>> musicasPorGrupo, Double tamanhoLista )
	{
		ThreadLocalRandom rnd = ThreadLocalRandom.current();

		musicasPorGrupo.forEach( ( grupo, subListaMusicas ) -> {

			double tamanhoListaLocal = Integer.valueOf( subListaMusicas.size() ).doubleValue();
			double distancia = tamanhoLista / tamanhoListaLocal;
			double limitePadding = tamanhoLista - ( distancia * tamanhoListaLocal );  
			double initialPadding = 1 + (limitePadding - 1) * rnd.nextDouble();
			double posicao = initialPadding;
			
			for ( Midia musica : subListaMusicas )
			{
				// Marcando a mídia como música... faço isso nesse ponto para não ter que fazer OUTRO for... estou aproveitando esse LOOP
				musica.setCategoriaSelecionada( categoria );
				
				musica.setPosicaoShuffle( Double.valueOf( posicao ) );
				
				int coinSignal = rnd.nextInt(2);
				
				double randomAmount = rnd.nextDouble(0.12);
				
				if ( coinSignal == 0 )
					posicao += ( distancia + ( distancia * randomAmount ) );
				else
					posicao += ( distancia - ( distancia * randomAmount ) );
			};
		});
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

				if ( !Categoria.SILENCIO.equals( midia.getCategoriaSelecionada().getCodigo() ) )
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
				// Mesmo que seja um silêncio a midia não pode ser nula.... pelo menos não nesse passo
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
	
	
	
	
	public Set<Genero> getGenerosProgramacaoMusicalTotal(Ambiente ambiente){
		
		List<ProgramacaoGenero> progGeneros = programacaoGeneroRepo.findByProgramacao_AmbienteAndProgramacao_AtivoTrue( ambiente );
		
		Set<Genero> result = null;

		result = progGeneros.stream().map( p -> p.getGenero() ).collect( Collectors.toSet() );
		
		return result;
	}

	
	@Transactional
	public void saveProgramacaoMusicalTotal(Ambiente ambiente, GeneroListDTO generosList){
		
		for (DiaSemana dia : DiaSemana.values() ){
			this.gravaGenerosProgramacaoDiaInteiro( ambiente, dia, generosList.getLista(), true );
		}
	}
	

	public boolean autenticarProgramacaoTotal( Ambiente ambiente, String pass ){
		
		AmbienteConfiguracao config = ambienteConfigRepo.findByAmbiente( ambiente );
		
		if ( config == null )
			throw new RuntimeException("Configuração não encontrada!");
		
		return config.getSenhaProgMusicalPlayer() != null && config.getSenhaProgMusicalPlayer().equals( pass );
	}


	public void cadastrarSenhaProgramacaoTotal( Ambiente ambiente, CadastrarSenhaPlayerDTO cadastrarSenhaDTO){
		
		AmbienteConfiguracao config = ambienteConfigRepo.findByAmbiente( ambiente );
		
		if ( config == null )
			throw new RuntimeException("Configuração não encontrada!");
		
		config.setSenhaProgMusicalPlayer( cadastrarSenhaDTO.getPassword() );
		
		ambienteConfigRepo.save( config );
	}
	
	
	@Transactional
	public void inativarMidias(Ambiente ambiente, MidiaListDTO midiaListDTO){
		
		midiaService.atualizaMidiasInativasBlock( midiaListDTO );
		geraTransmissao( ambiente );
	}
	
	
	
	/**
	 * Por convenção o início do metodo como "filtra" implica que ele é utilizado pela tela (por isso também ele retorna um Page)
	 * Métodos que são usados por business serão iniciados por "find" e retornam List.
	 * 
	 * @param pageable
	 * @param filter
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	@Transactional
	public Page<Transmissao> filtraTransmissoes( Pageable pageable, TransmissaoFilter filter ){
		
		Session session = entityManager.unwrap( Session.class );
		
		Criteria critCount = createCriteriaTransmissoes( filter, session );
		critCount.setProjection( Projections.rowCount() );
		Long total = (Long)critCount.uniqueResult();

		Criteria crit = createCriteriaTransmissoes( filter, session );
		
		if ( pageable != null ){
			crit.setMaxResults( pageable.getPageSize() );
			crit.setFirstResult( pageable.getOffset() );
		}
		
		crit.addOrder( Order.asc( "diaPlay" ) );
		crit.addOrder( Order.asc( "programacao.idProgramacao" ) );
		crit.addOrder( Order.asc( "posicaoplay" ) );

		List<Transmissao> listTransmissoes = crit.list();

		PageImpl<Transmissao> paginaTransmissoes = new PageImpl<Transmissao>( listTransmissoes, pageable, total );
		
		return paginaTransmissoes;
	}
	

	
	@SuppressWarnings( "unchecked" )
	@Transactional
	public List<Transmissao> findTransmissoes( TransmissaoFilter filter ){
		
		Session session = entityManager.unwrap( Session.class );
		
		Criteria crit = createCriteriaTransmissoes( filter, session );
		
		List<Transmissao> result = crit.list();
		
		return result;
	}



	private Criteria createCriteriaTransmissoes( TransmissaoFilter filter, Session session )
	{
		Criteria crit = session.createCriteria( Transmissao.class );
		
		boolean isIdCategoria = ( filter.getCategoria() != null && filter.getCategoria().getIdCategoria() != null && filter.getCategoria().getIdCategoria() > 0 );
		boolean isCodigoCategoria = ( filter.getCategoria() != null && filter.getCategoria().getIdCategoria() == null && StringUtils.isNotBlank( filter.getCategoria().getCodigo() ) );
		
		if ( isIdCategoria || isCodigoCategoria ){
			if ( isIdCategoria )
				crit.add( Restrictions.eq( "categoria.idCategoria", filter.getCategoria().getIdCategoria() ) );
			else if ( isCodigoCategoria )
				crit.add( Restrictions.eq( "categoria.codigo", filter.getCategoria().getCodigo() ) );
		}

		if ( filter.getAmbiente() != null )
			crit.add( Restrictions.eq( "ambiente", filter.getAmbiente() ) );
		
		crit.add( Restrictions.between( "diaPlay", filter.getDataInicio(), filter.getDataFim() ) );
		
		if (filter.isFuturo())
			crit.add( Restrictions.in( "statusPlayback", Arrays.asList( StatusPlayback.GERADA, StatusPlayback.NAFILA, StatusPlayback.TOCANDO, StatusPlayback.FIM ) ) );
		else
			crit.add( Restrictions.in( "statusPlayback", Arrays.asList( StatusPlayback.TOCANDO, StatusPlayback.FIM ) ) );

		return crit;
	}
	
	
	@Transactional
	public String getCSVRelatorioTransmissoes( TransmissaoFilter filter ){

		List<Transmissao> lista = findTransmissoes( filter );
		
		String nl = SystemUtils.LINE_SEPARATOR;
		
		StringBuilder result = new StringBuilder();

		String pattern = "dd/MM/yyyy HH:mm";
		
		Date agora = new Date();
		String dataFmt = new SimpleDateFormat( pattern ).format( agora );
				
		result.append( "Relatório do Ambiente : ").append(filter.getAmbiente().getNome()).append(nl);
		result.append( "Gerado às ").append(dataFmt).append(nl).append(nl);
		result.append( "Categoria;Data/Hora prevista;Data/Hora término;Status;Descrição;Arquivo;").append(nl);
		
		for (Transmissao t : lista ){
			
			result.append( t.getCategoria().getDescricao() ).append(";");
			result.append( UtilsStr.notNull( UtilsDates.format( t.getDataPrevisaoPlay(), pattern ) ) ).append(";");
			result.append( UtilsStr.notNull( UtilsDates.format( t.getDataFinishPlay(), pattern ) ) ).append(";");
			result.append( t.getStatusPlayback().getDescricao() ).append(";");
			result.append( UtilsStr.notNull( t.getMidia().getDescricao() ) ).append(";");
			result.append( t.getMidia().getNome()).append(";");
			
			result.append( nl );
		}
		
		return result.toString();
	}
	
}
