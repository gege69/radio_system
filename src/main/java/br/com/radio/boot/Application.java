package br.com.radio.boot;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.radio.dto.midia.MidiaFilter;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Midia;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.service.AmbienteService;
import br.com.radio.service.MidiaService;
import br.com.radio.service.ProgramacaoMusicalService;
import br.com.radio.util.UtilsDates;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
//@EnableTransactionManagement
public class Application {
				
	public static void main(String[] aaaa)
	{
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
//		Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
//		
		MidiaService midiaService = ctx.getBean( MidiaService.class );
		AmbienteRepository ambienteRepo = ctx.getBean( AmbienteRepository.class );
//		
		Ambiente ambiente = ambienteRepo.findOne( 1L );
//		Ambiente ambiente = null;
		
//		String search = null;
		
		MidiaFilter filter = MidiaFilter.create()
								.setAmbiente( ambiente )
								.setVerificaValidade( true )
								.setCodigoCategoria( "comercial" );
		
		List<Midia> list = midiaService.findMidiasCategorias( filter );
		
		list.forEach( m -> {
			System.out.println(m.getNome() + "  " + UtilsDates.format( m.getDataInicioValidade() ) + "  " + UtilsDates.format( m.getDataFimValidade() )  );
		});

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
