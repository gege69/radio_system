package br.com.radio.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.enumeration.StatusPlayback;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.Programacao;
import br.com.radio.model.ProgramacaoGenero;
import br.com.radio.model.Transmissao;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.ProgramacaoGeneroRepository;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.repository.TransmissaoRepository;
import br.com.radio.service.programacaomusical.ProgramacaoListMidiaListDTO;
import br.com.radio.util.UtilsDates;


@Service
public class ProgramacaoMusicalService {
	
	@Autowired
	private ProgramacaoRepository programacaoRepo;
	
	@Autowired
	private ProgramacaoGeneroRepository programacaoGeneroRepo;
	
	@Autowired
	private MidiaRepository midiaRepo;

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EntityManager em;

	@Autowired 
	private TransmissaoRepository transmissaoRepo;
	
	
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
		
		LocalDateTime data = LocalDateTime.now();
		
		DiaSemana diaSemana = DiaSemana.getByIndex( data.getDayOfWeek().getValue() );

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
			List<Midia> midias = midiaRepo.findByAmbientesAndCategoriasAndGenerosInGroupBy( ambiente, categoria, generosSet );

			ProgramacaoListMidiaListDTO dto = new ProgramacaoListMidiaListDTO( programacaoList, midias );
			
			result.put( generosSet, dto );
		});
		
		return result;
	}
	
	
	@Transactional
	public void geraTransmissao( Ambiente ambiente, String urlRequest )
	{
		Map<Set<Genero>, ProgramacaoListMidiaListDTO> musicasPorGenero = selecaoMusicas( ambiente );
		
		musicasPorGenero.forEach( ( generosSet, dto ) -> {
			
			printaInformacoes( generosSet, dto );

			// algoritmo do spotify
			applySpotifyShuffle( dto );
			
			// imprimindo
			dto.getMidias().forEach( m -> {
				System.out.println( m.toString() );
			});

			validaClusters( dto );
			
			
			// caso tenha alguma parametrização o URL request fazer antes... o método de consumir não precisa saber só gravar.
			consomeMusicas( ambiente, dto, urlRequest );
		});
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
			
			if ( next != null && StringUtils.equals( atual.getArtist(), next.getArtist() ) )
				System.out.println( String.format( "Artista %s está próximo em ids ( %d , %d ) ", atual.getArtist(), atual.getIdMidia(), next.getIdMidia()) );
			
			if ( nextnext != null && StringUtils.equals( atual.getArtist(), nextnext.getArtist() ) )
				System.out.println( String.format( "Artista %s está próximo (2) em ids ( %d , %d ) ", atual.getArtist(), atual.getIdMidia(), nextnext.getIdMidia()) );
				
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

	
	private void applySpotifyShuffle( ProgramacaoListMidiaListDTO dto )
	{
		
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
	
	
	public void consomeMusicas( Ambiente ambiente, ProgramacaoListMidiaListDTO dto, String urlRequest )
	{
		List<Programacao> programacoes = dto.getProgramacoes();
		
		// Na verdade é bagunçadas.. porque nesse ponto o shuffle já deveria ter ocorrido
		List<Midia> midiasOrdenadas = dto.getMidias();

		String url = urlRequest + "/api/ambientes/"+ ambiente.getIdAmbiente() +"/transmissoes/" ;
		
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
		
		int batchNumber = 0;
		int index = 0;
		
		for ( Programacao prog : programacoes )
		{
			Long ordemplay = 1l;
			
			// ao invés de acoplar isso no for... fazer um método que tem o indice de inicio e apenas retorna uma sublista com o total da duração em segundos ( 1 hora )
			for ( int i = index; i < midiasOrdenadas.size(); i++ )
			{
				Midia midia = midiasOrdenadas.get( i );
				
				Transmissao transmissao = new Transmissao();
				
				transmissao.setAmbiente( prog.getAmbiente() );
				transmissao.setDataCriacao( new Date() );
				transmissao.setDataPrevisaoPlay( null ); // pensar nisso
				transmissao.setDuracao( midia.getDuracao() );
				transmissao.setLinkativo( true );
				transmissao.setMidia( midia );
				transmissao.setProgramacao( prog );
				transmissao.setStatusPlayback( StatusPlayback.GERADA );
				transmissao.setOrdemPlay( ordemplay++ );

				transmissaoRepo.save( transmissao );
				
				batchNumber++;
				
				if ( index % batchNumber == 0 )
				{
					batchNumber = 0;
					em.flush();
					em.clear();
				}
				
				index = i + 1;
			}
		}

		transmissaoRepo.setLinkFor( url );
		
		System.out.println("finsih");
	}
	

}
