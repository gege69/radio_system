package br.com.radio.boot;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.jdk8.LocalDateKitCalculatorsFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.threeten.extra.Interval;

import br.com.radio.dto.midia.RelatorioMidiaGeneroVO;
import br.com.radio.enumeration.DiaSemana;
import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;
import br.com.radio.model.CondicaoComercial;
import br.com.radio.model.Genero;
import br.com.radio.model.HistoricoStatusAmbiente;
import br.com.radio.model.Midia;
import br.com.radio.model.Programacao;
import br.com.radio.model.Titulo;
import br.com.radio.model.Usuario;
import br.com.radio.model.fixture.FixtureAmbiente;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.CondicaoComercialRepository;
import br.com.radio.repository.MidiaGeneroRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.ProgramacaoGeneroRepository;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.service.AmbienteService;
import br.com.radio.service.MidiaService;
import br.com.radio.service.ProgramacaoMusicalService;
import br.com.radio.service.programacaomusical.ProgramacaoListMidiaListDTO;
import br.com.radio.util.UtilsDates;
import de.jollyday.Holiday;
import de.jollyday.HolidayManager;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */






//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@ActiveProfiles({"default"})
//@EnableConfigurationProperties
//@EnableTransactionManagement
public class Application {
				
	public static void main(String[] aaaa)
	{
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
		Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
		
//		testeCobranca( ctx );
		
//		testaCriteriaListaMusica( ctx );
		
//		testaRelatorioGeneros( ctx );
		
		testeAlternanciaGeneros( ctx );
	}




	private static void testeEspelhar( ApplicationContext ctx ){
		
		ProgramacaoMusicalService pms = ctx.getBean( ProgramacaoMusicalService.class );
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
		ProgramacaoRepository programacaoRepo = ctx.getBean( ProgramacaoRepository.class );
		ProgramacaoGeneroRepository progGenRepo = ctx.getBean( ProgramacaoGeneroRepository.class );
		
		Ambiente ambiente = ambRepo.findOne( 1L );

		Ambiente ambienteTestePazin = ambRepo.findOne( 9L );

		for ( DiaSemana dia : DiaSemana.values() ) {
			
			List<Programacao> programacoesDoDia = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, dia );
			
			programacoesDoDia.forEach( prog -> {
				prog.setAtivo( false );
				prog.setDataInativo( new Date() );
			});
			
		}

		
//		programacaoRepo.save( programacoesDoDia );
		



		
	}




	private static void testeAlternanciaGeneros( ApplicationContext ctx ){
		
		ProgramacaoMusicalService pms = ctx.getBean( ProgramacaoMusicalService.class );
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
		
		Ambiente ambiente = ambRepo.findOne( 1L );
		
//		Map<Set<Genero>, ProgramacaoListMidiaListDTO> musicasPorGenero = pms.selecaoMusicas( ambiente );
////		
//		for ( ProgramacaoListMidiaListDTO dto : musicasPorGenero.values() ){
//
//			dto.getMidias().forEach( m -> {
//				if ( m.getNome().equals("091 Candy Dulfer - Funkyness.mp3") )
//					System.out.println(m);
//			});
//			
//			Map<Set<Genero>, AtomicLong> totaisPorGeneros = new HashMap<Set<Genero>, AtomicLong>();
//			
//			for ( Midia m : dto.getMidias() ){
//				
//				Set<Genero> s = new HashSet<>(m.getGeneros());
//				
//				AtomicLong total = totaisPorGeneros.get( s );
//				
//				if ( total == null ){
//					total = new AtomicLong(0);
//					totaisPorGeneros.put( s, total );
//				}
//				total.incrementAndGet();
//			}
//			
//			totaisPorGeneros.forEach( ( s, l ) -> {
//				System.out.println( ""+l+" | " + s);
//			});
//		}


		pms.geraTransmissao( ambiente );

		
	}









	
	private static void testaRelatorioGeneros( ApplicationContext ctx ){
		
		MidiaGeneroRepository mgr = ctx.getBean( MidiaGeneroRepository.class );
		
		List<RelatorioMidiaGeneroVO> x =  mgr.findRelatorioGeneros();
		
		x.forEach( r -> System.out.println(r) );
		
	}
	
	private static void testaCriteriaListaMusica( ApplicationContext ctx ){
		
		MidiaService m = ctx.getBean( MidiaService.class );
		MidiaRepository mr = ctx.getBean( MidiaRepository.class );
		CategoriaRepository catRepo = ctx.getBean( CategoriaRepository.class );
		
		Pageable pageable = new PageRequest( 0, 99, Sort.Direction.fromStringOrNull( "asc" ), "nome" );
		
//		MidiaFilter filter = MidiaFilter.create()
//								.setCategoria( catRepo.findByCodigo( Categoria.MUSICA ) )
//								.setSearch( "Forró" )
//								.setIncluiGeneros( true );
//
//		Page<Midia> pagina = m.filtraMusicas( pageable, filter );
		
		Page<Midia> pagina = mr.findByCustomSearch( pageable, StringUtils.lowerCase( "%Joe%" ) );
		
		List<Midia> lista = pagina.getContent();
		
		lista.forEach( musica -> System.out.println(musica.toString()) );
		
	}


	private static void testeCobranca( ApplicationContext ctx ){
		
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
		ClienteRepository cliRepo = ctx.getBean( ClienteRepository.class );
		UsuarioRepository usuRepo = ctx.getBean( UsuarioRepository.class );
		CondicaoComercialRepository condRepo = ctx.getBean( CondicaoComercialRepository.class );

		Cliente cliente = cliRepo.findByCodigo( "eterion" );
		Usuario usuario = usuRepo.findByLogin( "fpazin" );

		// Verificar se está na hora de rodar a cobrança pelo dia vencimento do cliente
		
//		List<Ambiente> ambientes = ambRepo.findByCliente( cliente );
		List<Ambiente> ambientes = new ArrayList<Ambiente>();
		
		Ambiente amb1 = FixtureAmbiente.criaAmbiente( cliente, "amb1" );
		Ambiente amb2 = FixtureAmbiente.criaAmbiente( cliente, "amb2" );
		Ambiente amb3 = FixtureAmbiente.criaAmbiente( cliente, "amb3" );
		
		ambientes.add( amb1 );
		ambientes.add( amb2 );
		ambientes.add( amb3 );
		
		List<HistoricoStatusAmbiente> historicos = new ArrayList<HistoricoStatusAmbiente>();
		
		amb1.setStatus( StatusAmbiente.INATIVO );

		LocalDate x = LocalDate.of( 2016, 05, 15 ); 
		LocalDate y = LocalDate.of( 2016, 6, 25 );
	
		Long dias = ChronoUnit.DAYS.between( x, y );
		
		historicos.add( new HistoricoStatusAmbiente( amb1, x, y, dias.intValue(), StatusAmbiente.ATIVO ) );
		historicos.add( new HistoricoStatusAmbiente( amb1, LocalDate.of( 2016, 06, 26 ), StatusAmbiente.INATIVO ) );

		historicos.add( new HistoricoStatusAmbiente( amb2, LocalDate.of( 2016, 05, 15 ), StatusAmbiente.ATIVO) );
		historicos.add( new HistoricoStatusAmbiente( amb3, LocalDate.of( 2016, 06, 20 ), StatusAmbiente.ATIVO) );
		
		Map<Ambiente, List<HistoricoStatusAmbiente>> historicosPorAmbiente = historicos.stream().collect( Collectors.groupingBy( HistoricoStatusAmbiente::getAmbiente ) );
		
		List<CondicaoComercial> condicoesDoCliente = condRepo.findByCliente( cliente );
		
		List<CondicaoComercial> condicoesDosAmbientes = condicoesDoCliente.stream().filter( c -> c.getTipoTaxa().getPorambiente() ).collect( Collectors.toList() );
		List<CondicaoComercial> condicoesGeral = condicoesDoCliente.stream().filter( c -> !c.getTipoTaxa().getPorambiente() ).collect( Collectors.toList() );

		List<Ambiente> ambientesFull = ambientes.stream().filter( a -> isFull( a, historicosPorAmbiente.get( a ) ) ).collect( Collectors.toList() );
		List<Ambiente> ambientesProporcionais = ambientes.stream().filter( a -> !isFull( a, historicosPorAmbiente.get( a ) ) ).collect( Collectors.toList() );
		
		final Map<CondicaoComercial, List<Ambiente>> mapAmbientesPorCondicao = new HashMap<CondicaoComercial, List<Ambiente>>();
		
		condicoesDosAmbientes.forEach( condicao -> {
			mapAmbientesPorCondicao.put( condicao, ambientesFull );
		});
		
		Titulo tit = new Titulo();
		BigDecimal liquido = BigDecimal.ZERO;
		
		StringBuilder strBuild = new StringBuilder();
		
		for ( Entry<CondicaoComercial, List<Ambiente>> entry : mapAmbientesPorCondicao.entrySet() ){
			
			CondicaoComercial cond = entry.getKey();
			List<Ambiente> ambList = entry.getValue();
			
			BigDecimal valorCobrancas =  cond.getValor().multiply( new BigDecimal( ambList.size() ) );
			
			strBuild.append( String.format( "Cobrança de %.2f da condição comercial '%s' para cada um dos ambientes : ", valorCobrancas, cond.getTipoTaxa().getDescricao() ) );

			String separados = ambList.stream()
				     .map(amb -> amb.getNome())
				     .collect(Collectors.joining(", ", "[", "]"));	

			strBuild.append( separados );
			strBuild.append(System.lineSeparator());
			
			liquido = liquido.add( valorCobrancas );
		}
		
		StringBuilder strBuildGeral = new StringBuilder();
		
		for ( CondicaoComercial condGeral : condicoesGeral ){
			
			strBuildGeral.append(System.lineSeparator());
			strBuildGeral.append( String.format( "Cobrança de %.2f da condição comercial '%s' para o cliente.", condGeral.getValor().doubleValue(), condGeral.getTipoTaxa().getDescricao() ) );
			
			liquido = liquido.add( condGeral.getValor() );
		}

		System.out.println(strBuild.toString());
		System.out.println(strBuildGeral.toString());
		System.out.println(liquido);
		
		tit.setHistorico( strBuild.toString() );
		tit.setValorLiquido( liquido );
		
		System.out.println(tit);
	}
	
	
	private static boolean temPeriodoInativo( Ambiente ambiente, int mes, List<HistoricoStatusAmbiente> historicos ){
		
		boolean result = false;
		
		if ( ambiente.getStatus().equals( StatusAmbiente.INATIVO ) )
			result = true;
		else {
			
			final LocalDate inicioMes = LocalDate.now().withMonth( mes ).withDayOfMonth( 1 );
			final LocalDate finalMes = inicioMes.withDayOfMonth( inicioMes.lengthOfMonth() );
			
			final Interval intervaloMes = Interval.of( Instant.from( inicioMes ), Instant.from( finalMes ) );
			
			List<HistoricoStatusAmbiente> periodosInativos = historicos.stream().filter( h -> h.getStatus().equals( StatusAmbiente.INATIVO ) ).collect( Collectors.toList() );
			
			periodosInativos.forEach( p -> {
				if ( p.getDataFim() == null )
					p.setDataFim( new Date() );
				
				LocalDate ini = p.getDataInicioAsLocalDate();
				LocalDate fim = p.getDataFimAsLocalDate();
	
				Interval intervaloInativo = Interval.of( Instant.from( ini ), Instant.from( fim ) );
				
				if ( intervaloInativo.overlaps( intervaloMes ) ){
					
				}
			});
			
		}
		
		return result;
	}
	  
	private static boolean isFull( Ambiente ambiente, List<HistoricoStatusAmbiente> historicos ){
		return true;
	}
	
//	private static void diasAtivosNoMes( int mes, List<HistoricoStatusAmbiente> historicos ){
//		
//		historicos.stream().filter( h -> { 
//
//		});
//		
//	}
	
	
	
	private static void testeDiasUteis( ApplicationContext ctx ){
		
//		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
//		ClienteRepository cliRepo = ctx.getBean( ClienteRepository.class );
//		
//		Cliente cliente = cliRepo.findByCodigo( "eterion" );
//		
//		List<Ambiente> ambientes = ambRepo.findByCliente( cliente );
		
		HolidayManager gerenciadorDeFeriados = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);

		Set<Holiday> feriados = gerenciadorDeFeriados.getHolidays(LocalDate.now().getYear());
		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();
		for (Holiday h : feriados) {
			dataDosFeriados.add(UtilsDates.asLocalDate( h.getDate().toDate() ));
			System.out.println( h.getDate().toString( "dd/MM/yyyy" ) );
		}	
		
		// popula com os feriados brasileiros
		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);	

		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", calendarioDeFeriados);

		DateCalculator<LocalDate> calendario = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("BR", HolidayHandlerType.FORWARD);
		
		LocalDate dataInicial = LocalDate.of( 2016, 05, 15 );
		LocalDate outraData = LocalDate.of( 2016, 12, 25 );
		
		System.out.println(calendario.isNonWorkingDay(LocalDate.now()));
		System.out.println(calendario.isNonWorkingDay(dataInicial));
		System.out.println(calendario.isNonWorkingDay(outraData));
		
		int result = getDiasNaoUteis( dataInicial, outraData, calendario );
		
		System.out.println(result);
		 
		result = getDiasUteis( dataInicial, outraData, calendario );
		
		System.out.println(result);
	}
	
	public static int getDiasNaoUteis(LocalDate from, LocalDate to, DateCalculator<LocalDate> calendario){
		
		int diasNaoUteis = 0;
		 
		LocalDate dataInicialTemporaria = from;
		LocalDate dataFinalTemporaria = to;
		 
		while (!dataInicialTemporaria.isAfter(dataFinalTemporaria)) {
		    if (calendario.isNonWorkingDay(dataInicialTemporaria)) {
		       diasNaoUteis++;
		    }
		    dataInicialTemporaria = dataInicialTemporaria.plusDays(1);
		}	
		
		return diasNaoUteis;
	}
	

	public static int getDiasUteis(LocalDate from, LocalDate to, DateCalculator<LocalDate> calendario){
		
		int diasUteis = 0;
		 
		LocalDate dataInicialTemporaria = from;
		LocalDate dataFinalTemporaria = to;
		 
		while (!dataInicialTemporaria.isAfter(dataFinalTemporaria)) {
		    if (!calendario.isNonWorkingDay(dataInicialTemporaria)) {
		       diasUteis++;
		    }
		    dataInicialTemporaria = dataInicialTemporaria.plusDays(1);
		}	
		
		return diasUteis;
	}
	
	
	
	private static void criaProgramacaoVariosAmbientes( ApplicationContext ctx )
	{
//		UsuarioRepository usuRepo = ctx.getBean( UsuarioRepository.class );
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
//		AmbienteService ambienteService = ctx.getBean( AmbienteService.class );
		ProgramacaoMusicalService progMusicService = ctx.getBean( ProgramacaoMusicalService.class );
		
//		Usuario usu = usuRepo.findByLogin( "fpazin" );
	
		Integer total = 200;

		Integer inicio = 5;

		for ( int i = inicio; i < total; i++ )
		{

			try
			{
				Ambiente salvo = ambRepo.findOne( new Long(i) );
				
				progMusicService.geraTransmissao( salvo );;

				System.out.println(salvo);
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("fim");
	}
	
	
	private static void criarVariosAmbientes( ApplicationContext ctx )
	{
		UsuarioRepository usuRepo = ctx.getBean( UsuarioRepository.class );
		AmbienteService ambienteService = ctx.getBean( AmbienteService.class );
		ProgramacaoMusicalService progMusicService = ctx.getBean( ProgramacaoMusicalService.class );
		
		Usuario usu = usuRepo.findByLogin( "fpazin" );
	
		Integer total = 200;

		Integer inicio = 5;

		for ( int i = inicio; i < total; i++ )
		{

			try
			{
				Ambiente novo = new Ambiente();
				
				novo.setNome( "Ambiente " + i );
				novo.setLogin( "ambiente" + i );
				novo.setPassword( "123456" );
				novo.setCliente( usu.getCliente() );			
				
				Ambiente salvo = ambienteService.saveAmbiente( novo );
				
				progMusicService.verificaECriaProgramacaoDefault( salvo );

				System.out.println(salvo);
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("fim");
	}
	
}
