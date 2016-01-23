package br.com.radio.boot;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.radio.model.Ambiente;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.service.ProgramacaoMusicalService;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
//@EnableTransactionManagement
public class Application {
				
	public static void main(String[] aaaa)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);

		Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
		
//		MidiaService midiaService = ctx.getBean( MidiaService.class );
//		
//		midiaService.getNewMusicFromFileSystem();

		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
		
		ProgramacaoMusicalService progMusicalService = ctx.getBean( ProgramacaoMusicalService.class );

		Ambiente amb = ambRepo.findOne( 1L );
		
		progMusicalService.criaProgramacaoMusicalDoDiaParaAmbiente( amb );	
		
		System.out.println("fim");
																																																								
	}
	
}
