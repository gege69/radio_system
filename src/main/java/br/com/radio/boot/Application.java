package br.com.radio.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import br.com.radio.model.Ambiente;
import br.com.radio.service.MidiaService;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


@SpringBootApplication
@ComponentScan( basePackages = { "br.com.radio.*" } )
@EnableConfigurationProperties
@ActiveProfiles({"default"})
public class Application {
																
	public static void main(String[] aaaa)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
		
//		MidiaService midiaService = ctx.getBean( MidiaService.class );
		
//		midiaService.syncMusicFileSystem();
		
		System.out.println("fim");
																																																								
		
	}
	
}
