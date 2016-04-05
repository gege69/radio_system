package br.com.radio.boot;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.service.AmbienteService;
import br.com.radio.service.MidiaService;
import br.com.radio.service.ProgramacaoMusicalService;
import br.com.radio.service.programacaomusical.ListaInesgotavel;
import br.com.radio.service.programacaomusical.ListaInesgotavelRandom;
import br.com.radio.service.programacaomusical.ListaInesgotavelRandomAlternada;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
//@EnableTransactionManagement
public class Application {
				
	public static void main(String[] aaaa)
	{
//		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
////		Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
//		
//		MidiaService midiaService = ctx.getBean( MidiaService.class );
//		CategoriaRepository categoriaRepo = ctx.getBean( CategoriaRepository.class );
//		AmbienteRepository ambienteRepo = ctx.getBean( AmbienteRepository.class );
//		
//		Ambiente ambiente = ambienteRepo.findOne( 1L );
		



//		criarVariosAmbientes( ctx );
		
//		criaProgramacaoVariosAmbientes( ctx );
																																																								
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
