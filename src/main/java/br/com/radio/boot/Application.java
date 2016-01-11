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

import br.com.radio.service.MidiaService;

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
		
		MidiaService midiaService = ctx.getBean( MidiaService.class );
		
		midiaService.getNewMusicFromFileSystem();
		
		
//		AmbienteRepository ambServ = ctx.getBean( AmbienteRepository.class );
//			
//		Ambiente ambiente = ambServ.findOne( 1L );
//		
//		midiaService.associaTodasMidiasParaAmbiente( ambiente );
		
		System.out.println("fim");
																																																								
		
	}
	
}
