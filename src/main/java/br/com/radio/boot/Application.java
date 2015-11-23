package br.com.radio.boot;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Evento;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.EventoRepository;
import br.com.radio.service.EventoService;
import br.com.radio.service.MidiaService;
import br.com.radio.service.ProgramacaoMusicalService;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
public class Application {
																
	public static void main(String[] aaaa)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
		
		MidiaService midiaService = ctx.getBean( MidiaService.class );
		
		ProgramacaoMusicalService serv = ctx.getBean( ProgramacaoMusicalService.class );
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
																																																																																																																			
		Ambiente ambiente = ambRepo.findOne( 2L );

		EventoRepository eRepo = ctx.getBean( EventoRepository.class );

//		List<Evento> es = eRepo.findByHorarioAndIdAmbiente( ambiente.getIdAmbiente() );

//		es.forEach( e -> System.out.println(e) );
		
//		EventoService service = ctx.getBean( EventoService.class );
		
//		service.existeEventoAgoraPorAmbiente( ambiente );
		
//		midiaService.getNewMusicFromFileSystem();
		
//		serv.geraTransmissao( ambiente );
		
		System.out.println("fim");
																																																								
		
	}
	
}
